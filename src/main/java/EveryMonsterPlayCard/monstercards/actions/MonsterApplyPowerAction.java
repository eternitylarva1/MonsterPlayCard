package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerBuffEffect;
import com.megacrit.cardcrawl.vfx.combat.PowerDebuffEffect;

import java.util.Collections;

/**
 * 怪物版本的ApplyPowerAction
 * 基于原版ApplyPowerAction实现，适配怪物给怪物或玩家施加powers的场景
 * 遵循原版完整逻辑闭环，不简化处理
 */
public class MonsterApplyPowerAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ApplyPowerAction");
    public static final String[] TEXT = uiStrings.TEXT;

    private AbstractPower powerToApply;
    private float startingDuration;
    private AbstractGameAction.AttackEffect effect;

    public MonsterApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast, AbstractGameAction.AttackEffect effect) {
        if (Settings.FAST_MODE) {
            this.startingDuration = 0.1F;
        } else if (isFast) {
            this.startingDuration = Settings.ACTION_DUR_FASTER;
        } else {
            this.startingDuration = Settings.ACTION_DUR_FAST;
        }

        setValues(target, source, stackAmount);
        this.duration = this.startingDuration;
        this.powerToApply = powerToApply;
        this.effect = effect;

        // 处理特殊遗物效果（参考原版逻辑）
        if (AbstractDungeon.player.hasRelic("Snake Skull") && source != null && source.isPlayer && target != source && powerToApply.ID.equals("Poison")) {
            AbstractDungeon.player.getRelic("Snake Skull").flash();
            this.powerToApply.amount++;
        }
    }

    public MonsterApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, boolean isFast) {
        this(target, source, powerToApply, stackAmount, isFast, AbstractGameAction.AttackEffect.NONE);
    }

    public MonsterApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply) {
        this(target, source, powerToApply, powerToApply.amount);
    }

    public MonsterApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount) {
        this(target, source, powerToApply, stackAmount, false, AbstractGameAction.AttackEffect.NONE);
    }

    public MonsterApplyPowerAction(AbstractCreature target, AbstractCreature source, AbstractPower powerToApply, int stackAmount, AbstractGameAction.AttackEffect effect) {
        this(target, source, powerToApply, stackAmount, false, effect);
    }

    public void update() {
        if (this.target == null || this.target.isDeadOrEscaped()) {
            this.isDone = true;
            return;
        }

        if (this.duration == this.startingDuration) {
            // 处理特殊powers（参考原版逻辑）
            if (this.powerToApply instanceof com.megacrit.cardcrawl.powers.NoDrawPower && this.target.hasPower(this.powerToApply.ID)) {
                this.isDone = true;
                return;
            }

            // 触发源生物的powers影响
            if (this.source != null) {
                for (AbstractPower pow : this.source.powers) {
                    pow.onApplyPower(this.powerToApply, this.target, this.source);
                }
            }

            // 处理玩家遗物效果（参考原版完整逻辑）
            if (AbstractDungeon.player.hasRelic("Champion Belt") && this.source != null && this.source.isPlayer && this.target != this.source && this.powerToApply.ID.equals("Vulnerable") && !this.target.hasPower("Artifact")) {
                AbstractDungeon.player.getRelic("Champion Belt").onTrigger(this.target);
            }

            // 处理怪物特殊情况
            if (this.target instanceof com.megacrit.cardcrawl.monsters.AbstractMonster && this.target.isDeadOrEscaped()) {
                this.duration = 0.0F;
                this.isDone = true;
                return;
            }

            // 处理Ginger遗物（虚弱抗性）
            if (AbstractDungeon.player.hasRelic("Ginger") && this.target.isPlayer && this.powerToApply.ID.equals("Weakened")) {
                AbstractDungeon.player.getRelic("Ginger").flash();
                addToTop(new com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction(this.target, TEXT[1]));
                this.duration -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
                return;
            }

            // 处理Turnip遗物（脆弱抗性）
            if (AbstractDungeon.player.hasRelic("Turnip") && this.target.isPlayer && this.powerToApply.ID.equals("Frail")) {
                AbstractDungeon.player.getRelic("Turnip").flash();
                addToTop(new com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction(this.target, TEXT[1]));
                this.duration -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
                return;
            }

            // 处理Artifact效果（参考原版完整逻辑）
            if (this.target.hasPower("Artifact") && this.powerToApply.type == AbstractPower.PowerType.DEBUFF) {
                addToTop(new com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction(this.target, TEXT[0]));
                this.duration -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
                CardCrawlGame.sound.play("NULLIFY_SFX");
                this.target.getPower("Artifact").flashWithoutSound();
                this.target.getPower("Artifact").onSpecificTrigger();
                return;
            }

            // 添加视觉特效（参考原版）
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.effect));

            // 检查是否已经有相同的power
            boolean hasBuffAlready = false;
            for (AbstractPower p : this.target.powers) {
                if (p.ID.equals(this.powerToApply.ID) && !p.ID.equals("Night Terror")) {
                    // 叠加power
                    p.stackPower(this.amount);
                    p.flash();

                    // 处理特殊powers的视觉特效
                    if ((p instanceof com.megacrit.cardcrawl.powers.StrengthPower || p instanceof com.megacrit.cardcrawl.powers.DexterityPower) && this.amount <= 0) {
                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                    } else if (this.amount > 0) {
                        if (p.type == AbstractPower.PowerType.BUFF || p instanceof com.megacrit.cardcrawl.powers.StrengthPower || p instanceof com.megacrit.cardcrawl.powers.DexterityPower) {
                            AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + Integer.toString(this.amount) + " " + this.powerToApply.name));
                        } else {
                            AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, "+" + Integer.toString(this.amount) + " " + this.powerToApply.name));
                        }
                    } else if (p.type == AbstractPower.PowerType.BUFF) {
                        AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                    } else {
                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                    }

                    p.updateDescription();
                    hasBuffAlready = true;
                    AbstractDungeon.onModifyPower();
                }
            }

            // 处理减益特效
            if (this.powerToApply.type == AbstractPower.PowerType.DEBUFF) {
                this.target.useFastShakeAnimation(0.5F);
            }

            // 如果目标没有这个power，则添加新power
            if (!hasBuffAlready) {
                this.target.powers.add(this.powerToApply);
                Collections.sort(this.target.powers);
                this.powerToApply.onInitialApplication();
                this.powerToApply.flash();

                // 处理特殊负数值powers的视觉特效
                if (this.amount < 0 && (this.powerToApply.ID.equals("Strength") || this.powerToApply.ID.equals("Dexterity") || this.powerToApply.ID.equals("Focus"))) {
                    AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                } else if (this.amount > 0) {
                    if (this.powerToApply.type == AbstractPower.PowerType.BUFF) {
                        AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + " " + TEXT[2] + " " + this.amount));
                    } else {
                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + " " + TEXT[2] + " " + this.amount));
                    }
                } else {
                    if (this.powerToApply.type == AbstractPower.PowerType.BUFF) {
                        AbstractDungeon.effectList.add(new PowerBuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                    } else {
                        AbstractDungeon.effectList.add(new PowerDebuffEffect(this.target.hb.cX - this.target.animX, this.target.hb.cY + this.target.hb.height / 2.0F, this.powerToApply.name + TEXT[3]));
                    }
                }

                this.powerToApply.updateDescription();
                AbstractDungeon.onModifyPower();
            }
        }

        this.duration -= com.badlogic.gdx.Gdx.graphics.getDeltaTime();
        if (this.duration <= 0.0F) {
            this.isDone = true;
        }
    }
}