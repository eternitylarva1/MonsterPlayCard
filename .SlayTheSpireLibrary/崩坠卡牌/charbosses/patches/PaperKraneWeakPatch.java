/*    */ package charbosses.patches;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaperKraneWeakPatch
/*    */ {
/*    */   @SpirePatch(clz = WeakPower.class, method = "atDamageGive")
/*    */   public static class SuperWeakEffect
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<Float> Prefix(WeakPower instance, float damage, DamageInfo.DamageType type) {
/* 23 */       if (type == DamageInfo.DamageType.NORMAL && instance.owner.isPlayer && AbstractCharBoss.boss != null && AbstractCharBoss.boss
/* 24 */         .hasRelic("Paper Crane")) {
/* 25 */         return SpireReturn.Return(Float.valueOf(damage * 0.6F));
/*    */       }
/*    */       
/* 28 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = WeakPower.class, method = "updateDescription")
/*    */   public static class SuperWeakText
/*    */   {
/*    */     @SpirePostfixPatch
/*    */     public static void Postfix(WeakPower instance) {
/* 39 */       if (instance.owner != null && instance.owner.isPlayer && AbstractCharBoss.boss != null && AbstractCharBoss.boss
/* 40 */         .hasRelic("Paper Crane"))
/* 41 */         if (instance.amount == 1) {
/* 42 */           instance.description = WeakPower.DESCRIPTIONS[0] + '(' + WeakPower.DESCRIPTIONS[1] + instance.amount + WeakPower.DESCRIPTIONS[2];
/*    */         } else {
/* 44 */           instance.description = WeakPower.DESCRIPTIONS[0] + '(' + WeakPower.DESCRIPTIONS[1] + instance.amount + WeakPower.DESCRIPTIONS[3];
/*    */         }  
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\patches\PaperKraneWeakPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */