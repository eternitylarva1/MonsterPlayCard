package EveryMonsterPlayCard.downfall.service;

import java.util.HashMap;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.downfall.analyzer.CardAnalysisResult;
import EveryMonsterPlayCard.downfall.analyzer.DependencyAnalysisResult;

/**
 * 平衡性调整器
 * 负责调整移植后卡牌的平衡性
 */
public class BalanceAdjuster {
    
    private Map<String, BalanceRule> balanceRules;
    private boolean enableAutoAdjustment;
    private double adjustmentFactor;
    
    public BalanceAdjuster() {
        this.balanceRules = new HashMap<>();
        this.enableAutoAdjustment = true;
        this.adjustmentFactor = 1.0;
        initializeDefaultRules();
    }
    
    /**
     * 调整卡牌平衡性
     */
    public boolean adjustCardBalance(AbstractCard card) {
        if (card == null || !enableAutoAdjustment) {
            return false;
        }
        
        boolean adjusted = false;
        
        // 调整伤害
        if (adjustDamage(card)) {
            adjusted = true;
        }
        
        // 调整格挡
        if (adjustBlock(card)) {
            adjusted = true;
        }
        
        // 调整费用
        if (adjustCost(card)) {
            adjusted = true;
        }
        
        // 调整魔法数值
        if (adjustMagicNumber(card)) {
            adjusted = true;
        }
        
        return adjusted;
    }
    
    /**
     * 基于分析结果调整平衡性
     */
    public boolean adjustCardBalance(AbstractCard card, CardAnalysisResult analysis, DependencyAnalysisResult dependencies) {
        if (card == null || analysis == null || !enableAutoAdjustment) {
            return false;
        }
        
        boolean adjusted = false;
        
        // 根据卡牌类型应用不同的调整规则
        switch (analysis.getCardType()) {
            case ATTACK:
                adjusted = adjustAttackCardBalance(card, analysis, dependencies);
                break;
            case SKILL:
                adjusted = adjustSkillCardBalance(card, analysis, dependencies);
                break;
            case POWER:
                adjusted = adjustPowerCardBalance(card, analysis, dependencies);
                break;
            default:
                adjusted = adjustCardBalance(card);
                break;
        }
        
        return adjusted;
    }
    
    /**
     * 调整攻击卡牌平衡性
     */
    private boolean adjustAttackCardBalance(AbstractCard card, CardAnalysisResult analysis, DependencyAnalysisResult dependencies) {
        boolean adjusted = false;
        
        // 基础伤害调整
        if (card.baseDamage > 0) {
            double adjustedDamage = card.baseDamage * adjustmentFactor;
            
            // 根据费用调整伤害
            if (card.cost > 0) {
                double damagePerCost = adjustedDamage / card.cost;
                if (damagePerCost > 7.0) {
                    adjustedDamage = card.cost * 7.0; // 限制每点费用的伤害上限
                    adjusted = true;
                }
            }
            
            // 根据依赖复杂度调整
            if (dependencies.getRequiredPowers().size() > 2) {
                adjustedDamage *= 0.9; // 复杂卡牌伤害降低10%
                adjusted = true;
            }
            
            card.baseDamage = (int) Math.round(adjustedDamage);
            card.damage = card.baseDamage;
        }
        
        return adjusted;
    }
    
    /**
     * 调整技能卡牌平衡性
     */
    private boolean adjustSkillCardBalance(AbstractCard card, CardAnalysisResult analysis, DependencyAnalysisResult dependencies) {
        boolean adjusted = false;
        
        // 格挡调整
        if (card.baseBlock > 0) {
            double adjustedBlock = card.baseBlock * adjustmentFactor;
            
            // 根据费用调整格挡
            if (card.cost > 0) {
                double blockPerCost = adjustedBlock / card.cost;
                if (blockPerCost > 8.0) {
                    adjustedBlock = card.cost * 8.0; // 限制每点费用的格挡上限
                    adjusted = true;
                }
            }
            
            card.baseBlock = (int) Math.round(adjustedBlock);
            card.block = card.baseBlock;
        }
        
        // 抽牌效果调整
        if (hasDrawEffect(analysis)) {
            if (card.cost < 1) {
                card.cost = 1; // 抽牌效果至少费用1
                card.costForTurn = card.cost;
                adjusted = true;
            }
        }
        
        return adjusted;
    }
    
    /**
     * 调整能力卡牌平衡性
     */
    private boolean adjustPowerCardBalance(AbstractCard card, CardAnalysisResult analysis, DependencyAnalysisResult dependencies) {
        boolean adjusted = false;
        
        // 能力卡牌通常需要平衡费用和效果强度
        if (card.baseMagicNumber > 0) {
            double adjustedMagic = card.baseMagicNumber * adjustmentFactor;
            
            // 根据费用调整魔法数值
            if (card.cost > 0) {
                double magicPerCost = adjustedMagic / card.cost;
                if (magicPerCost > 5.0) {
                    adjustedMagic = card.cost * 5.0; // 限制每点费用的魔法数值上限
                    adjusted = true;
                }
            }
            
            card.baseMagicNumber = (int) Math.round(adjustedMagic);
            card.magicNumber = card.baseMagicNumber;
        }
        
        return adjusted;
    }
    
    /**
     * 调整伤害
     */
    private boolean adjustDamage(AbstractCard card) {
        if (card.baseDamage <= 0) {
            return false;
        }
        
        boolean adjusted = false;
        
        // 应用伤害上限规则
        BalanceRule damageRule = balanceRules.get("MAX_DAMAGE");
        if (damageRule != null && card.baseDamage > damageRule.getValue()) {
            card.baseDamage = damageRule.getValue();
            card.damage = card.baseDamage;
            adjusted = true;
        }
        
        return adjusted;
    }
    
    /**
     * 调整格挡
     */
    private boolean adjustBlock(AbstractCard card) {
        if (card.baseBlock <= 0) {
            return false;
        }
        
        boolean adjusted = false;
        
        // 应用格挡上限规则
        BalanceRule blockRule = balanceRules.get("MAX_BLOCK");
        if (blockRule != null && card.baseBlock > blockRule.getValue()) {
            card.baseBlock = blockRule.getValue();
            card.block = card.baseBlock;
            adjusted = true;
        }
        
        return adjusted;
    }
    
    /**
     * 调整费用
     */
    private boolean adjustCost(AbstractCard card) {
        boolean adjusted = false;
        
        // 应用费用下限规则
        BalanceRule minCostRule = balanceRules.get("MIN_COST");
        if (minCostRule != null && card.cost < minCostRule.getValue()) {
            card.cost = minCostRule.getValue();
            card.costForTurn = card.cost;
            adjusted = true;
        }
        
        // 应用费用上限规则
        BalanceRule maxCostRule = balanceRules.get("MAX_COST");
        if (maxCostRule != null && card.cost > maxCostRule.getValue()) {
            card.cost = maxCostRule.getValue();
            card.costForTurn = card.cost;
            adjusted = true;
        }
        
        return adjusted;
    }
    
    /**
     * 调整魔法数值
     */
    private boolean adjustMagicNumber(AbstractCard card) {
        if (card.baseMagicNumber <= 0) {
            return false;
        }
        
        boolean adjusted = false;
        
        // 应用魔法数值上限规则
        BalanceRule magicRule = balanceRules.get("MAX_MAGIC");
        if (magicRule != null && card.baseMagicNumber > magicRule.getValue()) {
            card.baseMagicNumber = magicRule.getValue();
            card.magicNumber = card.baseMagicNumber;
            adjusted = true;
        }
        
        return adjusted;
    }
    
    /**
     * 检查是否有抽牌效果
     */
    private boolean hasDrawEffect(CardAnalysisResult analysis) {
        if (analysis.getDescription() == null) {
            return false;
        }
        
        String description = analysis.getDescription().toLowerCase();
        return description.contains("draw") || 
               description.contains("抽牌") || 
               description.contains("抽取");
    }
    
    /**
     * 初始化默认平衡规则
     */
    private void initializeDefaultRules() {
        // 伤害上限
        balanceRules.put("MAX_DAMAGE", new BalanceRule("MAX_DAMAGE", 30, "单次伤害上限"));
        
        // 格挡上限
        balanceRules.put("MAX_BLOCK", new BalanceRule("MAX_BLOCK", 20, "单次格挡上限"));
        
        // 费用范围
        balanceRules.put("MIN_COST", new BalanceRule("MIN_COST", -2, "最低费用"));
        balanceRules.put("MAX_COST", new BalanceRule("MAX_COST", 6, "最高费用"));
        
        // 魔法数值上限
        balanceRules.put("MAX_MAGIC", new BalanceRule("MAX_MAGIC", 15, "魔法数值上限"));
    }
    
    /**
     * 添加平衡规则
     */
    public void addBalanceRule(String name, int value, String description) {
        balanceRules.put(name, new BalanceRule(name, value, description));
    }
    
    /**
     * 移除平衡规则
     */
    public void removeBalanceRule(String name) {
        balanceRules.remove(name);
    }
    
    /**
     * 获取平衡规则
     */
    public BalanceRule getBalanceRule(String name) {
        return balanceRules.get(name);
    }
    
    /**
     * 设置调整因子
     */
    public void setAdjustmentFactor(double factor) {
        this.adjustmentFactor = Math.max(0.1, Math.min(2.0, factor));
    }
    
    /**
     * 设置是否启用自动调整
     */
    public void setEnableAutoAdjustment(boolean enable) {
        this.enableAutoAdjustment = enable;
    }
    
    /**
     * 获取所有平衡规则
     */
    public Map<String, BalanceRule> getAllBalanceRules() {
        return new HashMap<>(balanceRules);
    }
    
    /**
     * 平衡规则
     */
    public static class BalanceRule {
        private final String name;
        private final int value;
        private final String description;
        
        public BalanceRule(String name, int value, String description) {
            this.name = name;
            this.value = value;
            this.description = description;
        }
        
        public String getName() { return name; }
        public int getValue() { return value; }
        public String getDescription() { return description; }
        
        @Override
        public String toString() {
            return String.format("BalanceRule{name='%s', value=%d, description='%s'}", 
                               name, value, description);
        }
    }
}