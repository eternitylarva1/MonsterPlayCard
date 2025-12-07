/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.GoldenIdol;
/*    */ 
/*    */ public class CBR_GoldenIdol
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "GoldenIdol";
/*    */   
/*    */   public CBR_GoldenIdol() {
/* 11 */     super((AbstractRelic)new GoldenIdol());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new CBR_GoldenIdol();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_GoldenIdol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */