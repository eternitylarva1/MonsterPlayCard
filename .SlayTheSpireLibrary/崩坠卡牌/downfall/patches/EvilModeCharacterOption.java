/*    */ package downfall.patches;
/*    */ 
/*    */ import automaton.AutomatonChar;
/*    */ import champ.ChampChar;
/*    */ import collector.CollectorChar;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import downfall.downfallMod;
/*    */ import gremlin.patches.GremlinEnum;
/*    */ import guardian.patches.GuardianEnum;
/*    */ import sneckomod.TheSnecko;
/*    */ import theHexaghost.TheHexaghost;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CharacterOption.class, method = "updateHitbox")
/*    */ public class EvilModeCharacterOption
/*    */ {
/* 26 */   public static final String[] TEXT = (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("Unlock"))).TEXT;
/*    */ 
/*    */   
/*    */   @SpirePostfixPatch
/*    */   public static void Prefix(CharacterOption __instance) {
/* 31 */     __instance.hb.update();
/*    */     
/* 33 */     if (__instance.hb.hovered && __instance.locked)
/* 34 */       if (__instance.c.chosenClass == GuardianEnum.GUARDIAN) {
/* 35 */         TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[0] + TEXT[11]);
/*    */       }
/* 37 */       else if (__instance.c.chosenClass == TheHexaghost.Enums.THE_SPIRIT) {
/*    */         
/* 39 */         if (UnlockTracker.isCharacterLocked("Guardian")) {
/* 40 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[1] + TEXT[11]);
/*    */         } else {
/* 42 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[2] + TEXT[11]);
/*    */         } 
/* 44 */       } else if (__instance.c.chosenClass == ChampChar.Enums.THE_CHAMP) {
/* 45 */         if (UnlockTracker.isCharacterLocked("Hexaghost")) {
/* 46 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[3] + TEXT[11]);
/*    */         } else {
/* 48 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[4] + TEXT[11]);
/*    */         } 
/* 50 */       } else if (__instance.c.chosenClass == AutomatonChar.Enums.THE_AUTOMATON) {
/* 51 */         if (UnlockTracker.isCharacterLocked("Champ")) {
/* 52 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[5] + TEXT[11]);
/*    */         } else {
/* 54 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[6] + TEXT[11]);
/*    */         } 
/* 56 */       } else if (__instance.c.chosenClass == CollectorChar.Enums.THE_COLLECTOR) {
/* 57 */         if (UnlockTracker.isCharacterLocked("Automaton")) {
/* 58 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[7] + TEXT[11]);
/*    */         } else {
/* 60 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[8] + TEXT[11]);
/*    */         } 
/* 62 */       } else if (__instance.c.chosenClass == GremlinEnum.GREMLIN) {
/* 63 */         if (UnlockTracker.isCharacterLocked("Collector")) {
/* 64 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[9] + TEXT[11]);
/*    */         } else {
/* 66 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[13] + TEXT[11]);
/*    */         } 
/* 68 */       } else if (__instance.c.chosenClass == TheSnecko.Enums.THE_SNECKO) {
/* 69 */         if (UnlockTracker.isCharacterLocked("Gremlin")) {
/* 70 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[12] + TEXT[11]);
/*    */         } else {
/* 72 */           TipHelper.renderGenericTip(InputHelper.mX + 70.0F * Settings.scale, InputHelper.mY - 10.0F * Settings.scale, CharacterOption.TEXT[0], TEXT[10] + TEXT[11]);
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\EvilModeCharacterOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */