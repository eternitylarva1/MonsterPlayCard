/*     */ package downfall.events;
/*     */ 
/*     */ import basemod.abstracts.AbstractCardModifier;
/*     */ import basemod.helpers.CardModifierManager;
/*     */ import collector.CollectorChar;
/*     */ import collector.CollectorCollection;
/*     */ import collector.cardmods.CollectedCardMod;
/*     */ import collector.cards.collectibles.VagrantCard;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import downfall.cards.curses.PrideStandard;
/*     */ 
/*     */ 
/*     */ public class Vagrant_Evil
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "downfall:Vagrant";
/*     */   public static final String NAME;
/*     */   public static final String[] DESCRIPTIONS;
/*     */   public static final String[] OPTIONS;
/*     */   public static final String[] DESCRIPTIONSALT;
/*     */   public static final String[] OPTIONSALT;
/*  31 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Addict"); static {
/*  32 */     NAME = eventStrings.NAME;
/*  33 */     DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  34 */     OPTIONS = eventStrings.OPTIONS;
/*  35 */     DESCRIPTIONSALT = (CardCrawlGame.languagePack.getEventString("downfall:Vagrant")).DESCRIPTIONS;
/*  36 */     OPTIONSALT = (CardCrawlGame.languagePack.getEventString("downfall:Vagrant")).OPTIONS;
/*     */   }
/*     */   
/*  39 */   private int screenNum = 0;
/*  40 */   private int takeCost = 0;
/*     */   
/*     */   public Vagrant_Evil() {
/*  43 */     super(NAME, DESCRIPTIONS[0], "images/events/addict.jpg");
/*  44 */     this.takeCost = Math.round(AbstractDungeon.player.getAscensionMaxHPLoss() * 1.5F);
/*  45 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  46 */       this.takeCost = (int)(this.takeCost * 1.5F);
/*     */     }
/*  48 */     this.imageEventText.setDialogOption(OPTIONSALT[0] + this.takeCost + OPTIONSALT[1]);
/*  49 */     this.imageEventText.setDialogOption(OPTIONSALT[2], (AbstractCard)new PrideStandard());
/*  50 */     if (AbstractDungeon.player.chosenClass.equals(CollectorChar.Enums.THE_COLLECTOR)) {
/*  51 */       VagrantCard vagrantCard = new VagrantCard();
/*  52 */       CardModifierManager.addModifier((AbstractCard)vagrantCard, (AbstractCardModifier)new CollectedCardMod());
/*  53 */       this.imageEventText.setDialogOption(CollectorChar.COLLECTORTAKE, (AbstractCard)vagrantCard);
/*     */     } else {
/*     */       
/*  56 */       this.imageEventText.setDialogOption(OPTIONS[5]);
/*     */     }  } protected void buttonEffect(int buttonPressed) {
/*     */     int gold;
/*     */     PrideStandard prideStandard;
/*     */     AbstractRelic relic;
/*  61 */     switch (this.screenNum) {
/*     */       case 0:
/*  63 */         switch (buttonPressed) {
/*     */           case 0:
/*  65 */             AbstractDungeon.player.damage(new DamageInfo(null, this.takeCost, DamageInfo.DamageType.HP_LOSS));
/*  66 */             gold = 85;
/*  67 */             AbstractDungeon.player.gainGold(gold);
/*  68 */             logMetricGainGoldAndDamage("downfall:Vagrant", "Punch", gold, this.takeCost);
/*  69 */             this.imageEventText.updateBodyText(DESCRIPTIONSALT[0]);
/*  70 */             this.screenNum = 1;
/*  71 */             this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/*  72 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */           case 1:
/*  75 */             this.imageEventText.updateBodyText(DESCRIPTIONSALT[1]);
/*  76 */             prideStandard = new PrideStandard();
/*  77 */             relic = AbstractDungeon.returnRandomScreenlessRelic(AbstractDungeon.returnRandomRelicTier());
/*  78 */             AbstractEvent.logMetricObtainCardAndRelic("downfall:Vagrant", "Stole Relic", (AbstractCard)prideStandard, relic);
/*  79 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)prideStandard, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*  80 */             AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, relic);
/*  81 */             this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/*  82 */             this.imageEventText.clearRemainingOptions();
/*  83 */             this.screenNum = 1;
/*     */             return;
/*     */           case 2:
/*  86 */             if (AbstractDungeon.player.chosenClass.equals(CollectorChar.Enums.THE_COLLECTOR)) {
/*  87 */               this.imageEventText.updateBodyText(DESCRIPTIONSALT[2]);
/*  88 */               this.imageEventText.clearAllDialogs();
/*  89 */               this.imageEventText.setDialogOption(OPTIONS[3]);
/*  90 */               VagrantCard vagrantCard = new VagrantCard();
/*  91 */               CardModifierManager.addModifier((AbstractCard)vagrantCard, (AbstractCardModifier)new CollectedCardMod());
/*  92 */               CollectorCollection.collection.addToTop((AbstractCard)vagrantCard);
/*     */               
/*  94 */               logMetric("downfall:Vagrant", "Take", null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0);
/*     */               
/*  96 */               this.screenNum = 1;
/*     */             } 
/*     */             break;
/*     */         } 
/* 100 */         logMetricIgnored("downfall:Vagrant");
/* 101 */         this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/* 102 */         this.imageEventText.clearRemainingOptions();
/* 103 */         this.screenNum = 1;
/* 104 */         openMap();
/*     */         break;
/*     */     } 
/* 107 */     openMap();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\events\Vagrant_Evil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */