/*    */ package downfall.unlocks;
/*    */ 
/*    */ import automaton.AutomatonChar;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.unlock.AbstractUnlock;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AutomatonUnlock
/*    */   extends AbstractUnlock
/*    */ {
/*    */   public static final String KEY = "Automaton";
/*    */   
/*    */   public void onUnlockScreenOpen() {
/* 16 */     this.player = CardCrawlGame.characterManager.getCharacter(AutomatonChar.Enums.THE_AUTOMATON);
/* 17 */     this.player.drawX = Settings.WIDTH / 2.0F - 20.0F * Settings.scale;
/* 18 */     this.player.drawY = Settings.HEIGHT / 2.0F - 150.0F * Settings.scale;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\unlocks\AutomatonUnlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */