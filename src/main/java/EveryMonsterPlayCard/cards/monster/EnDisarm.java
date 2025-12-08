package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;

public class EnDisarm extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Disarm";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Disarm");

    public EnDisarm() {
        super("downfall_Charboss:Disarm", cardStrings.NAME, "red/skill/disarm", 1, cardStrings.DESCRIPTION,
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)p, -this.magicNumber), -this.magicNumber));
    }

    public int getPriority(ArrayList<AbstractCard> hand) {
        return 20;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new EnDisarm();
    }
}