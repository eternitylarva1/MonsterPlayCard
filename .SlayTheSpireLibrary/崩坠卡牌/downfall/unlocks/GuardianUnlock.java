/*    */ package downfall.unlocks;
/*    */ 
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.unlock.AbstractUnlock;
/*    */ import guardian.patches.GuardianEnum;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GuardianUnlock
/*    */   extends AbstractUnlock
/*    */ {
/*    */   public static final String KEY = "Guardian";
/*    */   
/*    */   public void onUnlockScreenOpen() {
/* 16 */     this.player = CardCrawlGame.characterManager.getCharacter(GuardianEnum.GUARDIAN);
/* 17 */     this.player.drawX = Settings.WIDTH / 2.0F - 20.0F * Settings.scale;
/* 18 */     this.player.drawY = Settings.HEIGHT / 2.0F - 118.0F * Settings.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\unlocks\GuardianUnlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */