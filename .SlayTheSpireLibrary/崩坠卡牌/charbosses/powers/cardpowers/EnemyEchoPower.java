/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyEchoPower
/*    */   extends AbstractPower
/*    */ {
/* 22 */   private int cardsDoubledThisTurn = 1;
/*    */   
/*    */   public EnemyEchoPower(AbstractCreature owner, int amount) {
/* 25 */     this.name = NAME;
/* 26 */     this.ID = "Echo Form";
/* 27 */     this.owner = owner;
/* 28 */     this.amount = amount;
/* 29 */     updateDescription();
/* 30 */     loadRegion("echo");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 34 */     if (this.amount == 1) {
/* 35 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 37 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 78 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Echo Form");
/* 79 */   public static final String NAME = powerStrings.NAME;
/* 80 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   public static final String POWER_ID = "Echo Form";
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyEchoPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */