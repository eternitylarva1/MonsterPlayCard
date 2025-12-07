/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.PotionHelper;
/*    */ import gremlin.patches.GremlinEnum;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BanStuffPatch
/*    */ {
/*    */   @SpirePatch(clz = PotionHelper.class, method = "initialize")
/*    */   public static class PotionPatch
/*    */   {
/*    */     public static void Postfix(AbstractPlayer.PlayerClass chosenClass) {
/* 21 */       PotionHelper.potions.remove("downfall:CursedFountainPotion");
/*    */       
/* 23 */       if (chosenClass == GremlinEnum.GREMLIN) {
/* 24 */         AbstractDungeon.relicsToRemoveOnStart.add("Prismatic Shard");
/*    */       }
/*    */       
/* 27 */       if (EvilModeCharacterSelect.evilMode || chosenClass == hermit.Enums.HERMIT) {
/* 28 */         AbstractDungeon.relicsToRemoveOnStart.add("Blue Candle");
/*    */       }
/*    */       
/* 31 */       if (EvilModeCharacterSelect.evilMode)
/* 32 */         AbstractDungeon.relicsToRemoveOnStart.add("Ectoplasm"); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\BanStuffPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */