/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.FusionHammer;
/*    */ 
/*    */ public class CBR_FusionHammer extends AbstractCharbossRelic {
/*    */   public static final String ID = "FusionHammer";
/*    */   
/*    */   public CBR_FusionHammer() {
/* 13 */     super((AbstractRelic)new FusionHammer());
/*    */   }
/*    */   private int numCards;
/*    */   
/*    */   public void onEquip() {
/* 18 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 19 */     ((EnergyManager)enemyEnergyManager).energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new CBR_FusionHammer();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_FusionHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */