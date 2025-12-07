package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

/**
 * 激励 - 怪物版
 * 获得力量，是典型的强化类能力卡牌
 */
public class EnInflame extends AbstractMonsterCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Inflame");
    public static final String ID = "EveryMonsterPlayCard:EnInflame";

    public EnInflame() {
        super("EveryMonsterPlayCard:EnInflame", cardStrings.NAME, "red/power/inflame", 1, cardStrings.DESCRIPTION,
              CardType.POWER, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;

        // 保持原版费用，不设为0费
        this.cost = 1;
        this.costForTurn = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 激励效果动画
        addToBot(new VFXAction((AbstractCreature)m, (AbstractGameEffect)new InflameEffect((AbstractCreature)m), 1.0F));

        // 给自己施加力量
        AbstractPower strengthPower = new StrengthPower((AbstractCreature)m, this.magicNumber);
        addToBot(new MonsterApplyPowerAction(m, m, strengthPower, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnInflame();
    }
}