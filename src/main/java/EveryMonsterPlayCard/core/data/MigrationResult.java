package EveryMonsterPlayCard.core.data;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 卡牌移植结果
 */
public class MigrationResult {
    private boolean success;
    private AbstractCard originalCard;
    private AbstractCard migratedCard;
    private List<String> errors;
    private List<String> warnings;
    private String message;
    private long migrationTime;
    
    public MigrationResult() {
        this.success = false;
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.migrationTime = System.currentTimeMillis();
    }
    
    public MigrationResult(boolean success) {
        this();
        this.success = success;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public AbstractCard getOriginalCard() {
        return originalCard;
    }
    
    public void setOriginalCard(AbstractCard originalCard) {
        this.originalCard = originalCard;
    }
    
    public AbstractCard getMigratedCard() {
        return migratedCard;
    }
    
    public void setMigratedCard(AbstractCard migratedCard) {
        this.migratedCard = migratedCard;
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
    
    public long getMigrationTime() {
        return migrationTime;
    }
    
    public void setMigrationTime(long migrationTime) {
        this.migrationTime = migrationTime;
    }
    
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
    
    public boolean hasWarnings() {
        return !warnings.isEmpty();
    }
}