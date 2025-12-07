/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.IceCream;
/*    */ 
/*    */ public class CBR_IceCream
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_IceCream() {
/*  9 */     super((AbstractRelic)new IceCream());
/*    */   }
/*    */   public static final String ID = "IceCream";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 19 */     return new CBR_IceCream();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_IceCream.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */