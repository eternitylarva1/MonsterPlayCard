/*    */ package charbosses.patches;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.bosses.Silent.CharBossSilent;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.vfx.combat.PowerExpireTextEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DontRenderThatPatch
/*    */ {
/*    */   @SpirePatch(clz = PowerExpireTextEffect.class, method = "render")
/*    */   public static class Noooooo
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn Prefix() {
/* 20 */       if (AbstractCharBoss.boss != null && 
/* 21 */         AbstractCharBoss.boss instanceof CharBossSilent && 
/* 22 */         !((CharBossSilent)AbstractCharBoss.boss).foggy) {
/* 23 */         return SpireReturn.Return(null);
/*    */       }
/*    */ 
/*    */       
/* 27 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\patches\DontRenderThatPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */