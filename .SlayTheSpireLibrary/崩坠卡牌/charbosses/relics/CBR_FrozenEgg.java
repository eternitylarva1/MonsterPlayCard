/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.FrozenEgg2;
/*    */ 
/*    */ public class CBR_FrozenEgg extends AbstractCharbossRelic {
/*    */   public static final String ID = "FrozenEgg";
/*  8 */   int numCards = 0;
/*    */   
/*    */   public CBR_FrozenEgg() {
/* 11 */     super((AbstractRelic)new FrozenEgg2());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTrigger() {
/* 21 */     this.numCards++;
/* 22 */     this.description = getUpdatedDescription();
/* 23 */     refreshDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 28 */     return new CBR_FrozenEgg();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_FrozenEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */