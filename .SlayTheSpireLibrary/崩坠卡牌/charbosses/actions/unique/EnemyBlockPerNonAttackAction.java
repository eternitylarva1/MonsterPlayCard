/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyBlockPerNonAttackAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private int blockPerCard;
/*    */   
/*    */   public EnemyBlockPerNonAttackAction(int blockAmount) {
/* 23 */     this.blockPerCard = blockAmount;
/* 24 */     setValues((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss);
/* 25 */     this.actionType = AbstractGameAction.ActionType.BLOCK;
/*    */   }
/*    */   
/*    */   public void update() {
/* 29 */     ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
/* 30 */     Iterator<AbstractCard> var2 = AbstractCharBoss.boss.hand.group.iterator();
/*    */ 
/*    */     
/* 33 */     while (var2.hasNext()) {
/* 34 */       AbstractCard c = var2.next();
/* 35 */       if (c.type != AbstractCard.CardType.ATTACK) {
/* 36 */         cardsToExhaust.add(c);
/*    */       }
/*    */     } 
/*    */     
/* 40 */     var2 = cardsToExhaust.iterator();
/*    */     
/* 42 */     while (var2.hasNext()) {
/* 43 */       AbstractCard c = var2.next();
/* 44 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, this.blockPerCard));
/*    */     } 
/*    */     
/* 47 */     var2 = cardsToExhaust.iterator();
/*    */     
/* 49 */     while (var2.hasNext()) {
/* 50 */       AbstractCard c = var2.next();
/* 51 */       addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractCharBoss.boss.hand));
/*    */     } 
/*    */     
/* 54 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyBlockPerNonAttackAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */