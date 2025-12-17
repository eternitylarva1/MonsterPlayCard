package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import EveryMonsterPlayCard.powers.cardpowers.EnMetallicizePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

/**
 * 金属化 - 回合末格挡
 */
public class EnMetallicize extends AbstractMonsterCard {
    public static final String ID = "EnMetallicize";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Metallicize");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnMetallicize() {
        super(ID, NAME, "red/power/metallicize", 1, DESCRIPTION, 
              AbstractCard.CardType.POWER, AbstractCard.CardColor.RED, 
              AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new MonsterApplyPowerAction(m, m, 
            (AbstractPower)new EnMetallicizePower(m, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnMetallicize();
    }
    
    @Override
    public int getPriority(ArrayList<AbstractCard> hand) {
        return 50;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        } 
    }
}