/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.PaperCrane;
/*    */ 
/*    */ public class CBR_PaperKrane
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "Paper Crane";
/*    */   
/*    */   public CBR_PaperKrane() {
/* 11 */     super((AbstractRelic)new PaperCrane());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 21 */     return new CBR_PaperKrane();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_PaperKrane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */