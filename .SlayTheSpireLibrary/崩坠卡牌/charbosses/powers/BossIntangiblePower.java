/*    */ package charbosses.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class BossIntangiblePower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Intangible";
/*    */   
/*    */   public BossIntangiblePower(AbstractCreature owner, int turns) {
/* 18 */     this.name = NAME;
/* 19 */     this.ID = "Intangible";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = turns;
/* 22 */     updateDescription();
/* 23 */     loadRegion("intangible");
/* 24 */     this.priority = 75;
/*    */   }
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 28 */     CardCrawlGame.sound.play("POWER_INTANGIBLE", 0.05F);
/*    */   }
/*    */   
/*    */   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
/* 32 */     if (damage > 1.0F) {
/* 33 */       damage = 1.0F;
/*    */     }
/*    */     
/* 36 */     return damage;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 40 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 44 */     flash();
/* 45 */     if (this.amount == 0) {
/* 46 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Intangible"));
/*    */     } else {
/* 48 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Intangible", 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 53 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Intangible");
/* 54 */   public static final String NAME = powerStrings.NAME;
/* 55 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\BossIntangiblePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */