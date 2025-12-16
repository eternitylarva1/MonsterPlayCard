package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

/**
 * 铁波 - 怪物版
 * 同时造成伤害和格挡
 */
public class EnIronWave extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Iron Wave";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Iron Wave");

    public EnIronWave() {
        super("downfall_Charboss:Iron Wave", cardStrings.NAME, "red/attack/iron_wave", 1, cardStrings.DESCRIPTION,
              CardType.ATTACK, CardColor.RED, CardRarity.COMMON, CardTarget.ENEMY);

        this.baseDamage = 5;
        this.damage = this.baseDamage;
        this.baseBlock = 5;
        this.block = this.baseBlock;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得格挡
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
        // 短暂等待
        addToBot((AbstractGameAction)new WaitAction(0.1F));
        
        // 造成伤害
        if (p != null && m != null) {
            // 添加视觉效果（简化版本，不使用特殊的IronWaveReversedEffect）
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new com.megacrit.cardcrawl.vfx.combat.IntimidateEffect(m.hb.cX, p.hb.cY), 0.5F));
        }
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBlock(2);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnIronWave();
    }
}