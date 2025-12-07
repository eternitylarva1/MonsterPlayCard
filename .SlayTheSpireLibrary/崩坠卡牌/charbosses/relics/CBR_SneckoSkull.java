/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.SneckoSkull;
/*    */ 
/*    */ public class CBR_SneckoSkull extends AbstractCharbossRelic {
/*    */   public static final String ID = "Snake Skull";
/*    */   
/*    */   public CBR_SneckoSkull() {
/* 10 */     super((AbstractRelic)new SneckoSkull());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new CBR_SneckoSkull();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_SneckoSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */