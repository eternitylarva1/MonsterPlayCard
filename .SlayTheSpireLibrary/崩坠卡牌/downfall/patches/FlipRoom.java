/*     */ package downfall.patches;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.OrthographicCamera;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Matrix4;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.megacrit.cardcrawl.characters.AnimatedNpc;
/*     */ import com.megacrit.cardcrawl.core.OverlayMenu;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.shop.Merchant;
/*     */ import javassist.CtBehavior;
/*     */ 
/*     */ public class FlipRoom {
/*  21 */   private static OrthographicCamera camera = null; private static boolean isFlipped = false;
/*  22 */   private static Matrix4 oldProjectionMatrix = null;
/*     */   
/*     */   public static boolean isFlipped() {
/*  25 */     return isFlipped;
/*     */   }
/*     */   
/*     */   private static void initFlip() {
/*  29 */     if (camera == null) {
/*  30 */       camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
/*  31 */       if (Settings.VERT_LETTERBOX_AMT != 0 || Settings.HORIZ_LETTERBOX_AMT != 0) {
/*  32 */         camera.position.set(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, 0.0F);
/*     */       } else {
/*  34 */         camera.position.set(camera.viewportWidth / 2.0F, camera.viewportHeight / 2.0F, 0.0F);
/*     */       } 
/*  36 */       camera.rotate(camera.up, 180.0F);
/*  37 */       camera.update();
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
/*     */   public static void beginFlip(SpriteBatch sb) {}
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
/*     */   public static void endFlip(SpriteBatch sb) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void pauseFlip(SpriteBatch sb) {
/*  72 */     if (isFlipped) {
/*  73 */       endFlip(sb);
/*  74 */       isFlipped = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void unpauseFlip(SpriteBatch sb) {
/*  79 */     if (isFlipped) {
/*  80 */       beginFlip(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = AbstractRoom.class, method = "render")
/*     */   public static class Room
/*     */   {
/*     */     public static void Prefix(AbstractRoom __instance, SpriteBatch sb) {
/*  90 */       FlipRoom.beginFlip(sb);
/*     */     }
/*     */     
/*     */     public static void Postfix(AbstractRoom __instance, SpriteBatch sb) {
/*  94 */       FlipRoom.endFlip(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = AnimatedNpc.class, method = "render", paramtypez = {SpriteBatch.class})
/*     */   public static class NPC1
/*     */   {
/*     */     public static void Prefix(AnimatedNpc __instance, SpriteBatch sb) {
/* 105 */       FlipRoom.beginFlip(sb);
/*     */     }
/*     */     
/*     */     public static void Postfix(AnimatedNpc __instance, SpriteBatch sb) {
/* 109 */       FlipRoom.endFlip(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = AnimatedNpc.class, method = "render", paramtypez = {SpriteBatch.class, Color.class})
/*     */   public static class NPC2
/*     */   {
/*     */     public static void Prefix(AnimatedNpc __instance, SpriteBatch sb, Color color) {
/* 120 */       FlipRoom.beginFlip(sb);
/*     */     }
/*     */     
/*     */     public static void Postfix(AnimatedNpc __instance, SpriteBatch sb, Color color) {
/* 124 */       FlipRoom.endFlip(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = Merchant.class, method = "render")
/*     */   public static class MerchantRug
/*     */   {
/*     */     public static void Prefix(Merchant __instance, SpriteBatch sb) {
/* 134 */       FlipRoom.beginFlip(sb);
/*     */     }
/*     */     
/*     */     public static void Postfix(Merchant __instance, SpriteBatch sb) {
/* 138 */       FlipRoom.endFlip(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = AbstractDungeon.class, method = "render")
/*     */   public static class VFX
/*     */   {
/*     */     @SpireInsertPatch(locator = StartLocator.class)
/*     */     public static void Start(AbstractDungeon __instance, SpriteBatch sb) {
/* 151 */       FlipRoom.beginFlip(sb);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @SpireInsertPatch(locator = End1Locator.class)
/*     */     public static void End1(AbstractDungeon __instance, SpriteBatch sb) {
/* 158 */       FlipRoom.endFlip(sb);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @SpireInsertPatch(locator = End2Locator.class)
/*     */     public static void End2(AbstractDungeon __instance, SpriteBatch sb) {
/* 165 */       FlipRoom.endFlip(sb);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @SpireInsertPatch(locator = StartTopLevelLocator.class)
/*     */     public static void StartTopLevel(AbstractDungeon __instance, SpriteBatch sb) {
/* 172 */       FlipRoom.beginFlip(sb);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     @SpireInsertPatch(locator = EndTopLevelLocator.class)
/*     */     public static void EndTopLevel(AbstractDungeon __instance, SpriteBatch sb) {
/* 179 */       FlipRoom.endFlip(sb);
/*     */     }
/*     */     
/*     */     private static class StartLocator
/*     */       extends SpireInsertLocator {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 185 */         Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "effectList");
/* 186 */         return LineFinder.findAllInOrder(ctBehavior, (Matcher)fieldAccessMatcher);
/*     */       }
/*     */     }
/*     */     
/*     */     private static class End1Locator
/*     */       extends SpireInsertLocator {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 193 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(AbstractRoom.class, "render");
/* 194 */         return LineFinder.findInOrder(ctBehavior, (Matcher)methodCallMatcher);
/*     */       }
/*     */     }
/*     */     
/*     */     private static class End2Locator
/*     */       extends SpireInsertLocator {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 201 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(OverlayMenu.class, "render");
/* 202 */         return LineFinder.findInOrder(ctBehavior, (Matcher)methodCallMatcher);
/*     */       }
/*     */     }
/*     */     
/*     */     private static class StartTopLevelLocator
/*     */       extends SpireInsertLocator {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 209 */         Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "topLevelEffects");
/* 210 */         return LineFinder.findInOrder(ctBehavior, (Matcher)fieldAccessMatcher);
/*     */       }
/*     */     }
/*     */     
/*     */     private static class EndTopLevelLocator
/*     */       extends SpireInsertLocator {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 217 */         Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(SpriteBatch.class, "setColor");
/* 218 */         int[] found = LineFinder.findAllInOrder(ctBehavior, (Matcher)methodCallMatcher);
/* 219 */         return new int[] { found[found.length - 1] };
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\FlipRoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */