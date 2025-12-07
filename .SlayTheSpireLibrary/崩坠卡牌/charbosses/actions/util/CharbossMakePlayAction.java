/*    */ package charbosses.actions.util;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CharbossMakePlayAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 16 */     if (AbstractCharBoss.boss != null) {
/* 17 */       AbstractCharBoss.boss.makePlay();
/*    */     }
/* 19 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\util\CharbossMakePlayAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */