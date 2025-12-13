package EveryMonsterPlayCard.intent2card.analyzer;

import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import EveryMonsterPlayCard.core.data.IntentType;
import EveryMonsterPlayCard.core.data.MonsterIntent;

/**
 * 意图优先级分析器
 * 专门负责分析怪物意图的优先级
 */
public class IntentPriorityAnalyzer {
    
    /**
     * 分析意图优先级
     */
    public PriorityAnalysis analyzePriority(MonsterIntent intent) {
        if (intent == null) {
            return new PriorityAnalysis(1, "空意图");
        }
        
        // 基础优先级分析
        PriorityAnalysis analysis = analyzeBasePriority(intent);
        
        // 根据意图数量调整优先级
        adjustPriorityByAmount(intent, analysis);
        
        // 根据游戏状态调整优先级
        adjustPriorityByGameState(intent, analysis);
        
        // 根据组件动作调整优先级
        adjustPriorityByActions(intent, analysis);
        
        // 根据元数据调整优先级
        adjustPriorityByMetadata(intent, analysis);
        
        // 检查复合意图优先级
        adjustPriorityForCompositeIntent(intent, analysis);
        
        return analysis;
    }
    
    /**
     * 分析基础优先级
     */
    private PriorityAnalysis analyzeBasePriority(MonsterIntent intent) {
        return PriorityAnalysis.analyzeIntentType(intent.getType());
    }
    
    /**
     * 根据意图数量调整优先级
     */
    private void adjustPriorityByAmount(MonsterIntent intent, PriorityAnalysis analysis) {
        int amount = intent.getAmount();
        if (amount <= 0) {
            return;
        }
        
        PriorityAnalysis amountAnalysis = PriorityAnalysis.analyzeByIntentAmount(amount);
        
        // 取较高的优先级
        if (amountAnalysis.getPriorityScore() > analysis.getPriorityScore()) {
            analysis.setPriorityScore(amountAnalysis.getPriorityScore());
            analysis.setPriorityReason(amountAnalysis.getPriorityReason());
        }
    }
    
    /**
     * 根据游戏状态调整优先级
     */
    private void adjustPriorityByGameState(MonsterIntent intent, PriorityAnalysis analysis) {
        try {
            // 检查玩家生命值
            if (AbstractDungeon.player != null) {
                float playerHealthPercent = (float) AbstractDungeon.player.currentHealth / AbstractDungeon.player.maxHealth;
                
                if (playerHealthPercent <= 0.2f) {
                    // 玩家生命值极低，攻击意图优先级提高
                    if (intent.isAttack()) {
                        int newScore = Math.min(4, analysis.getPriorityScore() + 1);
                        analysis.setPriorityScore(newScore);
                        analysis.setPriorityReason(analysis.getPriorityReason() + "，玩家生命值极低");
                    }
                } else if (playerHealthPercent <= 0.5f) {
                    // 玩家生命值较低，攻击意图优先级略微提高
                    if (intent.isAttack()) {
                        int newScore = Math.min(4, analysis.getPriorityScore() + 0);
                        analysis.setPriorityReason(analysis.getPriorityReason() + "，玩家生命值较低");
                    }
                }
            }
            
            // 检查怪物数量
            if (AbstractDungeon.getMonsters() != null) {
                int monsterCount = AbstractDungeon.getMonsters().monsters.size();
                if (monsterCount > 1) {
                    // 多怪物情况下，增益意图优先级提高
                    if (intent.isBuff()) {
                        int newScore = Math.min(4, analysis.getPriorityScore() + 1);
                        analysis.setPriorityScore(newScore);
                        analysis.setPriorityReason(analysis.getPriorityReason() + "，多怪物战斗");
                    }
                }
            }
            
        } catch (Exception e) {
            // 游戏状态获取失败，忽略
        }
    }
    
    /**
     * 根据组件动作调整优先级
     */
    private void adjustPriorityByActions(MonsterIntent intent, PriorityAnalysis analysis) {
        List<AbstractGameAction> actions = intent.getComponentActions();
        if (actions == null || actions.isEmpty()) {
            return;
        }
        
        int actionCount = actions.size();
        
        // 多动作意图优先级提高
        if (actionCount >= 3) {
            int newScore = Math.min(4, analysis.getPriorityScore() + 1);
            analysis.setPriorityScore(newScore);
            analysis.setPriorityReason(analysis.getPriorityReason() + "，多动作意图");
        }
        
        // 分析动作类型的优先级影响
        int actionPriorityBonus = analyzeActionTypesPriority(actions);
        if (actionPriorityBonus > 0) {
            int newScore = Math.min(4, analysis.getPriorityScore() + actionPriorityBonus);
            analysis.setPriorityScore(newScore);
            analysis.setPriorityReason(analysis.getPriorityReason() + "，高威胁动作类型");
        }
    }
    
    /**
     * 分析动作类型的优先级影响
     */
    private int analyzeActionTypesPriority(List<AbstractGameAction> actions) {
        int priorityBonus = 0;
        boolean hasHighDamage = false;
        boolean hasDebuff = false;
        boolean hasMultiTarget = false;
        
        for (AbstractGameAction action : actions) {
            String actionType = action.getClass().getSimpleName();
            
            // 检查高伤害动作
            if (actionType.contains("Damage")) {
                hasHighDamage = true;
            }
            
            // 检查减益动作
            if (actionType.contains("ApplyPower") || actionType.contains("Debuff")) {
                hasDebuff = true;
            }
            
            // 检查多目标动作
            if (actionType.contains("DamageAll") || actionType.contains("ApplyPowerToAll")) {
                hasMultiTarget = true;
            }
        }
        
        // 多目标动作优先级最高
        if (hasMultiTarget) {
            priorityBonus += 2;
        }
        
        // 减益动作优先级较高
        if (hasDebuff) {
            priorityBonus += 1;
        }
        
        // 高伤害动作优先级较高
        if (hasHighDamage) {
            priorityBonus += 1;
        }
        
        return priorityBonus;
    }
    
    /**
     * 根据元数据调整优先级
     */
    private void adjustPriorityByMetadata(MonsterIntent intent, PriorityAnalysis analysis) {
        if (intent.getMetadata() == null) {
            return;
        }
        
        int metadataPriority = intent.getMetadata().getPriority();
        if (metadataPriority > 0) {
            // 元数据优先级直接映射到优先级分数
            int adjustedScore = Math.min(4, Math.max(1, metadataPriority));
            if (adjustedScore > analysis.getPriorityScore()) {
                analysis.setPriorityScore(adjustedScore);
                analysis.setPriorityReason(analysis.getPriorityReason() + "，元数据优先级：" + metadataPriority);
            }
        }
        
        // 检查元数据中的特殊标记
        if (intent.getMetadata().hasProperty("critical")) {
            boolean isCritical = intent.getMetadata().getBooleanParameter("critical");
            if (isCritical) {
                int newScore = Math.min(4, analysis.getPriorityScore() + 1);
                analysis.setPriorityScore(newScore);
                analysis.setPriorityReason(analysis.getPriorityReason() + "，关键意图");
            }
        }
        
        if (intent.getMetadata().hasProperty("urgent")) {
            boolean isUrgent = intent.getMetadata().getBooleanParameter("urgent");
            if (isUrgent) {
                int newScore = Math.min(4, analysis.getPriorityScore() + 1);
                analysis.setPriorityScore(newScore);
                analysis.setPriorityReason(analysis.getPriorityReason() + "，紧急意图");
            }
        }
    }
    
    /**
     * 调整复合意图优先级
     */
    private void adjustPriorityForCompositeIntent(MonsterIntent intent, PriorityAnalysis analysis) {
        // 检查是否明确标记为复合意图
        if (intent.hasProperty("isComposite") && intent.getBooleanParameter("isComposite")) {
            int newScore = Math.min(4, analysis.getPriorityScore() + 1);
            analysis.setPriorityScore(newScore);
            analysis.setPriorityReason(analysis.getPriorityReason() + "，明确复合意图");
            return;
        }
        
        // 检查次要意图类型
        if (intent.hasProperty("secondaryIntent")) {
            String secondaryIntentStr = intent.getStringParameter("secondaryIntent");
            try {
                IntentType secondaryType = IntentType.fromCode(secondaryIntentStr);
                PriorityAnalysis secondaryAnalysis = PriorityAnalysis.analyzeIntentType(secondaryType);
                
                // 复合意图优先级 = 较高的优先级 + 可能的提升
                int compositeScore = Math.max(analysis.getPriorityScore(), secondaryAnalysis.getPriorityScore());
                
                // 如果两个都是高优先级意图，则再提升一级
                if (analysis.getPriorityScore() >= 3 && secondaryAnalysis.getPriorityScore() >= 3) {
                    compositeScore = Math.min(4, compositeScore + 1);
                }
                
                analysis.setPriorityScore(compositeScore);
                analysis.setPriorityReason(analysis.getPriorityReason() + "，复合意图：" + 
                    intent.getType().getDisplayName() + " + " + secondaryType.getDisplayName());
            } catch (Exception e) {
                // 忽略解析错误
            }
        }
        
        // 检查多目标意图
        if (intent.hasProperty("multiTarget") && intent.getBooleanParameter("multiTarget")) {
            int newScore = Math.min(4, analysis.getPriorityScore() + 1);
            analysis.setPriorityScore(newScore);
            analysis.setPriorityReason(analysis.getPriorityReason() + "，多目标意图");
        }
        
        // 检查特殊效果
        if (intent.hasProperty("specialEffect")) {
            int newScore = Math.min(4, analysis.getPriorityScore() + 1);
            analysis.setPriorityScore(newScore);
            analysis.setPriorityReason(analysis.getPriorityReason() + "，特殊效果");
        }
    }
    
    /**
     * 分析意图优先级等级
     */
    public PriorityAnalysis.PriorityLevel determinePriorityLevel(MonsterIntent intent) {
        PriorityAnalysis analysis = analyzePriority(intent);
        return analysis.getPriorityLevel();
    }
    
    /**
     * 检查是否为高优先级意图
     */
    public boolean isHighPriority(MonsterIntent intent) {
        PriorityAnalysis analysis = analyzePriority(intent);
        return analysis.getPriorityLevel() == PriorityAnalysis.PriorityLevel.CRITICAL ||
               analysis.getPriorityLevel() == PriorityAnalysis.PriorityLevel.HIGH;
    }
    
    /**
     * 检查是否为关键优先级意图
     */
    public boolean isCriticalPriority(MonsterIntent intent) {
        PriorityAnalysis analysis = analyzePriority(intent);
        return analysis.getPriorityLevel() == PriorityAnalysis.PriorityLevel.CRITICAL;
    }
    
    /**
     * 比较两个意图的优先级
     */
    public int compareIntentPriority(MonsterIntent intent1, MonsterIntent intent2) {
        PriorityAnalysis analysis1 = analyzePriority(intent1);
        PriorityAnalysis analysis2 = analyzePriority(intent2);
        
        return Integer.compare(analysis2.getPriorityScore(), analysis1.getPriorityScore());
    }
    
    /**
     * 获取意图的威胁等级描述
     */
    public String getThreatLevelDescription(MonsterIntent intent) {
        PriorityAnalysis analysis = analyzePriority(intent);
        
        switch (analysis.getPriorityLevel()) {
            case CRITICAL:
                return "致命威胁";
            case HIGH:
                return "高威胁";
            case NORMAL:
                return "普通威胁";
            case LOW:
                return "低威胁";
            default:
                return "未知威胁";
        }
    }
}