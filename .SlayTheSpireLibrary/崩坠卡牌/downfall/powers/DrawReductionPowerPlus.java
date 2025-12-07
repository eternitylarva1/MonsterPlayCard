/*    */ package downfall.powers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DrawReductionPowerPlus
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Draw Reduction";
/* 17 */   public static final String NAME = (CardCrawlGame.languagePack.getPowerStrings("Draw Reduction")).NAME;
/* 18 */   public static final String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getPowerStrings("Draw Reduction")).DESCRIPTIONS;
/*    */ 
/*    */ 
/*    */   
/*    */   public DrawReductionPowerPlus(AbstractCreature owner, int amount) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "Draw Reduction";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = amount;
/* 27 */     updateDescription();
/* 28 */     loadRegion("lessdraw");
/* 29 */     this.type = AbstractPower.PowerType.DEBUFF;
/* 30 */     this.isTurnBased = true;
/*    */   }
/*    */   
/*    */   public void onInitialApplication() {
/* 34 */     AbstractDungeon.player.gameHandSize--;
/*    */   }
/*    */   
/*    */   public void atEndOfRound() {
/* 38 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, this.ID, 1));
/*    */   }
/*    */   
/*    */   public void onRemove() {
/* 42 */     AbstractDungeon.player.gameHandSize++;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 46 */     if (this.amount == 1) {
/* 47 */       this.description = DESCRIPTIONS[0];
/*    */     } else {
/* 49 */       this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\DrawReductionPowerPlus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */