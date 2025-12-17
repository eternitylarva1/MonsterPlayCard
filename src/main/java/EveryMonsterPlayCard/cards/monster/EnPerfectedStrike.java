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
 * 完美打击 - 基于打出的攻击牌
 */
public class EnPerfectedStrike extends AbstractMonsterCard {
    public static final String ID = "EnPerfectedStrike";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Perfected Strike");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnPerfectedStrike() {
        super(ID, NAME, "red/attack/perfected_strike", 2, DESCRIPTION, 
              AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, 
              AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractCard.CardTags.STRIKE);
    }
    
    /**
     * 计算攻击牌数量
     */
    public static int countCards() {
        // 简化版本：固定返回6，实际应该计算场上攻击牌数量
        return 6;
    }
    
    /**
     * 检查是否为攻击牌
     */
    public static boolean isStrike(AbstractCard c) {
        return c.hasTag(AbstractCard.CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction(p, new DamageInfo(m, this.damage, this.damageTypeForTurn), 
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * countCards();
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnPerfectedStrike();
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