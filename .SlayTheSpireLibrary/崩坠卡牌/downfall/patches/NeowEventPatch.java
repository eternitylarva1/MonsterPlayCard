/*    */ package downfall.patches;
/*    */ 
/*    */ import chronoMods.TogetherManager;
/*    */ import com.evacipated.cardcrawl.modthespire.Loader;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*    */ import com.megacrit.cardcrawl.neow.NeowRoom;
/*    */ import downfall.events.HeartEvent;
/*    */ import javassist.CtBehavior;
/*    */ import slimebound.SlimeboundMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = NeowRoom.class, method = "<ctor>")
/*    */ public class NeowEventPatch
/*    */ {
/*    */   @SpireInsertPatch(locator = Locator.class)
/*    */   public static void ChangeEvent(NeowRoom __instance, boolean isDone) {
/* 23 */     boolean switchToHeart = true;
/* 24 */     if (EvilModeCharacterSelect.evilMode) {
/* 25 */       SlimeboundMod.logger.info("Neow Event detected evil mode");
/* 26 */       if (Loader.isModLoaded("chronoMods")) {
/* 27 */         SlimeboundMod.logger.info("Neow Event detected Spire With Friends");
/* 28 */         if (TogetherManager.gameMode.equals(TogetherManager.mode.Coop)) {
/* 29 */           SlimeboundMod.logger.info("Neow Event detected Co-op Mode");
/* 30 */           switchToHeart = false;
/*    */         } 
/*    */       } 
/* 33 */       if (switchToHeart) __instance.event = (AbstractEvent)new HeartEvent(isDone); 
/*    */     } 
/*    */   }
/*    */   
/*    */   private static class Locator
/*    */     extends SpireInsertLocator {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 40 */       Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(AbstractEvent.class, "onEnterRoom");
/* 41 */       return LineFinder.findInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\NeowEventPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */