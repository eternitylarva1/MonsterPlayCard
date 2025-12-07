/*    */ package downfall.patches;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.shop.ShopScreen;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HeartShopUIPatches
/*    */ {
/*    */   @SpirePatch(clz = ShopScreen.class, method = "init", paramtypez = {ArrayList.class, ArrayList.class})
/*    */   public static class HeartShopUIPatch
/*    */   {
/*    */     @SpirePostfixPatch
/*    */     public static void Postfix(ShopScreen __result, ArrayList<AbstractCard> coloredCards, ArrayList<AbstractCard> colorlessCards) {
/* 24 */       if (EvilModeCharacterSelect.evilMode) {
/* 25 */         ReflectionHacks.setPrivate(__result, ShopScreen.class, "rugImg", TextureLoader.getTexture(downfallMod.assetPath("images/ui/heartRug.png")));
/* 26 */         ReflectionHacks.setPrivate(__result, ShopScreen.class, "handImg", TextureLoader.getTexture(downfallMod.assetPath("images/ui/heartHand.png")));
/*    */ 
/*    */         
/* 29 */         ReflectionHacks.setPrivateStaticFinal(ShopScreen.class, "characterStrings", CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("HeartMerchantScreen")));
/* 30 */         ReflectionHacks.setPrivateStaticFinal(ShopScreen.class, "WELCOME_MSG", (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("HeartMerchantScreen"))).NAMES[0]);
/* 31 */         ReflectionHacks.setPrivateStaticFinal(ShopScreen.class, "NAMES", (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("HeartMerchantScreen"))).NAMES);
/* 32 */         ReflectionHacks.setPrivateStaticFinal(ShopScreen.class, "TEXT", (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("HeartMerchantScreen"))).TEXT);
/*    */       }
/*    */       else {
/*    */         
/* 36 */         ReflectionHacks.setPrivateStaticFinal(ShopScreen.class, "characterStrings", CardCrawlGame.languagePack.getCharacterString("Shop Screen"));
/* 37 */         ReflectionHacks.setPrivateStaticFinal(ShopScreen.class, "WELCOME_MSG", (CardCrawlGame.languagePack.getCharacterString("Shop Screen")).NAMES[0]);
/* 38 */         ReflectionHacks.setPrivateStaticFinal(ShopScreen.class, "NAMES", (CardCrawlGame.languagePack.getCharacterString("Shop Screen")).NAMES);
/* 39 */         ReflectionHacks.setPrivateStaticFinal(ShopScreen.class, "TEXT", (CardCrawlGame.languagePack.getCharacterString("Shop Screen")).TEXT);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = ShopScreen.class, method = "playCantBuySfx")
/*    */   public static class DisableAudio1
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<Void> Prefix() {
/* 52 */       if (EvilModeCharacterSelect.evilMode) {
/* 53 */         return SpireReturn.Return(null);
/*    */       }
/* 55 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */   
/*    */   @SpirePatch(clz = ShopScreen.class, method = "playBuySfx")
/*    */   public static class DisableAudio2
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<Void> Prefix() {
/* 64 */       if (EvilModeCharacterSelect.evilMode) {
/* 65 */         return SpireReturn.Return(null);
/*    */       }
/* 67 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */   
/*    */   @SpirePatch(clz = ShopScreen.class, method = "playMiscSfx")
/*    */   public static class DisableAudio3
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<Void> Prefix() {
/* 76 */       if (EvilModeCharacterSelect.evilMode) {
/* 77 */         return SpireReturn.Return(null);
/*    */       }
/* 79 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */   
/*    */   @SpirePatch(clz = ShopScreen.class, method = "welcomeSfx")
/*    */   public static class DisableAudio4
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<Void> Prefix() {
/* 88 */       if (EvilModeCharacterSelect.evilMode) {
/* 89 */         return SpireReturn.Return(null);
/*    */       }
/* 91 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\HeartShopUIPatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */