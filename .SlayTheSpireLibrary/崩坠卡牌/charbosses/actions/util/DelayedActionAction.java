/*    */ package charbosses.actions.util;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ 
/*    */ public class DelayedActionAction extends AbstractGameAction {
/*    */   AbstractGameAction act;
/*    */   
/*    */   public DelayedActionAction(AbstractGameAction a) {
/*  9 */     this.act = a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 14 */     addToBot(this.act);
/* 15 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\util\DelayedActionAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */