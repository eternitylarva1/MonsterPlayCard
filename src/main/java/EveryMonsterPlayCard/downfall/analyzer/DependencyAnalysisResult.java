package EveryMonsterPlayCard.downfall.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * 依赖分析结果
 */
public class DependencyAnalysisResult {
    private String cardId;
    private List<String> requiredMods;
    private List<String> requiredClasses;
    private List<String> requiredPowers;
    private List<String> requiredActions;
    private List<String> requiredRelics;
    private List<String> requiredCards;
    private List<String> requiredKeywords;
    private List<String> requiredImages;
    private List<String> requiredSounds;
    private boolean usesCustomRenderer;
    private boolean usesCustomAnimation;
    private boolean usesCustomPower;
    private boolean usesCustomAction;
    
    public DependencyAnalysisResult() {
        requiredMods = new ArrayList<>();
        requiredClasses = new ArrayList<>();
        requiredPowers = new ArrayList<>();
        requiredActions = new ArrayList<>();
        requiredRelics = new ArrayList<>();
        requiredCards = new ArrayList<>();
        requiredKeywords = new ArrayList<>();
        requiredImages = new ArrayList<>();
        requiredSounds = new ArrayList<>();
    }
    
    // Getters and setters
    public String getCardId() { return cardId; }
    public void setCardId(String cardId) { this.cardId = cardId; }
    
    public List<String> getRequiredMods() { return requiredMods; }
    public void setRequiredMods(List<String> requiredMods) { this.requiredMods = requiredMods; }
    
    public List<String> getRequiredClasses() { return requiredClasses; }
    public void setRequiredClasses(List<String> requiredClasses) { this.requiredClasses = requiredClasses; }
    
    public List<String> getRequiredPowers() { return requiredPowers; }
    public void setRequiredPowers(List<String> requiredPowers) { this.requiredPowers = requiredPowers; }
    
    public List<String> getRequiredActions() { return requiredActions; }
    public void setRequiredActions(List<String> requiredActions) { this.requiredActions = requiredActions; }
    
    public List<String> getRequiredRelics() { return requiredRelics; }
    public void setRequiredRelics(List<String> requiredRelics) { this.requiredRelics = requiredRelics; }
    
    public List<String> getRequiredCards() { return requiredCards; }
    public void setRequiredCards(List<String> requiredCards) { this.requiredCards = requiredCards; }
    
    public List<String> getRequiredKeywords() { return requiredKeywords; }
    public void setRequiredKeywords(List<String> requiredKeywords) { this.requiredKeywords = requiredKeywords; }
    
    public List<String> getRequiredImages() { return requiredImages; }
    public void setRequiredImages(List<String> requiredImages) { this.requiredImages = requiredImages; }
    
    public List<String> getRequiredSounds() { return requiredSounds; }
    public void setRequiredSounds(List<String> requiredSounds) { this.requiredSounds = requiredSounds; }
    
    public boolean usesCustomRenderer() { return usesCustomRenderer; }
    public void setUsesCustomRenderer(boolean usesCustomRenderer) { this.usesCustomRenderer = usesCustomRenderer; }
    
    public boolean usesCustomAnimation() { return usesCustomAnimation; }
    public void setUsesCustomAnimation(boolean usesCustomAnimation) { this.usesCustomAnimation = usesCustomAnimation; }
    
    public boolean usesCustomPower() { return usesCustomPower; }
    public void setUsesCustomPower(boolean usesCustomPower) { this.usesCustomPower = usesCustomPower; }
    
    public boolean usesCustomAction() { return usesCustomAction; }
    public void setUsesCustomAction(boolean usesCustomAction) { this.usesCustomAction = usesCustomAction; }
}