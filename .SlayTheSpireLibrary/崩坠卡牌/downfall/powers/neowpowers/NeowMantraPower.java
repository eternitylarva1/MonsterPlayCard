/*    */ package downfall.powers.neowpowers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ public class NeowMantraPower
/*    */   extends AbstractPower
/*    */ {
/* 16 */   private final int PRAYER_REQUIRED = 10;
/*    */   
/*    */   public NeowMantraPower(AbstractCreature owner, int amount) {
/* 19 */     this.name = powerStrings.NAME;
/* 20 */     this.ID = "downfall:NeowMantra";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = amount;
/* 23 */     updateDescription();
/* 24 */     loadRegion("mantra");
/* 25 */     this.type = AbstractPower.PowerType.BUFF;
/* 26 */     if (this.owner instanceof AbstractCharBoss) {
/* 27 */       AbstractCharBoss cB = (AbstractCharBoss)this.owner;
/* 28 */       cB.mantraGained += amount;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 33 */     CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 37 */     this.description = powerStrings.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 41 */     super.stackPower(stackAmount);
/* 42 */     if (this.amount >= 10) {
/* 43 */       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new NeowDivinityPower(this.owner), 1, true, AbstractGameAction.AttackEffect.NONE));
/*    */ 
/*    */       
/* 46 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 52 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:NeowMantra");
/*    */   public static final String POWER_ID = "downfall:NeowMantra";
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\powers\neowpowers\NeowMantraPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */