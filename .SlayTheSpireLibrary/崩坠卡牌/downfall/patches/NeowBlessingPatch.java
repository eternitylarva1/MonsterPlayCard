/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatches;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.potions.AbstractPotion;
/*    */ import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;
/*    */ import com.megacrit.cardcrawl.trials.CustomTrial;
/*    */ import com.megacrit.cardcrawl.ui.panels.TopPanel;
/*    */ import downfall.dailymods.ExchangeController;
/*    */ import downfall.relics.NeowBlessing;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NeowBlessingPatch
/*    */ {
/*    */   @SpirePatch(clz = CustomModeScreen.class, method = "addNonDailyMods")
/*    */   public static class CustomModeScreenPatch
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<Void> Prefix(CustomModeScreen _instance, CustomTrial trial, ArrayList<String> modIds) {
/* 28 */       for (String modId : modIds) {
/* 29 */         if (modId.equals(ExchangeController.ID)) {
/* 30 */           trial.setShouldKeepStarterRelic(false);
/*    */         }
/*    */       } 
/*    */       
/* 34 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatches({@SpirePatch(clz = TopPanel.class, method = "renderPotions"), @SpirePatch(clz = TopPanel.class, method = "updatePotions")})
/*    */   public static class PotionPatch
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<Void> Prefix(TopPanel _instance) {
/* 53 */       if (AbstractDungeon.player.hasRelic(NeowBlessing.ID)) {
/* 54 */         for (AbstractPotion p : AbstractDungeon.player.potions) {
/* 55 */           if (!(p instanceof com.megacrit.cardcrawl.potions.PotionSlot)) {
/* 56 */             AbstractDungeon.topPanel.destroyPotion(p.slot);
/*    */           }
/*    */         } 
/* 59 */         return SpireReturn.Return(null);
/*    */       } 
/*    */       
/* 62 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = ObtainPotionAction.class, method = "update")
/*    */   public static class ObtainPotionPatch
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<Void> Prefix(ObtainPotionAction _instance) {
/* 73 */       if (AbstractDungeon.player.hasRelic(NeowBlessing.ID)) {
/* 74 */         AbstractDungeon.player.getRelic(NeowBlessing.ID).flash();
/* 75 */         _instance.isDone = true;
/*    */       } 
/* 77 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\NeowBlessingPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */