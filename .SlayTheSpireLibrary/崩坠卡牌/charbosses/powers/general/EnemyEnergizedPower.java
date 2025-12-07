/*    */ package charbosses.powers.general;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyEnergizedPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:EnemyEnergized";
/*    */   public static final String NAME;
/*    */   public static final String[] DESCRIPTIONS;
/* 20 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Energized"); static {
/* 21 */     NAME = powerStrings.NAME;
/* 22 */     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   }
/*    */   
/*    */   public EnemyEnergizedPower(AbstractCreature owner, int energyAmt) {
/* 26 */     this.name = NAME;
/* 27 */     this.ID = "downfall:EnemyEnergized";
/* 28 */     this.owner = owner;
/* 29 */     this.amount = energyAmt;
/* 30 */     if (this.amount >= 999) {
/* 31 */       this.amount = 999;
/*    */     }
/* 33 */     updateDescription();
/* 34 */     loadRegion("energized_green");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 39 */     if (this.amount == 1) {
/* 40 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 42 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 48 */     flash();
/* 49 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EnemyGainEnergyAction(this.amount));
/* 50 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\general\EnemyEnergizedPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */