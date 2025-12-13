package EveryMonsterPlayCard.core.events;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import EveryMonsterPlayCard.core.interfaces.IEvent;
import EveryMonsterPlayCard.core.interfaces.IEventBus;
import EveryMonsterPlayCard.core.interfaces.IEventListener;

/**
 * 事件总线实现
 */
public class EventBus implements IEventBus {
    private final Map<Class<? extends IEvent>, List<IEventListener<?>>> listeners = new ConcurrentHashMap<>();
    private final ExecutorService asyncExecutor = Executors.newCachedThreadPool();
    private boolean enabled = true;
    private ExceptionHandler exceptionHandler;
    
    public EventBus() {
        this.exceptionHandler = new DefaultExceptionHandler();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T extends IEvent> void registerListener(Class<T> eventType, IEventListener<T> listener) {
        if (eventType == null || listener == null) {
            return;
        }
        
        listeners.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>())
                .add((IEventListener<?>) listener);
        
        // 按优先级排序
        listeners.get(eventType).sort(Comparator.comparingInt(IEventListener::getPriority));
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T extends IEvent> void unregisterListener(Class<T> eventType, IEventListener<T> listener) {
        if (eventType == null || listener == null) {
            return;
        }
        
        List<IEventListener<?>> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            eventListeners.remove(listener);
            
            // 如果没有监听器了，移除整个条目
            if (eventListeners.isEmpty()) {
                listeners.remove(eventType);
            }
        }
    }
    
    @Override
    public void publishEvent(IEvent event) {
        if (!enabled || event == null) {
            return;
        }
        
        List<IEventListener<?>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (IEventListener<?> listener : eventListeners) {
                try {
                    if (listener.isEnabled() && listener.canHandle(event)) {
                        @SuppressWarnings("unchecked")
                        IEventListener<IEvent> typedListener = (IEventListener<IEvent>) listener;
                        typedListener.beforeHandle(event);
                        typedListener.handleEvent(event);
                        typedListener.afterHandle(event, true, null);
                    }
                } catch (Exception e) {
                    @SuppressWarnings("unchecked")
                    IEventListener<IEvent> typedListener = (IEventListener<IEvent>) listener;
                    typedListener.afterHandle(event, false, e.getMessage());
                    if (exceptionHandler != null) {
                        exceptionHandler.handleException(event, listener, e);
                    }
                }
            }
        }
    }
    
    @Override
    public void publishEventAsync(IEvent event) {
        if (!enabled || event == null) {
            return;
        }
        
        asyncExecutor.submit(() -> publishEvent(event));
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public <T extends IEvent> List<IEventListener<T>> getListeners(Class<T> eventType) {
        List<IEventListener<?>> eventListeners = listeners.get(eventType);
        if (eventListeners != null) {
            return eventListeners.stream()
                    .map(listener -> (IEventListener<T>) listener)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    
    @Override
    public List<Class<? extends IEvent>> getRegisteredEventTypes() {
        return new ArrayList<>(listeners.keySet());
    }
    
    @Override
    public void clearAllListeners() {
        listeners.clear();
    }
    
    @Override
    public <T extends IEvent> void clearListeners(Class<T> eventType) {
        listeners.remove(eventType);
    }
    
    @Override
    public <T extends IEvent> boolean hasListeners(Class<T> eventType) {
        List<IEventListener<?>> eventListeners = listeners.get(eventType);
        return eventListeners != null && !eventListeners.isEmpty();
    }
    
    @Override
    public <T extends IEvent> int getListenerCount(Class<T> eventType) {
        List<IEventListener<?>> eventListeners = listeners.get(eventType);
        return eventListeners != null ? eventListeners.size() : 0;
    }
    
    @Override
    public void enable() {
        this.enabled = true;
    }
    
    @Override
    public void disable() {
        this.enabled = false;
    }
    
    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    @Override
    public void setExceptionHandler(ExceptionHandler handler) {
        this.exceptionHandler = handler;
    }
    
    /**
     * 关闭事件总线，释放资源
     */
    public void shutdown() {
        disable();
        asyncExecutor.shutdown();
        clearAllListeners();
    }
    
    /**
     * 默认异常处理器
     */
    private static class DefaultExceptionHandler implements ExceptionHandler {
        @Override
        public void handleException(IEvent event, IEventListener<?> listener, Exception exception) {
            System.err.println("Event handling error:");
            System.err.println("  Event: " + event.getClass().getSimpleName());
            System.err.println("  Listener: " + listener.getClass().getSimpleName());
            System.err.println("  Error: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}