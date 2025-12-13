package EveryMonsterPlayCard.intent2card.template;

import java.util.UUID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;

/**
 * 抽象卡牌模板类
 * 提供卡牌模板的基础实现
 */
public abstract class AbstractCardTemplate implements CardTemplate {
    
    protected String templateName;
    protected String templateId;
    protected IntentType[] supportedTypes;
    protected int priority;
    
    public AbstractCardTemplate(String templateName, String templateId, IntentType[] supportedTypes) {
        this.templateName = templateName;
        this.templateId = templateId;
        this.supportedTypes = supportedTypes;
        this.priority = 0;
    }
    
    public AbstractCardTemplate(String templateName, String templateId, IntentType[] supportedTypes, int priority) {
        this(templateName, templateId, supportedTypes);
        this.priority = priority;
    }
    
    @Override
    public String getTemplateName() {
        return templateName;
    }
    
    @Override
    public int getTemplatePriority() {
        return priority;
    }
    
    @Override
    public IntentType[] getSupportedIntentTypes() {
        return supportedTypes;
    }
    
    @Override
    public boolean canHandle(MonsterIntent intent) {
        if (intent == null) {
            return false;
        }
        
        for (IntentType supportedType : supportedTypes) {
            if (supportedType == intent.getType()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public AbstractCard createCard(MonsterIntent intent, IntentParameters parameters) {
        if (!canHandle(intent) || !validateParameters(parameters)) {
            return null;
        }
        
        // 生成卡牌ID
        String cardId = generateCardId(intent, parameters);
        
        // 获取卡牌名称
        String cardName = generateCardName(intent, parameters);
        
        // 获取卡牌描述
        String cardDescription = generateCardDescription(intent, parameters);
        
        // 获取卡牌费用
        int cardCost = calculateCardCost(intent, parameters);
        
        // 获取卡牌图片路径
        String cardImage = getCardImagePath(intent, parameters);
        
        // 创建卡牌
        AbstractCard card = createCardInstance(cardId, cardName, cardImage, cardCost, cardDescription, intent, parameters);
        
        // 设置卡牌属性
        setupCardAttributes(card, intent, parameters);
        
        // 应用特殊效果
        applySpecialEffects(card, intent, parameters);
        
        return card;
    }
    
    /**
     * 生成卡牌ID
     */
    protected String generateCardId(MonsterIntent intent, IntentParameters parameters) {
        String baseId = templateId + "_" + intent.getType().getCode().toLowerCase();
        
        // 添加怪物名称（如果有）
        if (intent.getSource() != null) {
            baseId += "_" + intent.getSource().name.toLowerCase().replace(" ", "_");
        }
        
        // 添加唯一标识符
        baseId += "_" + UUID.randomUUID().toString().substring(0, 8);
        
        return baseId;
    }
    
    /**
     * 生成卡牌名称
     */
    protected String generateCardName(MonsterIntent intent, IntentParameters parameters) {
        String baseName = templateName;
        
        // 根据意图类型调整名称
        switch (intent.getType()) {
            case ATTACK:
                baseName = "怪物攻击";
                break;
            case DEFEND:
                baseName = "怪物防御";
                break;
            case BUFF:
                baseName = "怪物增益";
                break;
            case DEBUFF:
                baseName = "怪物减益";
                break;
            case STRONG:
                baseName = "怪物强击";
                break;
            case ESCAPE:
                baseName = "怪物逃跑";
                break;
            case UNKNOWN:
                baseName = "怪物未知行动";
                break;
        }
        
        // 添加怪物名称前缀（如果有）
        if (intent.getSource() != null) {
            baseName = intent.getSource().name + "的" + baseName;
        }
        
        return baseName;
    }
    
    /**
     * 生成卡牌描述
     */
    protected String generateCardDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder description = new StringBuilder();
        
        // 根据意图类型生成基础描述
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                if (parameters.hasDamage()) {
                    description.append("造成 !D! 点伤害。");
                }
                break;
            case DEFEND:
                if (parameters.hasBlock()) {
                    description.append("获得 !B! 点格挡。");
                }
                break;
            case BUFF:
                if (parameters.hasMagicNumber()) {
                    description.append("获得 !M! 点增益效果。");
                }
                break;
            case DEBUFF:
                if (parameters.hasMagicNumber()) {
                    description.append("施加 !M! 点减益效果。");
                }
                break;
            case ESCAPE:
                description.append("尝试逃跑。");
                break;
            case UNKNOWN:
                description.append("未知行动。");
                break;
        }
        
        // 添加特殊效果描述
        String specialDescription = generateSpecialEffectDescription(intent, parameters);
        if (specialDescription != null && !specialDescription.isEmpty()) {
            description.append(" ").append(specialDescription);
        }
        
        return description.toString();
    }
    
    /**
     * 计算卡牌费用
     */
    protected int calculateCardCost(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cost")) {
            return parameters.getIntParameter("cost");
        }
        
        // 根据意图类型和数量计算默认费用
        int amount = intent.getAmount();
        switch (intent.getType()) {
            case ATTACK:
                return Math.max(1, Math.min(3, amount / 7));
            case STRONG:
                return Math.max(2, Math.min(3, amount / 5));
            case DEFEND:
                return Math.max(0, Math.min(2, amount / 10));
            case BUFF:
            case DEBUFF:
                return Math.max(1, Math.min(2, amount / 5));
            case ESCAPE:
                return 0;
            case UNKNOWN:
            default:
                return 1;
        }
    }
    
    /**
     * 获取卡牌图片路径
     */
    protected String getCardImagePath(MonsterIntent intent, IntentParameters parameters) {
        // 默认图片路径，子类可以重写
        return "everyMonsterPlayCardResources/images/cards/monster_default.png";
    }
    
    /**
     * 创建卡牌实例
     */
    protected abstract AbstractCard createCardInstance(String cardId, String cardName, String cardImage, 
                                                  int cardCost, String cardDescription, 
                                                  MonsterIntent intent, IntentParameters parameters);
    
    /**
     * 设置卡牌属性
     */
    protected void setupCardAttributes(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 设置基础属性
        if (parameters.hasDamage()) {
            card.baseDamage = parameters.getDamage();
            card.damage = card.baseDamage;
        }
        
        if (parameters.hasBlock()) {
            card.baseBlock = parameters.getBlock();
            card.block = card.baseBlock;
        }
        
        if (parameters.hasMagicNumber()) {
            card.baseMagicNumber = parameters.getMagicNumber();
            card.magicNumber = card.baseMagicNumber;
        }
        
        // 设置卡牌类型
        card.type = determineCardType(intent, parameters);
        
        // 设置卡牌颜色
        card.color = determineCardColor(intent, parameters);
        
        // 设置卡牌稀有度
        card.rarity = determineCardRarity(intent, parameters);
        
        // 设置卡牌目标
        card.target = determineCardTarget(intent, parameters);
        
        // 初始化描述
        card.rawDescription = generateCardDescription(intent, parameters);
        card.initializeDescription();
    }
    
    /**
     * 确定卡牌类型
     */
    protected AbstractCard.CardType determineCardType(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardType")) {
            String typeStr = parameters.getStringParameter("cardType");
            try {
                return AbstractCard.CardType.valueOf(typeStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误，使用默认逻辑
            }
        }
        
        // 根据意图类型确定卡牌类型
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                return AbstractCard.CardType.ATTACK;
            case DEFEND:
            case BUFF:
            case DEBUFF:
                return AbstractCard.CardType.SKILL;
            case ESCAPE:
            case UNKNOWN:
            default:
                return AbstractCard.CardType.SKILL;
        }
    }
    
    /**
     * 确定卡牌颜色
     */
    protected AbstractCard.CardColor determineCardColor(MonsterIntent intent, IntentParameters parameters) {
        // 怪物卡牌默认使用诅咒颜色
        return AbstractCard.CardColor.CURSE;
    }
    
    /**
     * 确定卡牌稀有度
     */
    protected AbstractCard.CardRarity determineCardRarity(MonsterIntent intent, IntentParameters parameters) {
        // 怪物卡牌默认为特殊稀有度
        return AbstractCard.CardRarity.SPECIAL;
    }
    
    /**
     * 确定卡牌目标
     */
    protected AbstractCard.CardTarget determineCardTarget(MonsterIntent intent, IntentParameters parameters) {
        // 根据意图类型确定目标
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
            case DEBUFF:
                return AbstractCard.CardTarget.ENEMY;
            case DEFEND:
            case BUFF:
                return AbstractCard.CardTarget.SELF;
            case ESCAPE:
            case UNKNOWN:
            default:
                return AbstractCard.CardTarget.NONE;
        }
    }
    
    /**
     * 应用特殊效果
     */
    protected void applySpecialEffects(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 子类可以重写以应用特殊效果
    }
    
    /**
     * 生成特殊效果描述
     */
    protected String generateSpecialEffectDescription(MonsterIntent intent, IntentParameters parameters) {
        // 子类可以重写以提供特殊效果描述
        return "";
    }
    
    /**
     * 加载本地化文本
     */
    protected CardStrings loadCardStrings(String cardId) {
        try {
            return CardCrawlGame.languagePack.getCardStrings(cardId);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public String toString() {
        return "AbstractCardTemplate{" +
                "templateName='" + templateName + '\'' +
                ", templateId='" + templateId + '\'' +
                ", priority=" + priority +
                '}';
    }
}