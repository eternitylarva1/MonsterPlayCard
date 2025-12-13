package EveryMonsterPlayCard.core.data;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 卡牌生成结果
 */
public class GenerationResult {
    private boolean success;
    private AbstractCard originalIntent;
    private AbstractCard generatedCard;
    private List<String> errors;
    private List<String> warnings;
    private String message;
    private long generationTime;
    private double qualityScore;
    
    public GenerationResult() {
        this.success = false;
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.generationTime = System.currentTimeMillis();
        this.qualityScore = 0.0;
    }
    
    public GenerationResult(boolean success) {
        this();
        this.success = success;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public AbstractCard getOriginalIntent() {
        return originalIntent;
    }
    
    public void setOriginalIntent(AbstractCard originalIntent) {
        this.originalIntent = originalIntent;
    }
    
    public AbstractCard getGeneratedCard() {
        return generatedCard;
    }
    
    public void setGeneratedCard(AbstractCard generatedCard) {
        this.generatedCard = generatedCard;
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    
    public void addError(String error) {
        this.errors.add(error);
    }
    
    public List<String> getWarnings() {
        return warnings;
    }
    
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
    
    public void addWarning(String warning) {
        this.warnings.add(warning);
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public long getGenerationTime() {
        return generationTime;
    }
    
    public void setGenerationTime(long generationTime) {
        this.generationTime = generationTime;
    }
    
    public double getQualityScore() {
        return qualityScore;
    }
    
    public void setQualityScore(double qualityScore) {
        this.qualityScore = qualityScore;
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public boolean hasWarnings() {
        return !warnings.isEmpty();
    }
    
    public int getErrorCount() {
        return errors.size();
    }
    
    public int getWarningCount() {
        return warnings.size();
    }
    
    /**
     * 检查生成质量是否良好
     * 质量分数 >= 7.0 认为是良好的
     */
    public boolean isGoodQuality() {
        return qualityScore >= 7.0;
    }
    
    /**
     * 检查生成质量是否优秀
     * 质量分数 >= 8.5 认为是优秀的
     */
    public boolean isExcellentQuality() {
        return qualityScore >= 8.5;
    }
}