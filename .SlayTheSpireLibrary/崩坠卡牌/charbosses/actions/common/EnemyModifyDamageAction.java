/*    */ package charbosses.actions.common;
/*    */ 
/*    */ import charbosses.helpers.EnemyGetAllInBattleInstances;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class EnemyModifyDamageAction
/*    */   extends AbstractGameAction {
/*    */   private UUID uuid;
/*    */   
/*    */   public EnemyModifyDamageAction(UUID targetUUID, int amount) {
/* 13 */     setValues(this.target, this.source, amount);
/* 14 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/* 15 */     this.uuid = targetUUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     for (AbstractCard abstractCard : EnemyGetAllInBattleInstances.get(this.uuid)) {
/* 21 */       AbstractCard c = abstractCard;
/* 22 */       abstractCard.baseDamage += this.amount;
/* 23 */       if (c.baseDamage < 0) {
/* 24 */         c.baseDamage = 0;
/*    */       }
/*    */     } 
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyModifyDamageAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */