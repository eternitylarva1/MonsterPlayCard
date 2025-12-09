package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * 怪物执行卡牌的Action
 * 使用Action系统代替直接调用，避免动画冲突
 */
public class executeMonsterCardAction extends AbstractGameAction {

    private final AbstractCard card;
    private final AbstractMonster owningMonster;
    private final AbstractPlayer targetPlayer;

    public executeMonsterCardAction(AbstractCard card, AbstractPlayer targetPlayer, AbstractMonster owningMonster) {
        this.card = card;
        this.targetPlayer = targetPlayer;
        this.owningMonster = owningMonster;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.1F; // 短时执行
    }

    @Override
    public void update() {
        if (card == null || targetPlayer == null || owningMonster == null) {
            this.isDone = true;
            return;
        }

        try {
            // 安全检查
            if (com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom() == null ||
                com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom().monsters == null ||
                com.megacrit.cardcrawl.dungeons.AbstractDungeon.getCurrRoom().monsters.monsters.isEmpty()) {
                Hpr.info("怪物 " + owningMonster.name + " 当前房间没有有效目标，跳过卡牌执行");
                this.isDone = true;
                return;
            }

            // 设置卡牌拥有者怪物（如果是怪物卡牌）
            if (card instanceof EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) {
                ((EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) card).setOwningMonster(owningMonster);
            }

            // 使用游戏原生的卡牌使用方式，通过Action系统
            card.use(targetPlayer, owningMonster);

            Hpr.info("怪物 " + owningMonster.name + " 通过Action执行了卡牌: " + card.name);

        } catch (Exception e) {
            Hpr.info("怪物执行卡牌Action时出错: " + e.getMessage());
        }

        this.isDone = true;
    }
}
