/*    */ package charbosses.actions.orb;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ 
/*    */ public class EnemyAnimateOrbAction extends AbstractGameAction {
/*    */   private int orbCount;
/*    */   
/*    */   public EnemyAnimateOrbAction(int amount) {
/* 10 */     this.orbCount = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 15 */     for (int i = 0; i < this.orbCount; i++) {
/* 16 */       AbstractCharBoss.boss.triggerEvokeAnimation(i);
/*    */     }
/* 18 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyAnimateOrbAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */