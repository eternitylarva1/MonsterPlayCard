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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import EveryMonsterPlayCard.conversion.analyzer.ActionAnalyzer;
import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 动作-意图映射器
 * 负责将游戏动作映射为怪物意图
 */
public class ActionToIntentMapper {
    private final ActionAnalyzer actionAnalyzer;
    private final Map<String, IntentMappingRule> mappingRules;
    private final Map<String, Integer> intentPriorityMap;
    
    public ActionToIntentMapper() {
        this.actionAnalyzer = new ActionAnalyzer();
        this.mappingRules = new HashMap<>();
        this.intentPriorityMap = new HashMap<>();
        initializeDefaultRules();
        initializePriorityMap();
    }
    
    /**
     * 映射攻击动作
     * @param action 伤害动作
     * @return 怪物意图
     */
    public MonsterIntent mapAttackAction(DamageAction action) {
        MonsterIntent intent = new MonsterIntent(IntentType.ATTACK, action.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        intent.getMetadata().setIntentId("ATTACK_" + action.amount);
        intent.getMetadata().setDescription("造成 " + action.amount + " 点伤害");
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("ATTACK", 5));
        
        // 设置额外属性
        intent.setProperty("damageType", getDamageType(action));
        intent.setProperty("attackEffect", getAttackEffect(action));
        
        return intent;
    }
    
    /**
     * 映射防御动作
     * @param action 格挡动作
     * @return 怪物意图
     */
    public MonsterIntent mapDefenseAction(GainBlockAction action) {
        MonsterIntent intent = new MonsterIntent(IntentType.DEFEND, action.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        intent.getMetadata().setIntentId("DEFEND_" + action.amount);
        intent.getMetadata().setDescription("获得 " + action.amount + " 点格挡");
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("DEFEND", 3));
        
        return intent;
    }
    
    /**
     * 映射能力动作
     * @param action 能力动作
     * @return 怪物意图
     */
    public MonsterIntent mapPowerAction(ApplyPowerAction action) {
        // 使用反射获取能力信息
        AbstractPower power = null;
        try {
            java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
            powerField.setAccessible(true);
            power = (AbstractPower) powerField.get(action);
        } catch (Exception e) {
            // 反射失败，使用默认处理
        }
        
        if (power == null) {
            // 创建未知意图
            MonsterIntent intent = new MonsterIntent(IntentType.UNKNOWN, 0);
            if (action.source instanceof AbstractMonster) {
                intent.setSource((AbstractMonster) action.source);
            }
            if (action.target instanceof AbstractMonster) {
                intent.setTarget((AbstractMonster) action.target);
            }
            intent.addComponentAction(action);
            intent.getMetadata().setIntentId("UNKNOWN_POWER");
            intent.getMetadata().setDescription("应用未知能力");
            intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("UNKNOWN", 1));
            return intent;
        }
        
        // 根据能力类型确定意图类型
        IntentType intentType = power.type == AbstractPower.PowerType.DEBUFF ? IntentType.DEBUFF : IntentType.BUFF;
        
        MonsterIntent intent = new MonsterIntent(intentType, power.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        String intentId = (intentType == IntentType.DEBUFF ? "DEBUFF_" : "BUFF_") + power.ID;
        intent.getMetadata().setIntentId(intentId);
        intent.getMetadata().setDescription((intentType == IntentType.DEBUFF ? "施加减益" : "施加增益") + ": " + power.name);
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault(intentType.toString(), 4));
        
        // 设置额外属性
        intent.setProperty("powerId", power.ID);
        intent.setProperty("powerName", power.name);
        intent.setProperty("powerType", power.type.toString());
        intent.setProperty("isDebuff", intentType == IntentType.DEBUFF);
        
        return intent;
    }
    
    /**
     * 映射抽牌动作
     * @param action 抽牌动作
     * @return 怪物意图
     */
    public MonsterIntent mapDrawAction(DrawCardAction action) {
        MonsterIntent intent = new MonsterIntent(IntentType.BUFF, action.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        intent.getMetadata().setIntentId("DRAW_" + action.amount);
        intent.getMetadata().setDescription("抽 " + action.amount + " 张牌");
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("DRAW", 2));
        
        return intent;
    }
    
    /**
     * 映射能量动作
     * @param action 能量动作
     * @return 怪物意图
     */
    public MonsterIntent mapEnergyAction(GainEnergyAction action) {
        MonsterIntent intent = new MonsterIntent(IntentType.BUFF, action.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        intent.getMetadata().setIntentId("ENERGY_" + action.amount);
        intent.getMetadata().setDescription("获得 " + action.amount + " 点能量");
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("ENERGY", 2));
        
        return intent;
    }
    
    /**
     * 映射治疗动作
     * @param action 治疗动作
     * @return 怪物意图
     */
    public MonsterIntent mapHealAction(HealAction action) {
        MonsterIntent intent = new MonsterIntent(IntentType.BUFF, action.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        intent.getMetadata().setIntentId("HEAL_" + action.amount);
        intent.getMetadata().setDescription("恢复 " + action.amount + " 点生命值");
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("HEAL", 3));
        
        return intent;
    }
    
    /**
     * 映射弃牌动作
     * @param action 弃牌动作
     * @return 怪物意图
     */
    public MonsterIntent mapDiscardAction(DiscardAction action) {
        MonsterIntent intent = new MonsterIntent(IntentType.DEBUFF, action.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        intent.getMetadata().setIntentId("DISCARD_" + action.amount);
        intent.getMetadata().setDescription("弃掉 " + action.amount + " 张牌");
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("DISCARD", 2));
        
        return intent;
    }
    
    /**
     * 映射消耗动作
     * @param action 消耗动作
     * @return 怪物意图
     */
    public MonsterIntent mapExhaustAction(ExhaustAction action) {
        MonsterIntent intent = new MonsterIntent(IntentType.DEBUFF, action.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        intent.getMetadata().setIntentId("EXHAUST_" + action.amount);
        intent.getMetadata().setDescription("消耗 " + action.amount + " 张牌");
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("EXHAUST", 1));
        
        return intent;
    }
    
    /**
     * 映射自定义动作
     * @param action 自定义动作
     * @return 怪物意图
     */
    public MonsterIntent mapCustomAction(AbstractGameAction action) {
        // 使用动作分析器来确定意图类型
        IntentType intentType = actionAnalyzer.predictIntentType(action);
        
        MonsterIntent intent = new MonsterIntent(intentType, action.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        String intentId = "CUSTOM_" + action.getClass().getSimpleName();
        intent.getMetadata().setIntentId(intentId);
        intent.getMetadata().setDescription("自定义动作: " + action.getClass().getSimpleName());
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("CUSTOM", 1));
        
        // 设置额外属性
        intent.setProperty("actionClass", action.getClass().getSimpleName());
        intent.setProperty("actionType", action.actionType.toString());
        
        return intent;
    }
    
    /**
     * 映射未知动作
     * @param action 未知动作
     * @return 怪物意图
     */
    public MonsterIntent mapUnknownAction(AbstractGameAction action) {
        MonsterIntent intent = new MonsterIntent(IntentType.UNKNOWN, action.amount);
        
        // 设置源和目标（需要类型检查和转换）
        if (action.source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) action.source);
        }
        if (action.target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) action.target);
        }
        
        // 添加动作到组件列表
        intent.addComponentAction(action);
        
        // 设置元数据
        intent.getMetadata().setIntentId("UNKNOWN_" + action.getClass().getSimpleName());
        intent.getMetadata().setDescription("未知动作: " + action.getClass().getSimpleName());
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("UNKNOWN", 0));
        
        return intent;
    }
    
    /**
     * 映射动作序列
     * @param actions 动作列表
     * @return 意图列表
     */
    public List<MonsterIntent> mapActionSequence(List<AbstractGameAction> actions) {
        List<MonsterIntent> intents = new ArrayList<>();
        
        if (actions == null || actions.isEmpty()) {
            return intents;
        }
        
        // 分析动作序列
        EveryMonsterPlayCard.conversion.analyzer.ActionSequenceAnalysis sequenceAnalysis = actionAnalyzer.analyzeActionSequence(actions);
        
        // 根据序列类型决定映射策略
        String sequenceType = sequenceAnalysis.getSequenceType();
        
        switch (sequenceType) {
            case "UNIFORM_DAMAGE":
                // 统一伤害动作，合并为单个意图
                intents.add(createUnifiedDamageIntent(actions));
                break;
            case "UNIFORM_BLOCK":
                // 统一格挡动作，合并为单个意图
                intents.add(createUnifiedBlockIntent(actions));
                break;
            case "UNIFORM_POWER":
                // 统一能力动作，合并为单个意图
                intents.add(createUnifiedPowerIntent(actions));
                break;
            case "MIXED_ATTACK_DEFEND":
                // 混合攻击防御，分别映射
                intents.addAll(mapMixedActions(actions));
                break;
            case "MIXED_ATTACK_POWER":
                // 混合攻击能力，分别映射
                intents.addAll(mapMixedActions(actions));
                break;
            case "MIXED_DEFEND_POWER":
                // 混合防御能力，分别映射
                intents.addAll(mapMixedActions(actions));
                break;
            default:
                // 其他情况，分别映射每个动作
                intents.addAll(mapMixedActions(actions));
                break;
        }
        
        return intents;
    }
    
    /**
     * 创建统一伤害意图
     * @param actions 伤害动作列表
     * @return 统一伤害意图
     */
    private MonsterIntent createUnifiedDamageIntent(List<AbstractGameAction> actions) {
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
        if (source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) source);
        }
        if (target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) target);
        }
        
        // 设置元数据
        intent.getMetadata().setIntentId("UNIFIED_DAMAGE_" + totalDamage);
        intent.getMetadata().setDescription("造成 " + totalDamage + " 点总伤害");
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("ATTACK", 5));
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 创建统一格挡意图
     * @param actions 格挡动作列表
     * @return 统一格挡意图
     */
    private MonsterIntent createUnifiedBlockIntent(List<AbstractGameAction> actions) {
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
        if (source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) source);
        }
        if (target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) target);
        }
        
        // 设置元数据
        intent.getMetadata().setIntentId("UNIFIED_BLOCK_" + totalBlock);
        intent.getMetadata().setDescription("获得 " + totalBlock + " 点总格挡");
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault("DEFEND", 3));
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 创建统一能力意图
     * @param actions 能力动作列表
     * @return 统一能力意图
     */
    private MonsterIntent createUnifiedPowerIntent(List<AbstractGameAction> actions) {
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
        if (source instanceof AbstractMonster) {
            intent.setSource((AbstractMonster) source);
        }
        if (target instanceof AbstractMonster) {
            intent.setTarget((AbstractMonster) target);
        }
        
        // 设置元数据
        String intentId = "UNIFIED_" + (hasDebuff ? "DEBUFF" : "BUFF") + "_" + totalPower;
        intent.getMetadata().setIntentId(intentId);
        intent.getMetadata().setDescription((hasDebuff ? "施加" : "获得") + " " + totalPower + " 点总" + (hasDebuff ? "减益" : "增益"));
        intent.getMetadata().setPriority(intentPriorityMap.getOrDefault(intentType.toString(), 4));
        
        // 添加所有动作到组件列表
        for (AbstractGameAction action : actions) {
            intent.addComponentAction(action);
        }
        
        return intent;
    }
    
    /**
     * 映射混合动作
     * @param actions 动作列表
     * @return 意图列表
     */
    private List<MonsterIntent> mapMixedActions(List<AbstractGameAction> actions) {
        List<MonsterIntent> intents = new ArrayList<>();
        
        for (AbstractGameAction action : actions) {
            MonsterIntent intent = mapSingleAction(action);
            if (intent != null) {
                intents.add(intent);
            }
        }
        
        return intents;
    }
    
    /**
     * 映射单个动作
     * @param action 游戏动作
     * @return 怪物意图
     */
    public MonsterIntent mapSingleAction(AbstractGameAction action) {
        if (action instanceof DamageAction) {
            return mapAttackAction((DamageAction) action);
        } else if (action instanceof GainBlockAction) {
            return mapDefenseAction((GainBlockAction) action);
        } else if (action instanceof ApplyPowerAction) {
            return mapPowerAction((ApplyPowerAction) action);
        } else if (action instanceof DrawCardAction) {
            return mapDrawAction((DrawCardAction) action);
        } else if (action instanceof GainEnergyAction) {
            return mapEnergyAction((GainEnergyAction) action);
        } else if (action instanceof HealAction) {
            return mapHealAction((HealAction) action);
        } else if (action instanceof DiscardAction) {
            return mapDiscardAction((DiscardAction) action);
        } else if (action instanceof ExhaustAction) {
            return mapExhaustAction((ExhaustAction) action);
        } else {
            return mapCustomAction(action);
        }
    }
    
    /**
     * 获取伤害类型
     * @param action 伤害动作
     * @return 伤害类型字符串
     */
    private String getDamageType(DamageAction action) {
        try {
            java.lang.reflect.Field infoField = DamageAction.class.getDeclaredField("info");
            infoField.setAccessible(true);
            Object damageInfo = infoField.get(action);
            
            if (damageInfo != null) {
                java.lang.reflect.Field typeField = damageInfo.getClass().getDeclaredField("type");
                typeField.setAccessible(true);
                Object type = typeField.get(damageInfo);
                if (type != null) {
                    return type.toString();
                }
            }
        } catch (Exception e) {
            // 反射失败，返回默认值
        }
        
        return "NORMAL";
    }
    
    /**
     * 获取攻击效果
     * @param action 伤害动作
     * @return 攻击效果字符串
     */
    private String getAttackEffect(DamageAction action) {
        if (action.attackEffect != null) {
            return action.attackEffect.toString();
        }
        return "NONE";
    }
    
    /**
     * 初始化默认映射规则
     */
    private void initializeDefaultRules() {
        // 这里可以添加自定义映射规则
        // 目前使用内置逻辑
    }
    
    /**
     * 初始化优先级映射
     */
    private void initializePriorityMap() {
        intentPriorityMap.put("ATTACK", 5);
        intentPriorityMap.put("DEFEND", 3);
        intentPriorityMap.put("BUFF", 4);
        intentPriorityMap.put("DEBUFF", 4);
        intentPriorityMap.put("DRAW", 2);
        intentPriorityMap.put("ENERGY", 2);
        intentPriorityMap.put("HEAL", 3);
        intentPriorityMap.put("DISCARD", 2);
        intentPriorityMap.put("EXHAUST", 1);
        intentPriorityMap.put("UNKNOWN", 0);
        intentPriorityMap.put("CUSTOM", 1);
    }
}