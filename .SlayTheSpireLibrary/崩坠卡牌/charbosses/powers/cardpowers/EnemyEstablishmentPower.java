/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyEstablishmentPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class EnemyEstablishmentPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "EstablishmentPower";
/*    */   
/*    */   public EnemyEstablishmentPower(AbstractCreature owner, int strengthAmount) {
/* 15 */     this.name = powerStrings.NAME;
/* 16 */     this.ID = "EstablishmentPower";
/* 17 */     this.owner = owner;
/* 18 */     this.amount = strengthAmount;
/* 19 */     updateDescription();
/* 20 */     loadRegion("establishment");
/* 21 */     this.priority = 25;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 25 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 29 */     flash();
/* 30 */     addToBot((AbstractGameAction)new EnemyEstablishmentPowerAction(this.amount));
/*    */   }
/*    */ 
/*    */   
/* 34 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("EstablishmentPower");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyEstablishmentPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */