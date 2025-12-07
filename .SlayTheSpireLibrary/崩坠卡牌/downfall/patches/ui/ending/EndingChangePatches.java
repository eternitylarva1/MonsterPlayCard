/*    */ package downfall.patches.ui.ending;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.cutscenes.NeowNarrationScreen;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ @SpirePatch(clz = NeowNarrationScreen.class, method = "<ctor>")
/*    */ public class EndingChangePatches
/*    */ {
/*    */   @SpirePostfixPatch
/*    */   public static void patch(NeowNarrationScreen _instance) {
/* 15 */     if (downfallMod.neowtextoverride) {
/* 16 */       ReflectionHacks.setPrivateStaticFinal(NeowNarrationScreen.class, "charStrings", CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("NeowEnding")));
/*    */     } else {
/* 18 */       ReflectionHacks.setPrivateStaticFinal(NeowNarrationScreen.class, "charStrings", CardCrawlGame.languagePack.getCharacterString("PostCreditsNeow"));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patche\\ui\ending\EndingChangePatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */