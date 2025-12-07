/*    */ package downfall.patches;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockCharacterScreen;
/*    */ import java.util.ArrayList;
/*    */ import javassist.CtBehavior;
/*    */ import theHexaghost.TheHexaghost;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = UnlockCharacterScreen.class, method = "render", paramtypez = {SpriteBatch.class})
/*    */ public class HexFlamesUnlockPatch
/*    */ {
/*    */   @SpireInsertPatch(locator = Locator.class)
/*    */   public static void Insert(UnlockCharacterScreen __instance, SpriteBatch sb) {
/* 21 */     if (__instance.unlock.player instanceof TheHexaghost) {
/* 22 */       TheHexaghost hex = (TheHexaghost)__instance.unlock.player;
/* 23 */       hex.myBody.render(sb);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static class Locator
/*    */     extends SpireInsertLocator
/*    */   {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 33 */       Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(UnlockCharacterScreen.class, "unlock");
/* 34 */       return new int[] { LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList(), (Matcher)fieldAccessMatcher)[1] };
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\HexFlamesUnlockPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */