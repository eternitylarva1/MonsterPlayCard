/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.stances.AbstractEnemyStance;
/*    */ import charbosses.stances.EnRealWrathStance;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ public class EnemyWrathNextTurnPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:WrathNextTurnPower";
/*    */   public static final String NAME;
/*    */   public static final String[] DESCRIPTIONS;
/* 20 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:WrathNextTurnPower"); static {
/* 21 */     NAME = powerStrings.NAME;
/* 22 */     DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   }
/*    */   
/*    */   public EnemyWrathNextTurnPower(AbstractCreature owner) {
/* 26 */     this.name = NAME;
/* 27 */     this.ID = "downfall:WrathNextTurnPower";
/* 28 */     this.owner = owner;
/* 29 */     updateDescription();
/* 30 */     loadRegion("anger");
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurn() {
/* 35 */     flash();
/* 36 */     addToBot((AbstractGameAction)new EnemyChangeStanceAction("Real Wrath"));
/* 37 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 41 */     this.description = powerStrings.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public AbstractEnemyStance getWrathStance() {
/* 45 */     return (AbstractEnemyStance)new EnRealWrathStance();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyWrathNextTurnPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */