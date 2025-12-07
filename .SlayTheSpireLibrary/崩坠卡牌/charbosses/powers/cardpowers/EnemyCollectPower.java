/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyCollectPower
/*    */   extends AbstractPower
/*    */ {
/* 18 */   private int cardsDoubledThisTurn = 1;
/*    */   
/*    */   public EnemyCollectPower(AbstractCreature owner, int amount) {
/* 21 */     this.name = NAME;
/* 22 */     this.ID = "Collect";
/* 23 */     this.owner = owner;
/* 24 */     this.amount = amount;
/* 25 */     updateDescription();
/* 26 */     loadRegion("energized_blue");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 30 */     if (this.amount == 1) {
/* 31 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 33 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 39 */     flash();
/* 40 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Collect"));
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
/*    */ 
/*    */   
/* 80 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Collect");
/* 81 */   public static final String NAME = powerStrings.NAME;
/* 82 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   public static final String POWER_ID = "Collect";
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyCollectPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */