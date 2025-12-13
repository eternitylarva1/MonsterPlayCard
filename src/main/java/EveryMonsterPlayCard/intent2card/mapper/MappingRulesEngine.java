package EveryMonsterPlayCard.intent2card.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 映射规则引擎
 * 管理意图到卡牌的映射规则
 */
public class MappingRulesEngine {
    
    private Map<IntentType, MappingRule> customRules;
    private Map<String, MappingRule> namedRules;
    
    public MappingRulesEngine() {
        this.customRules = new ConcurrentHashMap<>();
        this.namedRules = new ConcurrentHashMap<>();
    }
    
    /**
     * 添加自定义规则
     */
    public void addCustomRule(IntentType intentType, MappingRule rule) {
        if (intentType != null && rule != null) {
            customRules.put(intentType, rule);
        }
    }
    
    /**
     * 添加命名规则
     */
    public void addNamedRule(String ruleName, MappingRule rule) {
        if (ruleName != null && !ruleName.trim().isEmpty() && rule != null) {
            namedRules.put(ruleName, rule);
        }
    }
    
    /**
     * 获取自定义规则
     */
    public MappingRule getCustomRule(IntentType intentType) {
        return customRules.get(intentType);
    }
    
    /**
     * 获取命名规则
     */
    public MappingRule getNamedRule(String ruleName) {
        return namedRules.get(ruleName);
    }
    
    /**
     * 获取意图的自定义规则
     */
    public MappingRule getRuleForIntent(MonsterIntent intent) {
        if (intent == null) {
            return null;
        }
        
        // 首先检查自定义规则
        MappingRule customRule = customRules.get(intent.getType());
        if (customRule != null && customRule.matches(intent)) {
            return customRule;
        }
        
        // 然后检查命名规则
        for (MappingRule rule : namedRules.values()) {
            if (rule.matches(intent)) {
                return rule;
            }
        }
        
        return null;
    }
    
    /**
     * 移除自定义规则
     */
    public void removeCustomRule(IntentType intentType) {
        customRules.remove(intentType);
    }
    
    /**
     * 移除命名规则
     */
    public void removeNamedRule(String ruleName) {
        namedRules.remove(ruleName);
    }
    
    /**
     * 清空所有自定义规则
     */
    public void clearCustomRules() {
        customRules.clear();
    }
    
    /**
     * 清空所有命名规则
     */
    public void clearNamedRules() {
        namedRules.clear();
    }
    
    /**
     * 清空所有规则
     */
    public void clearAllRules() {
        clearCustomRules();
        clearNamedRules();
    }
    
    /**
     * 获取自定义规则数量
     */
    public int getCustomRuleCount() {
        return customRules.size();
    }
    
    /**
     * 获取命名规则数量
     */
    public int getNamedRuleCount() {
        return namedRules.size();
    }
    
    /**
     * 获取总规则数量
     */
    public int getTotalRuleCount() {
        return customRules.size() + namedRules.size();
    }
    
    /**
     * 检查是否有规则匹配意图
     */
    public boolean hasRuleForIntent(MonsterIntent intent) {
        return getRuleForIntent(intent) != null;
    }
    
    /**
     * 获取所有规则信息
     */
    public RulesInfo getRulesInfo() {
        RulesInfo info = new RulesInfo();
        info.customRulesCount = customRules.size();
        info.namedRulesCount = namedRules.size();
        info.totalRulesCount = info.customRulesCount + info.namedRulesCount;
        info.customRuleTypes = new java.util.ArrayList<>(customRules.keySet());
        info.namedRuleNames = new java.util.ArrayList<>(namedRules.keySet());
        return info;
    }
    
    /**
     * 规则信息类
     */
    public static class RulesInfo {
        public int customRulesCount;
        public int namedRulesCount;
        public int totalRulesCount;
        public java.util.List<IntentType> customRuleTypes;
        public java.util.List<String> namedRuleNames;
        
        @Override
        public String toString() {
            return "RulesInfo{" +
                    "customRulesCount=" + customRulesCount +
                    ", namedRulesCount=" + namedRulesCount +
                    ", totalRulesCount=" + totalRulesCount +
                    ", customRuleTypes=" + customRuleTypes +
                    ", namedRuleNames=" + namedRuleNames +
                    '}';
        }
    }
}