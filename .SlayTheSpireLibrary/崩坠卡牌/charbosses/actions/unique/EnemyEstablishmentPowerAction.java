/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EnemyEstablishmentPowerAction
/*    */   extends AbstractGameAction {
/*    */   private int discountAmount;
/*    */   
/*    */   public EnemyEstablishmentPowerAction(int discountAmount) {
/* 13 */     this.discountAmount = discountAmount;
/*    */   }
/*    */   
/*    */   public void update() {
/* 17 */     Iterator<AbstractCard> var1 = AbstractCharBoss.boss.hand.group.iterator();
/*    */ 
/*    */ 
/*    */     
/*    */     while (true) {
/* 22 */       if (!var1.hasNext()) {
/* 23 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/* 27 */       AbstractCard c = var1.next();
/* 28 */       if (c.selfRetain || c.retain)
/*    */       {
/* 30 */         c.modifyCostForCombat(-this.discountAmount);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyEstablishmentPowerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */