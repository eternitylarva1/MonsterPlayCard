/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ import com.megacrit.cardcrawl.screens.CombatRewardScreen;
/*    */ import downfall.dailymods.TransformRewards;
/*    */ import downfall.util.TransformCardReward;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
/*    */ public class TransformRewardsPatch
/*    */ {
/*    */   public static void Postfix(CombatRewardScreen __instance) {
/* 19 */     if ((CardCrawlGame.trial != null && CardCrawlGame.trial.dailyModIDs().contains(TransformRewards.ID)) || ModHelper.isModEnabled(TransformRewards.ID)) {
/* 20 */       ArrayList<RewardItem> rewardsToRemove = new ArrayList<>();
/* 21 */       ArrayList<RewardItem> rewardsToAdd = new ArrayList<>();
/* 22 */       for (RewardItem reward : __instance.rewards) {
/* 23 */         if (reward.type == RewardItem.RewardType.CARD) {
/* 24 */           rewardsToRemove.add(reward);
/* 25 */           rewardsToAdd.add(new TransformCardReward());
/*    */         } 
/*    */       } 
/* 28 */       __instance.rewards.removeAll(rewardsToRemove);
/* 29 */       __instance.rewards.addAll(rewardsToAdd);
/* 30 */       __instance.positionRewards();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\TransformRewardsPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */