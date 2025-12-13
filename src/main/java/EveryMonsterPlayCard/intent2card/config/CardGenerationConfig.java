package EveryMonsterPlayCard.intent2card.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 卡牌生成配置
 * 管理卡牌生成的各种配置参数
 */
public class CardGenerationConfig {
    
    // 单例实例
    private static CardGenerationConfig instance;
    
    // 基础配置
    private boolean enableGeneration = true;
    private boolean enableFallbackGeneration = true;
    private boolean enableDynamicGeneration = true;
    
    // 数值配置
    private int minDamage = 1;
    private int maxDamage = 50;
    private int minBlock = 1;
    private int maxBlock = 30;
    private int minMagicNumber = 1;
    private int maxMagicNumber = 20;
    
    // 费用配置
    private int minCost = 0;
    private int maxCost = 3;
    private double costEfficiencyRatio = 0.8;
    
    // 稀有度配置
    private double commonRarityChance = 0.6;
    private double uncommonRarityChance = 0.3;
    private double rareRarityChance = 0.1;
    
    // 类型配置
    private Map<String, Double> typeChances = new HashMap<>();
    
    // 特殊效果配置
    private boolean enableSpecialEffects = true;
    private double specialEffectChance = 0.2;
    private Map<String, Double> specialEffectChances = new HashMap<>();
    
    // 复合意图配置
    private boolean enableCompositeIntents = true;
    private double compositeIntentChance = 0.15;
    private int maxCompositeEffects = 3;
    
    // 平衡性配置
    private boolean enableBalanceCheck = true;
    private double minBalanceRatio = 0.7;
    private double maxBalanceRatio = 1.3;
    
    // 验证配置
    private boolean enableValidation = true;
    private boolean enableAutoRepair = true;
    private int maxRepairAttempts = 3;
    
    // 优化配置
    private boolean enableOptimization = true;
    private double optimizationStrength = 1.0;
    
    // 缓存配置
    private boolean enableCaching = true;
    private int maxCacheSize = 100;
    private long cacheTTL = 5 * 60 * 1000; // 5分钟
    
    private CardGenerationConfig() {
        initializeDefaults();
    }
    
    /**
     * 获取配置实例
     */
    public static synchronized CardGenerationConfig getInstance() {
        if (instance == null) {
            instance = new CardGenerationConfig();
        }
        return instance;
    }
    
    /**
     * 初始化默认值
     */
    private void initializeDefaults() {
        // 初始化类型概率
        typeChances.put("ATTACK", 0.4);
        typeChances.put("SKILL", 0.35);
        typeChances.put("POWER", 0.2);
        typeChances.put("CURSE", 0.05);
        
        // 初始化特殊效果概率
        specialEffectChances.put("DRAW", 0.3);
        specialEffectChances.put("EXHAUST", 0.2);
        specialEffectChances.put("RETAIN", 0.15);
        specialEffectChances.put("ETHREAL", 0.1);
        specialEffectChances.put("INNATE", 0.1);
        specialEffectChances.put("TEMPORARY", 0.15);
    }
    
    // Getter和Setter方法
    
    public boolean isEnableGeneration() {
        return enableGeneration;
    }
    
    public void setEnableGeneration(boolean enableGeneration) {
        this.enableGeneration = enableGeneration;
    }
    
    public boolean isEnableFallbackGeneration() {
        return enableFallbackGeneration;
    }
    
    public void setEnableFallbackGeneration(boolean enableFallbackGeneration) {
        this.enableFallbackGeneration = enableFallbackGeneration;
    }
    
    public boolean isEnableDynamicGeneration() {
        return enableDynamicGeneration;
    }
    
    public void setEnableDynamicGeneration(boolean enableDynamicGeneration) {
        this.enableDynamicGeneration = enableDynamicGeneration;
    }
    
    public int getMinDamage() {
        return minDamage;
    }
    
    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }
    
    public int getMaxDamage() {
        return maxDamage;
    }
    
    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }
    
    public int getMinBlock() {
        return minBlock;
    }
    
    public void setMinBlock(int minBlock) {
        this.minBlock = minBlock;
    }
    
    public int getMaxBlock() {
        return maxBlock;
    }
    
    public void setMaxBlock(int maxBlock) {
        this.maxBlock = maxBlock;
    }
    
    public int getMinMagicNumber() {
        return minMagicNumber;
    }
    
    public void setMinMagicNumber(int minMagicNumber) {
        this.minMagicNumber = minMagicNumber;
    }
    
    public int getMaxMagicNumber() {
        return maxMagicNumber;
    }
    
    public void setMaxMagicNumber(int maxMagicNumber) {
        this.maxMagicNumber = maxMagicNumber;
    }
    
    public int getMinCost() {
        return minCost;
    }
    
    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }
    
    public int getMaxCost() {
        return maxCost;
    }
    
    public void setMaxCost(int maxCost) {
        this.maxCost = maxCost;
    }
    
    public double getCostEfficiencyRatio() {
        return costEfficiencyRatio;
    }
    
    public void setCostEfficiencyRatio(double costEfficiencyRatio) {
        this.costEfficiencyRatio = costEfficiencyRatio;
    }
    
    public double getCommonRarityChance() {
        return commonRarityChance;
    }
    
    public void setCommonRarityChance(double commonRarityChance) {
        this.commonRarityChance = commonRarityChance;
    }
    
    public double getUncommonRarityChance() {
        return uncommonRarityChance;
    }
    
    public void setUncommonRarityChance(double uncommonRarityChance) {
        this.uncommonRarityChance = uncommonRarityChance;
    }
    
    public double getRareRarityChance() {
        return rareRarityChance;
    }
    
    public void setRareRarityChance(double rareRarityChance) {
        this.rareRarityChance = rareRarityChance;
    }
    
    public Map<String, Double> getTypeChances() {
        return new HashMap<>(typeChances);
    }
    
    public void setTypeChances(Map<String, Double> typeChances) {
        this.typeChances = new HashMap<>(typeChances);
    }
    
    public boolean isEnableSpecialEffects() {
        return enableSpecialEffects;
    }
    
    public void setEnableSpecialEffects(boolean enableSpecialEffects) {
        this.enableSpecialEffects = enableSpecialEffects;
    }
    
    public double getSpecialEffectChance() {
        return specialEffectChance;
    }
    
    public void setSpecialEffectChance(double specialEffectChance) {
        this.specialEffectChance = specialEffectChance;
    }
    
    public Map<String, Double> getSpecialEffectChances() {
        return new HashMap<>(specialEffectChances);
    }
    
    public void setSpecialEffectChances(Map<String, Double> specialEffectChances) {
        this.specialEffectChances = new HashMap<>(specialEffectChances);
    }
    
    public boolean isEnableCompositeIntents() {
        return enableCompositeIntents;
    }
    
    public void setEnableCompositeIntents(boolean enableCompositeIntents) {
        this.enableCompositeIntents = enableCompositeIntents;
    }
    
    public double getCompositeIntentChance() {
        return compositeIntentChance;
    }
    
    public void setCompositeIntentChance(double compositeIntentChance) {
        this.compositeIntentChance = compositeIntentChance;
    }
    
    public int getMaxCompositeEffects() {
        return maxCompositeEffects;
    }
    
    public void setMaxCompositeEffects(int maxCompositeEffects) {
        this.maxCompositeEffects = maxCompositeEffects;
    }
    
    public boolean isEnableBalanceCheck() {
        return enableBalanceCheck;
    }
    
    public void setEnableBalanceCheck(boolean enableBalanceCheck) {
        this.enableBalanceCheck = enableBalanceCheck;
    }
    
    public double getMinBalanceRatio() {
        return minBalanceRatio;
    }
    
    public void setMinBalanceRatio(double minBalanceRatio) {
        this.minBalanceRatio = minBalanceRatio;
    }
    
    public double getMaxBalanceRatio() {
        return maxBalanceRatio;
    }
    
    public void setMaxBalanceRatio(double maxBalanceRatio) {
        this.maxBalanceRatio = maxBalanceRatio;
    }
    
    public boolean isEnableValidation() {
        return enableValidation;
    }
    
    public void setEnableValidation(boolean enableValidation) {
        this.enableValidation = enableValidation;
    }
    
    public boolean isEnableAutoRepair() {
        return enableAutoRepair;
    }
    
    public void setEnableAutoRepair(boolean enableAutoRepair) {
        this.enableAutoRepair = enableAutoRepair;
    }
    
    public int getMaxRepairAttempts() {
        return maxRepairAttempts;
    }
    
    public void setMaxRepairAttempts(int maxRepairAttempts) {
        this.maxRepairAttempts = maxRepairAttempts;
    }
    
    public boolean isEnableOptimization() {
        return enableOptimization;
    }
    
    public void setEnableOptimization(boolean enableOptimization) {
        this.enableOptimization = enableOptimization;
    }
    
    public double getOptimizationStrength() {
        return optimizationStrength;
    }
    
    public void setOptimizationStrength(double optimizationStrength) {
        this.optimizationStrength = optimizationStrength;
    }
    
    public boolean isEnableCaching() {
        return enableCaching;
    }
    
    public void setEnableCaching(boolean enableCaching) {
        this.enableCaching = enableCaching;
    }
    
    public int getMaxCacheSize() {
        return maxCacheSize;
    }
    
    public void setMaxCacheSize(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }
    
    public long getCacheTTL() {
        return cacheTTL;
    }
    
    public void setCacheTTL(long cacheTTL) {
        this.cacheTTL = cacheTTL;
    }
    
    /**
     * 重置为默认配置
     */
    public void resetToDefaults() {
        instance = new CardGenerationConfig();
    }
    
    /**
     * 验证配置
     */
    public boolean validateConfig() {
        // 验证数值范围
        if (minDamage < 0 || maxDamage < minDamage) {
            return false;
        }
        
        if (minBlock < 0 || maxBlock < minBlock) {
            return false;
        }
        
        if (minMagicNumber < 0 || maxMagicNumber < minMagicNumber) {
            return false;
        }
        
        if (minCost < -1 || maxCost < minCost || maxCost > 3) {
            return false;
        }
        
        // 验证概率
        if (commonRarityChance < 0 || uncommonRarityChance < 0 || rareRarityChance < 0) {
            return false;
        }
        
        double totalRarityChance = commonRarityChance + uncommonRarityChance + rareRarityChance;
        if (Math.abs(totalRarityChance - 1.0) > 0.01) {
            return false;
        }
        
        if (specialEffectChance < 0 || specialEffectChance > 1) {
            return false;
        }
        
        if (compositeIntentChance < 0 || compositeIntentChance > 1) {
            return false;
        }
        
        // 验证平衡比例
        if (minBalanceRatio < 0 || maxBalanceRatio < minBalanceRatio) {
            return false;
        }
        
        // 验证缓存配置
        if (maxCacheSize < 0 || cacheTTL < 0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 获取配置摘要
     */
    public Map<String, Object> getConfigSummary() {
        Map<String, Object> summary = new HashMap<>();
        
        summary.put("enableGeneration", enableGeneration);
        summary.put("enableFallbackGeneration", enableFallbackGeneration);
        summary.put("enableDynamicGeneration", enableDynamicGeneration);
        
        summary.put("damageRange", minDamage + "-" + maxDamage);
        summary.put("blockRange", minBlock + "-" + maxBlock);
        summary.put("magicNumberRange", minMagicNumber + "-" + maxMagicNumber);
        summary.put("costRange", minCost + "-" + maxCost);
        
        summary.put("rarityChances", Map.of(
            "COMMON", commonRarityChance,
            "UNCOMMON", uncommonRarityChance,
            "RARE", rareRarityChance
        ));
        
        summary.put("enableSpecialEffects", enableSpecialEffects);
        summary.put("specialEffectChance", specialEffectChance);
        
        summary.put("enableCompositeIntents", enableCompositeIntents);
        summary.put("compositeIntentChance", compositeIntentChance);
        summary.put("maxCompositeEffects", maxCompositeEffects);
        
        summary.put("enableBalanceCheck", enableBalanceCheck);
        summary.put("balanceRatio", minBalanceRatio + "-" + maxBalanceRatio);
        
        summary.put("enableValidation", enableValidation);
        summary.put("enableAutoRepair", enableAutoRepair);
        summary.put("maxRepairAttempts", maxRepairAttempts);
        
        summary.put("enableOptimization", enableOptimization);
        summary.put("optimizationStrength", optimizationStrength);
        
        summary.put("enableCaching", enableCaching);
        summary.put("maxCacheSize", maxCacheSize);
        summary.put("cacheTTL", cacheTTL + "ms");
        
        return summary;
    }
}