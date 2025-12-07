/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Turnip;
/*    */ 
/*    */ public class CBR_Turnip
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "Turnip";
/*    */   
/*    */   public CBR_Turnip() {
/* 11 */     super((AbstractRelic)new Turnip());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 21 */     return new CBR_Turnip();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Turnip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */