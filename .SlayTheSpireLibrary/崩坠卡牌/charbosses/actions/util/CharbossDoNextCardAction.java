/*    */ package charbosses.actions.util;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ 
/*    */ public class CharbossDoNextCardAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 10 */     if (AbstractCharBoss.boss != null)
/* 11 */       AbstractCharBoss.boss.makePlay(); 
/* 12 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\util\CharbossDoNextCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */