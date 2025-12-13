package EveryMonsterPlayCard.intent2card.analyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * 意图参数
 */
public class IntentParameters {
    private Map<String, Object> parameters;
    
    public IntentParameters() {
        this.parameters = new HashMap<>();
    }
    
    public IntentParameters(Map<String, Object> parameters) {
        this.parameters = parameters != null ? new HashMap<>(parameters) : new HashMap<>();
    }
    
    /**
     * 添加参数
     */
    public void addParameter(String key, Object value) {
        this.parameters.put(key, value);
    }
    
    /**
     * 获取参数
     */
    public Object getParameter(String key) {
        return this.parameters.get(key);
    }
    
    /**
     * 获取字符串参数
     */
    public String getStringParameter(String key) {
        Object value = this.parameters.get(key);
        return value != null ? value.toString() : null;
    }
    
    /**
     * 获取整数参数
     */
    public int getIntParameter(String key) {
        Object value = this.parameters.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
    
    /**
     * 获取布尔参数
     */
    public boolean getBooleanParameter(String key) {
        Object value = this.parameters.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }
        return false;
    }
    
    /**
     * 获取浮点数参数
     */
    public double getDoubleParameter(String key) {
        Object value = this.parameters.get(key);
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
        return 0.0;
    }
    
    /**
     * 检查是否包含参数
     */
    public boolean hasParameter(String key) {
        return this.parameters.containsKey(key);
    }
    
    /**
     * 移除参数
     */
    public Object removeParameter(String key) {
        return this.parameters.remove(key);
    }
    
    /**
     * 获取所有参数
     */
    public Map<String, Object> getAllParameters() {
        return new HashMap<>(this.parameters);
    }
    
    /**
     * 设置所有参数
     */
    public void setAllParameters(Map<String, Object> parameters) {
        this.parameters = parameters != null ? new HashMap<>(parameters) : new HashMap<>();
    }
    
    /**
     * 清空所有参数
     */
    public void clear() {
        this.parameters.clear();
    }
    
    /**
     * 获取参数数量
     */
    public int size() {
        return this.parameters.size();
    }
    
    /**
     * 检查是否为空
     */
    public boolean isEmpty() {
        return this.parameters.isEmpty();
    }
    
    /**
     * 检查是否有伤害值
     */
    public boolean hasDamage() {
        return hasParameter("damage") && getIntParameter("damage") > 0;
    }
    
    /**
     * 获取伤害值
     */
    public int getDamage() {
        return getIntParameter("damage");
    }
    
    /**
     * 设置伤害值
     */
    public void setDamage(int damage) {
        addParameter("damage", damage);
    }
    
    /**
     * 检查是否有格挡值
     */
    public boolean hasBlock() {
        return hasParameter("block") && getIntParameter("block") > 0;
    }
    
    /**
     * 获取格挡值
     */
    public int getBlock() {
        return getIntParameter("block");
    }
    
    /**
     * 设置格挡值
     */
    public void setBlock(int block) {
        addParameter("block", block);
    }
    
    /**
     * 检查是否有魔法数值
     */
    public boolean hasMagicNumber() {
        return hasParameter("magicNumber") && getIntParameter("magicNumber") > 0;
    }
    
    /**
     * 获取魔法数值
     */
    public int getMagicNumber() {
        return getIntParameter("magicNumber");
    }
    
    /**
     * 设置魔法数值
     */
    public void setMagicNumber(int magicNumber) {
        addParameter("magicNumber", magicNumber);
    }
    
    /**
     * 获取卡牌费用
     */
    public int getCost() {
        return getIntParameter("cost");
    }
    
    /**
     * 设置卡牌费用
     */
    public void setCost(int cost) {
        addParameter("cost", cost);
    }
    
    @Override
    public String toString() {
        return "IntentParameters{" +
                "parameters=" + parameters +
                '}';
    }
}