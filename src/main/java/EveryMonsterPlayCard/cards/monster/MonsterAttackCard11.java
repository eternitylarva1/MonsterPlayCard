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
import com.megacrit.cardcrawl.powers.DoubleTapPower;

import java.util.ArrayList;

public class MonsterAttackCard11 extends AbstractMonsterCard {
    public static final String ID = "EveryMonsterPlayCard:MonsterAttackCard11";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Double Tap");

    public MonsterAttackCard11() {
        super("EveryMonsterPlayCard:MonsterAttackCard11", cardStrings.NAME, "red/skill/double_tap", 1, cardStrings.DESCRIPTION,
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new DoubleTapPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public int getPriority(ArrayList<AbstractCard> hand) {
        for (AbstractCard c : hand) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                return 20;
            }
        }
        return 0;
    }

    public AbstractCard makeCopy() {
        return new MonsterAttackCard11();
    }
}