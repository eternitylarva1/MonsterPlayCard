package EveryMonsterPlayCard.core.data;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 验证结果
 */
public class ValidationResult {
    private boolean valid;
    private AbstractCard card;
    private List<String> errors;
    private List<String> warnings;
    private String message;
    private long validationTime;
    
    public ValidationResult() {
        this.valid = false;
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.validationTime = System.currentTimeMillis();
    }
    
    public ValidationResult(boolean valid) {
        this();
        this.valid = valid;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    public AbstractCard getCard() {
        return card;
    }
    
    public void setCard(AbstractCard card) {
        this.card = card;
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
    
    public long getValidationTime() {
        return validationTime;
    }
    
    public void setValidationTime(long validationTime) {
        this.validationTime = validationTime;
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
}