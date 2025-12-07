/*    */ package charbosses.actions.utility;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ 
/*    */ 
/*    */ public class EnemyHandCheckAction
/*    */   extends AbstractGameAction
/*    */ {
/* 10 */   private AbstractCharBoss player = AbstractCharBoss.boss;
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 15 */     this.player.hand.applyPowers();
/* 16 */     this.player.hand.glowCheck();
/* 17 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\utility\EnemyHandCheckAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */