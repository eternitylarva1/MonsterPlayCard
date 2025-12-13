package EveryMonsterPlayCard.intent2card.template;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;

/**
 * 卡牌模板接口
 * 定义了创建卡牌的基本方法
 */
public interface CardTemplate {
    
    /**
     * 根据意图和参数创建卡牌
     * @param intent 怪物意图
     * @param parameters 意图参数
     * @return 创建的卡牌
     */
    AbstractCard createCard(MonsterIntent intent, IntentParameters parameters);
    
    /**
     * 检查是否可以处理指定的意图
     * @param intent 怪物意图
     * @return 是否可以处理
     */
    boolean canHandle(MonsterIntent intent);
    
    /**
     * 获取模板名称
     * @return 模板名称
     */
    String getTemplateName();
    
    /**
     * 获取模板优先级
     * @return 优先级（数值越小优先级越高）
     */
    default int getTemplatePriority() {
        return 0;
    }
    
    /**
     * 获取支持的意图类型
     * @return 支持的意图类型数组
     */
    EveryMonsterPlayCard.core.data.IntentType[] getSupportedIntentTypes();
    
    /**
     * 验证参数是否有效
     * @param parameters 意图参数
     * @return 是否有效
     */
    default boolean validateParameters(IntentParameters parameters) {
        return parameters != null && !parameters.isEmpty();
    }
    
    /**
     * 计算模板匹配度
     * @param intent 怪物意图
     * @param parameters 意图参数
     * @return 匹配度（0-1）
     */
    default double calculateMatchScore(MonsterIntent intent, IntentParameters parameters) {
        if (!canHandle(intent) || !validateParameters(parameters)) {
            return 0.0;
        }
        
        double score = 0.5; // 基础分数
        
        // 根据意图类型匹配度加分
        if (isPrimaryIntentType(intent)) {
            score += 0.3;
        }
        
        // 根据参数完整性加分
        if (hasRequiredParameters(parameters)) {
            score += 0.2;
        }
        
        return Math.min(1.0, score);
    }
    
    /**
     * 检查是否为主要意图类型
     */
    default boolean isPrimaryIntentType(MonsterIntent intent) {
        EveryMonsterPlayCard.core.data.IntentType[] supportedTypes = getSupportedIntentTypes();
        for (EveryMonsterPlayCard.core.data.IntentType type : supportedTypes) {
            if (type == intent.getType()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 检查是否有必需的参数
     */
    default boolean hasRequiredParameters(IntentParameters parameters) {
        return parameters.hasParameter("intentType") && 
               parameters.hasParameter("intentAmount");
    }
    
    /**
     * 获取模板描述
     * @return 模板描述
     */
    default String getTemplateDescription() {
        return "基础卡牌模板";
    }
    
    /**
     * 获取模板版本
     * @return 模板版本
     */
    default String getTemplateVersion() {
        return "1.0.0";
    }
}