package EveryMonsterPlayCard.core.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import EveryMonsterPlayCard.core.interfaces.IConfigChangeListener;

/**
 * 配置管理器实现
 */
public class ConfigManager implements EveryMonsterPlayCard.core.interfaces.IConfigManager {
    private final Map<String, Object> config = new ConcurrentHashMap<>();
    private final List<IConfigChangeListener> listeners = new CopyOnWriteArrayList<>();
    private final String configFileName;
    
    public ConfigManager() {
        this("monsterplaycard-config.json");
    }
    
    public ConfigManager(String configFileName) {
        this.configFileName = configFileName;
        initializeDefaultConfig();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getConfig(String key, T defaultValue) {
        Object value = config.get(key);
        if (value == null) {
            return defaultValue;
        }
        
        try {
            return (T) value;
        } catch (ClassCastException e) {
            System.err.println("Config type mismatch for key: " + key + ", expected: " + 
                             defaultValue.getClass().getSimpleName() + ", actual: " + value.getClass().getSimpleName());
            return defaultValue;
        }
    }
    
    @Override
    public void setConfig(String key, Object value) {
        Object oldValue = config.get(key);
        config.put(key, value);
        
        // 通知监听器
        for (IConfigChangeListener listener : listeners) {
            try {
                listener.onConfigChanged(key, oldValue, value);
            } catch (Exception e) {
                System.err.println("Error notifying config change listener: " + e.getMessage());
            }
        }
    }
    
    @Override
    public Set<String> getAllKeys() {
        return new java.util.HashSet<>(config.keySet());
    }
    
    @Override
    public boolean hasConfig(String key) {
        return config.containsKey(key);
    }
    
    @Override
    public void reloadConfig() {
        // 从文件重新加载配置
        try {
            loadConfigFromFile();
        } catch (Exception e) {
            System.err.println("Failed to reload config from file: " + e.getMessage());
            initializeDefaultConfig();
        }
    }
    
    @Override
    public void saveConfig() {
        // 保存配置到文件
        try {
            saveConfigToFile();
        } catch (Exception e) {
            System.err.println("Failed to save config to file: " + e.getMessage());
        }
    }
    
    @Override
    public void registerConfigChangeListener(IConfigChangeListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }
    
    @Override
    public void unregisterConfigChangeListener(IConfigChangeListener listener) {
        if (listener != null) {
            listeners.remove(listener);
        }
    }
    
    /**
     * 初始化默认配置
     */
    private void initializeDefaultConfig() {
        // Downfall卡牌移植配置
        setConfig("downfall.enabled", true);
        setConfig("downfall.autoImport", true);
        setConfig("downfall.balanceAdjustments", true);
        setConfig("downfall.supportedVersions", new String[]{"1.0.0", "1.1.0"});
        
        // 意图转换配置
        setConfig("intentConversion.enabled", true);
        setConfig("intentConversion.complexActionDecomposition", true);
        setConfig("intentConversion.intentStrengthCalculation", "advanced");
        setConfig("intentConversion.validationEnabled", true);
        
        // 卡牌生成配置
        setConfig("cardGeneration.enabled", true);
        setConfig("cardGeneration.attributeInference", "smart");
        setConfig("cardGeneration.balanceOptimization", true);
        setConfig("cardGeneration.effectSimulation", true);
        
        // 集成配置
        setConfig("integration.eventLogging", true);
        setConfig("integration.performanceMonitoring", true);
        setConfig("integration.errorReporting", true);
        
        // 调试配置
        setConfig("debug.enabled", false);
        setConfig("debug.verboseLogging", false);
        setConfig("debug.performanceTracking", false);
    }
    
    /**
     * 从文件加载配置
     */
    private void loadConfigFromFile() {
        // TODO: 实现从JSON文件加载配置的逻辑
        // 这里暂时使用默认配置
        initializeDefaultConfig();
    }
    
    /**
     * 保存配置到文件
     */
    private void saveConfigToFile() {
        // TODO: 实现保存配置到JSON文件的逻辑
        System.out.println("Config saved to " + configFileName);
    }
    
    /**
     * 获取配置文件名
     * @return 配置文件名
     */
    public String getConfigFileName() {
        return configFileName;
    }
    
    /**
     * 获取所有配置的字符串表示
     * @return 配置字符串
     */
    public String getConfigString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Configuration:\n");
        
        for (Map.Entry<String, Object> entry : config.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * 清除所有配置
     */
    public void clearConfig() {
        config.clear();
    }
    
    /**
     * 移除指定配置项
     * @param key 配置键
     * @return 被移除的值
     */
    public Object removeConfig(String key) {
        return config.remove(key);
    }
    
    /**
     * 批量设置配置
     * @param configs 配置映射
     */
    public void setConfigs(Map<String, Object> configs) {
        for (Map.Entry<String, Object> entry : configs.entrySet()) {
            setConfig(entry.getKey(), entry.getValue());
        }
    }
}