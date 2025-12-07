/*    */ package charbosses.actions.util;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class CharbossDoCardQueueAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractCard c;
/*    */   
/*    */   public CharbossDoCardQueueAction(AbstractCard c) {
/* 13 */     this.c = c;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 18 */     if (AbstractCharBoss.boss != null) {
/* 19 */       AbstractCharBoss.boss.useCard(this.c, (AbstractMonster)AbstractCharBoss.boss, this.c.energyOnUse);
/*    */     }
/*    */     
/* 22 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\util\CharbossDoCardQueueAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */