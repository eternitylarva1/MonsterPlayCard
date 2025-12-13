package EveryMonsterPlayCard.conversion.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;

import EveryMonsterPlayCard.core.data.IntentType;

/**
 * 动作分析器
 * 负责分析游戏动作，提取关键信息用于意图转换
 */
public class ActionAnalyzer {
    
    /**
     * 分析卡牌动作
     * @param card 卡牌对象
     * @return 动作分析结果
     */
    public ActionAnalysisResult analyzeCardActions(AbstractCard card) {
        ActionAnalysisResult result = new ActionAnalysisResult();
        result.setCard(card);
        
        // 这里需要实现卡牌动作分析逻辑
        // 由于卡牌动作分析比较复杂，这里先返回基础结果
        result.setActionCount(1);
        result.setPrimaryActionType(AbstractGameAction.ActionType.WAIT);
        
        return result;
    }
    
    /**
     * 分析单个动作
     * @param action 游戏动作
     * @return 单个动作分析结果
     */
    public SingleActionAnalysis analyzeAction(AbstractGameAction action) {
        SingleActionAnalysis analysis = new SingleActionAnalysis();
        analysis.setAction(action);
        
        // 分析动作类型
        analysis.setActionType(action.actionType);
        
        // 分析动作参数
        analysis.setAmount(action.amount);
        analysis.setSource(action.source);
        analysis.setTarget(action.target);
        
        // 分析动作效果
        analysis.setEffectType(analyzeActionEffect(action));
        
        // 提取动作参数
        analysis.setParameters(extractActionParameters(action));
        
        return analysis;
    }
    
    /**
     * 分析动作效果
     * @param action 游戏动作
     * @return 动作效果类型
     */
    public String analyzeActionEffect(AbstractGameAction action) {
        if (action instanceof DamageAction) {
            return "DAMAGE";
        } else if (action instanceof GainBlockAction) {
            return "BLOCK";
        } else if (action instanceof ApplyPowerAction) {
            ApplyPowerAction powerAction = (ApplyPowerAction) action;
            // 使用反射获取私有字段
            AbstractPower power = null;
            try {
                java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
                powerField.setAccessible(true);
                power = (AbstractPower) powerField.get(powerAction);
            } catch (Exception e) {
                // 如果反射失败，跳过此动作
            }
            if (power != null) {
                if (power.type == AbstractPower.PowerType.BUFF) {
                    return "BUFF";
                } else if (power.type == AbstractPower.PowerType.DEBUFF) {
                    return "DEBUFF";
                }
            }
            return "POWER";
        } else if (action instanceof DrawCardAction) {
            return "DRAW";
        } else if (action instanceof GainEnergyAction) {
            return "ENERGY";
        } else if (action instanceof HealAction) {
            return "HEAL";
        } else if (action instanceof DiscardAction) {
            return "DISCARD";
        } else if (action instanceof ExhaustAction) {
            return "EXHAUST";
        }
        
        return "UNKNOWN";
    }
    
    /**
     * 提取动作参数
     * @param action 游戏动作
     * @return 动作参数映射
     */
    public ActionParameters extractActionParameters(AbstractGameAction action) {
        ActionParameters params = new ActionParameters();
        
        // 基础参数
        params.set("amount", action.amount);
        params.set("actionType", action.actionType.toString());
        
        // 源和目标信息
        if (action.source != null) {
            params.set("sourceName", action.source.name);
            params.set("sourceIsPlayer", action.source.isPlayer);
        }
        
        if (action.target != null) {
            params.set("targetName", action.target.name);
            params.set("targetIsPlayer", action.target.isPlayer);
        }
        
        // 特定动作类型的额外参数
        if (action instanceof DamageAction) {
            DamageAction damageAction = (DamageAction) action;
            params.set("damageType", damageAction.damageType != null ? damageAction.damageType.toString() : "UNKNOWN");
            params.set("attackEffect", damageAction.attackEffect != null ? damageAction.attackEffect.toString() : "NONE");
        } else if (action instanceof ApplyPowerAction) {
            ApplyPowerAction powerAction = (ApplyPowerAction) action;
            // 使用反射获取私有字段
            AbstractPower power = null;
            try {
                java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
                powerField.setAccessible(true);
                power = (AbstractPower) powerField.get(powerAction);
            } catch (Exception e) {
                // 如果反射失败，跳过此动作
            }
            
            if (power != null) {
                params.set("powerId", power.ID);
                params.set("powerName", power.name);
                params.set("powerType", power.type != null ? power.type.toString() : "UNKNOWN");
            }
        }
        
        return params;
    }
    
    /**
     * 分析动作序列
     * @param actions 动作列表
     * @return 动作序列分析结果
     */
    public ActionSequenceAnalysis analyzeActionSequence(List<AbstractGameAction> actions) {
        ActionSequenceAnalysis sequenceAnalysis = new ActionSequenceAnalysis();
        sequenceAnalysis.setTotalActions(actions.size());
        
        List<SingleActionAnalysis> analyses = new ArrayList<>();
        for (AbstractGameAction action : actions) {
            analyses.add(analyzeAction(action));
        }
        sequenceAnalysis.setActionAnalyses(analyses);
        
        // 分析序列类型
        sequenceAnalysis.setSequenceType(determineSequenceType(analyses));
        
        return sequenceAnalysis;
    }
    
    /**
     * 确定动作序列类型
     * @param analyses 动作分析列表
     * @return 序列类型
     */
    private String determineSequenceType(List<SingleActionAnalysis> analyses) {
        if (analyses.isEmpty()) {
            return "EMPTY";
        }
        
        // 检查是否都是同一类型的动作
        String firstType = analyses.get(0).getEffectType();
        boolean allSame = true;
        for (SingleActionAnalysis analysis : analyses) {
            if (!analysis.getEffectType().equals(firstType)) {
                allSame = false;
                break;
            }
        }
        
        if (allSame) {
            return "UNIFORM_" + firstType;
        }
        
        // 检查是否是复合动作（攻击+防御等）
        boolean hasDamage = false;
        boolean hasBlock = false;
        boolean hasPower = false;
        
        for (SingleActionAnalysis analysis : analyses) {
            String effectType = analysis.getEffectType();
            if (effectType.equals("DAMAGE")) {
                hasDamage = true;
            } else if (effectType.equals("BLOCK")) {
                hasBlock = true;
            } else if (effectType.equals("BUFF") || effectType.equals("DEBUFF") || effectType.equals("POWER")) {
                hasPower = true;
            }
        }
        
        if (hasDamage && hasBlock) {
            return "MIXED_ATTACK_DEFEND";
        } else if (hasDamage && hasPower) {
            return "MIXED_ATTACK_POWER";
        } else if (hasBlock && hasPower) {
            return "MIXED_DEFEND_POWER";
        }
        
        return "MIXED";
    }
    
    /**
     * 预测动作的意图类型
     * @param action 游戏动作
     * @return 预测的意图类型
     */
    public IntentType predictIntentType(AbstractGameAction action) {
        String effectType = analyzeActionEffect(action);
        
        switch (effectType) {
            case "DAMAGE":
                return IntentType.ATTACK;
            case "BLOCK":
                return IntentType.DEFEND;
            case "BUFF":
                return IntentType.BUFF;
            case "DEBUFF":
                return IntentType.DEBUFF;
            case "DRAW":
            case "ENERGY":
            case "HEAL":
                return IntentType.BUFF; // 这些通常被视为增益
            case "DISCARD":
            case "EXHAUST":
                return IntentType.DEBUFF; // 这些通常被视为减益
            default:
                return IntentType.UNKNOWN;
        }
    }
    
    /**
     * 计算动作强度
     * @param action 游戏动作
     * @return 强度值
     */
    public int calculateActionStrength(AbstractGameAction action) {
        int baseStrength = action.amount;
        
        // 根据动作类型调整强度
        String effectType = analyzeActionEffect(action);
        switch (effectType) {
            case "DAMAGE":
                return baseStrength * 2; // 伤害动作强度更高
            case "DEBUFF":
                return baseStrength * 3; // 减益动作强度更高
            case "BUFF":
                return baseStrength * 2; // 增益动作强度中等
            case "BLOCK":
                return baseStrength; // 防御动作强度基础
            default:
                return baseStrength;
        }
    }
}
