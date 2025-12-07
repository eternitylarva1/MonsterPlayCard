package EveryMonsterPlayCard.monstercards.cards;

import EveryMonsterPlayCard.utils.Hpr;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * 基础怪物攻击卡牌
 * 用于创建各种怪物攻击类型的卡牌
 */
public class MonsterAttackCard extends AbstractMonsterCard {

    private CardStrings cardStrings;

    public MonsterAttackCard(String id, String name, String img, int cost, int damage,
                           CardTarget target, AbstractGameAction.AttackEffect effect) {
        super(id, name, img, cost, getDescriptionFromId(id), CardType.ATTACK, target);

        this.baseDamage = damage;
        this.damage = this.baseDamage;
        this.attackEffect = effect;

        // 攻击卡牌不提供格挡，设置block为0
        this.block = 0;

        // 尝试加载本地化描述
        try {
            cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
            if (cardStrings != null) {
                this.name = cardStrings.NAME;
                this.rawDescription = cardStrings.DESCRIPTION;
                initializeDescription();
            }
        } catch (Exception e) {
            // 如果找不到本地化文本，使用默认描述
            Hpr.info("无法找到卡牌 " + id + " 的本地化文本，使用默认描述");
        }
    }

    private static String getDescriptionFromId(String id) {
        // 根据ID生成默认描述
        if (id.contains("monster_attack")) {
            return "造成伤害";
        } else if (id.contains("monster_strike")) {
            return "造成 !D! 点伤害";
        } else if (id.contains("monster_bash")) {
            return "造成 !D! 点伤害";
        }
        return "怪物攻击";
    }

    @Override
    public void use(AbstractCreature source, AbstractCreature target) {
        if (target != null) {
            DamageInfo damageInfo = new DamageInfo(source, damage, damageTypeForTurn);
            addToBot(new DamageAction(target, damageInfo, attackEffect));
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // 怪物卡牌不使用玩家版本
    }

    @Override
    public MonsterAttackCard makeCopy() {
        MonsterAttackCard copy = new MonsterAttackCard(cardID, name, assetUrl, cost, baseDamage, target, attackEffect);
        copy.baseDamage = this.baseDamage;
        copy.damage = this.damage;
        return copy;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }
}