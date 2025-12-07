/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.SmilingMask;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_SmilingMask
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Smiling Mask";
/*    */   
/*    */   public CBR_SmilingMask() {
/* 17 */     super((AbstractRelic)new SmilingMask());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0] + '2' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return new CBR_SmilingMask();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_SmilingMask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */