package EveryMonsterPlayCard.conversion.analyzer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

/**
 * 模拟抽牌动作
 * 用于分析和转换的模拟动作类
 */
public class SimulatedDrawCardAction extends AbstractGameAction {
    private int drawAmount;
    
    public SimulatedDrawCardAction(int drawAmount) {
        this.drawAmount = drawAmount;
        this.actionType = ActionType.DRAW;
        this.amount = drawAmount;
    }
    
    public int getDrawAmount() {
        return drawAmount;
    }
    
    public void setDrawAmount(int drawAmount) {
        this.drawAmount = drawAmount;
        this.amount = drawAmount;
    }
    
    @Override
    public void update() {
        // 模拟动作，不实际执行
        this.isDone = true;
    }
    
    @Override
    public String toString() {
        return "SimulatedDrawCardAction{drawAmount=" + drawAmount + "}";
    }
}