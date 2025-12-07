/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.MoltenEgg2;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_MoltenEgg extends AbstractCharbossRelic {
/*    */   public static final String ID = "MoltenEgg";
/* 10 */   int numCards = 0;
/*    */ 
/*    */   
/*    */   public CBR_MoltenEgg() {
/* 14 */     super((AbstractRelic)new MoltenEgg2());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("MoltenEgg"))).DESCRIPTIONS[0] + this.numCards + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("MoltenEgg"))).DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTrigger() {
/* 24 */     this.numCards++;
/* 25 */     this.description = getUpdatedDescription();
/* 26 */     refreshDescription();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 31 */     return new CBR_MoltenEgg();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MoltenEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */