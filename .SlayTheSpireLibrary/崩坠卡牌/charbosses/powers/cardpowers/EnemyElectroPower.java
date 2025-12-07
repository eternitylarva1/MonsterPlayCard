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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyElectroPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "Electro";
/*    */   
/*    */   public EnemyElectroPower(AbstractCreature owner) {
/* 24 */     this.name = NAME;
/* 25 */     this.ID = "Electro";
/* 26 */     this.owner = owner;
/* 27 */     updateDescription();
/* 28 */     loadRegion("mastery");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 32 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Electro");
/* 38 */   public static final String NAME = powerStrings.NAME;
/* 39 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\cardpowers\EnemyElectroPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */