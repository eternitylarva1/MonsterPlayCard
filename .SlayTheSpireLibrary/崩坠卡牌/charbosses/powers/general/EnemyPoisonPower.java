/*    */ package charbosses.powers.general;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
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
/*    */ public class EnemyPoisonPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Poison";
/*    */   
/*    */   public EnemyPoisonPower(AbstractCreature owner, AbstractCreature source, int poisonAmt) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "Poison";
/* 24 */     this.owner = owner;
/* 25 */     this.source = source;
/* 26 */     this.amount = poisonAmt;
/* 27 */     if (AbstractCharBoss.boss.hasRelic("Snake Skull")) {
/* 28 */       this.amount++;
/*    */     }
/* 30 */     if (this.amount >= 9999) {
/* 31 */       this.amount = 9999;
/*    */     }
/*    */     
/* 34 */     updateDescription();
/* 35 */     loadRegion("poison");
/* 36 */     this.type = AbstractPower.PowerType.DEBUFF;
/* 37 */     this.isTurnBased = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 42 */     this.amount += stackAmount;
/*    */     
/* 44 */     if (stackAmount >= 0 && AbstractCharBoss.boss.hasRelic("Snake Skull")) {
/* 45 */       this.amount++;
/*    */     }
/*    */   }
/*    */   
/*    */   public void playApplyPowerSfx() {
/* 50 */     CardCrawlGame.sound.play("POWER_POISON", 0.05F);
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 54 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 70 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Poison");
/* 71 */   public static final String NAME = powerStrings.NAME;
/* 72 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   private AbstractCreature source;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\general\EnemyPoisonPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */