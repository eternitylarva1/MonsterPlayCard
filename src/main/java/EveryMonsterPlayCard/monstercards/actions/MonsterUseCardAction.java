package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import EveryMonsterPlayCard.monstercards.AbstractMonsterAddFieldPatch;
import EveryMonsterPlayCard.monstercards.MonsterCardPlayer;

/**
 * 怪物版本的UseCardAction
 * 在正确的位置触发Power钩子
 */
public class MonsterUseCardAction extends AbstractGameAction {
    private AbstractCard card;
    private AbstractMonster targetMonster;
    private AbstractMonster cardSourceMonster; // 卡牌来源怪物
    
    public MonsterUseCardAction(AbstractCard card, AbstractMonster cardSourceMonster, AbstractMonster targetMonster) {
        this.card = card;
        this.targetMonster = targetMonster;
        this.cardSourceMonster = cardSourceMonster;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.USE;
    }
    
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (cardSourceMonster != null) {
                // 创建一个UseCardAction实例来传递给Power的onUseCard方法
                UseCardAction useCardAction = new UseCardAction(card, targetMonster);
                
                // 触发卡牌来源怪物身上Power的onUseCard钩子
                for (AbstractPower power : cardSourceMonster.powers) {
                    power.onUseCard(card, useCardAction);
                }
                
                // 触发玩家身上Power的onUseCard钩子（如果需要）
                for (AbstractPower power : AbstractDungeon.player.powers) {
                    power.onUseCard(card, useCardAction);
                }
                
                // 如果目标不是玩家，也触发目标怪物身上Power的onUseCard钩子
                if (targetMonster != null && !targetMonster.equals(AbstractDungeon.player)) {
                    for (AbstractPower power : targetMonster.powers) {
                        power.onUseCard(card, useCardAction);
                    }
                }
            }
        }
        tickDuration();
    }
}