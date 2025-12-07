/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.ByRef;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CNDoubleSpaceIssueFixPatch
/*    */ {
/* 14 */   private static float currentWidthStore = -999.0F;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = AbstractCard.class, method = "initializeDescriptionCN")
/*    */   public static class InsertBefore
/*    */   {
/*    */     @SpireInsertPatch(rloc = 14, localvars = {"currentWidth"})
/*    */     public static void Insert(AbstractCard __instance, @ByRef float[] currentWidth) {
/* 26 */       CNDoubleSpaceIssueFixPatch.currentWidthStore = currentWidth[0];
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = AbstractCard.class, method = "initializeDescriptionCN")
/*    */   public static class InsertAfter
/*    */   {
/*    */     @SpireInsertPatch(rloc = 15, localvars = {"currentWidth", "word", "sbuilder"})
/*    */     public static void Insert(AbstractCard __instance, @ByRef float[] currentWidth, @ByRef String[] word, @ByRef StringBuilder[] sbuilder) {
/* 40 */       if (sbuilder[0].length() != 0 && CNDoubleSpaceIssueFixPatch.currentWidthStore != -999.0F) currentWidth[0] = CNDoubleSpaceIssueFixPatch.currentWidthStore; 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\CNDoubleSpaceIssueFixPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */