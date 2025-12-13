package EveryMonsterPlayCard.conversion.analyzer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

/**
 * 动作分析结果
 * 存储卡牌动作分析的结果
 */
public class ActionAnalysisResult {
    private AbstractCard card;
    private int actionCount;
    private AbstractGameAction.ActionType primaryActionType;
    private String analysisSummary;
    private long analysisTime;
    
    public ActionAnalysisResult() {
        this.actionCount = 0;
        this.primaryActionType = AbstractGameAction.ActionType.WAIT;
        this.analysisTime = System.currentTimeMillis();
    }
    
    public ActionAnalysisResult(AbstractCard card) {
        this();
        this.card = card;
    }
    
    public AbstractCard getCard() {
        return card;
    }
    
    public void setCard(AbstractCard card) {
        this.card = card;
    }
    
    public int getActionCount() {
        return actionCount;
    }
    
    public void setActionCount(int actionCount) {
        this.actionCount = actionCount;
    }
    
    public AbstractGameAction.ActionType getPrimaryActionType() {
        return primaryActionType;
    }
    
    public void setPrimaryActionType(AbstractGameAction.ActionType primaryActionType) {
        this.primaryActionType = primaryActionType;
    }
    
    public String getAnalysisSummary() {
        return analysisSummary;
    }
    
    public void setAnalysisSummary(String analysisSummary) {
        this.analysisSummary = analysisSummary;
    }
    
    public long getAnalysisTime() {
        return analysisTime;
    }
    
    public void setAnalysisTime(long analysisTime) {
        this.analysisTime = analysisTime;
    }
    
    @Override
    public String toString() {
        return "ActionAnalysisResult{" +
                "card=" + (card != null ? card.name : "null") +
                ", actionCount=" + actionCount +
                ", primaryActionType=" + primaryActionType +
                ", analysisSummary='" + analysisSummary + '\'' +
                ", analysisTime=" + analysisTime +
                '}';
    }
}