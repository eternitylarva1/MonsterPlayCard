/*    */ package downfall.patches;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
/*    */ public class AscensionTextPatch
/*    */ {
/* 16 */   private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("AscensionText"));
/*    */   
/*    */   public static void Prefix(CharacterSelectScreen __instance, SpriteBatch sb) {
/* 19 */     if (EvilModeCharacterSelect.evilMode)
/*    */     {
/*    */       
/* 22 */       if (__instance.ascensionLevel == 19) {
/* 23 */         __instance.ascLevelInfoString = uiStrings.TEXT[0];
/* 24 */       } else if (__instance.ascensionLevel == 4) {
/* 25 */         __instance.ascLevelInfoString = uiStrings.TEXT[3];
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\AscensionTextPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */