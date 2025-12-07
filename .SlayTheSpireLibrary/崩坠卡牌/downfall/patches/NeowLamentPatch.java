/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
/*    */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ public class NeowLamentPatch
/*    */ {
/*    */   @SpirePatch(clz = SaveAndContinue.class, method = "save")
/*    */   public static class SaveGame
/*    */   {
/*    */     public static void Prefix(SaveFile save) {
/* 15 */       downfallMod.saveData();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\NeowLamentPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */