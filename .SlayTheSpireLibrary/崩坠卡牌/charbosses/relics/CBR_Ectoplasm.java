/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Ectoplasm;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_Ectoplasm extends AbstractCharbossRelic {
/*    */   public static final String ID = "Ectoplasm";
/*    */   
/*    */   public CBR_Ectoplasm() {
/* 17 */     super((AbstractRelic)new Ectoplasm());
/*    */   }
/*    */   private int numCards;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     if (AbstractCharBoss.boss != null) {
/* 23 */       return setDescription(AbstractCharBoss.boss.chosenClass);
/*    */     }
/* 25 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 29 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("Ectoplasm"))).DESCRIPTIONS[0] + this.numCards + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("Ectoplasm"))).DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 34 */     this.description = setDescription(c);
/* 35 */     this.tips.clear();
/* 36 */     this.tips.add(new PowerTip(this.name, this.description));
/* 37 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 42 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 43 */     ((EnergyManager)enemyEnergyManager).energyMaster++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 48 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 49 */     ((EnergyManager)enemyEnergyManager).energyMaster--;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 54 */     return new CBR_Ectoplasm();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Ectoplasm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */