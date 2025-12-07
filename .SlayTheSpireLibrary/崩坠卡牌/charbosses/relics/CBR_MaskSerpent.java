/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.SsserpentHead;
/*    */ 
/*    */ public class CBR_MaskSerpent
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_MaskSerpent() {
/*  9 */     super((AbstractRelic)new SsserpentHead());
/*    */   }
/*    */   public static final String ID = "CBRSerpentHead";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 19 */     return new CBR_MaskSerpent();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MaskSerpent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */