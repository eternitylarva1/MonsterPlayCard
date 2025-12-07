/*    */ package charbosses.actions.utility;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ 
/*    */ public class EnemyShowCardAction extends AbstractGameAction {
/*    */   private static final float PURGE_DURATION = 0.2F;
/*    */   
/*    */   public EnemyShowCardAction(AbstractCard card) {
/* 12 */     this.card = null;
/* 13 */     setValues((AbstractCreature)AbstractCharBoss.boss, null, 1);
/* 14 */     this.card = card;
/* 15 */     this.duration = 0.2F;
/* 16 */     this.actionType = AbstractGameAction.ActionType.SPECIAL;
/*    */   }
/*    */   private AbstractCard card;
/*    */   
/*    */   public void update() {
/* 21 */     if (this.duration == 0.2F) {
/* 22 */       if (AbstractCharBoss.boss.limbo.contains(this.card)) {
/* 23 */         AbstractCharBoss.boss.limbo.removeCard(this.card);
/*    */       }
/* 25 */       AbstractCharBoss.boss.cardInUse = null;
/*    */     } 
/* 27 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\utility\EnemyShowCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */