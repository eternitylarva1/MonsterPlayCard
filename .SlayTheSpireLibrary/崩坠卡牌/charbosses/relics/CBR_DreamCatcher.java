/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.DreamCatcher;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_DreamCatcher extends AbstractCharbossRelic {
/*    */   public static final String ID = "DreamCatcher";
/*    */   private int numCards;
/*    */   
/*    */   public CBR_DreamCatcher() {
/* 13 */     super((AbstractRelic)new DreamCatcher());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 17 */     return this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("DreamCatcher"))).DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 22 */     return new CBR_DreamCatcher();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_DreamCatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */