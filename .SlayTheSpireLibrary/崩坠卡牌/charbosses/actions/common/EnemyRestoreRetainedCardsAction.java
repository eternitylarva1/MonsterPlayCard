/*    */ package charbosses.actions.common;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EnemyRestoreRetainedCardsAction extends AbstractGameAction {
/*    */   private CardGroup group;
/*    */   private AbstractCharBoss boss;
/*    */   
/*    */   public EnemyRestoreRetainedCardsAction(AbstractCharBoss boss, CardGroup group) {
/* 15 */     setValues((AbstractCreature)boss, this.source, -1);
/* 16 */     this.group = group;
/* 17 */     this.boss = boss;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 22 */     this.isDone = true;
/* 23 */     Iterator<AbstractCard> c = this.group.group.iterator();
/* 24 */     while (c.hasNext()) {
/* 25 */       AbstractCard e = c.next();
/* 26 */       if (e.retain || e.selfRetain) {
/* 27 */         e.onRetained();
/* 28 */         this.boss.hand.addToTop(e);
/* 29 */         e.retain = false;
/* 30 */         c.remove();
/*    */       } 
/*    */     } 
/* 33 */     if (this.boss != null)
/* 34 */       this.boss.hand.refreshHandLayout(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyRestoreRetainedCardsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */