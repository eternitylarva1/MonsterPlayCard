package EveryMonsterPlayCard.core.interfaces;

import java.util.Map;
import java.util.Set;

/**
 * 配置管理器接口
 */
public interface IConfigManager {
    /**
     * 获取配置值
     * @param key 配置键
     * @param defaultValue 默认值
     * @return 配置值
     */
    <T> T getConfig(String key, T defaultValue);
    
    /**
     * 设置配置值
     * @param key 配置键
     * @param value 配置值
     */
    void setConfig(String key, Object value);
    
    /**
     * 获取所有配置键
     * @return 配置键集合
     */
    Set<String> getAllKeys();
    
    /**
     * 检查配置是否存在
     * @param key 配置键
     * @return 是否存在
     */
    boolean hasConfig(String key);
    
    /**
     * 重新加载配置
     */
    void reloadConfig();
    
    /**
     * 保存配置
     */
    void saveConfig();
    
    /**
     * 注册配置变更监听器
     * @param listener 监听器
     */
    void registerConfigChangeListener(IConfigChangeListener listener);
    
    /**
     * 取消注册配置变更监听器
     * @param listener 监听器
     */
    void unregisterConfigChangeListener(IConfigChangeListener listener);
    
    /**
     * 获取配置的字符串表示
     * @return 配置字符串
     */
    default String getConfigString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Configuration:\n");
        
        for (String key : getAllKeys()) {
            Object value = getConfig(key, null);
            sb.append("  ").append(key).append(" = ").append(value).append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * 批量设置配置
     * @param configs 配置映射
     */
    default void setConfigs(Map<String, Object> configs) {
        for (Map.Entry<String, Object> entry : configs.entrySet()) {
            setConfig(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * 清除所有配置
     */
    default void clearConfig() {
        for (String key : getAllKeys()) {
            setConfig(key, null);
        }
    }
    
    /**
     * 移除指定配置项
     * @param key 配置键
     * @return 被移除的值
     */
    default Object removeConfig(String key) {
        Object oldValue = getConfig(key, null);
        setConfig(key, null);
        return oldValue;
    }
}