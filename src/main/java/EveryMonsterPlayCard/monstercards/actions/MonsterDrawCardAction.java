package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import EveryMonsterPlayCard.monstercards.AbstractMonsterAddFieldPatch;
import EveryMonsterPlayCard.monstercards.MonsterCardPlayer;

/**
 * 怪物版本的抽牌Action
 * 在正确的位置触发Power钩子
 */
public class MonsterDrawCardAction extends AbstractGameAction {
    private AbstractCard card;
    private AbstractMonster targetMonster;
    
    public MonsterDrawCardAction(AbstractCard card, AbstractMonster targetMonster) {
        this.card = card;
        this.targetMonster = targetMonster;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }
    
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (targetMonster != null) {
                MonsterCardPlayer monsterCardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(targetMonster);
                if (monsterCardPlayer != null) {
                    // 添加卡牌到怪物手牌（使用公共方法）
                    monsterCardPlayer.addCardToHand(card);
                    
                    // 触发怪物身上Power的onCardDraw钩子
                    for (AbstractPower power : targetMonster.powers) {
                        power.onCardDraw(card);
                    }
                    
                    // 触发玩家身上Power的onCardDraw钩子（如果需要）
                    for (AbstractPower power : AbstractDungeon.player.powers) {
                        power.onCardDraw(card);
                    }
                    
                    // 刷新显示的卡牌
                    monsterCardPlayer.refreshDisplayedCardsPublic();
                }
            }
        }
        tickDuration();
    }
}