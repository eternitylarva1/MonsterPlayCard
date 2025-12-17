package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

/**
 * 鲁莽冲锋 - 自残换伤害
 */
public class EnRecklessCharge extends AbstractMonsterCard {
    public static final String ID = "EnRecklessCharge";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Reckless Charge");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnRecklessCharge() {
        super(ID, NAME, "red/attack/reckless_charge", 0, DESCRIPTION, 
              AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, 
              AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 7;
        this.cardsToPreview = new Dazed();
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction(p, new DamageInfo(m, this.damage, this.damageTypeForTurn), 
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(new Dazed(), 1));
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new EnRecklessCharge();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        } 
    }
}