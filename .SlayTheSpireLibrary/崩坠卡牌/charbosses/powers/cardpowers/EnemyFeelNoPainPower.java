/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyFeelNoPainPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Feel No Pain";
/*    */   
/*    */   public EnemyFeelNoPainPower(AbstractCreature owner, int amount) {
/* 24 */     this.name = NAME;
/* 25 */     this.ID = "Feel No Pain";
/* 26 */     this.owner = owner;
/* 27 */     this.amount = amount;
/* 28 */     updateDescription();
/* 29 */     loadRegion("noPain");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 33 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void onExhaust(AbstractCard card) {
/* 37 */     if (card instanceof charbosses.cards.AbstractBossCard) {
/* 38 */       flash();
/* 39 */       addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.amount, Settings.FAST_MODE));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/* 44 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Feel No Pain");
/* 45 */   public static final String NAME = powerStrings.NAME;
/* 46 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyFeelNoPainPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */