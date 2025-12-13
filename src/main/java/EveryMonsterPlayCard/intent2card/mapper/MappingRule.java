package EveryMonsterPlayCard.intent2card.mapper;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 映射规则接口
 * 定义了将意图映射到卡牌的规则
 */
public interface MappingRule {
    
    /**
     * 检查规则是否匹配指定的意图
     * @param intent 怪物意图
     * @return 是否匹配
     */
    boolean matches(MonsterIntent intent);
    
    /**
     * 应用规则，将意图转换为卡牌
     * @param intent 怪物意图
     * @return 生成的卡牌
     */
    AbstractCard apply(MonsterIntent intent);
    
    /**
     * 获取规则名称
     * @return 规则名称
     */
    String getRuleName();
    
    /**
     * 获取规则优先级
     * @return 优先级（数值越小优先级越高）
     */
    default int getPriority() {
        return 0;
    }
    
    /**
     * 获取规则描述
     * @return 规则描述
     */
    default String getDescription() {
        return "未描述的映射规则";
    }
    
    /**
     * 检查规则是否启用
     * @return 是否启用
     */
    default boolean isEnabled() {
        return true;
    }
    
    /**
     * 获取规则版本
     * @return 规则版本
     */
    default String getVersion() {
        return "1.0.0";
    }
    
    /**
     * 比较规则优先级
     * @param other 另一个规则
     * @return 比较结果
     */
    default int compareTo(MappingRule other) {
        if (other == null) {
            return -1;
        }
        return Integer.compare(this.getPriority(), other.getPriority());
    }
}