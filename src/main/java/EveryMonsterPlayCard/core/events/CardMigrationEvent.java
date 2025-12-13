package EveryMonsterPlayCard.core.events;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.MigrationResult;
import EveryMonsterPlayCard.core.interfaces.IEvent;

/**
 * 卡牌移植事件
 */
public class CardMigrationEvent implements IEvent {
    private final long timestamp;
    private final Object source;
    private final AbstractCard originalCard;
    private final AbstractCard migratedCard;
    private final MigrationResult migrationResult;
    private final String eventType;
    
    public CardMigrationEvent(Object source, AbstractCard originalCard, AbstractCard migratedCard, MigrationResult migrationResult) {
        this.timestamp = System.currentTimeMillis();
        this.source = source;
        this.originalCard = originalCard;
        this.migratedCard = migratedCard;
        this.migrationResult = migrationResult;
        this.eventType = "CardMigration";
    }
    
    @Override
    public long getTimestamp() {
        return timestamp;
    }
    
    @Override
    public Object getSource() {
        return source;
    }
    
    @Override
    public String getEventType() {
        return eventType;
    }
    
    public AbstractCard getOriginalCard() {
        return originalCard;
    }
    
    public AbstractCard getMigratedCard() {
        return migratedCard;
    }
    
    public MigrationResult getMigrationResult() {
        return migrationResult;
    }
    
    public boolean isSuccessful() {
        return migrationResult != null && migrationResult.isSuccess();
    }
    
    @Override
    public String getDescription() {
        return String.format("Card migration event: %s -> %s (success: %s)", 
                          originalCard != null ? originalCard.name : "null",
                          migratedCard != null ? migratedCard.name : "null",
                          isSuccessful());
    }
}