/*    */ package charbosses.actions.utility;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DestroyAntiCardsAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private String cardIDToKill;
/*    */   
/*    */   public DestroyAntiCardsAction(String ID) {
/* 16 */     this.cardIDToKill = ID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     ArrayList<AbstractCard> cardsToKill = new ArrayList<>();
/* 22 */     for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
/* 23 */       if (c.cardID == this.cardIDToKill) {
/* 24 */         cardsToKill.add(c);
/*    */       }
/*    */     } 
/* 27 */     for (AbstractCard c : cardsToKill) {
/* 28 */       AbstractDungeon.player.exhaustPile.removeCard(c);
/*    */     }
/* 30 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\utility\DestroyAntiCardsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */