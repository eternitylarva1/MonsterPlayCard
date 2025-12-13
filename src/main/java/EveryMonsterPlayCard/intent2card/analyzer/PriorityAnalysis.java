package EveryMonsterPlayCard.intent2card.analyzer;

import EveryMonsterPlayCard.core.data.IntentType;

/**
 * 意图优先级分析结果
 */
public class PriorityAnalysis {
    private int priorityScore;
    private String priorityReason;
    private PriorityLevel priorityLevel;
    
    public enum PriorityLevel {
        LOW(1, "低优先级"),
        NORMAL(2, "普通优先级"),
        HIGH(3, "高优先级"),
        CRITICAL(4, "关键优先级");
        
        private final int level;
        private final String description;
        
        PriorityLevel(int level, String description) {
            this.level = level;
            this.description = description;
        }
        
        public int getLevel() {
            return level;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    public PriorityAnalysis() {
        this.priorityScore = 2;
        this.priorityLevel = PriorityLevel.NORMAL;
        this.priorityReason = "普通意图";
    }
    
    public PriorityAnalysis(int priorityScore, String reason) {
        this.priorityScore = Math.max(1, Math.min(4, priorityScore));
        this.priorityReason = reason;
        this.priorityLevel = determinePriorityLevel(this.priorityScore);
    }
    
    public int getPriorityScore() {
        return priorityScore;
    }
    
    public void setPriorityScore(int priorityScore) {
        this.priorityScore = Math.max(1, Math.min(4, priorityScore));
        this.priorityLevel = determinePriorityLevel(this.priorityScore);
    }
    
    public String getPriorityReason() {
        return priorityReason;
    }
    
    public void setPriorityReason(String priorityReason) {
        this.priorityReason = priorityReason;
    }
    
    public PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }
    
    public void setPriorityLevel(PriorityLevel priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
    
    /**
     * 根据分数确定优先级级别
     */
    private PriorityLevel determinePriorityLevel(int score) {
        switch (score) {
            case 1:
                return PriorityLevel.LOW;
            case 2:
                return PriorityLevel.NORMAL;
            case 3:
                return PriorityLevel.HIGH;
            case 4:
                return PriorityLevel.CRITICAL;
            default:
                return PriorityLevel.NORMAL;
        }
    }
    
    /**
     * 分析意图类型的优先级
     */
    public static PriorityAnalysis analyzeIntentType(IntentType intentType) {
        int score = 2;
        String reason = "普通意图";
        
        switch (intentType) {
            case ATTACK:
                score = 3;
                reason = "攻击意图，影响玩家生命值";
                break;
            case DEFEND:
                score = 2;
                reason = "防御意图，影响怪物生存能力";
                break;
            case BUFF:
                score = 3;
                reason = "增益意图，增强怪物能力";
                break;
            case DEBUFF:
                score = 3;
                reason = "减益意图，削弱玩家能力";
                break;
            case STRONG:
                score = 4;
                reason = "强力攻击意图，高威胁";
                break;
            case ESCAPE:
                score = 1;
                reason = "逃跑意图，低威胁";
                break;
            case UNKNOWN:
                score = 2;
                reason = "未知意图，需要进一步分析";
                break;
        }
        
        return new PriorityAnalysis(score, reason);
    }
    
    /**
     * 分析复合意图的优先级
     */
    public static PriorityAnalysis analyzeCompositeIntent(IntentType primaryType, IntentType secondaryType) {
        int primaryScore = analyzeIntentType(primaryType).getPriorityScore();
        int secondaryScore = analyzeIntentType(secondaryType).getPriorityScore();
        
        // 复合意图取较高优先级，并可能提升一级
        int compositeScore = Math.max(primaryScore, secondaryScore);
        if (primaryScore >= 3 && secondaryScore >= 3) {
            compositeScore = Math.min(4, compositeScore + 1);
        }
        
        String reason = "复合意图：" + primaryType.getDisplayName() + " + " + secondaryType.getDisplayName();
        
        return new PriorityAnalysis(compositeScore, reason);
    }
    
    /**
     * 根据意图数量分析优先级
     */
    public static PriorityAnalysis analyzeByIntentAmount(int amount) {
        int score = 2;
        String reason = "普通数量";
        
        if (amount >= 20) {
            score = 4;
            reason = "高伤害意图，关键威胁";
        } else if (amount >= 15) {
            score = 3;
            reason = "中等伤害意图，高威胁";
        } else if (amount >= 10) {
            score = 3;
            reason = "中等伤害意图";
        } else if (amount >= 5) {
            score = 2;
            reason = "低伤害意图";
        } else {
            score = 1;
            reason = "极低伤害意图";
        }
        
        return new PriorityAnalysis(score, reason);
    }
    
    @Override
    public String toString() {
        return "PriorityAnalysis{" +
                "priorityScore=" + priorityScore +
                ", priorityLevel=" + priorityLevel.getDescription() +
                ", priorityReason='" + priorityReason + '\'' +
                '}';
    }
}