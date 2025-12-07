/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CursedKey;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_CursedKey extends AbstractCharbossRelic {
/*    */   public static final String ID = "CursedKey";
/*    */   private int numCurses;
/*    */   
/*    */   public CBR_CursedKey() {
/* 19 */     super((AbstractRelic)new CursedKey());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     if (AbstractCharBoss.boss != null) {
/* 25 */       return setDescription(AbstractCharBoss.boss.chosenClass);
/*    */     }
/* 27 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void justEnteredRoom(AbstractRoom room) {
/* 32 */     if (room instanceof com.megacrit.cardcrawl.rooms.TreasureRoom) {
/* 33 */       flash();
/* 34 */       this.pulse = true;
/*    */     } else {
/* 36 */       this.pulse = false;
/*    */     } 
/*    */   }
/*    */   
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 41 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("CursedKey"))).DESCRIPTIONS[0] + this.numCurses + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("CursedKey"))).DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 46 */     this.description = setDescription(c);
/* 47 */     this.tips.clear();
/* 48 */     this.tips.add(new PowerTip(this.name, this.description));
/* 49 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 54 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 55 */     ((EnergyManager)enemyEnergyManager).energyMaster++;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 61 */     return new CBR_CursedKey();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_CursedKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */