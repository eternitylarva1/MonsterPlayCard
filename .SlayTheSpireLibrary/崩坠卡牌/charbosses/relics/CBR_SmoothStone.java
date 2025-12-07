/*    */ package charbosses.relics;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.OddlySmoothStone;
/*    */ 
/*    */ public class CBR_SmoothStone extends AbstractCharbossRelic {
/*    */   public static final String ID = "Oddly Smooth Stone";
/*    */   
/*    */   public CBR_SmoothStone() {
/* 15 */     super((AbstractRelic)new OddlySmoothStone());
/*    */   }
/*    */   private static final int CON_AMT = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 25 */     flash();
/* 26 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new DexterityPower((AbstractCreature)AbstractCharBoss.boss, 1), 1));
/* 27 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new CBR_SmoothStone();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_SmoothStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */