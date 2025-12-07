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
/*    */ public class IroncladMushroomPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:IroncladMushroomPower";
/*    */   
/*    */   public IroncladMushroomPower(AbstractCreature owner) {
/* 20 */     this.name = NAME;
/* 21 */     this.ID = "downfall:IroncladMushroomPower";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = 0;
/* 24 */     updateDescription();
/* 25 */     loadRegion("curiosity");
/* 26 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 30 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */   
/* 34 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:IroncladMushroomPower");
/* 35 */   public static final String NAME = powerStrings.NAME;
/* 36 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\IroncladMushroomPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */