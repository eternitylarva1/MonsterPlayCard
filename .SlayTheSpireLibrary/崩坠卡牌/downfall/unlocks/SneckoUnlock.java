/*    */ package downfall.unlocks;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.unlock.AbstractUnlock;
/*    */ import sneckomod.TheSnecko;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SneckoUnlock
/*    */   extends AbstractUnlock
/*    */ {
/*    */   public static final String KEY = "Snecko";
/*    */   
/*    */   public void onUnlockScreenOpen() {
/* 16 */     this.player = CardCrawlGame.characterManager.getCharacter(TheSnecko.Enums.THE_SNECKO);
/* 17 */     this.player.drawX = Settings.WIDTH / 2.0F - 20.0F * Settings.scale;
/* 18 */     this.player.drawY = Settings.HEIGHT / 2.0F - 118.0F * Settings.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\unlocks\SneckoUnlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */