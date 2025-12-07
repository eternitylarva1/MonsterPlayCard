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
     * 原版：基于玩家计算
     * 现在：基于怪物计算
     */
    @Override
    public void applyPowers() {
        applyPowersToBlock();

        // 对于怪物卡牌，我们不需要应用遗物效果
        // 只需要应用powers的影响
        this.isDamageModified = false;

        if (!this.isMultiDamage) {
            float tmp = this.baseDamage;

            // 对于怪物卡牌，可以根据需要添加特殊的power处理逻辑
            // 这里可以根据怪物的特殊属性来调整伤害

            if (tmp < 0.0F) {
                tmp = 0.0F;
            }

            if (this.baseDamage != MathUtils.floor(tmp)) {
                this.isDamageModified = true;
            }
            this.damage = MathUtils.floor(tmp);
        }
        // TODO: 如果需要支持多重伤害，可以在这里添加相应的逻辑
    }

    /**
     * 重写applyPowersToBlock方法，适用于怪物卡牌的场景
     * 原版：基于玩家计算格挡
     * 现在：基于怪物计算格挡
     */
    @Override
    protected void applyPowersToBlock() {
        this.isBlockModified = false;
        float tmp = this.baseBlock;

        // 对于怪物卡牌，可以根据需要添加特殊的power处理逻辑
        // 这里可以根据怪物的特殊属性来调整格挡

        if (this.baseBlock != MathUtils.floor(tmp)) {
            this.isBlockModified = true;
        }

        if (tmp < 0.0F) {
            tmp = 0.0F;
        }
        this.block = MathUtils.floor(tmp);
    }
}
