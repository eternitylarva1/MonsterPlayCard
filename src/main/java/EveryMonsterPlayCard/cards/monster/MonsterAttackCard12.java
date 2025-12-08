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
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import com.badlogic.gdx.graphics.Color;

public class MonsterAttackCard12 extends AbstractMonsterCard {
    public static final String ID = "EveryMonsterPlayCard:MonsterAttackCard12";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Feed");

    public MonsterAttackCard12() {
        super("EveryMonsterPlayCard:MonsterAttackCard12", cardStrings.NAME, "red/attack/feed", 1, cardStrings.DESCRIPTION,
              AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 10;
        this.exhaust = true;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractCard.CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(m);

        AbstractDungeon.actionManager.addToBottom(new VFXAction((AbstractGameEffect)new BiteEffect(p.hb.cX, p.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F));
        AbstractDungeon.actionManager.addToBottom(new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new MonsterAttackCard12();
    }
}