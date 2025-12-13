package EveryMonsterPlayCard.conversion.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 意图优先级解析器
 * 负责解析和排序意图的优先级
 */
public class IntentPriorityResolver {
    private final Map<IntentType, Integer> defaultPriorities;
    private final Map<String, Integer> customPriorities;
    private final Map<String, PriorityRule> priorityRules;
    
    public IntentPriorityResolver() {
        this.defaultPriorities = new HashMap<>();
        this.customPriorities = new HashMap<>();
        this.priorityRules = new HashMap<>();
        initializeDefaultPriorities();
        initializePriorityRules();
    }
    
    /**
     * 初始化默认优先级
     */
    private void initializeDefaultPriorities() {
        defaultPriorities.put(IntentType.ATTACK, 5);
        defaultPriorities.put(IntentType.STRONG, 6);
        defaultPriorities.put(IntentType.DEFEND, 3);
        defaultPriorities.put(IntentType.BUFF, 4);
        defaultPriorities.put(IntentType.DEBUFF, 4);
        // IntentType中没有MAGIC类型，移除这行
        defaultPriorities.put(IntentType.UNKNOWN, 0);
    }
    
    /**
     * 初始化优先级规则
     */
    private void initializePriorityRules() {
        // 高伤害攻击优先级提升
        priorityRules.put("HIGH_DAMAGE_ATTACK", new PriorityRule(
            intent -> intent.getType() == IntentType.ATTACK && intent.getAmount() >= 20,
            1,
            "高伤害攻击（≥20）优先级提升"
        ));
        
        // 低伤害攻击优先级降低
        priorityRules.put("LOW_DAMAGE_ATTACK", new PriorityRule(
            intent -> intent.getType() == IntentType.ATTACK && intent.getAmount() <= 5,
            -1,
            "低伤害攻击（≤5）优先级降低"
        ));
        
        // 高格挡优先级提升
        priorityRules.put("HIGH_BLOCK_DEFEND", new PriorityRule(
            intent -> intent.getType() == IntentType.DEFEND && intent.getAmount() >= 15,
            1,
            "高格挡防御（≥15）优先级提升"
        ));
        
        // 多重增益优先级提升
        priorityRules.put("MULTI_BUFF", new PriorityRule(
            intent -> intent.getType() == IntentType.BUFF && intent.getAmount() >= 3,
            1,
            "多重增益（≥3）优先级提升"
        ));
        
        // 多重减益优先级提升
        priorityRules.put("MULTI_DEBUFF", new PriorityRule(
            intent -> intent.getType() == IntentType.DEBUFF && intent.getAmount() >= 3,
            1,
            "多重减益（≥3）优先级提升"
        ));
        
        // 未知意图优先级降低
        priorityRules.put("UNKNOWN_INTENT", new PriorityRule(
            intent -> intent.getType() == IntentType.UNKNOWN,
            -2,
            "未知意图优先级降低"
        ));
    }
    
    /**
     * 解析意图优先级
     * @param intent 怪物意图
     * @return 优先级
     */
    public int resolvePriority(MonsterIntent intent) {
        if (intent == null) {
            return 0;
        }
        
        // 获取基础优先级
        int basePriority = getBasePriority(intent);
        
        // 应用优先级规则
        int adjustedPriority = applyPriorityRules(intent, basePriority);
        
        // 应用自定义优先级
        int finalPriority = applyCustomPriorities(intent, adjustedPriority);
        
        // 确保优先级在合理范围内
        return Math.max(0, Math.min(10, finalPriority));
    }
    
    /**
     * 获取基础优先级
     * @param intent 怪物意图
     * @return 基础优先级
     */
    private int getBasePriority(MonsterIntent intent) {
        // 首先检查意图元数据中是否有优先级
        if (intent.getMetadata() != null && intent.getMetadata().getPriority() > 0) {
            return intent.getMetadata().getPriority();
        }
        
        // 使用默认优先级
        return defaultPriorities.getOrDefault(intent.getType(), 0);
    }
    
    /**
     * 应用优先级规则
     * @param intent 怪物意图
     * @param basePriority 基础优先级
     * @return 调整后的优先级
     */
    private int applyPriorityRules(MonsterIntent intent, int basePriority) {
        int adjustedPriority = basePriority;
        
        for (PriorityRule rule : priorityRules.values()) {
            if (rule.matches(intent)) {
                adjustedPriority += rule.getPriorityAdjustment();
            }
        }
        
        return adjustedPriority;
    }
    
    /**
     * 应用自定义优先级
     * @param intent 怪物意图
     * @param adjustedPriority 调整后的优先级
     * @return 最终优先级
     */
    private int applyCustomPriorities(MonsterIntent intent, int adjustedPriority) {
        // 检查是否有基于意图ID的自定义优先级
        String intentId = intent.getMetadata() != null ? intent.getMetadata().getIntentId() : null;
        if (intentId != null && customPriorities.containsKey(intentId)) {
            return customPriorities.get(intentId);
        }
        
        // 检查是否有基于意图类型的自定义优先级
        String intentTypeKey = "TYPE_" + intent.getType().toString();
        if (customPriorities.containsKey(intentTypeKey)) {
            return customPriorities.get(intentTypeKey);
        }
        
        return adjustedPriority;
    }
    
    /**
     * 排序意图列表
     * @param intents 意图列表
     * @return 按优先级排序的意图列表
     */
    public List<MonsterIntent> sortByPriority(List<MonsterIntent> intents) {
        if (intents == null || intents.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<MonsterIntent> sortedIntents = new ArrayList<>(intents);
        
        sortedIntents.sort((intent1, intent2) -> {
            int priority1 = resolvePriority(intent1);
            int priority2 = resolvePriority(intent2);
            
            // 优先级高的排在前面
            return Integer.compare(priority2, priority1);
        });
        
        return sortedIntents;
    }
    
    /**
     * 获取最高优先级的意图
     * @param intents 意图列表
     * @return 最高优先级的意图，如果列表为空则返回null
     */
    public MonsterIntent getHighestPriorityIntent(List<MonsterIntent> intents) {
        if (intents == null || intents.isEmpty()) {
            return null;
        }
        
        MonsterIntent highestIntent = null;
        int highestPriority = -1;
        
        for (MonsterIntent intent : intents) {
            int priority = resolvePriority(intent);
            if (priority > highestPriority) {
                highestPriority = priority;
                highestIntent = intent;
            }
        }
        
        return highestIntent;
    }
    
    /**
     * 获取指定优先级范围的意图
     * @param intents 意图列表
     * @param minPriority 最小优先级
     * @param maxPriority 最大优先级
     * @return 指定优先级范围的意图列表
     */
    public List<MonsterIntent> getIntentsByPriorityRange(List<MonsterIntent> intents, int minPriority, int maxPriority) {
        List<MonsterIntent> result = new ArrayList<>();
        
        if (intents == null) {
            return result;
        }
        
        for (MonsterIntent intent : intents) {
            int priority = resolvePriority(intent);
            if (priority >= minPriority && priority <= maxPriority) {
                result.add(intent);
            }
        }
        
        return result;
    }
    
    /**
     * 设置自定义优先级
     * @param key 键（意图ID或意图类型）
     * @param priority 优先级
     */
    public void setCustomPriority(String key, int priority) {
        customPriorities.put(key, priority);
    }
    
    /**
     * 移除自定义优先级
     * @param key 键
     * @return 是否成功移除
     */
    public boolean removeCustomPriority(String key) {
        return customPriorities.remove(key) != null;
    }
    
    /**
     * 清空所有自定义优先级
     */
    public void clearCustomPriorities() {
        customPriorities.clear();
    }
    
    /**
     * 添加优先级规则
     * @param ruleId 规则ID
     * @param rule 优先级规则
     */
    public void addPriorityRule(String ruleId, PriorityRule rule) {
        priorityRules.put(ruleId, rule);
    }
    
    /**
     * 移除优先级规则
     * @param ruleId 规则ID
     * @return 是否成功移除
     */
    public boolean removePriorityRule(String ruleId) {
        return priorityRules.remove(ruleId) != null;
    }
    
    /**
     * 清空所有优先级规则
     */
    public void clearPriorityRules() {
        priorityRules.clear();
    }
    
    /**
     * 获取默认优先级映射
     * @return 默认优先级映射
     */
    public Map<IntentType, Integer> getDefaultPriorities() {
        return new HashMap<>(defaultPriorities);
    }
    
    /**
     * 设置默认优先级
     * @param intentType 意图类型
     * @param priority 优先级
     */
    public void setDefaultPriority(IntentType intentType, int priority) {
        defaultPriorities.put(intentType, priority);
    }
    
    /**
     * 获取优先级统计信息
     * @param intents 意图列表
     * @return 优先级统计信息
     */
    public Map<String, Object> getPriorityStatistics(List<MonsterIntent> intents) {
        Map<String, Object> stats = new HashMap<>();
        
        if (intents == null || intents.isEmpty()) {
            stats.put("totalIntents", 0);
            return stats;
        }
        
        Map<IntentType, Integer> typeCount = new HashMap<>();
        Map<Integer, Integer> priorityCount = new HashMap<>();
        int totalPriority = 0;
        
        for (MonsterIntent intent : intents) {
            // 统计意图类型
            IntentType type = intent.getType();
            typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
            
            // 统计优先级
            int priority = resolvePriority(intent);
            priorityCount.put(priority, priorityCount.getOrDefault(priority, 0) + 1);
            totalPriority += priority;
        }
        
        stats.put("totalIntents", intents.size());
        stats.put("typeDistribution", typeCount);
        stats.put("priorityDistribution", priorityCount);
        stats.put("averagePriority", (double) totalPriority / intents.size());
        
        return stats;
    }
    
    /**
     * 优先级规则
     */
    public static class PriorityRule {
        private final IntentMatcher matcher;
        private final int priorityAdjustment;
        private final String description;
        
        public PriorityRule(IntentMatcher matcher, int priorityAdjustment, String description) {
            this.matcher = matcher;
            this.priorityAdjustment = priorityAdjustment;
            this.description = description;
        }
        
        /**
         * 检查意图是否匹配规则
         * @param intent 怪物意图
         * @return 是否匹配
         */
        public boolean matches(MonsterIntent intent) {
            return matcher.matches(intent);
        }
        
        /**
         * 获取优先级调整值
         * @return 优先级调整值
         */
        public int getPriorityAdjustment() {
            return priorityAdjustment;
        }
        
        /**
         * 获取规则描述
         * @return 规则描述
         */
        public String getDescription() {
            return description;
        }
    }
    
    /**
     * 意图匹配器接口
     */
    public interface IntentMatcher {
        /**
         * 检查意图是否匹配
         * @param intent 怪物意图
         * @return 是否匹配
         */
        boolean matches(MonsterIntent intent);
    }
}