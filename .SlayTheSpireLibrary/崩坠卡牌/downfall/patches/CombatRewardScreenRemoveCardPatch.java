/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.screens.CombatRewardScreen;
/*    */ import downfall.monsters.FleeingMerchant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
/*    */ public class CombatRewardScreenRemoveCardPatch
/*    */ {
/*    */   public static void Postfix(CombatRewardScreen __instance) {
/* 16 */     if (AbstractDungeon.getCurrRoom() instanceof downfall.rooms.HeartShopRoom && (FleeingMerchant.helpEscaped || FleeingMerchant.helpDied)) {
/* 17 */       FleeingMerchant.helpEscaped = false;
/* 18 */       FleeingMerchant.helpDied = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\CombatRewardScreenRemoveCardPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */