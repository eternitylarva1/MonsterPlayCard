package EveryMonsterPlayCard.core.interfaces;

import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 动作到意图转换器接口
 * 负责将游戏动作转换为怪物意图
 */
public interface IActionToIntentConverter {
    /**
     * 将单个动作转换为意图
     * @param action 游戏动作
     * @return 怪物意图
     */
    MonsterIntent convertAction(AbstractGameAction action);
    
    /**
     * 分解复杂动作为多个意图
     * @param action 复杂动作
     * @return 意图列表
     */
    List<MonsterIntent> decomposeComplexAction(AbstractGameAction action);
    
    /**
     * 分类意图类型
     * @param action 游戏动作
     * @return 意图类型
     */
    IntentType classifyIntent(AbstractGameAction action);
    
    /**
     * 计算意图强度
     * @param action 游戏动作
     * @return 意图强度值
     */
    int calculateIntentStrength(AbstractGameAction action);
    
    /**
     * 验证转换结果
     * @param action 原始动作
     * @param intent 转换后的意图
     * @return 验证结果
     */
    boolean validateConversion(AbstractGameAction action, MonsterIntent intent);
    
    /**
     * 检查是否可以处理指定动作
     * @param action 游戏动作
     * @return 是否可以处理
     */
    boolean canHandle(AbstractGameAction action);
    
    /**
     * 获取支持的动作类型列表
     * @return 支持的动作类型列表
     */
    Class<? extends AbstractGameAction>[] getSupportedActionTypes();
    
    /**
     * 获取转换器优先级
     * @return 优先级值（数值越小优先级越高）
     */
    default int getPriority() {
        return 0;
    }
    
    /**
     * 获取转换器名称
     * @return 转换器名称
     */
    default String getConverterName() {
        return this.getClass().getSimpleName();
    }
}