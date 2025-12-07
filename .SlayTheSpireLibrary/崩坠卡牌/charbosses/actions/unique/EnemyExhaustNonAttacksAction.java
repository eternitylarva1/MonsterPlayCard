/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyExhaustNonAttacksAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public EnemyExhaustNonAttacksAction() {
/* 19 */     setValues((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss);
/* 20 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/*    */   }
/*    */   
/*    */   public void update() {
/* 24 */     Iterator<AbstractCard> var2 = AbstractCharBoss.boss.hand.group.iterator();
/*    */ 
/*    */     
/* 27 */     while (var2.hasNext()) {
/* 28 */       AbstractCard c = var2.next();
/* 29 */       if (c.type != AbstractCard.CardType.ATTACK) {
/* 30 */         addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractCharBoss.boss.hand));
/*    */       }
/*    */     } 
/*    */     
/* 34 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyExhaustNonAttacksAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */