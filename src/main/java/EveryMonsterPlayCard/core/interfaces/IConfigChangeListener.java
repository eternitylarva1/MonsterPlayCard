package EveryMonsterPlayCard.core.interfaces;

/**
 * 配置变更监听器接口
 */
public interface IConfigChangeListener {
    /**
     * 配置变更时的回调
     * @param key 配置键
     * @param oldValue 旧值
     * @param newValue 新值
     */
    void onConfigChanged(String key, Object oldValue, Object newValue);
    
    /**
     * 获取监听器名称
     * @return 监听器名称
     */
    default String getListenerName() {
        return this.getClass().getSimpleName();
    }
    
    /**
     * 检查监听器是否启用
     * @return 是否启用
     */
    default boolean isEnabled() {
        return true;
    }
    
    /**
     * 检查是否关心指定配置项的变更
     * @param key 配置键
     * @return 是否关心
     */
    default boolean caresAbout(String key) {
        return true;
    }
}