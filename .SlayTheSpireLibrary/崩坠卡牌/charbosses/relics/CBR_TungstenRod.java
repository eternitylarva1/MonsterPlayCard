/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.TungstenRod;
/*    */ 
/*    */ public class CBR_TungstenRod extends AbstractCharbossRelic {
/*    */   public static final String ID = "TungstenRod";
/*    */   
/*    */   public CBR_TungstenRod() {
/* 10 */     super((AbstractRelic)new TungstenRod());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public int onLoseHpLast(int damageAmount) {
/* 20 */     if (damageAmount > 0) {
/* 21 */       flash();
/* 22 */       return damageAmount - 1;
/*    */     } 
/* 24 */     return damageAmount;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new CBR_TungstenRod();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_TungstenRod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */