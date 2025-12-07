/*    */ package downfall.patches;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.scenes.TitleBackground;
/*    */ import com.megacrit.cardcrawl.scenes.TitleCloud;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class MainMenuColorPatch
/*    */ {
/* 21 */   public static final String assetPath = downfallMod.assetPath("images/ui/mainmenu/");
/* 22 */   public static final String atlasPath = getAsset("title.atlas");
/*    */   
/*    */   private static String getAsset(String assetName) {
/* 25 */     return assetPath + assetName;
/*    */   }
/*    */   
/*    */   @SpirePatch(clz = TitleBackground.class, method = "<ctor>")
/*    */   public static class TitleBackgroundReplacementPatch
/*    */   {
/*    */     @SpirePostfixPatch
/*    */     public static void downfallTitleBackgroundAtlasReplacer(TitleBackground __instance) {
/* 33 */       if (downfallMod.replaceMenuColor && !downfallMod.noMusic) {
/* 34 */         MainMenuColorPatch.setMainMenuBG(__instance);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public static void setMainMenuBG() {
/* 40 */     setMainMenuBG(null);
/*    */   }
/*    */   
/*    */   public static void setMainMenuBG(TitleBackground __instance) {
/* 44 */     TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasPath));
/*    */     
/* 46 */     if (__instance == null) {
/* 47 */       __instance = CardCrawlGame.mainMenuScreen.bg;
/*    */     }
/*    */     
/* 50 */     setTitleBackgroundAtlasRegion(__instance, atlas, "sky", "jpg/sky");
/* 51 */     setTitleBackgroundAtlasRegion(__instance, atlas, "mg3Bot", "mg3Bot");
/* 52 */     setTitleBackgroundAtlasRegion(__instance, atlas, "mg3Top", "mg3Top");
/* 53 */     setTitleBackgroundAtlasRegion(__instance, atlas, "topGlow", "mg3TopGlow1");
/* 54 */     setTitleBackgroundAtlasRegion(__instance, atlas, "topGlow2", "mg3TopGlow2");
/* 55 */     setTitleBackgroundAtlasRegion(__instance, atlas, "botGlow", "mg3BotGlow");
/*    */     
/* 57 */     setClouds(__instance, atlas);
/* 58 */     setLogo(__instance);
/*    */   }
/*    */   
/*    */   private static void setLogo(TitleBackground menu) {
/* 62 */     Texture empty = TextureLoader.getTexture(getAsset("logo.png"));
/*    */     
/* 64 */     ReflectionHacks.setPrivate(menu, TitleBackground.class, "titleLogoImg", empty);
/*    */   }
/*    */   
/*    */   private static void setClouds(TitleBackground menu, TextureAtlas atlas) {
/* 68 */     ArrayList<TitleCloud> newTopClouds = new ArrayList<>();
/* 69 */     ArrayList<TitleCloud> newMidClouds = new ArrayList<>();
/*    */     int i;
/* 71 */     for (i = 0; i < 6; i++) {
/* 72 */       newTopClouds.add(new TitleCloud(atlas
/* 73 */             .findRegion("topCloud" + (i + 1)), 
/* 74 */             MathUtils.random(10.0F, 50.0F) * Settings.scale, 
/* 75 */             MathUtils.random(-1920.0F, 1920.0F) * Settings.scale));
/*    */     }
/*    */ 
/*    */     
/* 79 */     for (i = 0; i < 12; i++) {
/* 80 */       newTopClouds.add(new TitleCloud(atlas
/* 81 */             .findRegion("midCloud" + (i + 1)), 
/* 82 */             MathUtils.random(-50.0F, -10.0F) * Settings.scale, 
/* 83 */             MathUtils.random(-1920.0F, 1920.0F) * Settings.scale));
/*    */     }
/*    */ 
/*    */     
/* 87 */     ReflectionHacks.setPrivate(menu, TitleBackground.class, "topClouds", newTopClouds);
/* 88 */     ReflectionHacks.setPrivate(menu, TitleBackground.class, "midClouds", newMidClouds);
/*    */   }
/*    */   
/*    */   private static void setTitleBackgroundAtlasRegion(TitleBackground menu, TextureAtlas newAtlas, String classVarName, String srcRegionName) {
/* 92 */     ReflectionHacks.setPrivate(menu, TitleBackground.class, classVarName, newAtlas.findRegion(srcRegionName));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\MainMenuColorPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */