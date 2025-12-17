package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterBlockPerNonAttackAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

/**
 * 第二次风 - 格挡时抽牌
 */
public class EnSecondWind extends AbstractMonsterCard {
    public static final String ID = "EnSecondWind";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Second Wind");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnSecondWind() {
        super(ID, NAME, "red/skill/second_wind", 1, DESCRIPTION, 
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, 
              AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock = 5;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new MonsterBlockPerNonAttackAction(this.block));
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new EnSecondWind();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
        } 
    }
}