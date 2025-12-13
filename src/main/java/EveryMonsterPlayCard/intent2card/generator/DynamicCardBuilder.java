package EveryMonsterPlayCard.intent2card.generator;

import java.util.UUID;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;

/**
 * 动态卡牌构建器
 * 用于在没有合适模板时动态创建卡牌
 */
public class DynamicCardBuilder {
    
    /**
     * 构建卡牌
     */
    public AbstractCard buildCard(MonsterIntent intent, IntentParameters parameters) {
        if (intent == null) {
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
        
        // 确定卡牌属性
        CardType cardType = determineCardType(intent, parameters);
        CardColor cardColor = determineCardColor(intent, parameters);
        CardRarity cardRarity = determineCardRarity(intent, parameters);
        CardTarget cardTarget = determineCardTarget(intent, parameters);
        
        // 创建动态卡牌
        AbstractCard card = new DynamicCard(cardId, cardName, cardImage, cardCost, cardDescription, 
                                        cardType, cardColor, cardRarity, cardTarget, 
                                        intent, parameters);
        
        // 设置卡牌属性
        setupCardAttributes(card, intent, parameters);
        
        return card;
    }
    
    /**
     * 构建复合卡牌
     */
    public AbstractCard buildCompositeCard(MonsterIntent intent, IntentParameters parameters) {
        if (intent == null) {
            return null;
        }
        
        // 标记为复合卡牌
        parameters.addParameter("isComposite", true);
        
        // 生成复合卡牌ID
        String cardId = generateCompositeCardId(intent, parameters);
        
        // 获取复合卡牌名称
        String cardName = generateCompositeCardName(intent, parameters);
        
        // 获取复合卡牌描述
        String cardDescription = generateCompositeCardDescription(intent, parameters);
        
        // 获取卡牌费用
        int cardCost = calculateCompositeCardCost(intent, parameters);
        
        // 获取卡牌图片路径
        String cardImage = getCompositeCardImagePath(intent, parameters);
        
        // 确定卡牌属性
        CardType cardType = determineCompositeCardType(intent, parameters);
        CardColor cardColor = determineCardColor(intent, parameters);
        CardRarity cardRarity = determineCompositeCardRarity(intent, parameters);
        CardTarget cardTarget = determineCompositeCardTarget(intent, parameters);
        
        // 创建复合动态卡牌
        AbstractCard card = new DynamicCard(cardId, cardName, cardImage, cardCost, cardDescription, 
                                        cardType, cardColor, cardRarity, cardTarget, 
                                        intent, parameters);
        
        // 设置卡牌属性
        setupCompositeCardAttributes(card, intent, parameters);
        
        return card;
    }
    
    /**
     * 生成卡牌ID
     */
    private String generateCardId(MonsterIntent intent, IntentParameters parameters) {
        String baseId = "dynamic_" + intent.getType().getCode().toLowerCase();
        
        // 添加怪物名称（如果有）
        if (intent.getSource() != null) {
            baseId += "_" + intent.getSource().name.toLowerCase().replace(" ", "_");
        }
        
        // 添加唯一标识符
        baseId += "_" + UUID.randomUUID().toString().substring(0, 8);
        
        return baseId;
    }
    
    /**
     * 生成复合卡牌ID
     */
    private String generateCompositeCardId(MonsterIntent intent, IntentParameters parameters) {
        String baseId = "dynamic_composite_" + intent.getType().getCode().toLowerCase();
        
        // 添加次要意图类型（如果有）
        if (parameters.hasParameter("secondaryIntent")) {
            String secondaryIntent = parameters.getStringParameter("secondaryIntent");
            baseId += "_" + secondaryIntent.toLowerCase();
        }
        
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
    private String generateCardName(MonsterIntent intent, IntentParameters parameters) {
        String baseName = "动态";
        
        // 根据意图类型调整名称
        switch (intent.getType()) {
            case ATTACK:
                baseName = "动态攻击";
                break;
            case DEFEND:
                baseName = "动态防御";
                break;
            case BUFF:
                baseName = "动态增益";
                break;
            case DEBUFF:
                baseName = "动态减益";
                break;
            case STRONG:
                baseName = "动态强击";
                break;
            case ESCAPE:
                baseName = "动态逃跑";
                break;
            case UNKNOWN:
                baseName = "动态未知";
                break;
        }
        
        // 添加怪物名称前缀（如果有）
        if (intent.getSource() != null) {
            baseName = intent.getSource().name + "的" + baseName;
        }
        
        return baseName;
    }
    
    /**
     * 生成复合卡牌名称
     */
    private String generateCompositeCardName(MonsterIntent intent, IntentParameters parameters) {
        String baseName = "动态复合";
        
        // 根据主要意图类型调整名称
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                baseName = "动态攻击复合";
                break;
            case DEFEND:
                baseName = "动态防御复合";
                break;
            case BUFF:
                baseName = "动态增益复合";
                break;
            case DEBUFF:
                baseName = "动态减益复合";
                break;
            default:
                baseName = "动态复合";
                break;
        }
        
        // 添加次要意图类型（如果有）
        if (parameters.hasParameter("secondaryIntent")) {
            String secondaryIntent = parameters.getStringParameter("secondaryIntent");
            baseName += secondaryIntent;
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
    private String generateCardDescription(MonsterIntent intent, IntentParameters parameters) {
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
                    description.append("对敌人施加 !M! 点减益效果。");
                }
                break;
            case ESCAPE:
                description.append("尝试逃跑。");
                break;
            case UNKNOWN:
                description.append("未知动态效果。");
                break;
        }
        
        // 添加动态效果描述
        description.append(" [动态生成]");
        
        return description.toString();
    }
    
    /**
     * 生成复合卡牌描述
     */
    private String generateCompositeCardDescription(MonsterIntent intent, IntentParameters parameters) {
        StringBuilder description = new StringBuilder();
        
        // 根据主要意图类型生成基础描述
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                if (parameters.hasDamage()) {
                    description.append("造成 !D! 点伤害");
                }
                break;
            case DEFEND:
                if (parameters.hasBlock()) {
                    description.append("获得 !B! 点格挡");
                }
                break;
            case BUFF:
                if (parameters.hasMagicNumber()) {
                    description.append("获得 !M! 点增益效果");
                }
                break;
            case DEBUFF:
                if (parameters.hasMagicNumber()) {
                    description.append("对敌人施加 !M! 点减益效果");
                }
                break;
            default:
                description.append("动态效果");
                break;
        }
        
        // 添加次要效果描述
        if (parameters.hasParameter("secondaryIntent")) {
            String secondaryIntent = parameters.getStringParameter("secondaryIntent");
            description.append("，同时");
            
            switch (secondaryIntent.toLowerCase()) {
                case "defend":
                    if (parameters.hasBlock()) {
                        description.append("获得 !B! 点格挡");
                    }
                    break;
                case "buff":
                    if (parameters.hasMagicNumber()) {
                        description.append("获得 !M! 点增益");
                    }
                    break;
                case "debuff":
                    if (parameters.hasMagicNumber()) {
                        description.append("施加 !M! 点减益");
                    }
                    break;
            }
        }
        
        description.append("。 [动态复合生成]");
        
        return description.toString();
    }
    
    /**
     * 计算卡牌费用
     */
    private int calculateCardCost(MonsterIntent intent, IntentParameters parameters) {
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
     * 计算复合卡牌费用
     */
    private int calculateCompositeCardCost(MonsterIntent intent, IntentParameters parameters) {
        int baseCost = calculateCardCost(intent, parameters);
        
        // 复合卡牌费用增加
        return Math.min(3, baseCost + 1);
    }
    
    /**
     * 获取卡牌图片路径
     */
    private String getCardImagePath(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardImage")) {
            return parameters.getStringParameter("cardImage");
        }
        
        // 根据意图类型返回不同的图片
        switch (intent.getType()) {
            case ATTACK:
                return "everyMonsterPlayCardResources/images/cards/dynamic_attack.png";
            case DEFEND:
                return "everyMonsterPlayCardResources/images/cards/dynamic_defend.png";
            case BUFF:
                return "everyMonsterPlayCardResources/images/cards/dynamic_buff.png";
            case DEBUFF:
                return "everyMonsterPlayCardResources/images/cards/dynamic_debuff.png";
            case STRONG:
                return "everyMonsterPlayCardResources/images/cards/dynamic_strong.png";
            case ESCAPE:
                return "everyMonsterPlayCardResources/images/cards/dynamic_escape.png";
            case UNKNOWN:
            default:
                return "everyMonsterPlayCardResources/images/cards/dynamic_unknown.png";
        }
    }
    
    /**
     * 获取复合卡牌图片路径
     */
    private String getCompositeCardImagePath(MonsterIntent intent, IntentParameters parameters) {
        return "everyMonsterPlayCardResources/images/cards/dynamic_composite.png";
    }
    
    /**
     * 确定卡牌类型
     */
    private CardType determineCardType(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardType")) {
            String typeStr = parameters.getStringParameter("cardType");
            try {
                return CardType.valueOf(typeStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据意图类型确定卡牌类型
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                return CardType.ATTACK;
            case DEFEND:
            case BUFF:
            case DEBUFF:
            case ESCAPE:
                return CardType.SKILL;
            case UNKNOWN:
            default:
                return CardType.SKILL;
        }
    }
    
    /**
     * 确定复合卡牌类型
     */
    private CardType determineCompositeCardType(MonsterIntent intent, IntentParameters parameters) {
        // 复合卡牌通常为技能类型
        return CardType.SKILL;
    }
    
    /**
     * 确定卡牌颜色
     */
    private CardColor determineCardColor(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardColor")) {
            String colorStr = parameters.getStringParameter("cardColor");
            try {
                return CardColor.valueOf(colorStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 动态卡牌使用紫色
        return CardColor.PURPLE;
    }
    
    /**
     * 确定复合卡牌稀有度
     */
    private CardRarity determineCompositeCardRarity(MonsterIntent intent, IntentParameters parameters) {
        // 复合卡牌通常为稀有
        return CardRarity.RARE;
    }
    
    /**
     * 确定卡牌稀有度
     */
    private CardRarity determineCardRarity(MonsterIntent intent, IntentParameters parameters) {
        if (parameters.hasParameter("cardRarity")) {
            String rarityStr = parameters.getStringParameter("cardRarity");
            try {
                return CardRarity.valueOf(rarityStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 动态卡牌使用特殊稀有度
        return CardRarity.SPECIAL;
    }
    
    /**
     * 确定卡牌目标
     */
    private CardTarget determineCardTarget(MonsterIntent intent, IntentParameters parameters) {
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
            case ATTACK:
            case STRONG:
            case DEBUFF:
                return CardTarget.ENEMY;
            case DEFEND:
            case BUFF:
                return CardTarget.SELF;
            case ESCAPE:
            case UNKNOWN:
            default:
                return CardTarget.NONE;
        }
    }
    
    /**
     * 确定复合卡牌目标
     */
    private CardTarget determineCompositeCardTarget(MonsterIntent intent, IntentParameters parameters) {
        // 复合卡牌通常为多目标
        return CardTarget.ALL_ENEMY;
    }
    
    /**
     * 设置卡牌属性
     */
    private void setupCardAttributes(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
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
        
        // 设置动态标记
        card.retain = true; // 动态卡牌默认保留
    }
    
    /**
     * 设置复合卡牌属性
     */
    private void setupCompositeCardAttributes(AbstractCard card, MonsterIntent intent, IntentParameters parameters) {
        // 设置基础属性
        setupCardAttributes(card, intent, parameters);
        
        // 设置复合特有属性
        card.exhaust = true; // 复合卡牌默认消耗
    }
    
    /**
     * 动态卡牌类
     */
    private static class DynamicCard extends AbstractCard {
        private final MonsterIntent intent;
        private final IntentParameters parameters;
        
        public DynamicCard(String id, String name, String img, int cost, String rawDescription,
                        CardType type, CardColor color, CardRarity rarity, CardTarget target,
                        MonsterIntent intent, IntentParameters parameters) {
            super(id, name, img, cost, rawDescription, type, color, rarity, target);
            this.intent = intent;
            this.parameters = parameters;
        }
        
        @Override
        public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, com.megacrit.cardcrawl.monsters.AbstractMonster m) {
            // 动态卡牌的use方法留空，由具体的卡牌类型处理
        }
        
        @Override
        public AbstractCard makeCopy() {
            return new DynamicCardBuilder().buildCard(intent, parameters);
        }
        
        @Override
        public void upgrade() {
            // 动态卡牌不支持升级
        }
    }
}