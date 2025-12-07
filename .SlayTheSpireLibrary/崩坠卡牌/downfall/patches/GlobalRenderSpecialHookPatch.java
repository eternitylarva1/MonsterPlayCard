/*    */ package downfall.patches;
/*    */ 
/*    */ import automaton.EasyInfoDisplayPanel;
/*    */ import automaton.SuperTip;
/*    */ import champ.StanceHelper;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import theHexaghost.HexaMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = AbstractPlayer.class, method = "render", paramtypez = {SpriteBatch.class})
/*    */ public class GlobalRenderSpecialHookPatch
/*    */ {
/*    */   @SpirePostfixPatch
/*    */   public static void Postfix(AbstractPlayer __instance, SpriteBatch sb) {
/* 21 */     if (HexaMod.renderFlames) {
/* 22 */       HexaMod.renderGhostflames(sb);
/*    */     }
/* 24 */     SuperTip.render(sb, EasyInfoDisplayPanel.RENDER_TIMING.TIMING_PLAYER_RENDER);
/* 25 */     StanceHelper.render(sb);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\GlobalRenderSpecialHookPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */