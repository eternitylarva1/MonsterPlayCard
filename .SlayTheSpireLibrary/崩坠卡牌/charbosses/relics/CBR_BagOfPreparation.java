/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.BagOfPreparation;
/*    */ 
/*    */ public class CBR_BagOfPreparation extends AbstractCharbossRelic {
/*    */   public CBR_BagOfPreparation() {
/* 11 */     super((AbstractRelic)new BagOfPreparation());
/*    */   }
/*    */   public static final String ID = "BagOfPreparation";
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0] + '\002' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 20 */     flash();
/* 21 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return new CBR_BagOfPreparation();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_BagOfPreparation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */