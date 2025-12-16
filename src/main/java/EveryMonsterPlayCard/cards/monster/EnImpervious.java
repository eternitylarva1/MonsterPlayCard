package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 无敌 - 怪物版
 * 获得大量格挡，消耗
 */
public class EnImpervious extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Impervious";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Impervious");

    public EnImpervious() {
        super("downfall_Charboss:Impervious", cardStrings.NAME, "red/skill/impervious", 2, cardStrings.DESCRIPTION,
              CardType.SKILL, CardColor.RED, CardRarity.RARE, CardTarget.SELF);

        this.baseBlock = 30;
        this.block = this.baseBlock;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(10);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnImpervious();
    }
}