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
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 动作效果分析器
 * 负责分析动作的具体效果
 */
public class ActionEffectAnalyzer {
    
    /**
     * 分析动作效果
     * @param action 游戏动作
     * @return 效果分析结果
     */
    public EffectAnalysisResult analyzeEffect(AbstractGameAction action) {
        EffectAnalysisResult result = new EffectAnalysisResult();
        result.setAction(action);
        
        if (action == null) {
            result.setEffectType("UNKNOWN");
            result.setEffectDescription("动作为空");
            return result;
        }
        
        // 根据动作类型分析效果
        if (action instanceof DamageAction) {
            result.setEffectType("DAMAGE");
            result.setEffectDescription("造成伤害");
            result.setEffectMagnitude(analyzeDamageMagnitude((DamageAction) action));
            result.setTargetType("ENEMY");
        } else if (action instanceof GainBlockAction) {
            result.setEffectType("BLOCK");
            result.setEffectDescription("获得格挡");
            result.setEffectMagnitude(analyzeBlockMagnitude((GainBlockAction) action));
            result.setTargetType("SELF");
        } else if (action instanceof ApplyPowerAction) {
            ApplyPowerAction powerAction = (ApplyPowerAction) action;
            result.setEffectType("POWER");
            result.setEffectDescription("应用能力");
            result.setEffectMagnitude(analyzePowerMagnitude(powerAction));
            result.setTargetType(analyzePowerTargetType(powerAction));
        } else if (action instanceof DrawCardAction) {
            result.setEffectType("DRAW");
            result.setEffectDescription("抽牌");
            result.setEffectMagnitude(analyzeDrawMagnitude((DrawCardAction) action));
            result.setTargetType("SELF");
        } else if (action instanceof GainEnergyAction) {
            result.setEffectType("ENERGY");
            result.setEffectDescription("获得能量");
            result.setEffectMagnitude(analyzeEnergyMagnitude((GainEnergyAction) action));
            result.setTargetType("SELF");
        } else if (action instanceof HealAction) {
            result.setEffectType("HEAL");
            result.setEffectDescription("治疗");
            result.setEffectMagnitude(analyzeHealMagnitude((HealAction) action));
            result.setTargetType(analyzeHealTargetType((HealAction) action));
        } else if (action instanceof DiscardAction) {
            result.setEffectType("DISCARD");
            result.setEffectDescription("弃牌");
            result.setEffectMagnitude(analyzeDiscardMagnitude((DiscardAction) action));
            result.setTargetType("SELF");
        } else if (action instanceof ExhaustAction) {
            result.setEffectType("EXHAUST");
            result.setEffectDescription("消耗");
            result.setEffectMagnitude(analyzeExhaustMagnitude((ExhaustAction) action));
            result.setTargetType("SELF");
        } else {
            result.setEffectType("UNKNOWN");
            result.setEffectDescription("未知动作类型: " + action.getClass().getSimpleName());
            result.setEffectMagnitude(0);
            result.setTargetType("UNKNOWN");
        }
        
        return result;
    }
    
    /**
     * 分析伤害幅度
     * @param action 伤害动作
     * @return 伤害幅度
     */
    private int analyzeDamageMagnitude(DamageAction action) {
        // 使用反射获取伤害信息
        try {
            java.lang.reflect.Field damageField = DamageAction.class.getDeclaredField("info");
            damageField.setAccessible(true);
            Object damageInfo = damageField.get(action);
            
            if (damageInfo != null) {
                // 尝试获取伤害值
                java.lang.reflect.Field outputField = damageInfo.getClass().getDeclaredField("output");
                outputField.setAccessible(true);
                return (Integer) outputField.get(damageInfo);
            }
        } catch (Exception e) {
            // 如果反射失败，使用基础值
        }
        
        return action.amount;
    }
    
    /**
     * 分析格挡幅度
     * @param action 格挡动作
     * @return 格挡幅度
     */
    private int analyzeBlockMagnitude(GainBlockAction action) {
        return action.amount;
    }
    
    /**
     * 分析能力幅度
     * @param action 能力动作
     * @return 能力幅度
     */
    private int analyzePowerMagnitude(ApplyPowerAction action) {
        // 使用反射获取能力信息
        try {
            java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
            powerField.setAccessible(true);
            AbstractPower power = (AbstractPower) powerField.get(action);
            
            if (power != null) {
                return power.amount;
            }
        } catch (Exception e) {
            // 如果反射失败，使用基础值
        }
        
        return action.amount;
    }
    
    /**
     * 分析能力目标类型
     * @param action 能力动作
     * @return 目标类型
     */
    private String analyzePowerTargetType(ApplyPowerAction action) {
        // 使用反射获取能力信息
        try {
            java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
            powerField.setAccessible(true);
            AbstractPower power = (AbstractPower) powerField.get(action);
            
            if (power != null) {
                if (power.type == AbstractPower.PowerType.DEBUFF) {
                    return "ENEMY";
                } else {
                    return "SELF";
                }
            }
        } catch (Exception e) {
            // 如果反射失败，使用默认值
        }
        
        return "UNKNOWN";
    }
    
    /**
     * 分析抽牌幅度
     * @param action 抽牌动作
     * @return 抽牌幅度
     */
    private int analyzeDrawMagnitude(DrawCardAction action) {
        return action.amount;
    }
    
    /**
     * 分析能量幅度
     * @param action 能量动作
     * @return 能量幅度
     */
    private int analyzeEnergyMagnitude(GainEnergyAction action) {
        // 使用反射获取能量信息
        try {
            java.lang.reflect.Field energyField = GainEnergyAction.class.getDeclaredField("energyGain");
            energyField.setAccessible(true);
            return (Integer) energyField.get(action);
        } catch (Exception e) {
            // 如果反射失败，使用基础值
        }
        
        return action.amount;
    }
    
    /**
     * 分析治疗幅度
     * @param action 治疗动作
     * @return 治疗幅度
     */
    private int analyzeHealMagnitude(HealAction action) {
        return action.amount;
    }
    
    /**
     * 分析治疗目标类型
     * @param action 治疗动作
     * @return 目标类型
     */
    private String analyzeHealTargetType(HealAction action) {
        if (action.target != null) {
            return action.target.isPlayer ? "PLAYER" : "MONSTER";
        }
        return "UNKNOWN";
    }
    
    /**
     * 分析弃牌幅度
     * @param action 弃牌动作
     * @return 弃牌幅度
     */
    private int analyzeDiscardMagnitude(DiscardAction action) {
        return action.amount;
    }
    
    /**
     * 分析消耗幅度
     * @param action 消耗动作
     * @return 消耗幅度
     */
    private int analyzeExhaustMagnitude(ExhaustAction action) {
        return action.amount;
    }
    
    /**
     * 分析动作序列的复合效果
     * @param actions 动作列表
     * @return 复合效果分析结果
     */
    public CompositeEffectAnalysis analyzeCompositeEffects(List<AbstractGameAction> actions) {
        CompositeEffectAnalysis result = new CompositeEffectAnalysis();
        result.setTotalActions(actions.size());
        
        List<String> effectTypes = new ArrayList<>();
        int totalMagnitude = 0;
        String primaryTargetType = "UNKNOWN";
        
        for (AbstractGameAction action : actions) {
            EffectAnalysisResult effectResult = analyzeEffect(action);
            effectTypes.add(effectResult.getEffectType());
            totalMagnitude += effectResult.getEffectMagnitude();
            
            // 确定主要目标类型
            String targetType = effectResult.getTargetType();
            if (!targetType.equals("UNKNOWN") && primaryTargetType.equals("UNKNOWN")) {
                primaryTargetType = targetType;
            }
        }
        
        result.setEffectTypes(effectTypes);
        result.setTotalMagnitude(totalMagnitude);
        result.setPrimaryTargetType(primaryTargetType);
        
        // 分析复合类型
        result.setCompositeType(determineCompositeType(effectTypes));
        
        return result;
    }
    
    /**
     * 确定复合效果类型
     * @param effectTypes 效果类型列表
     * @return 复合类型
     */
    private String determineCompositeType(List<String> effectTypes) {
        if (effectTypes.isEmpty()) {
            return "EMPTY";
        }
        
        boolean hasDamage = effectTypes.contains("DAMAGE");
        boolean hasBlock = effectTypes.contains("BLOCK");
        boolean hasPower = effectTypes.contains("POWER");
        boolean hasDraw = effectTypes.contains("DRAW");
        boolean hasEnergy = effectTypes.contains("ENERGY");
        boolean hasHeal = effectTypes.contains("HEAL");
        boolean hasDiscard = effectTypes.contains("DISCARD");
        boolean hasExhaust = effectTypes.contains("EXHAUST");
        
        // 检查是否是单一效果
        if (effectTypes.size() == 1) {
            return "SINGLE_" + effectTypes.get(0);
        }
        
        // 检查常见组合
        if (hasDamage && hasBlock) {
            return "ATTACK_DEFEND";
        } else if (hasDamage && hasPower) {
            return "ATTACK_POWER";
        } else if (hasBlock && hasPower) {
            return "DEFEND_POWER";
        } else if (hasDraw && hasEnergy) {
            return "RESOURCE_GAIN";
        } else if (hasDiscard || hasExhaust) {
            return "RESOURCE_LOSS";
        }
        
        // 检查是否是混合效果
        int positiveCount = 0;
        int negativeCount = 0;
        
        if (hasDamage) positiveCount++;
        if (hasBlock) positiveCount++;
        if (hasPower) {
            // 需要进一步判断是增益还是减益
            // 这里简化处理，假设是增益
            positiveCount++;
        }
        if (hasDraw) positiveCount++;
        if (hasEnergy) positiveCount++;
        if (hasHeal) positiveCount++;
        
        if (hasDiscard) negativeCount++;
        if (hasExhaust) negativeCount++;
        
        if (positiveCount > 0 && negativeCount > 0) {
            return "MIXED_POSITIVE_NEGATIVE";
        } else if (positiveCount > 1) {
            return "MULTIPLE_POSITIVE";
        } else if (negativeCount > 1) {
            return "MULTIPLE_NEGATIVE";
        }
        
        return "COMPLEX";
    }
}