/*    */ package downfall.actions;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import downfall.monsters.NeowBoss;
/*    */ 
/*    */ public class TriggerPseudoStartOfCombatAction extends AbstractGameAction {
/*    */   private AbstractCharBoss cB;
/*    */   
/*    */   public TriggerPseudoStartOfCombatAction(AbstractCharBoss cB) {
/* 12 */     this.cB = cB;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 19 */     if (NeowBoss.Rezzes == 1) {
/* 20 */       this.cB.energy.recharge();
/*    */     }
/* 22 */     for (AbstractCharbossRelic r : this.cB.relics) {
/* 23 */       r.atBattleStartPreDraw();
/*    */     }
/* 25 */     for (AbstractCharbossRelic r : this.cB.relics) {
/* 26 */       r.atBattleStart();
/*    */     }
/*    */     
/* 29 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\TriggerPseudoStartOfCombatAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */