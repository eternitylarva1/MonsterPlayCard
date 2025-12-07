/*    */ package downfall.unlocks;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.unlock.AbstractUnlock;
/*    */ import theHexaghost.TheHexaghost;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HexaghostUnlock
/*    */   extends AbstractUnlock
/*    */ {
/*    */   public static final String KEY = "Hexaghost";
/*    */   
/*    */   public void onUnlockScreenOpen() {
/* 16 */     this.player = CardCrawlGame.characterManager.getCharacter(TheHexaghost.Enums.THE_SPIRIT);
/* 17 */     this.player.drawX = Settings.WIDTH / 2.0F + 20.0F * Settings.scale;
/* 18 */     this.player.drawY = Settings.HEIGHT / 2.0F - 235.0F * Settings.scale;
/* 19 */     TheHexaghost hP = (TheHexaghost)this.player;
/* 20 */     hP.myBody.XOffset = 485.0F * Settings.scale;
/* 21 */     hP.myBody.YOffset = -40.0F * Settings.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\unlocks\HexaghostUnlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */