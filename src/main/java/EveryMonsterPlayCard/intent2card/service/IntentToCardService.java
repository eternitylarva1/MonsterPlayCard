package EveryMonsterPlayCard.intent2card.service;

import java.util.HashMap;
import java.util.Map;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.generator.CardGenerator;
import EveryMonsterPlayCard.intent2card.mapper.IntentToCardMapper;

/**
 * 意图到卡牌转换服务
 * 提供统一的接口来处理意图到卡牌的转换
 */
public class IntentToCardService {
    
    private static IntentToCardService instance;
    
    private final IntentToCardMapper mapper;
    private final CardGenerator generator;
    private final CardValidationService validationService;
    private final CardOptimizationService optimizationService;
    private final CardCacheService cacheService;
    
    // 服务配置
    private boolean enableCaching = true;
    private boolean enableValidation = true;
    private boolean enableOptimization = true;
    private boolean enableFallbackGeneration = true;
    
    private IntentToCardService() {
        this.mapper = new IntentToCardMapper();
        this.generator = new CardGenerator();
        this.validationService = CardValidationService.getInstance();
        this.optimizationService = CardOptimizationService.getInstance();
        this.cacheService = CardCacheService.getInstance();
    }
    
    /**
     * 获取服务实例
     */
    public static synchronized IntentToCardService getInstance() {
        if (instance == null) {
            instance = new IntentToCardService();
        }
        return instance;
    }
    
    /**
     * 将意图转换为卡牌
     */
    public AbstractCard convertIntentToCard(MonsterIntent intent) {
        if (intent == null) {
            return null;
        }
        
        // 检查缓存
        if (enableCaching) {
            AbstractCard cachedCard = cacheService.getCachedCard(intent);
            if (cachedCard != null) {
                return cachedCard;
            }
        }
        
        AbstractCard card = null;
        
        try {
            // 尝试使用映射器转换
            card = mapper.mapIntent(intent);
            
            // 如果映射器失败，尝试使用生成器
            if (card == null && enableFallbackGeneration) {
                card = generator.convertIntent(intent);
            }
            
            // 验证卡牌
            if (card != null && enableValidation) {
                if (!validationService.validateCard(card, intent)) {
                    // 验证失败，尝试修复
                    card = validationService.repairCard(card, intent);
                }
            }
            
            // 优化卡牌
            if (card != null && enableOptimization) {
                card = optimizationService.optimizeCard(card, intent);
            }
            
            // 缓存结果
            if (card != null && enableCaching) {
                cacheService.cacheCard(intent, card);
            }
            
        } catch (Exception e) {
            // 记录错误并返回null
            System.err.println("Error converting intent to card: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        
        return card;
    }
    
    /**
     * 批量转换意图到卡牌
     */
    public Map<MonsterIntent, AbstractCard> convertIntentsToCards(Iterable<MonsterIntent> intents) {
        Map<MonsterIntent, AbstractCard> results = new HashMap<>();
        
        for (MonsterIntent intent : intents) {
            AbstractCard card = convertIntentToCard(intent);
            if (card != null) {
                results.put(intent, card);
            }
        }
        
        return results;
    }
    
    /**
     * 预热缓存
     */
    public void warmupCache(Iterable<MonsterIntent> commonIntents) {
        if (!enableCaching) {
            return;
        }
        
        for (MonsterIntent intent : commonIntents) {
            convertIntentToCard(intent);
        }
    }
    
    /**
     * 清空缓存
     */
    public void clearCache() {
        if (enableCaching) {
            cacheService.clearCache();
        }
    }
    
    /**
     * 获取缓存统计信息
     */
    public Map<String, Object> getCacheStats() {
        if (!enableCaching) {
            Map<String, Object> stats = new HashMap<>();
            stats.put("enabled", false);
            return stats;
        }
        
        return cacheService.getStats();
    }
    
    /**
     * 获取验证统计信息
     */
    public Map<String, Object> getValidationStats() {
        if (!enableValidation) {
            Map<String, Object> stats = new HashMap<>();
            stats.put("enabled", false);
            return stats;
        }
        
        return validationService.getStats();
    }
    
    /**
     * 获取优化统计信息
     */
    public Map<String, Object> getOptimizationStats() {
        if (!enableOptimization) {
            Map<String, Object> stats = new HashMap<>();
            stats.put("enabled", false);
            return stats;
        }
        
        return optimizationService.getStats();
    }
    
    /**
     * 配置服务
     */
    public void configureService(ServiceConfig config) {
        this.enableCaching = config.enableCaching;
        this.enableValidation = config.enableValidation;
        this.enableOptimization = config.enableOptimization;
        this.enableFallbackGeneration = config.enableFallbackGeneration;
        
        // 配置子服务
        if (config.cacheConfig != null) {
            cacheService.configure(config.cacheConfig);
        }
        
        if (config.validationConfig != null) {
            validationService.configure(config.validationConfig);
        }
        
        if (config.optimizationConfig != null) {
            optimizationService.configure(config.optimizationConfig);
        }
    }
    
    /**
     * 获取当前配置
     */
    public ServiceConfig getCurrentConfig() {
        ServiceConfig config = new ServiceConfig();
        config.enableCaching = this.enableCaching;
        config.enableValidation = this.enableValidation;
        config.enableOptimization = this.enableOptimization;
        config.enableFallbackGeneration = this.enableFallbackGeneration;
        
        return config;
    }
    
    /**
     * 重置服务到默认状态
     */
    public void resetToDefaults() {
        this.enableCaching = true;
        this.enableValidation = true;
        this.enableOptimization = true;
        this.enableFallbackGeneration = true;
        
        cacheService.resetToDefaults();
        validationService.resetToDefaults();
        optimizationService.resetToDefaults();
    }
    
    /**
     * 获取服务状态
     */
    public Map<String, Object> getServiceStatus() {
        Map<String, Object> status = new HashMap<>();
        
        status.put("cachingEnabled", enableCaching);
        status.put("validationEnabled", enableValidation);
        status.put("optimizationEnabled", enableOptimization);
        status.put("fallbackGenerationEnabled", enableFallbackGeneration);
        
        status.put("cacheStats", getCacheStats());
        status.put("validationStats", getValidationStats());
        status.put("optimizationStats", getOptimizationStats());
        
        return status;
    }
    
    /**
     * 服务配置类
     */
    public static class ServiceConfig {
        public boolean enableCaching = true;
        public boolean enableValidation = true;
        public boolean enableOptimization = true;
        public boolean enableFallbackGeneration = true;
        
        public CardCacheService.CacheConfig cacheConfig;
        public CardValidationService.ValidationConfig validationConfig;
        public CardOptimizationService.OptimizationConfig optimizationConfig;
    }
}