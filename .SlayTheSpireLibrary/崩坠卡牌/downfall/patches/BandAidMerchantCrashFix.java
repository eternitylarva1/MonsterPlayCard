/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BandAidMerchantCrashFix
/*    */ {
/*    */   @SpirePatch(clz = AbstractCard.class, method = "cardPlayable")
/*    */   public static class RelicPatch
/*    */   {
/*    */     public static SpireReturn Prefix(AbstractCard __instance) {
/* 17 */       if (AbstractDungeon.getMonsters() == null) {
/* 18 */         return SpireReturn.Return(Boolean.valueOf(false));
/*    */       }
/* 20 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\BandAidMerchantCrashFix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */