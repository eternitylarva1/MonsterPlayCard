/*    */ package charbosses.actions.orb;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class EnemyEvokeWithoutRemovingOrbAction extends AbstractGameAction {
/*    */   private int orbCount;
/*    */   
/*    */   public EnemyEvokeWithoutRemovingOrbAction(int amount) {
/* 11 */     if (Settings.FAST_MODE) {
/* 12 */       this.startDuration = Settings.ACTION_DUR_FAST;
/*    */     } else {
/* 14 */       this.startDuration = Settings.ACTION_DUR_XFAST;
/*    */     } 
/*    */     
/* 17 */     this.duration = this.startDuration;
/* 18 */     this.orbCount = amount;
/* 19 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/*    */   }
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == this.startDuration) {
/* 24 */       for (int i = 0; i < this.orbCount; i++) {
/* 25 */         AbstractCharBoss.boss.evokeWithoutLosingOrb();
/*    */       }
/*    */     }
/*    */     
/* 29 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyEvokeWithoutRemovingOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */