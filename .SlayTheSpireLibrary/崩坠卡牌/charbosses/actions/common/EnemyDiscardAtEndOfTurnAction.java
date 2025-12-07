/*    */ package charbosses.actions.common;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ public class EnemyDiscardAtEndOfTurnAction
/*    */   extends AbstractGameAction
/*    */ {
/* 16 */   private static final float DURATION = Settings.ACTION_DUR_XFAST;
/*    */   
/*    */   private AbstractCharBoss boss;
/*    */ 
/*    */   
/*    */   public EnemyDiscardAtEndOfTurnAction(AbstractCharBoss boss) {
/* 22 */     this.duration = DURATION;
/* 23 */     this.boss = boss;
/*    */   }
/*    */   
/*    */   public EnemyDiscardAtEndOfTurnAction() {
/* 27 */     this(AbstractCharBoss.boss);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 32 */     if (this.duration == DURATION) {
/* 33 */       Iterator<AbstractCard> c = this.boss.hand.group.iterator();
/* 34 */       while (c.hasNext()) {
/* 35 */         AbstractCard e = c.next();
/* 36 */         if (e.retain || e.selfRetain) {
/* 37 */           this.boss.limbo.addToTop(e);
/* 38 */           c.remove();
/*    */         } 
/*    */       } 
/* 41 */       addToTop(new EnemyRestoreRetainedCardsAction(this.boss, this.boss.limbo));
/* 42 */       if (!this.boss.hasRelic("Runic Pyramid") && !this.boss.hasPower("Equilibrium")) {
/* 43 */         for (int tempSize = this.boss.hand.size(), i = 0; i < tempSize; i++) {
/* 44 */           addToTop(new EnemyDiscardAction((AbstractCreature)this.boss, null, this.boss.hand.size(), true));
/*    */         }
/*    */       }
/* 47 */       ArrayList<AbstractCard> cards = (ArrayList<AbstractCard>)this.boss.hand.group.clone();
/* 48 */       Collections.shuffle(cards);
/* 49 */       for (AbstractCard c2 : cards) {
/* 50 */         c2.triggerOnEndOfPlayerTurn();
/*    */       }
/* 52 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyDiscardAtEndOfTurnAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */