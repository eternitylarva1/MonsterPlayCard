/*    */ package charbosses.actions.common;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyReduceCostAction
/*    */   extends AbstractGameAction
/*    */ {
/* 12 */   private AbstractCard card = null;
/*    */   
/*    */   public EnemyReduceCostAction(AbstractCard card) {
/* 15 */     this.card = card;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     this.card.modifyCostForCombat(-1);
/* 22 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyReduceCostAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */