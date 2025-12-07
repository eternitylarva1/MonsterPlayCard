/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.powers.general.EnemyPoisonPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.TwistedFunnel;
/*    */ 
/*    */ public class CBR_TwistedFunnel
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "TwistedFunnel";
/*    */   
/*    */   public CBR_TwistedFunnel() {
/* 19 */     super((AbstractRelic)new TwistedFunnel());
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 24 */     flash();
/* 25 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new EnemyPoisonPower((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractCharBoss.boss, 4), 4));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 32 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 37 */     return new CBR_TwistedFunnel();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_TwistedFunnel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */