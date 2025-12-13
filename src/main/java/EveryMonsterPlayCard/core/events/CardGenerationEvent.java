package EveryMonsterPlayCard.core.events;

import com.megacrit.cardcrawl.cards.AbstractCard;

import EveryMonsterPlayCard.core.data.GenerationResult;
import EveryMonsterPlayCard.core.data.MonsterIntent;
import EveryMonsterPlayCard.core.interfaces.IEvent;

/**
 * 卡牌生成事件
 */
public class CardGenerationEvent implements IEvent {
    private final long timestamp;
    private final Object source;
    private final MonsterIntent originalIntent;
    private final AbstractCard generatedCard;
    private final GenerationResult generationResult;
    private final String eventType;
    
    public CardGenerationEvent(Object source, MonsterIntent originalIntent, AbstractCard generatedCard, GenerationResult generationResult) {
        this.timestamp = System.currentTimeMillis();
        this.source = source;
        this.originalIntent = originalIntent;
        this.generatedCard = generatedCard;
        this.generationResult = generationResult;
        this.eventType = "CardGeneration";
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
    
    public MonsterIntent getOriginalIntent() {
        return originalIntent;
    }
    
    public AbstractCard getGeneratedCard() {
        return generatedCard;
    }
    
    public GenerationResult getGenerationResult() {
        return generationResult;
    }
    
    public boolean isSuccessful() {
        return generationResult != null && generationResult.isSuccess();
    }
    
    public double getQualityScore() {
        return generationResult != null ? generationResult.getQualityScore() : 0.0;
    }
    
    @Override
    public String getDescription() {
        if (isSuccessful()) {
            return String.format("Card generation event: %s -> %s (quality: %.1f)", 
                              originalIntent != null ? originalIntent.getType().getDisplayName() : "null",
                              generatedCard != null ? generatedCard.name : "null",
                              getQualityScore());
        } else {
            return String.format("Card generation event: %s -> %s (failed: %s)", 
                              originalIntent != null ? originalIntent.getType().getDisplayName() : "null",
                              generatedCard != null ? generatedCard.name : "null",
                              generationResult != null ? generationResult.getMessage() : "unknown error");
        }
    }
}