/*    */ package charbosses.powers.cardpowers;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyMasterRealityPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "MasterRealityPower";
/*    */   
/*    */   public EnemyMasterRealityPower(AbstractCreature owner) {
/* 17 */     this.name = powerStrings.NAME;
/* 18 */     this.ID = "MasterRealityPower";
/* 19 */     this.owner = owner;
/* 20 */     updateDescription();
/* 21 */     loadRegion("master_reality");
/* 22 */     this.type = AbstractPower.PowerType.BUFF;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 26 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/* 30 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("MasterRealityPower");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyMasterRealityPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */