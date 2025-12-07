/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BanCards
/*    */ {
/*    */   @SpirePatch(clz = AbstractDungeon.class, method = "initializeCardPools")
/*    */   public static class CardPatch
/*    */   {
/*    */     public static void Postfix(AbstractDungeon __instance) {
/* 16 */       if (EvilModeCharacterSelect.evilMode) {
/* 17 */         AbstractDungeon.colorlessCardPool.group.removeIf(c -> c instanceof com.megacrit.cardcrawl.cards.colorless.SadisticNature);
/* 18 */         AbstractDungeon.srcColorlessCardPool.group.removeIf(c -> c instanceof com.megacrit.cardcrawl.cards.colorless.SadisticNature);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\BanCards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */