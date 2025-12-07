/*    */ package downfall.patches;
/*    */ import automaton.AutomatonChar;
/*    */ import champ.ChampChar;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.screens.VictoryScreen;
/*    */ import com.megacrit.cardcrawl.ui.buttons.ReturnToMenuButton;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ import downfall.unlocks.AutomatonUnlock;
/*    */ import downfall.unlocks.ChampUnlock;
/*    */ import downfall.unlocks.CollectorUnlock;
/*    */ import downfall.unlocks.GremlinUnlock;
/*    */ import downfall.unlocks.GuardianUnlock;
/*    */ import downfall.unlocks.HexaghostUnlock;
/*    */ import downfall.unlocks.SneckoUnlock;
/*    */ import gremlin.patches.GremlinEnum;
/*    */ import guardian.patches.GuardianEnum;
/*    */ import javassist.CtBehavior;
/*    */ import slimebound.patches.SlimeboundEnum;
/*    */ import sneckomod.TheSnecko;
/*    */ import theHexaghost.TheHexaghost;
/*    */ 
/*    */ @SpirePatch(clz = VictoryScreen.class, method = "update")
/*    */ public class VictoryScreenUnlockPatch {
/*    */   @SpireInsertPatch(locator = Locator.class)
/*    */   public static SpireReturn Insert(VictoryScreen __instance) {
/* 33 */     if (AbstractDungeon.unlocks.isEmpty() || Settings.isDemo)
/* 34 */       if (Settings.isDemo || Settings.isDailyRun)
/* 35 */       { CardCrawlGame.startOver(); }
/* 36 */       else { if (UnlockTracker.isCharacterLocked("Guardian") && !UnlockTracker.isCharacterLocked("The Silent") && AbstractDungeon.player.chosenClass == SlimeboundEnum.SLIMEBOUND) {
/* 37 */           AbstractDungeon.unlocks.add(new GuardianUnlock());
/* 38 */           AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */           
/* 40 */           return SpireReturn.Return(null);
/* 41 */         }  if (UnlockTracker.isCharacterLocked("Hexaghost") && AbstractDungeon.player.chosenClass == GuardianEnum.GUARDIAN) {
/* 42 */           AbstractDungeon.unlocks.add(new HexaghostUnlock());
/* 43 */           AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */           
/* 45 */           return SpireReturn.Return(null);
/* 46 */         }  if (UnlockTracker.isCharacterLocked("Champ") && AbstractDungeon.player.chosenClass == TheHexaghost.Enums.THE_SPIRIT) {
/* 47 */           AbstractDungeon.unlocks.add(new ChampUnlock());
/* 48 */           AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */           
/* 50 */           return SpireReturn.Return(null);
/* 51 */         }  if (UnlockTracker.isCharacterLocked("Automaton") && AbstractDungeon.player.chosenClass == ChampChar.Enums.THE_CHAMP) {
/* 52 */           AbstractDungeon.unlocks.add(new AutomatonUnlock());
/* 53 */           AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */           
/* 55 */           return SpireReturn.Return(null);
/* 56 */         }  if (UnlockTracker.isCharacterLocked("Gremlin") && AbstractDungeon.player.chosenClass == AutomatonChar.Enums.THE_AUTOMATON) {
/* 57 */           AbstractDungeon.unlocks.add(new GremlinUnlock());
/* 58 */           AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */           
/* 60 */           return SpireReturn.Return(null);
/* 61 */         }  if (UnlockTracker.isCharacterLocked("Snecko") && AbstractDungeon.player.chosenClass == GremlinEnum.GREMLIN) {
/* 62 */           AbstractDungeon.unlocks.add(new SneckoUnlock());
/* 63 */           AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */           
/* 65 */           return SpireReturn.Return(null);
/* 66 */         }  if (UnlockTracker.isCharacterLocked("Collector") && AbstractDungeon.player.chosenClass == TheSnecko.Enums.THE_SNECKO) {
/* 67 */           AbstractDungeon.unlocks.add(new CollectorUnlock());
/* 68 */           AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/* 69 */           return SpireReturn.Return(null);
/*    */         }  }
/*    */        
/* 72 */     return SpireReturn.Continue();
/*    */   }
/*    */   
/*    */   private static class Locator
/*    */     extends SpireInsertLocator
/*    */   {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 79 */       Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ReturnToMenuButton.class, "hide");
/* 80 */       int[] lines = LineFinder.findAllInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
/* 81 */       return lines;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\VictoryScreenUnlockPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */