/*    */ package charbosses.powers.general;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ public class EnemyDrawCardNextTurnPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:Enemy Draw Card";
/*    */   public static final String NAME;
/*    */   public static final String[] DESCRIPTIONS;
/* 17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Draw Card"); static {
/* 18 */     NAME = powerStrings.NAME;
/* 19 */     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   }
/*    */   
/*    */   public EnemyDrawCardNextTurnPower(AbstractCreature owner, int drawAmount) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "downfall:Enemy Draw Card";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = drawAmount;
/* 27 */     updateDescription();
/* 28 */     loadRegion("carddraw");
/* 29 */     this.priority = 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 34 */     if (this.amount > 1) {
/* 35 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } else {
/* 37 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 43 */     flash();
/*    */     
/* 45 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "downfall:Enemy Draw Card"));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\general\EnemyDrawCardNextTurnPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */