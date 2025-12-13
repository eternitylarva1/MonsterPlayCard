package EveryMonsterPlayCard.intent2card.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证结果
 */
public class ValidationResult {
    private boolean valid;
    private List<String> errors;
    private List<String> warnings;
    
    public ValidationResult() {
        this.valid = true;
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }
    
    public ValidationResult(boolean valid) {
        this.valid = valid;
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    public List<String> getErrors() {
        return errors;
    }
    
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
    
    public List<String> getWarnings() {
        return warnings;
    }
    
    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }
    
    public void addError(String error) {
        this.errors.add(error);
        this.valid = false;
    }
    
    public void addWarning(String warning) {
        this.warnings.add(warning);
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
    
    public void clear() {
        this.valid = true;
        this.errors.clear();
        this.warnings.clear();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ValidationResult{");
        sb.append("valid=").append(valid);
        sb.append(", errors=").append(errors);
        sb.append(", warnings=").append(warnings);
        sb.append("}");
        return sb.toString();
    }
    
    /**
     * 创建成功的验证结果
     */
    public static ValidationResult success() {
        return new ValidationResult(true);
    }
    
    /**
     * 创建失败的验证结果
     */
    public static ValidationResult failure(String error) {
        ValidationResult result = new ValidationResult(false);
        result.addError(error);
        return result;
    }
    
    /**
     * 创建带有警告的验证结果
     */
    public static ValidationResult withWarning(String warning) {
        ValidationResult result = new ValidationResult(true);
        result.addWarning(warning);
        return result;
    }
    
    /**
     * 创建带有错误的验证结果
     */
    public static ValidationResult withError(String error) {
        ValidationResult result = new ValidationResult(false);
        result.addError(error);
        return result;
    }
}