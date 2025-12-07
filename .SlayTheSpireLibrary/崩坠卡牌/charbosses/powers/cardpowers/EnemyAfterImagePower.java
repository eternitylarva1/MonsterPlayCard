/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ public class EnemyAfterImagePower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "downfall:Enemy After Image";
/* 19 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("After Image");
/*    */ 
/*    */   
/*    */   public EnemyAfterImagePower(AbstractCreature owner, int amount) {
/* 23 */     this.name = powerStrings.NAME;
/* 24 */     this.ID = "downfall:Enemy After Image";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = amount;
/* 27 */     updateDescription();
/* 28 */     loadRegion("afterImage");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 33 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 38 */     if (!(card instanceof charbosses.cards.AbstractBossCard)) {
/*    */       return;
/*    */     }
/* 41 */     if (Settings.FAST_MODE) {
/* 42 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, this.amount, true));
/*    */     } else {
/* 44 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, this.amount));
/*    */     } 
/* 46 */     flash();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyAfterImagePower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */