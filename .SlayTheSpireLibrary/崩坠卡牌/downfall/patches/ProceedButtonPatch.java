/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
/*    */ import downfall.monsters.FleeingMerchant;
/*    */ import downfall.rooms.HeartShopRoom;
/*    */ import java.util.ArrayList;
/*    */ import javassist.CtBehavior;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = ProceedButton.class, method = "update")
/*    */ public class ProceedButtonPatch
/*    */ {
/*    */   @SpireInsertPatch(locator = Locator.class)
/*    */   public static SpireReturn Insert(ProceedButton __instance) {
/* 26 */     AbstractRoom r = AbstractDungeon.getCurrRoom();
/* 27 */     if (r instanceof HeartShopRoom && ((
/* 28 */       ((HeartShopRoom)r).startedCombat && FleeingMerchant.DEAD) || FleeingMerchant.ESCAPED)) {
/* 29 */       HeartShopRoom heartShopRoom = new HeartShopRoom(false);
/* 30 */       ((AbstractRoom)heartShopRoom).rewards.clear();
/* 31 */       AbstractDungeon.combatRewardScreen.clear();
/* 32 */       AbstractDungeon.currMapNode.setRoom((AbstractRoom)heartShopRoom);
/* 33 */       (AbstractDungeon.getCurrRoom()).rewards.clear();
/* 34 */       AbstractDungeon.scene.nextRoom((AbstractRoom)heartShopRoom);
/* 35 */       CardCrawlGame.fadeIn(1.5F);
/* 36 */       AbstractDungeon.rs = AbstractDungeon.RenderScene.NORMAL;
/* 37 */       heartShopRoom.onPlayerEntry();
/* 38 */       AbstractDungeon.closeCurrentScreen();
/* 39 */       return SpireReturn.Return(null);
/*    */     } 
/*    */     
/* 42 */     return SpireReturn.Continue();
/*    */   }
/*    */   
/*    */   private static class Locator
/*    */     extends SpireInsertLocator {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 48 */       Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "closeCurrentScreen");
/* 49 */       return new int[] { LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList(), (Matcher)methodCallMatcher)[2] };
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\ProceedButtonPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */