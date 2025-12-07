/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyDevaFormPower
/*    */   extends AbstractPower
/*    */ {
/* 17 */   private int energyGainAmount = 1;
/*    */   
/*    */   public EnemyDevaFormPower(AbstractCreature owner) {
/* 20 */     this.name = NAME;
/* 21 */     this.ID = "DevaForm";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = 1;
/* 24 */     this.energyGainAmount = 1;
/* 25 */     updateDescription();
/* 26 */     loadRegion("deva2");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 30 */     super.stackPower(stackAmount);
/* 31 */     this.energyGainAmount++;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 35 */     if (this.energyGainAmount == 1) {
/* 36 */       this.description = DESCRIPTIONS[0] + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
/*    */     } else {
/* 38 */       this.description = DESCRIPTIONS[1] + (this.energyGainAmount - 1) + DESCRIPTIONS[2] + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 44 */     flash();
/* 45 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new EnemyGainEnergyAction(this.energyGainAmount));
/*    */     
/* 47 */     this.energyGainAmount += this.amount;
/* 48 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/* 52 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DevaForm");
/* 53 */   public static final String NAME = powerStrings.NAME;
/* 54 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   public static final String POWER_ID = "DevaForm";
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyDevaFormPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */