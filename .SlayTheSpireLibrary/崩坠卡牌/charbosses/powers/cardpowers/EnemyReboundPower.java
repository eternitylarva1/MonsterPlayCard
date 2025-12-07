/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.common.EnemyUseCardAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
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
/*    */ public class EnemyReboundPower
/*    */   extends AbstractPower
/*    */ {
/*    */   private boolean justEvoked = true;
/*    */   
/*    */   public EnemyReboundPower(AbstractCreature owner) {
/* 26 */     this.name = NAME;
/* 27 */     this.ID = "Rebound";
/* 28 */     this.owner = owner;
/* 29 */     this.amount = 1;
/* 30 */     updateDescription();
/* 31 */     loadRegion("rebound");
/* 32 */     this.isTurnBased = true;
/* 33 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 37 */     if (this.amount > 1) {
/* 38 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } else {
/* 40 */       this.description = DESCRIPTIONS[0];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onAfterUse(AbstractCard card, EnemyUseCardAction action) {
/* 47 */     if (this.justEvoked) {
/* 48 */       this.justEvoked = false;
/*    */     } else {
/* 50 */       if (card.type != AbstractCard.CardType.POWER) {
/* 51 */         flash();
/*    */         
/* 53 */         action.reboundCard = true;
/*    */       } 
/*    */       
/* 56 */       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "Rebound", 1));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 61 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Rebound"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 66 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Rebound");
/* 67 */   public static final String NAME = powerStrings.NAME;
/* 68 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   public static final String POWER_ID = "Rebound";
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyReboundPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */