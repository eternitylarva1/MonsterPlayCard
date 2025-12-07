/*    */ package charbosses.powers.general;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class EnemyMarkPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "PathToVictoryPower";
/*    */   
/*    */   public EnemyMarkPower(AbstractCreature owner, AbstractCreature source, int amt) {
/* 13 */     this.name = powerStrings.NAME;
/* 14 */     this.ID = "PathToVictoryPower";
/* 15 */     this.owner = owner;
/* 16 */     this.amount = amt;
/* 17 */     this.source = source;
/* 18 */     this.type = AbstractPower.PowerType.DEBUFF;
/* 19 */     updateDescription();
/* 20 */     loadRegion("pressure_points");
/*    */   }
/*    */   public void updateDescription() {
/* 23 */     this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/* 27 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("PathToVictoryPower");
/*    */   private AbstractCreature source;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\general\EnemyMarkPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */