/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Matryoshka;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_Matroyshka
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "Matroyshka";
/*    */   private int numRelics;
/*    */   
/*    */   public CBR_Matroyshka() {
/* 14 */     super((AbstractRelic)new Matryoshka());
/* 15 */     this.counter = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("Matroyshka"))).DESCRIPTIONS[0] + this.numRelics + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("Matroyshka"))).DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 25 */     return new CBR_Matroyshka();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Matroyshka.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */