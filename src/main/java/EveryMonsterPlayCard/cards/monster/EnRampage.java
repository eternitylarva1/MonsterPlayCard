package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterModifyDamageAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.UUID;

/**
 * 狂暴 - 伤害递增
 */
public class EnRampage extends AbstractMonsterCard {
    public static final String ID = "EnRampage";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Rampage");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    
    public EnRampage() {
        super(ID, NAME, "red/attack/rampage", 1, DESCRIPTION, 
              AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, 
              AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction(p, new DamageInfo(m, this.damage, this.damageTypeForTurn), 
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot((AbstractGameAction)new MonsterModifyDamageAction(this.uuid, this.magicNumber));
    }
    
    @Override
    public int getPriority(ArrayList<AbstractCard> hand) {
        return 10 * 2; // 使用基础优先级的2倍
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnRampage();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
            initializeDescription();
        } 
    }
}