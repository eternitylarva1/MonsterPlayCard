/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.FocusPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefectBiasCuriosityPower
/*    */   extends AbstractBossMechanicPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:DefectBiasCuriosity";
/* 19 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:DefectBiasCuriosity");
/* 20 */   public static final String NAME = powerStrings.NAME;
/* 21 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private boolean triggered = false;
/*    */   
/*    */   public DefectBiasCuriosityPower(AbstractCreature owner) {
/* 25 */     this.name = NAME;
/* 26 */     this.ID = "downfall:DefectBiasCuriosity";
/* 27 */     this.owner = owner;
/* 28 */     this.amount = 1;
/* 29 */     updateDescription();
/* 30 */     loadRegion("bias");
/* 31 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 35 */     if (!this.triggered) {
/* 36 */       this.name = DESCRIPTIONS[3];
/* 37 */       this.description = DESCRIPTIONS[1];
/*    */     } else {
/*    */       
/* 40 */       this.name = NAME;
/* 41 */       this.description = DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */   
/*    */   public void atEndOfRound() {
/* 46 */     super.atEndOfRound();
/* 47 */     this.triggered = false;
/* 48 */     updateDescription();
/*    */   }
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 51 */     if (!this.triggered)
/* 52 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new FocusPower(this.owner, -this.amount), -this.amount)); 
/*    */   }
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 55 */     if (card.type == AbstractCard.CardType.POWER && !(card instanceof charbosses.cards.AbstractBossCard)) {
/* 56 */       flash();
/* 57 */       this.triggered = true;
/* 58 */       updateDescription();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\DefectBiasCuriosityPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */