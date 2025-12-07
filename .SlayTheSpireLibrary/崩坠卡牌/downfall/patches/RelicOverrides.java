/*     */ package downfall.patches;
/*     */ 
/*     */ import basemod.ReflectionHacks;
/*     */ import champ.ChampChar;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*     */ import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.relics.BustedCrown;
/*     */ import com.megacrit.cardcrawl.relics.Courier;
/*     */ import com.megacrit.cardcrawl.relics.Ectoplasm;
/*     */ import com.megacrit.cardcrawl.relics.MembershipCard;
/*     */ import com.megacrit.cardcrawl.relics.OldCoin;
/*     */ import com.megacrit.cardcrawl.relics.PrismaticShard;
/*     */ import downfall.downfallMod;
/*     */ import downfall.util.TextureLoader;
/*     */ import java.util.Objects;
/*     */ import javassist.CannotCompileException;
/*     */ import javassist.CtBehavior;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RelicOverrides
/*     */ {
/*     */   @SpirePatch(clz = BustedCrown.class, method = "getUpdatedDescription")
/*     */   public static class BustedCrownJokeText
/*     */   {
/*     */     @SpirePrefixPatch
/*     */     public static void Prefix(BustedCrown _instance) {
/*  93 */       if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == ChampChar.Enums.THE_CHAMP) {
/*  94 */         _instance.flavorText = (CardCrawlGame.languagePack.getRelicStrings("downfall:BustedCrownGagText")).FLAVOR;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = OldCoin.class, method = "getUpdatedDescription")
/*     */   public static class oldCoinName
/*     */   {
/*     */     @SpirePrefixPatch
/*     */     public static void Prefix(OldCoin _instance) {
/* 107 */       if (EvilModeCharacterSelect.evilMode && _instance.name != (CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[1]) {
/* 108 */         ReflectionHacks.setPrivateStaticFinal(OldCoin.class, "name", (CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[1]);
/* 109 */         _instance.img = TextureLoader.getTexture(downfallMod.assetPath("images/relics/oldCoinEvil.png"));
/* 110 */         _instance.outlineImg = TextureLoader.getTexture(downfallMod.assetPath("images/relics/Outline/oldCoinEvil.png"));
/* 111 */         _instance.flavorText = (CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[2];
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MembershipCard.class, method = "getUpdatedDescription")
/*     */   public static class membershipCardName
/*     */   {
/*     */     @SpirePrefixPatch
/*     */     public static void Prefix(MembershipCard _instance) {
/* 181 */       if (EvilModeCharacterSelect.evilMode && _instance.name != (CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[3]) {
/* 182 */         ReflectionHacks.setPrivateStaticFinal(MembershipCard.class, "name", (CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[3]);
/* 183 */         _instance.img = TextureLoader.getTexture(downfallMod.assetPath("images/relics/membershipCardEvil.png"));
/* 184 */         _instance.outlineImg = TextureLoader.getTexture(downfallMod.assetPath("images/relics/Outline/membershipCardEvil.png"));
/* 185 */         _instance.flavorText = (CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[4];
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = Courier.class, method = "getUpdatedDescription")
/*     */   public static class courierName
/*     */   {
/*     */     @SpirePrefixPatch
/*     */     public static void Prefix(Courier _instance) {
/* 200 */       if (EvilModeCharacterSelect.evilMode && _instance.name != (CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[6]) {
/* 201 */         ReflectionHacks.setPrivateStaticFinal(Courier.class, "name", (CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[6]);
/* 202 */         _instance.imgUrl = null;
/* 203 */         _instance.img = TextureLoader.getTexture(downfallMod.assetPath("images/relics/courierEvil.png"));
/* 204 */         _instance.outlineImg = TextureLoader.getTexture(downfallMod.assetPath("images/relics/Outline/courierEvil.png"));
/* 205 */         _instance.flavorText = (CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[5];
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = PrismaticShard.class, method = "getUpdatedDescription")
/*     */   public static class prismaticDesc
/*     */   {
/*     */     @SpirePrefixPatch
/*     */     public static void Postfix(PrismaticShard _instance) {
/* 219 */       if (EvilModeCharacterSelect.evilMode);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = Ectoplasm.class, method = "getUpdatedDescription")
/*     */   public static class EctoImage
/*     */   {
/*     */     @SpirePrefixPatch
/*     */     public static void Prefix(Ectoplasm _instance) {
/* 235 */       if (EvilModeCharacterSelect.evilMode) {
/* 236 */         _instance.imgUrl = null;
/* 237 */         ReflectionHacks.setPrivateStaticFinal(Ectoplasm.class, "name", (CardCrawlGame.languagePack.getRelicStrings("downfall:Hecktoplasm")).DESCRIPTIONS[1]);
/* 238 */         _instance.img = TextureLoader.getTexture(downfallMod.assetPath("images/relics/ectoplasmEvil.png"));
/* 239 */         _instance.outlineImg = TextureLoader.getTexture(downfallMod.assetPath("images/relics/Outline/ectoplasmEvil.png"));
/* 240 */         _instance.flavorText = (CardCrawlGame.languagePack.getRelicStrings("downfall:Hecktoplasm")).FLAVOR;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = Ectoplasm.class, method = "setDescription")
/*     */   public static class EctoDesc
/*     */   {
/*     */     @SpirePrefixPatch
/*     */     public static SpireReturn<String> Prefix() {
/* 254 */       if (EvilModeCharacterSelect.evilMode) {
/* 255 */         return SpireReturn.Return((CardCrawlGame.languagePack.getRelicStrings("downfall:replacements")).DESCRIPTIONS[9]);
/*     */       }
/*     */       
/* 258 */       return SpireReturn.Continue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = AbstractRelic.class, method = "<ctor>")
/*     */   public static class EctoTitle
/*     */   {
/*     */     @SpireInsertPatch(locator = Locator.class)
/*     */     public static void Insert(AbstractRelic __instance, String setId, String imgName, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound sfx) {
/* 271 */       if (Objects.equals(__instance.relicId, "Ectoplasm") && EvilModeCharacterSelect.evilMode)
/* 272 */         ReflectionHacks.setPrivateFinal(__instance, AbstractRelic.class, "relicStrings", (CardCrawlGame.languagePack.getRelicStrings("downfall:Hecktoplasm")).NAME); 
/*     */     }
/*     */     
/*     */     private static class Locator
/*     */       extends SpireInsertLocator
/*     */     {
/*     */       public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
/* 279 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(ImageMaster.class, "loadRelicImg");
/* 280 */         return LineFinder.findInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\RelicOverrides.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */