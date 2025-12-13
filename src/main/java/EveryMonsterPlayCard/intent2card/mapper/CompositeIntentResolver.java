package EveryMonsterPlayCard.intent2card.mapper;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.intent2card.analyzer.IntentParameters;
import EveryMonsterPlayCard.intent2card.template.CardTemplateEngine;

/**
 * 复合意图解析器
 * 负责解析和处理包含多种效果的复合意图
 */
public class CompositeIntentResolver {
    
    private final CardTemplateEngine templateEngine;
    
    public CompositeIntentResolver() {
        this.templateEngine = CardTemplateEngine.getInstance();
    }
    
    /**
     * 解析复合意图
     */
    public AbstractCard resolveCompositeIntent(MonsterIntent intent) {
        if (intent == null || !isCompositeIntent(intent)) {
            return null;
        }
        
        // 分析复合意图的组成部分
        CompositeIntentAnalysis analysis = analyzeCompositeIntent(intent);
        
        // 根据分析结果创建卡牌
        return createCompositeCard(intent, analysis);
    }
    
    /**
     * 检查是否为复合意图
     */
    public boolean isCompositeIntent(MonsterIntent intent) {
        if (intent == null) {
            return false;
        }
        
        // 检查明确的复合标记
        if (intent.hasProperty("isComposite") && intent.getBooleanParameter("isComposite")) {
            return true;
        }
        
        // 检查次要意图
        if (intent.hasProperty("secondaryIntent")) {
            return true;
        }
        
        // 检查多动作
        if (intent.getComponentActions() != null && intent.getComponentActions().size() > 1) {
            return true;
        }
        
        // 检查复合属性
        if (hasCompositeProperties(intent)) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 分析复合意图
     */
    private CompositeIntentAnalysis analyzeCompositeIntent(MonsterIntent intent) {
        CompositeIntentAnalysis analysis = new CompositeIntentAnalysis();
        
        // 设置主要意图类型
        analysis.primaryType = intent.getType();
        analysis.primaryAmount = intent.getAmount();
        
        // 分析次要意图
        analysis.secondaryType = determineSecondaryType(intent);
        analysis.secondaryAmount = determineSecondaryAmount(intent);
        
        // 分析复合类型
        analysis.compositeType = determineCompositeType(intent);
        
        // 分析复合复杂度
        analysis.complexity = calculateCompositeComplexity(intent);
        
        // 分析复合优先级
        analysis.priority = calculateCompositePriority(intent);
        
        return analysis;
    }
    
    /**
     * 确定次要意图类型
     */
    private IntentType determineSecondaryType(MonsterIntent intent) {
        // 检查明确的次要意图
        if (intent.hasProperty("secondaryIntent")) {
            String secondaryIntentStr = intent.getStringParameter("secondaryIntent");
            try {
                return IntentType.fromCode(secondaryIntentStr);
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 从动作中推断次要意图
        if (intent.getComponentActions() != null && intent.getComponentActions().size() > 1) {
            return inferSecondaryTypeFromActions(intent);
        }
        
        // 从属性中推断次要意图
        if (hasCompositeProperties(intent)) {
            return inferSecondaryTypeFromProperties(intent);
        }
        
        return IntentType.UNKNOWN;
    }
    
    /**
     * 确定次要意图数量
     */
    private int determineSecondaryAmount(MonsterIntent intent) {
        // 检查明确的次要数量
        if (intent.hasProperty("secondaryAmount")) {
            return intent.getIntParameter("secondaryAmount");
        }
        
        // 从动作中推断次要数量
        if (intent.getComponentActions() != null && intent.getComponentActions().size() > 1) {
            return inferSecondaryAmountFromActions(intent);
        }
        
        // 从属性中推断次要数量
        if (hasCompositeProperties(intent)) {
            return inferSecondaryAmountFromProperties(intent);
        }
        
        return 0;
    }
    
    /**
     * 确定复合类型
     */
    private CompositeType determineCompositeType(MonsterIntent intent) {
        // 检查明确的复合类型
        if (intent.hasProperty("compositeType")) {
            String compositeTypeStr = intent.getStringParameter("compositeType");
            try {
                return CompositeType.valueOf(compositeTypeStr.toUpperCase());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 根据主要和次要意图推断复合类型
        IntentType secondaryType = determineSecondaryType(intent);
        if (secondaryType != IntentType.UNKNOWN) {
            return inferCompositeType(intent.getType(), secondaryType);
        }
        
        // 默认为混合类型
        return CompositeType.MIXED;
    }
    
    /**
     * 计算复合复杂度
     */
    private int calculateCompositeComplexity(MonsterIntent intent) {
        int complexity = 1;
        
        // 基础复杂度
        complexity += getBaseComplexity(intent.getType());
        
        // 次要意图复杂度
        IntentType secondaryType = determineSecondaryType(intent);
        if (secondaryType != IntentType.UNKNOWN) {
            complexity += getBaseComplexity(secondaryType);
        }
        
        // 动作数量复杂度
        if (intent.getComponentActions() != null) {
            complexity += Math.min(3, intent.getComponentActions().size() - 1);
        }
        
        // 属性复杂度
        if (hasCompositeProperties(intent)) {
            complexity += 1;
        }
        
        return Math.min(10, complexity);
    }
    
    /**
     * 计算复合优先级
     */
    private int calculateCompositePriority(MonsterIntent intent) {
        int priority = 1;
        
        // 基础优先级
        priority += getBasePriority(intent.getType());
        
        // 次要意图优先级
        IntentType secondaryType = determineSecondaryType(intent);
        if (secondaryType != IntentType.UNKNOWN) {
            priority += getBasePriority(secondaryType);
        }
        
        // 复合类型优先级
        CompositeType compositeType = determineCompositeType(intent);
        priority += getCompositeTypePriority(compositeType);
        
        // 特殊属性优先级
        if (hasSpecialProperties(intent)) {
            priority += 1;
        }
        
        return Math.min(4, priority);
    }
    
    /**
     * 从动作中推断次要意图类型
     */
    private IntentType inferSecondaryTypeFromActions(MonsterIntent intent) {
        // 简化实现：根据主要意图类型推断次要意图
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                // 攻击意图的次要意图通常是防御或减益
                return IntentType.DEFEND;
            case DEFEND:
                // 防御意图的次要意图通常是攻击或增益
                return IntentType.ATTACK;
            case BUFF:
                // 增益意图的次要意图通常是攻击或防御
                return IntentType.ATTACK;
            case DEBUFF:
                // 减益意图的次要意图通常是攻击或防御
                return IntentType.ATTACK;
            default:
                return IntentType.UNKNOWN;
        }
    }
    
    /**
     * 从动作中推断次要数量
     */
    private int inferSecondaryAmountFromActions(MonsterIntent intent) {
        if (intent.getComponentActions() == null || intent.getComponentActions().size() <= 1) {
            return 0;
        }
        
        // 简化实现：次要数量为主要数量的一半
        return Math.max(1, intent.getAmount() / 2);
    }
    
    /**
     * 从属性中推断次要意图类型
     */
    private IntentType inferSecondaryTypeFromProperties(MonsterIntent intent) {
        // 检查属性组合
        boolean hasDamage = intent.hasProperty("damage") || intent.hasProperty("attack");
        boolean hasBlock = intent.hasProperty("block") || intent.hasProperty("defend");
        boolean hasBuff = intent.hasProperty("buff") || intent.hasProperty("power");
        boolean hasDebuff = intent.hasProperty("debuff") || intent.hasProperty("weakness");
        
        // 根据属性组合推断次要意图
        if (hasDamage && hasBlock) {
            return IntentType.DEFEND;
        } else if (hasDamage && hasBuff) {
            return IntentType.BUFF;
        } else if (hasBlock && hasBuff) {
            return IntentType.BUFF;
        } else if (hasDebuff) {
            return IntentType.DEBUFF;
        }
        
        return IntentType.UNKNOWN;
    }
    
    /**
     * 从属性中推断次要数量
     */
    private int inferSecondaryAmountFromProperties(MonsterIntent intent) {
        // 检查属性中的数量
        if (intent.hasProperty("secondaryAmount")) {
            return intent.getIntParameter("secondaryAmount");
        }
        
        // 检查属性中的数值
        if (intent.hasProperty("block")) {
            return intent.getIntParameter("block");
        }
        
        if (intent.hasProperty("buff")) {
            return intent.getIntParameter("buff");
        }
        
        if (intent.hasProperty("debuff")) {
            return intent.getIntParameter("debuff");
        }
        
        return 0;
    }
    
    /**
     * 推断复合类型
     */
    private CompositeType inferCompositeType(IntentType primaryType, IntentType secondaryType) {
        // 攻击+防御 = 混合
        if ((primaryType == IntentType.ATTACK || primaryType == IntentType.STRONG) && 
            secondaryType == IntentType.DEFEND) {
            return CompositeType.ATTACK_DEFEND;
        }
        
        // 攻击+增益 = 强化
        if ((primaryType == IntentType.ATTACK || primaryType == IntentType.STRONG) && 
            secondaryType == IntentType.BUFF) {
            return CompositeType.ATTACK_BUFF;
        }
        
        // 攻击+减益 = 弱化
        if ((primaryType == IntentType.ATTACK || primaryType == IntentType.STRONG) && 
            secondaryType == IntentType.DEBUFF) {
            return CompositeType.ATTACK_DEBUFF;
        }
        
        // 防御+增益 = 坚守
        if (primaryType == IntentType.DEFEND && secondaryType == IntentType.BUFF) {
            return CompositeType.DEFEND_BUFF;
        }
        
        // 防御+攻击 = 反击
        if (primaryType == IntentType.DEFEND && 
            (secondaryType == IntentType.ATTACK || secondaryType == IntentType.STRONG)) {
            return CompositeType.DEFEND_ATTACK;
        }
        
        return CompositeType.MIXED;
    }
    
    /**
     * 检查是否有复合属性
     */
    private boolean hasCompositeProperties(MonsterIntent intent) {
        return (intent.hasProperty("damage") && intent.hasProperty("block")) ||
               (intent.hasProperty("damage") && intent.hasProperty("buff")) ||
               (intent.hasProperty("block") && intent.hasProperty("buff")) ||
               intent.hasProperty("multiEffect");
   }
   
   /**
    * 检查是否有特殊属性
    */
   private boolean hasSpecialProperties(MonsterIntent intent) {
       return intent.hasProperty("special") ||
              intent.hasProperty("unique") ||
              intent.hasProperty("rare") ||
              intent.hasProperty("critical");
   }
    
    /**
     * 获取基础复杂度
     */
    private int getBaseComplexity(IntentType intentType) {
        switch (intentType) {
            case ATTACK:
                return 2;
            case STRONG:
                return 3;
            case DEFEND:
                return 2;
            case BUFF:
                return 3;
            case DEBUFF:
                return 3;
            case ESCAPE:
                return 1;
            case UNKNOWN:
                return 4;
            default:
                return 2;
        }
    }
    
    /**
     * 获取基础优先级
     */
    private int getBasePriority(IntentType intentType) {
        switch (intentType) {
            case ATTACK:
                return 2;
            case STRONG:
                return 3;
            case DEFEND:
                return 2;
            case BUFF:
                return 2;
            case DEBUFF:
                return 2;
            case ESCAPE:
                return 1;
            case UNKNOWN:
                return 1;
            default:
                return 1;
        }
    }
    
    /**
     * 获取复合类型优先级
     */
    private int getCompositeTypePriority(CompositeType compositeType) {
        switch (compositeType) {
            case ATTACK_DEFEND:
            case ATTACK_BUFF:
            case ATTACK_DEBUFF:
                return 3;
            case DEFEND_BUFF:
            case DEFEND_ATTACK:
                return 2;
            case MIXED:
                return 1;
            default:
                return 1;
        }
    }
    
    /**
     * 创建复合卡牌
     */
    private AbstractCard createCompositeCard(MonsterIntent intent, CompositeIntentAnalysis analysis) {
        IntentParameters parameters = new IntentParameters();
        
        // 设置复合标记
        parameters.addParameter("isComposite", true);
        parameters.addParameter("compositeType", analysis.compositeType.name());
        
        // 设置主要意图参数
        switch (analysis.primaryType) {
            case ATTACK:
            case STRONG:
                parameters.setDamage(analysis.primaryAmount);
                break;
            case DEFEND:
                parameters.setBlock(analysis.primaryAmount);
                break;
            case BUFF:
            case DEBUFF:
                parameters.setMagicNumber(analysis.primaryAmount);
                break;
        }
        
        // 设置次要意图参数
        if (analysis.secondaryType != IntentType.UNKNOWN) {
            switch (analysis.secondaryType) {
                case DEFEND:
                    parameters.setBlock(analysis.secondaryAmount);
                    break;
                case BUFF:
                case DEBUFF:
                    parameters.setMagicNumber(analysis.secondaryAmount);
                    break;
                case ATTACK:
                case STRONG:
                    parameters.setDamage(analysis.secondaryAmount);
                    break;
            }
        }
        
        // 计算复合费用
        parameters.setCost(calculateCompositeCost(analysis));
        
        // 设置复合属性
        parameters.addParameter("complexity", analysis.complexity);
        parameters.addParameter("priority", analysis.priority);
        
        // 使用自定义模板创建卡牌
        EveryMonsterPlayCard.intent2card.template.CardTemplate template = templateEngine.getTemplate("自定义卡牌模板");
        return template != null ? template.createCard(intent, parameters) : null;
    }
    
    /**
     * 计算复合费用
     */
    private int calculateCompositeCost(CompositeIntentAnalysis analysis) {
        int baseCost = 1;
        
        // 主要意图费用
        switch (analysis.primaryType) {
            case ATTACK:
                baseCost += Math.max(1, Math.min(3, analysis.primaryAmount / 7));
                break;
            case STRONG:
                baseCost += Math.max(2, Math.min(3, analysis.primaryAmount / 5));
                break;
            case DEFEND:
                baseCost += Math.max(0, Math.min(2, analysis.primaryAmount / 10));
                break;
            case BUFF:
            case DEBUFF:
                baseCost += Math.max(1, Math.min(2, analysis.primaryAmount / 5));
                break;
        }
        
        // 次要意图费用
        if (analysis.secondaryType != IntentType.UNKNOWN) {
            switch (analysis.secondaryType) {
                case ATTACK:
                case STRONG:
                    baseCost += Math.max(1, Math.min(2, analysis.secondaryAmount / 7));
                    break;
                case DEFEND:
                    baseCost += Math.max(0, Math.min(2, analysis.secondaryAmount / 10));
                    break;
                case BUFF:
                case DEBUFF:
                    baseCost += Math.max(1, Math.min(2, analysis.secondaryAmount / 5));
                    break;
            }
        }
        
        // 复合类型费用调整
        baseCost += getCompositeTypeCostBonus(analysis.compositeType);
        
        return Math.min(3, baseCost);
    }
    
    /**
     * 获取复合类型费用加成
     */
    private int getCompositeTypeCostBonus(CompositeType compositeType) {
        switch (compositeType) {
            case ATTACK_DEFEND:
            case ATTACK_BUFF:
            case ATTACK_DEBUFF:
                return 1; // 攻击复合类型费用+1
            case DEFEND_BUFF:
            case DEFEND_ATTACK:
                return 0; // 防御复合类型费用不变
            case MIXED:
                return 0; // 混合类型费用不变
            default:
                return 0;
        }
    }
    
    /**
     * 复合意图分析结果
     */
    private static class CompositeIntentAnalysis {
        IntentType primaryType;
        int primaryAmount;
        IntentType secondaryType;
        int secondaryAmount;
        CompositeType compositeType;
        int complexity;
        int priority;
    }
    
    /**
     * 复合类型枚举
     */
    public enum CompositeType {
        ATTACK_DEFEND("攻击防御"),
        ATTACK_BUFF("攻击增益"),
        ATTACK_DEBUFF("攻击减益"),
        DEFEND_BUFF("防御增益"),
        DEFEND_ATTACK("防御攻击"),
        MIXED("混合");
        
        private final String name;
        
        CompositeType(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
}