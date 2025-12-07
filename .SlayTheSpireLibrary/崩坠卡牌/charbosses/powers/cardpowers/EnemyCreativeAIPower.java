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
/*    */ public class EnemyCreativeAIPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Creative AI";
/*    */   
/*    */   public EnemyCreativeAIPower(AbstractCreature owner, int amount) {
/* 24 */     this.name = NAME;
/* 25 */     this.ID = "Creative AI";
/* 26 */     this.owner = owner;
/* 27 */     this.amount = amount;
/* 28 */     updateDescription();
/* 29 */     loadRegion("ai");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 33 */     if (this.amount > 1) {
/* 34 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */     } else {
/* 36 */       this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 42 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Creative AI");
/* 43 */   public static final String NAME = powerStrings.NAME;
/* 44 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyCreativeAIPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */