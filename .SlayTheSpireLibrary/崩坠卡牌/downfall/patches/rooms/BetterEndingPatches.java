/*     */ package downfall.patches.rooms;
/*     */ import basemod.ReflectionHacks;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*     */ import com.megacrit.cardcrawl.characters.AnimatedNpc;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.beyond.SpireHeart;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import downfall.patches.EvilModeCharacterSelect;
/*     */ import downfall.patches.ui.campfire.AddBustKeyButtonPatches;
/*     */ import javassist.CtBehavior;
/*     */ 
/*     */ public class BetterEndingPatches {
/*     */   @SpirePatch(clz = SpireHeart.class, method = "<ctor>")
/*     */   public static class StringChanges {
/*     */     @SpirePrefixPatch
/*     */     public static void patch(SpireHeart __instance) {
/*  23 */       if (EvilModeCharacterSelect.evilMode) {
/*  24 */         ReflectionHacks.setPrivateStaticFinal(SpireHeart.class, "eventStrings", CardCrawlGame.languagePack.getEventString("downfall:BetterEnding"));
/*     */       } else {
/*  26 */         ReflectionHacks.setPrivateStaticFinal(SpireHeart.class, "eventStrings", CardCrawlGame.languagePack.getEventString("Spire Heart"));
/*     */       } 
/*     */       
/*  29 */       EventStrings tmp = (EventStrings)ReflectionHacks.getPrivateStatic(SpireHeart.class, "eventStrings");
/*     */       int i;
/*  31 */       for (i = 0; i < SpireHeart.DESCRIPTIONS.length; i++) {
/*  32 */         SpireHeart.DESCRIPTIONS[i] = tmp.DESCRIPTIONS[i];
/*     */       }
/*     */       
/*  35 */       for (i = 0; i < SpireHeart.OPTIONS.length; i++) {
/*  36 */         SpireHeart.OPTIONS[i] = tmp.OPTIONS[i];
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = SpireHeart.class, method = "buttonEffect")
/*     */   public static class MoveToAct4
/*     */   {
/*     */     @SpirePrefixPatch
/*     */     public static SpireReturn<Void> Prefix(SpireHeart __instance) {
/*  47 */       if (EvilModeCharacterSelect.evilMode) {
/*  48 */         if (AbstractDungeon.actNum == 3 && ((Boolean)AddBustKeyButtonPatches.KeyFields.bustedRuby
/*  49 */           .get(AbstractDungeon.player)).booleanValue() && ((Boolean)AddBustKeyButtonPatches.KeyFields.bustedSapphire
/*  50 */           .get(AbstractDungeon.player)).booleanValue() && ((Boolean)AddBustKeyButtonPatches.KeyFields.bustedEmerald
/*  51 */           .get(AbstractDungeon.player)).booleanValue())
/*     */         {
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
/*  68 */           return SpireReturn.Continue();
/*     */         }
/*  70 */         Settings.hasRubyKey = false;
/*  71 */         Settings.hasEmeraldKey = false;
/*  72 */         Settings.hasSapphireKey = false;
/*     */         
/*  74 */         return SpireReturn.Continue();
/*     */       } 
/*  76 */       return SpireReturn.Continue();
/*     */     }
/*     */ 
/*     */     
/*     */     @SpirePatch(clz = SpireHeart.class, method = "buttonEffect")
/*     */     public static class BetterEffect
/*     */     {
/*     */       @SpireInsertPatch(locator = Locator.class)
/*     */       public static SpireReturn<Void> Insert(SpireHeart __instance, int buttonPressed) {
/*  85 */         if (EvilModeCharacterSelect.evilMode) {
/*  86 */           AnimatedNpc heart = (AnimatedNpc)ReflectionHacks.getPrivate(__instance, SpireHeart.class, "npc");
/*  87 */           AbstractDungeon.effectsQueue.add(new SoulStealEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, heart.skeleton.getX(), heart.skeleton.getY() + 300.0F * Settings.scale));
/*     */           
/*  89 */           return SpireReturn.Return(null);
/*     */         } 
/*     */         
/*  92 */         return SpireReturn.Continue();
/*     */       }
/*     */ 
/*     */       
/*     */       @SpireInsertPatch(locator = Locator2.class)
/*     */       public static void patch(SpireHeart __instance, int buttonPressed) {
/*  98 */         if (EvilModeCharacterSelect.evilMode)
/*  99 */           __instance.roomEventText.updateBodyText((CardCrawlGame.languagePack.getEventString("downfall:BetterEnding")).DESCRIPTIONS[16]); 
/*     */       }
/*     */       
/*     */       public static class Locator
/*     */         extends SpireInsertLocator
/*     */       {
/*     */         public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 106 */           Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "effectList");
/* 107 */           return LineFinder.findInOrder(ctBehavior, (Matcher)fieldAccessMatcher);
/*     */         }
/*     */       }
/*     */       
/*     */       public static class Locator2
/*     */         extends SpireInsertLocator {
/*     */         public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 114 */           Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(RoomEventDialog.class, "updateDialogOption");
/* 115 */           return LineFinder.findInOrder(ctBehavior, (Matcher)methodCallMatcher);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\rooms\BetterEndingPatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */