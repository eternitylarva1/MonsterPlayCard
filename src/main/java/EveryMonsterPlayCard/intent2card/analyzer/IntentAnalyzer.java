package EveryMonsterPlayCard.intent2card.analyzer;

import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 意图分析器
 * 负责分析怪物意图的类型、参数、复杂度和优先级
 */
public class IntentAnalyzer {
    
    /**
     * 分析怪物意图
     */
    public IntentAnalysisResult analyzeIntent(MonsterIntent intent) {
        if (intent == null) {
            return createDefaultAnalysisResult();
        }
        
        IntentAnalysisResult result = new IntentAnalysisResult();
        
        // 分析主要意图类型
        result.setPrimaryType(intent.getType());
        
        // 提取意图参数
        IntentParameters parameters = extractIntentParameters(intent);
        result.getExtractedParameters().putAll(parameters.getAllParameters());
        
        // 分析意图复杂度
        ComplexityAnalysis complexityAnalysis = analyzeIntentComplexity(intent);
        result.setComplexity(complexityAnalysis.getComplexityScore());
        
        // 分析意图优先级
        PriorityAnalysis priorityAnalysis = analyzeIntentPriority(intent);
        result.setPriority(priorityAnalysis.getPriorityScore());
        
        // 检查是否为复合意图
        analyzeCompositeIntent(intent, result);
        
        // 生成分析摘要
        generateAnalysisSummary(result);
        
        return result;
    }
    
    /**
     * 提取意图参数
     */
    public IntentParameters extractIntentParameters(MonsterIntent intent) {
        IntentParameters parameters = new IntentParameters();
        
        if (intent == null) {
            return parameters;
        }
        
        // 基础参数
        parameters.setDamage(intent.getAmount());
        parameters.setCost(calculateCardCost(intent));
        
        // 根据意图类型提取特定参数
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
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
            case ESCAPE:
                extractEscapeParameters(intent, parameters);
                break;
            case UNKNOWN:
                extractUnknownParameters(intent, parameters);
                break;
        }
        
        // 从组件动作中提取参数
        extractParametersFromActions(intent, parameters);
        
        // 从属性中提取参数
        extractParametersFromProperties(intent, parameters);
        
        return parameters;
    }
    
    /**
     * 分析意图复杂度
     */
    public ComplexityAnalysis analyzeIntentComplexity(MonsterIntent intent) {
        if (intent == null) {
            return new ComplexityAnalysis(1, "空意图");
        }
        
        // 基础复杂度分析
        ComplexityAnalysis baseAnalysis = ComplexityAnalysis.analyzeIntentType(intent.getType());
        
        // 根据意图数量调整复杂度
        int amount = intent.getAmount();
        if (amount > 0) {
            int additionalComplexity = Math.min(3, amount / 5);
            baseAnalysis.setComplexityScore(baseAnalysis.getComplexityScore() + additionalComplexity);
            baseAnalysis.setComplexityReason(baseAnalysis.getComplexityReason() + "，数量：" + amount);
        }
        
        // 根据组件动作数量调整复杂度
        List<AbstractGameAction> actions = intent.getComponentActions();
        if (actions != null && !actions.isEmpty()) {
            int actionComplexity = Math.min(2, actions.size());
            baseAnalysis.setComplexityScore(baseAnalysis.getComplexityScore() + actionComplexity);
            baseAnalysis.setComplexityReason(baseAnalysis.getComplexityReason() + "，动作数：" + actions.size());
        }
        
        // 根据属性数量调整复杂度
        int propertyCount = intent.getProperties().size();
        if (propertyCount > 0) {
            int propertyComplexity = Math.min(2, propertyCount / 3);
            baseAnalysis.setComplexityScore(baseAnalysis.getComplexityScore() + propertyComplexity);
            baseAnalysis.setComplexityReason(baseAnalysis.getComplexityReason() + "，属性数：" + propertyCount);
        }
        
        return baseAnalysis;
    }
    
    /**
     * 分析意图优先级
     */
    public PriorityAnalysis analyzeIntentPriority(MonsterIntent intent) {
        if (intent == null) {
            return new PriorityAnalysis(1, "空意图");
        }
        
        // 基础优先级分析
        PriorityAnalysis baseAnalysis = PriorityAnalysis.analyzeIntentType(intent.getType());
        
        // 根据意图数量调整优先级
        int amount = intent.getAmount();
        if (amount > 0) {
            PriorityAnalysis amountAnalysis = PriorityAnalysis.analyzeByIntentAmount(amount);
            // 取较高的优先级
            if (amountAnalysis.getPriorityScore() > baseAnalysis.getPriorityScore()) {
                baseAnalysis = amountAnalysis;
            }
        }
        
        // 根据元数据优先级调整
        if (intent.getMetadata() != null) {
            int metadataPriority = intent.getMetadata().getPriority();
            if (metadataPriority > 0) {
                baseAnalysis.setPriorityScore(Math.min(4, baseAnalysis.getPriorityScore() + 1));
                baseAnalysis.setPriorityReason(baseAnalysis.getPriorityReason() + "，元数据优先级：" + metadataPriority);
            }
        }
        
        return baseAnalysis;
    }
    
    /**
     * 分析复合意图
     */
    private void analyzeCompositeIntent(MonsterIntent intent, IntentAnalysisResult result) {
        // 检查是否有多个组件动作
        List<AbstractGameAction> actions = intent.getComponentActions();
        if (actions != null && actions.size() > 1) {
            result.setComposite(true);
            
            // 简单的复合意图检测
            if (hasAttackAndDefendActions(actions)) {
                result.setSecondaryType(IntentType.DEFEND);
            } else if (hasAttackAndDebuffActions(actions)) {
                result.setSecondaryType(IntentType.DEBUFF);
            } else if (hasDefendAndBuffActions(actions)) {
                result.setSecondaryType(IntentType.BUFF);
            }
        }
        
        // 检查属性中的复合意图信息
        if (intent.hasProperty("secondaryIntent")) {
            String secondaryIntentStr = intent.getStringParameter("secondaryIntent");
            try {
                IntentType secondaryType = IntentType.fromCode(secondaryIntentStr);
                result.setSecondaryType(secondaryType);
                result.setComposite(true);
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
    }
    
    /**
     * 生成分析摘要
     */
    private void generateAnalysisSummary(IntentAnalysisResult result) {
        StringBuilder summary = new StringBuilder();
        
        summary.append("主要意图：").append(result.getPrimaryType().getDisplayName());
        
        if (result.hasSecondaryType()) {
            summary.append("，次要意图：").append(result.getSecondaryType().getDisplayName());
        }
        
        summary.append("，复杂度：").append(result.getComplexity());
        summary.append("，优先级：").append(result.getPriority());
        
        if (result.isComposite()) {
            summary.append("，复合意图");
        }
        
        result.setAnalysisSummary(summary.toString());
    }
    
    /**
     * 创建默认分析结果
     */
    private IntentAnalysisResult createDefaultAnalysisResult() {
        IntentAnalysisResult result = new IntentAnalysisResult();
        result.setPrimaryType(IntentType.UNKNOWN);
        result.setComplexity(1);
        result.setPriority(1);
        result.setAnalysisSummary("空意图或未知意图");
        return result;
    }
    
    /**
     * 提取攻击意图参数
     */
    private void extractAttackParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.setDamage(intent.getAmount());
        parameters.setCost(Math.max(1, Math.min(3, intent.getAmount() / 5)));
        
        // 检查是否有特殊攻击效果
        if (intent.hasProperty("attackEffect")) {
            parameters.addParameter("attackEffect", intent.getProperty("attackEffect"));
        }
    }
    
    /**
     * 提取防御意图参数
     */
    private void extractDefendParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.setBlock(intent.getAmount());
        parameters.setCost(Math.max(0, Math.min(2, intent.getAmount() / 8)));
    }
    
    /**
     * 提取增益意图参数
     */
    private void extractBuffParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.setMagicNumber(intent.getAmount());
        parameters.setCost(Math.max(1, Math.min(2, intent.getAmount() / 3)));
        
        if (intent.hasProperty("buffType")) {
            parameters.addParameter("buffType", intent.getProperty("buffType"));
        }
    }
    
    /**
     * 提取减益意图参数
     */
    private void extractDebuffParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.setMagicNumber(intent.getAmount());
        parameters.setCost(Math.max(1, Math.min(2, intent.getAmount() / 3)));
        
        if (intent.hasProperty("debuffType")) {
            parameters.addParameter("debuffType", intent.getProperty("debuffType"));
        }
    }
    
    /**
     * 提取逃跑意图参数
     */
    private void extractEscapeParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.setCost(0);
    }
    
    /**
     * 提取未知意图参数
     */
    private void extractUnknownParameters(MonsterIntent intent, IntentParameters parameters) {
        parameters.setCost(1);
        parameters.addParameter("unknownIntent", true);
    }
    
    /**
     * 从组件动作中提取参数
     */
    private void extractParametersFromActions(MonsterIntent intent, IntentParameters parameters) {
        List<AbstractGameAction> actions = intent.getComponentActions();
        if (actions == null || actions.isEmpty()) {
            return;
        }
        
        // 这里可以根据具体的动作类型提取更多参数
        parameters.addParameter("actionCount", actions.size());
    }
    
    /**
     * 从属性中提取参数
     */
    private void extractParametersFromProperties(MonsterIntent intent, IntentParameters parameters) {
        // 复制所有属性到参数中
        parameters.getAllParameters().putAll(intent.getProperties());
    }
    
    /**
     * 计算卡牌费用
     */
    private int calculateCardCost(MonsterIntent intent) {
        if (intent == null) {
            return 1;
        }
        
        int amount = intent.getAmount();
        
        switch (intent.getType()) {
            case ATTACK:
            case STRONG:
                return Math.max(1, Math.min(3, amount / 7));
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
    
    /**
     * 检查是否同时有攻击和防御动作
     */
    private boolean hasAttackAndDefendActions(List<AbstractGameAction> actions) {
        // 简化实现，实际应该检查动作类型
        return actions.size() >= 2;
    }
    
    /**
     * 检查是否同时有攻击和减益动作
     */
    private boolean hasAttackAndDebuffActions(List<AbstractGameAction> actions) {
        // 简化实现，实际应该检查动作类型
        return actions.size() >= 2;
    }
    
    /**
     * 检查是否同时有防御和增益动作
     */
    private boolean hasDefendAndBuffActions(List<AbstractGameAction> actions) {
        // 简化实现，实际应该检查动作类型
        return actions.size() >= 2;
    }
}