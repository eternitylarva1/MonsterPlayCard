/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.ToxicEgg2;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_ToxicEgg extends AbstractCharbossRelic {
/*    */   public static final String ID = "ToxicEgg";
/* 10 */   int numCards = 0;
/*    */ 
/*    */   
/*    */   public CBR_ToxicEgg() {
/* 14 */     super((AbstractRelic)new ToxicEgg2());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("ToxicEgg"))).DESCRIPTIONS[0] + this.numCards + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("ToxicEgg"))).DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTrigger() {
/* 25 */     this.numCards++;
/* 26 */     this.description = getUpdatedDescription();
/* 27 */     refreshDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new CBR_ToxicEgg();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_ToxicEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */