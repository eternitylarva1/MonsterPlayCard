package EveryMonsterPlayCard.core.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 意图元数据
 */
public class IntentMetadata {
    private String intentId;
    private String description;
    private int priority;
    private Map<String, Object> properties;
    private long createTime;
    
    public IntentMetadata() {
        this.priority = 0;
        this.properties = new HashMap<>();
        this.createTime = System.currentTimeMillis();
    }
    
    public IntentMetadata(String intentId, String description) {
        this();
        this.intentId = intentId;
        this.description = description;
    }
    
    public IntentMetadata(String intentId, String description, int priority) {
        this(intentId, description);
        this.priority = priority;
    }
    
    public String getIntentId() {
        return intentId;
    }
    
    public void setIntentId(String intentId) {
        this.intentId = intentId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public Map<String, Object> getProperties() {
        return properties;
    }
    
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
    
    public void setProperty(String key, Object value) {
        this.properties.put(key, value);
    }
    
    public Object getProperty(String key) {
        return this.properties.get(key);
    }
    
    public boolean hasProperty(String key) {
        return this.properties.containsKey(key);
    }
    
    /**
     * 获取整数参数
     */
    public int getIntParameter(String key) {
        Object value = this.properties.get(key);
        if (value instanceof Integer) {
            return (Integer) value;
        } else if (value instanceof Number) {
            return ((Number) value).intValue();
        } else if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }
    
    /**
     * 获取字符串参数
     */
    public String getStringParameter(String key) {
        Object value = this.properties.get(key);
        if (value != null) {
            return value.toString();
        }
        return "";
    }
    
    /**
     * 获取布尔参数
     */
    public boolean getBooleanParameter(String key) {
        Object value = this.properties.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }
        return false;
    }
    
    public long getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
    
    @Override
    public String toString() {
        return "IntentMetadata{" +
                "intentId='" + intentId + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", createTime=" + createTime +
                '}';
    }
}