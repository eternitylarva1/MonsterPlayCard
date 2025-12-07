/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Circlet;
/*    */ 
/*    */ public class CBR_Circlet extends AbstractCharbossRelic {
/*    */   public static final String ID = "Circlet";
/*    */   
/*    */   public CBR_Circlet() {
/* 10 */     super((AbstractRelic)new Circlet());
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 15 */     return new CBR_Circlet();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Circlet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */