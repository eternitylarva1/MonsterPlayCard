/*    */ package downfall.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ public class EnemyDemonFormPower extends AbstractPower {
/*    */   public static final String POWER_ID = "Demon Form";
/*    */   
/*    */   public EnemyDemonFormPower(AbstractCreature owner, int strengthAmount) {
/* 15 */     this.name = powerStrings.NAME;
/* 16 */     this.ID = "Demon Form";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = strengthAmount;
/* 19 */     updateDescription();
/* 20 */     loadRegion("demonForm");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 24 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void atEndOfRound() {
/* 28 */     flash();
/* 29 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, this.amount), this.amount));
/*    */   }
/*    */ 
/*    */   
/* 33 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Demon Form");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\EnemyDemonFormPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */