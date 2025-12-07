/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.ChemicalX;
/*    */ 
/*    */ public class CBR_ChemicalX extends AbstractCharbossRelic {
/*    */   public static final String ID = "Chemical X";
/*    */   
/*    */   public CBR_ChemicalX() {
/* 10 */     super((AbstractRelic)new ChemicalX());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new CBR_ChemicalX();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_ChemicalX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */