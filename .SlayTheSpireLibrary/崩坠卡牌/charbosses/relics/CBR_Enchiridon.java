/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Enchiridion;
/*    */ 
/*    */ public class CBR_Enchiridon
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "Enchiridion";
/*    */   
/*    */   public CBR_Enchiridon() {
/* 11 */     super((AbstractRelic)new Enchiridion());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atPreBattle() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 26 */     return new CBR_Enchiridon();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Enchiridon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */