package EveryMonsterPlayCard.monstercards.examples;

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
 * 怪物卡牌使用MonsterApplyPowerAction的示例
 * 展示如何让怪物给自己或给其他目标施加powers
 */
public class MonsterPowerCardExample extends AbstractCard {

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("MonsterPowerCardExample");
    public static final String ID = "EveryMonsterPlayCard:MonsterPowerCardExample";

    public MonsterPowerCardExample() {
        super(ID, cardStrings.NAME, "red/skill/defend", 1, cardStrings.DESCRIPTION,
              AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS,
              AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF);

        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 怪物给自己施加力量
        AbstractPower strengthPower = new com.megacrit.cardcrawl.powers.StrengthPower(m, this.magicNumber);
        addToBot(new MonsterApplyPowerAction(m, m, strengthPower, this.magicNumber));

        // 怪物给自己施加敏捷
        AbstractPower dexterityPower = new com.megacrit.cardcrawl.powers.DexterityPower(m, this.magicNumber);
        addToBot(new MonsterApplyPowerAction(m, m, dexterityPower, this.magicNumber));

        // 怪物给玩家施加虚弱（减益效果）
        AbstractPower weakPower = new WeakPower(p, this.magicNumber, true);
        addToBot(new MonsterApplyPowerAction(p, m, weakPower, this.magicNumber));

        // 怪物给玩家施加易伤（减益效果）
        AbstractPower vulnerablePower = new VulnerablePower(p, this.magicNumber, true);
        addToBot(new MonsterApplyPowerAction(p, m, vulnerablePower, this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MonsterPowerCardExample();
    }
}