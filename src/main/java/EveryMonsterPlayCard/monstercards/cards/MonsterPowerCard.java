package EveryMonsterPlayCard.monstercards.cards;

import EveryMonsterPlayCard.utils.Hpr;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.DexterityPower;

/**
 * 基础怪物能力卡牌
 * 用于创建各种怪物力量类型的卡牌
 */
public class MonsterPowerCard extends AbstractMonsterCard {

    private CardStrings cardStrings;
    private PowerType powerType;
    private int powerAmount;

    public enum PowerType {
        STRENGTH,
        DEXTERITY,
        ARTIFACT
    }

    public MonsterPowerCard(String id, String name, String img, int cost,
                          PowerType powerType, int powerAmount, CardTarget target) {
        super(id, name, img, cost, getDescriptionFromId(id, powerType, powerAmount), CardType.POWER, target);

        this.powerType = powerType;
        this.powerAmount = powerAmount;
        this.magicNumber = powerAmount;
        this.baseMagicNumber = powerAmount;

        // 尝试加载本地化描述
        try {
            cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
            if (cardStrings != null) {
                this.name = cardStrings.NAME;
                this.rawDescription = cardStrings.DESCRIPTION;
                initializeDescription();
            }
        } catch (Exception e) {
            Hpr.info("无法找到卡牌 " + id + " 的本地化文本，使用默认描述");
        }
    }

    private static String getDescriptionFromId(String id, PowerType powerType, int amount) {
        switch (powerType) {
            case STRENGTH:
                return "获得 !M! 点力量";
            case DEXTERITY:
                return "获得 !M! 点敏捷";
            default:
                return "怪物能力";
        }
    }

    @Override
    public void use(AbstractCreature source, AbstractCreature target) {
        if (source != null) {
            switch (powerType) {
                case STRENGTH:
                    addToBot(new ApplyPowerAction(source, source, new StrengthPower(source, powerAmount), powerAmount));
                    break;
                case DEXTERITY:
                    addToBot(new ApplyPowerAction(source, source, new DexterityPower(source, powerAmount), powerAmount));
                    break;
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 怪物卡牌不使用玩家版本
    }

    @Override
    public MonsterPowerCard makeCopy() {
        MonsterPowerCard copy = new MonsterPowerCard(cardID, name, assetUrl, cost, powerType, powerAmount, target);
        copy.powerType = this.powerType;
        copy.powerAmount = this.powerAmount;
        return copy;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            powerAmount += 1;
            magicNumber = powerAmount;
            baseMagicNumber = powerAmount;
            initializeDescription();
        }
    }
}