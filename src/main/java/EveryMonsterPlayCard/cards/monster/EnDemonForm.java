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
import com.megacrit.cardcrawl.powers.DemonFormPower;

import java.util.ArrayList;

public class EnDemonForm extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Demon Form";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Demon Form");

    public EnDemonForm() {
        super("downfall_Charboss:Demon Form", cardStrings.NAME, "red/power/demon_form", 3, cardStrings.DESCRIPTION,
              AbstractCard.CardType.POWER, AbstractCard.CardColor.RED, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new DemonFormPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
    }

    public int getPriority(ArrayList<AbstractCard> hand) {
        return 200;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new EnDemonForm();
    }
}