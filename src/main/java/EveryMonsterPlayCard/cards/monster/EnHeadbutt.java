package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 头槌 - 怪物版
 * 造成伤害，将弃牌堆顶的牌移回抽牌堆
 */
public class EnHeadbutt extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Headbutt";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Headbutt");

    public EnHeadbutt() {
        super("downfall_Charboss:Headbutt", cardStrings.NAME, "red/attack/headbutt", 1, cardStrings.DESCRIPTION,
              CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = 9;
        this.damage = this.baseDamage;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 造成伤害
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        
        // 将弃牌堆顶的牌移回抽牌堆
        addToBot((AbstractGameAction)new DiscardPileToTopOfDeckAction((AbstractCreature)p));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnHeadbutt();
    }
}