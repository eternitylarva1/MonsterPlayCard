/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.BustedCrown;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_BustedCrown extends AbstractCharbossRelic {
/*    */   public CBR_BustedCrown() {
/* 18 */     super((AbstractRelic)new BustedCrown());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     if (AbstractCharBoss.boss != null) {
/* 24 */       return setDescription(AbstractCharBoss.boss.chosenClass);
/*    */     }
/* 26 */     return setDescription((AbstractPlayer.PlayerClass)null);
/*    */   }
/*    */   public static final String ID = "BustedCrown";
/*    */   private String setDescription(AbstractPlayer.PlayerClass c) {
/* 30 */     return this.DESCRIPTIONS[1] + this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("BustedCrown"))).DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 35 */     this.description = setDescription(c);
/* 36 */     this.tips.clear();
/* 37 */     this.tips.add(new PowerTip(this.name, this.description));
/* 38 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 43 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 44 */     ((EnergyManager)enemyEnergyManager).energyMaster++;
/* 45 */     this.owner.damage(new DamageInfo((AbstractCreature)this.owner, MathUtils.floor(this.owner.maxHealth * 0.15F), DamageInfo.DamageType.HP_LOSS));
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 50 */     EnemyEnergyManager enemyEnergyManager = AbstractCharBoss.boss.energy;
/* 51 */     ((EnergyManager)enemyEnergyManager).energyMaster--;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 57 */     return new CBR_BustedCrown();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_BustedCrown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */