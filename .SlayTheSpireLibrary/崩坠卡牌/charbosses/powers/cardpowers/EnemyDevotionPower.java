/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyDevotionPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "DevotionPower";
/*    */   
/*    */   public EnemyDevotionPower(AbstractCreature owner, int newAmount) {
/* 20 */     this.name = powerStrings.NAME;
/* 21 */     this.ID = "DevotionPower";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = newAmount;
/* 24 */     updateDescription();
/* 25 */     loadRegion("devotion");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 29 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 33 */     flash();
/* 34 */     if (!AbstractCharBoss.boss.hasPower("Mantra") && this.amount >= 10) {
/* 35 */       addToBot((AbstractGameAction)new EnemyChangeStanceAction("Divinity"));
/*    */     } else {
/* 37 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new EnemyMantraPower(this.owner, this.amount), this.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 43 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("DevotionPower");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyDevotionPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */