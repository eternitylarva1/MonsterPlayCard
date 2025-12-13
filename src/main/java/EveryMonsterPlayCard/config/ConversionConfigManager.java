package EveryMonsterPlayCard.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 转换配置管理器
 * 负责管理动作-意图转换系统的配置
 */
public class ConversionConfigManager {
    private static final String CONFIG_FILE_NAME = "conversion-config.properties";
    private static final Map<String, ConversionConfigManager> instances = new HashMap<>();
    
    private final String configPath;
    private final Properties properties;
    private final Map<String, Object> cache;
    
    private ConversionConfigManager(String configPath) {
        this.configPath = configPath;
        this.properties = new Properties();
        this.cache = new HashMap<>();
        loadConfiguration();
    }
    
    /**
     * 获取默认配置管理器实例
     * @return 默认配置管理器
     */
    public static ConversionConfigManager getDefault() {
        return getInstance("default");
    }
    
    /**
     * 获取指定路径的配置管理器实例
     * @param configPath 配置路径
     * @return 配置管理器实例
     */
    public static ConversionConfigManager getInstance(String configPath) {
        if (!instances.containsKey(configPath)) {
            instances.put(configPath, new ConversionConfigManager(configPath));
        }
        return instances.get(configPath);
    }
    
    /**
     * 加载配置
     */
    private void loadConfiguration() {
        try (FileInputStream fis = new FileInputStream(configPath)) {
            properties.load(fis);
            
            // 将属性转换为配置对象
            for (String key : properties.stringPropertyNames()) {
                String value = properties.getProperty(key);
                Object configValue = parseConfigValue(key, value);
                cache.put(key, configValue);
            }
            
        } catch (IOException e) {
            // 如果加载失败，使用默认配置
            initializeDefaultConfiguration();
        }
    }
    
    /**
     * 保存配置
     */
    public void saveConfiguration() {
        try (FileOutputStream fos = new FileOutputStream(configPath)) {
            // 将缓存中的配置转换为属性
            for (Map.Entry<String, Object> entry : cache.entrySet()) {
                String value = formatConfigValue(entry.getValue());
                properties.setProperty(entry.getKey(), value);
            }
            
            properties.store(fos, "Action-Intent Conversion Configuration");
            
        } catch (IOException e) {
            // 保存失败，记录错误
            System.err.println("保存配置失败: " + e.getMessage());
        }
    }
    
    /**
     * 初始化默认配置
     */
    private void initializeDefaultConfiguration() {
        // 分析器配置
        cache.put("analyzer.enableDetailedAnalysis", true);
        cache.put("analyzer.maxAnalysisDepth", 5);
        cache.put("analyzer.enableCaching", true);
        
        // 映射器配置
        cache.put("mapper.enableCustomRules", true);
        cache.put("mapper.enablePriorityAdjustment", true);
        cache.put("mapper.enableComplexActionHandling", true);
        
        // 验证器配置
        cache.put("validator.enableStrictValidation", false);
        cache.put("validator.enableAllRules", true);
        cache.put("validator.maxErrorCount", 10);
        
        // 服务配置
        cache.put("service.enableValidation", true);
        cache.put("service.enableStatistics", true);
        cache.put("service.enableLogging", false);
        cache.put("service.maxRetries", 3);
        cache.put("service.timeoutMs", 5000);
        
        // 性能配置
        cache.put("performance.enableOptimization", true);
        cache.put("performance.maxCacheSize", 1000);
        cache.put("performance.enableParallelProcessing", false);
        
        // 调试配置
        cache.put("debug.enableDebugMode", false);
        cache.put("debug.enableDetailedLogging", false);
        cache.put("debug.enablePerformanceMonitoring", false);
    }
    
    /**
     * 解析配置值
     * @param key 配置键
     * @param value 配置值字符串
     * @return 解析后的配置值
     */
    private Object parseConfigValue(String key, String value) {
        if (value == null) {
            return null;
        }
        
        // 根据键名推断类型
        if (key.endsWith(".enable") || key.endsWith(".Enabled")) {
            return Boolean.parseBoolean(value);
        } else if (key.endsWith(".max") || key.endsWith(".Size") || key.endsWith(".Count") || key.endsWith(".Depth") || key.endsWith(".Retries") || key.endsWith(".Ms")) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return 0; // 默认值
            }
        } else {
            return value; // 字符串类型
        }
    }
    
    /**
     * 格式化配置值
     * @param value 配置值
     * @return 格式化后的字符串
     */
    private String formatConfigValue(Object value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }
    
    /**
     * 获取配置值
     * @param key 配置键
     * @return 配置值
     */
    @SuppressWarnings("unchecked")
    public <T> T getConfig(String key) {
        return (T) cache.get(key);
    }
    
    /**
     * 获取配置值，如果不存在则返回默认值
     * @param key 配置键
     * @param defaultValue 默认值
     * @return 配置值
     */
    @SuppressWarnings("unchecked")
    public <T> T getConfig(String key, T defaultValue) {
        if (cache.containsKey(key)) {
            return (T) cache.get(key);
        } else {
            return defaultValue;
        }
    }
    
    /**
     * 设置配置值
     * @param key 配置键
     * @param value 配置值
     */
    public void setConfig(String key, Object value) {
        cache.put(key, value);
    }
    
    /**
     * 检查是否包含配置
     * @param key 配置键
     * @return 是否包含
     */
    public boolean hasConfig(String key) {
        return cache.containsKey(key);
    }
    
    /**
     * 移除配置
     * @param key 配置键
     * @return 被移除的值
     */
    public Object removeConfig(String key) {
        return cache.remove(key);
    }
    
    /**
     * 获取所有配置
     * @return 配置映射
     */
    public Map<String, Object> getAllConfig() {
        return new HashMap<>(cache);
    }
    
    /**
     * 清空所有配置
     */
    public void clearConfig() {
        cache.clear();
    }
    
    /**
     * 重置为默认配置
     */
    public void resetToDefault() {
        clearConfig();
        initializeDefaultConfiguration();
    }
    
    /**
     * 获取分析器配置
     * @return 分析器配置
     */
    public AnalyzerConfig getAnalyzerConfig() {
        return new AnalyzerConfig(
            getConfig("analyzer.enableDetailedAnalysis", true),
            getConfig("analyzer.maxAnalysisDepth", 5),
            getConfig("analyzer.enableCaching", true)
        );
    }
    
    /**
     * 获取映射器配置
     * @return 映射器配置
     */
    public MapperConfig getMapperConfig() {
        return new MapperConfig(
            getConfig("mapper.enableCustomRules", true),
            getConfig("mapper.enablePriorityAdjustment", true),
            getConfig("mapper.enableComplexActionHandling", true)
        );
    }
    
    /**
     * 获取验证器配置
     * @return 验证器配置
     */
    public ValidatorConfig getValidatorConfig() {
        return new ValidatorConfig(
            getConfig("validator.enableStrictValidation", false),
            getConfig("validator.enableAllRules", true),
            getConfig("validator.maxErrorCount", 10)
        );
    }
    
    /**
     * 获取服务配置
     * @return 服务配置
     */
    public ServiceConfig getServiceConfig() {
        return new ServiceConfig(
            getConfig("service.enableValidation", true),
            getConfig("service.enableStatistics", true),
            getConfig("service.enableLogging", false),
            getConfig("service.maxRetries", 3),
            getConfig("service.timeoutMs", 5000)
        );
    }
    
    /**
     * 获取性能配置
     * @return 性能配置
     */
    public PerformanceConfig getPerformanceConfig() {
        return new PerformanceConfig(
            getConfig("performance.enableOptimization", true),
            getConfig("performance.maxCacheSize", 1000),
            getConfig("performance.enableParallelProcessing", false)
        );
    }
    
    /**
     * 获取调试配置
     * @return 调试配置
     */
    public DebugConfig getDebugConfig() {
        return new DebugConfig(
            getConfig("debug.enableDebugMode", false),
            getConfig("debug.enableDetailedLogging", false),
            getConfig("debug.enablePerformanceMonitoring", false)
        );
    }
    
    /**
     * 分析器配置
     */
    public static class AnalyzerConfig {
        private final boolean enableDetailedAnalysis;
        private final int maxAnalysisDepth;
        private final boolean enableCaching;
        
        public AnalyzerConfig(boolean enableDetailedAnalysis, int maxAnalysisDepth, boolean enableCaching) {
            this.enableDetailedAnalysis = enableDetailedAnalysis;
            this.maxAnalysisDepth = maxAnalysisDepth;
            this.enableCaching = enableCaching;
        }
        
        public boolean isEnableDetailedAnalysis() {
            return enableDetailedAnalysis;
        }
        
        public int getMaxAnalysisDepth() {
            return maxAnalysisDepth;
        }
        
        public boolean isEnableCaching() {
            return enableCaching;
        }
    }
    
    /**
     * 映射器配置
     */
    public static class MapperConfig {
        private final boolean enableCustomRules;
        private final boolean enablePriorityAdjustment;
        private final boolean enableComplexActionHandling;
        
        public MapperConfig(boolean enableCustomRules, boolean enablePriorityAdjustment, boolean enableComplexActionHandling) {
            this.enableCustomRules = enableCustomRules;
            this.enablePriorityAdjustment = enablePriorityAdjustment;
            this.enableComplexActionHandling = enableComplexActionHandling;
        }
        
        public boolean isEnableCustomRules() {
            return enableCustomRules;
        }
        
        public boolean isEnablePriorityAdjustment() {
            return enablePriorityAdjustment;
        }
        
        public boolean isEnableComplexActionHandling() {
            return enableComplexActionHandling;
        }
    }
    
    /**
     * 验证器配置
     */
    public static class ValidatorConfig {
        private final boolean enableStrictValidation;
        private final boolean enableAllRules;
        private final int maxErrorCount;
        
        public ValidatorConfig(boolean enableStrictValidation, boolean enableAllRules, int maxErrorCount) {
            this.enableStrictValidation = enableStrictValidation;
            this.enableAllRules = enableAllRules;
            this.maxErrorCount = maxErrorCount;
        }
        
        public boolean isEnableStrictValidation() {
            return enableStrictValidation;
        }
        
        public boolean isEnableAllRules() {
            return enableAllRules;
        }
        
        public int getMaxErrorCount() {
            return maxErrorCount;
        }
    }
    
    /**
     * 服务配置
     */
    public static class ServiceConfig {
        private final boolean enableValidation;
        private final boolean enableStatistics;
        private final boolean enableLogging;
        private final int maxRetries;
        private final long timeoutMs;
        
        public ServiceConfig(boolean enableValidation, boolean enableStatistics, boolean enableLogging, int maxRetries, long timeoutMs) {
            this.enableValidation = enableValidation;
            this.enableStatistics = enableStatistics;
            this.enableLogging = enableLogging;
            this.maxRetries = maxRetries;
            this.timeoutMs = timeoutMs;
        }
        
        public boolean isEnableValidation() {
            return enableValidation;
        }
        
        public boolean isEnableStatistics() {
            return enableStatistics;
        }
        
        public boolean isEnableLogging() {
            return enableLogging;
        }
        
        public int getMaxRetries() {
            return maxRetries;
        }
        
        public long getTimeoutMs() {
            return timeoutMs;
        }
    }
    
    /**
     * 性能配置
     */
    public static class PerformanceConfig {
        private final boolean enableOptimization;
        private final int maxCacheSize;
        private final boolean enableParallelProcessing;
        
        public PerformanceConfig(boolean enableOptimization, int maxCacheSize, boolean enableParallelProcessing) {
            this.enableOptimization = enableOptimization;
            this.maxCacheSize = maxCacheSize;
            this.enableParallelProcessing = enableParallelProcessing;
        }
        
        public boolean isEnableOptimization() {
            return enableOptimization;
        }
        
        public int getMaxCacheSize() {
            return maxCacheSize;
        }
        
        public boolean isEnableParallelProcessing() {
            return enableParallelProcessing;
        }
    }
    
    /**
     * 调试配置
     */
    public static class DebugConfig {
        private final boolean enableDebugMode;
        private final boolean enableDetailedLogging;
        private final boolean enablePerformanceMonitoring;
        
        public DebugConfig(boolean enableDebugMode, boolean enableDetailedLogging, boolean enablePerformanceMonitoring) {
            this.enableDebugMode = enableDebugMode;
            this.enableDetailedLogging = enableDetailedLogging;
            this.enablePerformanceMonitoring = enablePerformanceMonitoring;
        }
        
        public boolean isEnableDebugMode() {
            return enableDebugMode;
        }
        
        public boolean isEnableDetailedLogging() {
            return enableDetailedLogging;
        }
        
        public boolean isEnablePerformanceMonitoring() {
            return enablePerformanceMonitoring;
        }
    }
}