package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterGainEnergyAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

/**
 * 哨兵 - 格挡获得额外格挡
 */
public class EnSentinel extends AbstractMonsterCard {
    public static final String ID = "EnSentinel";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Sentinel");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnSentinel() {
        super(ID, NAME, "red/skill/sentinel", 1, DESCRIPTION, 
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, 
              AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = 5;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction(p, p, this.block));
    }
    
    @Override
    public void triggerOnExhaust() {
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToTop((AbstractGameAction)new MonsterGainEnergyAction(3));
        } else {
            AbstractDungeon.actionManager.addToTop((AbstractGameAction)new MonsterGainEnergyAction(2));
        } 
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new EnSentinel();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        } 
    }
}