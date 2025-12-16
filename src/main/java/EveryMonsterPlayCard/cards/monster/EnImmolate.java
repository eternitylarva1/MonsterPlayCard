package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 献祭 - 怪物版
 * 造成火焰伤害，在弃牌堆中添加燃烧牌
 */
public class EnImmolate extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Immolate";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Immolate");

    public EnImmolate() {
        super("downfall_Charboss:Immolate", cardStrings.NAME, "red/attack/immolate", 2, cardStrings.DESCRIPTION,
              CardType.ATTACK, CardColor.RED, CardRarity.RARE, CardTarget.ENEMY);

        this.baseDamage = 21;
        this.damage = this.baseDamage;
        this.tags.add(AbstractCard.CardTags.STRIKE);
        this.cardsToPreview = (AbstractCard)new Burn();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 造成火焰伤害
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        
        // 在弃牌堆中添加燃烧牌
        addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Burn(), 1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(7);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnImmolate();
    }
}