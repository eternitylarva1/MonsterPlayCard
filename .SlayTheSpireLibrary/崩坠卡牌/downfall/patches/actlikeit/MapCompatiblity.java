/*    */ package downfall.patches.actlikeit;
/*    */ 
/*    */ import actlikeit.patches.DungeonMapPatches;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import downfall.patches.EvilModeCharacterSelect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(optional = true, cls = "actlikeit.patches.DungeonMapPatches", method = "atMapEnd")
/*    */ public class MapCompatiblity
/*    */ {
/*    */   @SpirePrefixPatch
/*    */   public static SpireReturn<Integer> evilMode() {
/* 19 */     if (EvilModeCharacterSelect.evilMode)
/*    */     {
/* 21 */       return SpireReturn.Return(Integer.valueOf(((AbstractDungeon.getCurrMapNode()).y == 0) ? (AbstractDungeon.id.equals("TheEnding") ? 2 : 14) : 13));
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     if (AbstractDungeon.id.equals("TheEnding"))
/*    */     {
/* 31 */       return SpireReturn.Return(Integer.valueOf((AbstractDungeon.getCurrMapNode()).y));
/*    */     }
/*    */     
/* 34 */     return SpireReturn.Continue();
/*    */   }
/*    */ 
/*    */   
/*    */   public static int actLikeItCheck() {
/* 39 */     return DungeonMapPatches.atMapEnd();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\actlikeit\MapCompatiblity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */