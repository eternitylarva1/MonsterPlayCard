package EveryMonsterPlayCard.conversion.analyzer;

import java.util.List;

/**
 * 复合效果分析结果
 * 存储多个动作效果的分析结果
 */
public class CompositeEffectAnalysis {
    private int totalActions;
    private List<String> effectTypes;
    private int totalMagnitude;
    private String primaryTargetType;
    private String compositeType;
    private String analysisSummary;
    private long analysisTime;
    
    public CompositeEffectAnalysis() {
        this.totalActions = 0;
        this.effectTypes = new java.util.ArrayList<>();
        this.totalMagnitude = 0;
        this.primaryTargetType = "UNKNOWN";
        this.compositeType = "UNKNOWN";
        this.analysisTime = System.currentTimeMillis();
    }
    
    public int getTotalActions() {
        return totalActions;
    }
    
    public void setTotalActions(int totalActions) {
        this.totalActions = totalActions;
    }
    
    public List<String> getEffectTypes() {
        return effectTypes;
    }
    
    public void setEffectTypes(List<String> effectTypes) {
        this.effectTypes = effectTypes != null ? new java.util.ArrayList<>(effectTypes) : new java.util.ArrayList<>();
    }
    
    public int getTotalMagnitude() {
        return totalMagnitude;
    }
    
    public void setTotalMagnitude(int totalMagnitude) {
        this.totalMagnitude = totalMagnitude;
    }
    
    public String getPrimaryTargetType() {
        return primaryTargetType;
    }
    
    public void setPrimaryTargetType(String primaryTargetType) {
        this.primaryTargetType = primaryTargetType;
    }
    
    public String getCompositeType() {
        return compositeType;
    }
    
    public void setCompositeType(String compositeType) {
        this.compositeType = compositeType;
    }
    
    public String getAnalysisSummary() {
        return analysisSummary;
    }
    
    public void setAnalysisSummary(String analysisSummary) {
        this.analysisSummary = analysisSummary;
    }
    
    public long getAnalysisTime() {
        return analysisTime;
    }
    
    public void setAnalysisTime(long analysisTime) {
        this.analysisTime = analysisTime;
    }
    
    @Override
    public String toString() {
        return "CompositeEffectAnalysis{" +
                "totalActions=" + totalActions +
                ", effectTypes=" + effectTypes +
                ", totalMagnitude=" + totalMagnitude +
                ", primaryTargetType='" + primaryTargetType + '\'' +
                ", compositeType='" + compositeType + '\'' +
                ", analysisSummary='" + analysisSummary + '\'' +
                ", analysisTime=" + analysisTime +
                '}';
    }
}