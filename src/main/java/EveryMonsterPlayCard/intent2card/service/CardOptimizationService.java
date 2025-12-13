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

/**
 * 卡牌优化服务
 * 负责优化生成的卡牌，提高其质量和可用性
 */
public class CardOptimizationService {
    
    private static CardOptimizationService instance;
    
    // 优化统计
    private int totalOptimizations = 0;
    private int successfulOptimizations = 0;
    private int failedOptimizations = 0;
    
    // 优化配置
    private OptimizationConfig config = new OptimizationConfig();
    
    private CardOptimizationService() {
        // 私有构造函数，实现单例模式
    }
    
    /**
     * 获取服务实例
     */
    public static synchronized CardOptimizationService getInstance() {
        if (instance == null) {
            instance = new CardOptimizationService();
        }
        return instance;
    }
    
    /**
     * 优化卡牌
     */
    public AbstractCard optimizeCard(AbstractCard card, MonsterIntent intent) {
        if (card == null || intent == null) {
            return null;
        }
        
        totalOptimizations++;
        
        try {
            // 创建优化后的卡牌副本
            AbstractCard optimizedCard = card.makeCopy();
            
            // 应用各种优化策略
            if (config.enableDescriptionOptimization) {
                optimizeDescription(optimizedCard, intent);
            }
            
            if (config.enableNameOptimization) {
                optimizeName(optimizedCard, intent);
            }
            
            if (config.enableCostOptimization) {
                optimizeCost(optimizedCard, intent);
            }
            
            if (config.enableRarityOptimization) {
                optimizeRarity(optimizedCard, intent);
            }
            
            if (config.enableTargetOptimization) {
                optimizeTarget(optimizedCard, intent);
            }
            
            if (config.enableValueOptimization) {
                optimizeValues(optimizedCard, intent);
            }
            
            if (config.enableBalanceOptimization) {
                optimizeBalance(optimizedCard, intent);
            }
            
            if (config.enableSpecialEffectOptimization) {
                optimizeSpecialEffects(optimizedCard, intent);
            }
            
            successfulOptimizations++;
            return optimizedCard;
            
        } catch (Exception e) {
            System.err.println("Error during card optimization: " + e.getMessage());
            e.printStackTrace();
            failedOptimizations++;
            return card; // 返回原始卡牌
        }
    }
    
    /**
     * 优化卡牌描述
     */
    private void optimizeDescription(AbstractCard card, MonsterIntent intent) {
        IntentType intentType = intent.getType();
        int amount = intent.getAmount();
        
        String description = card.rawDescription;
        
        // 根据意图类型优化描述
        switch (intentType) {
            case ATTACK:
                description = optimizeAttackDescription(description, amount);
                break;
            case STRONG:
                description = optimizeStrongAttackDescription(description, amount);
                break;
            case DEFEND:
                description = optimizeDefendDescription(description, amount);
                break;
            case BUFF:
                description = optimizeBuffDescription(description, amount);
                break;
            case DEBUFF:
                description = optimizeDebuffDescription(description, amount);
                break;
            case ESCAPE:
                description = optimizeEscapeDescription(description);
                break;
        }
        
        // 添加特殊效果描述
        if (intent.hasProperty("specialEffect")) {
            description = addSpecialEffectDescription(description, intent.getStringParameter("specialEffect"));
        }
        
        // 格式化描述
        description = formatDescription(description);
        
        card.rawDescription = description;
    }
    
    /**
     * 优化攻击描述
     */
    private String optimizeAttackDescription(String description, int damage) {
        if (description == null || description.trim().isEmpty()) {
            return "造成 !D! 点伤害。";
        }
        
        // 确保包含伤害信息
        if (!description.contains("!D!")) {
            description = "造成 !D! 点伤害。" + description;
        }
        
        return description;
    }
    
    /**
     * 优化强力攻击描述
     */
    private String optimizeStrongAttackDescription(String description, int damage) {
        if (description == null || description.trim().isEmpty()) {
            return "造成 !D! 点高额伤害。";
        }
        
        // 确保包含高额伤害信息
        if (!description.contains("!D!")) {
            description = "造成 !D! 点高额伤害。" + description;
        } else {
            description = description.replace("造成 !D! 点伤害。", "造成 !D! 点高额伤害。");
        }
        
        return description;
    }
    
    /**
     * 优化防御描述
     */
    private String optimizeDefendDescription(String description, int block) {
        if (description == null || description.trim().isEmpty()) {
            return "获得 !B! 点格挡。";
        }
        
        // 确保包含格挡信息
        if (!description.contains("!B!")) {
            description = "获得 !B! 点格挡。" + description;
        }
        
        return description;
    }
    
    /**
     * 优化增益描述
     */
    private String optimizeBuffDescription(String description, int amount) {
        if (description == null || description.trim().isEmpty()) {
            return "获得 !M! 点增益。";
        }
        
        // 确保包含增益信息
        if (!description.contains("!M!")) {
            description = "获得 !M! 点增益。" + description;
        }
        
        return description;
    }
    
    /**
     * 优化减益描述
     */
    private String optimizeDebuffDescription(String description, int amount) {
        if (description == null || description.trim().isEmpty()) {
            return "给敌人施加 !M! 点减益。";
        }
        
        // 确保包含减益信息
        if (!description.contains("!M!")) {
            description = "给敌人施加 !M! 点减益。" + description;
        }
        
        return description;
    }
    
    /**
     * 优化逃跑描述
     */
    private String optimizeEscapeDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            return "尝试逃跑。";
        }
        
        return description;
    }
    
    /**
     * 添加特殊效果描述
     */
    private String addSpecialEffectDescription(String description, String specialEffect) {
        if (specialEffect == null || specialEffect.trim().isEmpty()) {
            return description;
        }
        
        return description + " " + specialEffect;
    }
    
    /**
     * 格式化描述
     */
    private String formatDescription(String description) {
        if (description == null) {
            return "";
        }
        
        // 移除多余的空格
        description = description.replaceAll(" +", " ");
        
        // 确保句子以句号结尾
        if (!description.endsWith("。") && !description.endsWith("！") && !description.endsWith("？")) {
            description += "。";
        }
        
        return description;
    }
    
    /**
     * 优化卡牌名称
     */
    private void optimizeName(AbstractCard card, MonsterIntent intent) {
        IntentType intentType = intent.getType();
        int amount = intent.getAmount();
        
        String name = card.name;
        
        // 根据意图类型优化名称
        switch (intentType) {
            case ATTACK:
                name = optimizeAttackName(name, amount);
                break;
            case STRONG:
                name = optimizeStrongAttackName(name, amount);
                break;
            case DEFEND:
                name = optimizeDefendName(name, amount);
                break;
            case BUFF:
                name = optimizeBuffName(name, amount);
                break;
            case DEBUFF:
                name = optimizeDebuffName(name, amount);
                break;
            case ESCAPE:
                name = optimizeEscapeName(name);
                break;
        }
        
        card.name = name;
    }
    
    /**
     * 优化攻击名称
     */
    private String optimizeAttackName(String name, int damage) {
        if (name == null || name.trim().isEmpty()) {
            return "攻击";
        }
        
        // 根据伤害值添加前缀
        if (damage >= 20) {
            return "重击";
        } else if (damage >= 15) {
            return "猛击";
        } else if (damage >= 10) {
            return "打击";
        }
        
        return name;
    }
    
    /**
     * 优化强力攻击名称
     */
    private String optimizeStrongAttackName(String name, int damage) {
        if (name == null || name.trim().isEmpty()) {
            return "强力攻击";
        }
        
        // 根据伤害值添加前缀
        if (damage >= 25) {
            return "毁灭打击";
        } else if (damage >= 20) {
            return "致命一击";
        } else if (damage >= 15) {
            return "重击";
        }
        
        return name;
    }
    
    /**
     * 优化防御名称
     */
    private String optimizeDefendName(String name, int block) {
        if (name == null || name.trim().isEmpty()) {
            return "防御";
        }
        
        // 根据格挡值添加前缀
        if (block >= 15) {
            return "铁壁";
        } else if (block >= 10) {
            return "坚守";
        } else if (block >= 5) {
            return "格挡";
        }
        
        return name;
    }
    
    /**
     * 优化增益名称
     */
    private String optimizeBuffName(String name, int amount) {
        if (name == null || name.trim().isEmpty()) {
            return "增益";
        }
        
        // 根据数值添加前缀
        if (amount >= 5) {
            return "强化";
        } else if (amount >= 3) {
            return "增益";
        }
        
        return name;
    }
    
    /**
     * 优化减益名称
     */
    private String optimizeDebuffName(String name, int amount) {
        if (name == null || name.trim().isEmpty()) {
            return "减益";
        }
        
        // 根据数值添加前缀
        if (amount >= 5) {
            return "削弱";
        } else if (amount >= 3) {
            return "减益";
        }
        
        return name;
    }
    
    /**
     * 优化逃跑名称
     */
    private String optimizeEscapeName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "逃跑";
        }
        
        return name;
    }
    
    /**
     * 优化费用
     */
    private void optimizeCost(AbstractCard card, MonsterIntent intent) {
        int currentCost = card.cost;
        int optimalCost = calculateOptimalCost(card, intent);
        
        // 只有在差异较大时才调整
        if (Math.abs(currentCost - optimalCost) > 1) {
            card.cost = optimalCost;
        }
    }
    
    /**
     * 计算最优费用
     */
    private int calculateOptimalCost(AbstractCard card, MonsterIntent intent) {
        int cardPower = calculateCardPower(card);
        int intentPower = calculateIntentPower(intent);
        
        // 基于强度计算费用
        int baseCost = Math.max(0, (cardPower - 2) / 3);
        
        // 根据意图强度调整
        double powerRatio = (double) cardPower / intentPower;
        if (powerRatio > 1.2) {
            baseCost++; // 卡牌过强，增加费用
        } else if (powerRatio < 0.8) {
            baseCost = Math.max(0, baseCost - 1); // 卡牌过弱，减少费用
        }
        
        // 限制费用范围
        return Math.min(3, Math.max(0, baseCost));
    }
    
    /**
     * 优化稀有度
     */
    private void optimizeRarity(AbstractCard card, MonsterIntent intent) {
        int cardPower = calculateCardPower(card);
        CardRarity optimalRarity = calculateOptimalRarity(cardPower, intent);
        
        card.rarity = optimalRarity;
    }
    
    /**
     * 计算最优稀有度
     */
    private CardRarity calculateOptimalRarity(int cardPower, MonsterIntent intent) {
        // 基于强度计算稀有度
        if (cardPower <= 6) {
            return CardRarity.COMMON;
        } else if (cardPower <= 10) {
            return CardRarity.UNCOMMON;
        } else {
            return CardRarity.RARE;
        }
    }
    
    /**
     * 优化目标
     */
    private void optimizeTarget(AbstractCard card, MonsterIntent intent) {
        IntentType intentType = intent.getType();
        
        // 根据意图类型优化目标
        switch (intentType) {
            case ATTACK:
            case STRONG:
                if (card.target != CardTarget.ENEMY && card.target != CardTarget.ALL_ENEMY) {
                    card.target = CardTarget.ENEMY;
                }
                break;
            case DEFEND:
                if (card.target != CardTarget.SELF && card.target != CardTarget.NONE) {
                    card.target = CardTarget.SELF;
                }
                break;
            case BUFF:
                if (card.target != CardTarget.SELF && card.target != CardTarget.NONE) {
                    card.target = CardTarget.SELF;
                }
                break;
            case DEBUFF:
                if (card.target != CardTarget.ENEMY && card.target != CardTarget.ALL_ENEMY) {
                    card.target = CardTarget.ENEMY;
                }
                break;
            case ESCAPE:
                card.target = CardTarget.NONE;
                break;
        }
    }
    
    /**
     * 优化数值
     */
    private void optimizeValues(AbstractCard card, MonsterIntent intent) {
        IntentType intentType = intent.getType();
        int intentAmount = intent.getAmount();
        
        // 根据意图类型优化数值
        switch (intentType) {
            case ATTACK:
            case STRONG:
                optimizeAttackValue(card, intentAmount);
                break;
            case DEFEND:
                optimizeDefendValue(card, intentAmount);
                break;
            case BUFF:
            case DEBUFF:
                optimizeMagicValue(card, intentAmount);
                break;
        }
    }
    
    /**
     * 优化攻击数值
     */
    private void optimizeAttackValue(AbstractCard card, int intentAmount) {
        if (card.damage <= 0) {
            card.damage = Math.max(1, intentAmount);
        }
        
        // 确保伤害值在合理范围内
        if (card.damage > intentAmount * 1.5) {
            card.damage = (int) (intentAmount * 1.5);
        } else if (card.damage < intentAmount * 0.7) {
            card.damage = (int) (intentAmount * 0.7);
        }
    }
    
    /**
     * 优化防御数值
     */
    private void optimizeDefendValue(AbstractCard card, int intentAmount) {
        if (card.block <= 0) {
            card.block = Math.max(1, intentAmount);
        }
        
        // 确保格挡值在合理范围内
        if (card.block > intentAmount * 1.5) {
            card.block = (int) (intentAmount * 1.5);
        } else if (card.block < intentAmount * 0.7) {
            card.block = (int) (intentAmount * 0.7);
        }
    }
    
    /**
     * 优化魔法数值
     */
    private void optimizeMagicValue(AbstractCard card, int intentAmount) {
        if (card.magicNumber <= 0) {
            card.magicNumber = Math.max(1, intentAmount);
        }
        
        // 确保魔法数值在合理范围内
        if (card.magicNumber > intentAmount * 1.5) {
            card.magicNumber = (int) (intentAmount * 1.5);
        } else if (card.magicNumber < intentAmount * 0.7) {
            card.magicNumber = (int) (intentAmount * 0.7);
        }
    }
    
    /**
     * 优化平衡性
     */
    private void optimizeBalance(AbstractCard card, MonsterIntent intent) {
        int cardPower = calculateCardPower(card);
        int intentPower = calculateIntentPower(intent);
        
        double powerRatio = (double) cardPower / intentPower;
        
        // 如果卡牌过强，削弱
        if (powerRatio > 1.3) {
            weakenCard(card, (int) ((cardPower - intentPower * 1.3) * 0.5));
        }
        // 如果卡牌过弱，增强
        else if (powerRatio < 0.7) {
            enhanceCard(card, (int) ((intentPower * 0.7 - cardPower) * 0.5));
        }
    }
    
    /**
     * 优化特殊效果
     */
    private void optimizeSpecialEffects(AbstractCard card, MonsterIntent intent) {
        // 根据意图添加特殊效果
        if (intent.hasProperty("specialEffect")) {
            addSpecialEffect(card, intent.getStringParameter("specialEffect"));
        }
        
        // 根据卡牌类型添加特殊效果
        addTypeSpecificEffects(card, intent);
    }
    
    /**
     * 添加特殊效果
     */
    private void addSpecialEffect(AbstractCard card, String specialEffect) {
        // 这里可以根据特殊效果字符串添加相应的卡牌效果
        // 由于这是一个简化的实现，我们只更新描述
        if (!card.rawDescription.contains(specialEffect)) {
            card.rawDescription += " " + specialEffect;
        }
    }
    
    /**
     * 添加类型特定效果
     */
    private void addTypeSpecificEffects(AbstractCard card, MonsterIntent intent) {
        // 根据卡牌类型添加特定效果
        switch (card.type) {
            case ATTACK:
                // 攻击卡牌可能添加穿透效果
                if (card.damage >= 15 && !card.rawDescription.contains("穿透")) {
                    card.rawDescription += " 穿透。";
                }
                break;
            case SKILL:
                // 技能卡牌可能添加抽牌效果
                if (card.block >= 10 && !card.rawDescription.contains("抽牌")) {
                    card.rawDescription += " 抽1张牌。";
                }
                break;
            case POWER:
                // 能力卡牌可能添加持续效果
                if (!card.rawDescription.contains("持续")) {
                    card.rawDescription += " 持续。";
                }
                break;
        }
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
     * 配置服务
     */
    public void configure(OptimizationConfig config) {
        this.config = config;
    }
    
    /**
     * 重置到默认配置
     */
    public void resetToDefaults() {
        this.config = new OptimizationConfig();
    }
    
    /**
     * 获取统计信息
     */
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalOptimizations", totalOptimizations);
        stats.put("successfulOptimizations", successfulOptimizations);
        stats.put("failedOptimizations", failedOptimizations);
        
        if (totalOptimizations > 0) {
            stats.put("successRate", (double) successfulOptimizations / totalOptimizations);
        }
        
        return stats;
    }
    
    /**
     * 优化配置类
     */
    public static class OptimizationConfig {
        // 启用各种优化
        public boolean enableDescriptionOptimization = true;
        public boolean enableNameOptimization = true;
        public boolean enableCostOptimization = true;
        public boolean enableRarityOptimization = true;
        public boolean enableTargetOptimization = true;
        public boolean enableValueOptimization = true;
        public boolean enableBalanceOptimization = true;
        public boolean enableSpecialEffectOptimization = true;
        
        // 优化强度
        public double optimizationStrength = 1.0;
        
        // 优化阈值
        public double powerRatioThreshold = 0.2;
        public int costDifferenceThreshold = 1;
    }
}