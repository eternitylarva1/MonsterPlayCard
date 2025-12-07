/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ 
/*    */ public class EnemyDamagePerAttackPlayedAction extends AbstractGameAction {
/*    */   private DamageInfo info;
/*    */   
/*    */   public EnemyDamagePerAttackPlayedAction(AbstractCreature target, DamageInfo info, AbstractGameAction.AttackEffect effect) {
/* 13 */     setValues(target, this.info = info);
/* 14 */     this.actionType = AbstractGameAction.ActionType.DAMAGE;
/* 15 */     this.attackEffect = effect;
/*    */   }
/*    */   
/*    */   public EnemyDamagePerAttackPlayedAction(AbstractCreature target, DamageInfo info) {
/* 19 */     this(target, info, AbstractGameAction.AttackEffect.NONE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     this.isDone = true;
/* 25 */     if (this.target != null && this.target.currentHealth > 0) {
/* 26 */       int count = AbstractCharBoss.boss.attacksPlayedThisTurn;
/* 27 */       count--;
/* 28 */       for (int i = 0; i < count; i++)
/* 29 */         addToTop((AbstractGameAction)new DamageAction(this.target, this.info, this.attackEffect)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyDamagePerAttackPlayedAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */