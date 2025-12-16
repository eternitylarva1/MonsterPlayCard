package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.HemokinesisEffect;

/**
 * 血控术 - 怪物版
 * 自残换伤害
 */
public class EnHemokinesis extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Hemokinesis";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Hemokinesis");

    public EnHemokinesis() {
        super("downfall_Charboss:Hemokinesis", cardStrings.NAME, "red/attack/hemokinesis", 1, cardStrings.DESCRIPTION,
              CardType.ATTACK, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseDamage = 15;
        this.damage = this.baseDamage;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new HemokinesisEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.5F));
        }
        
        // 怪物自残
        addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)m, (AbstractCreature)m, this.magicNumber));
        // 对玩家造成伤害
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnHemokinesis();
    }
}