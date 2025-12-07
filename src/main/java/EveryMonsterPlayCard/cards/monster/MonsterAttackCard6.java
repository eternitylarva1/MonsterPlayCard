package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

public class MonsterAttackCard6 extends AbstractMonsterCard {
    public static final String ID = "EveryMonsterPlayCard:MonsterAttackCard6";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Clothesline");

    public MonsterAttackCard6() {
        super("EveryMonsterPlayCard:MonsterAttackCard6", cardStrings.NAME, "red/attack/clothesline", 2, cardStrings.DESCRIPTION,
              AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 12;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(m);

        AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new MonsterAttackCard6();
    }

    public int getPriority(ArrayList<AbstractCard> hand) {
        return 50;
    }
}