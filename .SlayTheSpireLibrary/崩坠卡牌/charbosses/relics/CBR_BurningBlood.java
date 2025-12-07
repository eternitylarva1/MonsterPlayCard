/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.BurningBlood;
/*    */ 
/*    */ public class CBR_BurningBlood extends AbstractCharbossRelic {
/*    */   public static final String ID = "Burning Blood";
/*    */   private static final int HEALTH_AMT = 6;
/*    */   
/*    */   public CBR_BurningBlood() {
/* 11 */     super((AbstractRelic)new BurningBlood());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0] + '\006' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 21 */     return new CBR_BurningBlood();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_BurningBlood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */