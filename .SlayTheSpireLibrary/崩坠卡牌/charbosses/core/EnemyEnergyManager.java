/*    */ package charbosses.core;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.ui.EnemyEnergyPanel;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.EnergyManager;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class EnemyEnergyManager
/*    */   extends EnergyManager {
/*    */   public EnemyEnergyManager(int e) {
/* 15 */     super(e);
/*    */   }
/*    */   
/*    */   public void prep() {
/* 19 */     this.energy = this.energyMaster;
/* 20 */     EnemyEnergyPanel.totalCount = 0;
/*    */   }
/*    */   
/*    */   public void recharge() {
/* 24 */     if (AbstractCharBoss.boss != null) {
/* 25 */       if (AbstractCharBoss.boss.hasRelic("Art of War")) {
/* 26 */         AbstractCharBoss.boss.getRelic("Art of War").onTrigger();
/*    */       }
/* 28 */       if (AbstractCharBoss.boss.hasRelic("Ice Cream")) {
/* 29 */         if (EnemyEnergyPanel.totalCount > 0) {
/* 30 */           AbstractCharBoss.boss.getRelic("Ice Cream").flash();
/* 31 */           AbstractDungeon.actionManager.addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, AbstractCharBoss.boss.getRelic("Ice Cream")));
/*    */         } 
/* 33 */         EnemyEnergyPanel.addEnergy(this.energy);
/* 34 */       } else if (AbstractCharBoss.boss.hasPower("Conserve")) {
/* 35 */         if (EnemyEnergyPanel.totalCount > 0) {
/* 36 */           AbstractDungeon.actionManager.addToTop((AbstractGameAction)new ReducePowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, "Conserve", 1));
/*    */         }
/* 38 */         EnemyEnergyPanel.addEnergy(this.energy);
/*    */       } else {
/* 40 */         EnemyEnergyPanel.setEnergy(this.energy);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(int e) {
/* 48 */     EnemyEnergyPanel.useEnergy(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\core\EnemyEnergyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */