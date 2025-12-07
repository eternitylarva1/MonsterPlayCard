/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.stances.AbstractStance;
/*    */ import java.util.Optional;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyMentalFortressPower
/*    */   extends AbstractPower
/*    */ {
/* 23 */   public static final Logger logger = LogManager.getLogger(EnemyMentalFortressPower.class.getName());
/*    */ 
/*    */   
/*    */   public static final String POWER_ID = "downfall_Charboss:MentalFortressPower";
/*    */ 
/*    */ 
/*    */   
/*    */   public EnemyMentalFortressPower(AbstractCreature owner, int amount) {
/* 31 */     this.name = NAME;
/* 32 */     this.ID = "Mental Fortress";
/* 33 */     this.owner = owner;
/* 34 */     this.amount = amount;
/* 35 */     updateDescription();
/* 36 */     loadRegion("mental_fortress");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 40 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
/* 44 */     logger.info(toString());
/* 45 */     logger.info(oldStance + " " + newStance);
/* 46 */     logger.info(oldStance.ID + " " + newStance.ID);
/* 47 */     logger.info(oldStance.name + " " + newStance.name);
/* 48 */     String oldStanceId = Optional.<AbstractStance>ofNullable(oldStance).map(a -> a.ID).orElse("");
/* 49 */     String newStanceId = Optional.<AbstractStance>ofNullable(newStance).map(a -> a.ID).orElse("");
/* 50 */     logger.info(oldStanceId + " " + newStanceId);
/* 51 */     if (!oldStanceId.equals(newStanceId)) {
/* 52 */       flash();
/* 53 */       addToBot((AbstractGameAction)new GainBlockAction(this.owner, this.owner, this.amount));
/* 54 */       logger.info("gained block");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 60 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Controlled");
/* 61 */   public static final String NAME = powerStrings.NAME;
/* 62 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyMentalFortressPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */