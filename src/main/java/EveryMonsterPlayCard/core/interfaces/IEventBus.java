package EveryMonsterPlayCard.core.interfaces;

import java.util.List;

/**
 * 事件总线接口
 * 负责系统间的事件通信
 */
public interface IEventBus {
    /**
     * 注册事件监听器
     * @param eventType 事件类型
     * @param listener 监听器
     */
    <T extends IEvent> void registerListener(Class<T> eventType, IEventListener<T> listener);
    
    /**
     * 取消注册事件监听器
     * @param eventType 事件类型
     * @param listener 监听器
     */
    <T extends IEvent> void unregisterListener(Class<T> eventType, IEventListener<T> listener);
    
    /**
     * 发布事件
     * @param event 事件对象
     */
    void publishEvent(IEvent event);
    
    /**
     * 异步发布事件
     * @param event 事件对象
     */
    void publishEventAsync(IEvent event);
    
    /**
     * 获取指定类型的监听器列表
     * @param eventType 事件类型
     * @return 监听器列表
     */
    <T extends IEvent> List<IEventListener<T>> getListeners(Class<T> eventType);
    
    /**
     * 获取所有已注册的事件类型
     * @return 事件类型列表
     */
    List<Class<? extends IEvent>> getRegisteredEventTypes();
    
    /**
     * 清除所有监听器
     */
    void clearAllListeners();
    
    /**
     * 清除指定事件类型的所有监听器
     * @param eventType 事件类型
     */
    <T extends IEvent> void clearListeners(Class<T> eventType);
    
    /**
     * 检查是否有指定事件类型的监听器
     * @param eventType 事件类型
     * @return 是否有监听器
     */
    <T extends IEvent> boolean hasListeners(Class<T> eventType);
    
    /**
     * 获取监听器数量
     * @param eventType 事件类型
     * @return 监听器数量
     */
    <T extends IEvent> int getListenerCount(Class<T> eventType);
    
    /**
     * 启用事件总线
     */
    void enable();
    
    /**
     * 禁用事件总线
     */
    void disable();
    
    /**
     * 检查事件总线是否启用
     * @return 是否启用
     */
    boolean isEnabled();
    
    /**
     * 设置事件处理异常处理器
     * @param handler 异常处理器
     */
    void setExceptionHandler(ExceptionHandler handler);
    
    /**
     * 事件处理异常处理器接口
     */
    interface ExceptionHandler {
        /**
         * 处理异常
         * @param event 事件对象
         * @param listener 监听器
         * @param exception 异常
         */
        void handleException(IEvent event, IEventListener<?> listener, Exception exception);
    }
}