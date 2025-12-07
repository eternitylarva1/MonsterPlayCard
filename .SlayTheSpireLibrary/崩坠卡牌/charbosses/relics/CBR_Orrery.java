/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Orrery;
/*    */ 
/*    */ public class CBR_Orrery extends AbstractCharbossRelic {
/*    */   public static final String ID = "Orrery";
/*    */   
/*    */   public CBR_Orrery() {
/* 10 */     super((AbstractRelic)new Orrery());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 14 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEquip() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 24 */     return new CBR_Orrery();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Orrery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */