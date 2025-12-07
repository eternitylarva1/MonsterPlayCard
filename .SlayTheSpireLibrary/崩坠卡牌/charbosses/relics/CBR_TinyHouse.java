/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.TinyHouse;
/*    */ 
/*    */ public class CBR_TinyHouse extends AbstractCharbossRelic {
/*    */   public static final String ID = "TinyHouse";
/*    */   
/*    */   public CBR_TinyHouse() {
/* 10 */     super((AbstractRelic)new TinyHouse());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 15 */     this.owner.increaseMaxHp(5, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new CBR_TinyHouse();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_TinyHouse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */