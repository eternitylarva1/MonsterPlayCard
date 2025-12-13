package EveryMonsterPlayCard.conversion.analyzer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

/**
 * 模拟能力动作
 * 用于分析和转换的模拟动作类
 */
public class SimulatedPowerAction extends AbstractGameAction {
    private String powerId;
    private int powerAmount;
    private boolean isDebuff;
    
    public SimulatedPowerAction(String powerId, int powerAmount, boolean isDebuff) {
        this.powerId = powerId;
        this.powerAmount = powerAmount;
        this.isDebuff = isDebuff;
        this.actionType = ActionType.POWER;
        this.amount = powerAmount;
    }
    
    public String getPowerId() {
        return powerId;
    }
    
    public void setPowerId(String powerId) {
        this.powerId = powerId;
    }
    
    public int getPowerAmount() {
        return powerAmount;
    }
    
    public void setPowerAmount(int powerAmount) {
        this.powerAmount = powerAmount;
        this.amount = powerAmount;
    }
    
    public boolean isDebuff() {
        return isDebuff;
    }
    
    public void setDebuff(boolean debuff) {
        this.isDebuff = debuff;
    }
    
    @Override
    public void update() {
        // 模拟动作，不实际执行
        this.isDone = true;
    }
    
    @Override
    public String toString() {
        return "SimulatedPowerAction{" +
                "powerId='" + powerId + '\'' +
                ", powerAmount=" + powerAmount +
                ", isDebuff=" + isDebuff +
                '}';
    }
}