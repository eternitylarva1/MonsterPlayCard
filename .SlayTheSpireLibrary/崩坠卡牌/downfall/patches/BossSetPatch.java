/*     */ package downfall.patches;
/*     */ 
/*     */ import charbosses.bosses.Defect.CharBossDefect;
/*     */ import charbosses.bosses.Hermit.CharBossHermit;
/*     */ import charbosses.bosses.Ironclad.CharBossIronclad;
/*     */ import charbosses.bosses.Silent.CharBossSilent;
/*     */ import charbosses.bosses.Watcher.CharBossWatcher;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.map.DungeonMap;
/*     */ import downfall.downfallMod;
/*     */ import downfall.monsters.NeowBoss;
/*     */ import downfall.monsters.NeowBossFinal;
/*     */ import downfall.util.TextureLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SpirePatch(clz = AbstractDungeon.class, method = "setBoss")
/*     */ public class BossSetPatch
/*     */ {
/*     */   @SpirePostfixPatch
/*     */   public static void Postfix(AbstractDungeon __instance, String key) {
/*  27 */     if (EvilModeCharacterSelect.evilMode) {
/*  28 */       AbstractDungeon.monsterList.replaceAll(s -> {
/*     */             switch (s) {
/*     */               case "Looter":
/*     */                 return downfallMod.makeID("LooterAlt");
/*     */               
/*     */               case "2 Thieves":
/*     */                 return downfallMod.makeID("LooterAlt2");
/*     */             } 
/*     */             return s;
/*     */           });
/*     */     }
/*  39 */     if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheEnding && EvilModeCharacterSelect.evilMode) {
/*     */       
/*  41 */       AbstractDungeon.bossList.clear();
/*     */       
/*  43 */       AbstractDungeon.bossList.add(NeowBossFinal.ID);
/*  44 */       AbstractDungeon.bossList.add(NeowBossFinal.ID);
/*  45 */       AbstractDungeon.bossList.add(NeowBossFinal.ID);
/*     */       
/*  47 */       AbstractDungeon.monsterList.clear();
/*  48 */       AbstractDungeon.eliteMonsterList.clear();
/*     */       
/*  50 */       AbstractDungeon.monsterList.add(NeowBoss.ID);
/*  51 */       AbstractDungeon.monsterList.add(NeowBoss.ID);
/*  52 */       AbstractDungeon.monsterList.add(NeowBoss.ID);
/*  53 */       AbstractDungeon.eliteMonsterList.add(NeowBoss.ID);
/*  54 */       AbstractDungeon.eliteMonsterList.add(NeowBoss.ID);
/*  55 */       AbstractDungeon.eliteMonsterList.add(NeowBoss.ID);
/*     */       
/*  57 */       key = NeowBossFinal.ID;
/*     */       
/*  59 */       AbstractDungeon.bossKey = key;
/*     */     } 
/*     */ 
/*     */     
/*  63 */     if (key.equals(CharBossIronclad.ID)) {
/*  64 */       if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheBeyond) {
/*  65 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/ironclad_bastion.png");
/*  66 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/ironclad_bastion_outline.png");
/*  67 */       } else if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheCity) {
/*  68 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/ironclad_shroom.png");
/*  69 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/ironclad_shroom_outline.png");
/*     */       } else {
/*  71 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/ironclad_status.png");
/*  72 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/ironclad_status_outline.png");
/*     */       } 
/*  74 */     } else if (key.equals(CharBossSilent.ID)) {
/*  75 */       if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheBeyond) {
/*  76 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/silent_shiv.png");
/*  77 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/silent_shiv_outline.png");
/*  78 */       } else if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheCity) {
/*  79 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/silent_mirror_image.png");
/*  80 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/silent_mirror_image_outline.png");
/*     */       } else {
/*  82 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/silent_poison.png");
/*  83 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/silent_poison_outline.png");
/*     */       } 
/*  85 */     } else if (key.equals(CharBossDefect.ID)) {
/*  86 */       if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheBeyond) {
/*  87 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/defect_focus.png");
/*  88 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/defect_focus_outline.png");
/*  89 */       } else if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheCity) {
/*  90 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/defect_bronze_orbs.png");
/*  91 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/defect_bronze_orbs_outline.png");
/*     */       } else {
/*  93 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/defect_void.png");
/*  94 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/defect_void_outline.png");
/*     */       } 
/*  96 */     } else if (key.equals(CharBossWatcher.ID)) {
/*  97 */       if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheBeyond) {
/*  98 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/watcher_divinity.png");
/*  99 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/watcher_divinity_outline.png");
/* 100 */       } else if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheCity) {
/* 101 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/watcher_blasphemy.png");
/* 102 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/watcher_blasphemy_outline.png");
/*     */       } else {
/* 104 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/watcher_wrath.png");
/* 105 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/watcher_wrath_outline.png");
/*     */       } 
/* 107 */     } else if (key.equals(CharBossHermit.ID)) {
/* 108 */       if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheBeyond) {
/* 109 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/hermit_curse.png");
/* 110 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/hermit_curse_outline.png");
/* 111 */       } else if (__instance instanceof com.megacrit.cardcrawl.dungeons.TheCity) {
/* 112 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/hermit_wheel.png");
/* 113 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/hermit_wheel_outline.png");
/*     */       } else {
/* 115 */         DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/hermit_sharpshooter.png");
/* 116 */         DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/hermit_sharpshooter_outline.png");
/*     */       } 
/* 118 */     } else if (key.equals(NeowBossFinal.ID)) {
/* 119 */       DungeonMap.boss = TextureLoader.getTexture("downfallResources/images/ui/map/icon/neow.png");
/* 120 */       DungeonMap.bossOutline = TextureLoader.getTexture("downfallResources/images/ui/map/outline/neow_outline.png");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\BossSetPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */