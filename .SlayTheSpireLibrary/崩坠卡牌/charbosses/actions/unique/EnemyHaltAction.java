/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnemyHaltAction extends AbstractGameAction {
/*    */   private AbstractMonster m;
/*    */   private int additionalAmt;
/*    */   
/*    */   public EnemyHaltAction(AbstractMonster monster, int block, int magicNumber) {
/* 14 */     this.m = monster;
/* 15 */     this.amount = block;
/* 16 */     this.additionalAmt = magicNumber;
/*    */   }
/*    */   
/*    */   public void update() {
/* 20 */     if (AbstractCharBoss.boss.stance instanceof charbosses.stances.EnWrathStance) {
/* 21 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)this.m, (AbstractCreature)this.m, this.amount + this.additionalAmt));
/*    */     } else {
/* 23 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)this.m, (AbstractCreature)this.m, this.amount));
/*    */     } 
/* 25 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyHaltAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */