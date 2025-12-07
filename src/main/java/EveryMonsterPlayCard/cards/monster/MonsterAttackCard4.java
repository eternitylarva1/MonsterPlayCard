package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.ViolentAttackEffect;
import com.badlogic.gdx.graphics.Color;

public class MonsterAttackCard4 extends AbstractMonsterCard {
    public static final String ID = "EveryMonsterPlayCard:MonsterAttackCard4";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Carnage");

    public MonsterAttackCard4() {
        super("EveryMonsterPlayCard:MonsterAttackCard4", cardStrings.NAME, "red/attack/carnage", 2, cardStrings.DESCRIPTION,
              AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 20;
        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(m);

        if (Settings.FAST_MODE) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction((AbstractGameEffect)new ViolentAttackEffect(p.hb.cX, p.hb.cY, Color.RED.cpy())));

            for (int i = 0; i < 5; i++) {
                AbstractDungeon.actionManager.addToBottom(new VFXAction((AbstractGameEffect)new StarBounceEffect(p.hb.cX, p.hb.cY)));
            }
        } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction((AbstractGameEffect)new ViolentAttackEffect(p.hb.cX, p.hb.cY, Color.RED.cpy()), 0.4F));

            for (int i = 0; i < 5; i++) {
                AbstractDungeon.actionManager.addToBottom(new VFXAction((AbstractGameEffect)new StarBounceEffect(p.hb.cX, m.hb.cY)));
            }
        }

        AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(8);
        }
    }

    public AbstractCard makeCopy() {
        return new MonsterAttackCard4();
    }
}