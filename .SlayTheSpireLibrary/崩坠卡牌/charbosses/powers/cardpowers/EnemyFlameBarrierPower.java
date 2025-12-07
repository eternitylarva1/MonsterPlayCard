/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.FlameBarrierPower;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class EnemyFlameBarrierPower extends AbstractPower {
/* 16 */   private static final Logger logger = LogManager.getLogger(FlameBarrierPower.class.getName());
/*    */ 
/*    */   
/*    */   public static final String POWER_ID = "Flame Barrier";
/*    */ 
/*    */   
/*    */   public EnemyFlameBarrierPower(AbstractCreature owner, int thornsDamage) {
/* 23 */     this.name = NAME;
/* 24 */     this.ID = "Flame Barrier";
/* 25 */     this.owner = owner;
/* 26 */     this.amount = thornsDamage;
/* 27 */     updateDescription();
/* 28 */     loadRegion("flameBarrier");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 32 */     if (this.amount == -1) {
/* 33 */       logger.info(this.name + " does not stack");
/*    */     } else {
/* 35 */       this.fontScale = 8.0F;
/* 36 */       this.amount += stackAmount;
/* 37 */       updateDescription();
/*    */     } 
/*    */   }
/*    */   
/*    */   public int onAttacked(DamageInfo info, int damageAmount) {
/* 42 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
/* 43 */       flash();
/* 44 */       addToTop((AbstractGameAction)new DamageAction(info.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*    */     } 
/*    */     
/* 47 */     return damageAmount;
/*    */   }
/*    */   
/*    */   public void atStartOfTurn() {
/* 51 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Flame Barrier"));
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 55 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/* 59 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Flame Barrier");
/* 60 */   public static final String NAME = powerStrings.NAME;
/* 61 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyFlameBarrierPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */