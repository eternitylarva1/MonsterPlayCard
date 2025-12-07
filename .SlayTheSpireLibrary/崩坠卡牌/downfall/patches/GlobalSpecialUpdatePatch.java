/*    */ package downfall.patches;
/*    */ 
/*    */ import automaton.FunctionHelper;
/*    */ import champ.StanceHelper;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ import theHexaghost.GhostflameHelper;
/*    */ import theHexaghost.HexaMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = EnergyPanel.class, method = "update")
/*    */ public class GlobalSpecialUpdatePatch
/*    */ {
/*    */   public static void Prefix(EnergyPanel __instance) {
/* 18 */     if (HexaMod.renderFlames) {
/* 19 */       GhostflameHelper.update();
/*    */     }
/* 21 */     if (FunctionHelper.doStuff) {
/* 22 */       FunctionHelper.update();
/*    */     }
/* 24 */     if (StanceHelper.hitboxStance == null) StanceHelper.init(); 
/* 25 */     StanceHelper.update();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\GlobalSpecialUpdatePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */