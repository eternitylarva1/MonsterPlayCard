package EveryMonsterPlayCard.conversion.analyzer;

import java.lang.reflect.Field;
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
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 动作参数提取器
 * 负责从动作中提取详细参数信息
 */
public class ActionParameterExtractor {
    
    /**
     * 提取动作的所有参数
     * @param action 游戏动作
     * @return 参数映射
     */
    public ActionParameters extractAllParameters(AbstractGameAction action) {
        ActionParameters params = new ActionParameters();
        
        if (action == null) {
            return params;
        }
        
        // 基础参数
        params.set("actionClass", action.getClass().getSimpleName());
        params.set("actionType", action.actionType.toString());
        params.set("amount", action.amount);
        // 使用反射获取受保护的字段
        try {
            java.lang.reflect.Field durationField = AbstractGameAction.class.getDeclaredField("duration");
            durationField.setAccessible(true);
            params.set("duration", durationField.get(action));
        } catch (Exception e) {
            params.set("duration", 0.0f); // 默认值
        }
        params.set("isDone", action.isDone);
        
        // 源和目标信息
        if (action.source != null) {
            params.set("sourceName", action.source.name);
            params.set("sourceIsPlayer", action.source.isPlayer);
            params.set("sourceCurrentHealth", action.source.currentHealth);
            params.set("sourceMaxHealth", action.source.maxHealth);
            params.set("sourceCurrentBlock", action.source.currentBlock);
            params.set("sourceHasPower", !action.source.powers.isEmpty());
        }
        
        if (action.target != null) {
            params.set("targetName", action.target.name);
            params.set("targetIsPlayer", action.target.isPlayer);
            params.set("targetCurrentHealth", action.target.currentHealth);
            params.set("targetMaxHealth", action.target.maxHealth);
            params.set("targetCurrentBlock", action.target.currentBlock);
            params.set("targetHasPower", !action.target.powers.isEmpty());
        }
        
        // 特定动作类型的参数
        extractSpecificParameters(action, params);
        
        return params;
    }
    
    /**
     * 提取特定动作类型的参数
     * @param action 游戏动作
     * @param params 参数映射
     */
    private void extractSpecificParameters(AbstractGameAction action, ActionParameters params) {
        if (action instanceof DamageAction) {
            extractDamageParameters((DamageAction) action, params);
        } else if (action instanceof GainBlockAction) {
            extractBlockParameters((GainBlockAction) action, params);
        } else if (action instanceof ApplyPowerAction) {
            extractPowerParameters((ApplyPowerAction) action, params);
        } else if (action instanceof DrawCardAction) {
            extractDrawParameters((DrawCardAction) action, params);
        } else if (action instanceof GainEnergyAction) {
            extractEnergyParameters((GainEnergyAction) action, params);
        } else if (action instanceof HealAction) {
            extractHealParameters((HealAction) action, params);
        } else if (action instanceof DiscardAction) {
            extractDiscardParameters((DiscardAction) action, params);
        } else if (action instanceof ExhaustAction) {
            extractExhaustParameters((ExhaustAction) action, params);
        }
    }
    
    /**
     * 提取伤害动作参数
     * @param action 伤害动作
     * @param params 参数映射
     */
    private void extractDamageParameters(DamageAction action, ActionParameters params) {
        try {
            // 使用反射获取私有字段
            Field infoField = DamageAction.class.getDeclaredField("info");
            infoField.setAccessible(true);
            DamageInfo damageInfo = (DamageInfo) infoField.get(action);
            
            if (damageInfo != null) {
                params.set("damageOutput", damageInfo.output);
                params.set("damageType", damageInfo.type.toString());
                params.set("damageBase", damageInfo.base);
                
                if (damageInfo.owner != null) {
                    params.set("damageOwner", damageInfo.owner.name);
                    params.set("damageOwnerIsPlayer", damageInfo.owner.isPlayer);
                }
            }
            
            Field attackEffectField = DamageAction.class.getDeclaredField("attackEffect");
            attackEffectField.setAccessible(true);
            Object attackEffect = attackEffectField.get(action);
            if (attackEffect != null) {
                params.set("attackEffect", attackEffect.toString());
            }
            
        } catch (Exception e) {
            // 反射失败，使用公共字段
            params.set("damageAmount", action.amount);
        }
    }
    
    /**
     * 提取格挡动作参数
     * @param action 格挡动作
     * @param params 参数映射
     */
    private void extractBlockParameters(GainBlockAction action, ActionParameters params) {
        params.set("blockAmount", action.amount);
        // 格挡动作没有额外的私有参数
    }
    
    /**
     * 提取能力动作参数
     * @param action 能力动作
     * @param params 参数映射
     */
    private void extractPowerParameters(ApplyPowerAction action, ActionParameters params) {
        try {
            // 使用反射获取私有字段
            Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
            powerField.setAccessible(true);
            AbstractPower power = (AbstractPower) powerField.get(action);
            
            if (power != null) {
                params.set("powerId", power.ID);
                params.set("powerName", power.name);
                params.set("powerType", power.type.toString());
                params.set("powerAmount", power.amount);
                params.set("powerDescription", power.description);
                
                // 检查能力是否是堆叠的
                if (power.amount > 1) {
                    params.set("isStackablePower", true);
                } else {
                    params.set("isStackablePower", false);
                }
            }
            
            Field attackEffectField = ApplyPowerAction.class.getDeclaredField("attackEffect");
            attackEffectField.setAccessible(true);
            Object attackEffect = attackEffectField.get(action);
            if (attackEffect != null) {
                params.set("powerAttackEffect", attackEffect.toString());
            }
            
        } catch (Exception e) {
            // 反射失败，使用公共字段
            params.set("powerAmount", action.amount);
        }
    }
    
    /**
     * 提取抽牌动作参数
     * @param action 抽牌动作
     * @param params 参数映射
     */
    private void extractDrawParameters(DrawCardAction action, ActionParameters params) {
        params.set("drawAmount", action.amount);
        
        try {
            // 使用反射获取私有字段
            Field followUpField = DrawCardAction.class.getDeclaredField("followUpAction");
            followUpField.setAccessible(true);
            Object followUpAction = followUpField.get(action);
            if (followUpAction != null) {
                params.set("hasFollowUpAction", true);
                params.set("followUpActionClass", followUpAction.getClass().getSimpleName());
            } else {
                params.set("hasFollowUpAction", false);
            }
        } catch (Exception e) {
            // 反射失败，忽略
        }
    }
    
    /**
     * 提取能量动作参数
     * @param action 能量动作
     * @param params 参数映射
     */
    private void extractEnergyParameters(GainEnergyAction action, ActionParameters params) {
        try {
            // 使用反射获取私有字段
            Field energyField = GainEnergyAction.class.getDeclaredField("energyGain");
            energyField.setAccessible(true);
            Integer energyGain = (Integer) energyField.get(action);
            if (energyGain != null) {
                params.set("energyGain", energyGain);
            }
        } catch (Exception e) {
            // 反射失败，使用公共字段
            params.set("energyGain", action.amount);
        }
    }
    
    /**
     * 提取治疗动作参数
     * @param action 治疗动作
     * @param params 参数映射
     */
    private void extractHealParameters(HealAction action, ActionParameters params) {
        params.set("healAmount", action.amount);
        // 治疗动作没有额外的私有参数
    }
    
    /**
     * 提取弃牌动作参数
     * @param action 弃牌动作
     * @param params 参数映射
     */
    private void extractDiscardParameters(DiscardAction action, ActionParameters params) {
        params.set("discardAmount", action.amount);
        // 使用反射获取私有字段
        try {
            java.lang.reflect.Field isRandomField = DiscardAction.class.getDeclaredField("isRandom");
            isRandomField.setAccessible(true);
            params.set("isRandom", isRandomField.get(action));
        } catch (Exception e) {
            params.set("isRandom", false); // 默认值
        }
        
        try {
            java.lang.reflect.Field endTurnField = DiscardAction.class.getDeclaredField("endTurn");
            endTurnField.setAccessible(true);
            params.set("endTurn", endTurnField.get(action));
        } catch (Exception e) {
            params.set("endTurn", false); // 默认值
        }
    }
    
    /**
     * 提取消耗动作参数
     * @param action 消耗动作
     * @param params 参数映射
     */
    private void extractExhaustParameters(ExhaustAction action, ActionParameters params) {
        params.set("exhaustAmount", action.amount);
        // 使用反射获取私有字段
        try {
            java.lang.reflect.Field isRandomField = ExhaustAction.class.getDeclaredField("isRandom");
            isRandomField.setAccessible(true);
            params.set("isRandom", isRandomField.get(action));
        } catch (Exception e) {
            params.set("isRandom", false); // 默认值
        }
        
        // ExhaustAction没有exhaustPile字段，移除这行
        // params.set("exhaustPile", action.exhaustPile.toString());
    }
    
    /**
     * 提取动作序列的复合参数
     * @param actions 动作列表
     * @return 复合参数映射
     */
    public ActionParameters extractCompositeParameters(List<AbstractGameAction> actions) {
        ActionParameters params = new ActionParameters();
        
        if (actions == null || actions.isEmpty()) {
            params.set("totalActions", 0);
            return params;
        }
        
        params.set("totalActions", actions.size());
        
        // 统计各种动作类型的数量
        Map<String, Integer> actionCounts = new HashMap<>();
        int totalDamage = 0;
        int totalBlock = 0;
        int totalPower = 0;
        int totalDraw = 0;
        int totalEnergy = 0;
        int totalHeal = 0;
        
        for (AbstractGameAction action : actions) {
            String actionType = action.getClass().getSimpleName();
            actionCounts.put(actionType, actionCounts.getOrDefault(actionType, 0) + 1);
            
            if (action instanceof DamageAction) {
                totalDamage += action.amount;
            } else if (action instanceof GainBlockAction) {
                totalBlock += action.amount;
            } else if (action instanceof ApplyPowerAction) {
                totalPower += action.amount;
            } else if (action instanceof DrawCardAction) {
                totalDraw += action.amount;
            } else if (action instanceof GainEnergyAction) {
                totalEnergy += action.amount;
            } else if (action instanceof HealAction) {
                totalHeal += action.amount;
            }
        }
        
        // 设置统计参数
        params.set("actionCounts", actionCounts);
        params.set("totalDamage", totalDamage);
        params.set("totalBlock", totalBlock);
        params.set("totalPower", totalPower);
        params.set("totalDraw", totalDraw);
        params.set("totalEnergy", totalEnergy);
        params.set("totalHeal", totalHeal);
        
        // 计算复合指标
        params.set("hasMixedEffects", actionCounts.size() > 1);
        params.set("primaryEffectType", determinePrimaryEffectType(actionCounts));
        params.set("complexityScore", calculateComplexityScore(actionCounts));
        
        return params;
    }
    
    /**
     * 确定主要效果类型
     * @param actionCounts 动作计数映射
     * @return 主要效果类型
     */
    private String determinePrimaryEffectType(Map<String, Integer> actionCounts) {
        String primaryType = "UNKNOWN";
        int maxCount = 0;
        
        for (Map.Entry<String, Integer> entry : actionCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                primaryType = entry.getKey();
            }
        }
        
        return primaryType;
    }
    
    /**
     * 计算复杂度分数
     * @param actionCounts 动作计数映射
     * @return 复杂度分数
     */
    private int calculateComplexityScore(Map<String, Integer> actionCounts) {
        int score = 0;
        
        for (Map.Entry<String, Integer> entry : actionCounts.entrySet()) {
            String actionType = entry.getKey();
            int count = entry.getValue();
            
            // 不同动作类型的权重
            switch (actionType) {
                case "DamageAction":
                    score += count * 2;
                    break;
                case "ApplyPowerAction":
                    score += count * 3;
                    break;
                case "GainBlockAction":
                    score += count * 1;
                    break;
                case "DrawCardAction":
                    score += count * 1;
                    break;
                case "GainEnergyAction":
                    score += count * 1;
                    break;
                default:
                    score += count;
                    break;
            }
        }
        
        return score;
    }
}