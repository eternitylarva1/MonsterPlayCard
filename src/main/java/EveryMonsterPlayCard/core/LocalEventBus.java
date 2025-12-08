package EveryMonsterPlayCard.core;

import EveryMonsterPlayCard.utils.Hpr;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.ArrayList;

/**
 * 本地事件总线 - 替代网络通信的事件系统
 * 单机模式下使用本地事件进行组件间通信
 */
public class LocalEventBus {

    private static LocalEventBus instance = null;

    // 事件类型到处理器列表的映射
    private HashMap<Class<?>, ArrayList<Consumer<?>>> handlers;

    private LocalEventBus() {
        this.handlers = new HashMap<>();
    }

    /**
     * 获取单例实例
     */
    public static LocalEventBus getInstance() {
        if (instance == null) {
            instance = new LocalEventBus();
        }
        return instance;
    }

    /**
     * 注册事件处理器
     */
    public <T> void registerEvent(Class<T> eventType, Consumer<T> handler) {
        handlers.computeIfAbsent(eventType, k -> new ArrayList<>())
                .add((Consumer<?>) handler);
        Hpr.info("注册事件处理器: " + eventType.getSimpleName());
    }

    /**
     * 发送事件
     */
    public <T> void sendEvent(T event) {
        Class<?> eventType = event.getClass();
        ArrayList<Consumer<?>> handlerList = handlers.get(eventType);

        if (handlerList != null) {
            for (Consumer<?> handler : handlerList) {
                try {
                    ((Consumer<T>) handler).accept(event);
                } catch (Exception e) {
                    Hpr.info("事件处理器执行失败: " + eventType.getSimpleName() + ", 错误: " + e.getMessage());
                }
            }
        } else {
            Hpr.info("没有找到事件处理器: " + eventType.getSimpleName());
        }
    }

    /**
     * 取消注册事件处理器
     */
    public <T> void unregisterEvent(Class<T> eventType, Consumer<T> handler) {
        ArrayList<Consumer<?>> handlerList = handlers.get(eventType);
        if (handlerList != null) {
            handlerList.remove(handler);
            if (handlerList.isEmpty()) {
                handlers.remove(eventType);
            }
        }
    }

    /**
     * 清理所有事件处理器
     */
    public void clear() {
        handlers.clear();
        Hpr.info("事件总线已清理");
    }

    /**
     * 获取已注册的事件类型数量
     */
    public int getRegisteredEventTypes() {
        return handlers.size();
    }

    /**
     * 获取指定事件类型的处理器数量
     */
    public <T> int getHandlerCount(Class<T> eventType) {
        ArrayList<Consumer<?>> handlerList = handlers.get(eventType);
        return handlerList != null ? handlerList.size() : 0;
    }
}
