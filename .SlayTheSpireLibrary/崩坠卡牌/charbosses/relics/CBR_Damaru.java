/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.powers.cardpowers.EnemyMantraPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Damaru;
/*    */ 
/*    */ public class CBR_Damaru
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Damaru";
/*    */   private int numCards;
/*    */   
/*    */   public CBR_Damaru() {
/* 20 */     super((AbstractRelic)new Damaru());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 30 */     flash();
/* 31 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/* 32 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new EnemyMantraPower((AbstractCreature)AbstractCharBoss.boss, 1), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 38 */     return new CBR_Damaru();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Damaru.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */