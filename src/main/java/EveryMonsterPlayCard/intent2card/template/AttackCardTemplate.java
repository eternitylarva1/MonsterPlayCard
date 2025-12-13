package EveryMonsterPlayCard.intent2card.template;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;
import EveryMonsterPlayCard.monstercards.cards.MonsterAttackCard;

/**
 * 攻击卡牌模板
 * 专门用于创建攻击类型的怪物卡牌
 */
public class AttackCardTemplate extends AbstractCardTemplate {
    
    public AttackCardTemplate() {
        super("攻击卡牌模板", "attack_card", 
              new IntentType[]{IntentType.ATTACK, IntentType.STRONG}, 
              2);
    }
    
    @Override
    protected AbstractCard createCardInstance(String cardId, String cardName, String cardImage, 
                                          int cardCost, String cardDescription, 
                                          MonsterIntent intent, IntentParameters parameters) {
        
        // 获取攻击参数
        int damage = parameters.hasDamage() ? parameters.getDamage() : intent.getAmount();
        CardTarget target = determineCardTarget(intent, parameters);
        
        // 获取攻击效果
        AbstractGameAction.AttackEffect attackEffect = getAttackEffect(intent, parameters);
        
        // 创建怪物攻击卡牌
        MonsterAttackCard card = new MonsterAttackCard(cardId, cardName, cardImage, 
                                                     cardCost, damage, target, attackEffect);
        
        // 设置描述
        card.rawDescription = cardDescription;
        card.initializeDescription();
        
        return card;
    }
    
    @Override
    protected CardType determineCardType(MonsterIntent intent, IntentParameters parameters) {
        return CardType.ATTACK;
    }
    
    @Override
    protected CardColor determineCardColor(MonsterIntent intent, IntentParameters parameters) {
        // 攻击卡牌主要使用红色
        if (parameters.hasParameter("cardColor")) {
            String colorStr = parameters.getStringParameter("cardColor");
            try {
                return CardColor.valueOf(colorStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据攻击强度调整颜色
        int damage = parameters.hasDamage() ? parameters.getDamage() : intent.getAmount();
        if (damage >= 30) {
            return CardColor.PURPLE; // 高伤害使用紫色
        } else if (damage >= 20) {
            return CardColor.RED; // 中等伤害使用红色
        } else {
            return CardColor.RED; // 低伤害也使用红色
        }
    }
    
    @Override
    protected CardRarity determineCardRarity(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardRarity")) {
            String rarityStr = parameters.getStringParameter("cardRarity");
            try {
                return CardRarity.valueOf(rarityStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据伤害值确定稀有度
        int damage = parameters.hasDamage() ? parameters.getDamage() : intent.getAmount();
        if (damage >= 40) {
            return CardRarity.RARE;
        } else if (damage >= 25) {
            return CardRarity.UNCOMMON;
        } else {
            return CardRarity.BASIC;
        }
    }
    
    @Override
    protected CardTarget determineCardTarget(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardTarget")) {
            String targetStr = parameters.getStringParameter("cardTarget");
            try {
                return CardTarget.valueOf(targetStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 检查是否为多目标攻击
        if (parameters.hasParameter("multiTarget") && parameters.getBooleanParameter("multiTarget")) {
            return CardTarget.ALL_ENEMY;
        }
        
        // 检查是否为随机目标
        if (parameters.hasParameter("randomTarget") && parameters.getBooleanParameter("randomTarget")) {
            return CardTarget.ENEMY;
        }
        
        return CardTarget.ENEMY;
    }
    
    @Override
    protected String getCardImagePath(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardImage")) {
            return parameters.getStringParameter("cardImage");
        }
        
        // 根据攻击类型选择图片
        if (intent.getType() == IntentType.STRONG) {
            return "everyMonsterPlayCardResources/images/cards/monster_strong_attack.png";
        }
        
        // 根据攻击效果选择图片
        AbstractGameAction.AttackEffect effect = getAttackEffect(intent, parameters);
        switch (effect) {
            case SLASH_HORIZONTAL:
                return "everyMonsterPlayCardResources/images/cards/monster_slash_attack.png";
            case BLUNT_HEAVY:
                return "everyMonsterPlayCardResources/images/cards/monster_heavy_attack.png";
            case SMASH:
                return "everyMonsterPlayCardResources/images/cards/monster_smash_attack.png";
            case FIRE:
                return "everyMonsterPlayCardResources/images/cards/monster_fire_attack.png";
            case POISON:
                return "everyMonsterPlayCardResources/images/cards/monster_poison_attack.png";
            default:
                return "everyMonsterPlayCardResources/images/cards/monster_attack.png";
        }
    }
    
    @Override
    protected String generateCardName(MonsterIntent intent, IntentParameters parameters) {
        String baseName = super.generateCardName(intent, parameters);
        
        // 根据攻击类型调整名称
        if (intent.getType() == IntentType.STRONG) {
            baseName = baseName.replace("攻击", "强击");
        }
        
        // 根据攻击效果调整名称
        AbstractGameAction.AttackEffect effect = getAttackEffect(intent, parameters);
        switch (effect) {
            case SLASH_HORIZONTAL:
                baseName = baseName.replace("攻击", "斩击");
                break;
            case BLUNT_HEAVY:
                baseName = baseName.replace("攻击", "重击");
                break;
            case SMASH:
                baseName = baseName.replace("攻击", "猛击");
                break;
            case FIRE:
                baseName = baseName.replace("攻击", "火焰攻击");
                break;
            case POISON:
                baseName = baseName.replace("攻击", "毒击");
                break;
        }
        
        // 根据攻击次数调整名称
        if (parameters.hasParameter("hitCount")) {
            int hitCount = parameters.getIntParameter("hitCount");
            if (hitCount > 1) {
                baseName = hitCount + "连" + baseName;
            }
        }
        
        return baseName;
    }
    
    @Override
    protected String generateCardDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder description = new StringBuilder();
        
        int damage = parameters.hasDamage() ? parameters.getDamage() : intent.getAmount();
        
        // 基础伤害描述
        if (parameters.hasParameter("hitCount") && parameters.getIntParameter("hitCount") > 1) {
            int hitCount = parameters.getIntParameter("hitCount");
            int damagePerHit = damage / hitCount;
            description.append("造成 ").append(hitCount).append("次伤害，每次 !D! 点伤害。");
        } else {
            description.append("造成 !D! 点伤害。");
        }
        
        // 添加攻击效果描述
        AbstractGameAction.AttackEffect effect = getAttackEffect(intent, parameters);
        switch (effect) {
            case SLASH_HORIZONTAL:
                description.append(" 斩击效果。");
                break;
            case BLUNT_HEAVY:
                description.append(" 重击效果。");
                break;
            case SMASH:
                description.append(" 猛击效果。");
                break;
            case FIRE:
                description.append(" 火焰伤害。");
                break;
            case POISON:
                description.append(" 中毒效果。");
                break;
        }
        
        // 添加多目标描述
        if (parameters.hasParameter("multiTarget") && parameters.getBooleanParameter("multiTarget")) {
            description.append(" 攻击所有敌人。");
        }
        
        // 添加特殊效果描述
        String specialDescription = generateSpecialEffectDescription(intent, parameters);
        if (specialDescription != null && !specialDescription.isEmpty()) {
            description.append(" ").append(specialDescription);
        }
        
        return description.toString();
    }
    
    @Override
    protected void applySpecialEffects(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        super.applySpecialEffects(card, intent, parameters);
        
        // 应用攻击特有的特殊效果
        if (parameters.hasParameter("hitCount")) {
            int hitCount = parameters.getIntParameter("hitCount");
            if (hitCount > 1) {
                card.baseMagicNumber = hitCount;
                card.magicNumber = card.baseMagicNumber;
            }
        }
        
        // 设置攻击效果
        if (card instanceof MonsterAttackCard) {
            MonsterAttackCard attackCard = (MonsterAttackCard) card;
            try {
                java.lang.reflect.Field attackEffectField = MonsterAttackCard.class.getDeclaredField("attackEffect");
                attackEffectField.setAccessible(true);
                attackEffectField.set(attackCard, getAttackEffect(intent, parameters));
            } catch (Exception e) {
                System.err.println("Error setting MonsterAttackCard attackEffect field: " + e.getMessage());
            }
        }
    }
    
    @Override
    protected String generateSpecialEffectDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder effects = new StringBuilder(super.generateSpecialEffectDescription(intent, parameters));
        
        // 添加攻击特有的特殊效果描述
        if (parameters.hasParameter("piercing") && parameters.getBooleanParameter("piercing")) {
            effects.append(" 穿透。");
        }
        
        if (parameters.hasParameter("critical") && parameters.getBooleanParameter("critical")) {
            effects.append(" 暴击。");
        }
        
        return effects.toString();
    }
    
    /**
     * 获取攻击效果
     */
    private AbstractGameAction.AttackEffect getAttackEffect(MonsterIntent intent, IntentParameters parameters) {
        // 检查是否有自定义攻击效果
        if (parameters.hasParameter("attackEffect")) {
            String effectStr = parameters.getStringParameter("attackEffect");
            try {
                return AbstractGameAction.AttackEffect.valueOf(effectStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据意图类型和参数确定攻击效果
        if (intent.getType() == IntentType.STRONG) {
            return AbstractGameAction.AttackEffect.BLUNT_HEAVY;
        }
        
        // 根据伤害值确定攻击效果
        int damage = parameters.hasDamage() ? parameters.getDamage() : intent.getAmount();
        if (damage >= 30) {
            return AbstractGameAction.AttackEffect.SMASH;
        } else if (damage >= 20) {
            return AbstractGameAction.AttackEffect.BLUNT_HEAVY;
        } else {
            return AbstractGameAction.AttackEffect.SLASH_HORIZONTAL;
        }
    }
    
    @Override
    public double calculateMatchScore(MonsterIntent intent, IntentParameters parameters) {
        double baseScore = super.calculateMatchScore(intent, parameters);
        
        // 对攻击意图有更高的匹配度
        if (intent.getType() == IntentType.ATTACK || intent.getType() == IntentType.STRONG) {
            baseScore += 0.4;
        }
        
        // 如果有攻击相关参数，提高匹配度
        if (parameters.hasDamage() || parameters.hasParameter("attackEffect")) {
            baseScore += 0.2;
        }
        
        return Math.min(1.0, baseScore);
    }
    
    @Override
    public String getTemplateDescription() {
        return "攻击卡牌模板，专门用于创建攻击类型的怪物卡牌，支持多种攻击效果和目标类型";
    }
}