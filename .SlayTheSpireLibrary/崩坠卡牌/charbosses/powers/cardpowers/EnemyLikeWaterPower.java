/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ public class EnemyLikeWaterPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "LikeWaterPower";
/*    */   
/*    */   public EnemyLikeWaterPower(AbstractCreature owner, int amt) {
/* 18 */     this.name = powerStrings.NAME;
/* 19 */     this.ID = "LikeWaterPower";
/* 20 */     this.owner = owner;
/* 21 */     this.amount = amt;
/* 22 */     updateDescription();
/* 23 */     loadRegion("like_water");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 27 */     this.fontScale = 8.0F;
/* 28 */     this.amount += stackAmount;
/* 29 */     if (this.amount > 999) {
/* 30 */       this.amount = 999;
/*    */     }
/*    */     
/* 33 */     updateDescription();
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 37 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
/* 41 */     AbstractCharBoss p = (AbstractCharBoss)this.owner;
/* 42 */     if (p.stance instanceof charbosses.stances.EnCalmStance) {
/* 43 */       flash();
/* 44 */       addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 50 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("LikeWaterPower");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyLikeWaterPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */