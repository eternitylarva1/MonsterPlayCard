package EveryMonsterPlayCard.integration;

import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import EveryMonsterPlayCard.modcore.everyMonsterPlayCard;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * 动作-意图转换系统配置
 * 管理转换系统的各种配置选项
 */
@SpireInitializer
public class ActionIntentConfig {
    
    private static final String CONFIG_FILE = "action-intent-config";
    private static SpireConfig config;
    
    // 配置选项
    public static boolean enableActionIntentConversion = true;
    public static boolean enableIntentPrediction = true;
    public static boolean enableDetailedLogging = false;
    public static boolean enablePerformanceMonitoring = false;
    public static boolean enableAutoValidation = true;
    public static int maxIntentDisplayCount = 3;
    public static float intentUpdateInterval = 0.5f;
    
    /**
     * 初始化配置
     */
    public static void initialize() {
        try {
            config = new SpireConfig(everyMonsterPlayCard.MOD_ID, CONFIG_FILE);
            
            // 加载配置
            loadConfiguration();
            
            Hpr.info("动作-意图转换系统配置已初始化");
            
        } catch (Exception e) {
            Hpr.info("动作-意图转换系统配置初始化失败: " + e.getMessage());
            // 使用默认配置
            setDefaultConfiguration();
        }
    }
    
    /**
     * 加载配置
     */
    private static void loadConfiguration() {
        try {
            // 加载各项配置
            enableActionIntentConversion = config.getBool("enableActionIntentConversion");
            enableIntentPrediction = config.getBool("enableIntentPrediction");
            enableDetailedLogging = config.getBool("enableDetailedLogging");
            enablePerformanceMonitoring = config.getBool("enablePerformanceMonitoring");
            enableAutoValidation = config.getBool("enableAutoValidation");
            maxIntentDisplayCount = config.getInt("maxIntentDisplayCount");
            intentUpdateInterval = config.getFloat("intentUpdateInterval");
            
            // 验证配置值
            validateConfiguration();
            
            Hpr.info("动作-意图转换系统配置已加载");
            
        } catch (Exception e) {
            Hpr.info("加载配置失败，使用默认配置: " + e.getMessage());
            setDefaultConfiguration();
        }
    }
    
    /**
     * 设置默认配置
     */
    private static void setDefaultConfiguration() {
        enableActionIntentConversion = true;
        enableIntentPrediction = true;
        enableDetailedLogging = false;
        enablePerformanceMonitoring = false;
        enableAutoValidation = true;
        maxIntentDisplayCount = 3;
        intentUpdateInterval = 0.5f;
        
        Hpr.info("使用默认动作-意图转换系统配置");
    }
    
    /**
     * 验证配置值
     */
    private static void validateConfiguration() {
        // 验证数值范围
        if (maxIntentDisplayCount < 1) {
            maxIntentDisplayCount = 1;
        } else if (maxIntentDisplayCount > 10) {
            maxIntentDisplayCount = 10;
        }
        
        if (intentUpdateInterval < 0.1f) {
            intentUpdateInterval = 0.1f;
        } else if (intentUpdateInterval > 2.0f) {
            intentUpdateInterval = 2.0f;
        }
    }
    
    /**
     * 保存配置
     */
    public static void saveConfiguration() {
        try {
            if (config == null) {
                Hpr.info("配置对象为空，无法保存");
                return;
            }
            
            // 保存各项配置
            config.setBool("enableActionIntentConversion", enableActionIntentConversion);
            config.setBool("enableIntentPrediction", enableIntentPrediction);
            config.setBool("enableDetailedLogging", enableDetailedLogging);
            config.setBool("enablePerformanceMonitoring", enablePerformanceMonitoring);
            config.setBool("enableAutoValidation", enableAutoValidation);
            config.setInt("maxIntentDisplayCount", maxIntentDisplayCount);
            config.setFloat("intentUpdateInterval", intentUpdateInterval);
            
            config.save();
            
            Hpr.info("动作-意图转换系统配置已保存");
            
        } catch (Exception e) {
            Hpr.info("保存配置失败: " + e.getMessage());
        }
    }
    
    /**
     * 重置配置为默认值
     */
    public static void resetToDefault() {
        setDefaultConfiguration();
        saveConfiguration();
        Hpr.info("动作-意图转换系统配置已重置为默认值");
    }
    
    /**
     * 获取配置摘要
     */
    public static String getConfigurationSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== 动作-意图转换系统配置 ===\n");
        summary.append("启用动作-意图转换: ").append(enableActionIntentConversion ? "是" : "否").append("\n");
        summary.append("启用意图预测: ").append(enableIntentPrediction ? "是" : "否").append("\n");
        summary.append("启用详细日志: ").append(enableDetailedLogging ? "是" : "否").append("\n");
        summary.append("启用性能监控: ").append(enablePerformanceMonitoring ? "是" : "否").append("\n");
        summary.append("启用自动验证: ").append(enableAutoValidation ? "是" : "否").append("\n");
        summary.append("最大意图显示数量: ").append(maxIntentDisplayCount).append("\n");
        summary.append("意图更新间隔: ").append(intentUpdateInterval).append("秒\n");
        summary.append("================================");
        
        return summary.toString();
    }
    
    /**
     * 检查是否启用动作-意图转换
     */
    public static boolean isActionIntentConversionEnabled() {
        return enableActionIntentConversion;
    }
    
    /**
     * 检查是否启用意图预测
     */
    public static boolean isIntentPredictionEnabled() {
        return enableIntentPrediction;
    }
    
    /**
     * 检查是否启用详细日志
     */
    public static boolean isDetailedLoggingEnabled() {
        return enableDetailedLogging;
    }
    
    /**
     * 检查是否启用性能监控
     */
    public static boolean isPerformanceMonitoringEnabled() {
        return enablePerformanceMonitoring;
    }
    
    /**
     * 检查是否启用自动验证
     */
    public static boolean isAutoValidationEnabled() {
        return enableAutoValidation;
    }
    
    /**
     * 获取最大意图显示数量
     */
    public static int getMaxIntentDisplayCount() {
        return maxIntentDisplayCount;
    }
    
    /**
     * 获取意图更新间隔
     */
    public static float getIntentUpdateInterval() {
        return intentUpdateInterval;
    }
    
    /**
     * 设置动作-意图转换启用状态
     */
    public static void setActionIntentConversionEnabled(boolean enabled) {
        enableActionIntentConversion = enabled;
        saveConfiguration();
    }
    
    /**
     * 设置意图预测启用状态
     */
    public static void setIntentPredictionEnabled(boolean enabled) {
        enableIntentPrediction = enabled;
        saveConfiguration();
    }
    
    /**
     * 设置详细日志启用状态
     */
    public static void setDetailedLoggingEnabled(boolean enabled) {
        enableDetailedLogging = enabled;
        saveConfiguration();
    }
    
    /**
     * 设置性能监控启用状态
     */
    public static void setPerformanceMonitoringEnabled(boolean enabled) {
        enablePerformanceMonitoring = enabled;
        saveConfiguration();
    }
    
    /**
     * 设置自动验证启用状态
     */
    public static void setAutoValidationEnabled(boolean enabled) {
        enableAutoValidation = enabled;
        saveConfiguration();
    }
    
    /**
     * 设置最大意图显示数量
     */
    public static void setMaxIntentDisplayCount(int count) {
        maxIntentDisplayCount = Math.max(1, Math.min(10, count));
        saveConfiguration();
    }
    
    /**
     * 设置意图更新间隔
     */
    public static void setIntentUpdateInterval(float interval) {
        intentUpdateInterval = Math.max(0.1f, Math.min(2.0f, interval));
        saveConfiguration();
    }
}