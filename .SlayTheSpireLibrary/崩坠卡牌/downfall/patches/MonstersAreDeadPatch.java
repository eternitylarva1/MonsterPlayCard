/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.monsters.MonsterGroup;
/*    */ import downfall.powers.FairyPotionPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MonstersAreDeadPatch
/*    */ {
/*    */   @SpirePatch(clz = MonsterGroup.class, method = "areMonstersBasicallyDead")
/*    */   public static class CurrMapNodeSteal
/*    */   {
/*    */     public static boolean Postfix(boolean __result, MonsterGroup __instance) {
/* 15 */       if (FairyPotionPower.CANNOT_END)
/* 16 */         return false; 
/* 17 */       return __result;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\MonstersAreDeadPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */