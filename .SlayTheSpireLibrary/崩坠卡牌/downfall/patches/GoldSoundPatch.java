/*     */ package downfall.patches;
/*     */ 
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*     */ import com.megacrit.cardcrawl.audio.SoundMaster;
/*     */ 
/*     */ public class GoldSoundPatch
/*     */ {
/*     */   public static boolean active = true;
/*     */   
/*     */   @SpirePatch(clz = SoundMaster.class, method = "playV", paramtypez = {String.class, float.class})
/*     */   public static class GoldSoundPatchV {
/*     */     public static SpireReturn Prefix(SoundMaster __instance, String key, float volumemod) {
/*  15 */       String s = GoldSoundPatch.findReplacement(key);
/*  16 */       if (s == "nope") {
/*  17 */         return SpireReturn.Continue();
/*     */       }
/*     */       
/*  20 */       __instance.playV(s, volumemod);
/*  21 */       return SpireReturn.Return(Long.valueOf(GoldSoundPatch.bruh()));
/*     */     }
/*     */   }
/*     */   
/*     */   @SpirePatch(clz = SoundMaster.class, method = "playA", paramtypez = {String.class, float.class})
/*     */   public static class GoldSoundPatchA
/*     */   {
/*     */     public static SpireReturn Prefix(SoundMaster __instance, String key, float pitchAdjust) {
/*  29 */       String s = GoldSoundPatch.findReplacement(key);
/*  30 */       if (s == "nope") {
/*  31 */         return SpireReturn.Continue();
/*     */       }
/*     */       
/*  34 */       __instance.playA(s, pitchAdjust);
/*  35 */       return SpireReturn.Return(Long.valueOf(GoldSoundPatch.bruh()));
/*     */     }
/*     */   }
/*     */   
/*     */   @SpirePatch(clz = SoundMaster.class, method = "play", paramtypez = {String.class})
/*     */   public static class GoldSoundPatchNormal
/*     */   {
/*     */     public static SpireReturn Prefix(SoundMaster __instance, String key) {
/*  43 */       String s = GoldSoundPatch.findReplacement(key);
/*  44 */       if (s == "nope") {
/*  45 */         return SpireReturn.Continue();
/*     */       }
/*     */       
/*  48 */       __instance.play(s);
/*  49 */       return SpireReturn.Return(Long.valueOf(GoldSoundPatch.bruh()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = SoundMaster.class, method = "play", paramtypez = {String.class, boolean.class})
/*     */   public static class GoldSoundPatchNormal2
/*     */   {
/*     */     public static SpireReturn Prefix(SoundMaster __instance, String key, boolean useBgmVolume) {
/*  58 */       String s = GoldSoundPatch.findReplacement(key);
/*  59 */       if (s == "nope") {
/*  60 */         return SpireReturn.Continue();
/*     */       }
/*     */       
/*  63 */       __instance.play(s, useBgmVolume);
/*  64 */       return SpireReturn.Return(Long.valueOf(GoldSoundPatch.bruh()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = SoundMaster.class, method = "play", paramtypez = {String.class, float.class})
/*     */   public static class GoldSoundPatchNormal3
/*     */   {
/*     */     public static SpireReturn Prefix(SoundMaster __instance, String key, float pitchVariation) {
/*  73 */       String s = GoldSoundPatch.findReplacement(key);
/*  74 */       if (s == "nope") {
/*  75 */         return SpireReturn.Continue();
/*     */       }
/*     */       
/*  78 */       __instance.play(s, pitchVariation);
/*  79 */       return SpireReturn.Return(Long.valueOf(GoldSoundPatch.bruh()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = SoundMaster.class, method = "playAV", paramtypez = {String.class, float.class, float.class})
/*     */   public static class GoldSoundPatchAV
/*     */   {
/*     */     public static SpireReturn Prefix(SoundMaster __instance, String key, float pitchAdjust, float volumeMod) {
/*  88 */       String s = GoldSoundPatch.findReplacement(key);
/*  89 */       if (s == "nope") {
/*  90 */         return SpireReturn.Continue();
/*     */       }
/*     */       
/*  93 */       __instance.playAV(s, pitchAdjust, volumeMod);
/*  94 */       return SpireReturn.Return(Long.valueOf(GoldSoundPatch.bruh()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static long bruh() {
/* 100 */     return 100L;
/*     */   }
/*     */   
/*     */   public static String findReplacement(String key) {
/* 104 */     if (!EvilModeCharacterSelect.evilMode || !active) {
/* 105 */       return "nope";
/*     */     }
/* 107 */     if (key.equals("GOLD_GAIN"))
/* 108 */       return "souls2"; 
/* 109 */     if (key.equals("GOLD_GAIN_2"))
/* 110 */       return "souls1"; 
/* 111 */     if (key.equals("GOLD_GAIN_3"))
/* 112 */       return "souls2"; 
/* 113 */     if (key.equals("GOLD_GAIN_4"))
/* 114 */       return "souls4"; 
/* 115 */     if (key.equals("GOLD_GAIN_5"))
/* 116 */       return "souls5"; 
/* 117 */     if (key.equals("GOLD_JINGLE"))
/* 118 */       return "soulsMain"; 
/* 119 */     if (key.equals("SHOP_PURCHASE")) {
/* 120 */       int rand = MathUtils.random(1, 5);
/* 121 */       String result = "nope";
/* 122 */       switch (rand) {
/*     */         case 1:
/* 124 */           result = "souls1";
/*     */           break;
/*     */         case 2:
/* 127 */           result = "souls2";
/*     */           break;
/*     */         case 3:
/* 130 */           result = "souls3";
/*     */           break;
/*     */         case 4:
/* 133 */           result = "souls4";
/*     */           break;
/*     */         case 5:
/* 136 */           result = "souls5";
/*     */           break;
/*     */       } 
/* 139 */       return result;
/*     */     } 
/* 141 */     return "nope";
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\GoldSoundPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */