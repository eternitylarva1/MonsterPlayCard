package EveryMonsterPlayCard.intent2card.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 映射规则配置
 * 管理意图到卡牌映射的各种规则配置
 */
public class MappingRulesConfig {
    
    // 单例实例
    private static MappingRulesConfig instance;
    
    // 基础配置
    private boolean enableMapping = true;
    private boolean enablePriorityMapping = true;
    private boolean enableComplexityMapping = true;
    private boolean enableSpecialMapping = true;
    
    // 意图类型映射配置
    private Map<String, String> intentTypeMappings = new HashMap<>();
    private Map<String, Double> intentTypeWeights = new HashMap<>();
    
    // 复合意图映射配置
    private boolean enableCompositeMapping = true;
    private double compositeMappingThreshold = 0.7;
    private Map<String, String> compositeTypeMappings = new HashMap<>();
    
    // 特殊意图映射配置
    private Map<String, String> specialIntentMappings = new HashMap<>();
    private List<String> highPriorityIntents = new ArrayList<>();
    private List<String> lowPriorityIntents = new ArrayList<>();
    
    // 数值映射配置
    private boolean enableValueMapping = true;
    private double damageMappingRatio = 1.0;
    private double blockMappingRatio = 1.0;
    private double magicNumberMappingRatio = 1.0;
    
    // 费用映射配置
    private boolean enableCostMapping = true;
    private Map<String, Integer> baseCostMappings = new HashMap<>();
    private double costComplexityFactor = 0.5;
    
    // 稀有度映射配置
    private boolean enableRarityMapping = true;
    private Map<String, String> rarityMappings = new HashMap<>();
    private double complexityRarityFactor = 0.3;
    
    // 目标映射配置
    private boolean enableTargetMapping = true;
    private Map<String, String> targetMappings = new HashMap<>();
    
    // 模板选择配置
    private boolean enableTemplateSelection = true;
    private double templateMatchThreshold = 0.6;
    private Map<String, Double> templateWeights = new HashMap<>();
    
    // 回退配置
    private boolean enableFallback = true;
    private String fallbackTemplate = "自定义卡牌模板";
    private int maxFallbackAttempts = 3;
    
    private MappingRulesConfig() {
        initializeDefaults();
    }
    
    /**
     * 获取配置实例
     */
    public static synchronized MappingRulesConfig getInstance() {
        if (instance == null) {
            instance = new MappingRulesConfig();
        }
        return instance;
    }
    
    /**
     * 初始化默认值
     */
    private void initializeDefaults() {
        // 初始化意图类型映射
        intentTypeMappings.put("ATTACK", "攻击卡牌模板");
        intentTypeMappings.put("STRONG", "攻击卡牌模板");
        intentTypeMappings.put("DEFEND", "技能卡牌模板");
        intentTypeMappings.put("BUFF", "能力卡牌模板");
        intentTypeMappings.put("DEBUFF", "能力卡牌模板");
        intentTypeMappings.put("ESCAPE", "技能卡牌模板");
        intentTypeMappings.put("UNKNOWN", "自定义卡牌模板");
        
        // 初始化意图类型权重
        intentTypeWeights.put("ATTACK", 1.0);
        intentTypeWeights.put("STRONG", 1.2);
        intentTypeWeights.put("DEFEND", 0.8);
        intentTypeWeights.put("BUFF", 1.1);
        intentTypeWeights.put("DEBUFF", 1.1);
        intentTypeWeights.put("ESCAPE", 0.5);
        intentTypeWeights.put("UNKNOWN", 0.7);
        
        // 初始化复合意图映射
        compositeTypeMappings.put("ATTACK_DEFEND", "自定义卡牌模板");
        compositeTypeMappings.put("ATTACK_BUFF", "自定义卡牌模板");
        compositeTypeMappings.put("ATTACK_DEBUFF", "自定义卡牌模板");
        compositeTypeMappings.put("DEFEND_BUFF", "自定义卡牌模板");
        compositeTypeMappings.put("DEFEND_ATTACK", "自定义卡牌模板");
        compositeTypeMappings.put("MIXED", "自定义卡牌模板");
        
        // 初始化特殊意图映射
        specialIntentMappings.put("MULTI_HIT", "攻击卡牌模板");
        specialIntentMappings.put("AREA_ATTACK", "攻击卡牌模板");
        specialIntentMappings.put("SELF_DAMAGE", "技能卡牌模板");
        specialIntentMappings.put("RANDOM_TARGET", "攻击卡牌模板");
        
        // 初始化高优先级意图
        highPriorityIntents.add("STRONG");
        highPriorityIntents.add("MULTI_HIT");
        highPriorityIntents.add("AREA_ATTACK");
        
        // 初始化低优先级意图
        lowPriorityIntents.add("ESCAPE");
        lowPriorityIntents.add("UNKNOWN");
        
        // 初始化基础费用映射
        baseCostMappings.put("ATTACK", 1);
        baseCostMappings.put("STRONG", 2);
        baseCostMappings.put("DEFEND", 1);
        baseCostMappings.put("BUFF", 1);
        baseCostMappings.put("DEBUFF", 1);
        baseCostMappings.put("ESCAPE", 0);
        baseCostMappings.put("UNKNOWN", 1);
        
        // 初始化稀有度映射
        rarityMappings.put("COMMON", "COMMON");
        rarityMappings.put("UNCOMMON", "UNCOMMON");
        rarityMappings.put("RARE", "RARE");
        rarityMappings.put("SPECIAL", "SPECIAL");
        rarityMappings.put("BASIC", "BASIC");
        
        // 初始化目标映射
        targetMappings.put("ATTACK", "ENEMY");
        targetMappings.put("STRONG", "ENEMY");
        targetMappings.put("DEFEND", "SELF");
        targetMappings.put("BUFF", "SELF");
        targetMappings.put("DEBUFF", "ENEMY");
        targetMappings.put("ESCAPE", "NONE");
        targetMappings.put("UNKNOWN", "ENEMY");
        
        // 初始化模板权重
        templateWeights.put("攻击卡牌模板", 1.0);
        templateWeights.put("技能卡牌模板", 1.0);
        templateWeights.put("能力卡牌模板", 1.0);
        templateWeights.put("标准卡牌模板", 0.8);
        templateWeights.put("自定义卡牌模板", 0.6);
    }
    
    // Getter和Setter方法
    
    public boolean isEnableMapping() {
        return enableMapping;
    }
    
    public void setEnableMapping(boolean enableMapping) {
        this.enableMapping = enableMapping;
    }
    
    public boolean isEnablePriorityMapping() {
        return enablePriorityMapping;
    }
    
    public void setEnablePriorityMapping(boolean enablePriorityMapping) {
        this.enablePriorityMapping = enablePriorityMapping;
    }
    
    public boolean isEnableComplexityMapping() {
        return enableComplexityMapping;
    }
    
    public void setEnableComplexityMapping(boolean enableComplexityMapping) {
        this.enableComplexityMapping = enableComplexityMapping;
    }
    
    public boolean isEnableSpecialMapping() {
        return enableSpecialMapping;
    }
    
    public void setEnableSpecialMapping(boolean enableSpecialMapping) {
        this.enableSpecialMapping = enableSpecialMapping;
    }
    
    public Map<String, String> getIntentTypeMappings() {
        return new HashMap<>(intentTypeMappings);
    }
    
    public void setIntentTypeMappings(Map<String, String> intentTypeMappings) {
        this.intentTypeMappings = new HashMap<>(intentTypeMappings);
    }
    
    public Map<String, Double> getIntentTypeWeights() {
        return new HashMap<>(intentTypeWeights);
    }
    
    public void setIntentTypeWeights(Map<String, Double> intentTypeWeights) {
        this.intentTypeWeights = new HashMap<>(intentTypeWeights);
    }
    
    public boolean isEnableCompositeMapping() {
        return enableCompositeMapping;
    }
    
    public void setEnableCompositeMapping(boolean enableCompositeMapping) {
        this.enableCompositeMapping = enableCompositeMapping;
    }
    
    public double getCompositeMappingThreshold() {
        return compositeMappingThreshold;
    }
    
    public void setCompositeMappingThreshold(double compositeMappingThreshold) {
        this.compositeMappingThreshold = compositeMappingThreshold;
    }
    
    public Map<String, String> getCompositeTypeMappings() {
        return new HashMap<>(compositeTypeMappings);
    }
    
    public void setCompositeTypeMappings(Map<String, String> compositeTypeMappings) {
        this.compositeTypeMappings = new HashMap<>(compositeTypeMappings);
    }
    
    public Map<String, String> getSpecialIntentMappings() {
        return new HashMap<>(specialIntentMappings);
    }
    
    public void setSpecialIntentMappings(Map<String, String> specialIntentMappings) {
        this.specialIntentMappings = new HashMap<>(specialIntentMappings);
    }
    
    public List<String> getHighPriorityIntents() {
        return new ArrayList<>(highPriorityIntents);
    }
    
    public void setHighPriorityIntents(List<String> highPriorityIntents) {
        this.highPriorityIntents = new ArrayList<>(highPriorityIntents);
    }
    
    public List<String> getLowPriorityIntents() {
        return new ArrayList<>(lowPriorityIntents);
    }
    
    public void setLowPriorityIntents(List<String> lowPriorityIntents) {
        this.lowPriorityIntents = new ArrayList<>(lowPriorityIntents);
    }
    
    public boolean isEnableValueMapping() {
        return enableValueMapping;
    }
    
    public void setEnableValueMapping(boolean enableValueMapping) {
        this.enableValueMapping = enableValueMapping;
    }
    
    public double getDamageMappingRatio() {
        return damageMappingRatio;
    }
    
    public void setDamageMappingRatio(double damageMappingRatio) {
        this.damageMappingRatio = damageMappingRatio;
    }
    
    public double getBlockMappingRatio() {
        return blockMappingRatio;
    }
    
    public void setBlockMappingRatio(double blockMappingRatio) {
        this.blockMappingRatio = blockMappingRatio;
    }
    
    public double getMagicNumberMappingRatio() {
        return magicNumberMappingRatio;
    }
    
    public void setMagicNumberMappingRatio(double magicNumberMappingRatio) {
        this.magicNumberMappingRatio = magicNumberMappingRatio;
    }
    
    public boolean isEnableCostMapping() {
        return enableCostMapping;
    }
    
    public void setEnableCostMapping(boolean enableCostMapping) {
        this.enableCostMapping = enableCostMapping;
    }
    
    public Map<String, Integer> getBaseCostMappings() {
        return new HashMap<>(baseCostMappings);
    }
    
    public void setBaseCostMappings(Map<String, Integer> baseCostMappings) {
        this.baseCostMappings = new HashMap<>(baseCostMappings);
    }
    
    public double getCostComplexityFactor() {
        return costComplexityFactor;
    }
    
    public void setCostComplexityFactor(double costComplexityFactor) {
        this.costComplexityFactor = costComplexityFactor;
    }
    
    public boolean isEnableRarityMapping() {
        return enableRarityMapping;
    }
    
    public void setEnableRarityMapping(boolean enableRarityMapping) {
        this.enableRarityMapping = enableRarityMapping;
    }
    
    public Map<String, String> getRarityMappings() {
        return new HashMap<>(rarityMappings);
    }
    
    public void setRarityMappings(Map<String, String> rarityMappings) {
        this.rarityMappings = new HashMap<>(rarityMappings);
    }
    
    public double getComplexityRarityFactor() {
        return complexityRarityFactor;
    }
    
    public void setComplexityRarityFactor(double complexityRarityFactor) {
        this.complexityRarityFactor = complexityRarityFactor;
    }
    
    public boolean isEnableTargetMapping() {
        return enableTargetMapping;
    }
    
    public void setEnableTargetMapping(boolean enableTargetMapping) {
        this.enableTargetMapping = enableTargetMapping;
    }
    
    public Map<String, String> getTargetMappings() {
        return new HashMap<>(targetMappings);
    }
    
    public void setTargetMappings(Map<String, String> targetMappings) {
        this.targetMappings = new HashMap<>(targetMappings);
    }
    
    public boolean isEnableTemplateSelection() {
        return enableTemplateSelection;
    }
    
    public void setEnableTemplateSelection(boolean enableTemplateSelection) {
        this.enableTemplateSelection = enableTemplateSelection;
    }
    
    public double getTemplateMatchThreshold() {
        return templateMatchThreshold;
    }
    
    public void setTemplateMatchThreshold(double templateMatchThreshold) {
        this.templateMatchThreshold = templateMatchThreshold;
    }
    
    public Map<String, Double> getTemplateWeights() {
        return new HashMap<>(templateWeights);
    }
    
    public void setTemplateWeights(Map<String, Double> templateWeights) {
        this.templateWeights = new HashMap<>(templateWeights);
    }
    
    public boolean isEnableFallback() {
        return enableFallback;
    }
    
    public void setEnableFallback(boolean enableFallback) {
        this.enableFallback = enableFallback;
    }
    
    public String getFallbackTemplate() {
        return fallbackTemplate;
    }
    
    public void setFallbackTemplate(String fallbackTemplate) {
        this.fallbackTemplate = fallbackTemplate;
    }
    
    public int getMaxFallbackAttempts() {
        return maxFallbackAttempts;
    }
    
    public void setMaxFallbackAttempts(int maxFallbackAttempts) {
        this.maxFallbackAttempts = maxFallbackAttempts;
    }
    
    /**
     * 重置为默认配置
     */
    public void resetToDefaults() {
        instance = new MappingRulesConfig();
    }
    
    /**
     * 验证配置
     */
    public boolean validateConfig() {
        // 验证映射比例
        if (damageMappingRatio < 0 || blockMappingRatio < 0 || magicNumberMappingRatio < 0) {
            return false;
        }
        
        // 验证阈值
        if (compositeMappingThreshold < 0 || compositeMappingThreshold > 1) {
            return false;
        }
        
        if (templateMatchThreshold < 0 || templateMatchThreshold > 1) {
            return false;
        }
        
        // 验证因子
        if (costComplexityFactor < 0 || complexityRarityFactor < 0) {
            return false;
        }
        
        // 验证回退配置
        if (maxFallbackAttempts < 0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 获取配置摘要
     */
    public Map<String, Object> getConfigSummary() {
        Map<String, Object> summary = new HashMap<>();
        
        summary.put("enableMapping", enableMapping);
        summary.put("enablePriorityMapping", enablePriorityMapping);
        summary.put("enableComplexityMapping", enableComplexityMapping);
        summary.put("enableSpecialMapping", enableSpecialMapping);
        
        summary.put("intentTypeMappings", intentTypeMappings);
        summary.put("intentTypeWeights", intentTypeWeights);
        
        summary.put("enableCompositeMapping", enableCompositeMapping);
        summary.put("compositeMappingThreshold", compositeMappingThreshold);
        summary.put("compositeTypeMappings", compositeTypeMappings);
        
        summary.put("specialIntentMappings", specialIntentMappings);
        summary.put("highPriorityIntents", highPriorityIntents);
        summary.put("lowPriorityIntents", lowPriorityIntents);
        
        summary.put("enableValueMapping", enableValueMapping);
        summary.put("damageMappingRatio", damageMappingRatio);
        summary.put("blockMappingRatio", blockMappingRatio);
        summary.put("magicNumberMappingRatio", magicNumberMappingRatio);
        
        summary.put("enableCostMapping", enableCostMapping);
        summary.put("baseCostMappings", baseCostMappings);
        summary.put("costComplexityFactor", costComplexityFactor);
        
        summary.put("enableRarityMapping", enableRarityMapping);
        summary.put("rarityMappings", rarityMappings);
        summary.put("complexityRarityFactor", complexityRarityFactor);
        
        summary.put("enableTargetMapping", enableTargetMapping);
        summary.put("targetMappings", targetMappings);
        
        summary.put("enableTemplateSelection", enableTemplateSelection);
        summary.put("templateMatchThreshold", templateMatchThreshold);
        summary.put("templateWeights", templateWeights);
        
        summary.put("enableFallback", enableFallback);
        summary.put("fallbackTemplate", fallbackTemplate);
        summary.put("maxFallbackAttempts", maxFallbackAttempts);
        
        return summary;
    }
}