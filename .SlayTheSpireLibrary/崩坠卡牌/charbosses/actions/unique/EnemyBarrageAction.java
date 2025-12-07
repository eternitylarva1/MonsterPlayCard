/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyBarrageAction
/*    */   extends AbstractGameAction
/*    */ {
/* 15 */   private DamageInfo info = null;
/*    */   private AbstractCreature target;
/*    */   
/*    */   public EnemyBarrageAction(AbstractCreature m, DamageInfo info) {
/* 19 */     this.info = info;
/* 20 */     this.target = m;
/*    */   }
/*    */   
/*    */   public void update() {
/* 24 */     for (int i = 0; i < AbstractCharBoss.boss.orbs.size(); i++) {
/* 25 */       if (!(AbstractCharBoss.boss.orbs.get(i) instanceof charbosses.orbs.EnemyEmptyOrbSlot)) {
/* 26 */         addToTop((AbstractGameAction)new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
/*    */       }
/*    */     } 
/*    */     
/* 30 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyBarrageAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */