package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EnBlur extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Blur";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Blur");

    public EnBlur() {
        super("downfall_Charboss:Blur", cardStrings.NAME, "green/skill/blur", 1, cardStrings.DESCRIPTION, 
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        this.baseBlock=this.block = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public AbstractCard makeCopy() {
        return new EnBlur();
    }
}
