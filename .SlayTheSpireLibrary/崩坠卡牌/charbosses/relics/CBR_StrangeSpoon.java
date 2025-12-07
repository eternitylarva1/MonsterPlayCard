/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.StrangeSpoon;
/*    */ 
/*    */ public class CBR_StrangeSpoon
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "StrangeSpoon";
/*    */   
/*    */   public CBR_StrangeSpoon() {
/* 11 */     super((AbstractRelic)new StrangeSpoon());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new CBR_StrangeSpoon();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_StrangeSpoon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */