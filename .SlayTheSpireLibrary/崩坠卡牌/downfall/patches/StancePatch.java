/*    */ package downfall.patches;
/*    */ 
/*    */ import champ.stances.BerserkerStance;
/*    */ import champ.stances.DefensiveStance;
/*    */ import champ.stances.UltimateStance;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.stances.AbstractStance;
/*    */ import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
/*    */ import guardian.stances.DefensiveMode;
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ 
/*    */ public class StancePatch
/*    */ {
/*    */   @SpirePatch(clz = AbstractStance.class, method = "getStanceFromName")
/*    */   public static class DefensiveMode_StancePatch
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<AbstractStance> returnStance(String name) {
/* 25 */       if (name.equals("guardianmod:DefensiveMode")) {
/* 26 */         return SpireReturn.Return(new DefensiveMode());
/*    */       }
/* 28 */       if (name.equals("champ:DefensiveStance")) {
/* 29 */         return SpireReturn.Return(new DefensiveStance());
/*    */       }
/* 31 */       if (name.equals("champ:BerserkerStance")) {
/* 32 */         return SpireReturn.Return(new BerserkerStance());
/*    */       }
/* 34 */       if (name.equals("champ:UltimateStance")) {
/* 35 */         return SpireReturn.Return(new UltimateStance());
/*    */       }
/* 37 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = StanceAuraEffect.class, method = "<ctor>", paramtypez = {String.class})
/*    */   public static class StanceAuraEffectPatch
/*    */   {
/*    */     @SpirePostfixPatch
/*    */     public static SpireReturn<Void> Postfix(StanceAuraEffect _instance, String stanceId) {
/* 49 */       if (stanceId.equals("DefensiveMode")) {
/*    */         try {
/* 51 */           Field colorField = _instance.getClass().getSuperclass().getDeclaredField("color");
/* 52 */           colorField.setAccessible(true);
/* 53 */           colorField.set(_instance, new Color(MathUtils.random(0.6F, 0.7F), MathUtils.random(0.6F, 0.7F), MathUtils.random(0.5F, 0.55F), 0.0F));
/* 54 */         } catch (Exception e) {
/* 55 */           e.printStackTrace();
/*    */         } 
/*    */       }
/*    */       
/* 59 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\StancePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */