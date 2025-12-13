package EveryMonsterPlayCard.conversion.analyzer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

/**
 * 单个动作分析结果
 * 存储单个动作的详细分析信息
 */
public class SingleActionAnalysis {
    private AbstractGameAction action;
    private AbstractGameAction.ActionType actionType;
    private String effectType;
    private int amount;
    private AbstractCreature source;
    private AbstractCreature target;
    private ActionParameters parameters;
    private long analysisTime;
    
    public SingleActionAnalysis() {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.effectType = "UNKNOWN";
        this.amount = 0;
        this.parameters = new ActionParameters();
        this.analysisTime = System.currentTimeMillis();
    }
    
    public SingleActionAnalysis(AbstractGameAction action) {
        this();
        this.action = action;
    }
    
    public AbstractGameAction getAction() {
        return action;
    }
    
    public void setAction(AbstractGameAction action) {
        this.action = action;
    }
    
    public AbstractGameAction.ActionType getActionType() {
        return actionType;
    }
    
    public void setActionType(AbstractGameAction.ActionType actionType) {
        this.actionType = actionType;
    }
    
    public String getEffectType() {
        return effectType;
    }
    
    public void setEffectType(String effectType) {
        this.effectType = effectType;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    public AbstractCreature getSource() {
        return source;
    }
    
    public void setSource(AbstractCreature source) {
        this.source = source;
    }
    
    public AbstractCreature getTarget() {
        return target;
    }
    
    public void setTarget(AbstractCreature target) {
        this.target = target;
    }
    
    public ActionParameters getParameters() {
        return parameters;
    }
    
    public void setParameters(ActionParameters parameters) {
        this.parameters = parameters;
    }
    
    public long getAnalysisTime() {
        return analysisTime;
    }
    
    public void setAnalysisTime(long analysisTime) {
        this.analysisTime = analysisTime;
    }
    
    @Override
    public String toString() {
        return "SingleActionAnalysis{" +
                "action=" + (action != null ? action.getClass().getSimpleName() : "null") +
                ", actionType=" + actionType +
                ", effectType='" + effectType + '\'' +
                ", amount=" + amount +
                ", source=" + (source != null ? source.name : "null") +
                ", target=" + (target != null ? target.name : "null") +
                ", analysisTime=" + analysisTime +
                '}';
    }
}