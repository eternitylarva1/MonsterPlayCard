/*     */ package downfall.events;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.potions.AbstractPotion;
/*     */ import com.megacrit.cardcrawl.rewards.RewardItem;
/*     */ import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
/*     */ import downfall.downfallMod;
/*     */ import downfall.potions.CursedFountainPotion;
/*     */ import gremlin.characters.GremlinCharacter;
/*     */ import java.util.Collections;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CursedFountain
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "downfall:CursedFountain";
/*     */   public static final String NAME;
/*     */   public static final String[] DESCRIPTIONS;
/*     */   public static final String[] OPTIONS;
/*  26 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("downfall:CursedFountain"); static {
/*  27 */     NAME = eventStrings.NAME;
/*  28 */     DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  29 */     OPTIONS = eventStrings.OPTIONS;
/*     */   }
/*     */   
/*  32 */   private int screenNum = 0;
/*  33 */   private int curseCount = 0;
/*     */   private int goldAmt;
/*     */   
/*     */   public CursedFountain() {
/*  37 */     super(NAME, DESCRIPTIONS[0], downfallMod.assetPath("images/events/cursedFountain.png"));
/*  38 */     this.noCardsInRewards = true;
/*  39 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  40 */       this.goldAmt = 25;
/*     */     } else {
/*  42 */       this.goldAmt = 75;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onEnterRoom() {
/*  47 */     CardCrawlGame.music.playTempBGM("SHRINE");
/*  48 */     if (Settings.AMBIANCE_ON) {
/*  49 */       CardCrawlGame.sound.play("EVENT_FOUNTAIN", -0.2F);
/*     */     }
/*  51 */     for (int i = AbstractDungeon.player.masterDeck.group.size() - 1; i >= 0; i--) {
/*  52 */       if (((AbstractCard)AbstractDungeon.player.masterDeck.group.get(i)).type == AbstractCard.CardType.CURSE) {
/*  53 */         this.curseCount++;
/*     */       }
/*     */     } 
/*  56 */     if (this.curseCount >= 1) {
/*  57 */       this.imageEventText.setDialogOption(OPTIONS[0]);
/*     */     } else {
/*  59 */       this.imageEventText.setDialogOption(OPTIONS[3], true);
/*     */     } 
/*  61 */     if (this.curseCount >= 2) {
/*  62 */       this.imageEventText.setDialogOption(OPTIONS[1]);
/*     */     } else {
/*  64 */       this.imageEventText.setDialogOption(OPTIONS[4], true);
/*     */     } 
/*  66 */     if (this.curseCount >= 3) {
/*  67 */       this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */     } else {
/*  69 */       this.imageEventText.setDialogOption(OPTIONS[5], true);
/*     */     } 
/*  71 */     this.imageEventText.setDialogOption(OPTIONS[7]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  76 */     switch (this.screenNum) {
/*     */       case 0:
/*  78 */         switch (buttonPressed) {
/*     */           
/*     */           case 0:
/*  81 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/*  82 */             this.imageEventText.updateDialogOption(0, OPTIONS[6], true);
/*  83 */             (AbstractDungeon.getCurrRoom()).rewards.clear();
/*  84 */             (AbstractDungeon.getCurrRoom()).rewards.add(new RewardItem((AbstractPotion)new CursedFountainPotion()));
/*  85 */             AbstractDungeon.combatRewardScreen.open();
/*  86 */             logMetric("downfall:CursedFountain", "Bottle", null, null, null, null, null, 
/*  87 */                 Collections.singletonList("downfall:CursedFountainPotion"), null, 0, 0, 0, 0, 0, 0);
/*     */             return;
/*     */ 
/*     */           
/*     */           case 1:
/*  92 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*  93 */             this.imageEventText.updateDialogOption(1, OPTIONS[6], true);
/*  94 */             AbstractDungeon.effectList.add(new RainingGoldEffect(this.goldAmt));
/*  95 */             AbstractDungeon.player.gainGold(this.goldAmt);
/*  96 */             logMetricGainGold("downfall:CursedFountain", "Consume", this.goldAmt);
/*     */             return;
/*     */           
/*     */           case 2:
/* 100 */             this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
/* 101 */             this.imageEventText.updateDialogOption(2, OPTIONS[6], true);
/* 102 */             logMetricHeal("downfall:CursedFountain", "Drink", AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
/* 103 */             AbstractDungeon.player.heal(AbstractDungeon.player.maxHealth);
/* 104 */             if (AbstractDungeon.player instanceof GremlinCharacter) {
/* 105 */               ((GremlinCharacter)AbstractDungeon.player).healGremlins(AbstractDungeon.player.maxHealth);
/*     */             }
/*     */             return;
/*     */           case 3:
/* 109 */             this.imageEventText.updateBodyText(DESCRIPTIONS[4]);
/* 110 */             this.imageEventText.clearAllDialogs();
/* 111 */             this.imageEventText.setDialogOption(OPTIONS[7]);
/* 112 */             this.screenNum = 1;
/* 113 */             logMetricIgnored("downfall:CursedFountain"); return;
/*     */         } 
/*     */         break;
/*     */     } 
/* 117 */     openMap();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\events\CursedFountain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */