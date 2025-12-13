package EveryMonsterPlayCard.conversion.analyzer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

/**
 * 模拟获得能量动作
 * 用于分析和转换的模拟动作类
 */
public class SimulatedGainEnergyAction extends AbstractGameAction {
    private int energyAmount;
    
    public SimulatedGainEnergyAction(int energyAmount) {
        this.energyAmount = energyAmount;
        this.actionType = ActionType.ENERGY;
        this.amount = energyAmount;
    }
    
    public int getEnergyAmount() {
        return energyAmount;
    }
    
    public void setEnergyAmount(int energyAmount) {
        this.energyAmount = energyAmount;
        this.amount = energyAmount;
    }
    
    @Override
    public void update() {
        // 模拟动作，不实际执行
        this.isDone = true;
    }
    
    @Override
    public String toString() {
        return "SimulatedGainEnergyAction{energyAmount=" + energyAmount + "}";
    }
}