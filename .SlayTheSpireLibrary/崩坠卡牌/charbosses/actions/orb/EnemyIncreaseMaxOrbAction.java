/*    */ package charbosses.actions.orb;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class EnemyIncreaseMaxOrbAction extends AbstractGameAction {
/*    */   public EnemyIncreaseMaxOrbAction(int slotIncrease) {
/*  9 */     this.duration = Settings.ACTION_DUR_FAST;
/* 10 */     this.amount = slotIncrease;
/* 11 */     this.actionType = AbstractGameAction.ActionType.BLOCK;
/*    */   }
/*    */   public void update() {
/* 14 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 15 */       for (int i = 0; i < this.amount; i++) {
/* 16 */         AbstractCharBoss.boss.increaseMaxOrbSlots(1, true);
/*    */       }
/*    */     }
/*    */     
/* 20 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyIncreaseMaxOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */