package EveryMonsterPlayCard.conversion.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import EveryMonsterPlayCard.conversion.analyzer.ActionAnalyzer;
import EveryMonsterPlayCard.conversion.analyzer.ActionSequenceAnalysis;
import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 复杂动作处理器
 * 处理复杂的动作序列和组合动作
 */
public class ComplexActionHandler {
    private final ActionToIntentMapper baseMapper;
    private final ActionAnalyzer actionAnalyzer;
    private final Map<String, ComplexActionStrategy> strategies;
    
    public ComplexActionHandler() {
        this.baseMapper = new ActionToIntentMapper();
        this.actionAnalyzer = new ActionAnalyzer();
        this.strategies = new HashMap<>();
        initializeStrategies();
    }
    
    /**
     * 初始化复杂动作处理策略
     */
    private void initializeStrategies() {
        // 攻击防御组合策略
        strategies.put("ATTACK_DEFEND_COMBO", new AttackDefendComboStrategy());
        
        // 攻击能力组合策略
        strategies.put("ATTACK_POWER_COMBO", new AttackPowerComboStrategy());
        
        // 防御能力组合策略
        strategies.put("DEFEND_POWER_COMBO", new DefendPowerComboStrategy());
        
        // 多重攻击策略
        strategies.put("MULTI_ATTACK", new MultiAttackStrategy());
        
        // 多重防御策略
        strategies.put("MULTI_DEFEND", new MultiDefendStrategy());
        
        // 多重能力策略
        strategies.put("MULTI_POWER", new MultiPowerStrategy());
        
        // 混合动作策略
        strategies.put("MIXED_ACTIONS", new MixedActionsStrategy());
        
        // 循环动作策略
        strategies.put("LOOP_ACTIONS", new LoopActionsStrategy());
    }
    
    /**
     * 处理复杂动作序列
     * @param actions 动作序列
     * @return 处理后的意图列表
     */
    public List<MonsterIntent> handleComplexActions(List<AbstractGameAction> actions) {
        if (actions == null || actions.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 分析动作序列
        ActionSequenceAnalysis sequenceAnalysis = actionAnalyzer.analyzeActionSequence(actions);
        
        // 根据序列类型选择处理策略
        String sequenceType = sequenceAnalysis.getSequenceType();
        
        ComplexActionStrategy strategy = strategies.get(sequenceType);
        if (strategy != null) {
            return strategy.handle(actions, sequenceAnalysis, baseMapper);
        }
        
        // 如果没有特定策略，使用默认处理方式
        return handleDefaultComplexActions(actions);
    }
    
    /**
     * 默认复杂动作处理
     * @param actions 动作序列
     * @return 意图列表
     */
    private List<MonsterIntent> handleDefaultComplexActions(List<AbstractGameAction> actions) {
        List<MonsterIntent> intents = new ArrayList<>();
        
        // 尝试合并相同类型的动作
        Map<String, List<AbstractGameAction>> groupedActions = groupActionsByType(actions);
        
        for (Map.Entry<String, List<AbstractGameAction>> entry : groupedActions.entrySet()) {
            String actionType = entry.getKey();
            List<AbstractGameAction> typeActions = entry.getValue();
            
            if (typeActions.size() == 1) {
                // 单个动作，直接映射
                MonsterIntent intent = baseMapper.mapSingleAction(typeActions.get(0));
                if (intent != null) {
                    intents.add(intent);
                }
            } else {
                // 多个相同类型动作，尝试合并
                MonsterIntent mergedIntent = mergeSameTypeActions(actionType, typeActions);
                if (mergedIntent != null) {
                    intents.add(mergedIntent);
                } else {
                    // 无法合并，分别映射
                    for (AbstractGameAction action : typeActions) {
                        MonsterIntent intent = baseMapper.mapSingleAction(action);
                        if (intent != null) {
                            intents.add(intent);
                        }
                    }
                }
            }
        }
        
        return intents;
    }
    
    /**
     * 按类型分组动作
     * @param actions 动作列表
     * @return 分组后的动作映射
     */
    private Map<String, List<AbstractGameAction>> groupActionsByType(List<AbstractGameAction> actions) {
        Map<String, List<AbstractGameAction>> groupedActions = new HashMap<>();
        
        for (AbstractGameAction action : actions) {
            String actionType = action.getClass().getSimpleName();
            
            if (!groupedActions.containsKey(actionType)) {
                groupedActions.put(actionType, new ArrayList<>());
            }
            
            groupedActions.get(actionType).add(action);
        }
        
        return groupedActions;
    }
    
    /**
     * 合并相同类型的动作
     * @param actionType 动作类型
     * @param actions 动作列表
     * @return 合并后的意图，如果无法合并则返回null
     */
    private MonsterIntent mergeSameTypeActions(String actionType, List<AbstractGameAction> actions) {
        if (actions.isEmpty()) {
            return null;
        }
        
        switch (actionType) {
            case "DamageAction":
                return mergeDamageActions(actions);
            case "GainBlockAction":
                return mergeBlockActions(actions);
            case "ApplyPowerAction":
                return mergePowerActions(actions);
            case "DrawCardAction":
                return mergeDrawActions(actions);
            case "GainEnergyAction":
                return mergeEnergyActions(actions);
            case "HealAction":
                return mergeHealActions(actions);
            case "DiscardAction":
                return mergeDiscardActions(actions);
            case "ExhaustAction":
                return mergeExhaustActions(actions);
            default:
                return null; // 无法合并未知类型
        }
    }
    
    /**
     * 合并伤害动作
     * @param actions 伤害动作列表
     * @return 合并后的意图
     */
    private MonsterIntent mergeDamageActions(List<AbstractGameAction> actions) {
        int totalDamage = 0;
        AbstractCreature source = null;
        AbstractCreature target = null;
        
        for (AbstractGameAction action : actions) {
            if (action instanceof DamageAction) {
                totalDamage += action.amount;
                if (source == null) source = action.source;
                if (target == null) target = action.target;
            }
        }
        
        MonsterIntent intent = new MonsterIntent(IntentType.ATTACK, totalDamage);
        
        // 设置源和目标（需要类型检查和转换）
        if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
        }
        if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
        }
        
        // 设置元数据
        intent.getMetadata().setIntentId("MERGED_DAMAGE_" + totalDamage);
        intent.getMetadata().setDescription("合并造成 " + totalDamage + " 点伤害");
        intent.getMetadata().setPriority(5);
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 合并格挡动作
     * @param actions 格挡动作列表
     * @return 合并后的意图
     */
    private MonsterIntent mergeBlockActions(List<AbstractGameAction> actions) {
        int totalBlock = 0;
        AbstractCreature source = null;
        AbstractCreature target = null;
        
        for (AbstractGameAction action : actions) {
            if (action instanceof GainBlockAction) {
                totalBlock += action.amount;
                if (source == null) source = action.source;
                if (target == null) target = action.target;
            }
        }
        
        MonsterIntent intent = new MonsterIntent(IntentType.DEFEND, totalBlock);
        
        // 设置源和目标（需要类型检查和转换）
        if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
        }
        if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
        }
        
        // 设置元数据
        intent.getMetadata().setIntentId("MERGED_BLOCK_" + totalBlock);
        intent.getMetadata().setDescription("合并获得 " + totalBlock + " 点格挡");
        intent.getMetadata().setPriority(3);
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 合并能力动作
     * @param actions 能力动作列表
     * @return 合并后的意图
     */
    private MonsterIntent mergePowerActions(List<AbstractGameAction> actions) {
        int totalPower = 0;
        AbstractCreature source = null;
        AbstractCreature target = null;
        boolean hasDebuff = false;
        String powerId = null;
        
        for (AbstractGameAction action : actions) {
            if (action instanceof ApplyPowerAction) {
                totalPower += action.amount;
                if (source == null) source = action.source;
                if (target == null) target = action.target;
                
                // 检查是否有减益
                try {
                    java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
                    powerField.setAccessible(true);
                    AbstractPower power = (AbstractPower) powerField.get(action);
                    if (power != null) {
                        if (power.type == AbstractPower.PowerType.DEBUFF) {
                            hasDebuff = true;
                        }
                        if (powerId == null) {
                            powerId = power.ID;
                        }
                    }
                } catch (Exception e) {
                    // 忽略反射错误
                }
            }
        }
        
        IntentType intentType = hasDebuff ? IntentType.DEBUFF : IntentType.BUFF;
        
        MonsterIntent intent = new MonsterIntent(intentType, totalPower);
        
        // 设置源和目标（需要类型检查和转换）
        if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
        }
        if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
        }
        
        // 设置元数据
        String intentId = "MERGED_" + (hasDebuff ? "DEBUFF" : "BUFF") + "_" + totalPower;
        intent.getMetadata().setIntentId(intentId);
        intent.getMetadata().setDescription("合并" + (hasDebuff ? "施加" : "获得") + " " + totalPower + " 点" + (hasDebuff ? "减益" : "增益"));
        intent.getMetadata().setPriority(4);
        
        // 设置额外属性
        if (powerId != null) {
            intent.setProperty("powerId", powerId);
        }
        intent.setProperty("isDebuff", hasDebuff);
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 合并抽牌动作
     * @param actions 抽牌动作列表
     * @return 合并后的意图
     */
    private MonsterIntent mergeDrawActions(List<AbstractGameAction> actions) {
        int totalDraw = 0;
        AbstractCreature source = null;
        AbstractCreature target = null;
        
        for (AbstractGameAction action : actions) {
            if (action instanceof DrawCardAction) {
                totalDraw += action.amount;
                if (source == null) source = action.source;
                if (target == null) target = action.target;
            }
        }
        
        MonsterIntent intent = new MonsterIntent(IntentType.BUFF, totalDraw);
        
        // 设置源和目标（需要类型检查和转换）
        if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
        }
        if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
        }
        
        // 设置元数据
        intent.getMetadata().setIntentId("MERGED_DRAW_" + totalDraw);
        intent.getMetadata().setDescription("合并抽 " + totalDraw + " 张牌");
        intent.getMetadata().setPriority(2);
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 合并能量动作
     * @param actions 能量动作列表
     * @return 合并后的意图
     */
    private MonsterIntent mergeEnergyActions(List<AbstractGameAction> actions) {
        int totalEnergy = 0;
        AbstractCreature source = null;
        AbstractCreature target = null;
        
        for (AbstractGameAction action : actions) {
            if (action instanceof GainEnergyAction) {
                totalEnergy += action.amount;
                if (source == null) source = action.source;
                if (target == null) target = action.target;
            }
        }
        
        MonsterIntent intent = new MonsterIntent(IntentType.BUFF, totalEnergy);
        
        // 设置源和目标（需要类型检查和转换）
        if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
        }
        if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
        }
        
        // 设置元数据
        intent.getMetadata().setIntentId("MERGED_ENERGY_" + totalEnergy);
        intent.getMetadata().setDescription("合并获得 " + totalEnergy + " 点能量");
        intent.getMetadata().setPriority(2);
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 合并治疗动作
     * @param actions 治疗动作列表
     * @return 合并后的意图
     */
    private MonsterIntent mergeHealActions(List<AbstractGameAction> actions) {
        int totalHeal = 0;
        AbstractCreature source = null;
        AbstractCreature target = null;
        
        for (AbstractGameAction action : actions) {
            if (action instanceof HealAction) {
                totalHeal += action.amount;
                if (source == null) source = action.source;
                if (target == null) target = action.target;
            }
        }
        
        MonsterIntent intent = new MonsterIntent(IntentType.BUFF, totalHeal);
        
        // 设置源和目标（需要类型检查和转换）
        if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
        }
        if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
        }
        
        // 设置元数据
        intent.getMetadata().setIntentId("MERGED_HEAL_" + totalHeal);
        intent.getMetadata().setDescription("合并恢复 " + totalHeal + " 点生命值");
        intent.getMetadata().setPriority(3);
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 合并弃牌动作
     * @param actions 弃牌动作列表
     * @return 合并后的意图
     */
    private MonsterIntent mergeDiscardActions(List<AbstractGameAction> actions) {
        int totalDiscard = 0;
        AbstractCreature source = null;
        AbstractCreature target = null;
        
        for (AbstractGameAction action : actions) {
            if (action instanceof DiscardAction) {
                totalDiscard += action.amount;
                if (source == null) source = action.source;
                if (target == null) target = action.target;
            }
        }
        
        MonsterIntent intent = new MonsterIntent(IntentType.DEBUFF, totalDiscard);
        
        // 设置源和目标（需要类型检查和转换）
        if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
        }
        if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
        }
        
        // 设置元数据
        intent.getMetadata().setIntentId("MERGED_DISCARD_" + totalDiscard);
        intent.getMetadata().setDescription("合并弃掉 " + totalDiscard + " 张牌");
        intent.getMetadata().setPriority(2);
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 合并消耗动作
     * @param actions 消耗动作列表
     * @return 合并后的意图
     */
    private MonsterIntent mergeExhaustActions(List<AbstractGameAction> actions) {
        int totalExhaust = 0;
        AbstractCreature source = null;
        AbstractCreature target = null;
        
        for (AbstractGameAction action : actions) {
            if (action instanceof ExhaustAction) {
                totalExhaust += action.amount;
                if (source == null) source = action.source;
                if (target == null) target = action.target;
            }
        }
        
        MonsterIntent intent = new MonsterIntent(IntentType.DEBUFF, totalExhaust);
        
        // 设置源和目标（需要类型检查和转换）
        if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
        }
        if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
            intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
        }
        
        // 设置元数据
        intent.getMetadata().setIntentId("MERGED_EXHAUST_" + totalExhaust);
        intent.getMetadata().setDescription("合并消耗 " + totalExhaust + " 张牌");
        intent.getMetadata().setPriority(1);
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 复杂动作处理策略接口
     */
    public interface ComplexActionStrategy {
        /**
         * 处理复杂动作
         * @param actions 动作列表
         * @param sequenceAnalysis 动作序列分析结果
         * @param baseMapper 基础映射器
         * @return 处理后的意图列表
         */
        List<MonsterIntent> handle(List<AbstractGameAction> actions, 
                                 ActionSequenceAnalysis sequenceAnalysis,
                                 ActionToIntentMapper baseMapper);
    }
    
    /**
     * 攻击防御组合策略
     */
    private static class AttackDefendComboStrategy implements ComplexActionStrategy {
        @Override
        public List<MonsterIntent> handle(List<AbstractGameAction> actions, 
                                        ActionSequenceAnalysis sequenceAnalysis,
                                        ActionToIntentMapper baseMapper) {
            List<MonsterIntent> intents = new ArrayList<>();
            
            // 分别处理攻击和防御动作
            List<AbstractGameAction> attackActions = new ArrayList<>();
            List<AbstractGameAction> defendActions = new ArrayList<>();
            
            for (AbstractGameAction action : actions) {
                if (action instanceof DamageAction) {
                    attackActions.add(action);
                } else if (action instanceof GainBlockAction) {
                    defendActions.add(action);
                }
            }
            
            // 合并攻击动作
            if (!attackActions.isEmpty()) {
                MonsterIntent attackIntent = mergeDamageActions(attackActions);
                if (attackIntent != null) {
                    intents.add(attackIntent);
                }
            }
            
            // 合并防御动作
            if (!defendActions.isEmpty()) {
                MonsterIntent defendIntent = mergeBlockActions(defendActions);
                if (defendIntent != null) {
                    intents.add(defendIntent);
                }
            }
            
            return intents;
        }
        
        private MonsterIntent mergeDamageActions(List<AbstractGameAction> actions) {
            int totalDamage = 0;
            AbstractCreature source = null;
            AbstractCreature target = null;
            
            for (AbstractGameAction action : actions) {
                if (action instanceof DamageAction) {
                    totalDamage += action.amount;
                    if (source == null) source = action.source;
                    if (target == null) target = action.target;
                }
            }
            
            MonsterIntent intent = new MonsterIntent(IntentType.ATTACK, totalDamage);
            
            // 设置源和目标（需要类型检查和转换）
            if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
            }
            if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
            }
            
            // 设置元数据
            intent.getMetadata().setIntentId("ATTACK_DEFEND_COMBO_DAMAGE_" + totalDamage);
            intent.getMetadata().setDescription("攻击防御组合：造成 " + totalDamage + " 点伤害");
            intent.getMetadata().setPriority(5);
            
            // 添加所有动作到组件列表
            for (AbstractGameAction action : actions) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
        
        private MonsterIntent mergeBlockActions(List<AbstractGameAction> actions) {
            int totalBlock = 0;
            AbstractCreature source = null;
            AbstractCreature target = null;
            
            for (AbstractGameAction action : actions) {
                if (action instanceof GainBlockAction) {
                    totalBlock += action.amount;
                    if (source == null) source = action.source;
                    if (target == null) target = action.target;
                }
            }
            
            MonsterIntent intent = new MonsterIntent(IntentType.DEFEND, totalBlock);
            
            // 设置源和目标（需要类型检查和转换）
            if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
            }
            if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
            }
            
            // 设置元数据
            intent.getMetadata().setIntentId("ATTACK_DEFEND_COMBO_BLOCK_" + totalBlock);
            intent.getMetadata().setDescription("攻击防御组合：获得 " + totalBlock + " 点格挡");
            intent.getMetadata().setPriority(3);
            
            // 添加所有动作到组件列表
            for (AbstractGameAction action : actions) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
    }
    
    /**
     * 攻击能力组合策略
     */
    private static class AttackPowerComboStrategy implements ComplexActionStrategy {
        @Override
        public List<MonsterIntent> handle(List<AbstractGameAction> actions, 
                                        ActionSequenceAnalysis sequenceAnalysis,
                                        ActionToIntentMapper baseMapper) {
            List<MonsterIntent> intents = new ArrayList<>();
            
            // 分别处理攻击和能力动作
            List<AbstractGameAction> attackActions = new ArrayList<>();
            List<AbstractGameAction> powerActions = new ArrayList<>();
            
            for (AbstractGameAction action : actions) {
                if (action instanceof DamageAction) {
                    attackActions.add(action);
                } else if (action instanceof ApplyPowerAction) {
                    powerActions.add(action);
                }
            }
            
            // 合并攻击动作
            if (!attackActions.isEmpty()) {
                MonsterIntent attackIntent = mergeDamageActions(attackActions);
                if (attackIntent != null) {
                    intents.add(attackIntent);
                }
            }
            
            // 合并能力动作
            if (!powerActions.isEmpty()) {
                MonsterIntent powerIntent = mergePowerActions(powerActions);
                if (powerIntent != null) {
                    intents.add(powerIntent);
                }
            }
            
            return intents;
        }
        
        private MonsterIntent mergeDamageActions(List<AbstractGameAction> actions) {
            int totalDamage = 0;
            AbstractCreature source = null;
            AbstractCreature target = null;
            
            for (AbstractGameAction action : actions) {
                if (action instanceof DamageAction) {
                    totalDamage += action.amount;
                    if (source == null) source = action.source;
                    if (target == null) target = action.target;
                }
            }
            
            MonsterIntent intent = new MonsterIntent(IntentType.ATTACK, totalDamage);
            
            // 设置源和目标（需要类型检查和转换）
            if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
            }
            if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
            }
            
            // 设置元数据
            intent.getMetadata().setIntentId("ATTACK_POWER_COMBO_DAMAGE_" + totalDamage);
            intent.getMetadata().setDescription("攻击能力组合：造成 " + totalDamage + " 点伤害");
            intent.getMetadata().setPriority(5);
            
            // 添加所有动作到组件列表
            for (AbstractGameAction action : actions) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
        
        private MonsterIntent mergePowerActions(List<AbstractGameAction> actions) {
            int totalPower = 0;
            AbstractCreature source = null;
            AbstractCreature target = null;
            boolean hasDebuff = false;
            
            for (AbstractGameAction action : actions) {
                if (action instanceof ApplyPowerAction) {
                    totalPower += action.amount;
                    if (source == null) source = action.source;
                    if (target == null) target = action.target;
                    
                    // 检查是否有减益
                    try {
                        java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
                        powerField.setAccessible(true);
                        AbstractPower power = (AbstractPower) powerField.get(action);
                        if (power != null && power.type == AbstractPower.PowerType.DEBUFF) {
                            hasDebuff = true;
                        }
                    } catch (Exception e) {
                        // 忽略反射错误
                    }
                }
            }
            
            IntentType intentType = hasDebuff ? IntentType.DEBUFF : IntentType.BUFF;
            
            MonsterIntent intent = new MonsterIntent(intentType, totalPower);
            
            // 设置源和目标（需要类型检查和转换）
            if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
            }
            if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
            }
            
            // 设置元数据
            String intentId = "ATTACK_POWER_COMBO_" + (hasDebuff ? "DEBUFF" : "BUFF") + "_" + totalPower;
            intent.getMetadata().setIntentId(intentId);
            intent.getMetadata().setDescription("攻击能力组合：" + (hasDebuff ? "施加" : "获得") + " " + totalPower + " 点" + (hasDebuff ? "减益" : "增益"));
            intent.getMetadata().setPriority(4);
            
            // 添加所有动作到组件列表
            for (AbstractGameAction action : actions) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
    }
    
    /**
     * 防御能力组合策略
     */
    private static class DefendPowerComboStrategy implements ComplexActionStrategy {
        @Override
        public List<MonsterIntent> handle(List<AbstractGameAction> actions, 
                                        ActionSequenceAnalysis sequenceAnalysis,
                                        ActionToIntentMapper baseMapper) {
            List<MonsterIntent> intents = new ArrayList<>();
            
            // 分别处理防御和能力动作
            List<AbstractGameAction> defendActions = new ArrayList<>();
            List<AbstractGameAction> powerActions = new ArrayList<>();
            
            for (AbstractGameAction action : actions) {
                if (action instanceof GainBlockAction) {
                    defendActions.add(action);
                } else if (action instanceof ApplyPowerAction) {
                    powerActions.add(action);
                }
            }
            
            // 合并防御动作
            if (!defendActions.isEmpty()) {
                MonsterIntent defendIntent = mergeBlockActions(defendActions);
                if (defendIntent != null) {
                    intents.add(defendIntent);
                }
            }
            
            // 合并能力动作
            if (!powerActions.isEmpty()) {
                MonsterIntent powerIntent = mergePowerActions(powerActions);
                if (powerIntent != null) {
                    intents.add(powerIntent);
                }
            }
            
            return intents;
        }
        
        private MonsterIntent mergeBlockActions(List<AbstractGameAction> actions) {
            int totalBlock = 0;
            AbstractCreature source = null;
            AbstractCreature target = null;
            
            for (AbstractGameAction action : actions) {
                if (action instanceof GainBlockAction) {
                    totalBlock += action.amount;
                    if (source == null) source = action.source;
                    if (target == null) target = action.target;
                }
            }
            
            MonsterIntent intent = new MonsterIntent(IntentType.DEFEND, totalBlock);
            
            // 设置源和目标（需要类型检查和转换）
            if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
            }
            if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
            }
            
            // 设置元数据
            intent.getMetadata().setIntentId("DEFEND_POWER_COMBO_BLOCK_" + totalBlock);
            intent.getMetadata().setDescription("防御能力组合：获得 " + totalBlock + " 点格挡");
            intent.getMetadata().setPriority(3);
            
            // 添加所有动作到组件列表
            for (AbstractGameAction action : actions) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
        
        private MonsterIntent mergePowerActions(List<AbstractGameAction> actions) {
            int totalPower = 0;
            AbstractCreature source = null;
            AbstractCreature target = null;
            boolean hasDebuff = false;
            
            for (AbstractGameAction action : actions) {
                if (action instanceof ApplyPowerAction) {
                    totalPower += action.amount;
                    if (source == null) source = action.source;
                    if (target == null) target = action.target;
                    
                    // 检查是否有减益
                    try {
                        java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
                        powerField.setAccessible(true);
                        AbstractPower power = (AbstractPower) powerField.get(action);
                        if (power != null && power.type == AbstractPower.PowerType.DEBUFF) {
                            hasDebuff = true;
                        }
                    } catch (Exception e) {
                        // 忽略反射错误
                    }
                }
            }
            
            IntentType intentType = hasDebuff ? IntentType.DEBUFF : IntentType.BUFF;
            
            MonsterIntent intent = new MonsterIntent(intentType, totalPower);
            
            // 设置源和目标（需要类型检查和转换）
            if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
            }
            if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
            }
            
            // 设置元数据
            String intentId = "DEFEND_POWER_COMBO_" + (hasDebuff ? "DEBUFF" : "BUFF") + "_" + totalPower;
            intent.getMetadata().setIntentId(intentId);
            intent.getMetadata().setDescription("防御能力组合：" + (hasDebuff ? "施加" : "获得") + " " + totalPower + " 点" + (hasDebuff ? "减益" : "增益"));
            intent.getMetadata().setPriority(4);
            
            // 添加所有动作到组件列表
            for (AbstractGameAction action : actions) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
    }
    
    /**
     * 多重攻击策略
     */
    private static class MultiAttackStrategy implements ComplexActionStrategy {
        @Override
        public List<MonsterIntent> handle(List<AbstractGameAction> actions, 
                                        ActionSequenceAnalysis sequenceAnalysis,
                                        ActionToIntentMapper baseMapper) {
            List<MonsterIntent> intents = new ArrayList<>();
            
            // 合并所有攻击动作
            MonsterIntent mergedIntent = mergeDamageActions(actions);
            if (mergedIntent != null) {
                intents.add(mergedIntent);
            }
            
            return intents;
        }
        
        private MonsterIntent mergeDamageActions(List<AbstractGameAction> actions) {
            int totalDamage = 0;
            AbstractCreature source = null;
            AbstractCreature target = null;
            
            for (AbstractGameAction action : actions) {
                if (action instanceof DamageAction) {
                    totalDamage += action.amount;
                    if (source == null) source = action.source;
                    if (target == null) target = action.target;
                }
            }
            
            MonsterIntent intent = new MonsterIntent(IntentType.ATTACK, totalDamage);
            
            // 设置源和目标（需要类型检查和转换）
            if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
            }
            if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
            }
            
            // 设置元数据
            intent.getMetadata().setIntentId("MULTI_ATTACK_" + totalDamage);
            intent.getMetadata().setDescription("多重攻击：造成 " + totalDamage + " 点总伤害");
            intent.getMetadata().setPriority(5);
            
            // 添加所有动作到组件列表
            for (AbstractGameAction action : actions) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
    }
    
    /**
     * 多重防御策略
     */
    private static class MultiDefendStrategy implements ComplexActionStrategy {
        @Override
        public List<MonsterIntent> handle(List<AbstractGameAction> actions, 
                                        ActionSequenceAnalysis sequenceAnalysis,
                                        ActionToIntentMapper baseMapper) {
            List<MonsterIntent> intents = new ArrayList<>();
            
            // 合并所有防御动作
            MonsterIntent mergedIntent = mergeBlockActions(actions);
            if (mergedIntent != null) {
                intents.add(mergedIntent);
            }
            
            return intents;
        }
        
        private MonsterIntent mergeBlockActions(List<AbstractGameAction> actions) {
            int totalBlock = 0;
            AbstractCreature source = null;
            AbstractCreature target = null;
            
            for (AbstractGameAction action : actions) {
                if (action instanceof GainBlockAction) {
                    totalBlock += action.amount;
                    if (source == null) source = action.source;
                    if (target == null) target = action.target;
                }
            }
            
            MonsterIntent intent = new MonsterIntent(IntentType.DEFEND, totalBlock);
            
            // 设置源和目标（需要类型检查和转换）
            if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
            }
            if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
            }
            
            // 设置元数据
            intent.getMetadata().setIntentId("MULTI_DEFEND_" + totalBlock);
            intent.getMetadata().setDescription("多重防御：获得 " + totalBlock + " 点总格挡");
            intent.getMetadata().setPriority(3);
            
            // 添加所有动作到组件列表
            for (AbstractGameAction action : actions) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
    }
    
    /**
     * 多重能力策略
     */
    private static class MultiPowerStrategy implements ComplexActionStrategy {
        @Override
        public List<MonsterIntent> handle(List<AbstractGameAction> actions, 
                                        ActionSequenceAnalysis sequenceAnalysis,
                                        ActionToIntentMapper baseMapper) {
            List<MonsterIntent> intents = new ArrayList<>();
            
            // 合并所有能力动作
            MonsterIntent mergedIntent = mergePowerActions(actions);
            if (mergedIntent != null) {
                intents.add(mergedIntent);
            }
            
            return intents;
        }
        
        private MonsterIntent mergePowerActions(List<AbstractGameAction> actions) {
            int totalPower = 0;
            AbstractCreature source = null;
            AbstractCreature target = null;
            boolean hasDebuff = false;
            
            for (AbstractGameAction action : actions) {
                if (action instanceof ApplyPowerAction) {
                    totalPower += action.amount;
                    if (source == null) source = action.source;
                    if (target == null) target = action.target;
                    
                    // 检查是否有减益
                    try {
                        java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
                        powerField.setAccessible(true);
                        AbstractPower power = (AbstractPower) powerField.get(action);
                        if (power != null && power.type == AbstractPower.PowerType.DEBUFF) {
                            hasDebuff = true;
                        }
                    } catch (Exception e) {
                        // 忽略反射错误
                    }
                }
            }
            
            IntentType intentType = hasDebuff ? IntentType.DEBUFF : IntentType.BUFF;
            
            MonsterIntent intent = new MonsterIntent(intentType, totalPower);
            
            // 设置源和目标（需要类型检查和转换）
            if (source instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setSource((com.megacrit.cardcrawl.monsters.AbstractMonster) source);
            }
            if (target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster) {
                intent.setTarget((com.megacrit.cardcrawl.monsters.AbstractMonster) target);
            }
            
            // 设置元数据
            String intentId = "MULTI_POWER_" + (hasDebuff ? "DEBUFF" : "BUFF") + "_" + totalPower;
            intent.getMetadata().setIntentId(intentId);
            intent.getMetadata().setDescription("多重能力：" + (hasDebuff ? "施加" : "获得") + " " + totalPower + " 点总" + (hasDebuff ? "减益" : "增益"));
            intent.getMetadata().setPriority(4);
            
            // 添加所有动作到组件列表
            for (AbstractGameAction action : actions) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
    }
    
    /**
     * 混合动作策略
     */
    private static class MixedActionsStrategy implements ComplexActionStrategy {
        @Override
        public List<MonsterIntent> handle(List<AbstractGameAction> actions, 
                                        ActionSequenceAnalysis sequenceAnalysis,
                                        ActionToIntentMapper baseMapper) {
            List<MonsterIntent> intents = new ArrayList<>();
            
            // 分别映射每个动作
            for (AbstractGameAction action : actions) {
                MonsterIntent intent = baseMapper.mapSingleAction(action);
                if (intent != null) {
                    intents.add(intent);
                }
            }
            
            return intents;
        }
    }
    
    /**
     * 循环动作策略
     */
    private static class LoopActionsStrategy implements ComplexActionStrategy {
        @Override
        public List<MonsterIntent> handle(List<AbstractGameAction> actions, 
                                        ActionSequenceAnalysis sequenceAnalysis,
                                        ActionToIntentMapper baseMapper) {
            List<MonsterIntent> intents = new ArrayList<>();
            
            // 检查是否有循环模式（通过序列类型判断）
            if ("LOOP_ACTIONS".equals(sequenceAnalysis.getSequenceType())) {
                // 处理循环模式
                MonsterIntent patternIntent = createLoopPatternIntent(actions);
                if (patternIntent != null) {
                    intents.add(patternIntent);
                }
            } else {
                // 没有明确的循环模式，分别处理每个动作
                for (AbstractGameAction action : actions) {
                    MonsterIntent intent = baseMapper.mapSingleAction(action);
                    if (intent != null) {
                        intents.add(intent);
                    }
                }
            }
            
            return intents;
        }
        
        private MonsterIntent createLoopPatternIntent(List<AbstractGameAction> pattern) {
            // 分析循环模式的主要动作类型
            Map<String, Integer> actionTypeCount = new HashMap<>();
            
            for (AbstractGameAction action : pattern) {
                String actionType = action.getClass().getSimpleName();
                actionTypeCount.put(actionType, actionTypeCount.getOrDefault(actionType, 0) + 1);
            }
            
            // 找出最常见的动作类型
            String mostCommonType = null;
            int maxCount = 0;
            
            for (Map.Entry<String, Integer> entry : actionTypeCount.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostCommonType = entry.getKey();
                }
            }
            
            // 根据最常见的动作类型创建意图
            IntentType intentType;
            String description;
            
            switch (mostCommonType) {
                case "DamageAction":
                    intentType = IntentType.ATTACK;
                    description = "循环攻击模式";
                    break;
                case "GainBlockAction":
                    intentType = IntentType.DEFEND;
                    description = "循环防御模式";
                    break;
                case "ApplyPowerAction":
                    intentType = IntentType.BUFF;
                    description = "循环能力模式";
                    break;
                default:
                    intentType = IntentType.UNKNOWN;
                    description = "循环未知模式";
                    break;
            }
            
            MonsterIntent intent = new MonsterIntent(intentType, maxCount);
            
            // 设置元数据
            intent.getMetadata().setIntentId("LOOP_PATTERN_" + mostCommonType);
            intent.getMetadata().setDescription(description + "（重复 " + maxCount + " 次）");
            intent.getMetadata().setPriority(2);
            
            // 添加所有模式动作到组件列表
            for (AbstractGameAction action : pattern) {
                intent.addComponentAction(action);
            }
            
            return intent;
        }
    }
}