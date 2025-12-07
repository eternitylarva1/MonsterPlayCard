/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyApotheosisAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 21 */     if (this.duration == Settings.ACTION_DUR_MED) {
/* 22 */       AbstractCharBoss p = AbstractCharBoss.boss;
/* 23 */       upgradeAllCardsInGroup(p.hand);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 29 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void upgradeAllCardsInGroup(CardGroup cardGroup) {
/* 35 */     Iterator<AbstractCard> var2 = cardGroup.group.iterator();
/*    */     
/* 37 */     while (var2.hasNext()) {
/* 38 */       AbstractCard c = var2.next();
/* 39 */       if (c.canUpgrade()) {
/* 40 */         if (cardGroup.type == CardGroup.CardGroupType.HAND) {
/* 41 */           c.superFlash();
/*    */         }
/*    */         
/* 44 */         c.upgrade();
/* 45 */         c.applyPowers();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyApotheosisAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */