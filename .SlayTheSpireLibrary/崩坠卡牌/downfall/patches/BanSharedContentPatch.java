/*     */ package downfall.patches;
/*     */ import automaton.relics.BronzeIdol;
/*     */ import automaton.relics.DecasWashers;
/*     */ import collector.cards.WhirlingFlame;
/*     */ import collector.relics.AutoCurser;
/*     */ import collector.relics.Bagpipes;
/*     */ import collector.relics.RoughDiamond;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import downfall.cards.curses.Aged;
/*     */ import downfall.cards.curses.Bewildered;
/*     */ import downfall.cards.curses.Haunted;
/*     */ import downfall.cards.curses.Icky;
/*     */ import downfall.cards.curses.Malfunctioning;
/*     */ import downfall.cards.curses.Sapped;
/*     */ import downfall.cards.curses.Scatterbrained;
/*     */ import downfall.downfallMod;
/*     */ import expansioncontent.actions.RandomCardWithTagAction;
/*     */ import expansioncontent.cards.AwakenDeath;
/*     */ import expansioncontent.cards.BeatOfDeath;
/*     */ import expansioncontent.cards.BloodBarrage;
/*     */ import expansioncontent.cards.ChargeUp;
/*     */ import expansioncontent.cards.Chronoboost;
/*     */ import expansioncontent.cards.DashGenerateEvil;
/*     */ import expansioncontent.cards.DoubleAct;
/*     */ import expansioncontent.cards.FaceSlap;
/*     */ import expansioncontent.cards.Flail;
/*     */ import expansioncontent.cards.GoopSpray;
/*     */ import expansioncontent.cards.GuardianWhirl;
/*     */ import expansioncontent.cards.Hexaburn;
/*     */ import expansioncontent.cards.HyperBeam;
/*     */ import expansioncontent.cards.InvincibleStrength;
/*     */ import expansioncontent.cards.LastStand;
/*     */ import expansioncontent.cards.ManipulateTime;
/*     */ import expansioncontent.cards.Pandemonium;
/*     */ import expansioncontent.cards.PeekPages;
/*     */ import expansioncontent.cards.QuickStudy;
/*     */ import expansioncontent.cards.ShapePower;
/*     */ import expansioncontent.cards.StudyTheSpire;
/*     */ import expansioncontent.cards.SuperBloodthirst;
/*     */ import expansioncontent.cards.SuperBodyCrash;
/*     */ import expansioncontent.cards.SuperClobber;
/*     */ import expansioncontent.cards.SuperEtherStep;
/*     */ import expansioncontent.cards.SuperGhostShield;
/*     */ import expansioncontent.cards.SuperPrepareCrush;
/*     */ import expansioncontent.cards.SuperSomberShield;
/*     */ import expansioncontent.cards.TakeFlight;
/*     */ import expansioncontent.cards.Virus;
/*     */ import expansioncontent.cards.YouAreMine;
/*     */ import gremlin.relics.ImpeccablePecs;
/*     */ import guardian.relics.GemstoneGun;
/*     */ import hermit.relics.RyeStalk;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import sneckomod.relics.BlankCard;
/*     */ import sneckomod.relics.SuperSneckoEye;
/*     */ import theHexaghost.relics.BolsterEngine;
/*     */ import theHexaghost.relics.Sixitude;
/*     */ 
/*     */ public class BanSharedContentPatch {
/*  63 */   private static Map<AbstractPlayer.PlayerClass, List<String>> runLockedPotions = new HashMap<>();
/*     */   
/*     */   public static void registerRunLockedPotion(AbstractPlayer.PlayerClass playerClass, String potionId) {
/*  66 */     ((List<String>)runLockedPotions.computeIfAbsent(playerClass, _ignore -> new ArrayList())).add(potionId);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = AbstractDungeon.class, method = "initializeCardPools")
/*     */   public static class CardPatch
/*     */   {
/*     */     public static void Postfix(AbstractDungeon __instance) {
/*  75 */       if (!EvilModeCharacterSelect.evilMode && !downfallMod.contentSharing_colorlessCards) {
/*  76 */         AbstractDungeon.colorlessCardPool.removeCard(AwakenDeath.ID);
/*  77 */         AbstractDungeon.colorlessCardPool.removeCard(Chronoboost.ID);
/*  78 */         AbstractDungeon.colorlessCardPool.removeCard(DashGenerateEvil.ID);
/*  79 */         AbstractDungeon.colorlessCardPool.removeCard(GuardianWhirl.ID);
/*  80 */         AbstractDungeon.colorlessCardPool.removeCard(Hexaburn.ID);
/*  81 */         AbstractDungeon.colorlessCardPool.removeCard(HyperBeam.ID);
/*  82 */         AbstractDungeon.colorlessCardPool.removeCard(LastStand.ID);
/*  83 */         AbstractDungeon.colorlessCardPool.removeCard(ShapePower.ID);
/*  84 */         AbstractDungeon.colorlessCardPool.removeCard(SuperPrepareCrush.ID);
/*  85 */         AbstractDungeon.colorlessCardPool.removeCard(QuickStudy.ID);
/*  86 */         AbstractDungeon.colorlessCardPool.removeCard(StudyTheSpire.ID);
/*  87 */         AbstractDungeon.colorlessCardPool.removeCard(YouAreMine.ID);
/*  88 */         AbstractDungeon.colorlessCardPool.removeCard(InvincibleStrength.ID);
/*     */         
/*  90 */         AbstractDungeon.colorlessCardPool.removeCard(BeatOfDeath.ID);
/*  91 */         AbstractDungeon.colorlessCardPool.removeCard(BloodBarrage.ID);
/*  92 */         AbstractDungeon.colorlessCardPool.removeCard(ChargeUp.ID);
/*  93 */         AbstractDungeon.colorlessCardPool.removeCard(DoubleAct.ID);
/*  94 */         AbstractDungeon.colorlessCardPool.removeCard(FaceSlap.ID);
/*  95 */         AbstractDungeon.colorlessCardPool.removeCard(Flail.ID);
/*  96 */         AbstractDungeon.colorlessCardPool.removeCard(GoopSpray.ID);
/*  97 */         AbstractDungeon.colorlessCardPool.removeCard(ManipulateTime.ID);
/*  98 */         AbstractDungeon.colorlessCardPool.removeCard(Pandemonium.ID);
/*  99 */         AbstractDungeon.colorlessCardPool.removeCard(PeekPages.ID);
/* 100 */         AbstractDungeon.colorlessCardPool.removeCard(SuperBloodthirst.ID);
/* 101 */         AbstractDungeon.colorlessCardPool.removeCard(SuperBodyCrash.ID);
/* 102 */         AbstractDungeon.colorlessCardPool.removeCard(SuperClobber.ID);
/*     */         
/* 104 */         AbstractDungeon.colorlessCardPool.removeCard(SuperEtherStep.ID);
/* 105 */         AbstractDungeon.colorlessCardPool.removeCard(SuperGhostShield.ID);
/* 106 */         AbstractDungeon.colorlessCardPool.removeCard("expansioncontent:SuperLivingWall");
/* 107 */         AbstractDungeon.colorlessCardPool.removeCard(SuperSomberShield.ID);
/* 108 */         AbstractDungeon.colorlessCardPool.removeCard(TakeFlight.ID);
/* 109 */         AbstractDungeon.colorlessCardPool.removeCard(Virus.ID);
/*     */         
/* 111 */         AbstractDungeon.srcColorlessCardPool.removeCard(AwakenDeath.ID);
/* 112 */         AbstractDungeon.srcColorlessCardPool.removeCard(Chronoboost.ID);
/* 113 */         AbstractDungeon.srcColorlessCardPool.removeCard(DashGenerateEvil.ID);
/* 114 */         AbstractDungeon.srcColorlessCardPool.removeCard(GuardianWhirl.ID);
/* 115 */         AbstractDungeon.srcColorlessCardPool.removeCard(Hexaburn.ID);
/* 116 */         AbstractDungeon.srcColorlessCardPool.removeCard(HyperBeam.ID);
/* 117 */         AbstractDungeon.srcColorlessCardPool.removeCard(LastStand.ID);
/* 118 */         AbstractDungeon.srcColorlessCardPool.removeCard(ShapePower.ID);
/* 119 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperPrepareCrush.ID);
/* 120 */         AbstractDungeon.srcColorlessCardPool.removeCard(QuickStudy.ID);
/* 121 */         AbstractDungeon.srcColorlessCardPool.removeCard(StudyTheSpire.ID);
/* 122 */         AbstractDungeon.srcColorlessCardPool.removeCard(YouAreMine.ID);
/* 123 */         AbstractDungeon.srcColorlessCardPool.removeCard(InvincibleStrength.ID);
/*     */         
/* 125 */         AbstractDungeon.srcColorlessCardPool.removeCard(BeatOfDeath.ID);
/* 126 */         AbstractDungeon.srcColorlessCardPool.removeCard(BloodBarrage.ID);
/* 127 */         AbstractDungeon.srcColorlessCardPool.removeCard(ChargeUp.ID);
/* 128 */         AbstractDungeon.srcColorlessCardPool.removeCard(DoubleAct.ID);
/* 129 */         AbstractDungeon.srcColorlessCardPool.removeCard(FaceSlap.ID);
/* 130 */         AbstractDungeon.srcColorlessCardPool.removeCard(Flail.ID);
/* 131 */         AbstractDungeon.srcColorlessCardPool.removeCard(GoopSpray.ID);
/* 132 */         AbstractDungeon.srcColorlessCardPool.removeCard(ManipulateTime.ID);
/* 133 */         AbstractDungeon.srcColorlessCardPool.removeCard(Pandemonium.ID);
/* 134 */         AbstractDungeon.srcColorlessCardPool.removeCard(PeekPages.ID);
/* 135 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperBloodthirst.ID);
/* 136 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperBodyCrash.ID);
/* 137 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperClobber.ID);
/*     */         
/* 139 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperEtherStep.ID);
/* 140 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperGhostShield.ID);
/* 141 */         AbstractDungeon.srcColorlessCardPool.removeCard("expansioncontent:SuperLivingWall");
/* 142 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperSomberShield.ID);
/* 143 */         AbstractDungeon.srcColorlessCardPool.removeCard(TakeFlight.ID);
/* 144 */         AbstractDungeon.srcColorlessCardPool.removeCard(Virus.ID);
/*     */ 
/*     */         
/* 147 */         AbstractDungeon.curseCardPool.removeCard(Aged.ID);
/* 148 */         AbstractDungeon.curseCardPool.removeCard(Icky.ID);
/* 149 */         AbstractDungeon.curseCardPool.removeCard(Bewildered.ID);
/* 150 */         AbstractDungeon.curseCardPool.removeCard(Haunted.ID);
/* 151 */         AbstractDungeon.curseCardPool.removeCard("Pride");
/* 152 */         AbstractDungeon.curseCardPool.removeCard(Malfunctioning.ID);
/* 153 */         AbstractDungeon.curseCardPool.removeCard(Scatterbrained.ID);
/* 154 */         AbstractDungeon.curseCardPool.removeCard(Sapped.ID);
/*     */         
/* 156 */         AbstractDungeon.srcCurseCardPool.removeCard(Aged.ID);
/* 157 */         AbstractDungeon.srcCurseCardPool.removeCard(Icky.ID);
/* 158 */         AbstractDungeon.srcCurseCardPool.removeCard(Bewildered.ID);
/* 159 */         AbstractDungeon.srcCurseCardPool.removeCard(Haunted.ID);
/* 160 */         AbstractDungeon.srcCurseCardPool.removeCard("Pride");
/* 161 */         AbstractDungeon.srcCurseCardPool.removeCard(Malfunctioning.ID);
/* 162 */         AbstractDungeon.srcCurseCardPool.removeCard(Scatterbrained.ID);
/* 163 */         AbstractDungeon.srcCurseCardPool.removeCard(Sapped.ID);
/*     */       } 
/* 165 */       if (AbstractDungeon.player instanceof slimebound.characters.SlimeboundCharacter) {
/* 166 */         AbstractDungeon.colorlessCardPool.removeCard(SuperPrepareCrush.ID);
/* 167 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperPrepareCrush.ID);
/*     */         
/* 169 */         AbstractDungeon.colorlessCardPool.removeCard(GoopSpray.ID);
/* 170 */         AbstractDungeon.srcColorlessCardPool.removeCard(GoopSpray.ID);
/*     */         
/* 172 */         AbstractDungeon.colorlessCardPool.removeCard("expansioncontent:SuperLivingWall");
/* 173 */         AbstractDungeon.srcColorlessCardPool.removeCard("expansioncontent:SuperLivingWall");
/*     */       } 
/* 175 */       if (AbstractDungeon.player instanceof theHexaghost.TheHexaghost || RandomCardWithTagAction.hexaLocked()) {
/* 176 */         AbstractDungeon.colorlessCardPool.removeCard(Hexaburn.ID);
/* 177 */         AbstractDungeon.srcColorlessCardPool.removeCard(Hexaburn.ID);
/*     */         
/* 179 */         AbstractDungeon.colorlessCardPool.removeCard(SuperGhostShield.ID);
/* 180 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperGhostShield.ID);
/*     */         
/* 182 */         AbstractDungeon.colorlessCardPool.removeCard(SuperEtherStep.ID);
/* 183 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperEtherStep.ID);
/*     */       } 
/* 185 */       if (AbstractDungeon.player instanceof guardian.characters.GuardianCharacter || RandomCardWithTagAction.guardianLocked()) {
/* 186 */         AbstractDungeon.colorlessCardPool.removeCard(GuardianWhirl.ID);
/* 187 */         AbstractDungeon.srcColorlessCardPool.removeCard(GuardianWhirl.ID);
/*     */         
/* 189 */         AbstractDungeon.colorlessCardPool.removeCard(ChargeUp.ID);
/* 190 */         AbstractDungeon.srcColorlessCardPool.removeCard(ChargeUp.ID);
/*     */         
/* 192 */         AbstractDungeon.colorlessCardPool.removeCard(SuperBodyCrash.ID);
/* 193 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperBodyCrash.ID);
/*     */       } 
/* 195 */       if (AbstractDungeon.player instanceof champ.ChampChar || RandomCardWithTagAction.champLocked()) {
/* 196 */         AbstractDungeon.colorlessCardPool.removeCard(LastStand.ID);
/* 197 */         AbstractDungeon.srcColorlessCardPool.removeCard(LastStand.ID);
/*     */         
/* 199 */         AbstractDungeon.colorlessCardPool.removeCard(SuperClobber.ID);
/* 200 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperClobber.ID);
/*     */         
/* 202 */         AbstractDungeon.colorlessCardPool.removeCard(FaceSlap.ID);
/* 203 */         AbstractDungeon.srcColorlessCardPool.removeCard(FaceSlap.ID);
/*     */       } 
/* 205 */       if (AbstractDungeon.player instanceof automaton.AutomatonChar || RandomCardWithTagAction.autoLocked()) {
/* 206 */         AbstractDungeon.colorlessCardPool.removeCard(HyperBeam.ID);
/* 207 */         AbstractDungeon.srcColorlessCardPool.removeCard(HyperBeam.ID);
/*     */         
/* 209 */         AbstractDungeon.colorlessCardPool.removeCard(Virus.ID);
/* 210 */         AbstractDungeon.srcColorlessCardPool.removeCard(Virus.ID);
/*     */         
/* 212 */         AbstractDungeon.colorlessCardPool.removeCard(Flail.ID);
/* 213 */         AbstractDungeon.srcColorlessCardPool.removeCard(Flail.ID);
/*     */       } 
/* 215 */       if (AbstractDungeon.player instanceof collector.CollectorChar || RandomCardWithTagAction.collectorLocked()) {
/* 216 */         AbstractDungeon.colorlessCardPool.removeCard(YouAreMine.ID);
/* 217 */         AbstractDungeon.srcColorlessCardPool.removeCard(YouAreMine.ID);
/*     */         
/* 219 */         AbstractDungeon.colorlessCardPool.removeCard(SuperSomberShield.ID);
/* 220 */         AbstractDungeon.srcColorlessCardPool.removeCard(SuperSomberShield.ID);
/*     */         
/* 222 */         AbstractDungeon.colorlessCardPool.removeCard(SuperWhirlingFlame.ID);
/* 223 */         AbstractDungeon.srcColorlessCardPool.removeCard(WhirlingFlame.ID);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = AbstractDungeon.class, method = "initializeRelicList")
/*     */   public static class RelicPatch
/*     */   {
/*     */     public static void Prefix(AbstractDungeon __instance) {
/* 236 */       if (!EvilModeCharacterSelect.evilMode && !downfallMod.contentSharing_relics) {
/*     */         
/* 238 */         AbstractDungeon.relicsToRemoveOnStart.add(AutoCurser.ID);
/* 239 */         AbstractDungeon.relicsToRemoveOnStart.add(Bagpipes.ID);
/* 240 */         AbstractDungeon.relicsToRemoveOnStart.add(Barbells.ID);
/* 241 */         AbstractDungeon.relicsToRemoveOnStart.add("Guardian:BottledAnomaly");
/* 242 */         AbstractDungeon.relicsToRemoveOnStart.add(BlankCard.ID);
/* 243 */         AbstractDungeon.relicsToRemoveOnStart.add(BolsterEngine.ID);
/* 244 */         AbstractDungeon.relicsToRemoveOnStart.add(BronzeIdol.ID);
/* 245 */         AbstractDungeon.relicsToRemoveOnStart.add(BrassTacks.ID);
/* 246 */         AbstractDungeon.relicsToRemoveOnStart.add(BloodyTooth.ID);
/*     */         
/* 248 */         AbstractDungeon.relicsToRemoveOnStart.add(DeflectingBracers.ID);
/* 249 */         AbstractDungeon.relicsToRemoveOnStart.add(DuelingGlove.ID);
/* 250 */         AbstractDungeon.relicsToRemoveOnStart.add(DecasWashers.ID);
/* 251 */         AbstractDungeon.relicsToRemoveOnStart.add(DonusWashers.ID);
/* 252 */         AbstractDungeon.relicsToRemoveOnStart.add(ForbiddenFruit.ID);
/* 253 */         AbstractDungeon.relicsToRemoveOnStart.add(FuelCanister.ID);
/* 254 */         AbstractDungeon.relicsToRemoveOnStart.add(GemstoneGun.ID);
/* 255 */         AbstractDungeon.relicsToRemoveOnStart.add(ImpeccablePecs.ID);
/* 256 */         AbstractDungeon.relicsToRemoveOnStart.add(Incense.ID);
/* 257 */         AbstractDungeon.relicsToRemoveOnStart.add(MakeshiftBattery.ID);
/* 258 */         AbstractDungeon.relicsToRemoveOnStart.add("Guardian:PocketSentry");
/* 259 */         AbstractDungeon.relicsToRemoveOnStart.add(PricklyShields.ID);
/* 260 */         AbstractDungeon.relicsToRemoveOnStart.add("Slimebound:PreparedRelic");
/* 261 */         AbstractDungeon.relicsToRemoveOnStart.add(RoughDiamond.ID);
/* 262 */         AbstractDungeon.relicsToRemoveOnStart.add(RyeStalk.ID);
/* 263 */         AbstractDungeon.relicsToRemoveOnStart.add("Slimebound:StickyStick");
/* 264 */         AbstractDungeon.relicsToRemoveOnStart.add(StudyCardRelic.ID);
/* 265 */         AbstractDungeon.relicsToRemoveOnStart.add(SuperSneckoEye.ID);
/* 266 */         AbstractDungeon.relicsToRemoveOnStart.add(SneckoTalon.ID);
/* 267 */         AbstractDungeon.relicsToRemoveOnStart.add(Sixitude.ID);
/* 268 */         AbstractDungeon.relicsToRemoveOnStart.add(SupplyScroll.ID);
/*     */       } 
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
/*     */   @SpirePatch(clz = PotionHelper.class, method = "initialize")
/*     */   public static class PotionPatch
/*     */   {
/*     */     public static void Postfix(AbstractPlayer.PlayerClass chosenClass) {
/* 285 */       if (EvilModeCharacterSelect.evilMode || !downfallMod.contentSharing_potions);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\BanSharedContentPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */