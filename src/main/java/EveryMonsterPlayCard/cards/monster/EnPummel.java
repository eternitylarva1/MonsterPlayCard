package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

/**
 * 连续重击 - 多段攻击
 */
public class EnPummel extends AbstractMonsterCard {
    public static final String ID = "EnPummel";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Pummel");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnPummel() {
        super(ID, NAME, "red/attack/pummel", 1, DESCRIPTION, 
              AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, 
              AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 2;
        this.exhaust = true;
        this.baseMagicNumber = 4;
        this.isMultiDamage = true;
        this.magicNumber = this.baseMagicNumber;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(m); // 关键修复：计算伤害
        
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot((AbstractGameAction)new DamageAction(p, new DamageInfo(m, this.damage, this.damageTypeForTurn),
                AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }
    
    @Override
    public int getPriority(ArrayList<AbstractCard> hand) {
        int badCards = 0;
        for (AbstractCard c : hand) {
            if (c.type == AbstractCard.CardType.CURSE || c.type == AbstractCard.CardType.STATUS) {
                badCards++;
            }
        }
        
        // 使用基础优先级加上坏牌数量的加成
        return 10 + badCards * 5;
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnPummel();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        } 
    }
}