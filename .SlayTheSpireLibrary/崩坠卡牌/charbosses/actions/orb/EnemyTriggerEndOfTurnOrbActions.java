/*    */ package charbosses.actions.orb;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyTriggerEndOfTurnOrbActions
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 15 */     if (!AbstractCharBoss.boss.orbs.isEmpty()) {
/* 16 */       for (AbstractOrb o : AbstractCharBoss.boss.orbs) {
/* 17 */         o.onEndOfTurn();
/*    */       }
/* 19 */       if (AbstractCharBoss.boss.hasRelic("Cables") && !(AbstractCharBoss.boss.orbs.get(0) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot)) {
/* 20 */         ((AbstractOrb)AbstractCharBoss.boss.orbs.get(0)).onEndOfTurn();
/*    */       }
/*    */     } 
/*    */     
/* 24 */     for (AbstractRelic r : AbstractCharBoss.boss.relics) {
/* 25 */       r.onPlayerEndTurn();
/* 26 */       r.onEnergyRecharge();
/*    */     } 
/*    */     
/* 29 */     for (AbstractPower p : AbstractCharBoss.boss.powers) {
/* 30 */       if (!AbstractCharBoss.boss.isPlayer) {
/* 31 */         p.atEndOfTurnPreEndTurnCards(false);
/*    */       }
/* 33 */       p.atEndOfTurn(AbstractCharBoss.boss.isPlayer);
/*    */     } 
/*    */ 
/*    */     
/* 37 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyTriggerEndOfTurnOrbActions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */