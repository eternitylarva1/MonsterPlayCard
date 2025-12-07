/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Omamori;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_Omamori extends AbstractCharbossRelic {
/*    */   public static final String ID = "Omamori";
/* 10 */   private String addedDesc = "";
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0] + this.addedDesc;
/*    */   }
/*    */ 
/*    */   
/*    */   public CBR_Omamori() {
/* 20 */     super((AbstractRelic)new Omamori());
/* 21 */     this.counter = 0;
/* 22 */     usedUp();
/* 23 */     this.description = getUpdatedDescription();
/* 24 */     refreshDescription();
/*    */   }
/*    */   
/*    */   public void use(String cardName) {
/* 28 */     flash();
/* 29 */     this.counter--;
/* 30 */     this.addedDesc += (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("Omamori"))).DESCRIPTIONS[0] + cardName + ".";
/*    */     
/* 32 */     if (this.counter == 0) {
/* 33 */       usedUp();
/*    */     }
/*    */     
/* 36 */     this.description = getUpdatedDescription();
/* 37 */     refreshDescription();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 44 */     return new CBR_Omamori();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Omamori.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */