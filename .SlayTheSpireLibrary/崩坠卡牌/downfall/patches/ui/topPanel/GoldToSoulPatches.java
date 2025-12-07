/*     */ package downfall.patches.ui.topPanel;
/*     */ import basemod.ReflectionHacks;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*     */ import com.megacrit.cardcrawl.localization.CardStrings;
/*     */ import com.megacrit.cardcrawl.localization.CharacterStrings;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*     */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*     */ import com.megacrit.cardcrawl.localization.RelicStrings;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
/*     */ import com.megacrit.cardcrawl.ui.panels.TopPanel;
/*     */ import downfall.downfallMod;
/*     */ import downfall.monsters.FleeingMerchant;
/*     */ import downfall.patches.EvilModeCharacterSelect;
/*     */ import downfall.util.ReplaceData;
/*     */ import downfall.util.TextureLoader;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javassist.CannotCompileException;
/*     */ import javassist.CtBehavior;
/*     */ import javassist.expr.ExprEditor;
/*     */ import javassist.expr.MethodCall;
/*     */ 
/*     */ public class GoldToSoulPatches {
/*  34 */   public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("SoulToGoldChanges"));
/*  35 */   private static final String[] GOLD_TEXT = new String[] { TopPanel.LABEL[4], TopPanel.MSG[4] };
/*  36 */   public static Map<String, CardStrings[]> renameC = (Map)new HashMap<>();
/*  37 */   public static Map<String, EventStrings[]> renameE = (Map)new HashMap<>();
/*  38 */   public static Map<String, RelicStrings[]> renameR = (Map)new HashMap<>();
/*  39 */   public static Map<String, CharacterStrings[]> renameCh = (Map)new HashMap<>();
/*  40 */   public static Map<String, PowerStrings[]> renameP = (Map)new HashMap<>();
/*  41 */   public static Map<String, UIStrings[]> renameUI = (Map)new HashMap<>();
/*     */   
/*  43 */   public static PowerTip SoulsTip = new PowerTip(uiStrings.TEXT[0], uiStrings.TEXT[1]);
/*  44 */   public static PowerTip MerchantTip = new PowerTip(uiStrings.TEXT[2], "");
/*     */   
/*     */   private static Texture GOLD_ICON;
/*     */   
/*     */   private static Texture GOLD_UI_ICON;
/*     */   private static boolean triggered = false;
/*     */   
/*     */   public static void changeGoldToSouls(boolean revert) {
/*  52 */     if (!triggered) {
/*  53 */       triggered = true;
/*  54 */       GOLD_ICON = ImageMaster.TP_GOLD;
/*  55 */       GOLD_UI_ICON = ImageMaster.UI_GOLD;
/*     */     } 
/*     */     
/*  58 */     if (!revert) {
/*  59 */       ImageMaster.TP_GOLD = TextureLoader.getTexture(downfallMod.assetPath("images/ui/Souls_Icon.png"));
/*  60 */       ImageMaster.UI_GOLD = TextureLoader.getTexture(downfallMod.assetPath("images/ui/Souls_UI_Icon.png"));
/*  61 */       TopPanel.LABEL[4] = uiStrings.TEXT[0];
/*  62 */       TopPanel.MSG[4] = uiStrings.TEXT[1];
/*  63 */       replaceStrings(true);
/*     */     } else {
/*  65 */       ImageMaster.TP_GOLD = GOLD_ICON;
/*  66 */       ImageMaster.UI_GOLD = GOLD_UI_ICON;
/*  67 */       TopPanel.LABEL[4] = GOLD_TEXT[0];
/*  68 */       TopPanel.MSG[4] = GOLD_TEXT[1];
/*  69 */       replaceStrings(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void postLoadLocalizationStrings() {
/*     */     try {
/*  75 */       Map<String, CardStrings> cardStrings = (Map<String, CardStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "cards");
/*  76 */       if (cardStrings != null)
/*     */       {
/*  78 */         for (Map.Entry<String, CardStrings> cardString : cardStrings.entrySet()) {
/*  79 */           CardStrings replacementString = new CardStrings();
/*  80 */           replacementString.NAME = ((CardStrings)cardString.getValue()).NAME;
/*     */           
/*  82 */           if (((CardStrings)cardString.getValue()).DESCRIPTION != null) {
/*  83 */             replacementString.DESCRIPTION = filterString(((CardStrings)cardString.getValue()).DESCRIPTION);
/*     */           }
/*     */           
/*  86 */           if (((CardStrings)cardString.getValue()).UPGRADE_DESCRIPTION != null) {
/*  87 */             replacementString.UPGRADE_DESCRIPTION = filterString(((CardStrings)cardString.getValue()).UPGRADE_DESCRIPTION);
/*     */           }
/*     */           
/*  90 */           if (((CardStrings)cardString.getValue()).EXTENDED_DESCRIPTION != null) {
/*  91 */             replacementString.EXTENDED_DESCRIPTION = filterString(((CardStrings)cardString.getValue()).EXTENDED_DESCRIPTION);
/*     */           }
/*  93 */           if ((replacementString.DESCRIPTION != null && !replacementString.DESCRIPTION.equals(((CardStrings)cardString.getValue()).DESCRIPTION)) || (replacementString.EXTENDED_DESCRIPTION != null && 
/*  94 */             !Arrays.equals((Object[])replacementString.EXTENDED_DESCRIPTION, (Object[])((CardStrings)cardString.getValue()).EXTENDED_DESCRIPTION)) || (replacementString.UPGRADE_DESCRIPTION != null && 
/*  95 */             !replacementString.UPGRADE_DESCRIPTION.equals(((CardStrings)cardString.getValue()).UPGRADE_DESCRIPTION))) {
/*  96 */             renameC.put(cardString.getKey(), new CardStrings[] { cardString.getValue(), replacementString });
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 109 */       Map<String, EventStrings> eventStrings = (Map<String, EventStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "events");
/* 110 */       if (eventStrings != null)
/*     */       {
/* 112 */         for (Map.Entry<String, EventStrings> eventString : eventStrings.entrySet()) {
/* 113 */           EventStrings replacementString = new EventStrings();
/* 114 */           replacementString.NAME = ((EventStrings)eventString.getValue()).NAME;
/*     */           
/* 116 */           if (((EventStrings)eventString.getValue()).DESCRIPTIONS != null) {
/* 117 */             replacementString.DESCRIPTIONS = filterString(((EventStrings)eventString.getValue()).DESCRIPTIONS);
/*     */           }
/*     */           
/* 120 */           if (((EventStrings)eventString.getValue()).OPTIONS != null) {
/* 121 */             replacementString.OPTIONS = filterString(((EventStrings)eventString.getValue()).OPTIONS);
/*     */           }
/*     */           
/* 124 */           if ((replacementString.DESCRIPTIONS != null && !Arrays.equals((Object[])replacementString.DESCRIPTIONS, (Object[])((EventStrings)eventString.getValue()).DESCRIPTIONS)) || (replacementString.OPTIONS != null && 
/* 125 */             !Arrays.equals((Object[])replacementString.OPTIONS, (Object[])((EventStrings)eventString.getValue()).OPTIONS))) {
/* 126 */             renameE.put(eventString.getKey(), new EventStrings[] { eventString.getValue(), replacementString });
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 131 */       Map<String, RelicStrings> relicStrings = (Map<String, RelicStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "relics");
/* 132 */       if (relicStrings != null)
/*     */       {
/* 134 */         for (Map.Entry<String, RelicStrings> relicString : relicStrings.entrySet()) {
/* 135 */           RelicStrings replacementString = new RelicStrings();
/* 136 */           replacementString.NAME = ((RelicStrings)relicString.getValue()).NAME;
/* 137 */           replacementString.FLAVOR = ((RelicStrings)relicString.getValue()).FLAVOR;
/*     */           
/* 139 */           if (((RelicStrings)relicString.getValue()).DESCRIPTIONS != null) {
/* 140 */             replacementString.DESCRIPTIONS = filterString(((RelicStrings)relicString.getValue()).DESCRIPTIONS);
/*     */           }
/*     */           
/* 143 */           if (replacementString.DESCRIPTIONS != null && !Arrays.equals((Object[])replacementString.DESCRIPTIONS, (Object[])((RelicStrings)relicString.getValue()).DESCRIPTIONS)) {
/* 144 */             renameR.put(relicString.getKey(), new RelicStrings[] { relicString.getValue(), replacementString });
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 149 */       Map<String, CharacterStrings> characterStrings = (Map<String, CharacterStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "characters");
/* 150 */       if (characterStrings != null)
/*     */       {
/* 152 */         for (Map.Entry<String, CharacterStrings> characterString : characterStrings.entrySet()) {
/* 153 */           CharacterStrings replacementString = new CharacterStrings();
/* 154 */           replacementString.UNIQUE_REWARDS = ((CharacterStrings)characterString.getValue()).UNIQUE_REWARDS;
/*     */           
/* 156 */           if (((CharacterStrings)characterString.getValue()).NAMES != null) {
/* 157 */             replacementString.NAMES = filterString(((CharacterStrings)characterString.getValue()).NAMES);
/*     */           }
/*     */           
/* 160 */           if (((CharacterStrings)characterString.getValue()).TEXT != null) {
/* 161 */             replacementString.TEXT = filterString(((CharacterStrings)characterString.getValue()).TEXT);
/*     */           }
/*     */           
/* 164 */           if (((CharacterStrings)characterString.getValue()).OPTIONS != null) {
/* 165 */             replacementString.OPTIONS = filterString(((CharacterStrings)characterString.getValue()).OPTIONS);
/*     */           }
/*     */           
/* 168 */           if ((replacementString.NAMES != null && !Arrays.equals((Object[])replacementString.NAMES, (Object[])((CharacterStrings)characterString.getValue()).NAMES)) || (replacementString.TEXT != null && 
/* 169 */             !Arrays.equals((Object[])replacementString.TEXT, (Object[])((CharacterStrings)characterString.getValue()).TEXT)) || (replacementString.OPTIONS != null && 
/* 170 */             !Arrays.equals((Object[])replacementString.OPTIONS, (Object[])((CharacterStrings)characterString.getValue()).OPTIONS))) {
/* 171 */             renameCh.put(characterString.getKey(), new CharacterStrings[] { characterString.getValue(), replacementString });
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 176 */       Map<String, PowerStrings> powerStrings = (Map<String, PowerStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "powers");
/* 177 */       if (powerStrings != null)
/*     */       {
/* 179 */         for (Map.Entry<String, PowerStrings> powerString : powerStrings.entrySet()) {
/* 180 */           PowerStrings replacementString = new PowerStrings();
/* 181 */           replacementString.NAME = ((PowerStrings)powerString.getValue()).NAME;
/*     */           
/* 183 */           if (((PowerStrings)powerString.getValue()).DESCRIPTIONS != null) {
/* 184 */             replacementString.DESCRIPTIONS = filterString(((PowerStrings)powerString.getValue()).DESCRIPTIONS);
/*     */           }
/*     */           
/* 187 */           if (replacementString.DESCRIPTIONS != null && !Arrays.equals((Object[])replacementString.DESCRIPTIONS, (Object[])((PowerStrings)powerString.getValue()).DESCRIPTIONS)) {
/* 188 */             renameP.put(powerString.getKey(), new PowerStrings[] { powerString.getValue(), replacementString });
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 193 */       Map<String, UIStrings> uiStrings = (Map<String, UIStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "ui");
/* 194 */       if (uiStrings != null)
/*     */       {
/* 196 */         for (Map.Entry<String, UIStrings> uiString : uiStrings.entrySet()) {
/* 197 */           UIStrings replacementString = new UIStrings();
/* 198 */           replacementString.EXTRA_TEXT = ((UIStrings)uiString.getValue()).EXTRA_TEXT;
/* 199 */           replacementString.TEXT_DICT = ((UIStrings)uiString.getValue()).TEXT_DICT;
/*     */           
/* 201 */           if (((UIStrings)uiString.getValue()).TEXT != null) {
/* 202 */             replacementString.TEXT = filterString(((UIStrings)uiString.getValue()).TEXT);
/*     */           }
/*     */           
/* 205 */           if (replacementString.TEXT != null && !Arrays.equals((Object[])replacementString.TEXT, (Object[])((UIStrings)uiString.getValue()).TEXT)) {
/* 206 */             renameUI.put(uiString.getKey(), new UIStrings[] { uiString.getValue(), replacementString });
/*     */           }
/*     */         } 
/*     */       }
/* 210 */     } catch (Exception e) {
/* 211 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void replaceStrings(boolean toSouls) {
/* 216 */     int tmp = toSouls ? 1 : 0;
/* 217 */     Map<String, CardStrings> cardStrings = (Map<String, CardStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "cards");
/* 218 */     for (Map.Entry<String, CardStrings[]> val : renameC.entrySet()) {
/* 219 */       cardStrings.put(val.getKey(), ((CardStrings[])val.getValue())[tmp]);
/*     */     }
/* 221 */     ReflectionHacks.setPrivateStaticFinal(LocalizedStrings.class, "cards", cardStrings);
/*     */     
/* 223 */     Map<String, EventStrings> eventStrings = (Map<String, EventStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "events");
/* 224 */     for (Map.Entry<String, EventStrings[]> val : renameE.entrySet()) {
/* 225 */       eventStrings.put(val.getKey(), ((EventStrings[])val.getValue())[tmp]);
/*     */     }
/* 227 */     ReflectionHacks.setPrivateStaticFinal(LocalizedStrings.class, "events", eventStrings);
/*     */     
/* 229 */     Map<String, RelicStrings> relicStrings = (Map<String, RelicStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "relics");
/* 230 */     for (Map.Entry<String, RelicStrings[]> val : renameR.entrySet()) {
/* 231 */       relicStrings.put(val.getKey(), ((RelicStrings[])val.getValue())[tmp]);
/*     */     }
/* 233 */     ReflectionHacks.setPrivateStaticFinal(LocalizedStrings.class, "relics", relicStrings);
/*     */     
/* 235 */     Map<String, CharacterStrings> characterStrings = (Map<String, CharacterStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "characters");
/* 236 */     for (Map.Entry<String, CharacterStrings[]> val : renameCh.entrySet()) {
/* 237 */       characterStrings.put(val.getKey(), ((CharacterStrings[])val.getValue())[tmp]);
/*     */     }
/* 239 */     ReflectionHacks.setPrivateStaticFinal(LocalizedStrings.class, "characters", characterStrings);
/*     */     
/* 241 */     Map<String, PowerStrings> powerStrings = (Map<String, PowerStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "powers");
/* 242 */     for (Map.Entry<String, PowerStrings[]> val : renameP.entrySet()) {
/* 243 */       powerStrings.put(val.getKey(), ((PowerStrings[])val.getValue())[tmp]);
/*     */     }
/* 245 */     ReflectionHacks.setPrivateStaticFinal(LocalizedStrings.class, "powers", powerStrings);
/*     */     
/* 247 */     Map<String, UIStrings> uiStrings = (Map<String, UIStrings>)ReflectionHacks.getPrivateStatic(LocalizedStrings.class, "ui");
/* 248 */     for (Map.Entry<String, UIStrings[]> val : renameUI.entrySet()) {
/* 249 */       uiStrings.put(val.getKey(), ((UIStrings[])val.getValue())[tmp]);
/*     */     }
/* 251 */     ReflectionHacks.setPrivateStaticFinal(LocalizedStrings.class, "ui", uiStrings);
/*     */ 
/*     */     
/* 254 */     String[] hack = (CardCrawlGame.languagePack.getUIString("CharacterOption")).TEXT;
/* 255 */     for (int i = 0; i < CharacterOption.TEXT.length; i++)
/*     */     {
/* 257 */       CharacterOption.TEXT[i] = hack[i];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void setFinalStatic(Field field, Object newValue) throws Exception {
/* 264 */     field.setAccessible(true);
/*     */     
/* 266 */     Field modifiersField = Field.class.getDeclaredField("modifiers");
/* 267 */     modifiersField.setAccessible(true);
/* 268 */     modifiersField.setInt(field, field.getModifiers() & 0xFFFFFFEF);
/*     */     
/* 270 */     field.set(null, newValue);
/*     */   }
/*     */   
/*     */   private static String[] filterString(String[] str) {
/* 274 */     String[] plsNoReplace = new String[str.length];
/* 275 */     System.arraycopy(str, 0, plsNoReplace, 0, str.length);
/* 276 */     for (int i = 0; i < str.length; i++) {
/* 277 */       plsNoReplace[i] = filterString(plsNoReplace[i]);
/*     */     }
/* 279 */     return plsNoReplace;
/*     */   }
/*     */   
/*     */   private static String filterString(String spireString) {
/* 283 */     String replacementString = spireString;
/*     */ 
/*     */     
/* 286 */     if (replacementString == null) {
/* 287 */       return "";
/*     */     }
/* 289 */     for (ReplaceData data : downfallMod.wordReplacements) {
/* 290 */       for (String phrase : data.KEYS) {
/* 291 */         if (data.VALUE == null) {
/* 292 */           data.VALUE = "";
/*     */         }
/* 294 */         if (phrase != null) {
/* 295 */           replacementString = replacementString.replaceAll(phrase, data.VALUE);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 301 */     return replacementString;
/*     */   }
/*     */   
/*     */   public static void UpdateMerchantTip() {
/* 305 */     String result = uiStrings.TEXT[3] + ": " + (FleeingMerchant.DEAD ? uiStrings.TEXT[4] : (FleeingMerchant.CURRENT_HP + "/" + 'Ɛ')) + " NL " + uiStrings.TEXT[5] + ": " + FleeingMerchant.CURRENT_STRENGTH + " NL " + uiStrings.TEXT[6] + ": " + FleeingMerchant.CURRENT_SOULS;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 313 */     MerchantTip.body = result;
/*     */   }
/*     */   
/*     */   @SpirePatch(clz = TopPanel.class, method = "renderGold")
/*     */   public static class BlueSoulsText {
/*     */     public static boolean firstInstance = true;
/*     */     
/*     */     public static ExprEditor Instrument() {
/* 321 */       return new ExprEditor()
/*     */         {
/*     */           public void edit(MethodCall m) throws CannotCompileException {
/* 324 */             if (m.getMethodName().equals("renderFontLeftTopAligned") && GoldToSoulPatches.BlueSoulsText.firstInstance) {
/* 325 */               GoldToSoulPatches.BlueSoulsText.firstInstance = false;
/* 326 */               m.replace("{if(" + EvilModeCharacterSelect.class
/* 327 */                   .getName() + ".evilMode) {$proceed($1, $2, $3, $4, $5, " + Color.class
/* 328 */                   .getName() + ".SKY);} else {$proceed($$);}}");
/*     */             } 
/*     */           }
/*     */         };
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = TopPanel.class, method = "updateTips")
/*     */   public static class MerchantTipPatch
/*     */   {
/*     */     public static void Prefix(TopPanel __instance) {
/* 343 */       if (__instance.goldHb.hovered && EvilModeCharacterSelect.evilMode)
/* 344 */         TipHelper.queuePowerTips(InputHelper.mX - (
/* 345 */             (Float)ReflectionHacks.getPrivateStatic(TopPanel.class, "TIP_OFF_X")).floatValue(), (
/* 346 */             (Float)ReflectionHacks.getPrivateStatic(TopPanel.class, "TIP_Y")).floatValue(), new ArrayList(
/* 347 */               Arrays.asList((Object[])new PowerTip[] { GoldToSoulPatches.SoulsTip, GoldToSoulPatches.MerchantTip }))); 
/*     */     }
/*     */   }
/*     */   
/*     */   @SpirePatch(clz = CardCrawlGame.class, method = "create")
/*     */   public static class PostLoadLocalizationPatch
/*     */   {
/*     */     @SpireInsertPatch(locator = Locator.class, localvars = {"languagePack"})
/*     */     public static void PostLocalization(CardCrawlGame __instance, LocalizedStrings languagePack) {
/* 356 */       for (Settings.GameLanguage lang : downfallMod.SupportedLanguages) {
/* 357 */         if (lang.equals(Settings.language)) {
/* 358 */           GoldToSoulPatches.postLoadLocalizationStrings();
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private static class Locator
/*     */       extends SpireInsertLocator
/*     */     {
/*     */       public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
/* 368 */         Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(Settings.class, "IS_FULLSCREEN");
/* 369 */         return LineFinder.findInOrder(ctMethodToPatch, (Matcher)fieldAccessMatcher);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patche\\ui\topPanel\GoldToSoulPatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */