/*    */ package downfall.patches;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
/*    */ import com.megacrit.cardcrawl.ui.campfire.RecallOption;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = RecallOption.class, method = "<ctor>")
/*    */ public class CampfireOptionTextPatch
/*    */ {
/* 17 */   private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("AscensionText"));
/*    */   
/*    */   public static void Postfix(RecallOption __instance) {
/* 20 */     if (EvilModeCharacterSelect.evilMode)
/* 21 */       ReflectionHacks.setPrivate(__instance, AbstractCampfireOption.class, "description", uiStrings.TEXT[2]); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\CampfireOptionTextPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */