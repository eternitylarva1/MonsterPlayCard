/*    */ package downfall.patches.vfx;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.rooms.EmptyRoom;
/*    */ import com.megacrit.cardcrawl.screens.CombatRewardScreen;
/*    */ import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
/*    */ import downfall.util.ThirdSealReward;
/*    */ import guardian.rewards.GemReward;
/*    */ import javassist.CtBehavior;
/*    */ import sneckomod.util.UpgradedUnknownReward;
/*    */ import theHexaghost.util.SealSealReward;
/*    */ 
/*    */ public class DownfallCustomCardRewardNormalFunctionPatch {
/*    */   @SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
/*    */   public static class GenerateCardsFromRewardPoolPatch {
/*    */     @SpireInsertPatch(locator = Locator.class)
/*    */     public static void generate_cards(CombatRewardScreen __instance) {
/* 24 */       for (int i = 0; i < __instance.rewards.size(); i++) {
/* 25 */         RewardItem rewardItem = __instance.rewards.get(i);
/*    */         
/* 27 */         if (rewardItem instanceof ThirdSealReward) {
/* 28 */           ThirdSealReward reward = (ThirdSealReward)rewardItem;
/* 29 */           AbstractRoom temp = AbstractDungeon.getCurrRoom();
/* 30 */           if (temp instanceof com.megacrit.cardcrawl.rooms.MonsterRoomBoss) {
/* 31 */             AbstractDungeon.currMapNode.setRoom((AbstractRoom)new EmptyRoom());
/* 32 */             reward.generate_reward_cards();
/* 33 */             AbstractDungeon.currMapNode.setRoom(temp);
/*    */           } else {
/* 35 */             reward.generate_reward_cards();
/*    */           } 
/* 37 */         } else if (rewardItem instanceof SealSealReward) {
/* 38 */           SealSealReward reward = (SealSealReward)rewardItem;
/* 39 */           reward.generate_reward_cards();
/*    */         }
/* 41 */         else if (rewardItem instanceof GemReward) {
/* 42 */           GemReward reward = (GemReward)rewardItem;
/* 43 */           reward.generate_reward_cards();
/* 44 */         } else if (rewardItem instanceof UpgradedUnknownReward) {
/* 45 */           UpgradedUnknownReward reward = (UpgradedUnknownReward)rewardItem;
/* 46 */           reward.generate_reward_cards();
/*    */         } 
/*    */       } 
/* 49 */       AbstractDungeon.combatRewardScreen.positionRewards();
/*    */     }
/*    */     
/*    */     private static class Locator
/*    */       extends SpireInsertLocator {
/*    */       public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 55 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ProceedButton.class, "show");
/* 56 */         return LineFinder.findInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\vfx\DownfallCustomCardRewardNormalFunctionPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */