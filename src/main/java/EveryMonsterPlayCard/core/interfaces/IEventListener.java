package EveryMonsterPlayCard.core.interfaces;

/**
 * 事件监听器接口
 */
public interface IEventListener<T extends IEvent> {
    /**
     * 处理事件
     * @param event 事件对象
     */
    void handleEvent(T event);
    
    /**
     * 获取监听器优先级
     * @return 优先级值（数值越小优先级越高）
     */
    default int getPriority() {
        return 0;
    }
    
    /**
     * 获取监听器名称
     * @return 监听器名称
     */
    default String getListenerName() {
        return this.getClass().getSimpleName();
    }
    
    /**
     * 检查是否可以处理指定事件
     * @param event 事件对象
     * @return 是否可以处理
     */
    default boolean canHandle(IEvent event) {
        return true;
    }
    
    /**
     * 检查监听器是否启用
     * @return 是否启用
     */
    default boolean isEnabled() {
        return true;
    }
    
    /**
     * 事件处理前的回调
     * @param event 事件对象
     */
    default void beforeHandle(T event) {
        // 默认空实现
    }
    
    /**
     * 事件处理后的回调
     * @param event 事件对象
     * @param success 是否处理成功
     * @param error 错误信息（如果有）
     */
    default void afterHandle(T event, boolean success, String error) {
        // 默认空实现
    }
}