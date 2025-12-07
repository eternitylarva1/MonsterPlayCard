/*    */ package charbosses.actions.common;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ 
/*    */ public class EnemyGainEnergyAction extends AbstractGameAction {
/*    */   private int energyGain;
/*    */   
/*    */   public EnemyGainEnergyAction(int amount) {
/* 13 */     this(AbstractCharBoss.boss, amount);
/*    */   }
/*    */   private AbstractCharBoss boss;
/*    */   public EnemyGainEnergyAction(AbstractCharBoss target, int amount) {
/* 17 */     setValues((AbstractCreature)target, (AbstractCreature)target, 0);
/* 18 */     this.duration = Settings.ACTION_DUR_FAST;
/* 19 */     this.energyGain = amount;
/* 20 */     this.boss = target;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 25 */     if (this.duration == Settings.ACTION_DUR_FAST) {
/* 26 */       if (AbstractCharBoss.boss != null) {
/* 27 */         this.boss = AbstractCharBoss.boss;
/*    */         
/* 29 */         this.boss.gainEnergy(this.energyGain);
/* 30 */         for (AbstractCard c : this.boss.hand.group) {
/* 31 */           c.triggerOnGainEnergy(this.energyGain, true);
/*    */         }
/*    */       } else {
/*    */         
/* 35 */         this.isDone = true;
/*    */         return;
/*    */       } 
/*    */     }
/* 39 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyGainEnergyAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */