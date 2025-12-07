/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyInfiniteBladesPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:Enemy Infinite Blades";
/* 16 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Infinite Blades");
/*    */ 
/*    */   
/*    */   public EnemyInfiniteBladesPower(AbstractCreature owner, int bladeAmt) {
/* 20 */     this.name = powerStrings.NAME;
/* 21 */     this.ID = "downfall:Enemy Infinite Blades";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = bladeAmt;
/* 24 */     updateDescription();
/* 25 */     loadRegion("infiniteBlades");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 40 */     this.fontScale = 8.0F;
/* 41 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 46 */     if (this.amount > 1) {
/* 47 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */     } else {
/* 49 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyInfiniteBladesPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */