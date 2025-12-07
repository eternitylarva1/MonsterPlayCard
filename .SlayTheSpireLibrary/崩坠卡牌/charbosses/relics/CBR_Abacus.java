/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.Abacus;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ public class CBR_Abacus extends AbstractCharbossRelic {
/*    */   public static final String ID = "Abacus";
/*    */   
/*    */   public CBR_Abacus() {
/* 14 */     super((AbstractRelic)new Abacus());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\006' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onShuffle() {
/* 23 */     flash();
/* 24 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 25 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, 6));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 30 */     return new CBR_Abacus();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Abacus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */