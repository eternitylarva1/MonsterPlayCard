/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DoubleDamagePower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyPhantasmalPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:Phantasmal";
/* 20 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Phantasmal");
/*    */ 
/*    */   
/*    */   public EnemyPhantasmalPower(AbstractCreature owner, int amount) {
/* 24 */     this.name = powerStrings.NAME;
/* 25 */     this.ID = "Phantasmal";
/* 26 */     this.owner = owner;
/* 27 */     this.amount = amount;
/* 28 */     updateDescription();
/* 29 */     loadRegion("phantasmal");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 34 */     flash();
/* 35 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new DoubleDamagePower(this.owner, 1, true), this.amount));
/* 36 */     addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Phantasmal", 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 41 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyPhantasmalPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */