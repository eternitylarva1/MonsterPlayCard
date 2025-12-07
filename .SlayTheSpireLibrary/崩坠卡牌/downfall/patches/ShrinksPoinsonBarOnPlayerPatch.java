/*    */ package downfall.patches;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.ByRef;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import javassist.CtBehavior;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ShrinksPoinsonBarOnPlayerPatch
/*    */ {
/*    */   @SpirePatch(clz = AbstractCreature.class, method = "renderRedHealthBar")
/*    */   public static class ShrinksPoinsonBarOnPlayer
/*    */   {
/*    */     @SpireInsertPatch(locator = Locator.class, localvars = {"poisonAmt"})
/*    */     public static void Insert(AbstractCreature __instance, SpriteBatch sb, float x, float y, @ByRef int[] poisonAmt) {
/* 24 */       if (poisonAmt[0] > 0 && AbstractDungeon.player.hasPower("IntangiblePlayer"))
/* 25 */         poisonAmt[0] = 1; 
/*    */     }
/*    */     
/*    */     private static class Locator
/*    */       extends SpireInsertLocator
/*    */     {
/*    */       public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 32 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "hasPower");
/* 33 */         return new int[] { LineFinder.findAllInOrder(ctMethodToPatch, (Matcher)methodCallMatcher)[1] };
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\ShrinksPoinsonBarOnPlayerPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */