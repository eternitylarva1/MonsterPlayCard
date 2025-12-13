package EveryMonsterPlayCard.intent2card.template;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;
import EveryMonsterPlayCard.monstercards.cards.MonsterPowerCard;

/**
 * 能力卡牌模板
 * 专门用于创建能力类型的怪物卡牌
 */
public class PowerCardTemplate extends AbstractCardTemplate {
    
    public PowerCardTemplate() {
        super("能力卡牌模板", "power_card", 
              new IntentType[]{IntentType.BUFF, IntentType.DEBUFF}, 
              3);
    }
    
    @Override
    protected AbstractCard createCardInstance(String cardId, String cardName, String cardImage, 
                                          int cardCost, String cardDescription, 
                                          MonsterIntent intent, IntentParameters parameters) {
        
        // 获取能力参数
        int magicNumber = parameters.hasMagicNumber() ? parameters.getMagicNumber() : intent.getAmount();
        MonsterPowerCard.PowerType powerType = getPowerType(intent, parameters);
        CardTarget target = determineCardTarget(intent, parameters);
        
        // 创建怪物能力卡牌
        MonsterPowerCard card = new MonsterPowerCard(cardId, cardName, cardImage, 
                                                   cardCost, powerType, magicNumber, target);
        
        // 设置描述
        card.rawDescription = cardDescription;
        card.initializeDescription();
        
        return card;
    }
    
    @Override
    protected CardType determineCardType(MonsterIntent intent, IntentParameters parameters) {
        return CardType.POWER;
    }
    
    @Override
    protected CardColor determineCardColor(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardColor")) {
            String colorStr = parameters.getStringParameter("cardColor");
            try {
                return CardColor.valueOf(colorStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据能力类型调整颜色
        MonsterPowerCard.PowerType powerType = getPowerType(intent, parameters);
        switch (powerType) {
            case STRENGTH:
                return CardColor.RED; // 力量使用红色
            case DEXTERITY:
                return CardColor.GREEN; // 敏捷使用绿色
            case ARTIFACT:
                return CardColor.BLUE; // 神器使用蓝色
            default:
                return CardColor.PURPLE; // 其他能力使用紫色
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
        
        // 根据能力强度确定稀有度
        int magicNumber = parameters.hasMagicNumber() ? parameters.getMagicNumber() : intent.getAmount();
        if (magicNumber >= 10) {
            return CardRarity.RARE;
        } else if (magicNumber >= 5) {
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
        
        // 根据意图类型确定目标
        switch (intent.getType()) {
            case BUFF:
                return CardTarget.SELF;
            case DEBUFF:
                return CardTarget.ENEMY;
            default:
                return CardTarget.SELF;
        }
    }
    
    @Override
    protected String getCardImagePath(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardImage")) {
            return parameters.getStringParameter("cardImage");
        }
        
        // 根据能力类型选择图片
        MonsterPowerCard.PowerType powerType = getPowerType(intent, parameters);
        switch (powerType) {
            case STRENGTH:
                return "everyMonsterPlayCardResources/images/cards/monster_strength_power.png";
            case DEXTERITY:
                return "everyMonsterPlayCardResources/images/cards/monster_dexterity_power.png";
            case ARTIFACT:
                return "everyMonsterPlayCardResources/images/cards/monster_artifact_power.png";
            default:
                return "everyMonsterPlayCardResources/images/cards/monster_power.png";
        }
    }
    
    @Override
    protected String generateCardName(MonsterIntent intent, IntentParameters parameters) {
        String baseName = super.generateCardName(intent, parameters);
        
        // 根据能力类型调整名称
        MonsterPowerCard.PowerType powerType = getPowerType(intent, parameters);
        switch (powerType) {
            case STRENGTH:
                baseName = baseName.replace("增益", "力量");
                break;
            case DEXTERITY:
                baseName = baseName.replace("增益", "敏捷");
                break;
            case ARTIFACT:
                baseName = baseName.replace("增益", "神器");
                break;
        }
        
        // 根据能力强度添加修饰词
        int magicNumber = parameters.hasMagicNumber() ? parameters.getMagicNumber() : intent.getAmount();
        if (magicNumber > 0) {
            String modifier = getPowerModifier(powerType, magicNumber);
            if (!modifier.isEmpty()) {
                baseName = modifier + baseName;
            }
        }
        
        return baseName;
    }
    
    @Override
    protected String generateCardDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder description = new StringBuilder();
        
        int magicNumber = parameters.hasMagicNumber() ? parameters.getMagicNumber() : intent.getAmount();
        MonsterPowerCard.PowerType powerType = getPowerType(intent, parameters);
        
        // 根据能力类型生成描述
        switch (powerType) {
            case STRENGTH:
                description.append("获得 !M! 点力量。");
                break;
            case DEXTERITY:
                description.append("获得 !M! 点敏捷。");
                break;
            case ARTIFACT:
                description.append("获得 !M! 点神器。");
                break;
            default:
                description.append("获得 !M! 点能力。");
                break;
        }
        
        // 添加能力特有的描述
        if (magicNumber >= 10) {
            description.append(" 这是一个强大的能力。");
        }
        
        // 添加持续时间描述
        if (parameters.hasParameter("duration")) {
            int duration = parameters.getIntParameter("duration");
            if (duration > 0) {
                description.append(" 持续 ").append(duration).append(" 回合。");
            } else {
                description.append(" 永久效果。");
            }
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
        
        // 应用能力特有的特殊效果
        if (parameters.hasParameter("duration")) {
            int duration = parameters.getIntParameter("duration");
            if (duration > 0) {
                card.baseMagicNumber = duration;
                card.magicNumber = card.baseMagicNumber;
            }
        }
        
        // 设置能力类型标记
        if (card instanceof MonsterPowerCard) {
            MonsterPowerCard powerCard = (MonsterPowerCard) card;
            try {
                java.lang.reflect.Field powerTypeField = MonsterPowerCard.class.getDeclaredField("powerType");
                powerTypeField.setAccessible(true);
                powerTypeField.set(powerCard, getPowerType(intent, parameters));
                
                java.lang.reflect.Field powerAmountField = MonsterPowerCard.class.getDeclaredField("powerAmount");
                powerAmountField.setAccessible(true);
                powerAmountField.set(powerCard, parameters.hasMagicNumber() ? parameters.getMagicNumber() : intent.getAmount());
            } catch (Exception e) {
                System.err.println("Error setting MonsterPowerCard fields: " + e.getMessage());
            }
        }
    }
    
    @Override
    protected String generateSpecialEffectDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder effects = new StringBuilder(super.generateSpecialEffectDescription(intent, parameters));
        
        // 添加能力特有的特殊效果描述
        if (parameters.hasParameter("stackable") && parameters.getBooleanParameter("stackable")) {
            effects.append(" 可叠加。");
        }
        
        if (parameters.hasParameter("unremovable") && parameters.getBooleanParameter("unremovable")) {
            effects.append(" 不可移除。");
        }
        
        if (parameters.hasParameter("scaling") && parameters.getBooleanParameter("scaling")) {
            effects.append(" 成长性。");
        }
        
        if (parameters.hasParameter("synergy") && parameters.getBooleanParameter("synergy")) {
            effects.append(" 协同效应。");
        }
        
        return effects.toString();
    }
    
    /**
     * 获取能力类型
     */
    private MonsterPowerCard.PowerType getPowerType(MonsterIntent intent, IntentParameters parameters) {
        // 检查是否有自定义能力类型
        if (parameters.hasParameter("powerType")) {
            String powerTypeStr = parameters.getStringParameter("powerType");
            try {
                return MonsterPowerCard.PowerType.valueOf(powerTypeStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据意图类型和参数确定能力类型
        switch (intent.getType()) {
            case BUFF:
                // 根据增益类型确定能力类型
                if (parameters.hasParameter("buffType")) {
                    String buffType = parameters.getStringParameter("buffType");
                    if (buffType.contains("力量") || buffType.contains("strength")) {
                        return MonsterPowerCard.PowerType.STRENGTH;
                    } else if (buffType.contains("敏捷") || buffType.contains("dexterity")) {
                        return MonsterPowerCard.PowerType.DEXTERITY;
                    } else if (buffType.contains("神器") || buffType.contains("artifact")) {
                        return MonsterPowerCard.PowerType.ARTIFACT;
                    }
                }
                // 默认增益使用力量
                return MonsterPowerCard.PowerType.STRENGTH;
                
            case DEBUFF:
                // 减益意图默认使用力量（负值）
                return MonsterPowerCard.PowerType.STRENGTH;
                
            default:
                return MonsterPowerCard.PowerType.STRENGTH;
        }
    }
    
    /**
     * 根据能力类型和数量获取修饰词
     */
    private String getPowerModifier(MonsterPowerCard.PowerType powerType, int amount) {
        switch (powerType) {
            case STRENGTH:
                if (amount >= 15) return "狂暴";
                if (amount >= 10) return "强力";
                if (amount >= 5) return "增强";
                return "基础";
                
            case DEXTERITY:
                if (amount >= 15) return "迅捷";
                if (amount >= 10) return "灵活";
                if (amount >= 5) return "敏捷";
                return "基础";
                
            case ARTIFACT:
                if (amount >= 10) return "神圣";
                if (amount >= 5) return "魔法";
                return "基础";
                
            default:
                if (amount >= 10) return "强大";
                if (amount >= 5) return "普通";
                return "微弱";
        }
    }
    
    @Override
    public double calculateMatchScore(MonsterIntent intent, IntentParameters parameters) {
        double baseScore = super.calculateMatchScore(intent, parameters);
        
        // 对能力意图有更高的匹配度
        if (intent.getType() == IntentType.BUFF || intent.getType() == IntentType.DEBUFF) {
            baseScore += 0.4;
        }
        
        // 如果有能力相关参数，提高匹配度
        if (parameters.hasMagicNumber() || parameters.hasParameter("powerType") || 
            parameters.hasParameter("buffType") || parameters.hasParameter("debuffType")) {
            baseScore += 0.2;
        }
        
        // 如果明确指定使用能力模板，大幅提高匹配度
        if (parameters.hasParameter("usePowerTemplate") && 
            parameters.getBooleanParameter("usePowerTemplate")) {
            baseScore += 0.3;
        }
        
        return Math.min(1.0, baseScore);
    }
    
    @Override
    public String getTemplateDescription() {
        return "能力卡牌模板，专门用于创建能力类型的怪物卡牌，支持力量、敏捷、神器等多种能力类型";
    }
}