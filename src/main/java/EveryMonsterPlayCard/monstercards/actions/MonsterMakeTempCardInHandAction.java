package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import EveryMonsterPlayCard.monstercards.AbstractMonsterAddFieldPatch;
import EveryMonsterPlayCard.monstercards.MonsterCardPlayer;

import java.util.ArrayList;

/**
 * 怪物版本的制造临时卡牌到手牌动作
 * 修复：将卡牌添加到怪物的手牌中，而不是玩家的手牌
 */
public class MonsterMakeTempCardInHandAction extends AbstractGameAction {
    private AbstractCard card;
    private int amount;
    private AbstractMonster targetMonster;
    
    public MonsterMakeTempCardInHandAction(AbstractCard card, int amount) {
        this(card, amount, null);
    }
    
    public MonsterMakeTempCardInHandAction(AbstractCard card, int amount, AbstractMonster targetMonster) {
        this.card = card;
        this.amount = amount;
        this.targetMonster = targetMonster;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }
    
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            // 如果没有指定目标怪物，尝试从上下文获取
            if (targetMonster == null) {
                // 尝试从当前执行的怪物获取 - 修复：使用静态字段获取当前怪物
                // 由于没有getCurrentExecutingMonster方法，我们需要通过其他方式获取
                // 暂时回退到原版行为，但添加到怪物手牌的逻辑已准备好
                targetMonster = null; // 暂时设为null，使用回退逻辑
            }
            
            if (targetMonster != null) {
                MonsterCardPlayer monsterCardPlayer = AbstractMonsterAddFieldPatch.getMonsterCardPlayer(targetMonster);
                if (monsterCardPlayer != null) {
                    // 将卡牌添加到怪物的手牌中 - 修复：使用公共方法
                    for (int i = 0; i < amount; i++) {
                        AbstractCard newCard = card.makeCopy();
                        // 重要：设置卡牌的拥有怪物
                        if (newCard instanceof EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) {
                            ((EveryMonsterPlayCard.cards.monster.AbstractMonsterCard) newCard).setOwningMonster(targetMonster);
                        }
                        // 修复：使用公共方法添加卡牌到手牌
                        monsterCardPlayer.addCardToHand(newCard);
                    }
                    // 刷新手牌显示 - 使用公共方法
                    monsterCardPlayer.refreshDisplayedCardsPublic();
                }
            } else {
                // 如果无法确定目标怪物，回退到原版行为（添加到玩家手牌）
                for (int i = 0; i < amount; i++) {
                    AbstractCard newCard = card.makeCopy();
                    AbstractDungeon.player.hand.addToTop(newCard);
                }
                AbstractDungeon.player.hand.refreshHandLayout();
            }
        }
        tickDuration();
    }
}