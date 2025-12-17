package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterGainEnergyAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

/**
 * 看见红色 - 获得愤怒
 */
public class EnSeeingRed extends AbstractMonsterCard {
    public static final String ID = "EnSeeingRed";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Seeing Red");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnSeeingRed() {
        super(ID, NAME, "red/skill/seeing_red", 1, DESCRIPTION,
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED,
              AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE);
        this.exhaust = true;
        // 注意：energyGeneratedIfPlayed字段在AbstractCard中不存在，这里移除
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new MonsterGainEnergyAction(2));
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new EnSeeingRed();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        } 
    }
}