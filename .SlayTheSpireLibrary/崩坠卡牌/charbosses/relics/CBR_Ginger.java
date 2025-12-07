/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Ginger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_Ginger
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Ginger";
/*    */   
/*    */   public CBR_Ginger() {
/* 19 */     super((AbstractRelic)new Ginger());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new CBR_Ginger();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Ginger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */