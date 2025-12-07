/*     */ package downfall.patches;
/*     */ 
/*     */ import automaton.AutomatonChar;
/*     */ import basemod.CustomCharacterSelectScreen;
/*     */ import basemod.ReflectionHacks;
/*     */ import champ.ChampChar;
/*     */ import collector.CollectorChar;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
/*     */ import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import downfall.downfallMod;
/*     */ import downfall.patches.ui.topPanel.GoldToSoulPatches;
/*     */ import gremlin.patches.GremlinEnum;
/*     */ import guardian.patches.GuardianEnum;
/*     */ import hermit.characters.hermit;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javassist.CannotCompileException;
/*     */ import javassist.CtBehavior;
/*     */ import javassist.expr.ExprEditor;
/*     */ import javassist.expr.FieldAccess;
/*     */ import slimebound.patches.SlimeboundEnum;
/*     */ import sneckomod.TheSnecko;
/*     */ import theHexaghost.TheHexaghost;
/*     */ 
/*     */ public class EvilModeCharacterSelect {
/*     */   public static boolean evilMode = false;
/*  39 */   private static int maxEvilSelectIndex = 0;
/*  40 */   private static final List<CharacterOption> villains = new ArrayList<>();
/*  41 */   private static final List<CharacterOption> standard = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = CustomCharacterSelectScreen.class, method = "initialize")
/*     */   public static class InitializeCharacterOptions
/*     */   {
/*     */     @SpireInsertPatch(locator = Locator.class)
/*     */     public static void Insert(CustomCharacterSelectScreen __instance) {
/*  52 */       EvilModeCharacterSelect.villains.clear();
/*  53 */       EvilModeCharacterSelect.standard.clear();
/*     */       
/*  55 */       Iterator<CharacterOption> options = __instance.options.iterator();
/*     */       
/*  57 */       ArrayList<CharacterOption> basegameOptions = new ArrayList<>(), moddedOptions = new ArrayList<>();
/*  58 */       CharacterOption[] villainOptions = new CharacterOption[8];
/*     */       
/*  60 */       while (options.hasNext()) {
/*  61 */         CharacterOption o = options.next();
/*     */         
/*  63 */         switch (o.c.chosenClass) {
/*     */           case IRONCLAD:
/*     */           case THE_SILENT:
/*     */           case DEFECT:
/*     */           case WATCHER:
/*  68 */             if (downfallMod.crossoverCharacters)
/*  69 */               basegameOptions.add(o); 
/*     */             continue;
/*     */         } 
/*  72 */         if (o.c.chosenClass == hermit.Enums.HERMIT) {
/*  73 */           if (downfallMod.crossoverCharacters)
/*  74 */             basegameOptions.add(o); 
/*     */           continue;
/*     */         } 
/*  77 */         boolean isVillain = true;
/*  78 */         if (o.c.chosenClass == SlimeboundEnum.SLIMEBOUND) {
/*  79 */           villainOptions[0] = o;
/*  80 */         } else if (o.c.chosenClass == GuardianEnum.GUARDIAN) {
/*  81 */           if (UnlockTracker.isCharacterLocked("Guardian")) {
/*  82 */             o.locked = true;
/*  83 */             ReflectionHacks.setPrivate(o, CharacterOption.class, "buttonImg", ImageMaster.CHAR_SELECT_LOCKED);
/*     */           } 
/*  85 */           villainOptions[1] = o;
/*  86 */         } else if (o.c.chosenClass == TheHexaghost.Enums.THE_SPIRIT) {
/*  87 */           if (UnlockTracker.isCharacterLocked("Hexaghost")) {
/*  88 */             o.locked = true;
/*  89 */             ReflectionHacks.setPrivate(o, CharacterOption.class, "buttonImg", ImageMaster.CHAR_SELECT_LOCKED);
/*     */           } 
/*  91 */           villainOptions[2] = o;
/*  92 */         } else if (o.c.chosenClass == ChampChar.Enums.THE_CHAMP) {
/*  93 */           if (UnlockTracker.isCharacterLocked("Champ")) {
/*  94 */             o.locked = true;
/*  95 */             ReflectionHacks.setPrivate(o, CharacterOption.class, "buttonImg", ImageMaster.CHAR_SELECT_LOCKED);
/*     */           } 
/*  97 */           villainOptions[3] = o;
/*  98 */         } else if (o.c.chosenClass == AutomatonChar.Enums.THE_AUTOMATON) {
/*  99 */           if (UnlockTracker.isCharacterLocked("Automaton")) {
/* 100 */             o.locked = true;
/* 101 */             ReflectionHacks.setPrivate(o, CharacterOption.class, "buttonImg", ImageMaster.CHAR_SELECT_LOCKED);
/*     */           } 
/* 103 */           villainOptions[4] = o;
/* 104 */         } else if (o.c.chosenClass == CollectorChar.Enums.THE_COLLECTOR) {
/* 105 */           if (UnlockTracker.isCharacterLocked("Collector")) {
/* 106 */             o.locked = true;
/* 107 */             ReflectionHacks.setPrivate(o, CharacterOption.class, "buttonImg", ImageMaster.CHAR_SELECT_LOCKED);
/*     */           } 
/* 109 */           villainOptions[5] = o;
/* 110 */         } else if (o.c.chosenClass == GremlinEnum.GREMLIN) {
/* 111 */           if (UnlockTracker.isCharacterLocked("Gremlin")) {
/* 112 */             o.locked = true;
/* 113 */             ReflectionHacks.setPrivate(o, CharacterOption.class, "buttonImg", ImageMaster.CHAR_SELECT_LOCKED);
/*     */           } 
/* 115 */           villainOptions[6] = o;
/* 116 */         } else if (o.c.chosenClass == TheSnecko.Enums.THE_SNECKO) {
/* 117 */           if (UnlockTracker.isCharacterLocked("Snecko")) {
/* 118 */             o.locked = true;
/* 119 */             ReflectionHacks.setPrivate(o, CharacterOption.class, "buttonImg", ImageMaster.CHAR_SELECT_LOCKED);
/*     */           } 
/* 121 */           villainOptions[7] = o;
/*     */         } else {
/* 123 */           isVillain = false;
/* 124 */           moddedOptions.add(o);
/*     */         } 
/*     */         
/* 127 */         if (isVillain && !downfallMod.crossoverCharacters) {
/* 128 */           options.remove();
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 135 */       EvilModeCharacterSelect.villains.addAll(Arrays.asList(villainOptions));
/* 136 */       EvilModeCharacterSelect.villains.addAll(basegameOptions);
/* 137 */       if (downfallMod.crossoverModCharacters) {
/* 138 */         EvilModeCharacterSelect.villains.addAll(moddedOptions);
/*     */       }
/* 140 */       EvilModeCharacterSelect.maxEvilSelectIndex = (int)Math.ceil((EvilModeCharacterSelect.villains.size() / 4.0F)) - 1;
/*     */       
/* 142 */       EvilModeCharacterSelect.standard.addAll(__instance.options);
/*     */     }
/*     */ 
/*     */     
/*     */     public static class Locator
/*     */       extends SpireInsertLocator
/*     */     {
/*     */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 150 */         Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(CustomCharacterSelectScreen.class, "options");
/* 151 */         ArrayList<Matcher> matchers = new ArrayList<>();
/* 152 */         matchers.add(fieldAccessMatcher);
/* 153 */         return LineFinder.findInOrder(ctBehavior, matchers, (Matcher)fieldAccessMatcher);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = CharacterSelectScreen.class, method = "open")
/*     */   public static class ChangeToEvilOptions
/*     */   {
/*     */     public static void Prefix(CharacterSelectScreen __instance, boolean isEndless) {
/* 165 */       if (__instance instanceof CustomCharacterSelectScreen) {
/* 166 */         CustomCharacterSelectScreen screen = (CustomCharacterSelectScreen)__instance;
/* 167 */         if (EvilModeCharacterSelect.evilMode) {
/* 168 */           GoldToSoulPatches.changeGoldToSouls(false);
/* 169 */           screen.options.clear();
/* 170 */           ArrayList<CharacterOption> allOptions = (ArrayList<CharacterOption>)ReflectionHacks.getPrivate(screen, CustomCharacterSelectScreen.class, "allOptions");
/*     */           
/* 172 */           EvilModeCharacterSelect.ResetOptions.saved_optionsPerIndex = ((Integer)ReflectionHacks.getPrivate(screen, CustomCharacterSelectScreen.class, "optionsPerIndex")).intValue();
/* 173 */           ReflectionHacks.setPrivate(screen, CustomCharacterSelectScreen.class, "optionsPerIndex", Integer.valueOf(4));
/*     */           
/* 175 */           ReflectionHacks.setPrivate(screen, CustomCharacterSelectScreen.class, "selectIndex", Integer.valueOf(0));
/* 176 */           EvilModeCharacterSelect.ResetOptions.saved_maxSelectIndex = ((Integer)ReflectionHacks.getPrivate(screen, CustomCharacterSelectScreen.class, "maxSelectIndex")).intValue();
/* 177 */           ReflectionHacks.setPrivate(screen, CustomCharacterSelectScreen.class, "maxSelectIndex", Integer.valueOf(EvilModeCharacterSelect.maxEvilSelectIndex));
/*     */           
/* 179 */           allOptions.clear();
/* 180 */           allOptions.addAll(EvilModeCharacterSelect.villains);
/*     */           
/*     */           try {
/* 183 */             Method m = CustomCharacterSelectScreen.class.getDeclaredMethod("setCurrentOptions", new Class[] { boolean.class });
/* 184 */             m.setAccessible(true);
/* 185 */             m.invoke(screen, new Object[] { Boolean.valueOf(false) });
/* 186 */           } catch (NoSuchMethodException|IllegalAccessException|java.lang.reflect.InvocationTargetException e) {
/* 187 */             throw new RuntimeException(e);
/*     */           } 
/*     */         } else {
/* 190 */           GoldToSoulPatches.changeGoldToSouls(true);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = MainMenuScreen.class, method = "update")
/*     */   public static class ResetOptions
/*     */   {
/* 201 */     private static int saved_maxSelectIndex = -1;
/* 202 */     private static int saved_optionsPerIndex = 4;
/*     */ 
/*     */     
/*     */     public static void Prefix(MainMenuScreen __instance) {
/* 206 */       if (__instance.screen != MainMenuScreen.CurScreen.CHAR_SELECT && 
/* 207 */         saved_maxSelectIndex >= 0) {
/* 208 */         if (__instance.charSelectScreen instanceof CustomCharacterSelectScreen) {
/* 209 */           CustomCharacterSelectScreen screen = (CustomCharacterSelectScreen)__instance.charSelectScreen;
/* 210 */           ArrayList<CharacterOption> allOptions = (ArrayList<CharacterOption>)ReflectionHacks.getPrivate(screen, CustomCharacterSelectScreen.class, "allOptions");
/*     */           
/* 212 */           ReflectionHacks.setPrivate(screen, CustomCharacterSelectScreen.class, "selectIndex", Integer.valueOf(0));
/* 213 */           ReflectionHacks.setPrivate(screen, CustomCharacterSelectScreen.class, "maxSelectIndex", Integer.valueOf(saved_maxSelectIndex));
/* 214 */           ReflectionHacks.setPrivate(screen, CustomCharacterSelectScreen.class, "optionsPerIndex", Integer.valueOf(saved_optionsPerIndex));
/*     */           
/* 216 */           allOptions.clear();
/* 217 */           allOptions.addAll(EvilModeCharacterSelect.standard);
/*     */           
/*     */           try {
/* 220 */             Method m = CustomCharacterSelectScreen.class.getDeclaredMethod("setCurrentOptions", new Class[] { boolean.class });
/* 221 */             m.setAccessible(true);
/* 222 */             m.invoke(screen, new Object[] { Boolean.valueOf(false) });
/* 223 */           } catch (NoSuchMethodException|IllegalAccessException|java.lang.reflect.InvocationTargetException e) {
/* 224 */             throw new RuntimeException(e);
/*     */           } 
/*     */         } 
/* 227 */         saved_maxSelectIndex = -1;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
/*     */   public static class ChangeText
/*     */   {
/*     */     public static ExprEditor Instrument() {
/* 239 */       return new ExprEditor()
/*     */         {
/*     */           public void edit(FieldAccess f) throws CannotCompileException {
/* 242 */             if (f.isReader() && f.getFieldName().equals("CHOOSE_CHAR_MSG"))
/* 243 */               f.replace("if (" + EvilModeCharacterSelect.class.getName() + ".evilMode) {$_ = " + MainMenuEvilMode.EvilMainMenuPanelButton.class
/* 244 */                   .getName() + ".uiStrings.TEXT[2];} else {$_ = $proceed($$);}"); 
/*     */           }
/*     */         };
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\EvilModeCharacterSelect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */