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
/*    */ public class DefectAncientConstructPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:DefectAncientConstructPower";
/*    */   
/*    */   public DefectAncientConstructPower(AbstractCreature owner, int stack) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "downfall:DefectAncientConstructPower";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = stack;
/* 27 */     updateDescription();
/* 28 */     loadRegion("curiosity");
/* 29 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 33 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:DefectAncientConstructPower");
/* 46 */   public static final String NAME = powerStrings.NAME;
/* 47 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\DefectAncientConstructPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */