package EveryMonsterPlayCard.powers.cardpowers;

import EveryMonsterPlayCard.cards.monster.AbstractMonsterCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EnDoubleTapPower extends AbstractPower {
    public static final String POWER_ID = "Double Tap";
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Double Tap");

    static {
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }

    public EnDoubleTapPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Double Tap";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("doubleTap");
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        // 检查是不是攻击牌，并且是怪物攻击牌
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK && this.amount > 0 && card instanceof AbstractMonsterCard) {
            flash();

            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }

            AbstractCard tmp = card.makeSameInstanceOf();

            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }
            tmp.purgeOnUse = true;

            // 使用怪物的卡牌执行系统
            tmp.use(AbstractDungeon.player, m);

            this.amount--;
            if (this.amount == 0) {
                addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Double Tap"));
            }
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer)
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Double Tap"));
    }
}