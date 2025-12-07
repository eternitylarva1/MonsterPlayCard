/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Sozu;
/*    */ 
/*    */ public class CBR_Sozu extends AbstractCharbossRelic {
/*    */   public static final String ID = "Sozu";
/*    */   
/*    */   public CBR_Sozu() {
/* 13 */     super((AbstractRelic)new Sozu());
/*    */   }
/*    */ 
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
/* 29 */     return new CBR_Sozu();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Sozu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */