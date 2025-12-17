package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterLimitBreakAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import java.util.ArrayList;

/**
 * 突破极限 - 双倍力量
 */
public class EnLimitBreak extends AbstractMonsterCard {
    public static final String ID = "EnLimitBreak";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Limit Break");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnLimitBreak() {
        super(ID, NAME, "red/skill/limit_break", 1, DESCRIPTION, 
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, 
              AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        MonsterLimitBreakAction action = new MonsterLimitBreakAction();
        action.source = m;
        addToBot((AbstractGameAction)action);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnLimitBreak();
    }
    
    @Override
    public int getPriority(ArrayList<AbstractCard> hand) {
        if (this.owningMonster != null) {
            int v = 0;
            if (this.owningMonster.hasPower(StrengthPower.POWER_ID)) {
                v = this.owningMonster.getPower(StrengthPower.POWER_ID).amount;
            }
            // 检查传入的hand参数中是否有EnFlex卡牌
            for (AbstractCard c : hand) {
                if (c.cardID.equals("EnFlex")) {
                    v += c.magicNumber;
                }
            }
            return v * 3;
        }
        return 0;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        } 
    }
}