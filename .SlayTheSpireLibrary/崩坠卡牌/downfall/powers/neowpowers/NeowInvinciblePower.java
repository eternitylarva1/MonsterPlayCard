/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NeowInvinciblePower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Invincible";
/*    */   
/*    */   public NeowInvinciblePower(AbstractCreature owner, int amount) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "Invincible";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amount;
/* 23 */     this.maxAmt = amount;
/* 24 */     updateDescription();
/* 25 */     loadRegion("heartDef");
/* 26 */     this.priority = 99;
/*    */   }
/*    */   
/*    */   public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
/* 30 */     if (damageAmount > this.amount) {
/* 31 */       damageAmount = this.amount;
/*    */     }
/*    */     
/* 34 */     this.amount -= damageAmount;
/* 35 */     if (this.amount <= 0 && 
/* 36 */       AbstractDungeon.player.hasPower(HeartsFavorPower.POWER_ID)) {
/* 37 */       AbstractDungeon.player.getPower(HeartsFavorPower.POWER_ID).onSpecificTrigger();
/*    */     }
/*    */     
/* 40 */     if (this.amount < 0) {
/* 41 */       this.amount = 0;
/*    */     }
/*    */     
/* 44 */     updateDescription();
/* 45 */     return damageAmount;
/*    */   }
/*    */   
/*    */   public void atStartOfTurn() {
/* 49 */     this.amount = this.maxAmt;
/* 50 */     updateDescription();
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 54 */     if (this.amount <= 0) {
/* 55 */       this.description = DESCRIPTIONS[2];
/*    */     } else {
/* 57 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 63 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Invincible");
/* 64 */   public static final String NAME = powerStrings.NAME;
/* 65 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private int maxAmt;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\NeowInvinciblePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */