package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BarricadePower;

import java.util.ArrayList;

/**
 * 路障 - 怪物版
 * 获得路障力量，使得格挡在回合结束后不会清零
 * 典型的防御强化能力卡牌
 */
public class EnBarricade extends AbstractMonsterCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Barricade");
    public static final String ID = "EveryMonsterPlayCard:EnBarricade";

    public EnBarricade() {
        super("EveryMonsterPlayCard:EnBarricade", cardStrings.NAME, "red/power/barricade", 3, cardStrings.DESCRIPTION,
              CardType.POWER, CardColor.RED, CardRarity.RARE, CardTarget.SELF);

        this.baseMagicNumber = this.magicNumber = 1;

        // 保持原版费用：3费
        this.cost = 3;
        this.costForTurn = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 检查是否已经有路障力量
        boolean powerExists = false;
        for (AbstractPower pow : m.powers) {
            if (pow.ID.equals("Barricade")) {
                powerExists = true;
                break;
            }
        }

        // 如果没有路障力量，则施加路障力量
        if (!powerExists) {
            AbstractPower barricadePower = new BarricadePower((AbstractCreature)m);
            addToBot(new MonsterApplyPowerAction(m, m, barricadePower, 1));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2); // 升级版费用从3费降到2费
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnBarricade();
    }
}