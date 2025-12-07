/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ 
/*    */ public class EnemyLimitBreakAction
/*    */   extends AbstractGameAction
/*    */ {
/* 14 */   private AbstractCharBoss p = AbstractCharBoss.boss;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == Settings.ACTION_DUR_XFAST && this.p.hasPower("Strength")) {
/* 21 */       int strAmt = (this.p.getPower("Strength")).amount;
/* 22 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.p, (AbstractCreature)this.p, (AbstractPower)new StrengthPower((AbstractCreature)this.p, strAmt), strAmt));
/*    */     } 
/* 24 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyLimitBreakAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */