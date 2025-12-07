/*    */ package downfall.patches;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.shop.ShopScreen;
/*    */ import expansioncontent.patches.CardColorEnumPatch;
/*    */ import expansioncontent.patches.ShopBossPatch;
/*    */ import java.lang.reflect.Method;
/*    */ import javassist.CtBehavior;
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = ShopScreen.class, method = "purchaseCard")
/*    */ public class CourierFixCrashPatch
/*    */ {
/*    */   @SpireInsertPatch(locator = Locator.class, localvars = {"c"})
/*    */   public static SpireReturn replaceCrashWithGood(ShopScreen __instance, AbstractCard hoveredCard, AbstractCard c) {
/* 28 */     if (hoveredCard.color == CardColorEnumPatch.CardColorPatch.BOSS) {
/* 29 */       c = ShopBossPatch.getReplacement(hoveredCard.rarity);
/* 30 */       for (AbstractRelic r : AbstractDungeon.player.relics)
/* 31 */         r.onPreviewObtainCard(c); 
/* 32 */       c.current_x = hoveredCard.current_x;
/* 33 */       c.current_y = hoveredCard.current_y;
/* 34 */       c.target_x = c.current_x;
/* 35 */       c.target_y = c.current_y;
/*    */       try {
/* 37 */         Method m = ShopScreen.class.getDeclaredMethod("setPrice", new Class[] { AbstractCard.class });
/* 38 */         m.setAccessible(true);
/* 39 */         m.invoke(__instance, new Object[] { c });
/* 40 */       } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException|NoSuchMethodException e) {
/* 41 */         e.printStackTrace();
/*    */       } 
/* 43 */       __instance.colorlessCards.set(__instance.colorlessCards.indexOf(hoveredCard), c);
/* 44 */       ReflectionHacks.setPrivate(__instance, ShopScreen.class, "hoveredCard", null);
/* 45 */       InputHelper.justClickedLeft = false;
/* 46 */       ReflectionHacks.setPrivate(__instance, ShopScreen.class, "notHoveredTimer", Float.valueOf(1.0F));
/* 47 */       ReflectionHacks.setPrivate(__instance, ShopScreen.class, "speechTimer", Float.valueOf(MathUtils.random(40.0F, 60.0F)));
/* 48 */       __instance.playBuySfx();
/* 49 */       __instance.createSpeech(ShopScreen.getBuyMsg());
/* 50 */       return SpireReturn.Return(null);
/*    */     } 
/* 52 */     return SpireReturn.Continue();
/*    */   }
/*    */   
/*    */   private static class Locator
/*    */     extends SpireInsertLocator {
/*    */     public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 58 */       Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
/* 59 */       return new int[] { LineFinder.findAllInOrder(ctMethodToPatch, (Matcher)fieldAccessMatcher)[1] };
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\CourierFixCrashPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */