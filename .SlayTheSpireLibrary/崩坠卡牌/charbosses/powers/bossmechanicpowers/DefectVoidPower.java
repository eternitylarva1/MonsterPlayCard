/*    */ package charbosses.powers.bossmechanicpowers;
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
/*    */ 
/*    */ public class DefectVoidPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:DefectVoidPower";
/*    */   
/*    */   public DefectVoidPower(AbstractCreature owner) {
/* 26 */     this.name = NAME;
/* 27 */     this.ID = "downfall:DefectVoidPower";
/* 28 */     this.owner = owner;
/* 29 */     this.amount = 0;
/* 30 */     updateDescription();
/* 31 */     loadRegion("curiosity");
/* 32 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 36 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */   
/* 40 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:DefectVoidPower");
/* 41 */   public static final String NAME = powerStrings.NAME;
/* 42 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\DefectVoidPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */