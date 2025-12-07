/*    */ package charbosses.actions.common;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyNotStanceCheckAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractGameAction actionToBuffer;
/*    */   
/*    */   public EnemyNotStanceCheckAction(AbstractGameAction actionToCheck) {
/* 16 */     this.actionToBuffer = actionToCheck;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     if (!(AbstractCharBoss.boss.stance instanceof charbosses.stances.EnNeutralStance)) {
/* 23 */       addToBot(this.actionToBuffer);
/*    */     }
/*    */     
/* 26 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyNotStanceCheckAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */