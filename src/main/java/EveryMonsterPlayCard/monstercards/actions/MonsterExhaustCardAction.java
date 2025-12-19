package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import EveryMonsterPlayCard.monstercards.AbstractMonsterAddFieldPatch;
import EveryMonsterPlayCard.monstercards.MonsterCardPlayer;

/**
 * 怪物版本的消耗卡牌Action
 * 在正确的位置触发Power钩子
 */
public class MonsterExhaustCardAction extends AbstractGameAction {
    private AbstractCard card;
    private AbstractMonster targetMonster;
    
    public MonsterExhaustCardAction(AbstractCard card, AbstractMonster targetMonster) {
        this.card = card;
        this.targetMonster = targetMonster;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
    }
    
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (targetMonster != null) {
                MonsterCardPlayer monsterCardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(targetMonster);
                if (monsterCardPlayer != null) {
                    // 从怪物手牌中移除卡牌（使用公共方法）
                    // 注意：这里需要先检查卡牌是否在手牌中
                    if (monsterCardPlayer.getMonsterHand() != null &&
                        monsterCardPlayer.getMonsterHand().contains(card)) {
                        monsterCardPlayer.getMonsterHand().removeCard(card);
                        
                        // 触发怪物身上Power的onExhaust钩子
                        for (AbstractPower power : targetMonster.powers) {
                            power.onExhaust(card);
                        }
                        
                        // 触发玩家身上Power的onExhaust钩子（如果需要）
                        for (AbstractPower power : AbstractDungeon.player.powers) {
                            power.onExhaust(card);
                        }
                    }
                }
            }
        }
        tickDuration();
    }
}