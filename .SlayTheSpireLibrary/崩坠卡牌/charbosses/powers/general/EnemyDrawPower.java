/*    */ package charbosses.powers.general;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyDrawPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:EnemyDraw";
/*    */   public static final String NAME;
/*    */   public static final String[] DESCRIPTIONS;
/* 16 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Draw"); static {
/* 17 */     NAME = powerStrings.NAME;
/* 18 */     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   }
/*    */   
/*    */   public EnemyDrawPower(AbstractCreature owner, int drawAmount) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "downfall:EnemyDraw";
/* 24 */     this.owner = owner;
/* 25 */     this.amount = drawAmount;
/* 26 */     updateDescription();
/* 27 */     loadRegion("draw");
/* 28 */     this.priority = 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 33 */     if (this.amount > 0) {
/* 34 */       if (this.amount == 1) {
/* 35 */         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */       } else {
/* 37 */         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3];
/*    */       } 
/*    */       
/* 40 */       this.type = AbstractPower.PowerType.BUFF;
/*    */     } else {
/* 42 */       if (this.amount == -1) {
/* 43 */         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
/*    */       } else {
/* 45 */         this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[4];
/*    */       } 
/*    */       
/* 48 */       this.type = AbstractPower.PowerType.DEBUFF;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 55 */     flash();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\general\EnemyDrawPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */