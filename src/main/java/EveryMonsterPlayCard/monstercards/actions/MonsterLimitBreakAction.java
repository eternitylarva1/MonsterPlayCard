package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

/**
 * 怪物版本的突破极限动作
 * 将当前力量值翻倍
 */
public class MonsterLimitBreakAction extends AbstractGameAction {
    
    public MonsterLimitBreakAction() {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.POWER;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST && this.source != null) {
            if (this.source.hasPower(StrengthPower.POWER_ID)) {
                int strAmt = this.source.getPower(StrengthPower.POWER_ID).amount;
                addToTop((AbstractGameAction)new ApplyPowerAction(this.source, this.source, 
                    (AbstractPower)new StrengthPower(this.source, strAmt), strAmt));
            } 
        }
        tickDuration();
    }
}