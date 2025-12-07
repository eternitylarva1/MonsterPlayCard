/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Calipers;
/*    */ 
/*    */ public class CBR_Calipers extends AbstractCharbossRelic {
/*    */   public static final String ID = "Calipers";
/*    */   
/*    */   public CBR_Calipers() {
/* 10 */     super((AbstractRelic)new Calipers());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0] + '\017' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new CBR_Calipers();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Calipers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */