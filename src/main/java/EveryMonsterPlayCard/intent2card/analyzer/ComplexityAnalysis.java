package EveryMonsterPlayCard.intent2card.analyzer;

import EveryMonsterPlayCard.core.data.IntentType;

/**
 * 意图复杂度分析结果
 */
public class ComplexityAnalysis {
    private int complexityScore;
    private String complexityReason;
    private ComplexityLevel complexityLevel;
    
    public enum ComplexityLevel {
        SIMPLE(1, "简单"),
        MODERATE(2, "中等"),
        COMPLEX(3, "复杂"),
        VERY_COMPLEX(4, "非常复杂");
        
        private final int level;
        private final String description;
        
        ComplexityLevel(int level, String description) {
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
    
    public ComplexityAnalysis() {
        this.complexityScore = 1;
        this.complexityLevel = ComplexityLevel.SIMPLE;
        this.complexityReason = "基础意图";
    }
    
    public ComplexityAnalysis(int complexityScore, String reason) {
        this.complexityScore = Math.max(1, Math.min(10, complexityScore));
        this.complexityReason = reason;
        this.complexityLevel = determineComplexityLevel(this.complexityScore);
    }
    
    public int getComplexityScore() {
        return complexityScore;
    }
    
    public void setComplexityScore(int complexityScore) {
        this.complexityScore = Math.max(1, Math.min(10, complexityScore));
        this.complexityLevel = determineComplexityLevel(this.complexityScore);
    }
    
    public String getComplexityReason() {
        return complexityReason;
    }
    
    public void setComplexityReason(String complexityReason) {
        this.complexityReason = complexityReason;
    }
    
    public ComplexityLevel getComplexityLevel() {
        return complexityLevel;
    }
    
    public void setComplexityLevel(ComplexityLevel complexityLevel) {
        this.complexityLevel = complexityLevel;
    }
    
    /**
     * 根据分数确定复杂度级别
     */
    private ComplexityLevel determineComplexityLevel(int score) {
        if (score <= 2) {
            return ComplexityLevel.SIMPLE;
        } else if (score <= 4) {
            return ComplexityLevel.MODERATE;
        } else if (score <= 7) {
            return ComplexityLevel.COMPLEX;
        } else {
            return ComplexityLevel.VERY_COMPLEX;
        }
    }
    
    /**
     * 分析意图类型的复杂度
     */
    public static ComplexityAnalysis analyzeIntentType(IntentType intentType) {
        int score = 1;
        String reason = "基础意图";
        
        switch (intentType) {
            case ATTACK:
                score = 2;
                reason = "攻击意图，需要计算伤害";
                break;
            case DEFEND:
                score = 2;
                reason = "防御意图，需要计算格挡";
                break;
            case BUFF:
                score = 3;
                reason = "增益意图，需要处理能力效果";
                break;
            case DEBUFF:
                score = 4;
                reason = "减益意图，需要处理负面效果";
                break;
            case STRONG:
                score = 5;
                reason = "强力攻击意图，需要特殊处理";
                break;
            case ESCAPE:
                score = 2;
                reason = "逃跑意图，需要特殊处理";
                break;
            case UNKNOWN:
                score = 6;
                reason = "未知意图，需要额外分析";
                break;
        }
        
        return new ComplexityAnalysis(score, reason);
    }
    
    /**
     * 分析复合意图的复杂度
     */
    public static ComplexityAnalysis analyzeCompositeIntent(IntentType primaryType, IntentType secondaryType) {
        int baseScore = analyzeIntentType(primaryType).getComplexityScore();
        int secondaryScore = analyzeIntentType(secondaryType).getComplexityScore();
        
        // 复合意图增加复杂度
        int compositeScore = baseScore + secondaryScore + 1;
        String reason = "复合意图：" + primaryType.getDisplayName() + " + " + secondaryType.getDisplayName();
        
        return new ComplexityAnalysis(compositeScore, reason);
    }
    
    @Override
    public String toString() {
        return "ComplexityAnalysis{" +
                "complexityScore=" + complexityScore +
                ", complexityLevel=" + complexityLevel.getDescription() +
                ", complexityReason='" + complexityReason + '\'' +
                '}';
    }
}