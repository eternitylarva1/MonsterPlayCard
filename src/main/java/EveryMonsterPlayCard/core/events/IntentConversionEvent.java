package EveryMonsterPlayCard.core.events;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.core.interfaces.IEvent;

/**
 * 意图转换事件
 */
public class IntentConversionEvent implements IEvent {
    private final long timestamp;
    private final Object source;
    private final AbstractGameAction originalAction;
    private final MonsterIntent convertedIntent;
    private final boolean success;
    private final String errorMessage;
    private final String eventType;
    
    public IntentConversionEvent(Object source, AbstractGameAction originalAction, MonsterIntent convertedIntent) {
        this(source, originalAction, convertedIntent, true, null);
    }
    
    public IntentConversionEvent(Object source, AbstractGameAction originalAction, MonsterIntent convertedIntent, boolean success, String errorMessage) {
        this.timestamp = System.currentTimeMillis();
        this.source = source;
        this.originalAction = originalAction;
        this.convertedIntent = convertedIntent;
        this.success = success;
        this.errorMessage = errorMessage;
        this.eventType = "IntentConversion";
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
    
    public AbstractGameAction getOriginalAction() {
        return originalAction;
    }
    
    public MonsterIntent getConvertedIntent() {
        return convertedIntent;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    @Override
    public String getDescription() {
        if (success) {
            return String.format("Intent conversion event: %s -> %s (success)", 
                              originalAction != null ? originalAction.getClass().getSimpleName() : "null",
                              convertedIntent != null ? convertedIntent.getType().getDisplayName() : "null");
        } else {
            return String.format("Intent conversion event: %s -> %s (failed: %s)", 
                              originalAction != null ? originalAction.getClass().getSimpleName() : "null",
                              convertedIntent != null ? convertedIntent.getType().getDisplayName() : "null",
                              errorMessage != null ? errorMessage : "unknown error");
        }
    }
}