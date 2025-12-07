/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.WarPaint;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_WarPaint
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "WarPaint";
/*    */   
/*    */   public CBR_WarPaint() {
/* 17 */     super((AbstractRelic)new WarPaint());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0] + '\002' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new CBR_WarPaint();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_WarPaint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */