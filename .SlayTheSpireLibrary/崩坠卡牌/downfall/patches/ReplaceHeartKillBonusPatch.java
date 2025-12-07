/*    */ package downfall.patches;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.screens.GameOverScreen;
/*    */ import com.megacrit.cardcrawl.screens.GameOverStat;
/*    */ import com.megacrit.cardcrawl.screens.VictoryScreen;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReplaceHeartKillBonusPatch
/*    */ {
/* 20 */   public static String CHECKAGAINST = (CardCrawlGame.languagePack.getScoreString("Heartbreaker")).NAME;
/* 21 */   public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("WhaleHunter"));
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = VictoryScreen.class, method = "createGameOverStats")
/*    */   public static class VictoryScreenPatch
/*    */   {
/*    */     public static void Postfix(VictoryScreen __instance) {
/* 29 */       if (EvilModeCharacterSelect.evilMode) {
/* 30 */         ArrayList<GameOverStat> stats = (ArrayList<GameOverStat>)ReflectionHacks.getPrivate(__instance, GameOverScreen.class, "stats");
/* 31 */         int idx = -1;
/* 32 */         for (int i = 0; i < stats.size(); i++) {
/* 33 */           if (((GameOverStat)stats.get(i)).label != null && 
/* 34 */             ((GameOverStat)stats.get(i)).label.equals(ReplaceHeartKillBonusPatch.CHECKAGAINST)) {
/* 35 */             idx = i;
/*    */           }
/*    */         } 
/*    */         
/* 39 */         stats.remove(idx);
/* 40 */         stats.add(idx, new GameOverStat(ReplaceHeartKillBonusPatch.uiStrings.TEXT[0], ReplaceHeartKillBonusPatch.uiStrings.TEXT[1], Integer.toString(250)));
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\ReplaceHeartKillBonusPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */