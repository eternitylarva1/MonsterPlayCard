/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CoffeeDripper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_CoffeeDripper
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Coffee Dripper";
/*    */   
/*    */   public CBR_CoffeeDripper() {
/* 18 */     super((AbstractRelic)new CoffeeDripper());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 27 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 28 */     ((EnergyManager)enemyEnergyManager).energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 33 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 34 */     ((EnergyManager)enemyEnergyManager).energyMaster--;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 39 */     return new CBR_CoffeeDripper();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_CoffeeDripper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */