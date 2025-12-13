package EveryMonsterPlayCard.core.interfaces;

import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.CardAttributes;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 意图到卡牌转换器接口
 * 负责将怪物意图转换为卡牌
 */
public interface IIntentToCardConverter {
    /**
     * 将意图转换为卡牌
     * @param intent 怪物意图
     * @return 生成的卡牌
     */
    AbstractCard convertIntent(MonsterIntent intent);
    
    /**
     * 推断卡牌属性
     * @param intent 怪物意图
     * @return 卡牌属性
     */
    CardAttributes inferAttributes(MonsterIntent intent);
    
    /**
     * 模拟卡牌效果
     * @param card 卡牌对象
     * @return 模拟的动作列表
     */
    List<AbstractGameAction> simulateEffects(AbstractCard card);
    
    /**
     * 优化卡牌
     * @param card 原始卡牌
     * @return 优化后的卡牌
     */
    AbstractCard optimizeCard(AbstractCard card);
    
    /**
     * 验证生成的卡牌
     * @param card 卡牌对象
     * @param originalIntent 原始意图
     * @return 验证结果
     */
    boolean validateCard(AbstractCard card, MonsterIntent originalIntent);
    
    /**
     * 检查是否可以处理指定意图
     * @param intent 怪物意图
     * @return 是否可以处理
     */
    boolean canHandle(MonsterIntent intent);
    
    /**
     * 获取支持的意图类型列表
     * @return 支持的意图类型列表
     */
    EveryMonsterPlayCard.core.data.IntentType[] getSupportedIntentTypes();
    
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
    
    /**
     * 计算生成质量分数
     * @param card 生成的卡牌
     * @param originalIntent 原始意图
     * @return 质量分数（0-10）
     */
    default double calculateQualityScore(AbstractCard card, MonsterIntent originalIntent) {
        // 默认基础质量分数计算
        double score = 5.0;
        
        if (card != null && originalIntent != null) {
            // 检查卡牌类型是否匹配意图类型
            if (isCardTypeMatchingIntent(card, originalIntent)) {
                score += 2.0;
            }
            
            // 检查卡牌属性是否合理
            if (areCardAttributesReasonable(card)) {
                score += 1.5;
            }
            
            // 检查卡牌效果是否匹配意图
            if (doCardEffectsMatchIntent(card, originalIntent)) {
                score += 1.5;
            }
        }
        
        return Math.min(10.0, Math.max(0.0, score));
    }
    
    /**
     * 检查卡牌类型是否匹配意图类型
     */
    default boolean isCardTypeMatchingIntent(AbstractCard card, MonsterIntent intent) {
        if (card == null || intent == null) return false;
        
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                return card.type == AbstractCard.CardType.ATTACK;
            case DEFEND:
                return card.type == AbstractCard.CardType.SKILL;
            case BUFF:
            case DEBUFF:
                return card.type == AbstractCard.CardType.SKILL || card.type == AbstractCard.CardType.POWER;
            default:
                return true;
        }
    }
    
    /**
     * 检查卡牌属性是否合理
     */
    default boolean areCardAttributesReasonable(AbstractCard card) {
        if (card == null) return false;
        
        // 检查费用是否合理
        if (card.cost < -2 || card.cost > 3) return false;
        
        // 检查伤害是否合理
        if (card.type == AbstractCard.CardType.ATTACK && card.baseDamage < 1) return false;
        
        // 检查格挡是否合理
        if (card.type == AbstractCard.CardType.SKILL && card.baseBlock < 1) return false;
        
        return true;
    }
    
    /**
     * 检查卡牌效果是否匹配意图
     */
    default boolean doCardEffectsMatchIntent(AbstractCard card, MonsterIntent intent) {
        // 默认实现，子类可以重写
        return true;
    }
}