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

    // 存储使用此卡牌的怪物引用，用于applyPowers计算
    protected AbstractMonster owningMonster;

    public AbstractMonsterCard(String id, String name, String img, int cost, String rawDescription,
                              CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.costForTurn =this.cost= cost;
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
     * 设置使用此卡牌的怪物
     */
    public void setOwningMonster(AbstractMonster monster) {
        this.owningMonster = monster;
    }

    /**
     * 获取使用此卡牌的怪物
     */
    public AbstractMonster getOwningMonster() {
        return this.owningMonster;
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
     * 遵循角色关系：怪物卡牌是怪物出的，atDamageGive看怪物的powers
     */
    @Override
    public void applyPowers() {
        applyPowersToBlock();

        this.isDamageModified = false;

        if (!this.isMultiDamage) {
            float tmp = this.baseDamage;

            // 应用怪物的powers对伤害的影响（遵循角色关系）
            if (this.owningMonster != null) {
                for (AbstractPower p : this.owningMonster.powers) {
                    tmp = p.atDamageGive(tmp, this.damageTypeForTurn, this);
                }
            }

            // 应用玩家的powers对怪物伤害的接收影响
            for (AbstractPower p : AbstractDungeon.player.powers) {
                tmp = p.atDamageReceive(tmp, this.damageTypeForTurn, this);
            }

            // 应用玩家的powers最终修改
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
        }
        // TODO: 对于多重伤害，未来可以实现完整逻辑
    }

    /**
     * 重写applyPowersToBlock方法，适用于怪物卡牌的场景
     */
    @Override
    protected void applyPowersToBlock() {
        this.isBlockModified = false;
        float tmp = this.baseBlock;

        if (this.baseBlock != MathUtils.floor(tmp)) {
            this.isBlockModified = true;
        }

        if (tmp < 0.0F) {
            tmp = 0.0F;
        }
        this.block = MathUtils.floor(tmp);
    }
}
