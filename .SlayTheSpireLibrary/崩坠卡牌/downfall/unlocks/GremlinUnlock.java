/*    */ package downfall.unlocks;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.unlock.AbstractUnlock;
/*    */ import gremlin.patches.GremlinEnum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GremlinUnlock
/*    */   extends AbstractUnlock
/*    */ {
/*    */   public static final String KEY = "Gremlin";
/*    */   
/*    */   public void onUnlockScreenOpen() {
/* 17 */     this.player = CardCrawlGame.characterManager.getCharacter(GremlinEnum.GREMLIN);
/* 18 */     this.player.drawX = Settings.WIDTH / 2.0F - 20.0F * Settings.scale;
/* 19 */     this.player.drawY = Settings.HEIGHT / 2.0F - 150.0F * Settings.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\unlocks\GremlinUnlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */