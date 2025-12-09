package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import EveryMonsterPlayCard.powers.cardpowers.EnFlameBarrierPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlameBarrierEffect;

/**
 * 火焰壁垒 - 怪物版
 * 获得格挡，反击敌人造成等于格挡值的伤害
 */
public class EnFlameBarrier extends AbstractMonsterCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Flame Barrier");
    public static final String ID = "downfall_Charboss:FlameBarrier";

    public EnFlameBarrier() {
        super("downfall_Charboss:FlameBarrier", cardStrings.NAME, "red/skill/flame_barrier", 2, cardStrings.DESCRIPTION,
              CardType.SKILL, CardColor.RED, CardRarity.UNCOMMON, CardTarget.SELF);

        this.baseBlock = 12;
        this.block = this.baseBlock;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得格挡
        addToBot(new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));

        // 添加火焰壁垒效果（反击）
        AbstractPower flameBarrierPower = new EnFlameBarrierPower((AbstractCreature)m, this.magicNumber);
        addToBot(new MonsterApplyPowerAction(m, m, flameBarrierPower, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnFlameBarrier();
    }
}