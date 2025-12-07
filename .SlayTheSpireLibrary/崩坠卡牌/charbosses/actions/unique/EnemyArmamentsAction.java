/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyArmamentsAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public static final String[] TEXT;
/* 17 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ArmamentsAction"); static {
/* 18 */     TEXT = uiStrings.TEXT;
/*    */   }
/*    */   
/*    */   private AbstractCharBoss p;
/*    */   private ArrayList<AbstractCard> cannotUpgrade;
/*    */   private boolean upgraded;
/*    */   
/*    */   public EnemyArmamentsAction(boolean armamentsPlus) {
/* 26 */     this.cannotUpgrade = new ArrayList<>();
/* 27 */     this.upgraded = false;
/* 28 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/* 29 */     this.p = AbstractCharBoss.boss;
/* 30 */     this.duration = Settings.ACTION_DUR_FAST;
/* 31 */     this.upgraded = armamentsPlus;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 36 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 37 */       if (this.upgraded) {
/* 38 */         for (AbstractCard c : this.p.hand.group) {
/* 39 */           if (c.canUpgrade()) {
/* 40 */             c.upgrade();
/* 41 */             c.superFlash();
/* 42 */             c.applyPowers();
/*    */           } 
/*    */         } 
/* 45 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 48 */       for (AbstractCard c : this.p.hand.group) {
/* 49 */         if (!c.canUpgrade()) {
/* 50 */           this.cannotUpgrade.add(c);
/*    */         }
/*    */       } 
/* 53 */       if (this.cannotUpgrade.size() == this.p.hand.group.size()) {
/* 54 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 57 */       for (AbstractCard c : this.p.hand.group) {
/* 58 */         if (c.canUpgrade()) {
/* 59 */           c.upgrade();
/* 60 */           c.superFlash();
/* 61 */           c.applyPowers();
/* 62 */           this.isDone = true;
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } 
/* 67 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyArmamentsAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */