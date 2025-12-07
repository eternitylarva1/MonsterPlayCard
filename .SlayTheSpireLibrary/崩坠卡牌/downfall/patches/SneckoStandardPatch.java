/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.neow.NeowEvent;
/*    */ import downfall.downfallMod;
/*    */ import javassist.CtBehavior;
/*    */ 
/*    */ @SpirePatch(clz = NeowEvent.class, method = "update")
/*    */ public class SneckoStandardPatch
/*    */ {
/*    */   @SpireInsertPatch(locator = Locator.class)
/*    */   public static void SetTheThing(NeowEvent __instance) {
/* 17 */     downfallMod.readyToDoThing = true;
/*    */   }
/*    */   
/*    */   private static class Locator
/*    */     extends SpireInsertLocator {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 23 */       Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(NeowEvent.class, "setPhaseToEvent");
/* 24 */       return new int[] { LineFinder.findAllInOrder(ctMethodToPatch, (Matcher)fieldAccessMatcher)[1] };
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\SneckoStandardPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */