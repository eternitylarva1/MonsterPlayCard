/*    */ package downfall.patches;
/*    */ 
/*    */ import automaton.AutomatonChar;
/*    */ import champ.ChampChar;
/*    */ import collector.CollectorChar;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.screens.DeathScreen;
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
/*    */ import java.util.ArrayList;
/*    */ import javassist.CtBehavior;
/*    */ import slimebound.patches.SlimeboundEnum;
/*    */ import theHexaghost.TheHexaghost;
/*    */ 
/*    */ @SpirePatch(clz = DeathScreen.class, method = "update")
/*    */ public class DeathScreenUnlockPatch
/*    */ {
/*    */   @SpireInsertPatch(locator = Locator.class)
/*    */   public static SpireReturn Insert(DeathScreen __instance) {
/* 37 */     if (AbstractDungeon.unlocks.isEmpty() || Settings.isDemo)
/*    */     {
/* 39 */       if (Settings.isDemo || Settings.isDailyRun) {
/*    */         
/* 41 */         CardCrawlGame.startOver();
/*    */ 
/*    */ 
/*    */ 
/*    */       
/*    */       }
/* 47 */       else if (AbstractDungeon.unlocks.isEmpty() || Settings.isDemo) {
/* 48 */         if (Settings.isDemo || Settings.isDailyRun)
/* 49 */         { CardCrawlGame.startOver(); }
/* 50 */         else { if (UnlockTracker.isCharacterLocked("Guardian") && !UnlockTracker.isCharacterLocked("The Silent") && AbstractDungeon.player.chosenClass == SlimeboundEnum.SLIMEBOUND) {
/* 51 */             AbstractDungeon.unlocks.add(new GuardianUnlock());
/* 52 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */             
/* 54 */             return SpireReturn.Return(null);
/* 55 */           }  if (UnlockTracker.isCharacterLocked("Hexaghost") && AbstractDungeon.player.chosenClass == GuardianEnum.GUARDIAN) {
/* 56 */             AbstractDungeon.unlocks.add(new HexaghostUnlock());
/* 57 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */             
/* 59 */             return SpireReturn.Return(null);
/* 60 */           }  if (UnlockTracker.isCharacterLocked("Champ") && AbstractDungeon.player.chosenClass == TheHexaghost.Enums.THE_SPIRIT) {
/* 61 */             AbstractDungeon.unlocks.add(new ChampUnlock());
/* 62 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */             
/* 64 */             return SpireReturn.Return(null);
/* 65 */           }  if (UnlockTracker.isCharacterLocked("Automaton") && AbstractDungeon.player.chosenClass == ChampChar.Enums.THE_CHAMP) {
/* 66 */             AbstractDungeon.unlocks.add(new AutomatonUnlock());
/* 67 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */             
/* 69 */             return SpireReturn.Return(null);
/* 70 */           }  if (UnlockTracker.isCharacterLocked("Collector") && AbstractDungeon.player.chosenClass == AutomatonChar.Enums.THE_AUTOMATON) {
/* 71 */             AbstractDungeon.unlocks.add(new CollectorUnlock());
/* 72 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */             
/* 74 */             return SpireReturn.Return(null);
/* 75 */           }  if (UnlockTracker.isCharacterLocked("Gremlin") && AbstractDungeon.player.chosenClass == CollectorChar.Enums.THE_COLLECTOR) {
/* 76 */             AbstractDungeon.unlocks.add(new GremlinUnlock());
/* 77 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/*    */             
/* 79 */             return SpireReturn.Return(null);
/* 80 */           }  if (UnlockTracker.isCharacterLocked("Snecko") && AbstractDungeon.player.chosenClass == GremlinEnum.GREMLIN) {
/* 81 */             AbstractDungeon.unlocks.add(new SneckoUnlock());
/* 82 */             AbstractDungeon.unlockScreen.open(AbstractDungeon.unlocks.remove(0));
/* 83 */             return SpireReturn.Return(null);
/*    */           }  }
/*    */       
/*    */       } 
/*    */     }
/* 88 */     return SpireReturn.Continue();
/*    */   }
/*    */ 
/*    */   
/*    */   private static class Locator
/*    */     extends SpireInsertLocator
/*    */   {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 96 */       Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ReturnToMenuButton.class, "hide");
/* 97 */       return new int[] { LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList(), (Matcher)methodCallMatcher)[0], LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList(), (Matcher)methodCallMatcher)[1] };
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\DeathScreenUnlockPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */