package EveryMonsterPlayCard.monstercards.cards;

import EveryMonsterPlayCard.utils.Hpr;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 基础怪物技能卡牌
 * 用于创建各种怪物防御和技能类型的卡牌
 */
public class MonsterSkillCard extends AbstractMonsterCard {

    private CardStrings cardStrings;

    public MonsterSkillCard(String id, String name, String img, int cost, int block, CardTarget target) {
        super(id, name, img, cost, getDescriptionFromId(id), CardType.SKILL, target);

        this.baseBlock = block;
        this.block = this.baseBlock;

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

    private static String getDescriptionFromId(String id) {
        if (id.contains("monster_defend")) {
            return "获得 !B! 点格挡";
        } else if (id.contains("monster_protect")) {
            return "获得 !B! 点格挡";
        }
        return "怪物技能";
    }

    @Override
    public void use(AbstractCreature source, AbstractCreature target) {
        if (source != null) {
            addToBot(new GainBlockAction(source, source, block));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 怪物卡牌版本：给自己（怪物）施加格挡
        if (m != null) {
            addToBot(new GainBlockAction(m, m, block));
        }
    }

    @Override
    public MonsterSkillCard makeCopy() {
        MonsterSkillCard copy = new MonsterSkillCard(cardID, name, assetUrl, cost, baseBlock, target);
        copy.baseBlock = this.baseBlock;
        copy.block = this.block;
        return copy;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }
}