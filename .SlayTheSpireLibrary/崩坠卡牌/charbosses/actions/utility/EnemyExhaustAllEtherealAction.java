/*    */ package charbosses.actions.utility;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyExhaustAllEtherealAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 15 */     for (AbstractCard c : AbstractCharBoss.boss.hand.group) {
/* 16 */       if (c.isEthereal) {
/* 17 */         addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractCharBoss.boss.hand));
/*    */       }
/*    */     } 
/* 20 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\utility\EnemyExhaustAllEtherealAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */