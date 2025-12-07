/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
/*    */ import charbosses.cards.purple.EnSmite;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class EnemyBattleHymnPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "BattleHymn";
/*    */   
/*    */   public EnemyBattleHymnPower(AbstractCreature owner, int amt) {
/* 18 */     this.name = powerStrings.NAME;
/* 19 */     this.ID = "BattleHymn";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amt;
/* 22 */     updateDescription();
/* 23 */     loadRegion("hymn");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 29 */     addToBot((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)new EnSmite(), this.amount, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 34 */     this.fontScale = 8.0F;
/* 35 */     this.amount += stackAmount;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 39 */     if (this.amount > 1) {
/* 40 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */     } else {
/* 42 */       this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 48 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("BattleHymn");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyBattleHymnPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */