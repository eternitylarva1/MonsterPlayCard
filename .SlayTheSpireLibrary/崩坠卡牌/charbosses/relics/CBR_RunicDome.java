/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.RunicDome;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_RunicDome
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_RunicDome() {
/* 14 */     super((AbstractRelic)new RunicDome());
/*    */   }
/*    */   public static final String ID = "RunicDome";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("RunicDome"))).DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 25 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 26 */     ((EnergyManager)enemyEnergyManager).energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 31 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 32 */     ((EnergyManager)enemyEnergyManager).energyMaster--;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 38 */     return new CBR_RunicDome();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_RunicDome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */