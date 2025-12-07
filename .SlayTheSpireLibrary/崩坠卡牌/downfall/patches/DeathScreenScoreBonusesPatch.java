/*    */ package downfall.patches;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.screens.DeathScreen;
/*    */ import com.megacrit.cardcrawl.screens.GameOverScreen;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeathScreenScoreBonusesPatch
/*    */ {
/* 19 */   public static String CHECKAGAINST = (CardCrawlGame.languagePack.getScoreString("Heartbreaker")).NAME;
/* 20 */   public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("WhaleHunter"));
/* 21 */   public static final UIStrings uiStrings2 = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("Unfettered"));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = DeathScreen.class, method = "createGameOverStats")
/*    */   public static class DeathScreenPatch
/*    */   {
/*    */     public static void Postfix(DeathScreen __instance) {
/* 30 */       if (EvilModeCharacterSelect.evilMode)
/* 31 */         ArrayList arrayList = (ArrayList)ReflectionHacks.getPrivate(__instance, GameOverScreen.class, "stats"); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\DeathScreenScoreBonusesPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */