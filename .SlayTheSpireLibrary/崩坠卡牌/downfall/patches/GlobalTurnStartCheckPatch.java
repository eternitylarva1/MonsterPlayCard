/*    */ package downfall.patches;
/*    */ 
/*    */ import automaton.FunctionHelper;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.bosses.Silent.CharBossSilent;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.actions.GameActionManager;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import javassist.CtBehavior;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = GameActionManager.class, method = "getNextAction")
/*    */ public class GlobalTurnStartCheckPatch
/*    */ {
/*    */   @SpireInsertPatch(locator = Locator.class)
/*    */   public static void Insert(GameActionManager __instance) {
/* 26 */     if (FunctionHelper.held != null) {
/* 27 */       for (AbstractCard q : FunctionHelper.held.group) {
/* 28 */         if (q instanceof automaton.cards.RecursiveStrike) {
/* 29 */           q.atTurnStart();
/*    */         }
/*    */       } 
/*    */     }
/* 33 */     if (AbstractCharBoss.boss != null && 
/* 34 */       AbstractCharBoss.boss.chosenArchetype instanceof charbosses.bosses.Silent.NewAge.ArchetypeAct2MirrorImageNewAge && (AbstractDungeon.lastCombatMetricKey.equals("SI_MIRROR_ARCHETYPE") || AbstractDungeon.actNum == 4))
/* 35 */       ((CharBossSilent)AbstractCharBoss.boss).spawnImage(false); 
/*    */   }
/*    */   
/*    */   private static class Locator
/*    */     extends SpireInsertLocator
/*    */   {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 42 */       Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnRelics");
/* 43 */       return LineFinder.findInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\GlobalTurnStartCheckPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */