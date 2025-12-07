/*    */ package charbosses.actions.orb;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class EnemyDecreaseMaxOrbAction
/*    */   extends AbstractGameAction {
/*    */   public EnemyDecreaseMaxOrbAction(int slotIncrease) {
/* 10 */     this.duration = Settings.ACTION_DUR_FAST;
/* 11 */     this.amount = slotIncrease;
/* 12 */     this.actionType = AbstractGameAction.ActionType.BLOCK;
/*    */   }
/*    */   
/*    */   public void update() {
/* 16 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 17 */       for (int i = 0; i < this.amount; i++) {
/* 18 */         AbstractCharBoss.boss.decreaseMaxOrbSlots(1);
/*    */       }
/*    */     }
/*    */     
/* 22 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyDecreaseMaxOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */