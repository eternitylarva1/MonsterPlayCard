package EveryMonsterPlayCard.intent2card.analyzer;

import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 意图复杂度分析器
 * 专门负责分析怪物意图的复杂度
 */
public class IntentComplexityAnalyzer {
    
    /**
     * 分析意图复杂度
     */
    public ComplexityAnalysis analyzeComplexity(MonsterIntent intent) {
        if (intent == null) {
            return new ComplexityAnalysis(1, "空意图");
        }
        
        // 基础复杂度分析
        ComplexityAnalysis analysis = analyzeBaseComplexity(intent);
        
        // 根据意图数量调整复杂度
        adjustComplexityByAmount(intent, analysis);
        
        // 根据组件动作调整复杂度
        adjustComplexityByActions(intent, analysis);
        
        // 根据属性调整复杂度
        adjustComplexityByProperties(intent, analysis);
        
        // 根据元数据调整复杂度
        adjustComplexityByMetadata(intent, analysis);
        
        // 检查复合意图复杂度
        adjustComplexityForCompositeIntent(intent, analysis);
        
        return analysis;
    }
    
    /**
     * 分析基础复杂度
     */
    private ComplexityAnalysis analyzeBaseComplexity(MonsterIntent intent) {
        return ComplexityAnalysis.analyzeIntentType(intent.getType());
    }
    
    /**
     * 根据意图数量调整复杂度
     */
    private void adjustComplexityByAmount(MonsterIntent intent, ComplexityAnalysis analysis) {
        int amount = intent.getAmount();
        if (amount <= 0) {
            return;
        }
        
        int additionalComplexity = 0;
        String reason = "";
        
        if (amount >= 50) {
            additionalComplexity = 3;
            reason = "极高数量意图";
        } else if (amount >= 30) {
            additionalComplexity = 2;
            reason = "高数量意图";
        } else if (amount >= 15) {
            additionalComplexity = 1;
            reason = "中等数量意图";
        } else if (amount >= 5) {
            additionalComplexity = 0;
            reason = "低数量意图";
        } else {
            additionalComplexity = 0;
            reason = "极低数量意图";
        }
        
        int newScore = Math.min(10, analysis.getComplexityScore() + additionalComplexity);
        analysis.setComplexityScore(newScore);
        analysis.setComplexityReason(analysis.getComplexityReason() + "，" + reason + "：" + amount);
    }
    
    /**
     * 根据组件动作调整复杂度
     */
    private void adjustComplexityByActions(MonsterIntent intent, ComplexityAnalysis analysis) {
        List<AbstractGameAction> actions = intent.getComponentActions();
        if (actions == null || actions.isEmpty()) {
            return;
        }
        
        int actionCount = actions.size();
        int additionalComplexity = 0;
        String reason = "";
        
        if (actionCount >= 5) {
            additionalComplexity = 3;
            reason = "极多动作";
        } else if (actionCount >= 3) {
            additionalComplexity = 2;
            reason = "多动作";
        } else if (actionCount >= 2) {
            additionalComplexity = 1;
            reason = "复合动作";
        } else {
            additionalComplexity = 0;
            reason = "单一动作";
        }
        
        // 分析动作类型的复杂度
        int actionTypeComplexity = analyzeActionTypesComplexity(actions);
        additionalComplexity += actionTypeComplexity;
        
        int newScore = Math.min(10, analysis.getComplexityScore() + additionalComplexity);
        analysis.setComplexityScore(newScore);
        analysis.setComplexityReason(analysis.getComplexityReason() + "，" + reason + "：" + actionCount);
    }
    
    /**
     * 分析动作类型的复杂度
     */
    private int analyzeActionTypesComplexity(List<AbstractGameAction> actions) {
        int complexity = 0;
        boolean hasDamage = false;
        boolean hasBlock = false;
        boolean hasPower = false;
        boolean hasSpecial = false;
        
        for (AbstractGameAction action : actions) {
            String actionType = action.getClass().getSimpleName();
            if (actionType.contains("Damage")) {
                hasDamage = true;
            } else if (actionType.contains("Block")) {
                hasBlock = true;
            } else if (actionType.contains("Power")) {
                hasPower = true;
            } else {
                hasSpecial = true;
            }
        }
        
        // 不同类型的动作组合增加复杂度
        int typeCount = (hasDamage ? 1 : 0) + (hasBlock ? 1 : 0) + (hasPower ? 1 : 0) + (hasSpecial ? 1 : 0);
        
        if (typeCount >= 3) {
            complexity = 2;
        } else if (typeCount >= 2) {
            complexity = 1;
        }
        
        return complexity;
    }
    
    /**
     * 根据属性调整复杂度
     */
    private void adjustComplexityByProperties(MonsterIntent intent, ComplexityAnalysis analysis) {
        int propertyCount = intent.getProperties().size();
        if (propertyCount <= 0) {
            return;
        }
        
        int additionalComplexity = 0;
        String reason = "";
        
        if (propertyCount >= 10) {
            additionalComplexity = 2;
            reason = "极多属性";
        } else if (propertyCount >= 5) {
            additionalComplexity = 1;
            reason = "多属性";
        } else {
            additionalComplexity = 0;
            reason = "少量属性";
        }
        
        // 检查特殊属性
        boolean hasComplexProperties = checkComplexProperties(intent);
        if (hasComplexProperties) {
            additionalComplexity += 1;
            reason += "，包含复杂属性";
        }
        
        int newScore = Math.min(10, analysis.getComplexityScore() + additionalComplexity);
        analysis.setComplexityScore(newScore);
        analysis.setComplexityReason(analysis.getComplexityReason() + "，" + reason + "：" + propertyCount);
    }
    
    /**
     * 检查复杂属性
     */
    private boolean checkComplexProperties(MonsterIntent intent) {
        return intent.hasProperty("conditionalEffect") ||
               intent.hasProperty("randomEffect") ||
               intent.hasProperty("scalingEffect") ||
               intent.hasProperty("multiTarget") ||
               intent.hasProperty("delayedEffect");
    }
    
    /**
     * 根据元数据调整复杂度
     */
    private void adjustComplexityByMetadata(MonsterIntent intent, ComplexityAnalysis analysis) {
        if (intent.getMetadata() == null) {
            return;
        }
        
        int metadataPriority = intent.getMetadata().getPriority();
        if (metadataPriority > 5) {
            int additionalComplexity = 1;
            int newScore = Math.min(10, analysis.getComplexityScore() + additionalComplexity);
            analysis.setComplexityScore(newScore);
            analysis.setComplexityReason(analysis.getComplexityReason() + "，高优先级元数据：" + metadataPriority);
        }
        
        // 检查元数据中的复杂属性
        int metadataPropertyCount = intent.getMetadata().getProperties().size();
        if (metadataPropertyCount > 3) {
            int additionalComplexity = 1;
            int newScore = Math.min(10, analysis.getComplexityScore() + additionalComplexity);
            analysis.setComplexityScore(newScore);
            analysis.setComplexityReason(analysis.getComplexityReason() + "，复杂元数据");
        }
    }
    
    /**
     * 调整复合意图复杂度
     */
    private void adjustComplexityForCompositeIntent(MonsterIntent intent, ComplexityAnalysis analysis) {
        // 检查是否明确标记为复合意图
        if (intent.hasProperty("isComposite") && intent.getBooleanParameter("isComposite")) {
            int additionalComplexity = 2;
            int newScore = Math.min(10, analysis.getComplexityScore() + additionalComplexity);
            analysis.setComplexityScore(newScore);
            analysis.setComplexityReason(analysis.getComplexityReason() + "，明确复合意图");
            return;
        }
        
        // 检查次要意图类型
        if (intent.hasProperty("secondaryIntent")) {
            String secondaryIntentStr = intent.getStringParameter("secondaryIntent");
            try {
                IntentType secondaryType = IntentType.fromCode(secondaryIntentStr);
                ComplexityAnalysis secondaryAnalysis = ComplexityAnalysis.analyzeIntentType(secondaryType);
                
                // 复合意图复杂度 = 主要意图复杂度 + 次要意图复杂度 + 1
                int compositeScore = analysis.getComplexityScore() + secondaryAnalysis.getComplexityScore() + 1;
                int newScore = Math.min(10, compositeScore);
                analysis.setComplexityScore(newScore);
                analysis.setComplexityReason(analysis.getComplexityReason() + "，复合意图：" + 
                    intent.getType().getDisplayName() + " + " + secondaryType.getDisplayName());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 检查多目标意图
        if (intent.hasProperty("multiTarget") && intent.getBooleanParameter("multiTarget")) {
            int additionalComplexity = 1;
            int newScore = Math.min(10, analysis.getComplexityScore() + additionalComplexity);
            analysis.setComplexityScore(newScore);
            analysis.setComplexityReason(analysis.getComplexityReason() + "，多目标意图");
        }
    }
    
    /**
     * 分析意图复杂度等级
     */
    public ComplexityAnalysis.ComplexityLevel determineComplexityLevel(MonsterIntent intent) {
        ComplexityAnalysis analysis = analyzeComplexity(intent);
        return analysis.getComplexityLevel();
    }
    
    /**
     * 检查是否为高复杂度意图
     */
    public boolean isHighComplexity(MonsterIntent intent) {
        ComplexityAnalysis analysis = analyzeComplexity(intent);
        return analysis.getComplexityLevel() == ComplexityAnalysis.ComplexityLevel.VERY_COMPLEX ||
               analysis.getComplexityLevel() == ComplexityAnalysis.ComplexityLevel.COMPLEX;
    }
    
    /**
     * 检查是否为低复杂度意图
     */
    public boolean isLowComplexity(MonsterIntent intent) {
        ComplexityAnalysis analysis = analyzeComplexity(intent);
        return analysis.getComplexityLevel() == ComplexityAnalysis.ComplexityLevel.SIMPLE;
    }
}