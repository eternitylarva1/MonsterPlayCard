package EveryMonsterPlayCard.intent2card.generator;

import EveryMonsterPlayCard.core.data.CardAttributes;
import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;

/**
 * 卡牌属性计算器
 * 负责根据意图计算卡牌属性
 */
public class CardAttributeCalculator {
    
    /**
     * 计算卡牌属性
     */
    public CardAttributes calculateAttributes(MonsterIntent intent) {
        if (intent == null) {
            return new CardAttributes();
        }
        
        CardAttributes attributes = new CardAttributes();
        
        // 基础属性计算
        calculateBasicAttributes(intent, attributes);
        
        // 根据意图类型计算特定属性
        calculateTypeSpecificAttributes(intent, attributes);
        
        // 计算高级属性
        calculateAdvancedAttributes(intent, attributes);
        
        return attributes;
    }
    
    /**
     * 计算基础属性
     */
    private void calculateBasicAttributes(MonsterIntent intent, CardAttributes attributes) {
        // 设置基础名称和描述
        attributes.setName(generateCardName(intent));
        attributes.setDescription(generateCardDescription(intent));
        
        // 计算基础费用
        attributes.setCost(calculateBaseCost(intent));
    }
    
    /**
     * 计算类型特定属性
     */
    private void calculateTypeSpecificAttributes(MonsterIntent intent, CardAttributes attributes) {
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                calculateAttackAttributes(intent, attributes);
                break;
            case DEFEND:
                calculateDefendAttributes(intent, attributes);
                break;
            case BUFF:
                calculateBuffAttributes(intent, attributes);
                break;
            case DEBUFF:
                calculateDebuffAttributes(intent, attributes);
                break;
            case ESCAPE:
                calculateEscapeAttributes(intent, attributes);
                break;
            case UNKNOWN:
                calculateUnknownAttributes(intent, attributes);
                break;
        }
    }
    
    /**
     * 计算攻击属性
     */
    private void calculateAttackAttributes(MonsterIntent intent, CardAttributes attributes) {
        attributes.setType(com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK);
        attributes.setDamage(intent.getAmount());
        attributes.setTarget(com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ENEMY);
        
        // 根据伤害值调整稀有度
        if (intent.getAmount() >= 30) {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE);
        } else if (intent.getAmount() >= 20) {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.UNCOMMON);
        } else {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.BASIC);
        }
        
        // 攻击卡牌通常使用红色
        attributes.setColor(com.megacrit.cardcrawl.cards.AbstractCard.CardColor.RED);
        
        // 添加攻击特有标签
        attributes.addTag("attack");
        attributes.addTag("damage");
        
        // 强力攻击特殊处理
        if (intent.getType() == IntentType.STRONG) {
            attributes.addTag("strong");
            attributes.setCost(Math.max(2, attributes.getCost()));
        }
    }
    
    /**
     * 计算防御属性
     */
    private void calculateDefendAttributes(MonsterIntent intent, CardAttributes attributes) {
        attributes.setType(com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL);
        attributes.setBlock(intent.getAmount());
        attributes.setTarget(com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.SELF);
        
        // 根据格挡值调整稀有度
        if (intent.getAmount() >= 25) {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE);
        } else if (intent.getAmount() >= 15) {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.UNCOMMON);
        } else {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.BASIC);
        }
        
        // 防御卡牌通常使用绿色
        attributes.setColor(com.megacrit.cardcrawl.cards.AbstractCard.CardColor.GREEN);
        
        // 添加防御特有标签
        attributes.addTag("defend");
        attributes.addTag("block");
        attributes.addTag("skill");
    }
    
    /**
     * 计算增益属性
     */
    private void calculateBuffAttributes(MonsterIntent intent, CardAttributes attributes) {
        attributes.setType(com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL);
        attributes.setMagicNumber(intent.getAmount());
        attributes.setTarget(com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.SELF);
        
        // 根据效果强度调整稀有度
        if (intent.getAmount() >= 10) {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE);
        } else if (intent.getAmount() >= 5) {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.UNCOMMON);
        } else {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.BASIC);
        }
        
        // 增益卡牌通常使用蓝色
        attributes.setColor(com.megacrit.cardcrawl.cards.AbstractCard.CardColor.BLUE);
        
        // 添加增益特有标签
        attributes.addTag("buff");
        attributes.addTag("power");
        attributes.addTag("skill");
    }
    
    /**
     * 计算减益属性
     */
    private void calculateDebuffAttributes(MonsterIntent intent, CardAttributes attributes) {
        attributes.setType(com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL);
        attributes.setMagicNumber(intent.getAmount());
        attributes.setTarget(com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ENEMY);
        
        // 根据效果强度调整稀有度
        if (intent.getAmount() >= 10) {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE);
        } else if (intent.getAmount() >= 5) {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.UNCOMMON);
        } else {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.BASIC);
        }
        
        // 减益卡牌通常使用紫色
        attributes.setColor(com.megacrit.cardcrawl.cards.AbstractCard.CardColor.PURPLE);
        
        // 添加减益特有标签
        attributes.addTag("debuff");
        attributes.addTag("power");
        attributes.addTag("skill");
    }
    
    /**
     * 计算逃跑属性
     */
    private void calculateEscapeAttributes(MonsterIntent intent, CardAttributes attributes) {
        attributes.setType(com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL);
        attributes.setTarget(com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.NONE);
        attributes.setCost(0); // 逃跑通常免费
        
        // 逃跑卡牌总是基础稀有度
        attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.BASIC);
        
        // 逃跑卡牌使用无色
        attributes.setColor(com.megacrit.cardcrawl.cards.AbstractCard.CardColor.COLORLESS);
        
        // 添加逃跑特有标签
        attributes.addTag("escape");
        attributes.addTag("skill");
    }
    
    /**
     * 计算未知属性
     */
    private void calculateUnknownAttributes(MonsterIntent intent, CardAttributes attributes) {
        attributes.setType(com.megacrit.cardcrawl.cards.AbstractCard.CardType.SKILL);
        attributes.setTarget(com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.NONE);
        attributes.setCost(1); // 未知意图默认费用为1
        
        // 未知卡牌使用基础稀有度
        attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.BASIC);
        
        // 未知卡牌使用诅咒色
        attributes.setColor(com.megacrit.cardcrawl.cards.AbstractCard.CardColor.CURSE);
        
        // 添加未知特有标签
        attributes.addTag("unknown");
        attributes.addTag("skill");
        
        // 如果有数量，尝试推断属性
        if (intent.getAmount() > 0) {
            // 简单推断：如果数量较大，可能是攻击
            if (intent.getAmount() >= 10) {
                attributes.setDamage(intent.getAmount());
                attributes.setType(com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK);
                attributes.setTarget(com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ENEMY);
                attributes.addTag("attack");
                attributes.addTag("damage");
            } else {
                // 否则可能是防御或增益
                attributes.setBlock(intent.getAmount());
                attributes.addTag("block");
            }
        }
    }
    
    /**
     * 计算高级属性
     */
    private void calculateAdvancedAttributes(MonsterIntent intent, CardAttributes attributes) {
        // 根据组件动作计算属性
        if (intent.getComponentActions() != null && !intent.getComponentActions().isEmpty()) {
            calculateActionAttributes(intent, attributes);
        }
        
        // 根据属性计算特殊属性
        calculatePropertyAttributes(intent, attributes);
        
        // 根据元数据计算属性
        calculateMetadataAttributes(intent, attributes);
    }
    
    /**
     * 根据组件动作计算属性
     */
    private void calculateActionAttributes(MonsterIntent intent, CardAttributes attributes) {
        int actionCount = intent.getComponentActions().size();
        
        // 多动作意图增加复杂度
        if (actionCount >= 3) {
            attributes.addTag("complex");
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE);
        } else if (actionCount >= 2) {
            attributes.addTag("composite");
            if (attributes.getRarity() == com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.BASIC) {
                attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.UNCOMMON);
            }
        }
        
        // 多动作可能增加费用
        if (actionCount >= 2) {
            attributes.setCost(attributes.getCost() + 1);
        }
    }
    
    /**
     * 根据属性计算特殊属性
     */
    private void calculatePropertyAttributes(MonsterIntent intent, CardAttributes attributes) {
        // 检查多目标属性
        if (intent.hasProperty("multiTarget") && intent.getBooleanParameter("multiTarget")) {
            attributes.setTarget(com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ALL_ENEMY);
            attributes.addTag("multi_target");
            attributes.setCost(attributes.getCost() + 1); // 多目标增加费用
        }
        
        // 检查特殊效果
        if (intent.hasProperty("specialEffect")) {
            attributes.addTag("special_effect");
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE);
        }
        
        // 检查持续效果
        if (intent.hasProperty("duration")) {
            int duration = intent.getIntParameter("duration");
            if (duration > 0) {
                attributes.addTag("duration");
                attributes.setProperty("duration", duration);
            }
        }
    }
    
    /**
     * 根据元数据计算属性
     */
    private void calculateMetadataAttributes(MonsterIntent intent, CardAttributes attributes) {
        if (intent.getMetadata() == null) {
            return;
        }
        
        // 根据元数据优先级调整稀有度
        int priority = intent.getMetadata().getPriority();
        if (priority >= 5) {
            attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE);
        } else if (priority >= 3) {
            if (attributes.getRarity() == com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.BASIC) {
                attributes.setRarity(com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.UNCOMMON);
            }
        }
        
        // 检查元数据中的特殊标记
        if (intent.getMetadata().hasProperty("critical")) {
            attributes.addTag("critical");
        }
        
        if (intent.getMetadata().hasProperty("ethereal")) {
            attributes.addTag("ethereal");
        }
        
        if (intent.getMetadata().hasProperty("exhaust")) {
            attributes.addTag("exhaust");
        }
    }
    
    /**
     * 计算基础费用
     */
    private int calculateBaseCost(MonsterIntent intent) {
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
     * 生成卡牌名称
     */
    private String generateCardName(MonsterIntent intent) {
        String baseName = "怪物";
        
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
    private String generateCardDescription(MonsterIntent intent) {
        StringBuilder description = new StringBuilder();
        
        // 根据意图类型生成基础描述
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                description.append("造成 ").append(intent.getAmount()).append(" 点伤害。");
                break;
            case DEFEND:
                description.append("获得 ").append(intent.getAmount()).append(" 点格挡。");
                break;
            case BUFF:
                description.append("获得 ").append(intent.getAmount()).append(" 点增益效果。");
                break;
            case DEBUFF:
                description.append("对敌人施加 ").append(intent.getAmount()).append(" 点减益效果。");
                break;
            case ESCAPE:
                description.append("尝试从战斗中逃跑。");
                break;
            case UNKNOWN:
                description.append("未知怪物行动。");
                break;
        }
        
        return description.toString();
    }
    
    /**
     * 计算卡牌平衡性分数
     */
    public double calculateBalanceScore(CardAttributes attributes) {
        if (attributes == null) {
            return 0.0;
        }
        
        double score = 5.0; // 基础分数
        
        // 根据费用与效果的比例调整分数
        double costEffectRatio = calculateCostEffectRatio(attributes);
        if (costEffectRatio >= 0.8 && costEffectRatio <= 1.2) {
            score += 2.0; // 费用与效果平衡
        } else if (costEffectRatio >= 0.6 && costEffectRatio <= 1.4) {
            score += 1.0; // 费用与效果基本平衡
        } else {
            score -= 1.0; // 费用与效果不平衡
        }
        
        // 根据稀有度调整分数
        if (attributes.getRarity() == com.megacrit.cardcrawl.cards.AbstractCard.CardRarity.RARE) {
            score += 1.0; // 稀有卡牌应该有更好的效果
        }
        
        // 根据标签调整分数
        if (attributes.hasTag("complex")) {
            score += 0.5; // 复杂卡牌可能有更好的效果
        }
        
        return Math.max(0.0, Math.min(10.0, score));
    }
    
    /**
     * 计算费用与效果的比例
     */
    private double calculateCostEffectRatio(CardAttributes attributes) {
        int cost = Math.max(1, attributes.getCost());
        int effectValue = 0;
        
        if (attributes.hasDamage()) {
            effectValue += attributes.getDamage();
        }
        
        if (attributes.hasBlock()) {
            effectValue += attributes.getBlock();
        }
        
        if (attributes.hasMagicNumber()) {
            effectValue += attributes.getMagicNumber() * 2; // 魔法效果价值更高
        }
        
        if (effectValue == 0) {
            return 1.0; // 无效果卡牌默认平衡
        }
        
        return (double) cost / effectValue;
    }
}