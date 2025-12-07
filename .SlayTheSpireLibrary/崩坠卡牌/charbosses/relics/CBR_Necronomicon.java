/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Necronomicon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_Necronomicon
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Necronomicon";
/*    */   
/*    */   public CBR_Necronomicon() {
/* 14 */     super((AbstractRelic)new Necronomicon());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\002' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 24 */     return new CBR_Necronomicon();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Necronomicon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */