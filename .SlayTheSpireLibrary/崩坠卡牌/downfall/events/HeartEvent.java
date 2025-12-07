/*     */ package downfall.events;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.blights.AbstractBlight;
/*     */ import com.megacrit.cardcrawl.blights.GrotesqueTrophy;
/*     */ import com.megacrit.cardcrawl.blights.MimicInfestation;
/*     */ import com.megacrit.cardcrawl.blights.Muzzle;
/*     */ import com.megacrit.cardcrawl.blights.Shield;
/*     */ import com.megacrit.cardcrawl.blights.Spear;
/*     */ import com.megacrit.cardcrawl.blights.TimeMaze;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.CardGroup;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.RoomEventDialog;
/*     */ import com.megacrit.cardcrawl.helpers.ModHelper;
/*     */ import com.megacrit.cardcrawl.helpers.SaveHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipTracker;
/*     */ import com.megacrit.cardcrawl.localization.CharacterStrings;
/*     */ import com.megacrit.cardcrawl.random.Random;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
/*     */ import com.megacrit.cardcrawl.vfx.InfiniteSpeechBubble;
/*     */ import com.megacrit.cardcrawl.vfx.scene.LevelTransitionTextOverlayEffect;
/*     */ import downfall.downfallMod;
/*     */ import downfall.patches.EvilModeCharacterSelect;
/*     */ import downfall.util.HeartReward;
/*     */ import downfall.vfx.CustomAnimatedNPC;
/*     */ import downfall.vfx.TopLevelInfiniteSpeechBubble;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class HeartEvent extends AbstractEvent {
/*  44 */   private static final Logger logger = LogManager.getLogger(HeartEvent.class.getName());
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
/*     */   
/*     */   public HeartEvent(boolean isDone) {
/*  64 */     dismissBubble();
/*  65 */     this.screenNum = 2;
/*  66 */     this.setPhaseToEvent = false;
/*  67 */     this.rewards = new ArrayList<>();
/*  68 */     this.pickCard = false;
/*  69 */     waitingToSave = false;
/*  70 */     if (this.npc == null) {
/*  71 */       this.npc = new CustomAnimatedNPC(1334.0F * Settings.scale, AbstractDungeon.floorY + 200.0F * Settings.scale, "images/npcs/heart/skeleton.atlas", "images/npcs/heart/skeleton.json", "idle", true, 0);
/*     */     }
/*  73 */     this.npc.portalRenderActive = true;
/*  74 */     this.npc.changeBorderColor(Color.MAROON);
/*     */     
/*  76 */     this.roomEventText.clear();
/*  77 */     playSfx();
/*  78 */     if (!Settings.isEndless || AbstractDungeon.floorNum <= 1) {
/*  79 */       if (Settings.isStandardRun() || (Settings.isEndless && AbstractDungeon.floorNum <= 1)) {
/*  80 */         this.bossCount = CardCrawlGame.playerPref.getInteger(AbstractDungeon.player.chosenClass.name() + "_SPIRITS", 0);
/*  81 */         AbstractDungeon.bossCount = this.bossCount;
/*  82 */       } else if (Settings.seedSet) {
/*  83 */         this.bossCount = 1;
/*     */       } else {
/*  85 */         this.bossCount = 0;
/*     */       } 
/*     */     }
/*     */     
/*  89 */     this.body = "";
/*  90 */     if (Settings.isEndless && AbstractDungeon.floorNum > 1) {
/*  91 */       talk(TEXT[MathUtils.random(12, 14)]);
/*  92 */       this.screenNum = 999;
/*  93 */       this.roomEventText.addDialogOption(OPTIONS[0]);
/*  94 */     } else if (shouldSkipNeowDialog()) {
/*  95 */       this.screenNum = 10;
/*  96 */       talk(TEXT[10]);
/*  97 */       this.roomEventText.addDialogOption(OPTIONS[1]);
/*  98 */     } else if (!isDone) {
/*  99 */       if (!((Boolean)TipTracker.tips.get("NEOW_INTRO")).booleanValue()) {
/* 100 */         this.screenNum = 0;
/* 101 */         TipTracker.neverShowAgain("NEOW_INTRO");
/* 102 */         talk(TEXT[0]);
/* 103 */         this.roomEventText.addDialogOption(OPTIONS[1]);
/*     */       } else {
/* 105 */         this.screenNum = 1;
/* 106 */         talk(TEXT[MathUtils.random(1, 3)]);
/* 107 */         this.roomEventText.addDialogOption(OPTIONS[1]);
/*     */       } 
/*     */       
/* 110 */       AbstractDungeon.topLevelEffects.add(new LevelTransitionTextOverlayEffect(AbstractDungeon.name, AbstractDungeon.levelNum, true));
/*     */     } else {
/* 112 */       this.screenNum = 99;
/* 113 */       talk(TEXT[8]);
/* 114 */       this.roomEventText.addDialogOption(OPTIONS[3]);
/*     */     } 
/*     */     
/* 117 */     this.hasDialog = true;
/* 118 */     this.hasFocus = true;
/*     */     
/* 120 */     if (EvilModeCharacterSelect.evilMode) {
/* 121 */       Settings.isFinalActAvailable = true;
/* 122 */       AbstractDungeon.topPanel.setPlayerName();
/*     */     } 
/*     */   }
/*     */   
/*     */   public HeartEvent() {
/* 127 */     this(false);
/*     */   }
/*     */   
/*     */   private boolean shouldSkipNeowDialog() {
/* 131 */     if (Settings.seedSet && !Settings.isTrial && !Settings.isDailyRun) {
/* 132 */       return false;
/*     */     }
/* 134 */     return !Settings.isStandardRun();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasPlayedRun(AbstractPlayer.PlayerClass p) {
/* 139 */     return (UnlockTracker.getCurrentProgress(p) > 0 || UnlockTracker.getUnlockLevel(p) > 0);
/*     */   }
/*     */   
/*     */   public void update() {
/* 143 */     super.update();
/*     */     
/* 145 */     this.npc.update();
/*     */ 
/*     */     
/* 148 */     if (this.pickCard && !AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/* 149 */       CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/* 150 */       Iterator<AbstractCard> var2 = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
/*     */       
/* 152 */       while (var2.hasNext()) {
/* 153 */         AbstractCard c = var2.next();
/* 154 */         group.addToBottom(c.makeCopy());
/*     */       } 
/*     */       
/* 157 */       AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, TEXT[11]);
/* 158 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*     */     } 
/*     */     
/* 161 */     for (HeartReward r : this.rewards) {
/* 162 */       r.update();
/*     */     }
/*     */     
/* 165 */     if (!this.setPhaseToEvent) {
/* 166 */       (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.EVENT;
/* 167 */       downfallMod.readyToDoThing = true;
/* 168 */       this.setPhaseToEvent = true;
/*     */     } 
/*     */     
/* 171 */     if (!RoomEventDialog.waitForInput) {
/* 172 */       buttonEffect(this.roomEventText.getSelectedOption());
/*     */     }
/*     */     
/* 175 */     if (waitingToSave && !AbstractDungeon.isScreenUp && AbstractDungeon.topLevelEffects.isEmpty() && AbstractDungeon.player.relicsDoneAnimating()) {
/* 176 */       boolean doneAnims = true;
/* 177 */       Iterator<AbstractRelic> var2 = AbstractDungeon.player.relics.iterator();
/*     */       
/* 179 */       while (var2.hasNext()) {
/* 180 */         AbstractRelic r = var2.next();
/* 181 */         if (!r.isDone) {
/* 182 */           doneAnims = false;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 187 */       if (doneAnims) {
/* 188 */         waitingToSave = false;
/* 189 */         SaveHelper.saveIfAppropriate(SaveFile.SaveType.POST_NEOW);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void talk(String msg) {
/* 197 */     if (this.speechBubble != null) this.speechBubble.dismiss(); 
/* 198 */     this.speechBubble = new TopLevelInfiniteSpeechBubble(DIALOG_X, DIALOG_Y, msg);
/* 199 */     AbstractDungeon.topLevelEffects.add(this.speechBubble);
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/* 203 */     switch (this.screenNum) {
/*     */       case 0:
/* 205 */         dismissBubble();
/* 206 */         talk(TEXT[4]);
/* 207 */         if (this.bossCount == 0 && !Settings.isTestingNeow) {
/* 208 */           miniBlessing();
/*     */         } else {
/* 210 */           blessing();
/*     */         } 
/*     */         return;
/*     */       case 1:
/* 214 */         dismissBubble();
/* 215 */         logger.info(Integer.valueOf(this.bossCount));
/* 216 */         if (this.bossCount == 0 && !Settings.isTestingNeow) {
/* 217 */           miniBlessing();
/*     */         } else {
/* 219 */           blessing();
/*     */         } 
/*     */         return;
/*     */       case 2:
/* 223 */         if (buttonPressed == 0) {
/* 224 */           blessing();
/*     */         } else {
/* 226 */           if (this.speechBubble != null) this.speechBubble.dismiss(); 
/* 227 */           openMap();
/*     */         } 
/*     */         return;
/*     */       case 3:
/* 231 */         dismissBubble();
/* 232 */         this.roomEventText.clearRemainingOptions();
/* 233 */         switch (buttonPressed) {
/*     */           case 0:
/* 235 */             ((HeartReward)this.rewards.get(0)).activate();
/* 236 */             talk(TEXT[8]);
/*     */             break;
/*     */           case 1:
/* 239 */             ((HeartReward)this.rewards.get(1)).activate();
/* 240 */             talk(TEXT[8]);
/*     */             break;
/*     */           case 2:
/* 243 */             ((HeartReward)this.rewards.get(2)).activate();
/* 244 */             talk(TEXT[9]);
/*     */             break;
/*     */           case 3:
/* 247 */             ((HeartReward)this.rewards.get(3)).activate();
/* 248 */             talk(TEXT[9]);
/*     */             break;
/*     */         } 
/* 251 */         this.screenNum = 99;
/* 252 */         this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/* 253 */         this.roomEventText.clearRemainingOptions();
/* 254 */         waitingToSave = true;
/*     */         return;
/*     */       case 10:
/* 257 */         dailyBlessing();
/* 258 */         this.roomEventText.clearRemainingOptions();
/* 259 */         this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/* 260 */         this.screenNum = 99;
/*     */         return;
/*     */       case 999:
/* 263 */         endlessBlight();
/* 264 */         this.roomEventText.clearRemainingOptions();
/* 265 */         this.roomEventText.updateDialogOption(0, OPTIONS[3]);
/* 266 */         this.screenNum = 99;
/*     */         return;
/*     */     } 
/* 269 */     if (this.speechBubble != null) this.speechBubble.dismiss(); 
/* 270 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void endlessBlight() {
/* 277 */     if (AbstractDungeon.player.hasBlight("DeadlyEnemies")) {
/* 278 */       AbstractBlight tmp = AbstractDungeon.player.getBlight("DeadlyEnemies");
/* 279 */       tmp.incrementUp();
/* 280 */       tmp.flash();
/*     */     } else {
/* 282 */       AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new Spear());
/*     */     } 
/*     */     
/* 285 */     if (AbstractDungeon.player.hasBlight("ToughEnemies")) {
/* 286 */       AbstractBlight tmp = AbstractDungeon.player.getBlight("ToughEnemies");
/* 287 */       tmp.incrementUp();
/* 288 */       tmp.flash();
/*     */     } else {
/* 290 */       AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new Shield());
/*     */     } 
/*     */     
/* 293 */     uniqueBlight();
/*     */   }
/*     */   
/*     */   private void uniqueBlight() {
/* 297 */     AbstractBlight temp = AbstractDungeon.player.getBlight("MimicInfestation");
/* 298 */     if (temp != null) {
/* 299 */       temp = AbstractDungeon.player.getBlight("TimeMaze");
/* 300 */       if (temp != null) {
/* 301 */         temp = AbstractDungeon.player.getBlight("FullBelly");
/* 302 */         if (temp != null) {
/* 303 */           temp = AbstractDungeon.player.getBlight("GrotesqueTrophy");
/* 304 */           if (temp != null) {
/* 305 */             AbstractDungeon.player.getBlight("GrotesqueTrophy").stack();
/*     */           } else {
/* 307 */             AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new GrotesqueTrophy());
/*     */           } 
/*     */         } else {
/* 310 */           AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new Muzzle());
/*     */         } 
/*     */       } else {
/* 313 */         AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new TimeMaze());
/*     */       } 
/*     */     } else {
/*     */       
/* 317 */       AbstractDungeon.getCurrRoom().spawnBlightAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractBlight)new MimicInfestation());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void dailyBlessing() {
/* 322 */     rng = new Random(Settings.seed);
/* 323 */     dismissBubble();
/* 324 */     talk(TEXT[8]);
/* 325 */     if (ModHelper.isModEnabled("Heirloom")) {
/* 326 */       AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.RARE));
/*     */     }
/*     */     
/* 329 */     boolean addedCards = false;
/* 330 */     CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*     */     
/* 332 */     if (ModHelper.isModEnabled("Allstar")) {
/* 333 */       addedCards = true;
/*     */       
/* 335 */       for (int i = 0; i < 5; i++) {
/* 336 */         AbstractCard card = AbstractDungeon.getColorlessCardFromPool(AbstractDungeon.rollRareOrUncommon(0.5F));
/* 337 */         UnlockTracker.markCardAsSeen(card.cardID);
/* 338 */         group.addToBottom(card.makeCopy());
/*     */       } 
/*     */     } 
/*     */     
/* 342 */     if (ModHelper.isModEnabled("Specialized"))
/*     */     {
/* 344 */       if (!ModHelper.isModEnabled("SealedDeck") && !ModHelper.isModEnabled("Draft")) {
/* 345 */         addedCards = true;
/* 346 */         AbstractCard rareCard = AbstractDungeon.returnTrulyRandomCard();
/* 347 */         UnlockTracker.markCardAsSeen(rareCard.cardID);
/* 348 */         group.addToBottom(rareCard.makeCopy());
/* 349 */         group.addToBottom(rareCard.makeCopy());
/* 350 */         group.addToBottom(rareCard.makeCopy());
/* 351 */         group.addToBottom(rareCard.makeCopy());
/* 352 */         group.addToBottom(rareCard.makeCopy());
/*     */       } else {
/* 354 */         AbstractCard rareCard = AbstractDungeon.returnTrulyRandomCard();
/*     */         
/* 356 */         for (int i = 0; i < 5; i++) {
/* 357 */           AbstractCard tmpCard = rareCard.makeCopy();
/* 358 */           AbstractDungeon.topLevelEffectsQueue.add(new FastCardObtainEffect(tmpCard, MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 363 */     if (addedCards) {
/* 364 */       AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, TEXT[11]);
/*     */     }
/*     */     
/* 367 */     if (ModHelper.isModEnabled("Draft")) {
/* 368 */       AbstractDungeon.cardRewardScreen.draftOpen();
/*     */     }
/*     */     
/* 371 */     this.pickCard = true;
/* 372 */     if (ModHelper.isModEnabled("SealedDeck")) {
/* 373 */       CardGroup sealedGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
/*     */       
/* 375 */       for (int i = 0; i < 30; i++) {
/* 376 */         AbstractCard card = AbstractDungeon.getCard(AbstractDungeon.rollRarity());
/* 377 */         if (!sealedGroup.contains(card)) {
/* 378 */           sealedGroup.addToBottom(card.makeCopy());
/*     */         } else {
/* 380 */           i--;
/*     */         } 
/*     */       } 
/*     */       
/* 384 */       for (AbstractCard c : sealedGroup.group) {
/* 385 */         UnlockTracker.markCardAsSeen(c.cardID);
/*     */       }
/*     */       
/* 388 */       AbstractDungeon.gridSelectScreen.open(sealedGroup, 10, OPTIONS[4], false);
/*     */     } 
/*     */     
/* 391 */     this.roomEventText.clearRemainingOptions();
/* 392 */     this.screenNum = 99;
/*     */   }
/*     */   
/*     */   private void miniBlessing() {
/* 396 */     AbstractDungeon.bossCount = 0;
/* 397 */     dismissBubble();
/* 398 */     talk(TEXT[MathUtils.random(4, 6)]);
/* 399 */     this.rewards.add(new HeartReward(true));
/* 400 */     this.rewards.add(new HeartReward(false));
/* 401 */     this.roomEventText.removeDialogOption(0);
/* 402 */     if (hasPlayedRun(AbstractDungeon.player.chosenClass)) {
/* 403 */       this.roomEventText.addDialogOption(OPTIONS[5]);
/*     */     } else {
/* 405 */       this.roomEventText.addDialogOption(OPTIONS[6], true);
/*     */     } 
/* 407 */     this.roomEventText.addDialogOption(((HeartReward)this.rewards.get(1)).optionLabel);
/* 408 */     this.screenNum = 3;
/*     */   }
/*     */   
/*     */   private void blessing() {
/* 412 */     logger.info("BLESSING");
/* 413 */     rng = new Random(Settings.seed);
/* 414 */     logger.info("COUNTER: " + rng.counter);
/* 415 */     AbstractDungeon.bossCount = 0;
/* 416 */     dismissBubble();
/* 417 */     talk(TEXT[7]);
/* 418 */     this.rewards.add(new HeartReward(0));
/* 419 */     this.rewards.add(new HeartReward(1));
/* 420 */     this.rewards.add(new HeartReward(2));
/* 421 */     this.rewards.add(new HeartReward(3));
/* 422 */     this.roomEventText.clearRemainingOptions();
/* 423 */     this.roomEventText.updateDialogOption(0, ((HeartReward)this.rewards.get(0)).optionLabel);
/* 424 */     this.roomEventText.addDialogOption(((HeartReward)this.rewards.get(1)).optionLabel);
/* 425 */     this.roomEventText.addDialogOption(((HeartReward)this.rewards.get(2)).optionLabel);
/* 426 */     this.roomEventText.addDialogOption(((HeartReward)this.rewards.get(3)).optionLabel);
/* 427 */     this.screenNum = 3;
/*     */   }
/*     */ 
/*     */   
/*     */   private void dismissBubble() {
/* 432 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/* 433 */       if (e instanceof InfiniteSpeechBubble) {
/* 434 */         ((InfiniteSpeechBubble)e).dismiss();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void playSfx() {
/* 441 */     CardCrawlGame.sound.play("HEART_SIMPLE");
/*     */   }
/*     */   
/*     */   public void logMetric(String actionTaken) {
/* 445 */     AbstractEvent.logMetric(NAME, actionTaken);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 449 */     this.npc.render(sb);
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 453 */     super.dispose();
/* 454 */     if (this.npc != null) {
/* 455 */       logger.info("Disposing Neow asset.");
/* 456 */       this.npc.dispose();
/* 457 */       this.npc = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 463 */   private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("Heart Event"));
/* 464 */   public static final String[] NAMES = characterStrings.NAMES;
/* 465 */   public static final String[] TEXT = characterStrings.TEXT;
/* 466 */   public static final String[] OPTIONS = characterStrings.OPTIONS; private CustomAnimatedNPC npc;
/* 467 */   public static final String NAME = NAMES[0]; private int screenNum; private int bossCount;
/* 468 */   public static Random rng = null; private boolean setPhaseToEvent; private ArrayList<HeartReward> rewards; private boolean pickCard;
/*     */   public static boolean waitingToSave = false;
/* 470 */   private static final float DIALOG_X = 1200.0F * Settings.scale;
/* 471 */   private static final float DIALOG_Y = 500.0F * Settings.scale;
/*     */   private TopLevelInfiniteSpeechBubble speechBubble;
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\events\HeartEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */