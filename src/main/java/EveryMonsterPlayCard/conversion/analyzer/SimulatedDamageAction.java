package EveryMonsterPlayCard.conversion.analyzer;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

/**
 * 模拟伤害动作
 * 用于分析和转换的模拟动作类
 */
public class SimulatedDamageAction extends AbstractGameAction {
    private int damageAmount;
    
    public SimulatedDamageAction(int damageAmount) {
        this.damageAmount = damageAmount;
        this.actionType = ActionType.DAMAGE;
        this.amount = damageAmount;
    }
    
    public int getDamageAmount() {
        return damageAmount;
    }
    
    public void setDamageAmount(int damageAmount) {
        this.damageAmount = damageAmount;
        this.amount = damageAmount;
    }
    
    @Override
    public void update() {
        // 模拟动作，不实际执行
        this.isDone = true;
    }
    
    @Override
    public String toString() {
        return "SimulatedDamageAction{damageAmount=" + damageAmount + "}";
    }
}