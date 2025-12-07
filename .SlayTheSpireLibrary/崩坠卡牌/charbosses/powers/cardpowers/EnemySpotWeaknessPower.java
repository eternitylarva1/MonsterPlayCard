/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.FlameBarrierPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemySpotWeaknessPower
/*    */   extends AbstractPower
/*    */ {
/* 24 */   private static final Logger logger = LogManager.getLogger(FlameBarrierPower.class.getName());
/*    */ 
/*    */ 
/*    */   
/*    */   public static final String POWER_ID = "Spot Weakness";
/*    */ 
/*    */ 
/*    */   
/*    */   public EnemySpotWeaknessPower(AbstractCreature owner, int strengthGain) {
/* 33 */     this.name = NAME;
/* 34 */     this.ID = "Spot Weakness";
/* 35 */     this.owner = owner;
/* 36 */     this.amount = 0;
/* 37 */     this.theoreticalGain = strengthGain;
/* 38 */     updateDescription();
/* 39 */     loadRegion("curiosity");
/* 40 */     this.isActive = false;
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 44 */     if (this.amount == -1) {
/* 45 */       logger.info(this.name + " does not stack");
/*    */     } else {
/* 47 */       this.fontScale = 8.0F;
/* 48 */       this.amount += stackAmount;
/* 49 */       updateDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 55 */     if (card instanceof charbosses.cards.AbstractBossCard) {
/*    */       return;
/*    */     }
/* 58 */     if (card.type.equals(AbstractCard.CardType.ATTACK) && !this.isActive) {
/* 59 */       flash();
/* 60 */       this.isActive = true;
/* 61 */       this.amount = this.theoreticalGain;
/* 62 */       updateDescription();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void atStartOfTurn() {
/* 67 */     if (this.isActive) {
/* 68 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new StrengthPower((AbstractCreature)AbstractCharBoss.boss, this.amount)));
/* 69 */       this.isActive = false;
/*    */     } 
/* 71 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Spot Weakness"));
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 75 */     this.description = DESCRIPTIONS[0] + this.theoreticalGain + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
/*    */   }
/*    */ 
/*    */   
/* 79 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("downfall:SpotWeakness");
/* 80 */   public static final String NAME = powerStrings.NAME;
/* 81 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   public boolean isActive;
/*    */   public int theoreticalGain;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemySpotWeaknessPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */