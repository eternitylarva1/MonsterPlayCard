/*    */ package downfall.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ public class PerformXAction
/*    */   extends AbstractGameAction {
/* 10 */   protected int baseValue = -1;
/*    */   
/*    */   protected boolean freeToPlayOnce = false;
/* 13 */   protected AbstractXAction XAction = null;
/*    */   
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public PerformXAction(AbstractXAction XAction, AbstractPlayer p, int energyOnUse, boolean freeToPlayOnce) {
/* 18 */     this.baseValue = energyOnUse;
/* 19 */     this.freeToPlayOnce = freeToPlayOnce;
/* 20 */     this.XAction = XAction;
/* 21 */     this.p = p;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     int effect = EnergyPanel.totalCount;
/* 27 */     if (this.baseValue != -1) {
/* 28 */       effect = this.baseValue;
/*    */     }
/*    */     
/* 31 */     if (this.p.hasRelic("Chemical X")) {
/* 32 */       effect += 2;
/* 33 */       this.p.getRelic("Chemical X").flash();
/*    */     } 
/*    */     
/* 36 */     this.XAction.initialize(effect);
/* 37 */     if (this.XAction.amount > 0) {
/* 38 */       AbstractDungeon.actionManager.addToTop(this.XAction);
/*    */     }
/*    */     
/* 41 */     if (!this.freeToPlayOnce) {
/* 42 */       this.p.energy.use(EnergyPanel.totalCount);
/*    */     }
/* 44 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\PerformXAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */