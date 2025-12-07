/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyDarkImpulseAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_FAST && !AbstractCharBoss.boss.orbs.isEmpty()) {
/* 21 */       Iterator<AbstractOrb> var1 = AbstractCharBoss.boss.orbs.iterator();
/*    */       
/* 23 */       while (var1.hasNext()) {
/* 24 */         AbstractOrb o = var1.next();
/* 25 */         if (o instanceof charbosses.orbs.EnemyDark) {
/* 26 */           o.onStartOfTurn();
/* 27 */           o.onEndOfTurn();
/*    */         } 
/*    */       } 
/*    */       
/* 31 */       if (AbstractCharBoss.boss.hasRelic("Cables") && !(AbstractCharBoss.boss.orbs.get(0) instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot) && AbstractCharBoss.boss.orbs.get(0) instanceof charbosses.orbs.EnemyDark) {
/* 32 */         ((AbstractOrb)AbstractCharBoss.boss.orbs.get(0)).onStartOfTurn();
/* 33 */         ((AbstractOrb)AbstractCharBoss.boss.orbs.get(0)).onEndOfTurn();
/*    */       } 
/*    */     } 
/*    */     
/* 37 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyDarkImpulseAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */