package EveryMonsterPlayCard.intent2card.service;

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
import EveryMonsterPlayCard.intent2card.generator.ValidationResult;
import EveryMonsterPlayCard.intent2card.template.CardTemplateEngine;

/**
 * 卡牌验证服务
 * 负责验证生成的卡牌是否符合规则和平衡性要求
 */
public class CardValidationService {
    
    private static CardValidationService instance;
    
    // 验证统计
    private int totalValidations = 0;
    private int passedValidations = 0;
    private int failedValidations = 0;
    private int repairedCards = 0;
    
    // 验证配置
    private ValidationConfig config = new ValidationConfig();
    
    private CardValidationService() {
        // 私有构造函数，实现单例模式
    }
    
    /**
     * 获取服务实例
     */
    public static synchronized CardValidationService getInstance() {
        if (instance == null) {
            instance = new CardValidationService();
        }
        return instance;
    }
    
    /**
     * 验证卡牌
     */
    public boolean validateCard(AbstractCard card, MonsterIntent intent) {
        if (card == null || intent == null) {
            return false;
        }
        
        totalValidations++;
        
        try {
            // 基础验证
            if (!validateBasicProperties(card)) {
                failedValidations++;
                return false;
            }
            
            // 意图一致性验证
            if (!validateIntentConsistency(card, intent)) {
                failedValidations++;
                return false;
            }
            
            // 平衡性验证
            if (!validateBalance(card, intent)) {
                failedValidations++;
                return false;
            }
            
            // 特殊规则验证
            if (!validateSpecialRules(card, intent)) {
                failedValidations++;
                return false;
            }
            
            passedValidations++;
            return true;
            
        } catch (Exception e) {
            System.err.println("Error during card validation: " + e.getMessage());
            e.printStackTrace();
            failedValidations++;
            return false;
        }
    }
    
    /**
     * 修复卡牌
     */
    public AbstractCard repairCard(AbstractCard card, MonsterIntent intent) {
        if (card == null || intent == null) {
            return null;
        }
        
        try {
            // 创建修复后的卡牌副本
            AbstractCard repairedCard = card.makeCopy();
            
            // 修复基础属性
            repairBasicProperties(repairedCard);
            
            // 修复意图一致性
            repairIntentConsistency(repairedCard, intent);
            
            // 修复平衡性
            repairBalance(repairedCard, intent);
            
            // 修复特殊规则
            repairSpecialRules(repairedCard, intent);
            
            // 验证修复后的卡牌
            if (validateCard(repairedCard, intent)) {
                repairedCards++;
                return repairedCard;
            }
            
        } catch (Exception e) {
            System.err.println("Error during card repair: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * 验证基础属性
     */
    private boolean validateBasicProperties(AbstractCard card) {
        // 检查卡牌名称
        if (card.name == null || card.name.trim().isEmpty()) {
            return false;
        }
        
        // 检查卡牌描述
        if (card.rawDescription == null || card.rawDescription.trim().isEmpty()) {
            return false;
        }
        
        // 检查卡牌颜色
        if (card.color == null) {
            return false;
        }
        
        // 检查卡牌类型
        if (card.type == null) {
            return false;
        }
        
        // 检查卡牌稀有度
        if (card.rarity == null) {
            return false;
        }
        
        // 检查卡牌目标
        if (card.target == null) {
            return false;
        }
        
        // 检查费用
        if (card.cost < -1 || card.cost > 3) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证意图一致性
     */
    private boolean validateIntentConsistency(AbstractCard card, MonsterIntent intent) {
        IntentType intentType = intent.getType();
        
        // 检查卡牌类型与意图类型的一致性
        switch (intentType) {
            case ATTACK:
            case STRONG:
                if (card.type != CardType.ATTACK) {
                    return false;
                }
                if (card.damage < 0) {
                    return false;
                }
                break;
                
            case DEFEND:
                if (card.type != CardType.SKILL) {
                    return false;
                }
                if (card.block < 0) {
                    return false;
                }
                break;
                
            case BUFF:
            case DEBUFF:
                if (card.type != CardType.POWER && card.type != CardType.SKILL) {
                    return false;
                }
                if (card.magicNumber < 0) {
                    return false;
                }
                break;
                
            case ESCAPE:
                if (card.type != CardType.SKILL) {
                    return false;
                }
                break;
                
            default:
                // 未知意图类型，允许任何卡牌类型
                break;
        }
        
        // 检查数值一致性
        if (!validateValueConsistency(card, intent)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证数值一致性
     */
    private boolean validateValueConsistency(AbstractCard card, MonsterIntent intent) {
        int intentAmount = intent.getAmount();
        
        // 检查伤害值
        if (card.damage > 0 && intentAmount > 0) {
            double damageRatio = (double) card.damage / intentAmount;
            if (damageRatio < config.minDamageRatio || damageRatio > config.maxDamageRatio) {
                return false;
            }
        }
        
        // 检查格挡值
        if (card.block > 0 && intentAmount > 0) {
            double blockRatio = (double) card.block / intentAmount;
            if (blockRatio < config.minBlockRatio || blockRatio > config.maxBlockRatio) {
                return false;
            }
        }
        
        // 检查魔法数值
        if (card.magicNumber > 0 && intentAmount > 0) {
            double magicRatio = (double) card.magicNumber / intentAmount;
            if (magicRatio < config.minMagicRatio || magicRatio > config.maxMagicRatio) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 验证平衡性
     */
    private boolean validateBalance(AbstractCard card, MonsterIntent intent) {
        // 计算卡牌强度
        int cardPower = calculateCardPower(card);
        
        // 计算意图强度
        int intentPower = calculateIntentPower(intent);
        
        // 检查强度比例
        double powerRatio = (double) cardPower / intentPower;
        if (powerRatio < config.minPowerRatio || powerRatio > config.maxPowerRatio) {
            return false;
        }
        
        // 检查费用效率
        if (!validateCostEfficiency(card)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证特殊规则
     */
    private boolean validateSpecialRules(AbstractCard card, MonsterIntent intent) {
        // 检查稀有度限制
        if (!validateRarityRestrictions(card)) {
            return false;
        }
        
        // 检查目标限制
        if (!validateTargetRestrictions(card, intent)) {
            return false;
        }
        
        // 检查特殊效果限制
        if (!validateSpecialEffectRestrictions(card)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 修复基础属性
     */
    private void repairBasicProperties(AbstractCard card) {
        // 修复名称
        if (card.name == null || card.name.trim().isEmpty()) {
            card.name = "未知卡牌";
        }
        
        // 修复描述
        if (card.rawDescription == null || card.rawDescription.trim().isEmpty()) {
            card.rawDescription = "无描述";
        }
        
        // 修复颜色
        if (card.color == null) {
            card.color = CardColor.COLORLESS;
        }
        
        // 修复类型
        if (card.type == null) {
            card.type = CardType.SKILL;
        }
        
        // 修复稀有度
        if (card.rarity == null) {
            card.rarity = CardRarity.COMMON;
        }
        
        // 修复目标
        if (card.target == null) {
            card.target = CardTarget.ENEMY;
        }
        
        // 修复费用
        if (card.cost < -1) {
            card.cost = -1;
        } else if (card.cost > 3) {
            card.cost = 3;
        }
    }
    
    /**
     * 修复意图一致性
     */
    private void repairIntentConsistency(AbstractCard card, MonsterIntent intent) {
        IntentType intentType = intent.getType();
        
        // 修复卡牌类型
        switch (intentType) {
            case ATTACK:
            case STRONG:
                if (card.type != CardType.ATTACK) {
                    card.type = CardType.ATTACK;
                }
                if (card.damage < 0) {
                    card.damage = Math.max(1, intent.getAmount());
                }
                break;
                
            case DEFEND:
                if (card.type != CardType.SKILL) {
                    card.type = CardType.SKILL;
                }
                if (card.block < 0) {
                    card.block = Math.max(1, intent.getAmount());
                }
                break;
                
            case BUFF:
            case DEBUFF:
                if (card.type != CardType.POWER && card.type != CardType.SKILL) {
                    card.type = CardType.POWER;
                }
                if (card.magicNumber < 0) {
                    card.magicNumber = Math.max(1, intent.getAmount());
                }
                break;
                
            case ESCAPE:
                if (card.type != CardType.SKILL) {
                    card.type = CardType.SKILL;
                }
                break;
        }
        
        // 修复数值一致性
        repairValueConsistency(card, intent);
    }
    
    /**
     * 修复数值一致性
     */
    private void repairValueConsistency(AbstractCard card, MonsterIntent intent) {
        int intentAmount = intent.getAmount();
        
        // 修复伤害值
        if (card.damage > 0 && intentAmount > 0) {
            double damageRatio = (double) card.damage / intentAmount;
            if (damageRatio < config.minDamageRatio) {
                card.damage = (int) (intentAmount * config.minDamageRatio);
            } else if (damageRatio > config.maxDamageRatio) {
                card.damage = (int) (intentAmount * config.maxDamageRatio);
            }
        }
        
        // 修复格挡值
        if (card.block > 0 && intentAmount > 0) {
            double blockRatio = (double) card.block / intentAmount;
            if (blockRatio < config.minBlockRatio) {
                card.block = (int) (intentAmount * config.minBlockRatio);
            } else if (blockRatio > config.maxBlockRatio) {
                card.block = (int) (intentAmount * config.maxBlockRatio);
            }
        }
        
        // 修复魔法数值
        if (card.magicNumber > 0 && intentAmount > 0) {
            double magicRatio = (double) card.magicNumber / intentAmount;
            if (magicRatio < config.minMagicRatio) {
                card.magicNumber = (int) (intentAmount * config.minMagicRatio);
            } else if (magicRatio > config.maxMagicRatio) {
                card.magicNumber = (int) (intentAmount * config.maxMagicRatio);
            }
        }
    }
    
    /**
     * 修复平衡性
     */
    private void repairBalance(AbstractCard card, MonsterIntent intent) {
        // 计算卡牌强度
        int cardPower = calculateCardPower(card);
        
        // 计算意图强度
        int intentPower = calculateIntentPower(intent);
        
        // 修复强度比例
        double powerRatio = (double) cardPower / intentPower;
        if (powerRatio < config.minPowerRatio) {
            // 增强卡牌
            enhanceCard(card, (int)(intentPower * config.minPowerRatio - cardPower));
        } else if (powerRatio > config.maxPowerRatio) {
            // 削弱卡牌
            weakenCard(card, (int)(cardPower - intentPower * config.maxPowerRatio));
        }
        
        // 修复费用效率
        repairCostEfficiency(card);
    }
    
    /**
     * 修复特殊规则
     */
    private void repairSpecialRules(AbstractCard card, MonsterIntent intent) {
        // 修复稀有度
        repairRarity(card);
        
        // 修复目标
        repairTarget(card, intent);
        
        // 修复特殊效果
        repairSpecialEffects(card);
    }
    
    /**
     * 计算卡牌强度
     */
    private int calculateCardPower(AbstractCard card) {
        int power = 0;
        
        // 基础强度
        power += card.damage;
        power += card.block;
        power += card.magicNumber * 2; // 魔法数值权重更高
        
        // 类型修正
        switch (card.type) {
            case ATTACK:
                power += 2;
                break;
            case SKILL:
                power += 1;
                break;
            case POWER:
                power += 3;
                break;
            case CURSE:
                power -= 2;
                break;
            case STATUS:
                power -= 1;
                break;
        }
        
        // 稀有度修正
        switch (card.rarity) {
            case COMMON:
                power += 0;
                break;
            case UNCOMMON:
                power += 2;
                break;
            case RARE:
                power += 4;
                break;
            case SPECIAL:
                power += 3;
                break;
            case BASIC:
                power += 1;
                break;
        }
        
        // 费用修正
        if (card.cost >= 0) {
            power -= card.cost * 3;
        } else {
            power += 2; // 负费用卡牌强度加成
        }
        
        return Math.max(0, power);
    }
    
    /**
     * 计算意图强度
     */
    private int calculateIntentPower(MonsterIntent intent) {
        int power = 0;
        
        IntentType intentType = intent.getType();
        int amount = intent.getAmount();
        
        switch (intentType) {
            case ATTACK:
                power = amount;
                break;
            case STRONG:
                power = (int) (amount * 1.5);
                break;
            case DEFEND:
                power = amount / 2;
                break;
            case BUFF:
                power = amount * 2;
                break;
            case DEBUFF:
                power = amount * 2;
                break;
            case ESCAPE:
                power = 1;
                break;
            default:
                power = amount;
                break;
        }
        
        return Math.max(1, power);
    }
    
    /**
     * 验证费用效率
     */
    private boolean validateCostEfficiency(AbstractCard card) {
        if (card.cost < 0) {
            return true; // 负费用卡牌总是有效率的
        }
        
        int cardPower = calculateCardPower(card);
        int expectedPower = card.cost * 3 + 2; // 基础期望强度
        
        return cardPower >= expectedPower * config.minCostEfficiency && 
               cardPower <= expectedPower * config.maxCostEfficiency;
    }
    
    /**
     * 验证稀有度限制
     */
    private boolean validateRarityRestrictions(AbstractCard card) {
        // 检查稀有度与卡牌强度的匹配
        int cardPower = calculateCardPower(card);
        
        switch (card.rarity) {
            case COMMON:
                return cardPower <= 8;
            case UNCOMMON:
                return cardPower > 8 && cardPower <= 12;
            case RARE:
                return cardPower > 12;
            case SPECIAL:
                return cardPower > 10;
            case BASIC:
                return cardPower <= 6;
            default:
                return true;
        }
    }
    
    /**
     * 验证目标限制
     */
    private boolean validateTargetRestrictions(AbstractCard card, MonsterIntent intent) {
        // 攻击卡牌应该有敌人目标
        if (card.type == CardType.ATTACK && card.target != CardTarget.ENEMY && card.target != CardTarget.ALL_ENEMY) {
            return false;
        }
        
        // 防御卡牌应该有自己目标
        if (card.type == CardType.SKILL && card.target != CardTarget.SELF && card.target != CardTarget.NONE) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证特殊效果限制
     */
    private boolean validateSpecialEffectRestrictions(AbstractCard card) {
        // 检查是否有不允许的特殊效果
        // 这里可以根据具体需求添加更多检查
        
        return true;
    }
    
    /**
     * 增强卡牌
     */
    private void enhanceCard(AbstractCard card, int powerIncrease) {
        // 根据卡牌类型增强
        switch (card.type) {
            case ATTACK:
                card.damage += powerIncrease;
                break;
            case SKILL:
                card.block += powerIncrease;
                break;
            case POWER:
                card.magicNumber += powerIncrease / 2;
                break;
        }
    }
    
    /**
     * 削弱卡牌
     */
    private void weakenCard(AbstractCard card, int powerDecrease) {
        // 根据卡牌类型削弱
        switch (card.type) {
            case ATTACK:
                card.damage = Math.max(1, card.damage - powerDecrease);
                break;
            case SKILL:
                card.block = Math.max(1, card.block - powerDecrease);
                break;
            case POWER:
                card.magicNumber = Math.max(1, card.magicNumber - powerDecrease / 2);
                break;
        }
    }
    
    /**
     * 修复费用效率
     */
    private void repairCostEfficiency(AbstractCard card) {
        if (card.cost < 0) {
            return; // 负费用卡牌不需要修复
        }
        
        int cardPower = calculateCardPower(card);
        int expectedPower = card.cost * 3 + 2;
        
        if (cardPower < expectedPower * config.minCostEfficiency) {
            // 增强卡牌或降低费用
            if (card.cost > 0) {
                card.cost--;
            } else {
                enhanceCard(card, expectedPower - cardPower);
            }
        } else if (cardPower > expectedPower * config.maxCostEfficiency) {
            // 削弱卡牌或增加费用
            if (card.cost < 3) {
                card.cost++;
            } else {
                weakenCard(card, cardPower - expectedPower);
            }
        }
    }
    
    /**
     * 修复稀有度
     */
    private void repairRarity(AbstractCard card) {
        int cardPower = calculateCardPower(card);
        
        if (cardPower <= 6 && card.rarity != CardRarity.BASIC) {
            card.rarity = CardRarity.COMMON;
        } else if (cardPower <= 8 && card.rarity != CardRarity.BASIC) {
            card.rarity = CardRarity.COMMON;
        } else if (cardPower <= 12) {
            card.rarity = CardRarity.UNCOMMON;
        } else {
            card.rarity = CardRarity.RARE;
        }
    }
    
    /**
     * 修复目标
     */
    private void repairTarget(AbstractCard card, MonsterIntent intent) {
        // 攻击卡牌应该有敌人目标
        if (card.type == CardType.ATTACK && card.target != CardTarget.ENEMY && card.target != CardTarget.ALL_ENEMY) {
            card.target = CardTarget.ENEMY;
        }
        
        // 防御卡牌应该有自己目标
        if (card.type == CardType.SKILL && card.target != CardTarget.SELF && card.target != CardTarget.NONE) {
            card.target = CardTarget.SELF;
        }
    }
    
    /**
     * 修复特殊效果
     */
    private void repairSpecialEffects(AbstractCard card) {
        // 这里可以根据具体需求添加特殊效果的修复逻辑
    }
    
    /**
     * 配置服务
     */
    public void configure(ValidationConfig config) {
        this.config = config;
    }
    
    /**
     * 重置到默认配置
     */
    public void resetToDefaults() {
        this.config = new ValidationConfig();
    }
    
    /**
     * 获取统计信息
     */
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalValidations", totalValidations);
        stats.put("passedValidations", passedValidations);
        stats.put("failedValidations", failedValidations);
        stats.put("repairedCards", repairedCards);
        
        if (totalValidations > 0) {
            stats.put("passRate", (double) passedValidations / totalValidations);
            stats.put("repairRate", (double) repairedCards / failedValidations);
        }
        
        return stats;
    }
    
    /**
     * 验证配置类
     */
    public static class ValidationConfig {
        // 数值比例限制
        public double minDamageRatio = 0.8;
        public double maxDamageRatio = 1.2;
        public double minBlockRatio = 0.8;
        public double maxBlockRatio = 1.2;
        public double minMagicRatio = 0.8;
        public double maxMagicRatio = 1.2;
        
        // 强度比例限制
        public double minPowerRatio = 0.7;
        public double maxPowerRatio = 1.3;
        
        // 费用效率限制
        public double minCostEfficiency = 0.8;
        public double maxCostEfficiency = 1.5;
    }
}