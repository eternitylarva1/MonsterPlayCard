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
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import com.badlogic.gdx.graphics.Color;

/**
 * 愤怒 - 怪物版
 * 造成伤害并将自己的复制品加入弃牌堆
 * 这是经典的"愤怒"机制：每用一次就多一张
 */
public class EnAnger extends AbstractMonsterCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Anger");
    public static final String ID = "downfall_Charboss:Anger";

    public EnAnger() {
        super("downfall_Charboss:Anger", cardStrings.NAME, "red/attack/anger", 0, cardStrings.DESCRIPTION,
              CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = 6;
        this.damage = this.baseDamage;


    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 造成伤害
        addToBot(new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        // 添加愤怒特效
        addToBot(new VFXAction((AbstractCreature)m, (AbstractGameEffect)new VerticalAuraEffect(Color.FIREBRICK, m.hb.cX, m.hb.cY), 0.0F));

        // 将自己的复制品加入弃牌堆（愤怒机制）
        // 在怪物系统中，我们直接添加到怪物的弃牌堆
        addToBot(new com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction(makeStatEquivalentCopy(), 1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnAnger();
    }
}