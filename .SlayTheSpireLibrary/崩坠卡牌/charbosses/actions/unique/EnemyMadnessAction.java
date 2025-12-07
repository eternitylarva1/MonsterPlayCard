/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyMadnessAction
/*    */   extends AbstractGameAction
/*    */ {
/* 18 */   private AbstractCharBoss p = AbstractCharBoss.boss;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 25 */       boolean betterPossible = false;
/* 26 */       boolean possible = false;
/* 27 */       Iterator<AbstractCard> var3 = this.p.hand.group.iterator();
/*    */       
/* 29 */       while (var3.hasNext()) {
/* 30 */         AbstractCard c = var3.next();
/* 31 */         if (c.costForTurn > 0) {
/* 32 */           betterPossible = true; continue;
/* 33 */         }  if (c.cost > 0) {
/* 34 */           possible = true;
/*    */         }
/*    */       } 
/*    */       
/* 38 */       if (betterPossible || possible) {
/* 39 */         findAndModifyCard(betterPossible);
/*    */       }
/*    */     } 
/*    */     
/* 43 */     tickDuration();
/*    */   }
/*    */   
/*    */   private void findAndModifyCard(boolean better) {
/* 47 */     AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
/* 48 */     if (better) {
/* 49 */       if (c.costForTurn > 0) {
/* 50 */         c.cost = 0;
/* 51 */         c.costForTurn = 0;
/* 52 */         c.isCostModified = true;
/* 53 */         c.superFlash(Color.GOLD.cpy());
/*    */       } else {
/* 55 */         findAndModifyCard(better);
/*    */       } 
/* 57 */     } else if (c.cost > 0) {
/* 58 */       c.cost = 0;
/* 59 */       c.costForTurn = 0;
/* 60 */       c.isCostModified = true;
/* 61 */       c.superFlash(Color.GOLD.cpy());
/*    */     } else {
/* 63 */       findAndModifyCard(better);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyMadnessAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */