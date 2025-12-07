package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

/**
 * 上勾拳 - 怪物版
 * 造成伤害并施加虚弱和易伤，是复合减益效果的典型攻击卡牌
 */
public class MonsterAttackCard6 extends AbstractMonsterCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Uppercut");
    public static final String ID = "EveryMonsterPlayCard:MonsterAttackCard6";

    public MonsterAttackCard6() {
        super("EveryMonsterPlayCard:MonsterAttackCard6", cardStrings.NAME, "red/attack/uppercut", 2, cardStrings.DESCRIPTION,
              CardType.ATTACK, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ENEMY);

        this.baseDamage = 13;
        this.damage = this.baseDamage;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

        // 保持原版费用，不设为0费
        this.cost = 2;
        this.costForTurn = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 造成伤害
        addToBot(new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));

        // 施加虚弱
        AbstractPower weakPower = new WeakPower((AbstractCreature)p, this.magicNumber, true);
        addToBot(new MonsterApplyPowerAction(p, m, weakPower, this.magicNumber));

        // 施加易伤
        AbstractPower vulnerablePower = new VulnerablePower((AbstractCreature)p, this.magicNumber, true);
        addToBot(new MonsterApplyPowerAction(p, m, vulnerablePower, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new MonsterAttackCard6();
    }
}