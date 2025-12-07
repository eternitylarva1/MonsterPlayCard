/*    */ package charbosses.relics;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.core.EnemyEnergyManager;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.PhilosopherStone;
/*    */ 
/*    */ public class CBR_PhilosopherStone extends AbstractCharbossRelic {
/*    */   public CBR_PhilosopherStone() {
/* 17 */     super((AbstractRelic)new PhilosopherStone());
/*    */   }
/*    */   public static final String ID = "PhilosopherStone";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 27 */     this.description = getUpdatedDescription();
/* 28 */     this.tips.clear();
/* 29 */     this.tips.add(new PowerTip(this.name, this.description));
/* 30 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 35 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/* 36 */     AbstractDungeon.player.addPower((AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, 1));
/* 37 */     AbstractDungeon.onModifyPower();
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
/* 54 */     return new CBR_PhilosopherStone();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_PhilosopherStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */