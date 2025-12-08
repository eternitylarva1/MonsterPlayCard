package EveryMonsterPlayCard.cards.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

/**
 * 武装 - 怪物版
 * 获得格挡
 * 在怪物系统中简化为只获得格挡，因为怪物没有手牌概念
 */
public class EnArmaments extends AbstractMonsterCard {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ArmamentsAction");
    private static final String[] TEXT = uiStrings.TEXT;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Armaments");
    public static final String ID = "downfall_Charboss:Armaments";

    public EnArmaments() {
        super("downfall_Charboss:Armaments", cardStrings.NAME, "red/skill/armaments", 1, cardStrings.DESCRIPTION,
              CardType.SKILL, CardColor.RED, CardRarity.COMMON, CardTarget.SELF);

        this.baseBlock = 5;
        this.block = this.baseBlock;

        // 保持原版费用：1费
        this.cost = 1;
        this.costForTurn = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // 获得格挡
        addToBot(new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));

        // 在怪物系统中，我们不实现升级手牌功能，因为怪物没有传统意义上的手牌
        // 怪物是从抽牌堆抽牌出牌的系统
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2); // 升级版本获得更多格挡
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new EnArmaments();
    }
}