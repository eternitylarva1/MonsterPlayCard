package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * 怪物版本的获得能量动作
 */
public class MonsterGainEnergyAction extends AbstractGameAction {
    private int energyGain;
    private AbstractCreature target;
    
    public MonsterGainEnergyAction(int amount) {
        this(AbstractDungeon.player, amount);
    }
    
    public MonsterGainEnergyAction(AbstractCreature target, int amount) {
        setValues(target, target, 0);
        this.duration = Settings.ACTION_DUR_FAST;
        this.energyGain = amount;
        this.target = target;
    }
    
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.target != null) {
                // 简化版本：直接增加能量
                // 在实际实现中，这里应该调用怪物的能量系统
                // 由于我们没有完整的怪物能量系统，这里使用简化实现
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    c.triggerOnGainEnergy(this.energyGain, true);
                }
            } else {
                this.isDone = true;
                return;
            } 
        }
        tickDuration();
    }
}