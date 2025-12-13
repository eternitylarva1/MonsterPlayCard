package EveryMonsterPlayCard.intent2card.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 卡牌缓存服务
 * 负责缓存生成的卡牌，提高性能
 */
public class CardCacheService {
    
    private static CardCacheService instance;
    
    // 缓存存储
    private final Map<String, AbstractCard> cardCache;
    private final Map<String, Long> cacheTimestamps;
    
    // 缓存统计
    private int totalRequests = 0;
    private int cacheHits = 0;
    private int cacheMisses = 0;
    private int cacheEvictions = 0;
    
    // 缓存配置
    private CacheConfig config = new CacheConfig();
    
    private CardCacheService() {
        // 初始化缓存
        if (config.enableLRU) {
            this.cardCache = new LinkedHashMap<String, AbstractCard>(config.maxSize, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, AbstractCard> eldest) {
                    boolean shouldRemove = size() > config.maxSize;
                    if (shouldRemove) {
                        cacheEvictions++;
                        cacheTimestamps.remove(eldest.getKey());
                    }
                    return shouldRemove;
                }
            };
        } else {
            this.cardCache = new HashMap<>(config.maxSize);
        }
        
        this.cacheTimestamps = new HashMap<>(config.maxSize);
    }
    
    /**
     * 获取服务实例
     */
    public static synchronized CardCacheService getInstance() {
        if (instance == null) {
            instance = new CardCacheService();
        }
        return instance;
    }
    
    /**
     * 获取缓存的卡牌
     */
    public AbstractCard getCachedCard(MonsterIntent intent) {
        if (intent == null || !config.enabled) {
            return null;
        }
        
        totalRequests++;
        
        String cacheKey = generateCacheKey(intent);
        
        // 检查缓存是否存在
        if (!cardCache.containsKey(cacheKey)) {
            cacheMisses++;
            return null;
        }
        
        // 检查缓存是否过期
        if (config.enableTTL) {
            Long timestamp = cacheTimestamps.get(cacheKey);
            if (timestamp == null || (System.currentTimeMillis() - timestamp) > config.ttlMillis) {
                // 缓存过期，移除
                cardCache.remove(cacheKey);
                cacheTimestamps.remove(cacheKey);
                cacheMisses++;
                return null;
            }
        }
        
        cacheHits++;
        
        // 返回卡牌副本
        AbstractCard cachedCard = cardCache.get(cacheKey);
        return cachedCard != null ? cachedCard.makeCopy() : null;
    }
    
    /**
     * 缓存卡牌
     */
    public void cacheCard(MonsterIntent intent, AbstractCard card) {
        if (intent == null || card == null || !config.enabled) {
            return;
        }
        
        String cacheKey = generateCacheKey(intent);
        
        // 检查缓存是否已满
        if (cardCache.size() >= config.maxSize && !config.enableLRU) {
            // 简单的FIFO策略：移除第一个元素
            String firstKey = cardCache.keySet().iterator().next();
            cardCache.remove(firstKey);
            cacheTimestamps.remove(firstKey);
            cacheEvictions++;
        }
        
        // 添加到缓存
        cardCache.put(cacheKey, card.makeCopy());
        
        if (config.enableTTL) {
            cacheTimestamps.put(cacheKey, System.currentTimeMillis());
        }
    }
    
    /**
     * 清空缓存
     */
    public void clearCache() {
        cardCache.clear();
        cacheTimestamps.clear();
    }
    
    /**
     * 清理过期缓存
     */
    public void cleanupExpiredCache() {
        if (!config.enableTTL) {
            return;
        }
        
        long currentTime = System.currentTimeMillis();
        java.util.List<String> expiredKeys = cacheTimestamps.entrySet().stream()
            .filter(entry -> (currentTime - entry.getValue()) > config.ttlMillis)
            .map(Map.Entry::getKey)
            .collect(java.util.stream.Collectors.toList());
        
        for (String key : expiredKeys) {
            cardCache.remove(key);
            cacheTimestamps.remove(key);
            cacheEvictions++;
        }
    }
    
    /**
     * 预热缓存
     */
    public void warmupCache(Map<MonsterIntent, AbstractCard> preloadCards) {
        if (preloadCards == null || !config.enabled) {
            return;
        }
        
        for (Map.Entry<MonsterIntent, AbstractCard> entry : preloadCards.entrySet()) {
            cacheCard(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * 生成缓存键
     */
    private String generateCacheKey(MonsterIntent intent) {
        StringBuilder keyBuilder = new StringBuilder();
        
        // 基本属性
        keyBuilder.append(intent.getType().name());
        keyBuilder.append(":").append(intent.getAmount());
        
        // 特殊属性
        if (intent.hasProperty("damage")) {
            keyBuilder.append(":damage=").append(intent.getIntParameter("damage"));
        }
        
        if (intent.hasProperty("block")) {
            keyBuilder.append(":block=").append(intent.getIntParameter("block"));
        }
        
        if (intent.hasProperty("magicNumber")) {
            keyBuilder.append(":magic=").append(intent.getIntParameter("magicNumber"));
        }
        
        if (intent.hasProperty("specialEffect")) {
            keyBuilder.append(":effect=").append(intent.getStringParameter("specialEffect"));
        }
        
        // 复合意图
        if (intent.hasProperty("isComposite") && intent.getBooleanParameter("isComposite")) {
            keyBuilder.append(":composite");
            
            if (intent.hasProperty("secondaryIntent")) {
                keyBuilder.append(":secondary=").append(intent.getStringParameter("secondaryIntent"));
            }
            
            if (intent.hasProperty("secondaryAmount")) {
                keyBuilder.append(":secondaryAmount=").append(intent.getIntParameter("secondaryAmount"));
            }
        }
        
        return keyBuilder.toString();
    }
    
    /**
     * 配置服务
     */
    public void configure(CacheConfig config) {
        // 保存当前缓存大小
        int currentSize = cardCache.size();
        
        // 更新配置
        this.config = config;
        
        // 如果新配置的最大大小小于当前缓存大小，需要清理缓存
        if (currentSize > config.maxSize) {
            if (config.enableLRU) {
                // LRU会自动清理
                cleanupExpiredCache();
            } else {
                // 手动清理到新的大小限制
                while (cardCache.size() > config.maxSize) {
                    String firstKey = cardCache.keySet().iterator().next();
                    cardCache.remove(firstKey);
                    cacheTimestamps.remove(firstKey);
                    cacheEvictions++;
                }
            }
        }
    }
    
    /**
     * 重置到默认配置
     */
    public void resetToDefaults() {
        configure(new CacheConfig());
    }
    
    /**
     * 获取统计信息
     */
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("enabled", config.enabled);
        stats.put("maxSize", config.maxSize);
        stats.put("currentSize", cardCache.size());
        stats.put("enableTTL", config.enableTTL);
        stats.put("ttlMillis", config.ttlMillis);
        stats.put("enableLRU", config.enableLRU);
        
        stats.put("totalRequests", totalRequests);
        stats.put("cacheHits", cacheHits);
        stats.put("cacheMisses", cacheMisses);
        stats.put("cacheEvictions", cacheEvictions);
        
        if (totalRequests > 0) {
            stats.put("hitRate", (double) cacheHits / totalRequests);
            stats.put("missRate", (double) cacheMisses / totalRequests);
        }
        
        return stats;
    }
    
    /**
     * 获取缓存内容信息
     */
    public Map<String, Object> getCacheContentInfo() {
        Map<String, Object> info = new HashMap<>();
        
        Map<String, Integer> intentTypeCount = new HashMap<>();
        Map<String, Integer> compositeCount = new HashMap<>();
        
        for (String key : cardCache.keySet()) {
            // 统计意图类型
            String[] parts = key.split(":");
            if (parts.length > 0) {
                String intentType = parts[0];
                intentTypeCount.put(intentType, intentTypeCount.getOrDefault(intentType, 0) + 1);
            }
            
            // 统计复合意图
            if (key.contains(":composite")) {
                String compositeType = "unknown";
                if (key.contains(":secondary=")) {
                    int secondaryIndex = key.indexOf(":secondary=");
                    if (secondaryIndex != -1) {
                        int endIndex = key.indexOf(":", secondaryIndex + 1);
                        if (endIndex == -1) {
                            endIndex = key.length();
                        }
                        compositeType = key.substring(secondaryIndex + 11, endIndex);
                    }
                }
                compositeCount.put(compositeType, compositeCount.getOrDefault(compositeType, 0) + 1);
            }
        }
        
        info.put("intentTypeDistribution", intentTypeCount);
        info.put("compositeIntentDistribution", compositeCount);
        
        return info;
    }
    
    /**
     * 缓存配置类
     */
    public static class CacheConfig {
        // 基本配置
        public boolean enabled = true;
        public int maxSize = 100;
        
        // TTL配置
        public boolean enableTTL = false;
        public long ttlMillis = 5 * 60 * 1000; // 5分钟
        
        // LRU配置
        public boolean enableLRU = true;
        
        // 预热配置
        public boolean enableWarmup = true;
        
        // 清理配置
        public boolean enableAutoCleanup = true;
        public long cleanupIntervalMillis = 60 * 1000; // 1分钟
    }
}