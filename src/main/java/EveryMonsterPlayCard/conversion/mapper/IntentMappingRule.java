package EveryMonsterPlayCard.conversion.mapper;

import java.util.function.Predicate;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import EveryMonsterPlayCard.core.data.IntentType;

/**
 * 意图映射规则
 * 定义如何将游戏动作映射为怪物意图
 */
public class IntentMappingRule {
    private final String ruleId;
    private final Predicate<AbstractGameAction> condition;
    private final IntentType intentType;
    private final int priority;
    private final String description;
    
    /**
     * 构造函数
     * @param ruleId 规则ID
     * @param condition 匹配条件
     * @param intentType 意图类型
     * @param priority 优先级
     * @param description 规则描述
     */
    public IntentMappingRule(String ruleId, Predicate<AbstractGameAction> condition, 
                           IntentType intentType, int priority, String description) {
        this.ruleId = ruleId;
        this.condition = condition;
        this.intentType = intentType;
        this.priority = priority;
        this.description = description;
    }
    
    /**
     * 检查动作是否匹配此规则
     * @param action 游戏动作
     * @return 是否匹配
     */
    public boolean matches(AbstractGameAction action) {
        return condition.test(action);
    }
    
    /**
     * 获取规则ID
     * @return 规则ID
     */
    public String getRuleId() {
        return ruleId;
    }
    
    /**
     * 获取意图类型
     * @return 意图类型
     */
    public IntentType getIntentType() {
        return intentType;
    }
    
    /**
     * 获取优先级
     * @return 优先级
     */
    public int getPriority() {
        return priority;
    }
    
    /**
     * 获取规则描述
     * @return 规则描述
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 创建伤害动作映射规则
     * @param ruleId 规则ID
     * @param priority 优先级
     * @param description 规则描述
     * @return 映射规则
     */
    public static IntentMappingRule createDamageRule(String ruleId, int priority, String description) {
        return new IntentMappingRule(
            ruleId,
            action -> action.getClass().getSimpleName().equals("DamageAction"),
            IntentType.ATTACK,
            priority,
            description
        );
    }
    
    /**
     * 创建格挡动作映射规则
     * @param ruleId 规则ID
     * @param priority 优先级
     * @param description 规则描述
     * @return 映射规则
     */
    public static IntentMappingRule createBlockRule(String ruleId, int priority, String description) {
        return new IntentMappingRule(
            ruleId,
            action -> action.getClass().getSimpleName().equals("GainBlockAction"),
            IntentType.DEFEND,
            priority,
            description
        );
    }
    
    /**
     * 创建能力动作映射规则
     * @param ruleId 规则ID
     * @param priority 优先级
     * @param description 规则描述
     * @return 映射规则
     */
    public static IntentMappingRule createPowerRule(String ruleId, int priority, String description) {
        return new IntentMappingRule(
            ruleId,
            action -> action.getClass().getSimpleName().equals("ApplyPowerAction"),
            IntentType.BUFF,
            priority,
            description
        );
    }
    
    /**
     * 创建抽牌动作映射规则
     * @param ruleId 规则ID
     * @param priority 优先级
     * @param description 规则描述
     * @return 映射规则
     */
    public static IntentMappingRule createDrawRule(String ruleId, int priority, String description) {
        return new IntentMappingRule(
            ruleId,
            action -> action.getClass().getSimpleName().equals("DrawCardAction"),
            IntentType.BUFF,
            priority,
            description
        );
    }
    
    /**
     * 创建能量动作映射规则
     * @param ruleId 规则ID
     * @param priority 优先级
     * @param description 规则描述
     * @return 映射规则
     */
    public static IntentMappingRule createEnergyRule(String ruleId, int priority, String description) {
        return new IntentMappingRule(
            ruleId,
            action -> action.getClass().getSimpleName().equals("GainEnergyAction"),
            IntentType.BUFF,
            priority,
            description
        );
    }
    
    /**
     * 创建治疗动作映射规则
     * @param ruleId 规则ID
     * @param priority 优先级
     * @param description 规则描述
     * @return 映射规则
     */
    public static IntentMappingRule createHealRule(String ruleId, int priority, String description) {
        return new IntentMappingRule(
            ruleId,
            action -> action.getClass().getSimpleName().equals("HealAction"),
            IntentType.BUFF,
            priority,
            description
        );
    }
    
    /**
     * 创建弃牌动作映射规则
     * @param ruleId 规则ID
     * @param priority 优先级
     * @param description 规则描述
     * @return 映射规则
     */
    public static IntentMappingRule createDiscardRule(String ruleId, int priority, String description) {
        return new IntentMappingRule(
            ruleId,
            action -> action.getClass().getSimpleName().equals("DiscardAction"),
            IntentType.DEBUFF,
            priority,
            description
        );
    }
    
    /**
     * 创建消耗动作映射规则
     * @param ruleId 规则ID
     * @param priority 优先级
     * @param description 规则描述
     * @return 映射规则
     */
    public static IntentMappingRule createExhaustRule(String ruleId, int priority, String description) {
        return new IntentMappingRule(
            ruleId,
            action -> action.getClass().getSimpleName().equals("ExhaustAction"),
            IntentType.DEBUFF,
            priority,
            description
        );
    }
    
    /**
     * 创建自定义动作映射规则
     * @param ruleId 规则ID
     * @param condition 匹配条件
     * @param intentType 意图类型
     * @param priority 优先级
     * @param description 规则描述
     * @return 映射规则
     */
    public static IntentMappingRule createCustomRule(String ruleId, Predicate<AbstractGameAction> condition,
                                                   IntentType intentType, int priority, String description) {
        return new IntentMappingRule(ruleId, condition, intentType, priority, description);
    }
}