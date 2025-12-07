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
/*    */ public class IroncladFortificationPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:IroncladFortificationPower";
/*    */   
/*    */   public IroncladFortificationPower(AbstractCreature owner) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "downfall:IroncladFortificationPower";
/* 24 */     this.owner = owner;
/* 25 */     this.amount = 0;
/* 26 */     updateDescription();
/* 27 */     loadRegion("curiosity");
/* 28 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 32 */     this.description = DESC[0] + '\n' + DESC[1];
/*    */   }
/*    */ 
/*    */   
/* 36 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:IroncladFortificationPower");
/* 37 */   public static final String NAME = powerStrings.NAME;
/* 38 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\IroncladFortificationPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */