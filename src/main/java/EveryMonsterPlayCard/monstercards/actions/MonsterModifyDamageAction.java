package EveryMonsterPlayCard.monstercards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

/**
 * 怪物版本的修改伤害动作
 */
public class MonsterModifyDamageAction extends AbstractGameAction {
    private UUID uuid;
    
    public MonsterModifyDamageAction(UUID targetUUID, int amount) {
        this.amount = amount;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.uuid = targetUUID;
    }
    
    @Override
    public void update() {
        for (AbstractCard abstractCard : GetAllInBattleInstances.get(this.uuid)) {
            AbstractCard c = abstractCard;
            c.baseDamage += this.amount;
            if (c.baseDamage < 0) {
                c.baseDamage = 0;
            }
        } 
        this.isDone = true;
    }
}