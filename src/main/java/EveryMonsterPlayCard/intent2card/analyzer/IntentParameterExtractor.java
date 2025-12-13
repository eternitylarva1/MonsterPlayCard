package EveryMonsterPlayCard.intent2card.analyzer;

import java.util.List;
import java.util.Map;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 意图参数提取器
 * 负责从怪物意图中提取详细的参数信息
 */
public class IntentParameterExtractor {
    
    /**
     * 提取意图参数
     */
    public IntentParameters extractParameters(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        
        if (intent == null) {
            return parameters;
        }
        
        // 提取基础参数
        extractBasicParameters(intent, parameters);
        
        // 从组件动作中提取参数
        extractFromComponentActions(intent, parameters);
        
        // 从属性中提取参数
        extractFromProperties(intent, parameters);
        
        // 从元数据中提取参数
        extractFromMetadata(intent, parameters);
        
        // 根据意图类型提取特定参数
        extractTypeSpecificParameters(intent, parameters);
        
        return parameters;
    }
    
    /**
     * 提取基础参数
     */
    private void extractBasicParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.setDamage(intent.getAmount());
        parameters.setCost(calculateBaseCost(intent));
        parameters.addParameter("intentType", intent.getType());
        parameters.addParameter("intentAmount", intent.getAmount());
        
        if (intent.getSource() != null) {
            parameters.addParameter("sourceMonster", intent.getSource().name);
        }
        
        if (intent.getTarget() != null) {
            parameters.addParameter("targetMonster", intent.getTarget().name);
        }
    }
    
    /**
     * 从组件动作中提取参数
     */
    private void extractFromComponentActions(MonsterIntent intent, IntentParameters parameters) {
        List<AbstractGameAction> actions = intent.getComponentActions();
        if (actions == null || actions.isEmpty()) {
            return;
        }
        
        parameters.addParameter("actionCount", actions.size());
        
        int totalDamage = 0;
        int totalBlock = 0;
        int powerApplications = 0;
        
        for (AbstractGameAction action : actions) {
            if (action instanceof DamageAction) {
                DamageAction damageAction = (DamageAction) action;
                // 使用反射访问私有字段
                try {
                    java.lang.reflect.Field damageField = DamageAction.class.getDeclaredField("damage");
                    damageField.setAccessible(true);
                    DamageInfo damageInfo = (DamageInfo) damageField.get(damageAction);
                    if (damageInfo != null) {
                        totalDamage += damageInfo.base;
                    }
                } catch (Exception e) {
                    // 忽略反射错误
                }
                parameters.addParameter("attackEffect", damageAction.attackEffect);
            } else if (action instanceof GainBlockAction) {
                GainBlockAction blockAction = (GainBlockAction) action;
                totalBlock += blockAction.amount;
            } else if (action instanceof ApplyPowerAction) {
                ApplyPowerAction powerAction = (ApplyPowerAction) action;
                powerApplications++;
                // 使用反射访问私有字段
                try {
                    java.lang.reflect.Field powerField = ApplyPowerAction.class.getDeclaredField("powerToApply");
                    powerField.setAccessible(true);
                    AbstractPower power = (AbstractPower) powerField.get(powerAction);
                    if (power != null) {
                        parameters.addParameter("powerType", power.ID);
                        parameters.addParameter("powerAmount", powerAction.amount);
                    }
                } catch (Exception e) {
                    // 忽略反射错误
                }
            }
        }
        
        if (totalDamage > 0) {
            parameters.setDamage(totalDamage);
        }
        if (totalBlock > 0) {
            parameters.setBlock(totalBlock);
        }
        if (powerApplications > 0) {
            parameters.addParameter("powerApplications", powerApplications);
        }
    }
    
    /**
     * 从属性中提取参数
     */
    private void extractFromProperties(MonsterIntent intent, IntentParameters parameters) {
        Map<String, Object> properties = intent.getProperties();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            parameters.addParameter(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * 从元数据中提取参数
     */
    private void extractFromMetadata(MonsterIntent intent, IntentParameters parameters) {
        if (intent.getMetadata() != null) {
            parameters.addParameter("intentId", intent.getMetadata().getIntentId());
            parameters.addParameter("description", intent.getMetadata().getDescription());
            parameters.addParameter("priority", intent.getMetadata().getPriority());
            parameters.addParameter("createTime", intent.getMetadata().getCreateTime());
            
            Map<String, Object> metadataProperties = intent.getMetadata().getProperties();
            for (Map.Entry<String, Object> entry : metadataProperties.entrySet()) {
                parameters.addParameter("meta_" + entry.getKey(), entry.getValue());
            }
        }
    }
    
    /**
     * 根据意图类型提取特定参数
     */
    private void extractTypeSpecificParameters(MonsterIntent intent, IntentParameters parameters) {
        switch (intent.getType()) {
            case ATTACK:
                extractAttackParameters(intent, parameters);
                break;
            case DEFEND:
                extractDefendParameters(intent, parameters);
                break;
            case BUFF:
                extractBuffParameters(intent, parameters);
                break;
            case DEBUFF:
                extractDebuffParameters(intent, parameters);
                break;
            case STRONG:
                extractStrongAttackParameters(intent, parameters);
                break;
            case ESCAPE:
                extractEscapeParameters(intent, parameters);
                break;
            case UNKNOWN:
                extractUnknownParameters(intent, parameters);
                break;
        }
    }
    
    /**
     * 提取攻击意图参数
     */
    private void extractAttackParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.addParameter("isAttack", true);
        parameters.addParameter("cardType", "ATTACK");
        
        // 检查是否有特殊攻击效果
        if (intent.hasProperty("attackEffect")) {
            parameters.addParameter("attackEffect", intent.getProperty("attackEffect"));
        }
        
        // 检查是否有多次攻击
        if (intent.hasProperty("hitCount")) {
            parameters.addParameter("hitCount", intent.getIntParameter("hitCount"));
        } else {
            parameters.addParameter("hitCount", 1);
        }
        
        // 计算单次伤害
        int hitCount = parameters.getIntParameter("hitCount");
        if (hitCount > 1) {
            parameters.addParameter("damagePerHit", intent.getAmount() / hitCount);
        }
    }
    
    /**
     * 提取防御意图参数
     */
    private void extractDefendParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.addParameter("isDefend", true);
        parameters.addParameter("cardType", "SKILL");
        parameters.setBlock(intent.getAmount());
        
        // 检查是否有特殊防御效果
        if (intent.hasProperty("blockEffect")) {
            parameters.addParameter("blockEffect", intent.getProperty("blockEffect"));
        }
    }
    
    /**
     * 提取增益意图参数
     */
    private void extractBuffParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.addParameter("isBuff", true);
        parameters.addParameter("cardType", "POWER");
        parameters.setMagicNumber(intent.getAmount());
        
        // 检查增益类型
        if (intent.hasProperty("buffType")) {
            parameters.addParameter("buffType", intent.getProperty("buffType"));
        }
        
        // 检查增益目标
        if (intent.hasProperty("buffTarget")) {
            parameters.addParameter("buffTarget", intent.getProperty("buffTarget"));
        }
    }
    
    /**
     * 提取减益意图参数
     */
    private void extractDebuffParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.addParameter("isDebuff", true);
        parameters.addParameter("cardType", "SKILL");
        parameters.setMagicNumber(intent.getAmount());
        
        // 检查减益类型
        if (intent.hasProperty("debuffType")) {
            parameters.addParameter("debuffType", intent.getProperty("debuffType"));
        }
        
        // 检查减益目标
        if (intent.hasProperty("debuffTarget")) {
            parameters.addParameter("debuffTarget", intent.getProperty("debuffTarget"));
        }
    }
    
    /**
     * 提取强力攻击意图参数
     */
    private void extractStrongAttackParameters(MonsterIntent intent, IntentParameters parameters) {
        extractAttackParameters(intent, parameters);
        parameters.addParameter("isStrongAttack", true);
        parameters.addParameter("cardType", "ATTACK");
        
        // 强力攻击通常有更高的费用
        parameters.setCost(Math.max(2, parameters.getCost()));
        
        // 检查特殊强力效果
        if (intent.hasProperty("strongEffect")) {
            parameters.addParameter("strongEffect", intent.getProperty("strongEffect"));
        }
    }
    
    /**
     * 提取逃跑意图参数
     */
    private void extractEscapeParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.addParameter("isEscape", true);
        parameters.addParameter("cardType", "SKILL");
        parameters.setCost(0);
        
        // 检查逃跑条件
        if (intent.hasProperty("escapeCondition")) {
            parameters.addParameter("escapeCondition", intent.getProperty("escapeCondition"));
        }
    }
    
    /**
     * 提取未知意图参数
     */
    private void extractUnknownParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.addParameter("isUnknown", true);
        parameters.addParameter("cardType", "SKILL");
        parameters.setCost(1);
        
        // 未知意图需要更多信息来确定类型
        if (intent.getAmount() > 0) {
            // 如果有数量，可能是攻击或防御
            if (intent.hasProperty("damage")) {
                parameters.addParameter("cardType", "ATTACK");
                parameters.setDamage(intent.getAmount());
            } else if (intent.hasProperty("block")) {
                parameters.addParameter("cardType", "SKILL");
                parameters.setBlock(intent.getAmount());
            }
        }
    }
    
    /**
     * 计算基础费用
     */
    private int calculateBaseCost(MonsterIntent intent) {
        if (intent == null) {
            return 1;
        }
        
        int amount = intent.getAmount();
        
        switch (intent.getType()) {
            case ATTACK:
                return Math.max(1, Math.min(3, amount / 7));
            case STRONG:
                return Math.max(2, Math.min(3, amount / 5));
            case DEFEND:
                return Math.max(0, Math.min(2, amount / 10));
            case BUFF:
            case DEBUFF:
                return Math.max(1, Math.min(2, amount / 5));
            case ESCAPE:
                return 0;
            case UNKNOWN:
            default:
                return 1;
        }
    }
}