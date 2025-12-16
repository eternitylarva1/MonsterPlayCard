package EveryMonsterPlayCard.cards.monster;

import EveryMonsterPlayCard.monstercards.actions.MonsterApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.IntimidateEffect;

/**
 * 恐吓 - 怪物版
 * 给所有敌人施加虚弱效果
 */
public class EnIntimidate extends AbstractMonsterCard {
    public static final String ID = "downfall_Charboss:Intimidate";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Intimidate");

    public EnIntimidate() {
        super("downfall_Charboss:Intimidate", cardStrings.NAME, "red/skill/intimidate", 0, cardStrings.DESCRIPTION,
              CardType.SKILL, CardColor.RED, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);

        this.exhaust = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("INTIMIDATE"));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new IntimidateEffect(m.hb.cX, m.hb.cY), 1.0F));
        
        // 给玩家施加虚弱效果（恐吓的主要功能）
        AbstractPower weakPower = new WeakPower((AbstractCreature)p, this.magicNumber, true);
        addToBot(new MonsterApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, weakPower, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnIntimidate();
    }
}