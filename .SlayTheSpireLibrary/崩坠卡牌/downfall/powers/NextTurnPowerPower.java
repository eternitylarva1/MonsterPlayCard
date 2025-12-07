/*    */ package downfall.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class NextTurnPowerPower extends AbstractPower {
/* 15 */   private static PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(downfallMod.makeID("NextTurnPowerPower"));
/*    */   private AbstractPower powerToGain;
/*    */   
/*    */   public NextTurnPowerPower(AbstractCreature owner, AbstractPower powerToGrant) {
/* 19 */     this.ID = "Next Turn " + powerToGrant.ID;
/* 20 */     this.name = powerStrings.NAME + powerToGrant.name;
/* 21 */     this.owner = owner;
/* 22 */     this.type = powerToGrant.type;
/* 23 */     this.isTurnBased = true;
/* 24 */     this.amount = powerToGrant.amount;
/* 25 */     this.img = powerToGrant.img;
/* 26 */     this.region48 = powerToGrant.region48;
/* 27 */     this.region128 = powerToGrant.region128;
/* 28 */     this.powerToGain = powerToGrant;
/* 29 */     updateDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public void renderIcons(SpriteBatch sb, float x, float y, Color c) {
/* 34 */     super.renderIcons(sb, x, y, Color.GREEN.cpy());
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 39 */     super.stackPower(stackAmount);
/* 40 */     this.powerToGain.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 45 */     flash();
/* 46 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, this.powerToGain, this.powerToGain.amount));
/* 47 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 52 */     if (this.powerToGain == null) {
/* 53 */       this.description = powerStrings.DESCRIPTIONS[0];
/*    */     } else {
/* 55 */       this.description = powerStrings.DESCRIPTIONS[1] + this.powerToGain.amount + powerStrings.DESCRIPTIONS[2] + this.powerToGain.name + powerStrings.DESCRIPTIONS[3];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\NextTurnPowerPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */