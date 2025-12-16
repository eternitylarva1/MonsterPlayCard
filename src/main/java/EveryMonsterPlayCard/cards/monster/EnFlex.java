package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import java.util.ArrayList;

/**
 * 屈肌 - 怪物版
 * 0费，获得临时力量
 */
public class EnFlex extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Flex";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Flex");

    public EnFlex() {
        super("downfall_Charboss:Flex", cardStrings.NAME, "red/skill/flex", 0, cardStrings.DESCRIPTION,
              CardType.SKILL, CardColor.RED, CardRarity.COMMON, CardTarget.SELF);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得力量
        addToBot(new MonsterApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, 
            (AbstractPower)new StrengthPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
        // 失去力量（临时力量）
        addToBot(new MonsterApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, 
            (AbstractPower)new LoseStrengthPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
    }

    public int getPriority(ArrayList<AbstractCard> hand) {
        int priority = 100;
        
        if (this.cost > 1) {
            priority -= 1000;
        }
        return priority;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnFlex();
    }
}