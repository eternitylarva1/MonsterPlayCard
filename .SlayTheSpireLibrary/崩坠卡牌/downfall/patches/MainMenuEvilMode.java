/*     */ package downfall.patches;
/*     */ import basemod.ReflectionHacks;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuPanelButton;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MenuPanelScreen;
/*     */ import downfall.downfallMod;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.function.Consumer;
/*     */ import javassist.CtBehavior;
/*     */ 
/*     */ public class MainMenuEvilMode {
/*     */   public static class Enums {
/*     */     @SpireEnum
/*     */     public static MenuPanelScreen.PanelScreen EVIL;
/*     */     @SpireEnum
/*     */     public static MainMenuPanelButton.PanelClickResult PLAY_EVIL;
/*     */   }
/*     */   
/*     */   @SpirePatch(clz = MainMenuPanelButton.class, method = "buttonEffect")
/*     */   public static class RedirectPlayNormal {
/*     */     public static SpireReturn<Void> Prefix(MainMenuPanelButton __instance, MainMenuPanelButton.PanelClickResult ___result) {
/*  33 */       if (___result == MainMenuPanelButton.PanelClickResult.PLAY_NORMAL) {
/*     */         MainMenuEvilMode.LatePanelOpen.lateOpen = (x -> x.open(MainMenuEvilMode.Enums.EVIL));
/*     */ 
/*     */         
/*  37 */         return SpireReturn.Return(null);
/*     */       } 
/*  39 */       return SpireReturn.Continue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MenuPanelScreen.class, method = "update")
/*     */   public static class RedirectBackButton
/*     */   {
/*     */     @SpireInsertPatch(locator = Locator.class)
/*     */     public static SpireReturn<Void> Insert(MenuPanelScreen __instance, MenuPanelScreen.PanelScreen ___screen) {
/*  52 */       if (___screen == MainMenuEvilMode.Enums.EVIL) {
/*  53 */         __instance.button.hb.clicked = false;
/*  54 */         __instance.button.hb.hovered = false;
/*  55 */         InputHelper.justClickedLeft = false;
/*     */         
/*     */         MainMenuEvilMode.LatePanelOpen.lateOpen = (x -> x.open(MenuPanelScreen.PanelScreen.PLAY));
/*     */         
/*  59 */         return SpireReturn.Return(null);
/*     */       } 
/*  61 */       return SpireReturn.Continue();
/*     */     }
/*     */     
/*     */     public static class Locator
/*     */       extends SpireInsertLocator {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/*  67 */         Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(MainMenuScreen.class, "screen");
/*  68 */         return LineFinder.findInOrder(ctBehavior, (Matcher)fieldAccessMatcher);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MenuPanelScreen.class, method = "update")
/*     */   public static class LatePanelOpen
/*     */   {
/*  79 */     private static Consumer<MenuPanelScreen> lateOpen = null;
/*     */     
/*     */     public static void Postfix(MenuPanelScreen __instance) {
/*  82 */       if (lateOpen != null) {
/*  83 */         lateOpen.accept(__instance);
/*  84 */         lateOpen = null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MenuPanelScreen.class, method = "initializePanels")
/*     */   public static class InitEvilPanels
/*     */   {
/*     */     public static void Postfix(MenuPanelScreen __instance, MenuPanelScreen.PanelScreen ___screen, float ___PANEL_Y) {
/*  95 */       if (___screen == MainMenuEvilMode.Enums.EVIL) {
/*  96 */         __instance.panels.add(new MainMenuEvilMode.EvilMainMenuPanelButton(MainMenuPanelButton.PanelClickResult.PLAY_NORMAL, MainMenuPanelButton.PanelColor.BLUE, Settings.WIDTH / 2.0F - 225.0F * Settings.scale, ___PANEL_Y));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 102 */         __instance.panels.add(new MainMenuEvilMode.EvilMainMenuPanelButton(MainMenuEvilMode.Enums.PLAY_EVIL, MainMenuPanelButton.PanelColor.RED, Settings.WIDTH / 2.0F + 225.0F * Settings.scale, ___PANEL_Y));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class EvilMainMenuPanelButton
/*     */     extends MainMenuPanelButton
/*     */   {
/* 113 */     public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("EvilMenuPanel"));
/* 114 */     private static Field resultField = null;
/*     */     
/*     */     public EvilMainMenuPanelButton(MainMenuPanelButton.PanelClickResult setResult, MainMenuPanelButton.PanelColor setColor, float x, float y) {
/* 117 */       super(setResult, setColor, x, y);
/*     */     }
/*     */     
/*     */     @SpireOverride
/*     */     protected void setLabel() {
/* 122 */       if (getResult() == MainMenuEvilMode.Enums.PLAY_EVIL) {
/* 123 */         ReflectionHacks.setPrivate(this, MainMenuPanelButton.class, "panelImg", ImageMaster.MENU_PANEL_BG_RED);
/* 124 */         ReflectionHacks.setPrivate(this, MainMenuPanelButton.class, "portraitImg", ImageMaster.P_STAT_CHAR);
/* 125 */         ReflectionHacks.setPrivate(this, MainMenuPanelButton.class, "header", uiStrings.TEXT[0]);
/* 126 */         ReflectionHacks.setPrivate(this, MainMenuPanelButton.class, "description", uiStrings.TEXT[1]);
/*     */       } else {
/* 128 */         SpireSuper.call(new Object[0]);
/*     */       } 
/*     */     }
/*     */     
/*     */     @SpireOverride
/*     */     protected void buttonEffect() {
/* 134 */       if (getResult() == MainMenuEvilMode.Enums.PLAY_EVIL || getResult() == MainMenuPanelButton.PanelClickResult.PLAY_NORMAL) {
/* 135 */         EvilModeCharacterSelect.evilMode = (getResult() == MainMenuEvilMode.Enums.PLAY_EVIL);
/* 136 */         DailyModeEvilPatch.todaysRunIsEvil = false;
/* 137 */         CardCrawlGame.mainMenuScreen.charSelectScreen.open(false);
/*     */       } else {
/* 139 */         SpireSuper.call(new Object[0]);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected MainMenuPanelButton.PanelClickResult getResult() {
/* 144 */       if (resultField == null) {
/*     */         try {
/* 146 */           resultField = MainMenuPanelButton.class.getDeclaredField("result");
/* 147 */           resultField.setAccessible(true);
/* 148 */         } catch (NoSuchFieldException e) {
/* 149 */           throw new RuntimeException(e);
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/* 154 */         return (MainMenuPanelButton.PanelClickResult)resultField.get(this);
/* 155 */       } catch (IllegalAccessException e) {
/* 156 */         throw new RuntimeException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\MainMenuEvilMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */