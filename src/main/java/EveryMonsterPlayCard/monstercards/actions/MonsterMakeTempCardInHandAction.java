package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;

/**
 * 怪物版本的制造临时卡牌到手牌动作
 */
public class MonsterMakeTempCardInHandAction extends AbstractGameAction {
    private AbstractCard card;
    private int amount;
    
    public MonsterMakeTempCardInHandAction(AbstractCard card, int amount) {
        this.card = card;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }
    
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            // 使用原版的MakeTempCardInHandAction，但目标改为怪物
            addToTop(new MakeTempCardInHandAction(this.card, this.amount));
        }
        tickDuration();
    }
}