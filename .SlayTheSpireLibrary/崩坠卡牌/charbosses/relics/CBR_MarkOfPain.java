/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.MarkOfPain;
/*    */ 
/*    */ public class CBR_MarkOfPain
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_MarkOfPain() {
/* 14 */     super((AbstractRelic)new MarkOfPain());
/*    */   }
/*    */   public static final String ID = "MarkOfPain";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     if (AbstractCharBoss.boss != null) {
/* 20 */       return setDescription(AbstractCharBoss.boss.chosenClass);
/*    */     }
/* 22 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 26 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 31 */     this.description = setDescription(c);
/* 32 */     this.tips.clear();
/* 33 */     this.tips.add(new PowerTip(this.name, this.description));
/* 34 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 39 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 40 */     ((EnergyManager)enemyEnergyManager).energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 45 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 46 */     ((EnergyManager)enemyEnergyManager).energyMaster--;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 52 */     return new CBR_MarkOfPain();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MarkOfPain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */