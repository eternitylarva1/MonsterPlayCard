package EveryMonsterPlayCard.intent2card.analyzer;

import java.util.HashMap;
import java.util.Map;

import EveryMonsterPlayCard.core.data.IntentType;

/**
 * 意图分析结果
 */
public class IntentAnalysisResult {
    private IntentType primaryType;
    private IntentType secondaryType;
    private int complexity;
    private int priority;
    private Map<String, Object> extractedParameters;
    private boolean isComposite;
    private String analysisSummary;
    
    public IntentAnalysisResult() {
        this.extractedParameters = new HashMap<>();
        this.complexity = 1;
        this.priority = 0;
        this.isComposite = false;
    }
    
    public IntentAnalysisResult(IntentType primaryType) {
        this();
        this.primaryType = primaryType;
    }
    
    public IntentType getPrimaryType() {
        return primaryType;
    }
    
    public void setPrimaryType(IntentType primaryType) {
        this.primaryType = primaryType;
    }
    
    public IntentType getSecondaryType() {
        return secondaryType;
    }
    
    public void setSecondaryType(IntentType secondaryType) {
        this.secondaryType = secondaryType;
    }
    
    public int getComplexity() {
        return complexity;
    }
    
    public void setComplexity(int complexity) {
        this.complexity = Math.max(1, Math.min(10, complexity));
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public Map<String, Object> getExtractedParameters() {
        return extractedParameters;
    }
    
    public void setExtractedParameters(Map<String, Object> extractedParameters) {
        this.extractedParameters = extractedParameters;
    }
    
    public void addParameter(String key, Object value) {
        this.extractedParameters.put(key, value);
    }
    
    public Object getParameter(String key) {
        return this.extractedParameters.get(key);
    }
    
    public boolean hasParameter(String key) {
        return this.extractedParameters.containsKey(key);
    }
    
    public boolean isComposite() {
        return isComposite;
    }
    
    public void setComposite(boolean composite) {
        isComposite = composite;
    }
    
    public String getAnalysisSummary() {
        return analysisSummary;
    }
    
    public void setAnalysisSummary(String analysisSummary) {
        this.analysisSummary = analysisSummary;
    }
    
    /**
     * 检查是否为攻击意图
     */
    public boolean isAttackIntent() {
        return primaryType == IntentType.ATTACK || primaryType == IntentType.STRONG;
    }
    
    /**
     * 检查是否为防御意图
     */
    public boolean isDefendIntent() {
        return primaryType == IntentType.DEFEND;
    }
    
    /**
     * 检查是否为增益意图
     */
    public boolean isBuffIntent() {
        return primaryType == IntentType.BUFF;
    }
    
    /**
     * 检查是否为减益意图
     */
    public boolean isDebuffIntent() {
        return primaryType == IntentType.DEBUFF;
    }
    
    /**
     * 检查是否为复合意图
     */
    public boolean hasSecondaryType() {
        return secondaryType != null && secondaryType != IntentType.UNKNOWN;
    }
    
    @Override
    public String toString() {
        return "IntentAnalysisResult{" +
                "primaryType=" + primaryType +
                ", secondaryType=" + secondaryType +
                ", complexity=" + complexity +
                ", priority=" + priority +
                ", isComposite=" + isComposite +
                ", parametersCount=" + extractedParameters.size() +
                '}';
    }
}