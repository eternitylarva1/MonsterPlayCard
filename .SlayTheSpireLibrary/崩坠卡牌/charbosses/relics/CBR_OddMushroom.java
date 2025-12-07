/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.OddMushroom;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_OddMushroom
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "OddMushroom";
/*    */   
/*    */   public CBR_OddMushroom() {
/* 15 */     super((AbstractRelic)new OddMushroom());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 25 */     return new CBR_OddMushroom();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_OddMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */