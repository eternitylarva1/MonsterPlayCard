package EveryMonsterPlayCard.intent2card.template;

import java.util.HashMap;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;

/**
 * 自定义卡牌模板
 * 允许用户完全自定义卡牌的创建过程
 */
public class CustomCardTemplate extends AbstractCardTemplate {
    
    private Map<String, Object> customProperties;
    private CardCreationHandler creationHandler;
    
    public CustomCardTemplate() {
        super("自定义卡牌模板", "custom_card", 
              new IntentType[]{IntentType.ATTACK, IntentType.DEFEND, IntentType.BUFF, 
                             IntentType.DEBUFF, IntentType.STRONG, IntentType.ESCAPE, IntentType.UNKNOWN}, 
              4);
        this.customProperties = new HashMap<>();
    }
    
    public CustomCardTemplate(String templateName, String templateId, IntentType[] supportedTypes) {
        super(templateName, templateId, supportedTypes, 4);
        this.customProperties = new HashMap<>();
    }
    
    /**
     * 设置自定义属性
     */
    public void setCustomProperty(String key, Object value) {
        customProperties.put(key, value);
    }
    
    /**
     * 获取自定义属性
     */
    public Object getCustomProperty(String key) {
        return customProperties.get(key);
    }
    
    /**
     * 设置卡牌创建处理器
     */
    public void setCreationHandler(CardCreationHandler handler) {
        this.creationHandler = handler;
    }
    
    @Override
    protected AbstractCard createCardInstance(String cardId, String cardName, String cardImage, 
                                          int cardCost, String cardDescription, 
                                          MonsterIntent intent, IntentParameters parameters) {
        
        // 如果有自定义创建处理器，使用处理器创建卡牌
        if (creationHandler != null) {
            return creationHandler.createCard(cardId, cardName, cardImage, cardCost, cardDescription, 
                                         intent, parameters, this);
        }
        
        // 否则使用默认的自定义创建逻辑
        return createCustomCard(cardId, cardName, cardImage, cardCost, cardDescription, 
                             intent, parameters);
    }
    
    /**
     * 创建自定义卡牌
     */
    private AbstractCard createCustomCard(String cardId, String cardName, String cardImage, 
                                      int cardCost, String cardDescription, 
                                      MonsterIntent intent, IntentParameters parameters) {
        
        // 获取自定义卡牌属性
        CardType cardType = getCustomCardType(intent, parameters);
        CardColor cardColor = getCustomCardColor(intent, parameters);
        CardRarity cardRarity = getCustomCardRarity(intent, parameters);
        CardTarget cardTarget = getCustomCardTarget(intent, parameters);
        
        // 创建自定义卡牌
        AbstractCard card = new AbstractCard(cardId, cardName, cardImage, cardCost, cardDescription, 
                                          cardType, cardColor, cardRarity, cardTarget) {
            
            @Override
            public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, com.megacrit.cardcrawl.monsters.AbstractMonster m) {
                // 自定义卡牌的use方法留空，由具体的卡牌类型处理
            }
            
            @Override
            public AbstractCard makeCopy() {
                return new CustomCardTemplate().createCard(intent, parameters);
            }
            
            @Override
            public void upgrade() {
                // 自定义卡牌的升级逻辑
                if (!upgraded) {
                    upgradeName();
                    applyCustomUpgrade(this, intent, parameters);
                    initializeDescription();
                }
            }
        };
        
        return card;
    }
    
    @Override
    protected CardType determineCardType(MonsterIntent intent, IntentParameters parameters) {
        return getCustomCardType(intent, parameters);
    }
    
    @Override
    protected CardColor determineCardColor(MonsterIntent intent, IntentParameters parameters) {
        return getCustomCardColor(intent, parameters);
    }
    
    @Override
    protected CardRarity determineCardRarity(MonsterIntent intent, IntentParameters parameters) {
        return getCustomCardRarity(intent, parameters);
    }
    
    @Override
    protected CardTarget determineCardTarget(MonsterIntent intent, IntentParameters parameters) {
        return getCustomCardTarget(intent, parameters);
    }
    
    @Override
    protected String getCardImagePath(MonsterIntent intent, IntentParameters parameters) {
        // 检查自定义图片路径
        if (customProperties.containsKey("cardImage")) {
            return (String) customProperties.get("cardImage");
        }
        
        if (parameters.hasParameter("cardImage")) {
            return parameters.getStringParameter("cardImage");
        }
        
        // 使用默认自定义图片
        return "everyMonsterPlayCardResources/images/cards/monster_custom.png";
    }
    
    @Override
    protected String generateCardName(MonsterIntent intent, IntentParameters parameters) {
        // 检查自定义名称
        if (customProperties.containsKey("cardName")) {
            return (String) customProperties.get("cardName");
        }
        
        if (parameters.hasParameter("customName")) {
            return parameters.getStringParameter("customName");
        }
        
        // 使用默认自定义名称
        return "自定义" + super.generateCardName(intent, parameters);
    }
    
    @Override
    protected String generateCardDescription(MonsterIntent intent, IntentParameters parameters) {
        // 检查自定义描述
        if (customProperties.containsKey("cardDescription")) {
            return (String) customProperties.get("cardDescription");
        }
        
        if (parameters.hasParameter("customDescription")) {
            return parameters.getStringParameter("customDescription");
        }
        
        // 使用默认自定义描述
        StringBuilder description = new StringBuilder();
        description.append("自定义效果。");
        
        // 添加自定义属性描述
        for (Map.Entry<String, Object> entry : customProperties.entrySet()) {
            if (entry.getKey().startsWith("desc_")) {
                String effectDesc = entry.getValue().toString();
                description.append(" ").append(effectDesc);
            }
        }
        
        return description.toString();
    }
    
    @Override
    protected void applySpecialEffects(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        super.applySpecialEffects(card, intent, parameters);
        
        // 应用自定义特殊效果
        for (Map.Entry<String, Object> entry : customProperties.entrySet()) {
            if (entry.getKey().startsWith("effect_")) {
                applyCustomEffect(card, entry.getKey(), entry.getValue());
            }
        }
        
        // 应用参数中的自定义效果
        for (String key : parameters.getAllParameters().keySet()) {
            if (key.startsWith("customEffect_")) {
                Object value = parameters.getParameter(key);
                applyCustomEffect(card, key, value);
            }
        }
    }
    
    /**
     * 获取自定义卡牌类型
     */
    private CardType getCustomCardType(MonsterIntent intent, IntentParameters parameters) {
        // 检查自定义类型
        if (customProperties.containsKey("cardType")) {
            String typeStr = (String) customProperties.get("cardType");
            try {
                return CardType.valueOf(typeStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        if (parameters.hasParameter("customCardType")) {
            String typeStr = parameters.getStringParameter("customCardType");
            try {
                return CardType.valueOf(typeStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 使用父类的默认逻辑
        return super.determineCardType(intent, parameters);
    }
    
    /**
     * 获取自定义卡牌颜色
     */
    private CardColor getCustomCardColor(MonsterIntent intent, IntentParameters parameters) {
        // 检查自定义颜色
        if (customProperties.containsKey("cardColor")) {
            String colorStr = (String) customProperties.get("cardColor");
            try {
                return CardColor.valueOf(colorStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        if (parameters.hasParameter("customCardColor")) {
            String colorStr = parameters.getStringParameter("customCardColor");
            try {
                return CardColor.valueOf(colorStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 使用父类的默认逻辑
        return super.determineCardColor(intent, parameters);
    }
    
    /**
     * 获取自定义卡牌稀有度
     */
    private CardRarity getCustomCardRarity(MonsterIntent intent, IntentParameters parameters) {
        // 检查自定义稀有度
        if (customProperties.containsKey("cardRarity")) {
            String rarityStr = (String) customProperties.get("cardRarity");
            try {
                return CardRarity.valueOf(rarityStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        if (parameters.hasParameter("customCardRarity")) {
            String rarityStr = parameters.getStringParameter("customCardRarity");
            try {
                return CardRarity.valueOf(rarityStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 使用父类的默认逻辑
        return super.determineCardRarity(intent, parameters);
    }
    
    /**
     * 获取自定义卡牌目标
     */
    private CardTarget getCustomCardTarget(MonsterIntent intent, IntentParameters parameters) {
        // 检查自定义目标
        if (customProperties.containsKey("cardTarget")) {
            String targetStr = (String) customProperties.get("cardTarget");
            try {
                return CardTarget.valueOf(targetStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        if (parameters.hasParameter("customCardTarget")) {
            String targetStr = parameters.getStringParameter("customCardTarget");
            try {
                return CardTarget.valueOf(targetStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 使用父类的默认逻辑
        return super.determineCardTarget(intent, parameters);
    }
    
    /**
     * 应用自定义效果
     */
    private void applyCustomEffect(AbstractCard card, String effectKey, Object effectValue) {
        String effectType = effectKey.replace("effect_", "").replace("customEffect_", "");
        
        switch (effectType) {
            case "ethereal":
                card.isEthereal = Boolean.parseBoolean(effectValue.toString());
                break;
            case "exhaust":
                card.exhaust = Boolean.parseBoolean(effectValue.toString());
                break;
            case "innate":
                card.isInnate = Boolean.parseBoolean(effectValue.toString());
                break;
            case "retain":
                card.retain = Boolean.parseBoolean(effectValue.toString());
                break;
            case "selfRetain":
                card.selfRetain = Boolean.parseBoolean(effectValue.toString());
                break;
            case "costToPlay":
                try {
                    card.costForTurn = Integer.parseInt(effectValue.toString());
                } catch (NumberFormatException e) {
                    // 忽略解析错误
                }
                break;
            case "damageBonus":
                try {
                    int bonus = Integer.parseInt(effectValue.toString());
                    card.baseDamage += bonus;
                    card.damage = card.baseDamage;
                } catch (NumberFormatException e) {
                    // 忽略解析错误
                }
                break;
            case "blockBonus":
                try {
                    int bonus = Integer.parseInt(effectValue.toString());
                    card.baseBlock += bonus;
                    card.block = card.baseBlock;
                } catch (NumberFormatException e) {
                    // 忽略解析错误
                }
                break;
            case "magicBonus":
                try {
                    int bonus = Integer.parseInt(effectValue.toString());
                    card.baseMagicNumber += bonus;
                    card.magicNumber = card.baseMagicNumber;
                } catch (NumberFormatException e) {
                    // 忽略解析错误
                }
                break;
        }
    }
    
    /**
     * 应用自定义升级
     */
    private void applyCustomUpgrade(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 检查自定义升级效果
        if (customProperties.containsKey("upgradeEffect")) {
            String upgradeEffect = (String) customProperties.get("upgradeEffect");
            applyUpgradeEffect(card, upgradeEffect);
        }
        
        if (parameters.hasParameter("customUpgrade")) {
            String upgradeEffect = parameters.getStringParameter("customUpgrade");
            applyUpgradeEffect(card, upgradeEffect);
        }
        
        // 默认升级效果
        if (card.baseDamage > 0) {
            card.baseDamage += 3;
            card.damage = card.baseDamage;
        }
        if (card.baseBlock > 0) {
            card.baseBlock += 3;
            card.block = card.baseBlock;
        }
        if (card.baseMagicNumber > 0) {
            card.baseMagicNumber += 1;
            card.magicNumber = card.baseMagicNumber;
        }
    }
    
    /**
     * 应用升级效果
     */
    private void applyUpgradeEffect(AbstractCard card, String upgradeEffect) {
        switch (upgradeEffect.toLowerCase()) {
            case "damage":
                card.baseDamage += 5;
                card.damage = card.baseDamage;
                break;
            case "block":
                card.baseBlock += 5;
                card.block = card.baseBlock;
                break;
            case "magic":
                card.baseMagicNumber += 2;
                card.magicNumber = card.baseMagicNumber;
                break;
            case "cost":
                if (card.cost > 0) {
                    card.cost = Math.max(0, card.cost - 1);
                }
                break;
            case "exhaust":
                card.exhaust = true;
                break;
            case "retain":
                card.retain = true;
                break;
        }
    }
    
    @Override
    public double calculateMatchScore(MonsterIntent intent, IntentParameters parameters) {
        double baseScore = super.calculateMatchScore(intent, parameters);
        
        // 如果明确指定使用自定义模板，大幅提高匹配度
        if (parameters.hasParameter("useCustomTemplate") && 
            parameters.getBooleanParameter("useCustomTemplate")) {
            baseScore += 0.5;
        }
        
        // 如果有自定义属性，提高匹配度
        if (!customProperties.isEmpty()) {
            baseScore += 0.2;
        }
        
        // 如果有自定义参数，提高匹配度
        boolean hasCustomParams = false;
        for (String key : parameters.getAllParameters().keySet()) {
            if (key.startsWith("custom")) {
                hasCustomParams = true;
                break;
            }
        }
        if (hasCustomParams) {
            baseScore += 0.2;
        }
        
        return Math.min(1.0, baseScore);
    }
    
    @Override
    public String getTemplateDescription() {
        return "自定义卡牌模板，允许完全自定义卡牌的创建过程，支持自定义属性、效果和升级";
    }
    
    /**
     * 卡牌创建处理器接口
     */
    public interface CardCreationHandler {
        AbstractCard createCard(String cardId, String cardName, String cardImage, 
                              int cardCost, String cardDescription, 
                              MonsterIntent intent, IntentParameters parameters, 
                              CustomCardTemplate template);
    }
}