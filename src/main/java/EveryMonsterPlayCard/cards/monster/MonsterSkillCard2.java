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

public class MonsterSkillCard2 extends AbstractMonsterCard {
    public static final String ID = "EveryMonsterPlayCard:MonsterSkillCard2";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Blur");

    public MonsterSkillCard2() {
        super("EveryMonsterPlayCard:MonsterSkillCard2", cardStrings.NAME, "green/skill/blur", 1, cardStrings.DESCRIPTION, 
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
        return new MonsterSkillCard2();
    }
}
