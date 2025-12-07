/*     */ package downfall.mainmenu;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;
/*     */ import downfall.util.TextureLoader;
/*     */ import java.awt.Desktop;
/*     */ import java.net.URL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiscordButton
/*     */ {
/*  18 */   public static Button discordButton = new Button(1400.0F * Settings.xScale, (Settings.HEIGHT / 2) - 200.0F * Settings.yScale, "", TextureLoader.getTexture("downfallResources/images/discord.png"));
/*     */   
/*     */   public static void openWebpage(String urlString) {
/*     */     try {
/*  22 */       Desktop.getDesktop().browse((new URL(urlString)).toURI());
/*  23 */     } catch (Exception e) {
/*  24 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MainMenuScreen.class, method = "update")
/*     */   public static class Update
/*     */   {
/*     */     public static void Postfix(MainMenuScreen __instance) {
/*  34 */       if (__instance.screen == MainMenuScreen.CurScreen.MAIN_MENU);
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
/*     */   @SpirePatch(clz = MainMenuScreen.class, method = "render")
/*     */   public static class Render
/*     */   {
/*     */     public static void Postfix(MainMenuScreen __instance, SpriteBatch sb) {
/*  58 */       if (__instance.screen == MainMenuScreen.CurScreen.MAIN_MENU);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MenuButton.class, method = "update")
/*     */   public static class DontPressThroughAd
/*     */   {
/*     */     public static SpireReturn Prefix(MenuButton __instance) {
/*  72 */       if (!MainMenuAdPatch.popup.done) {
/*  73 */         return SpireReturn.Return();
/*     */       }
/*  75 */       return SpireReturn.Continue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MenuButton.class, method = "render")
/*     */   public static class DontSeeThroughAd
/*     */   {
/*     */     public static SpireReturn Prefix(MenuButton __instance, SpriteBatch sb) {
/*  85 */       if (!MainMenuAdPatch.popup.done) {
/*  86 */         return SpireReturn.Return();
/*     */       }
/*  88 */       return SpireReturn.Continue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MainMenuScreen.class, method = "updateRenameArea")
/*     */   public static class DontSeeThroughAd2
/*     */   {
/*     */     public static SpireReturn Prefix(MainMenuScreen __instance) {
/*  98 */       if (!MainMenuAdPatch.popup.done) {
/*  99 */         return SpireReturn.Return();
/*     */       }
/* 101 */       return SpireReturn.Continue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MainMenuScreen.class, method = "renderNameEdit")
/*     */   public static class DontSeeThroughAd3
/*     */   {
/*     */     public static SpireReturn Prefix(MainMenuScreen __instance, SpriteBatch sb) {
/* 111 */       if (!MainMenuAdPatch.popup.done) {
/* 112 */         return SpireReturn.Return();
/*     */       }
/* 114 */       return SpireReturn.Continue();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\mainmenu\DiscordButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */