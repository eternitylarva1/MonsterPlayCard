/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.NlothsMask;
/*    */ 
/*    */ public class CBR_MaskNloth
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_MaskNloth() {
/*  9 */     super((AbstractRelic)new NlothsMask());
/*    */   }
/*    */   public static final String ID = "CBRNlothsMask";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 19 */     return new CBR_MaskNloth();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MaskNloth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */