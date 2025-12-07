/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.EmptyCage;
/*    */ 
/*    */ public class CBR_EmptyCage extends AbstractCharbossRelic {
/*    */   public static final String ID = "EmptyCage";
/*    */   
/*    */   public CBR_EmptyCage() {
/* 10 */     super((AbstractRelic)new EmptyCage());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 25 */     return new CBR_EmptyCage();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_EmptyCage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */