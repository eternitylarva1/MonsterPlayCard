/*    */ package downfall.patches;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.BitmapFont;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import javassist.CtBehavior;
/*    */ import javassist.CtClass;
/*    */ import javassist.CtMethod;
/*    */ 
/*    */ 
/*    */ public class UnflipUI
/*    */ {
/*    */   public static float flipPos(SpriteBatch sb, float x) {
/* 19 */     if (FlipRoom.isFlipped()) {
/* 20 */       FlipRoom.pauseFlip(sb);
/* 21 */       x = Gdx.graphics.getWidth() - x;
/*    */     } 
/* 23 */     return x;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = AbstractCreature.class, method = "renderHealth")
/*    */   public static class HealthBar
/*    */   {
/*    */     public static void Prefix(AbstractCreature __instance, SpriteBatch sb) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public static void Postfix(AbstractCreature __instance, SpriteBatch sb) {}
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = FontHelper.class, method = "renderFontLeftTopAligned")
/*    */   public static class TextDamageIntent
/*    */   {
/*    */     public static SpireReturn<Void> Prefix(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/* 52 */       if (FlipRoom.isFlipped()) {
/* 53 */         FlipRoom.pauseFlip(sb);
/*    */         
/* 55 */         x = Gdx.graphics.getWidth() - x;
/* 56 */         FontHelper.renderFontRightTopAligned(sb, font, msg, x, y, c);
/*    */         
/* 58 */         return SpireReturn.Return(null);
/*    */       } 
/* 60 */       return SpireReturn.Continue();
/*    */     }
/*    */     
/*    */     public static void Postfix(SpriteBatch sb, BitmapFont font, String msg, float x, float y, Color c) {
/* 64 */       FlipRoom.unpauseFlip(sb);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = FontHelper.class, method = "<staticinit>")
/*    */   public static class CenteredText
/*    */   {
/*    */     public static void Raw(CtBehavior ctBehavior) throws Exception {
/* 75 */       CtClass ctClass = ctBehavior.getDeclaringClass();
/*    */       
/* 77 */       for (CtMethod ctMethod : ctClass.getDeclaredMethods()) {
/* 78 */         if (ctMethod.getName().startsWith("renderFontCentered")) {
/* 79 */           ctMethod.insertBefore("x = " + UnflipUI.class.getName() + ".flipPos(sb, x);");
/* 80 */           ctMethod.insertAfter(FlipRoom.class.getName() + ".unpauseFlip(sb);");
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\UnflipUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */