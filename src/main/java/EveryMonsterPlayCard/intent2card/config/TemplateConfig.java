package EveryMonsterPlayCard.intent2card.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板配置
 * 管理卡牌模板的各种配置参数
 */
public class TemplateConfig {
    
    // 单例实例
    private static TemplateConfig instance;
    
    // 基础配置
    private boolean enableTemplates = true;
    private boolean enableTemplateSelection = true;
    private boolean enableTemplateCaching = true;
    
    // 模板优先级配置
    private Map<String, Integer> templatePriorities = new HashMap<>();
    private Map<String, Double> templateWeights = new HashMap<>();
    
    // 模板匹配配置
    private double templateMatchThreshold = 0.6;
    private boolean enableFuzzyMatching = true;
    private double fuzzyMatchThreshold = 0.4;
    
    // 意图类型到模板的映射
    private Map<String, List<String>> intentTypeToTemplates = new HashMap<>();
    
    // 复合意图模板配置
    private Map<String, String> compositeIntentTemplates = new HashMap<>();
    private boolean enableCompositeTemplates = true;
    
    // 特殊意图模板配置
    private Map<String, String> specialIntentTemplates = new HashMap<>();
    private boolean enableSpecialTemplates = true;
    
    // 模板参数配置
    private Map<String, Map<String, Object>> templateParameters = new HashMap<>();
    private boolean enableParameterOverride = true;
    
    // 模板继承配置
    private Map<String, String> templateInheritance = new HashMap<>();
    private boolean enableTemplateInheritance = true;
    
    // 模板扩展配置
    private List<String> customTemplateClasses = new ArrayList<>();
    private boolean enableCustomTemplates = true;
    
    // 模板验证配置
    private boolean enableTemplateValidation = true;
    private boolean enableAutoTemplateRepair = true;
    
    // 模板优化配置
    private boolean enableTemplateOptimization = true;
    private double templateOptimizationStrength = 0.5;
    
    // 默认模板配置
    private String defaultAttackTemplate = "攻击卡牌模板";
    private String defaultSkillTemplate = "技能卡牌模板";
    private String defaultPowerTemplate = "能力卡牌模板";
    private String defaultTemplate = "标准卡牌模板";
    
    private TemplateConfig() {
        initializeDefaults();
    }
    
    /**
     * 获取配置实例
     */
    public static synchronized TemplateConfig getInstance() {
        if (instance == null) {
            instance = new TemplateConfig();
        }
        return instance;
    }
    
    /**
     * 初始化默认值
     */
    private void initializeDefaults() {
        // 初始化模板优先级
        templatePriorities.put("攻击卡牌模板", 1);
        templatePriorities.put("技能卡牌模板", 1);
        templatePriorities.put("能力卡牌模板", 1);
        templatePriorities.put("标准卡牌模板", 2);
        templatePriorities.put("自定义卡牌模板", 3);
        
        // 初始化模板权重
        templateWeights.put("攻击卡牌模板", 1.0);
        templateWeights.put("技能卡牌模板", 1.0);
        templateWeights.put("能力卡牌模板", 1.0);
        templateWeights.put("标准卡牌模板", 0.8);
        templateWeights.put("自定义卡牌模板", 0.6);
        
        // 初始化意图类型到模板的映射
        List<String> attackTemplates = new ArrayList<>();
        attackTemplates.add("攻击卡牌模板");
        attackTemplates.add("标准卡牌模板");
        attackTemplates.add("自定义卡牌模板");
        intentTypeToTemplates.put("ATTACK", attackTemplates);
        
        List<String> strongTemplates = new ArrayList<>();
        strongTemplates.add("攻击卡牌模板");
        strongTemplates.add("标准卡牌模板");
        strongTemplates.add("自定义卡牌模板");
        intentTypeToTemplates.put("STRONG", strongTemplates);
        
        List<String> defendTemplates = new ArrayList<>();
        defendTemplates.add("技能卡牌模板");
        defendTemplates.add("标准卡牌模板");
        defendTemplates.add("自定义卡牌模板");
        intentTypeToTemplates.put("DEFEND", defendTemplates);
        
        List<String> buffTemplates = new ArrayList<>();
        buffTemplates.add("能力卡牌模板");
        buffTemplates.add("技能卡牌模板");
        buffTemplates.add("标准卡牌模板");
        buffTemplates.add("自定义卡牌模板");
        intentTypeToTemplates.put("BUFF", buffTemplates);
        
        List<String> debuffTemplates = new ArrayList<>();
        debuffTemplates.add("能力卡牌模板");
        debuffTemplates.add("技能卡牌模板");
        debuffTemplates.add("标准卡牌模板");
        debuffTemplates.add("自定义卡牌模板");
        intentTypeToTemplates.put("DEBUFF", debuffTemplates);
        
        List<String> escapeTemplates = new ArrayList<>();
        escapeTemplates.add("技能卡牌模板");
        escapeTemplates.add("标准卡牌模板");
        escapeTemplates.add("自定义卡牌模板");
        intentTypeToTemplates.put("ESCAPE", escapeTemplates);
        
        List<String> unknownTemplates = new ArrayList<>();
        unknownTemplates.add("标准卡牌模板");
        unknownTemplates.add("自定义卡牌模板");
        intentTypeToTemplates.put("UNKNOWN", unknownTemplates);
        
        // 初始化复合意图模板
        compositeIntentTemplates.put("ATTACK_DEFEND", "自定义卡牌模板");
        compositeIntentTemplates.put("ATTACK_BUFF", "自定义卡牌模板");
        compositeIntentTemplates.put("ATTACK_DEBUFF", "自定义卡牌模板");
        compositeIntentTemplates.put("DEFEND_BUFF", "自定义卡牌模板");
        compositeIntentTemplates.put("DEFEND_ATTACK", "自定义卡牌模板");
        compositeIntentTemplates.put("MIXED", "自定义卡牌模板");
        
        // 初始化特殊意图模板
        specialIntentTemplates.put("MULTI_HIT", "攻击卡牌模板");
        specialIntentTemplates.put("AREA_ATTACK", "攻击卡牌模板");
        specialIntentTemplates.put("SELF_DAMAGE", "技能卡牌模板");
        specialIntentTemplates.put("RANDOM_TARGET", "攻击卡牌模板");
        
        // 初始化模板参数
        Map<String, Object> attackTemplateParams = new HashMap<>();
        attackTemplateParams.put("baseDamage", 6);
        attackTemplateParams.put("damageScaling", 1.2);
        attackTemplateParams.put("baseCost", 1);
        templateParameters.put("攻击卡牌模板", attackTemplateParams);
        
        Map<String, Object> skillTemplateParams = new HashMap<>();
        skillTemplateParams.put("baseBlock", 5);
        skillTemplateParams.put("blockScaling", 1.0);
        skillTemplateParams.put("baseCost", 1);
        templateParameters.put("技能卡牌模板", skillTemplateParams);
        
        Map<String, Object> powerTemplateParams = new HashMap<>();
        powerTemplateParams.put("baseMagicNumber", 2);
        powerTemplateParams.put("magicScaling", 1.0);
        powerTemplateParams.put("baseCost", 1);
        templateParameters.put("能力卡牌模板", powerTemplateParams);
        
        Map<String, Object> standardTemplateParams = new HashMap<>();
        standardTemplateParams.put("baseValue", 3);
        standardTemplateParams.put("valueScaling", 1.0);
        standardTemplateParams.put("baseCost", 1);
        templateParameters.put("标准卡牌模板", standardTemplateParams);
        
        Map<String, Object> customTemplateParams = new HashMap<>();
        customTemplateParams.put("baseValue", 4);
        customTemplateParams.put("valueScaling", 1.1);
        customTemplateParams.put("baseCost", 1);
        templateParameters.put("自定义卡牌模板", customTemplateParams);
    }
    
    // Getter和Setter方法
    
    public boolean isEnableTemplates() {
        return enableTemplates;
    }
    
    public void setEnableTemplates(boolean enableTemplates) {
        this.enableTemplates = enableTemplates;
    }
    
    public boolean isEnableTemplateSelection() {
        return enableTemplateSelection;
    }
    
    public void setEnableTemplateSelection(boolean enableTemplateSelection) {
        this.enableTemplateSelection = enableTemplateSelection;
    }
    
    public boolean isEnableTemplateCaching() {
        return enableTemplateCaching;
    }
    
    public void setEnableTemplateCaching(boolean enableTemplateCaching) {
        this.enableTemplateCaching = enableTemplateCaching;
    }
    
    public Map<String, Integer> getTemplatePriorities() {
        return new HashMap<>(templatePriorities);
    }
    
    public void setTemplatePriorities(Map<String, Integer> templatePriorities) {
        this.templatePriorities = new HashMap<>(templatePriorities);
    }
    
    public Map<String, Double> getTemplateWeights() {
        return new HashMap<>(templateWeights);
    }
    
    public void setTemplateWeights(Map<String, Double> templateWeights) {
        this.templateWeights = new HashMap<>(templateWeights);
    }
    
    public double getTemplateMatchThreshold() {
        return templateMatchThreshold;
    }
    
    public void setTemplateMatchThreshold(double templateMatchThreshold) {
        this.templateMatchThreshold = templateMatchThreshold;
    }
    
    public boolean isEnableFuzzyMatching() {
        return enableFuzzyMatching;
    }
    
    public void setEnableFuzzyMatching(boolean enableFuzzyMatching) {
        this.enableFuzzyMatching = enableFuzzyMatching;
    }
    
    public double getFuzzyMatchThreshold() {
        return fuzzyMatchThreshold;
    }
    
    public void setFuzzyMatchThreshold(double fuzzyMatchThreshold) {
        this.fuzzyMatchThreshold = fuzzyMatchThreshold;
    }
    
    public Map<String, List<String>> getIntentTypeToTemplates() {
        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : intentTypeToTemplates.entrySet()) {
            result.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return result;
    }
    
    public void setIntentTypeToTemplates(Map<String, List<String>> intentTypeToTemplates) {
        this.intentTypeToTemplates = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : intentTypeToTemplates.entrySet()) {
            this.intentTypeToTemplates.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
    }
    
    public Map<String, String> getCompositeIntentTemplates() {
        return new HashMap<>(compositeIntentTemplates);
    }
    
    public void setCompositeIntentTemplates(Map<String, String> compositeIntentTemplates) {
        this.compositeIntentTemplates = new HashMap<>(compositeIntentTemplates);
    }
    
    public boolean isEnableCompositeTemplates() {
        return enableCompositeTemplates;
    }
    
    public void setEnableCompositeTemplates(boolean enableCompositeTemplates) {
        this.enableCompositeTemplates = enableCompositeTemplates;
    }
    
    public Map<String, String> getSpecialIntentTemplates() {
        return new HashMap<>(specialIntentTemplates);
    }
    
    public void setSpecialIntentTemplates(Map<String, String> specialIntentTemplates) {
        this.specialIntentTemplates = new HashMap<>(specialIntentTemplates);
    }
    
    public boolean isEnableSpecialTemplates() {
        return enableSpecialTemplates;
    }
    
    public void setEnableSpecialTemplates(boolean enableSpecialTemplates) {
        this.enableSpecialTemplates = enableSpecialTemplates;
    }
    
    public Map<String, Map<String, Object>> getTemplateParameters() {
        Map<String, Map<String, Object>> result = new HashMap<>();
        for (Map.Entry<String, Map<String, Object>> entry : templateParameters.entrySet()) {
            result.put(entry.getKey(), new HashMap<>(entry.getValue()));
        }
        return result;
    }
    
    public void setTemplateParameters(Map<String, Map<String, Object>> templateParameters) {
        this.templateParameters = new HashMap<>();
        for (Map.Entry<String, Map<String, Object>> entry : templateParameters.entrySet()) {
            this.templateParameters.put(entry.getKey(), new HashMap<>(entry.getValue()));
        }
    }
    
    public boolean isEnableParameterOverride() {
        return enableParameterOverride;
    }
    
    public void setEnableParameterOverride(boolean enableParameterOverride) {
        this.enableParameterOverride = enableParameterOverride;
    }
    
    public Map<String, String> getTemplateInheritance() {
        return new HashMap<>(templateInheritance);
    }
    
    public void setTemplateInheritance(Map<String, String> templateInheritance) {
        this.templateInheritance = new HashMap<>(templateInheritance);
    }
    
    public boolean isEnableTemplateInheritance() {
        return enableTemplateInheritance;
    }
    
    public void setEnableTemplateInheritance(boolean enableTemplateInheritance) {
        this.enableTemplateInheritance = enableTemplateInheritance;
    }
    
    public List<String> getCustomTemplateClasses() {
        return new ArrayList<>(customTemplateClasses);
    }
    
    public void setCustomTemplateClasses(List<String> customTemplateClasses) {
        this.customTemplateClasses = new ArrayList<>(customTemplateClasses);
    }
    
    public boolean isEnableCustomTemplates() {
        return enableCustomTemplates;
    }
    
    public void setEnableCustomTemplates(boolean enableCustomTemplates) {
        this.enableCustomTemplates = enableCustomTemplates;
    }
    
    public boolean isEnableTemplateValidation() {
        return enableTemplateValidation;
    }
    
    public void setEnableTemplateValidation(boolean enableTemplateValidation) {
        this.enableTemplateValidation = enableTemplateValidation;
    }
    
    public boolean isEnableAutoTemplateRepair() {
        return enableAutoTemplateRepair;
    }
    
    public void setEnableAutoTemplateRepair(boolean enableAutoTemplateRepair) {
        this.enableAutoTemplateRepair = enableAutoTemplateRepair;
    }
    
    public boolean isEnableTemplateOptimization() {
        return enableTemplateOptimization;
    }
    
    public void setEnableTemplateOptimization(boolean enableTemplateOptimization) {
        this.enableTemplateOptimization = enableTemplateOptimization;
    }
    
    public double getTemplateOptimizationStrength() {
        return templateOptimizationStrength;
    }
    
    public void setTemplateOptimizationStrength(double templateOptimizationStrength) {
        this.templateOptimizationStrength = templateOptimizationStrength;
    }
    
    public String getDefaultAttackTemplate() {
        return defaultAttackTemplate;
    }
    
    public void setDefaultAttackTemplate(String defaultAttackTemplate) {
        this.defaultAttackTemplate = defaultAttackTemplate;
    }
    
    public String getDefaultSkillTemplate() {
        return defaultSkillTemplate;
    }
    
    public void setDefaultSkillTemplate(String defaultSkillTemplate) {
        this.defaultSkillTemplate = defaultSkillTemplate;
    }
    
    public String getDefaultPowerTemplate() {
        return defaultPowerTemplate;
    }
    
    public void setDefaultPowerTemplate(String defaultPowerTemplate) {
        this.defaultPowerTemplate = defaultPowerTemplate;
    }
    
    public String getDefaultTemplate() {
        return defaultTemplate;
    }
    
    public void setDefaultTemplate(String defaultTemplate) {
        this.defaultTemplate = defaultTemplate;
    }
    
    /**
     * 重置为默认配置
     */
    public void resetToDefaults() {
        instance = new TemplateConfig();
    }
    
    /**
     * 验证配置
     */
    public boolean validateConfig() {
        // 验证阈值
        if (templateMatchThreshold < 0 || templateMatchThreshold > 1) {
            return false;
        }
        
        if (fuzzyMatchThreshold < 0 || fuzzyMatchThreshold > 1) {
            return false;
        }
        
        // 验证优化强度
        if (templateOptimizationStrength < 0 || templateOptimizationStrength > 1) {
            return false;
        }
        
        // 验证默认模板
        if (defaultAttackTemplate == null || defaultAttackTemplate.trim().isEmpty()) {
            return false;
        }
        
        if (defaultSkillTemplate == null || defaultSkillTemplate.trim().isEmpty()) {
            return false;
        }
        
        if (defaultPowerTemplate == null || defaultPowerTemplate.trim().isEmpty()) {
            return false;
        }
        
        if (defaultTemplate == null || defaultTemplate.trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 获取配置摘要
     */
    public Map<String, Object> getConfigSummary() {
        Map<String, Object> summary = new HashMap<>();
        
        summary.put("enableTemplates", enableTemplates);
        summary.put("enableTemplateSelection", enableTemplateSelection);
        summary.put("enableTemplateCaching", enableTemplateCaching);
        
        summary.put("templatePriorities", templatePriorities);
        summary.put("templateWeights", templateWeights);
        
        summary.put("templateMatchThreshold", templateMatchThreshold);
        summary.put("enableFuzzyMatching", enableFuzzyMatching);
        summary.put("fuzzyMatchThreshold", fuzzyMatchThreshold);
        
        summary.put("intentTypeToTemplates", intentTypeToTemplates);
        
        summary.put("compositeIntentTemplates", compositeIntentTemplates);
        summary.put("enableCompositeTemplates", enableCompositeTemplates);
        
        summary.put("specialIntentTemplates", specialIntentTemplates);
        summary.put("enableSpecialTemplates", enableSpecialTemplates);
        
        summary.put("templateParameters", templateParameters);
        summary.put("enableParameterOverride", enableParameterOverride);
        
        summary.put("templateInheritance", templateInheritance);
        summary.put("enableTemplateInheritance", enableTemplateInheritance);
        
        summary.put("customTemplateClasses", customTemplateClasses);
        summary.put("enableCustomTemplates", enableCustomTemplates);
        
        summary.put("enableTemplateValidation", enableTemplateValidation);
        summary.put("enableAutoTemplateRepair", enableAutoTemplateRepair);
        
        summary.put("enableTemplateOptimization", enableTemplateOptimization);
        summary.put("templateOptimizationStrength", templateOptimizationStrength);
        
        summary.put("defaultAttackTemplate", defaultAttackTemplate);
        summary.put("defaultSkillTemplate", defaultSkillTemplate);
        summary.put("defaultPowerTemplate", defaultPowerTemplate);
        summary.put("defaultTemplate", defaultTemplate);
        
        return summary;
    }
}