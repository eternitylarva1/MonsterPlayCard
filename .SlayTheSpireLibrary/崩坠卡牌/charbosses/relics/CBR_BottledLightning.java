/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.BottledLightning;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_BottledLightning
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Bottled Lightning";
/*    */   
/*    */   public CBR_BottledLightning() {
/* 18 */     super((AbstractRelic)new BottledLightning());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 28 */     return new CBR_BottledLightning();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_BottledLightning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */