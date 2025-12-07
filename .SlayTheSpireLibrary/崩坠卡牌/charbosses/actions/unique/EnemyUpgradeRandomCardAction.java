/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyUpgradeRandomCardAction
/*    */   extends AbstractGameAction
/*    */ {
/* 14 */   private AbstractCharBoss p = AbstractCharBoss.boss;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration != Settings.ACTION_DUR_FAST) {
/* 21 */       tickDuration();
/*    */       return;
/*    */     } 
/* 24 */     if (this.p.hand.group.size() <= 0) {
/* 25 */       this.isDone = true;
/*    */       return;
/*    */     } 
/* 28 */     CardGroup upgradeable = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 29 */     for (AbstractCard c : this.p.hand.group) {
/* 30 */       if (c.canUpgrade() && c.type != AbstractCard.CardType.STATUS) {
/* 31 */         upgradeable.addToTop(c);
/*    */       }
/*    */     } 
/* 34 */     if (upgradeable.size() > 0) {
/* 35 */       upgradeable.shuffle();
/* 36 */       ((AbstractCard)upgradeable.group.get(0)).upgrade();
/* 37 */       ((AbstractCard)upgradeable.group.get(0)).superFlash();
/* 38 */       ((AbstractCard)upgradeable.group.get(0)).applyPowers();
/*    */     } 
/* 40 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyUpgradeRandomCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */