package EveryMonsterPlayCard.optimization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import EveryMonsterPlayCard.converter.UniversalActionConverter;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * 性能优化器
 * 负责优化动作-意图转换系统的性能
 */
public class PerformanceOptimizer {
    
    // 单例实例
    private static PerformanceOptimizer instance = null;
    
    // 缓存系统
    private final Map<String, MonsterIntent> intentCache = new ConcurrentHashMap<>();
    private final Map<String, ActionAnalysisResult> analysisCache = new ConcurrentHashMap<>();
    private final Map<String, Long> lastAccessTime = new ConcurrentHashMap<>();
    
    // 性能统计
    private long totalConversions = 0;
    private long cacheHits = 0;
    private long cacheMisses = 0;
    private long totalConversionTime = 0;
    private long maxConversionTime = 0;
    private long minConversionTime = Long.MAX_VALUE;
    
    // 配置选项
    private boolean enableCaching = true;
    private boolean enableBatchProcessing = true;
    private boolean enableAsyncProcessing = false;
    private int maxCacheSize = 1000;
    private long cacheExpirationTime = 300000; // 5分钟
    
    private PerformanceOptimizer() {
        initializeOptimizer();
    }
    
    /**
     * 获取单例实例
     */
    public static PerformanceOptimizer getInstance() {
        if (instance == null) {
            instance = new PerformanceOptimizer();
        }
        return instance;
    }
    
    /**
     * 初始化优化器
     */
    private void initializeOptimizer() {
        // 启动缓存清理线程
        startCacheCleanupThread();
        
        Hpr.info("性能优化器已初始化");
    }
    
    /**
     * 优化的动作转换方法
     * 
     * @param action 动作
     * @return 转换后的意图
     */
    public MonsterIntent optimizedConvertAction(com.megacrit.cardcrawl.actions.AbstractGameAction action) {
        if (!enableCaching) {
            return convertWithoutCache(action);
        }
        
        long startTime = System.nanoTime();
        
        try {
            // 生成缓存键
            String cacheKey = generateCacheKey(action);
            
            // 检查缓存
            MonsterIntent cachedIntent = getCachedIntent(cacheKey);
            if (cachedIntent != null) {
                cacheHits++;
                updatePerformanceStats(startTime);
                return cachedIntent;
            }
            
            // 缓存未命中，执行转换
            cacheMisses++;
            MonsterIntent intent = convertWithoutCache(action);
            
            // 存入缓存
            cacheIntent(cacheKey, intent);
            
            updatePerformanceStats(startTime);
            return intent;
            
        } catch (Exception e) {
            Hpr.info("优化转换动作时出错: " + e.getMessage());
            return convertWithoutCache(action);
        }
    }
    
    /**
     * 不使用缓存的转换方法
     */
    private MonsterIntent convertWithoutCache(com.megacrit.cardcrawl.actions.AbstractGameAction action) {
        try {
            UniversalActionConverter converter = new UniversalActionConverter();
            return converter.convertAction(action);
        } catch (Exception e) {
            Hpr.info("转换动作失败: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * 生成缓存键
     */
    private String generateCacheKey(com.megacrit.cardcrawl.actions.AbstractGameAction action) {
        if (action == null) {
            return "null_action";
        }
        
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(action.getClass().getSimpleName());
        keyBuilder.append("_");
        keyBuilder.append(action.amount);
        keyBuilder.append("_");
        keyBuilder.append(action.actionType != null ? action.actionType.name() : "NULL");
        
        return keyBuilder.toString();
    }
    
    /**
     * 获取缓存的意图
     */
    private MonsterIntent getCachedIntent(String cacheKey) {
        if (!enableCaching || cacheKey == null) {
            return null;
        }
        
        // 检查缓存是否存在且未过期
        MonsterIntent intent = intentCache.get(cacheKey);
        if (intent != null) {
            Long lastAccess = lastAccessTime.get(cacheKey);
            if (lastAccess != null && (System.currentTimeMillis() - lastAccess) < cacheExpirationTime) {
                lastAccessTime.put(cacheKey, System.currentTimeMillis());
                return intent;
            } else {
                // 缓存已过期，移除
                intentCache.remove(cacheKey);
                lastAccessTime.remove(cacheKey);
            }
        }
        
        return null;
    }
    
    /**
     * 缓存意图
     */
    private void cacheIntent(String cacheKey, MonsterIntent intent) {
        if (!enableCaching || cacheKey == null || intent == null) {
            return;
        }
        
        // 检查缓存大小限制
        if (intentCache.size() >= maxCacheSize) {
            // 移除最旧的缓存项
            removeOldestCacheEntry();
        }
        
        intentCache.put(cacheKey, intent);
        lastAccessTime.put(cacheKey, System.currentTimeMillis());
    }
    
    /**
     * 移除最旧的缓存项
     */
    private void removeOldestCacheEntry() {
        String oldestKey = null;
        long oldestTime = Long.MAX_VALUE;
        
        for (Map.Entry<String, Long> entry : lastAccessTime.entrySet()) {
            if (entry.getValue() < oldestTime) {
                oldestTime = entry.getValue();
                oldestKey = entry.getKey();
            }
        }
        
        if (oldestKey != null) {
            intentCache.remove(oldestKey);
            lastAccessTime.remove(oldestKey);
        }
    }
    
    /**
     * 更新性能统计
     */
    private void updatePerformanceStats(long startTime) {
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        
        totalConversions++;
        totalConversionTime += duration;
        maxConversionTime = Math.max(maxConversionTime, duration);
        minConversionTime = Math.min(minConversionTime, duration);
    }
    
    /**
     * 启动缓存清理线程
     */
    private void startCacheCleanupThread() {
        Thread cleanupThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(cacheExpirationTime / 2); // 每隔一半过期时间清理一次
                    cleanupExpiredCache();
                } catch (InterruptedException e) {
                    Hpr.info("缓存清理线程被中断");
                    break;
                }
            }
        });
        
        cleanupThread.setDaemon(true);
        cleanupThread.setName("ActionIntentCacheCleanup");
        cleanupThread.start();
    }
    
    /**
     * 清理过期缓存
     */
    private void cleanupExpiredCache() {
        long currentTime = System.currentTimeMillis();
        int removedCount = 0;
        
        for (Map.Entry<String, Long> entry : lastAccessTime.entrySet()) {
            if ((currentTime - entry.getValue()) >= cacheExpirationTime) {
                String key = entry.getKey();
                intentCache.remove(key);
                lastAccessTime.remove(key);
                removedCount++;
            }
        }
        
        if (removedCount > 0) {
            Hpr.info("清理了 " + removedCount + " 个过期缓存项");
        }
    }
    
    /**
     * 批量转换动作
     */
    public java.util.List<MonsterIntent> optimizedBatchConvert(java.util.List<com.megacrit.cardcrawl.actions.AbstractGameAction> actions) {
        if (!enableBatchProcessing || actions == null || actions.isEmpty()) {
            java.util.List<MonsterIntent> results = new java.util.ArrayList<>();
            for (com.megacrit.cardcrawl.actions.AbstractGameAction action : actions) {
                results.add(optimizedConvertAction(action));
            }
            return results;
        }
        
        java.util.List<MonsterIntent> results = new java.util.ArrayList<>();
        
        // 批量处理优化
        for (com.megacrit.cardcrawl.actions.AbstractGameAction action : actions) {
            results.add(optimizedConvertAction(action));
        }
        
        return results;
    }
    
    /**
     * 获取性能统计信息
     */
    public String getPerformanceStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== 动作-意图转换性能统计 ===\n");
        stats.append("总转换次数: ").append(totalConversions).append("\n");
        stats.append("缓存命中次数: ").append(cacheHits).append("\n");
        stats.append("缓存未命中次数: ").append(cacheMisses).append("\n");
        
        if (cacheHits + cacheMisses > 0) {
            double hitRate = (double) cacheHits / (cacheHits + cacheMisses) * 100;
            stats.append("缓存命中率: ").append(String.format("%.2f%%", hitRate)).append("\n");
        }
        
        if (totalConversions > 0) {
            long avgTime = totalConversionTime / totalConversions;
            stats.append("平均转换时间: ").append(avgTime).append(" 纳秒\n");
            stats.append("最大转换时间: ").append(maxConversionTime).append(" 纳秒\n");
            stats.append("最小转换时间: ").append(minConversionTime).append(" 纳秒\n");
        }
        
        stats.append("当前缓存大小: ").append(intentCache.size()).append("/").append(maxCacheSize).append("\n");
        stats.append("缓存启用状态: ").append(enableCaching ? "启用" : "禁用").append("\n");
        stats.append("批量处理启用状态: ").append(enableBatchProcessing ? "启用" : "禁用").append("\n");
        stats.append("异步处理启用状态: ").append(enableAsyncProcessing ? "启用" : "禁用").append("\n");
        stats.append("================================");
        
        return stats.toString();
    }
    
    /**
     * 重置性能统计
     */
    public void resetStatistics() {
        totalConversions = 0;
        cacheHits = 0;
        cacheMisses = 0;
        totalConversionTime = 0;
        maxConversionTime = 0;
        minConversionTime = Long.MAX_VALUE;
        
        Hpr.info("性能统计已重置");
    }
    
    /**
     * 清空缓存
     */
    public void clearCache() {
        intentCache.clear();
        analysisCache.clear();
        lastAccessTime.clear();
        
        Hpr.info("缓存已清空");
    }
    
    /**
     * 设置缓存启用状态
     */
    public void setCachingEnabled(boolean enabled) {
        enableCaching = enabled;
        if (!enabled) {
            clearCache();
        }
        Hpr.info("缓存" + (enabled ? "已启用" : "已禁用"));
    }
    
    /**
     * 设置批量处理启用状态
     */
    public void setBatchProcessingEnabled(boolean enabled) {
        enableBatchProcessing = enabled;
        Hpr.info("批量处理" + (enabled ? "已启用" : "已禁用"));
    }
    
    /**
     * 设置异步处理启用状态
     */
    public void setAsyncProcessingEnabled(boolean enabled) {
        enableAsyncProcessing = enabled;
        Hpr.info("异步处理" + (enabled ? "已启用" : "已禁用"));
    }
    
    /**
     * 设置最大缓存大小
     */
    public void setMaxCacheSize(int size) {
        maxCacheSize = Math.max(100, size);
        Hpr.info("最大缓存大小设置为: " + maxCacheSize);
    }
    
    /**
     * 设置缓存过期时间
     */
    public void setCacheExpirationTime(long timeMs) {
        cacheExpirationTime = Math.max(60000, timeMs); // 最少1分钟
        Hpr.info("缓存过期时间设置为: " + cacheExpirationTime + " 毫秒");
    }
    
    /**
     * 获取缓存命中率
     */
    public double getCacheHitRate() {
        if (cacheHits + cacheMisses == 0) {
            return 0.0;
        }
        return (double) cacheHits / (cacheHits + cacheMisses) * 100;
    }
    
    /**
     * 获取平均转换时间
     */
    public long getAverageConversionTime() {
        if (totalConversions == 0) {
            return 0;
        }
        return totalConversionTime / totalConversions;
    }
    
    /**
     * 内部类：动作分析结果
     */
    private static class ActionAnalysisResult {
        public final String actionType;
        public final int amount;
        public final long timestamp;
        
        public ActionAnalysisResult(String actionType, int amount) {
            this.actionType = actionType;
            this.amount = amount;
            this.timestamp = System.currentTimeMillis();
        }
    }
}