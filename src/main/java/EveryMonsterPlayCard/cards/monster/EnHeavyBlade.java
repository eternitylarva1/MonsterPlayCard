package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;

/**
 * 重剑 - 怪物版
 * 基于力量值造成额外伤害
 */
public class EnHeavyBlade extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Heavy Blade";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Heavy Blade");

    public EnHeavyBlade() {
        super("downfall_Charboss:Heavy Blade", cardStrings.NAME, "red/attack/heavy_blade", 2, cardStrings.DESCRIPTION,
              CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = 14;
        this.damage = this.baseDamage;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new VerticalImpactEffect(p.hb.cX + p.hb.width / 4.0F, p.hb.cY - p.hb.height / 4.0F)));
        }
        
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void calculateCardDamage(AbstractMonster mo) {
        // 获取怪物的力量值
        AbstractPower strength = mo.getPower("Strength");
        if (strength != null) {
            strength.amount *= this.magicNumber;
        }
        
        super.calculateCardDamage(mo);
        if (strength != null) {
            strength.amount /= this.magicNumber;
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnHeavyBlade();
    }
}