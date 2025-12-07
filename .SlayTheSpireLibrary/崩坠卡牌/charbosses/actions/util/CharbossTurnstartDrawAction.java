/*    */ package charbosses.actions.util;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ 
/*    */ public class CharbossTurnstartDrawAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 10 */     if (AbstractCharBoss.boss != null) AbstractCharBoss.boss.endTurnStartTurn(); 
/* 11 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\util\CharbossTurnstartDrawAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */