package EveryMonsterPlayCard.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 动作转换服务工厂
 * 负责创建和配置动作转换服务
 */
public class ActionConversionServiceFactory {
    private static final Map<String, ActionConversionService> serviceInstances = new HashMap<>();
    private static final Map<String, ServiceConfiguration> serviceConfigurations = new HashMap<>();
    
    /**
     * 获取默认服务实例
     * @return 默认配置的动作转换服务
     */
    public static ActionConversionService getDefaultService() {
        return getService("default");
    }
    
    /**
     * 获取指定配置的服务实例
     * @param configName 配置名称
     * @return 指定配置的动作转换服务
     */
    public static ActionConversionService getService(String configName) {
        if (!serviceInstances.containsKey(configName)) {
            ServiceConfiguration config = getServiceConfiguration(configName);
            ActionConversionService service = createService(config);
            serviceInstances.put(configName, service);
        }
        
        return serviceInstances.get(configName);
    }
    
    /**
     * 创建服务实例
     * @param config 服务配置
     * @return 服务实例
     */
    private static ActionConversionService createService(ServiceConfiguration config) {
        ActionConversionService service = new ActionConversionService();
        
        // 应用配置
        for (Map.Entry<String, Object> entry : config.getConfigurations().entrySet()) {
            service.setConfig(entry.getKey(), entry.getValue());
        }
        
        return service;
    }
    
    /**
     * 获取服务配置
     * @param configName 配置名称
     * @return 服务配置
     */
    public static ServiceConfiguration getServiceConfiguration(String configName) {
        if (!serviceConfigurations.containsKey(configName)) {
            ServiceConfiguration config = createDefaultConfiguration(configName);
            serviceConfigurations.put(configName, config);
        }
        
        return serviceConfigurations.get(configName);
    }
    
    /**
     * 创建默认配置
     * @param configName 配置名称
     * @return 默认配置
     */
    private static ServiceConfiguration createDefaultConfiguration(String configName) {
        ServiceConfiguration config = new ServiceConfiguration(configName);
        
        switch (configName) {
            case "default":
                // 默认配置
                config.setConfig("enableValidation", true);
                config.setConfig("enableStatistics", true);
                config.setConfig("enableLogging", false);
                config.setConfig("maxRetries", 3);
                config.setConfig("timeoutMs", 5000);
                break;
                
            case "development":
                // 开发配置
                config.setConfig("enableValidation", true);
                config.setConfig("enableStatistics", true);
                config.setConfig("enableLogging", true);
                config.setConfig("maxRetries", 5);
                config.setConfig("timeoutMs", 10000);
                break;
                
            case "production":
                // 生产配置
                config.setConfig("enableValidation", true);
                config.setConfig("enableStatistics", false);
                config.setConfig("enableLogging", false);
                config.setConfig("maxRetries", 2);
                config.setConfig("timeoutMs", 3000);
                break;
                
            case "testing":
                // 测试配置
                config.setConfig("enableValidation", true);
                config.setConfig("enableStatistics", true);
                config.setConfig("enableLogging", true);
                config.setConfig("maxRetries", 1);
                config.setConfig("timeoutMs", 1000);
                break;
                
            default:
                // 未知配置，使用默认配置
                config.setConfig("enableValidation", true);
                config.setConfig("enableStatistics", true);
                config.setConfig("enableLogging", false);
                config.setConfig("maxRetries", 3);
                config.setConfig("timeoutMs", 5000);
                break;
        }
        
        return config;
    }
    
    /**
     * 注册自定义配置
     * @param configName 配置名称
     * @param config 服务配置
     */
    public static void registerConfiguration(String configName, ServiceConfiguration config) {
        serviceConfigurations.put(configName, config);
        
        // 如果已有服务实例，清除缓存以强制重新创建
        if (serviceInstances.containsKey(configName)) {
            serviceInstances.remove(configName);
        }
    }
    
    /**
     * 获取所有已注册的配置名称
     * @return 配置名称列表
     */
    public static String[] getRegisteredConfigurationNames() {
        return serviceConfigurations.keySet().toArray(new String[0]);
    }
    
    /**
     * 清除所有服务实例缓存
     */
    public static void clearServiceCache() {
        serviceInstances.clear();
    }
    
    /**
     * 清除指定服务实例缓存
     * @param configName 配置名称
     */
    public static void clearServiceCache(String configName) {
        serviceInstances.remove(configName);
    }
    
    /**
     * 重置所有配置为默认值
     */
    public static void resetAllConfigurations() {
        serviceConfigurations.clear();
        serviceInstances.clear();
    }
    
    /**
     * 服务配置
     */
    public static class ServiceConfiguration {
        private final String name;
        private final Map<String, Object> configurations;
        
        public ServiceConfiguration(String name) {
            this.name = name;
            this.configurations = new HashMap<>();
        }
        
        public String getName() {
            return name;
        }
        
        public Map<String, Object> getConfigurations() {
            return configurations;
        }
        
        public void setConfig(String key, Object value) {
            configurations.put(key, value);
        }
        
        @SuppressWarnings("unchecked")
        public <T> T getConfig(String key) {
            return (T) configurations.get(key);
        }
        
        public boolean hasConfig(String key) {
            return configurations.containsKey(key);
        }
        
        public void removeConfig(String key) {
            configurations.remove(key);
        }
        
        public void clearConfigs() {
            configurations.clear();
        }
        
        @Override
        public String toString() {
            return "ServiceConfiguration{" +
                    "name='" + name + '\'' +
                    ", configurations=" + configurations +
                    '}';
        }
    }
}