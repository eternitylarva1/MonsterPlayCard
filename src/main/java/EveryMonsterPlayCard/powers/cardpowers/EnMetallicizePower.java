package EveryMonsterPlayCard.powers.cardpowers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 金属化力量 - 回合结束时获得格挡
 */
public class EnMetallicizePower extends AbstractPower {
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Metallicize");
    public static final String POWER_ID = "Metallicize";
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    
    public EnMetallicizePower(AbstractCreature owner, int armorAmt) {
        this.name = NAME;
        this.ID = "Metallicize";
        this.owner = owner;
        this.amount = armorAmt;
        updateDescription();
        loadRegion("armor");
    }

    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_METALLICIZE", 0.05F);
    }

    @Override
    public void updateDescription() {
        if (this.owner.isPlayer) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
        } 
    }
    
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        flash();
        addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
    }
}