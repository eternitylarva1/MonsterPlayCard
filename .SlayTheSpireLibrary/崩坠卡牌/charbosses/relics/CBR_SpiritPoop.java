/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.SpiritPoop;
/*    */ 
/*    */ public class CBR_SpiritPoop
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_SpiritPoop() {
/*  9 */     super((AbstractRelic)new SpiritPoop());
/* 10 */     this.tier = AbstractRelic.RelicTier.SPECIAL;
/*    */   }
/*    */   public static final String ID = "SpiritPoop";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 20 */     return new CBR_SpiritPoop();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_SpiritPoop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */