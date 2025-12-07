/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HermitConcentrateAdder
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:HermitConcentrateAdder";
/*    */   
/*    */   public HermitConcentrateAdder(AbstractCreature owner) {
/* 21 */     this.name = NAME;
/* 22 */     this.ID = "downfall:HermitConcentrateAdder";
/* 23 */     this.owner = owner;
/* 24 */     this.amount = 0;
/* 25 */     updateDescription();
/* 26 */     loadRegion("curiosity");
/* 27 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 31 */     this.description = DESC[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 36 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "downfall:HermitConcentrationPower"));
/* 37 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new HermitConcentrationPower(this.owner), 10));
/*    */   }
/*    */ 
/*    */   
/* 41 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:HermitConcentrateAdder");
/* 42 */   public static final String NAME = powerStrings.NAME;
/* 43 */   public static final String[] DESC = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\HermitConcentrateAdder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */