/*    */ package charbosses.relics;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Vajra;
/*    */ 
/*    */ public class CBR_Vajra extends AbstractCharbossRelic {
/*    */   public static final String ID = "Vajra";
/*    */   
/*    */   public CBR_Vajra() {
/* 15 */     super((AbstractRelic)new Vajra());
/*    */   }
/*    */   private static final int STR = 1;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 25 */     flash();
/* 26 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new StrengthPower((AbstractCreature)AbstractCharBoss.boss, 1), 1));
/* 27 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new CBR_Vajra();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Vajra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */