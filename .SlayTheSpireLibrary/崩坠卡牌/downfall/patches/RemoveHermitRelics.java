/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = AbstractDungeon.class, method = "initializeRelicList")
/*    */ public class RemoveHermitRelics
/*    */ {
/* 15 */   public static String[] hermitRelics = new String[] { "hermit:BloodyTooth", "hermit:BrassTacks", "hermit:Horseshoe" };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpireInsertPatch(rloc = 8, localvars = {"tmp"})
/*    */   public static void Prefix(AbstractDungeon __instance) {
/* 22 */     if (AbstractDungeon.player.chosenClass.name() != "HERMIT" && !EvilModeCharacterSelect.evilMode && !downfallMod.contentSharing_relics)
/* 23 */       for (String i : hermitRelics)
/* 24 */         AbstractDungeon.relicsToRemoveOnStart.add(i);  
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\RemoveHermitRelics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */