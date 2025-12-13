package EveryMonsterPlayCard.conversion.analyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * 动作参数
 * 存储动作的各种参数信息
 */
public class ActionParameters {
    private Map<String, Object> parameters;
    
    public ActionParameters() {
        this.parameters = new HashMap<>();
    }
    
    public ActionParameters(Map<String, Object> parameters) {
        this.parameters = parameters != null ? new HashMap<>(parameters) : new HashMap<>();
    }
    
    /**
     * 设置参数
     * @param key 参数键
     * @param value 参数值
     */
    public void set(String key, Object value) {
        parameters.put(key, value);
    }
    
    /**
     * 获取参数
     * @param key 参数键
     * @return 参数值
     */
    public Object get(String key) {
        return parameters.get(key);
    }
    
    /**
     * 获取字符串参数
     * @param key 参数键
     * @return 字符串参数值
     */
    public String getString(String key) {
        Object value = parameters.get(key);
        return value != null ? value.toString() : null;
    }
    
    /**
     * 获取整数参数
     * @param key 参数键
     * @return 整数参数值
     */
    public int getInt(String key) {
        Object value = parameters.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return 0;
    }
    
    /**
     * 获取布尔参数
     * @param key 参数键
     * @return 布尔参数值
     */
    public boolean getBoolean(String key) {
        Object value = parameters.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        return false;
    }
    
    /**
     * 检查是否包含指定参数
     * @param key 参数键
     * @return 是否包含
     */
    public boolean contains(String key) {
        return parameters.containsKey(key);
    }
    
    /**
     * 移除参数
     * @param key 参数键
     * @return 被移除的参数值
     */
    public Object remove(String key) {
        return parameters.remove(key);
    }
    
    /**
     * 获取所有参数键
     * @return 参数键集合
     */
    public java.util.Set<String> keySet() {
        return parameters.keySet();
    }
    
    /**
     * 获取参数数量
     * @return 参数数量
     */
    public int size() {
        return parameters.size();
    }
    
    /**
     * 清空所有参数
     */
    public void clear() {
        parameters.clear();
    }
    
    /**
     * 检查是否为空
     * @return 是否为空
     */
    public boolean isEmpty() {
        return parameters.isEmpty();
    }
    
    /**
     * 获取所有参数的副本
     * @return 参数映射副本
     */
    public Map<String, Object> getAll() {
        return new HashMap<>(parameters);
    }
    
    @Override
    public String toString() {
        return "ActionParameters{" +
                "parameters=" + parameters +
                '}';
    }
}