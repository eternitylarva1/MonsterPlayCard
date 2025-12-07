/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.VelvetChoker;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_VelvetChoker
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Velvet Choker";
/*    */   private int numCards;
/*    */   
/*    */   public CBR_VelvetChoker() {
/* 19 */     super((AbstractRelic)new VelvetChoker());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[2] + this.DESCRIPTIONS[0] + '\006' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 28 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 29 */     ((EnergyManager)enemyEnergyManager).energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 34 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 35 */     ((EnergyManager)enemyEnergyManager).energyMaster--;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 40 */     return new CBR_VelvetChoker();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_VelvetChoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */