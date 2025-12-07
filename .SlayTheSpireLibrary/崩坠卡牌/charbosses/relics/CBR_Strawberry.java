/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Strawberry;
/*    */ 
/*    */ public class CBR_Strawberry
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Strawberry";
/*    */   
/*    */   public CBR_Strawberry() {
/* 12 */     super((AbstractRelic)new Strawberry());
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 17 */     this.owner.increaseMaxHp(7, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 22 */     return new CBR_Strawberry();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Strawberry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */