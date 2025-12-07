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
/*    */ public class HermitDoomsday
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:HermitDoomsday";
/*    */   
/*    */   public HermitDoomsday(AbstractCreature owner) {
/* 24 */     this.name = NAME;
/* 25 */     this.ID = "downfall:HermitDoomsday";
/* 26 */     this.owner = owner;
/* 27 */     this.amount = 0;
/* 28 */     updateDescription();
/* 29 */     loadRegion("curiosity");
/* 30 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 34 */     this.description = DESC[0];
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
/* 49 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:HermitDoomsday");
/* 50 */   public static final String NAME = powerStrings.NAME;
/* 51 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\HermitDoomsday.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */