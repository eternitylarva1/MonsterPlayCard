package EveryMonsterPlayCard.conversion.analyzer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

/**
 * 效果分析结果
 * 存储单个动作效果的分析结果
 */
public class EffectAnalysisResult {
    private AbstractGameAction action;
    private String effectType;
    private String effectDescription;
    private int effectMagnitude;
    private String targetType;
    private long analysisTime;
    
    public EffectAnalysisResult() {
        this.effectType = "UNKNOWN";
        this.effectDescription = "";
        this.effectMagnitude = 0;
        this.targetType = "UNKNOWN";
        this.analysisTime = System.currentTimeMillis();
    }
    
    public EffectAnalysisResult(AbstractGameAction action) {
        this();
        this.action = action;
    }
    
    public AbstractGameAction getAction() {
        return action;
    }
    
    public void setAction(AbstractGameAction action) {
        this.action = action;
    }
    
    public String getEffectType() {
        return effectType;
    }
    
    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }
    
    public String getEffectDescription() {
        return effectDescription;
    }
    
    public void setEffectDescription(String effectDescription) {
        this.effectDescription = effectDescription;
    }
    
    public int getEffectMagnitude() {
        return effectMagnitude;
    }
    
    public void setEffectMagnitude(int effectMagnitude) {
        this.effectMagnitude = effectMagnitude;
    }
    
    public String getTargetType() {
        return targetType;
    }
    
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
    
    public long getAnalysisTime() {
        return analysisTime;
    }
    
    public void setAnalysisTime(long analysisTime) {
        this.analysisTime = analysisTime;
    }
    
    @Override
    public String toString() {
        return "EffectAnalysisResult{" +
                "effectType='" + effectType + '\'' +
                ", effectDescription='" + effectDescription + '\'' +
                ", effectMagnitude=" + effectMagnitude +
                ", targetType='" + targetType + '\'' +
                ", analysisTime=" + analysisTime +
                '}';
    }
}