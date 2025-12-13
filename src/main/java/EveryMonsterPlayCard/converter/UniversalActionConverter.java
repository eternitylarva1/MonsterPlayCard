package EveryMonsterPlayCard.converter;

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

import EveryMonsterPlayCard.conversion.analyzer.ActionAnalyzer;
import EveryMonsterPlayCard.conversion.analyzer.SingleActionAnalysis;
import EveryMonsterPlayCard.conversion.mapper.ActionToIntentMapper;
import EveryMonsterPlayCard.conversion.mapper.ComplexActionHandler;
import EveryMonsterPlayCard.conversion.mapper.IntentPriorityResolver;
import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 通用动作转换器
 * 负责将游戏动作转换为怪物意图
 */
public class UniversalActionConverter implements EveryMonsterPlayCard.core.interfaces.IActionToIntentConverter {
    private final ActionAnalyzer actionAnalyzer;
    private final ActionToIntentMapper actionMapper;
    private final ComplexActionHandler complexHandler;
    private final IntentPriorityResolver priorityResolver;
    private final ConversionStatistics statistics;
    
    public UniversalActionConverter() {
        this.actionAnalyzer = new ActionAnalyzer();
        this.actionMapper = new ActionToIntentMapper();
        this.complexHandler = new ComplexActionHandler();
        this.priorityResolver = new IntentPriorityResolver();
        this.statistics = new ConversionStatistics();
    }
    
    /**
     * 将单个动作转换为意图
     * @param action 游戏动作
     * @return 怪物意图
     */
    @Override
    public MonsterIntent convertAction(AbstractGameAction action) {
        if (action == null) {
            statistics.recordConversion(false, 0);
            return null;
        }
        
        long startTime = System.currentTimeMillis();
        
        try {
            // 映射为意图
            MonsterIntent intent = actionMapper.mapSingleAction(action);
            
            if (intent == null) {
                statistics.recordConversion(false, System.currentTimeMillis() - startTime);
                return null;
            }
            
            // 解析优先级
            int priority = priorityResolver.resolvePriority(intent);
            intent.getMetadata().setPriority(priority);
            
            statistics.recordConversion(true, System.currentTimeMillis() - startTime);
            return intent;
            
        } catch (Exception e) {
            statistics.recordConversion(false, System.currentTimeMillis() - startTime);
            return null;
        }
    }
    
    /**
     * 分解复杂动作为多个意图
     * @param action 复杂动作
     * @return 意图列表
     */
    @Override
    public List<MonsterIntent> decomposeComplexAction(AbstractGameAction action) {
        if (action == null) {
            return new ArrayList<>();
        }
        
        long startTime = System.currentTimeMillis();
        
        try {
            // 分析动作
            SingleActionAnalysis analysis = actionAnalyzer.analyzeAction(action);
            
            // 检查是否为复杂动作（使用动作强度作为复杂度指标）
            if (analysis != null && actionAnalyzer.calculateActionStrength(action) > 10) {
                // 使用复杂动作处理器
                List<AbstractGameAction> actions = new ArrayList<>();
                actions.add(action);
                List<MonsterIntent> intents = complexHandler.handleComplexActions(actions);
                
                statistics.recordConversion(true, System.currentTimeMillis() - startTime);
                return intents;
            } else {
                // 简单动作，直接转换
                MonsterIntent intent = convertAction(action);
                if (intent != null) {
                    List<MonsterIntent> intents = new ArrayList<>();
                    intents.add(intent);
                    statistics.recordConversion(true, System.currentTimeMillis() - startTime);
                    return intents;
                }
            }
            
            statistics.recordConversion(false, System.currentTimeMillis() - startTime);
            return new ArrayList<>();
            
        } catch (Exception e) {
            statistics.recordConversion(false, System.currentTimeMillis() - startTime);
            return new ArrayList<>();
        }
    }
    
    /**
     * 分类意图类型
     * @param action 游戏动作
     * @return 意图类型
     */
    @Override
    public IntentType classifyIntent(AbstractGameAction action) {
        if (action == null) {
            return IntentType.UNKNOWN;
        }
        
        try {
            // 使用动作分析器预测意图类型
            return actionAnalyzer.predictIntentType(action);
        } catch (Exception e) {
            return IntentType.UNKNOWN;
        }
    }
    
    /**
     * 计算意图强度
     * @param action 游戏动作
     * @return 意图强度值
     */
    @Override
    public int calculateIntentStrength(AbstractGameAction action) {
        if (action == null) {
            return 0;
        }
        
        try {
            // 基础强度为动作数量
            int strength = action.amount;
            
            // 根据动作类型调整强度
            if (action instanceof DamageAction) {
                strength *= 2; // 伤害动作强度加倍
            } else if (action instanceof ApplyPowerAction) {
                strength *= 1.5; // 能力动作强度增加50%
            } else if (action instanceof GainBlockAction) {
                strength *= 1.2; // 格挡动作强度增加20%
            }
            
            return Math.max(0, strength);
        } catch (Exception e) {
            return 0;
        }
    }
    
    /**
     * 验证转换结果
     * @param action 原始动作
     * @param intent 转换后的意图
     * @return 验证结果
     */
    @Override
    public boolean validateConversion(AbstractGameAction action, MonsterIntent intent) {
        if (action == null || intent == null) {
            return false;
        }
        
        try {
            // 基本验证
            if (intent.getType() == null || intent.getAmount() < 0) {
                return false;
            }
            
            // 验证意图类型与动作类型的一致性
            IntentType expectedType = classifyIntent(action);
            if (expectedType != IntentType.UNKNOWN && intent.getType() != expectedType) {
                return false;
            }
            
            // 验证强度
            int expectedStrength = calculateIntentStrength(action);
            int actualStrength = intent.getAmount();
            
            // 允许一定的误差范围
            if (Math.abs(expectedStrength - actualStrength) > expectedStrength * 0.3) {
                return false;
            }
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 检查是否可以处理指定动作
     * @param action 游戏动作
     * @return 是否可以处理
     */
    @Override
    public boolean canHandle(AbstractGameAction action) {
        if (action == null) {
            return false;
        }
        
        // 检查是否为支持的动作类型
        Class<? extends AbstractGameAction> actionClass = action.getClass();
        for (Class<? extends AbstractGameAction> supportedClass : getSupportedActionTypes()) {
            if (supportedClass.isAssignableFrom(actionClass)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 获取支持的动作类型列表
     * @return 支持的动作类型列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends AbstractGameAction>[] getSupportedActionTypes() {
        return new Class[] {
            DamageAction.class,
            GainBlockAction.class,
            ApplyPowerAction.class,
            DrawCardAction.class,
            GainEnergyAction.class,
            HealAction.class,
            DiscardAction.class,
            ExhaustAction.class
        };
    }
    
    /**
     * 转换动作序列为意图列表
     * @param actions 动作序列
     * @return 意图列表
     */
    public List<MonsterIntent> convertActionSequence(List<AbstractGameAction> actions) {
        if (actions == null || actions.isEmpty()) {
            return new ArrayList<>();
        }
        
        long startTime = System.currentTimeMillis();
        
        try {
            // 处理复杂动作
            List<MonsterIntent> intents = complexHandler.handleComplexActions(actions);
            
            if (intents.isEmpty()) {
                statistics.recordConversion(false, System.currentTimeMillis() - startTime);
                return new ArrayList<>();
            }
            
            // 解析优先级并排序
            List<MonsterIntent> sortedIntents = priorityResolver.sortByPriority(intents);
            
            statistics.recordConversion(true, System.currentTimeMillis() - startTime);
            return sortedIntents;
            
        } catch (Exception e) {
            statistics.recordConversion(false, System.currentTimeMillis() - startTime);
            return new ArrayList<>();
        }
    }
    
    /**
     * 转换卡牌动作为意图列表
     * @param card 卡牌
     * @return 意图列表
     */
    public List<MonsterIntent> convertCardActions(AbstractCard card) {
        if (card == null) {
            return new ArrayList<>();
        }
        
        try {
            // 分析卡牌动作
            EveryMonsterPlayCard.conversion.analyzer.ActionAnalysisResult analysisResult = actionAnalyzer.analyzeCardActions(card);
            
            if (analysisResult == null || analysisResult.getActionCount() == 0) {
                return new ArrayList<>();
            }
            
            // 从卡牌推断动作
            List<AbstractGameAction> inferredActions = inferActionsFromCard(card);
            if (inferredActions.isEmpty()) {
                return new ArrayList<>();
            }
            
            // 转换动作
            List<MonsterIntent> intents = new ArrayList<>();
            for (AbstractGameAction action : inferredActions) {
                MonsterIntent intent = convertAction(action);
                if (intent != null) {
                    intents.add(intent);
                }
            }
            
            return intents;
            
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    /**
     * 从卡牌推断动作
     * @param card 卡牌
     * @return 推断的动作列表
     */
    private List<AbstractGameAction> inferActionsFromCard(AbstractCard card) {
        List<AbstractGameAction> actions = new ArrayList<>();
        
        // 根据卡牌类型推断动作
        if (card.type == AbstractCard.CardType.ATTACK) {
            // 攻击卡牌通常有伤害动作
            if (card.baseDamage > 0) {
                // 这里创建一个模拟的伤害动作，实际使用时需要更复杂的逻辑
                // 由于无法直接创建真实的动作对象，这里返回空列表
                // 在实际应用中，可能需要使用卡牌的use方法或其他方式获取动作
            }
        } else if (card.type == AbstractCard.CardType.SKILL) {
            // 技能卡牌可能有格挡或能力动作
            if (card.baseBlock > 0) {
                // 格挡动作
            }
            if (card.baseMagicNumber > 0) {
                // 能力动作
            }
        } else if (card.type == AbstractCard.CardType.POWER) {
            // 能力卡牌通常有增益动作
            if (card.baseMagicNumber > 0) {
                // 增益动作
            }
        }
        
        return actions;
    }
    
    /**
     * 获取最高优先级意图
     * @param actions 动作序列
     * @return 最高优先级意图
     */
    public MonsterIntent getHighestPriorityIntent(List<AbstractGameAction> actions) {
        List<MonsterIntent> intents = convertActionSequence(actions);
        return priorityResolver.getHighestPriorityIntent(intents);
    }
    
    /**
     * 获取转换器统计信息
     * @return 统计信息
     */
    public ConversionStatistics getStatistics() {
        return statistics;
    }
    
    /**
     * 重置转换器状态
     */
    public void reset() {
        statistics.reset();
    }
    
    /**
     * 转换统计信息
     */
    public static class ConversionStatistics {
        private int totalConversions;
        private int successfulConversions;
        private int failedConversions;
        private long totalAnalysisTime;
        
        public ConversionStatistics() {
            this.totalConversions = 0;
            this.successfulConversions = 0;
            this.failedConversions = 0;
            this.totalAnalysisTime = 0;
        }
        
        public int getTotalConversions() {
            return totalConversions;
        }
        
        public int getSuccessfulConversions() {
            return successfulConversions;
        }
        
        public int getFailedConversions() {
            return failedConversions;
        }
        
        public long getTotalAnalysisTime() {
            return totalAnalysisTime;
        }
        
        public double getSuccessRate() {
            return totalConversions > 0 ? (double) successfulConversions / totalConversions : 0.0;
        }
        
        public double getAverageAnalysisTime() {
            return totalConversions > 0 ? (double) totalAnalysisTime / totalConversions : 0.0;
        }
        
        public void recordConversion(boolean success, long analysisTime) {
            totalConversions++;
            if (success) {
                successfulConversions++;
            } else {
                failedConversions++;
            }
            totalAnalysisTime += analysisTime;
        }
        
        public void reset() {
            totalConversions = 0;
            successfulConversions = 0;
            failedConversions = 0;
            totalAnalysisTime = 0;
        }
        
        @Override
        public String toString() {
            return "ConversionStatistics{" +
                    "totalConversions=" + totalConversions +
                    ", successfulConversions=" + successfulConversions +
                    ", failedConversions=" + failedConversions +
                    ", successRate=" + String.format("%.2f%%", getSuccessRate() * 100) +
                    ", averageAnalysisTime=" + String.format("%.2fms", getAverageAnalysisTime()) +
                    '}';
        }
    }
}