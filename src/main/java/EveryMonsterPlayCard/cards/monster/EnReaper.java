package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

/**
 * 死神收割者 - 怪物版
 * 对所有敌人造成伤害，击杀敌人时获得力量
 * 包含生命吸取效果：造成伤害时回复等量生命值
 */
public class EnReaper extends AbstractMonsterCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Reaper");
    public static final String ID = "downfall_Charboss:Reaper";

    public EnReaper() {
        super("downfall_Charboss:Reaper", cardStrings.NAME, "red/attack/reaper", 2, cardStrings.DESCRIPTION,
              CardType.ATTACK, CardColor.RED, CardRarity.RARE, CardTarget.ALL_ENEMY);

        this.baseDamage = 4;
        this.damage = this.baseDamage;

        // 添加治疗标签
        this.tags.add(AbstractCard.CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 对玩家造成伤害
        calculateCardDamage(m);
        addToBot(new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));

        // 如果击杀玩家（这里简化处理，实际上很难触发）
        if (p.currentHealth <= this.damage) {
            // 怪物获得力量
            AbstractPower strengthPower = new StrengthPower((AbstractCreature)m, 1);
            addToBot(new MonsterApplyPowerAction(m, m, strengthPower, 1));

            // 生命吸取效果
            addToBot(new HealAction((AbstractCreature)m, (AbstractCreature)m, this.damage));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnReaper();
    }
}