package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

/**
 * 怪物卡牌基类
 */
public abstract class AbstractMonsterCard extends AbstractCard {

    protected CardStrings cardStrings;

    public AbstractMonsterCard(String id, String name, String img, int cost, String rawDescription,
                              CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.freeToPlayOnce = true;
        this.costForTurn = 0;
        this.isInnate = false;
        this.exhaust = false;
    }

    public int getPriority(ArrayList<AbstractCard> hand) {
        return 0;
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return true;
    }

    /**
     * 重写calculateCardDamage方法，适用于怪物卡牌的场景
     * 原版：使用者是玩家，目标怪物
     * 现在：使用者是怪物，目标玩家
     */
    public void calculateCardDamage(AbstractMonster m) {
        this.isDamageModified = false;

        if (m != null) {
            float tmp = this.baseDamage;

            // 应用怪物的伤害给予修改
            for (AbstractPower p : m.powers) {
                tmp = p.atDamageGive(tmp, this.damageTypeForTurn, this);
            }

            // 应用怪物的伤害接收修改
            for (AbstractPower p : AbstractDungeon.player.powers) {
                tmp = p.atDamageReceive(tmp, this.damageTypeForTurn, this);
            }

            // 应用最终修改
            for (AbstractPower p : AbstractDungeon.player.powers) {
                tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn, this);
            }

            if (tmp < 0.0F) {
                tmp = 0.0F;
            }

            if (this.baseDamage != MathUtils.floor(tmp)) {
                this.isDamageModified = true;
            }
            this.damage = MathUtils.floor(tmp);
        } else {
            this.damage = this.baseDamage;
        }
    }

    public abstract void use(AbstractPlayer p, AbstractMonster m);

    public abstract AbstractCard makeCopy();

    /**
     * 重写applyPowers方法，适用于怪物卡牌的场景
     * 原版：基于玩家计算（考虑玩家遗物、powers、姿态）
     * 现在：基于怪物计算（考虑怪物powers、玩家powers影响）
     */
    @Override
    public void applyPowers() {
        applyPowersToBlock();

        // 对于怪物卡牌，我们不需要应用遗物效果（怪物没有遗物）
        // 但需要考虑玩家的powers对怪物的伤害影响
        this.isDamageModified = false;

        if (!this.isMultiDamage) {
            float tmp = this.baseDamage;

            // 应用玩家的powers对怪物伤害的影响
            for (AbstractPower p : AbstractDungeon.player.powers) {
                tmp = p.atDamageGive(tmp, this.damageTypeForTurn, this);
            }

            // 移除姿态影响，因为怪物没有姿态
            // tmp = player.stance.atDamageGive(tmp, this.damageTypeForTurn, this);
            if (this.baseDamage != (int)tmp) {
                this.isDamageModified = true;
            }

            // 应用玩家的powers最终修改
            for (AbstractPower p : AbstractDungeon.player.powers) {
                tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn, this);
            }

            if (tmp < 0.0F) {
                tmp = 0.0F;
            }

            if (this.baseDamage != MathUtils.floor(tmp)) {
                this.isDamageModified = true;
            }
            this.damage = MathUtils.floor(tmp);
        } else {
            // 对于多重伤害，参考原版逻辑
            ArrayList<AbstractMonster> m = (AbstractDungeon.getCurrRoom()).monsters.monsters;
            float[] tmp = new float[m.size()];
            int i;

            for (i = 0; i < tmp.length; i++) {
                tmp[i] = this.baseDamage;
            }

            for (i = 0; i < tmp.length; i++) {
                // 应用玩家的powers对每个怪物的伤害影响
                for (AbstractPower p : AbstractDungeon.player.powers) {
                    tmp[i] = p.atDamageGive(tmp[i], this.damageTypeForTurn, this);
                }

                // 移除姿态影响
                // tmp[i] = AbstractDungeon.player.stance.atDamageGive(tmp[i], this.damageTypeForTurn, this);
                if (this.baseDamage != (int)tmp[i]) {
                    this.isDamageModified = true;
                }
            }

            for (i = 0; i < tmp.length; i++) {
                // 应用玩家的powers最终修改
                for (AbstractPower p : AbstractDungeon.player.powers) {
                    tmp[i] = p.atDamageFinalGive(tmp[i], this.damageTypeForTurn, this);
                }

                if (tmp[i] < 0.0F) {
                    tmp[i] = 0.0F;
                }

                if (this.baseDamage != MathUtils.floor(tmp[i])) {
                    this.isDamageModified = true;
                }
                this.multiDamage[i] = MathUtils.floor(tmp[i]);
            }
        }
    }

    /**
     * 重写applyPowersToBlock方法，适用于怪物卡牌的场景
     * 原版：基于玩家计算格挡（考虑玩家powers）
     * 现在：基于怪物计算格挡（考虑怪物powers）
     */
    @Override
    protected void applyPowersToBlock() {
        this.isBlockModified = false;
        float tmp = this.baseBlock;

        // 对于怪物卡牌，我们可以考虑怪物的powers对格挡的影响
        // 这里可以根据需要添加怪物power的修改逻辑

        if (this.baseBlock != MathUtils.floor(tmp)) {
            this.isBlockModified = true;
        }

        if (tmp < 0.0F) {
            tmp = 0.0F;
        }
        this.block = MathUtils.floor(tmp);
    }
}
