package EveryMonsterPlayCard.core.interfaces;

/**
 * 基础事件接口
 */
public interface IEvent {
    /**
     * 获取事件时间戳
     * @return 时间戳
     */
    long getTimestamp();
    
    /**
     * 获取事件源
     * @return 事件源对象
     */
    Object getSource();
    
    /**
     * 获取事件类型
     * @return 事件类型
     */
    String getEventType();
    
    /**
     * 获取事件ID
     * @return 事件ID
     */
    default String getEventId() {
        return this.getClass().getSimpleName();
    }
    
    /**
     * 检查事件是否有效
     * @return 是否有效
     */
    default boolean isValid() {
        return true;
    }
    
    /**
     * 获取事件描述
     * @return 事件描述
     */
    default String getDescription() {
        return getEventType() + " event from " + getSource();
    }
}