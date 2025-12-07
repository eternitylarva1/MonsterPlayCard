/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.PrayerWheel;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_PrayerWheel extends AbstractCharbossRelic {
/*    */   public static final String ID = "PrayerWheel";
/*    */   private int numCards;
/*    */   
/*    */   public CBR_PrayerWheel() {
/* 13 */     super((AbstractRelic)new PrayerWheel());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("PrayerWheel"))).DESCRIPTIONS[0] + this.numCards + (CardCrawlGame.languagePack.getRelicStrings(downfallMod.makeID("PrayerWheel"))).DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 23 */     return new CBR_PrayerWheel();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_PrayerWheel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */