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

public class EnDefendRed extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:DefendRed";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_R");

    public EnDefendRed() {
        super("downfall_Charboss:DefendRed", cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION,
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
        this.baseBlock=this.block = 5;
        this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);
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
        return new EnDefendRed();
    }
}
