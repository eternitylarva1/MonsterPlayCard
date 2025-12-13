package EveryMonsterPlayCard.conversion.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.powers.AbstractPower;

import EveryMonsterPlayCard.core.data.IntentType;

/**
 * 意图映射规则集合
 * 管理所有动作到意图的映射规则
 */
public class IntentMappingRules {
    private final Map<String, List<IntentMappingRule>> rulesByActionType;
    private final List<IntentMappingRule> customRules;
    
    public IntentMappingRules() {
        this.rulesByActionType = new HashMap<>();
        this.customRules = new ArrayList<>();
        initializeDefaultRules();
    }
    
    /**
     * 初始化默认映射规则
     */
    private void initializeDefaultRules() {
        // 伤害动作规则
        addRule(IntentMappingRule.createDamageRule("DAMAGE_BASIC", 5, "基础伤害动作"));
        
        // 格挡动作规则
        addRule(IntentMappingRule.createBlockRule("BLOCK_BASIC", 3, "基础格挡动作"));
        
        // 能力动作规则
        addRule(IntentMappingRule.createPowerRule("POWER_BUFF", 4, "增益能力动作"));
        
        // 抽牌动作规则
        addRule(IntentMappingRule.createDrawRule("DRAW_BASIC", 2, "基础抽牌动作"));
        
        // 能量动作规则
        addRule(IntentMappingRule.createEnergyRule("ENERGY_BASIC", 2, "基础能量动作"));
        
        // 治疗动作规则
        addRule(IntentMappingRule.createHealRule("HEAL_BASIC", 3, "基础治疗动作"));
        
        // 弃牌动作规则
        addRule(IntentMappingRule.createDiscardRule("DISCARD_BASIC", 2, "基础弃牌动作"));
        
        // 消耗动作规则
        addRule(IntentMappingRule.createExhaustRule("EXHAUST_BASIC", 1, "基础消耗动作"));
        
        // 自定义规则：减益能力
        addCustomRule(IntentMappingRule.createCustomRule(
            "POWER_DEBUFF",
            action -> action instanceof ApplyPowerAction && isDebuffAction((ApplyPowerAction) action),
            IntentType.DEBUFF,
            4,
            "减益能力动作"
        ));
        
        // 自定义规则：强攻击
        addCustomRule(IntentMappingRule.createCustomRule(
            "STRONG_ATTACK",
            action -> action instanceof DamageAction && ((DamageAction) action).amount >= 20,
            IntentType.STRONG,
            6,
            "强攻击动作（伤害≥20）"
        ));
        
        // 自定义规则：弱攻击
        addCustomRule(IntentMappingRule.createCustomRule(
            "WEAK_ATTACK",
            action -> action instanceof DamageAction && ((DamageAction) action).amount <= 5,
            IntentType.ATTACK,
            4,
            "弱攻击动作（伤害≤5）"
        ));
    }
    
    /**
     * 检查是否为减益动作
     * @param action 能力动作
     * @return 是否为减益动作
     */
    private boolean isDebuffAction(ApplyPowerAction action) {
        try {
            java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
            powerField.setAccessible(true);
            AbstractPower power = (AbstractPower) powerField.get(action);
            return power != null && power.type == AbstractPower.PowerType.DEBUFF;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 添加映射规则
     * @param rule 映射规则
     */
    public void addRule(IntentMappingRule rule) {
        String actionType = getActionTypeForRule(rule);
        if (!rulesByActionType.containsKey(actionType)) {
            rulesByActionType.put(actionType, new ArrayList<>());
        }
        rulesByActionType.get(actionType).add(rule);
    }
    
    /**
     * 添加自定义规则
     * @param rule 映射规则
     */
    public void addCustomRule(IntentMappingRule rule) {
        customRules.add(rule);
    }
    
    /**
     * 获取规则对应的动作类型
     * @param rule 映射规则
     * @return 动作类型
     */
    private String getActionTypeForRule(IntentMappingRule rule) {
        // 根据规则ID推断动作类型
        String ruleId = rule.getRuleId();
        if (ruleId.startsWith("DAMAGE")) {
            return "DamageAction";
        } else if (ruleId.startsWith("BLOCK")) {
            return "GainBlockAction";
        } else if (ruleId.startsWith("POWER")) {
            return "ApplyPowerAction";
        } else if (ruleId.startsWith("DRAW")) {
            return "DrawCardAction";
        } else if (ruleId.startsWith("ENERGY")) {
            return "GainEnergyAction";
        } else if (ruleId.startsWith("HEAL")) {
            return "HealAction";
        } else if (ruleId.startsWith("DISCARD")) {
            return "DiscardAction";
        } else if (ruleId.startsWith("EXHAUST")) {
            return "ExhaustAction";
        } else {
            return "Custom";
        }
    }
    
    /**
     * 查找匹配的动作规则
     * @param action 游戏动作
     * @return 匹配的规则列表，按优先级排序
     */
    public List<IntentMappingRule> findMatchingRules(AbstractGameAction action) {
        List<IntentMappingRule> matchingRules = new ArrayList<>();
        
        // 查找特定动作类型的规则
        String actionClassName = action.getClass().getSimpleName();
        if (rulesByActionType.containsKey(actionClassName)) {
            for (IntentMappingRule rule : rulesByActionType.get(actionClassName)) {
                if (rule.matches(action)) {
                    matchingRules.add(rule);
                }
            }
        }
        
        // 查找自定义规则
        for (IntentMappingRule rule : customRules) {
            if (rule.matches(action)) {
                matchingRules.add(rule);
            }
        }
        
        // 按优先级排序（高优先级在前）
        matchingRules.sort((r1, r2) -> Integer.compare(r2.getPriority(), r1.getPriority()));
        
        return matchingRules;
    }
    
    /**
     * 获取最佳匹配规则
     * @param action 游戏动作
     * @return 最佳匹配规则，如果没有匹配则返回null
     */
    public IntentMappingRule getBestMatchingRule(AbstractGameAction action) {
        List<IntentMappingRule> matchingRules = findMatchingRules(action);
        return matchingRules.isEmpty() ? null : matchingRules.get(0);
    }
    
    /**
     * 获取所有规则
     * @return 所有规则的列表
     */
    public List<IntentMappingRule> getAllRules() {
        List<IntentMappingRule> allRules = new ArrayList<>();
        
        for (List<IntentMappingRule> rules : rulesByActionType.values()) {
            allRules.addAll(rules);
        }
        
        allRules.addAll(customRules);
        
        return allRules;
    }
    
    /**
     * 根据规则ID获取规则
     * @param ruleId 规则ID
     * @return 映射规则，如果不存在则返回null
     */
    public IntentMappingRule getRuleById(String ruleId) {
        for (List<IntentMappingRule> rules : rulesByActionType.values()) {
            for (IntentMappingRule rule : rules) {
                if (rule.getRuleId().equals(ruleId)) {
                    return rule;
                }
            }
        }
        
        for (IntentMappingRule rule : customRules) {
            if (rule.getRuleId().equals(ruleId)) {
                return rule;
            }
        }
        
        return null;
    }
    
    /**
     * 移除规则
     * @param ruleId 规则ID
     * @return 是否成功移除
     */
    public boolean removeRule(String ruleId) {
        // 从特定动作类型规则中移除
        for (List<IntentMappingRule> rules : rulesByActionType.values()) {
            for (int i = 0; i < rules.size(); i++) {
                if (rules.get(i).getRuleId().equals(ruleId)) {
                    rules.remove(i);
                    return true;
                }
            }
        }
        
        // 从自定义规则中移除
        for (int i = 0; i < customRules.size(); i++) {
            if (customRules.get(i).getRuleId().equals(ruleId)) {
                customRules.remove(i);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 清空所有规则
     */
    public void clearAllRules() {
        rulesByActionType.clear();
        customRules.clear();
    }
    
    /**
     * 获取规则统计信息
     * @return 规则统计信息
     */
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        int totalRules = 0;
        Map<String, Integer> rulesByType = new HashMap<>();
        
        for (Map.Entry<String, List<IntentMappingRule>> entry : rulesByActionType.entrySet()) {
            int count = entry.getValue().size();
            totalRules += count;
            rulesByType.put(entry.getKey(), count);
        }
        
        totalRules += customRules.size();
        rulesByType.put("Custom", customRules.size());
        
        stats.put("totalRules", totalRules);
        stats.put("rulesByType", rulesByType);
        
        return stats;
    }
}