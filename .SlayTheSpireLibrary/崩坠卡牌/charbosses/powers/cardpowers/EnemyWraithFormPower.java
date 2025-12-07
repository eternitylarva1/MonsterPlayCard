/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*    */ 
/*    */ public class EnemyWraithFormPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "Wraith Form v2";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Wraith Form v2");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public EnemyWraithFormPower(AbstractCreature owner, int amount) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "Wraith Form v2";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amount;
/* 23 */     updateDescription();
/* 24 */     loadRegion("wraithForm");
/* 25 */     this.canGoNegative = true;
/* 26 */     this.type = AbstractPower.PowerType.DEBUFF;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 31 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new DexterityPower(this.owner, this.amount), this.amount));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 38 */     this.fontScale = 8.0F;
/* 39 */     this.amount += stackAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 44 */     this.description = DESCRIPTIONS[0] + -this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyWraithFormPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */