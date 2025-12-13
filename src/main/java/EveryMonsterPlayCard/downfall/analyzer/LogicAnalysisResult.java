package EveryMonsterPlayCard.downfall.analyzer;

import java.util.ArrayList;
import java.util.List;

/**
 * 卡牌逻辑分析结果
 */
public class LogicAnalysisResult {
    private String cardId;
    private List<String> actionClasses;
    private List<String> powerClasses;
    private List<String> effectDescriptions;
    private List<String> triggerConditions;
    private List<String> upgradeChanges;
    private boolean hasOnUse;
    private boolean hasOnUpgrade;
    private boolean hasOnDraw;
    private boolean hasOnDiscard;
    private boolean hasOnExhaust;
    private boolean hasOnRetain;
    private String useMethodBody;
    private String upgradeMethodBody;
    
    public LogicAnalysisResult() {
        actionClasses = new ArrayList<>();
        powerClasses = new ArrayList<>();
        effectDescriptions = new ArrayList<>();
        triggerConditions = new ArrayList<>();
        upgradeChanges = new ArrayList<>();
    }
    
    // Getters and setters
    public String getCardId() { return cardId; }
    public void setCardId(String cardId) { this.cardId = cardId; }
    
    public List<String> getActionClasses() { return actionClasses; }
    public void setActionClasses(List<String> actionClasses) { this.actionClasses = actionClasses; }
    
    public List<String> getPowerClasses() { return powerClasses; }
    public void setPowerClasses(List<String> powerClasses) { this.powerClasses = powerClasses; }
    
    public List<String> getEffectDescriptions() { return effectDescriptions; }
    public void setEffectDescriptions(List<String> effectDescriptions) { this.effectDescriptions = effectDescriptions; }
    
    public List<String> getTriggerConditions() { return triggerConditions; }
    public void setTriggerConditions(List<String> triggerConditions) { this.triggerConditions = triggerConditions; }
    
    public List<String> getUpgradeChanges() { return upgradeChanges; }
    public void setUpgradeChanges(List<String> upgradeChanges) { this.upgradeChanges = upgradeChanges; }
    
    public boolean hasOnUse() { return hasOnUse; }
    public void setHasOnUse(boolean hasOnUse) { this.hasOnUse = hasOnUse; }
    
    public boolean hasOnUpgrade() { return hasOnUpgrade; }
    public void setHasOnUpgrade(boolean hasOnUpgrade) { this.hasOnUpgrade = hasOnUpgrade; }
    
    public boolean hasOnDraw() { return hasOnDraw; }
    public void setHasOnDraw(boolean hasOnDraw) { this.hasOnDraw = hasOnDraw; }
    
    public boolean hasOnDiscard() { return hasOnDiscard; }
    public void setHasOnDiscard(boolean hasOnDiscard) { this.hasOnDiscard = hasOnDiscard; }
    
    public boolean hasOnExhaust() { return hasOnExhaust; }
    public void setHasOnExhaust(boolean hasOnExhaust) { this.hasOnExhaust = hasOnExhaust; }
    
    public boolean hasOnRetain() { return hasOnRetain; }
    public void setHasOnRetain(boolean hasOnRetain) { this.hasOnRetain = hasOnRetain; }
    
    public String getUseMethodBody() { return useMethodBody; }
    public void setUseMethodBody(String useMethodBody) { this.useMethodBody = useMethodBody; }
    
    public String getUpgradeMethodBody() { return upgradeMethodBody; }
    public void setUpgradeMethodBody(String upgradeMethodBody) { this.upgradeMethodBody = upgradeMethodBody; }
}