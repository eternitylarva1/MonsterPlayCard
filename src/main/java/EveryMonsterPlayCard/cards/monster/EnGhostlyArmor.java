package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterExhaustAllEtherealAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 幽灵盔甲 - 怪物版
 * 获得格挡，回合结束时弃置所有虚无牌
 */
public class EnGhostlyArmor extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Ghostly Armor";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Ghostly Armor");

    public EnGhostlyArmor() {
        super("downfall_Charboss:Ghostly Armor", cardStrings.NAME, "red/skill/ghostly_armor", 1, cardStrings.DESCRIPTION,
              CardType.SKILL, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF);

        this.isEthereal = true;
        this.baseBlock = 10;
        this.block = this.baseBlock;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
    }

    public void triggerOnEndOfPlayerTurn() {
        addToTop((AbstractGameAction)new MonsterExhaustAllEtherealAction());
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnGhostlyArmor();
    }
}