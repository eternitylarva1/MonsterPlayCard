package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 怪物版本的每张非攻击牌获得格挡动作
 */
public class MonsterBlockPerNonAttackAction extends AbstractGameAction {
    private int blockPerCard;
    
    public MonsterBlockPerNonAttackAction(int blockAmount) {
        this.blockPerCard = blockAmount;
        setValues(AbstractDungeon.player, AbstractDungeon.player, 0);
        this.actionType = ActionType.BLOCK;
    }
    
    @Override
    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
        Iterator<AbstractCard> var2 = AbstractDungeon.player.hand.group.iterator();
        
        while (var2.hasNext()) {
            AbstractCard c = var2.next();
            if (c.type != AbstractCard.CardType.ATTACK) {
                cardsToExhaust.add(c);
            }
        } 
        
        var2 = cardsToExhaust.iterator();
        
        while (var2.hasNext()) {
            AbstractCard c = var2.next();
            addToTop((AbstractGameAction)new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, this.blockPerCard));
        } 
        
        var2 = cardsToExhaust.iterator();
        
        while (var2.hasNext()) {
            AbstractCard c = var2.next();
            addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        } 
        
        this.isDone = true;
    }
}