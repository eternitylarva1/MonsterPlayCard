package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterMakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

/**
 * 挺过 - 获得额外格挡
 */
public class EnPowerThrough extends AbstractMonsterCard {
    public static final String ID = "EnPowerThrough";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Power Through");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    private boolean addWoundsToPlayer = false;
    
    public EnPowerThrough() {
        super(ID, NAME, "red/skill/power_through", 1, DESCRIPTION, 
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, 
              AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = 15;
        this.cardsToPreview = new Wound();
        addWoundsToPlayer = false;
    }
    
    public EnPowerThrough(boolean wounds) {
        this();
        addWoundsToPlayer = true;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new MonsterMakeTempCardInHandAction(new EnWound(), 2));
        addToBot((AbstractGameAction)new GainBlockAction(m, m, this.block));
        if (addWoundsToPlayer) {
            addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(new Wound(), 2));
        }
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new EnPowerThrough();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        } 
    }
}