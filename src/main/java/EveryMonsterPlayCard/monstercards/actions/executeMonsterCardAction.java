package EveryMonsterPlayCard.monstercards.actions;

import EveryMonsterPlayCard.monstercards.AbstractMonsterAddFieldPatch;
import EveryMonsterPlayCard.monstercards.MonsterCardPlayer;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import EveryMonsterPlayCard.utils.Hpr;

/**
 * 怪物执行卡牌的Action
 * 使用Action系统代替直接调用，避免动画冲突
 */
public class executeMonsterCardAction extends AbstractGameAction {

    private final AbstractCard card;
    private final AbstractMonster owningMonster;
    private final AbstractPlayer targetPlayer;
    private boolean cardUsed = false;

    public executeMonsterCardAction(AbstractCard card, AbstractPlayer targetPlayer, AbstractMonster owningMonster) {
        this.card = card;
        this.targetPlayer = targetPlayer;
        this.owningMonster = owningMonster;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.3F; // 更长的持续时间，确保动画完成
        this.startDuration = 0.3F;
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

            // 只在第一次update时执行卡牌效果
            if (!cardUsed) {
                // 触发怪物身上Power的onUseCard钩子（类似原版UseCardAction的构造函数逻辑）
                if (!card.dontTriggerOnUseCard) {
                    // 创建一个临时的UseCardAction来触发Power钩子
                    UseCardAction tempUseCardAction = new UseCardAction(card, targetPlayer);
                    for (AbstractPower power : owningMonster.powers) {
                        power.onUseCard(card, tempUseCardAction);
                    }
                }

                // 设置卡牌拥有者怪物（如果是怪物卡牌）
                if (card instanceof EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) {
                    ((EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) card).setOwningMonster(owningMonster);
                }

                MonsterCardPlayer monsterCardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(owningMonster);

                // 创建卡牌使用动画
                monsterCardPlayer.createCardPlayAnimation(this.card);

                // 使用游戏原生的卡牌使用方式，通过Action系统
                card.use(targetPlayer, owningMonster);

                Hpr.info("怪物 " + owningMonster.name + " 通过Action执行了卡牌: " + card.name);
                cardUsed = true;
            }

        } catch (Exception e) {
            Hpr.info("怪物执行卡牌Action时出错: " + e.getMessage());
        }

        // 使用标准的tickDuration()处理Action持续时间
        this.tickDuration();
    }
}
