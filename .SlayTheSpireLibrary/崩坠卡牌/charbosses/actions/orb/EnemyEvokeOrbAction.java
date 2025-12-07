/*    */ package charbosses.actions.orb;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class EnemyEvokeOrbAction extends AbstractGameAction {
/*    */   private int orbCount;
/*    */   
/*    */   public EnemyEvokeOrbAction(int amount) {
/* 11 */     if (Settings.FAST_MODE) {
/* 12 */       this.duration = Settings.ACTION_DUR_XFAST;
/*    */     } else {
/* 14 */       this.duration = Settings.ACTION_DUR_FAST;
/*    */     } 
/* 16 */     this.duration = this.startDuration;
/* 17 */     this.orbCount = amount;
/* 18 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == this.startDuration) {
/* 24 */       for (int i = 0; i < this.orbCount; i++) {
/* 25 */         AbstractCharBoss.boss.evokeOrb();
/*    */       }
/*    */     }
/* 28 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyEvokeOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */