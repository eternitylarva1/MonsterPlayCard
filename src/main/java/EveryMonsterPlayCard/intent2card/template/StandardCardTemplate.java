package EveryMonsterPlayCard.intent2card.template;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;

/**
 * 标准卡牌模板
 * 用于创建标准的怪物卡牌
 */
public class StandardCardTemplate extends AbstractCardTemplate {
    
    public StandardCardTemplate() {
        super("标准卡牌模板", "standard_card", 
              new IntentType[]{IntentType.ATTACK, IntentType.DEFEND, IntentType.BUFF, 
                             IntentType.DEBUFF, IntentType.STRONG, IntentType.ESCAPE, IntentType.UNKNOWN}, 
              1);
    }
    
    @Override
    protected AbstractCard createCardInstance(String cardId, String cardName, String cardImage, 
                                          int cardCost, String cardDescription, 
                                          MonsterIntent intent, IntentParameters parameters) {
        // 创建一个通用的怪物卡牌
        AbstractCard card = new AbstractCard(cardId, cardName, cardImage, cardCost, cardDescription, 
                                          determineCardType(intent, parameters), 
                                          determineCardColor(intent, parameters), 
                                          determineCardRarity(intent, parameters), 
                                          determineCardTarget(intent, parameters)) {
            
            @Override
            public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, com.megacrit.cardcrawl.monsters.AbstractMonster m) {
                // 标准卡牌的use方法留空，由具体的卡牌类型处理
            }
            
            @Override
            public AbstractCard makeCopy() {
                return new StandardCardTemplate().createCard(intent, parameters);
            }
            
            @Override
            public void upgrade() {
                // 标准卡牌不支持升级
            }
        };
        
        return card;
    }
    
    @Override
    protected CardType determineCardType(MonsterIntent intent, IntentParameters parameters) {
        // 使用父类的默认逻辑，但添加一些特殊处理
        CardType baseType = super.determineCardType(intent, parameters);
        
        // 检查是否有特殊类型标记
        if (parameters.hasParameter("overrideCardType")) {
            String typeStr = parameters.getStringParameter("overrideCardType");
            try {
                return CardType.valueOf(typeStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        return baseType;
    }
    
    @Override
    protected CardColor determineCardColor(MonsterIntent intent, IntentParameters parameters) {
        // 检查是否有特殊颜色标记
        if (parameters.hasParameter("cardColor")) {
            String colorStr = parameters.getStringParameter("cardColor");
            try {
                return CardColor.valueOf(colorStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据意图类型调整颜色
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                return CardColor.RED; // 攻击卡牌使用红色
            case DEFEND:
                return CardColor.GREEN; // 防御卡牌使用绿色
            case BUFF:
            case DEBUFF:
                return CardColor.BLUE; // 能力卡牌使用蓝色
            case ESCAPE:
                return CardColor.COLORLESS; // 逃跑卡牌使用无色
            case UNKNOWN:
            default:
                return CardColor.CURSE; // 未知意图使用诅咒色
        }
    }
    
    @Override
    protected CardRarity determineCardRarity(MonsterIntent intent, IntentParameters parameters) {
        // 检查是否有特殊稀有度标记
        if (parameters.hasParameter("cardRarity")) {
            String rarityStr = parameters.getStringParameter("cardRarity");
            try {
                return CardRarity.valueOf(rarityStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据意图复杂度确定稀有度
        if (parameters.hasParameter("complexity")) {
            int complexity = parameters.getIntParameter("complexity");
            if (complexity >= 8) {
                return CardRarity.RARE;
            } else if (complexity >= 5) {
                return CardRarity.UNCOMMON;
            }
        }
        
        // 根据意图数量确定稀有度
        int amount = intent.getAmount();
        if (amount >= 30) {
            return CardRarity.RARE;
        } else if (amount >= 15) {
            return CardRarity.UNCOMMON;
        }
        
        return CardRarity.BASIC;
    }
    
    @Override
    protected CardTarget determineCardTarget(MonsterIntent intent, IntentParameters parameters) {
        // 检查是否有特殊目标标记
        if (parameters.hasParameter("cardTarget")) {
            String targetStr = parameters.getStringParameter("cardTarget");
            try {
                return CardTarget.valueOf(targetStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 检查是否为多目标
        if (parameters.hasParameter("multiTarget") && parameters.getBooleanParameter("multiTarget")) {
            return CardTarget.ALL_ENEMY;
        }
        
        return super.determineCardTarget(intent, parameters);
    }
    
    @Override
    protected String getCardImagePath(MonsterIntent intent, IntentParameters parameters) {
        // 检查是否有自定义图片路径
        if (parameters.hasParameter("cardImage")) {
            return parameters.getStringParameter("cardImage");
        }
        
        // 根据意图类型返回不同的图片
        switch (intent.getType()) {
            case ATTACK:
                return "everyMonsterPlayCardResources/images/cards/monster_attack.png";
            case DEFEND:
                return "everyMonsterPlayCardResources/images/cards/monster_defend.png";
            case BUFF:
                return "everyMonsterPlayCardResources/images/cards/monster_buff.png";
            case DEBUFF:
                return "everyMonsterPlayCardResources/images/cards/monster_debuff.png";
            case STRONG:
                return "everyMonsterPlayCardResources/images/cards/monster_strong.png";
            case ESCAPE:
                return "everyMonsterPlayCardResources/images/cards/monster_escape.png";
            case UNKNOWN:
            default:
                return "everyMonsterPlayCardResources/images/cards/monster_unknown.png";
        }
    }
    
    @Override
    protected String generateCardName(MonsterIntent intent, IntentParameters parameters) {
        String baseName = super.generateCardName(intent, parameters);
        
        // 根据意图数量添加修饰词
        int amount = intent.getAmount();
        if (amount > 0) {
            String modifier = getAmountModifier(amount);
            if (!modifier.isEmpty()) {
                baseName = modifier + baseName;
            }
        }
        
        return baseName;
    }
    
    @Override
    protected String generateCardDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder description = new StringBuilder();
        
        // 基础描述
        String baseDescription = super.generateCardDescription(intent, parameters);
        description.append(baseDescription);
        
        // 添加数量相关的描述
        int amount = intent.getAmount();
        if (amount > 0) {
            switch (intent.getType()) {
                case ATTACK:
                case STRONG:
                    if (parameters.hasParameter("hitCount") && parameters.getIntParameter("hitCount") > 1) {
                        int hitCount = parameters.getIntParameter("hitCount");
                        description.append(" 分").append(hitCount).append("次造成伤害。");
                    }
                    break;
                case DEFEND:
                    if (amount >= 20) {
                        description.append(" 这是一个强大的防御。");
                    }
                    break;
                case BUFF:
                case DEBUFF:
                    if (amount >= 10) {
                        description.append(" 这是一个强大的效果。");
                    }
                    break;
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
        // 应用特殊标记
        if (parameters.hasParameter("isEthereal") && parameters.getBooleanParameter("isEthereal")) {
            card.isEthereal = true;
        }
        
        if (parameters.hasParameter("isExhaust") && parameters.getBooleanParameter("isExhaust")) {
            card.exhaust = true;
        }
        
        if (parameters.hasParameter("isInnate") && parameters.getBooleanParameter("isInnate")) {
            card.isInnate = true;
        }
        
        if (parameters.hasParameter("isRetain") && parameters.getBooleanParameter("isRetain")) {
            card.retain = true;
        }
        
        if (parameters.hasParameter("isSelfRetain") && parameters.getBooleanParameter("isSelfRetain")) {
            card.selfRetain = true;
        }
        
        // 应用特殊属性
        if (parameters.hasParameter("magicNumber")) {
            card.baseMagicNumber = parameters.getIntParameter("magicNumber");
            card.magicNumber = card.baseMagicNumber;
        }
    }
    
    @Override
    protected String generateSpecialEffectDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder effects = new StringBuilder();
        
        if (parameters.hasParameter("isEthereal") && parameters.getBooleanParameter("isEthereal")) {
            effects.append("虚无。");
        }
        
        if (parameters.hasParameter("isExhaust") && parameters.getBooleanParameter("isExhaust")) {
            effects.append("消耗。");
        }
        
        if (parameters.hasParameter("isInnate") && parameters.getBooleanParameter("isInnate")) {
            effects.append("天生。");
        }
        
        return effects.toString();
    }
    
    /**
     * 根据数量获取修饰词
     */
    private String getAmountModifier(int amount) {
        if (amount >= 50) {
            return "致命的";
        } else if (amount >= 30) {
            return "强大的";
        } else if (amount >= 20) {
            return "强力的";
        } else if (amount >= 15) {
            return "中等的";
        } else if (amount >= 10) {
            return "普通的";
        } else if (amount >= 5) {
            return "弱小的";
        } else {
            return "微弱的";
        }
    }
    
    @Override
    public double calculateMatchScore(MonsterIntent intent, IntentParameters parameters) {
        double baseScore = super.calculateMatchScore(intent, parameters);
        
        // 标准模板对所有意图都有较好的匹配度
        baseScore += 0.2;
        
        // 如果有标准参数，提高匹配度
        if (parameters.hasParameter("useStandardTemplate") && 
            parameters.getBooleanParameter("useStandardTemplate")) {
            baseScore += 0.3;
        }
        
        return Math.min(1.0, baseScore);
    }
    
    @Override
    public String getTemplateDescription() {
        return "标准卡牌模板，适用于所有类型的怪物意图，提供基础的卡牌创建功能";
    }
}