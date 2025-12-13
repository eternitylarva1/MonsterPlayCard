package EveryMonsterPlayCard.downfall.analyzer;

import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 移植报告
 */
public class MigrationReport {
    private CardAnalysisResult cardAnalysisResult;
    private LogicAnalysisResult logicAnalysisResult;
    private DependencyAnalysisResult dependencyAnalysisResult;
    private Class<? extends AbstractCard> cardClass;
    private long generatedTime;
    private int migrationDifficulty; // 0-10, 越高越难
    private String suggestedTemplate;
    private String migrationNotes;
    
    public MigrationReport() {
        generatedTime = System.currentTimeMillis();
        migrationDifficulty = 0;
    }
    
    // Getters and setters
    public CardAnalysisResult getCardAnalysisResult() { return cardAnalysisResult; }
    public void setCardAnalysisResult(CardAnalysisResult cardAnalysisResult) { this.cardAnalysisResult = cardAnalysisResult; }
    
    public LogicAnalysisResult getLogicAnalysisResult() { return logicAnalysisResult; }
    public void setLogicAnalysisResult(LogicAnalysisResult logicAnalysisResult) { this.logicAnalysisResult = logicAnalysisResult; }
    
    public DependencyAnalysisResult getDependencyAnalysisResult() { return dependencyAnalysisResult; }
    public void setDependencyAnalysisResult(DependencyAnalysisResult dependencyAnalysisResult) { this.dependencyAnalysisResult = dependencyAnalysisResult; }
    
    public Class<? extends AbstractCard> getCardClass() { return cardClass; }
    public void setCardClass(Class<? extends AbstractCard> cardClass) { this.cardClass = cardClass; }
    
    public long getGeneratedTime() { return generatedTime; }
    public void setGeneratedTime(long generatedTime) { this.generatedTime = generatedTime; }
    
    public int getMigrationDifficulty() { return migrationDifficulty; }
    public void setMigrationDifficulty(int migrationDifficulty) { this.migrationDifficulty = migrationDifficulty; }
    
    public String getSuggestedTemplate() { return suggestedTemplate; }
    public void setSuggestedTemplate(String suggestedTemplate) { this.suggestedTemplate = suggestedTemplate; }
    
    public String getMigrationNotes() { return migrationNotes; }
    public void setMigrationNotes(String migrationNotes) { this.migrationNotes = migrationNotes; }
    
    @Override
    public String toString() {
        return "MigrationReport{" +
               "cardId=" + (cardAnalysisResult != null ? cardAnalysisResult.getCardId() : "null") +
               ", difficulty=" + migrationDifficulty +
               ", template=" + suggestedTemplate +
               '}';
    }
}