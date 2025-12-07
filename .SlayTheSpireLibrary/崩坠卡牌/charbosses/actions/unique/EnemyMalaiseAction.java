/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.ui.EnemyEnergyPanel;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class EnemyMalaiseAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private boolean freeToPlayOnce = false;
/*    */   private boolean upgraded = false;
/*    */   private AbstractCharBoss c;
/* 20 */   private int energyOnUse = -1;
/*    */   
/*    */   public EnemyMalaiseAction(AbstractCharBoss t, boolean upgraded, boolean freeToPlayOnce, int energyOnUse) {
/* 23 */     this.c = t;
/* 24 */     this.freeToPlayOnce = freeToPlayOnce;
/* 25 */     this.upgraded = upgraded;
/* 26 */     this.duration = Settings.ACTION_DUR_XFAST;
/* 27 */     this.actionType = AbstractGameAction.ActionType.SPECIAL;
/* 28 */     this.energyOnUse = energyOnUse;
/*    */   }
/*    */   
/*    */   public void update() {
/* 32 */     int effect = EnemyEnergyPanel.totalCount;
/* 33 */     if (this.energyOnUse != -1) {
/* 34 */       effect = this.energyOnUse;
/*    */     }
/*    */     
/* 37 */     if (this.upgraded) {
/* 38 */       effect++;
/*    */     }
/*    */     
/* 41 */     if (effect > 0) {
/* 42 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this.c, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, -effect), -effect));
/* 43 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this.c, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, effect, false), effect));
/* 44 */       if (!this.freeToPlayOnce) {
/* 45 */         this.c.energy.use(EnemyEnergyPanel.totalCount);
/*    */       }
/*    */     } 
/*    */     
/* 49 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyMalaiseAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */