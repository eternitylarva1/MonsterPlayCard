package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 放血 - 怪物版
 * 自残换取能量，典型的力量型交换机制
 */
public class MonsterSkillCard3 extends AbstractMonsterCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Bloodletting");
    public static final String ID = "EveryMonsterPlayCard:MonsterSkillCard3";

    public MonsterSkillCard3() {
        super("EveryMonsterPlayCard:MonsterSkillCard3", cardStrings.NAME, "red/skill/bloodletting", 0, cardStrings.DESCRIPTION,
              CardType.SKILL, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        // 对于怪物来说，这个卡牌消耗生命但不需要能量
        this.cost = 0;
        this.costForTurn = 0;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 怪物自残
        addToBot(new LoseHPAction((AbstractCreature)m, (AbstractCreature)m, 3));

        // 怪物获得能量（在怪物系统中可能不需要，但保持完整性）
        addToBot(new GainEnergyAction(this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new MonsterSkillCard3();
    }
}