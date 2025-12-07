/*    */ package charbosses.actions.orb;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ 
/*    */ public class EnemyChannelAction
/*    */   extends AbstractGameAction {
/*    */   private AbstractOrb orbType;
/*    */   private boolean autoEvoke;
/*    */   
/*    */   public EnemyChannelAction(AbstractOrb newOrbType) {
/* 14 */     this(newOrbType, true);
/*    */   }
/*    */   
/*    */   public EnemyChannelAction(AbstractOrb newOrbType, boolean autoEvoke) {
/* 18 */     this.autoEvoke = false;
/* 19 */     this.duration = Settings.ACTION_DUR_FAST;
/* 20 */     this.orbType = newOrbType;
/* 21 */     this.autoEvoke = autoEvoke;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 26 */     if (AbstractCharBoss.boss != null && 
/* 27 */       this.duration == Settings.ACTION_DUR_FAST) {
/* 28 */       if (this.autoEvoke) {
/* 29 */         AbstractCharBoss.boss.channelOrb(this.orbType);
/*    */       } else {
/* 31 */         for (AbstractOrb o : AbstractCharBoss.boss.orbs) {
/* 32 */           if (o instanceof com.megacrit.cardcrawl.orbs.EmptyOrbSlot) {
/* 33 */             AbstractCharBoss.boss.channelOrb(this.orbType);
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/* 38 */       if (Settings.FAST_MODE) {
/* 39 */         this.isDone = true;
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/* 44 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\orb\EnemyChannelAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */