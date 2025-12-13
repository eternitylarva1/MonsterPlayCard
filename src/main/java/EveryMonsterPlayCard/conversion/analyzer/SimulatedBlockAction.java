package EveryMonsterPlayCard.conversion.analyzer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

/**
 * 模拟格挡动作
 * 用于分析和转换的模拟动作类
 */
public class SimulatedBlockAction extends AbstractGameAction {
    private int blockAmount;
    
    public SimulatedBlockAction(int blockAmount) {
        this.blockAmount = blockAmount;
        this.actionType = ActionType.BLOCK;
        this.amount = blockAmount;
    }
    
    public int getBlockAmount() {
        return blockAmount;
    }
    
    public void setBlockAmount(int blockAmount) {
        this.blockAmount = blockAmount;
        this.amount = blockAmount;
    }
    
    @Override
    public void update() {
        // 模拟动作，不实际执行
        this.isDone = true;
    }
    
    @Override
    public String toString() {
        return "SimulatedBlockAction{blockAmount=" + blockAmount + "}";
    }
}