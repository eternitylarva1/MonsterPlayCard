/*    */ package downfall.patches;
/*    */ 
/*    */ import automaton.AutomatonChar;
/*    */ import champ.ChampChar;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.ui.buttons.ReturnToMenuButton;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import gremlin.patches.GremlinEnum;
/*    */ import guardian.patches.GuardianEnum;
/*    */ import theHexaghost.TheHexaghost;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = ReturnToMenuButton.class, method = "appear", paramtypez = {float.class, float.class, String.class})
/*    */ public class DeathScreenCharUnlockPatch
/*    */ {
/*    */   @SpirePostfixPatch
/*    */   public static void Postfix(ReturnToMenuButton __instance, float x, float y, String label) {
/* 28 */     String[] TEXT = (CardCrawlGame.languagePack.getUIString("DeathScreen")).TEXT;
/*    */     
/* 30 */     if (label == TEXT[37])
/*    */     {
/* 32 */       if (UnlockTracker.isCharacterLocked("Guardian")) {
/*    */         
/* 34 */         __instance.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/* 35 */         __instance.label = TEXT[40];
/* 36 */       } else if (UnlockTracker.isCharacterLocked("Hexaghost") && EvilModeCharacterSelect.evilMode && AbstractDungeon.player.chosenClass == GuardianEnum.GUARDIAN) {
/*    */         
/* 38 */         __instance.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/* 39 */         __instance.label = TEXT[40];
/* 40 */       } else if (UnlockTracker.isCharacterLocked("Champ") && EvilModeCharacterSelect.evilMode && AbstractDungeon.player.chosenClass == TheHexaghost.Enums.THE_SPIRIT) {
/*    */         
/* 42 */         __instance.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/* 43 */         __instance.label = TEXT[40];
/* 44 */       } else if (UnlockTracker.isCharacterLocked("Automaton") && EvilModeCharacterSelect.evilMode && AbstractDungeon.player.chosenClass == ChampChar.Enums.THE_CHAMP) {
/*    */         
/* 46 */         __instance.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/* 47 */         __instance.label = TEXT[40];
/* 48 */       } else if (UnlockTracker.isCharacterLocked("Gremlin") && EvilModeCharacterSelect.evilMode && AbstractDungeon.player.chosenClass == AutomatonChar.Enums.THE_AUTOMATON) {
/*    */         
/* 50 */         __instance.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/* 51 */         __instance.label = TEXT[40];
/* 52 */       } else if (UnlockTracker.isCharacterLocked("Snecko") && 
/* 53 */         !UnlockTracker.isCharacterLocked("SlimeBoss") && 
/* 54 */         !UnlockTracker.isCharacterLocked("Guardian") && 
/* 55 */         !UnlockTracker.isCharacterLocked("Hexaghost") && 
/* 56 */         !UnlockTracker.isCharacterLocked("Champ") && 
/* 57 */         !UnlockTracker.isCharacterLocked("Automaton") && 
/* 58 */         !UnlockTracker.isCharacterLocked("Gremlin") && EvilModeCharacterSelect.evilMode && AbstractDungeon.player.chosenClass == GremlinEnum.GREMLIN) {
/*    */ 
/*    */         
/* 61 */         __instance.appear(Settings.WIDTH / 2.0F, Settings.HEIGHT * 0.15F, TEXT[40]);
/* 62 */         __instance.label = TEXT[40];
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\DeathScreenCharUnlockPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */