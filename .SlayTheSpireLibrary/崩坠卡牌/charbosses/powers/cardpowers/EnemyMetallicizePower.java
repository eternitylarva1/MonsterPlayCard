/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class EnemyMetallicizePower extends AbstractPower {
/* 11 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Metallicize"); public static final String POWER_ID = "Metallicize";
/* 12 */   public static final String NAME = powerStrings.NAME;
/* 13 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public EnemyMetallicizePower(AbstractCreature owner, int armorAmt) {
/* 16 */     this.name = NAME;
/* 17 */     this.ID = "Metallicize";
/* 18 */     this.owner = owner;
/* 19 */     this.amount = armorAmt;
/* 20 */     updateDescription();
/* 21 */     loadRegion("armor");
/*    */   }
/*    */ 
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 26 */     CardCrawlGame.sound.play("POWER_METALLICIZE", 0.05F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 31 */     if (this.owner.isPlayer) {
/* 32 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 34 */       this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 42 */     super.atEndOfTurn(isPlayer);
/* 43 */     flash();
/* 44 */     addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyMetallicizePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */