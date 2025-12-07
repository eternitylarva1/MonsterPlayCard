/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.OddlySmoothStone;
/*    */ 
/*    */ public class CBR_OddlySmoothStone extends AbstractCharbossRelic {
/*    */   public static final String ID = "OddlySmoothStone";
/*    */   
/*    */   public CBR_OddlySmoothStone() {
/* 17 */     super((AbstractRelic)new OddlySmoothStone());
/*    */   }
/*    */   private static final int DEX = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 27 */     flash();
/* 28 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new DexterityPower((AbstractCreature)AbstractCharBoss.boss, 1), 1));
/* 29 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 34 */     return new CBR_OddlySmoothStone();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_OddlySmoothStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */