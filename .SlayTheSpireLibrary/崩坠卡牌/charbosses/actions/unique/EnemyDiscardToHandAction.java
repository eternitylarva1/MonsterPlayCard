/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class EnemyDiscardToHandAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractCard card;
/*    */   private AbstractCharBoss p;
/*    */   
/*    */   public EnemyDiscardToHandAction(AbstractCharBoss p, AbstractCard card) {
/* 15 */     this.p = p;
/* 16 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/* 17 */     this.card = card;
/* 18 */     this.duration = Settings.ACTION_DUR_FAST;
/*    */   }
/*    */   
/*    */   public void update() {
/* 22 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 23 */       if (this.p.hand.size() < 10) {
/* 24 */         this.p.hand.addToHand(this.card);
/* 25 */         this.card.unhover();
/* 26 */         this.card.setAngle(0.0F, true);
/* 27 */         this.card.lighten(false);
/* 28 */         this.card.drawScale = 0.12F;
/* 29 */         this.card.targetDrawScale = 0.75F;
/* 30 */         this.card.applyPowers();
/*    */       } 
/*    */ 
/*    */       
/* 34 */       this.p.hand.refreshHandLayout();
/* 35 */       this.p.hand.glowCheck();
/*    */     } 
/*    */     
/* 38 */     tickDuration();
/* 39 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyDiscardToHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */