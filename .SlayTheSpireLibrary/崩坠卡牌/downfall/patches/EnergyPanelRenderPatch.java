/*    */ package downfall.patches;
/*    */ 
/*    */ import automaton.EasyInfoDisplayPanel;
/*    */ import automaton.FunctionHelper;
/*    */ import automaton.SuperTip;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = EnergyPanel.class, method = "render", paramtypes = {"com.badlogic.gdx.graphics.g2d.SpriteBatch"})
/*    */ public class EnergyPanelRenderPatch
/*    */ {
/*    */   public static void Prefix(EnergyPanel __instance, SpriteBatch sb) {
/* 20 */     if (FunctionHelper.doStuff) {
/* 21 */       FunctionHelper.render(sb);
/*    */     }
/* 23 */     SuperTip.render(sb, EasyInfoDisplayPanel.RENDER_TIMING.TIMING_ENERGYPANEL_RENDER);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\EnergyPanelRenderPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */