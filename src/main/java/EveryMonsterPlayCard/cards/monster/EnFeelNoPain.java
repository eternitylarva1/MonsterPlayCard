package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import EveryMonsterPlayCard.powers.cardpowers.EnFeelNoPainPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * 无痛 - 怪物版
 * 每当弃置一张牌时，获得格挡
 */
public class EnFeelNoPain extends AbstractMonsterCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Feel No Pain");
    public static final String ID = "downfall_Charboss:Feel No Pain";

    public EnFeelNoPain() {
        super("downfall_Charboss:Feel No Pain", cardStrings.NAME, "red/power/feel_no_pain", 1, cardStrings.DESCRIPTION,
              CardType.POWER, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 添加无痛力量
        AbstractPower feelNoPainPower = new EnFeelNoPainPower((AbstractCreature)m, this.magicNumber);
        addToBot(new MonsterApplyPowerAction(m, m, feelNoPainPower, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnFeelNoPain();
    }
}