/*    */ package downfall.patches;
/*    */ 
/*    */ import com.badlogic.gdx.audio.Music;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.audio.MainMusic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ @SpirePatch(clz = MainMusic.class, method = "getSong")
/*    */ public class MainMenuMusic {
/*    */   @SpirePostfixPatch
/*    */   public static Music Postfix(Music __result, MainMusic __instance, String key) {
/* 13 */     if (!downfallMod.noMusic && 
/* 14 */       "MENU".equals(key)) {
/* 15 */       return MainMusic.newMusic("downfallResources/music/MenuMusic.mp3");
/*    */     }
/*    */     
/* 18 */     return __result;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\MainMenuMusic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */