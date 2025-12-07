/*     */ package downfall.events;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.colorless.JAX;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.events.city.DrugDealer;
/*     */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*     */ import com.megacrit.cardcrawl.helpers.MonsterHelper;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.relics.Circlet;
/*     */ import com.megacrit.cardcrawl.relics.MutagenicStrength;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import downfall.downfallMod;
/*     */ import downfall.util.JaxReward;
/*     */ import downfall.util.TransformCardReward;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class Augmenter_Evil
/*     */   extends AbstractImageEvent {
/*  28 */   public static final String ID = downfallMod.makeID("Augmenter");
/*     */   public static final String NAME;
/*     */   public static final String[] DESCRIPTIONS;
/*     */   public static final String[] OPTIONS;
/*     */   public static final String[] DESCRIPTIONSALT;
/*     */   public static final String[] OPTIONSALT;
/*  34 */   private static final Logger logger = LogManager.getLogger(DrugDealer.class.getName());
/*     */ 
/*     */ 
/*     */   
/*  38 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Drug Dealer"); static {
/*  39 */     NAME = eventStrings.NAME;
/*  40 */     DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  41 */     OPTIONS = eventStrings.OPTIONS;
/*  42 */     DESCRIPTIONSALT = (CardCrawlGame.languagePack.getEventString(ID)).DESCRIPTIONS;
/*  43 */     OPTIONSALT = (CardCrawlGame.languagePack.getEventString(ID)).OPTIONS;
/*     */   }
/*     */   
/*  46 */   private int screenNum = 0;
/*     */   private boolean cardsSelected = false;
/*     */   
/*     */   public Augmenter_Evil() {
/*  50 */     super(NAME, DESCRIPTIONSALT[0], "images/events/drugDealer.jpg");
/*  51 */     this.imageEventText.setDialogOption(OPTIONS[0], CardLibrary.getCopy("J.A.X."));
/*  52 */     if (AbstractDungeon.player.masterDeck.getPurgeableCards().size() >= 2) {
/*  53 */       this.imageEventText.setDialogOption(OPTIONS[1]);
/*     */     } else {
/*  55 */       this.imageEventText.setDialogOption(OPTIONS[4], true);
/*     */     } 
/*     */     
/*  58 */     this.imageEventText.setDialogOption(OPTIONS[2], (AbstractRelic)new MutagenicStrength());
/*  59 */     this.imageEventText.setDialogOption(OPTIONSALT[0]);
/*     */   } protected void buttonEffect(int buttonPressed) {
/*     */     JAX jAX;
/*     */     Object r;
/*  63 */     switch (this.screenNum) {
/*     */       case 0:
/*  65 */         switch (buttonPressed) {
/*     */           case 0:
/*  67 */             jAX = new JAX();
/*  68 */             logMetricObtainCard(ID, "Obtain J.A.X.", (AbstractCard)jAX);
/*  69 */             this.imageEventText.updateBodyText(DESCRIPTIONS[1]);
/*  70 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)jAX, (Settings.WIDTH / 2), (Settings.HEIGHT / 2)));
/*  71 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  72 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           case 1:
/*  75 */             this.imageEventText.updateBodyText(DESCRIPTIONS[2]);
/*  76 */             transform();
/*  77 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  78 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           case 2:
/*  81 */             this.imageEventText.updateBodyText(DESCRIPTIONS[3]);
/*     */             
/*  83 */             if (!AbstractDungeon.player.hasRelic("MutagenicStrength")) {
/*  84 */               r = new MutagenicStrength();
/*  85 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, (AbstractRelic)r);
/*     */             } else {
/*  87 */               r = new Circlet();
/*  88 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain(this.drawX, this.drawY, (AbstractRelic)r);
/*     */             } 
/*     */             
/*  91 */             logMetricObtainRelic(ID, "Inject Mutagens", (AbstractRelic)r);
/*  92 */             this.imageEventText.updateDialogOption(0, OPTIONS[3]);
/*  93 */             this.imageEventText.clearRemainingOptions();
/*     */             break;
/*     */           
/*     */           case 3:
/*  97 */             logMetric(ID, "Fight");
/*     */             
/*  99 */             (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("downfall:Augmenter");
/* 100 */             (AbstractDungeon.getCurrRoom()).rewards.clear();
/* 101 */             if (Settings.isDailyRun) {
/* 102 */               AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(30));
/*     */             } else {
/* 104 */               AbstractDungeon.getCurrRoom().addGoldToRewards(AbstractDungeon.miscRng.random(25, 35));
/*     */             } 
/* 106 */             AbstractDungeon.getCurrRoom().addRelicToRewards((AbstractRelic)new MutagenicStrength());
/* 107 */             (AbstractDungeon.getCurrRoom()).rewards.add(new TransformCardReward());
/* 108 */             (AbstractDungeon.getCurrRoom()).rewards.add(new TransformCardReward());
/* 109 */             (AbstractDungeon.getCurrRoom()).rewards.add(new JaxReward());
/* 110 */             (AbstractDungeon.getCurrRoom()).eliteTrigger = true;
/* 111 */             this.imageEventText.clearRemainingOptions();
/* 112 */             enterCombatFromImage();
/* 113 */             AbstractDungeon.lastCombatMetricKey = "downfall:Augmenter";
/*     */             break;
/*     */           default:
/* 116 */             logger.info("ERROR: Unhandled case " + buttonPressed);
/*     */             break;
/*     */         } 
/* 119 */         this.screenNum = 1;
/*     */         break;
/*     */       case 1:
/* 122 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/* 128 */     super.update();
/* 129 */     if (!this.cardsSelected) {
/* 130 */       List<String> transformedCards = new ArrayList<>();
/* 131 */       List<String> obtainedCards = new ArrayList<>();
/* 132 */       if (AbstractDungeon.gridSelectScreen.selectedCards.size() == 2) {
/* 133 */         this.cardsSelected = true;
/* 134 */         float displayCount = 0.0F;
/* 135 */         Iterator<AbstractCard> i = AbstractDungeon.gridSelectScreen.selectedCards.iterator();
/*     */         
/* 137 */         while (i.hasNext()) {
/* 138 */           AbstractCard card = i.next();
/* 139 */           card.untip();
/* 140 */           card.unhover();
/* 141 */           transformedCards.add(card.cardID);
/* 142 */           AbstractDungeon.player.masterDeck.removeCard(card);
/* 143 */           AbstractDungeon.transformCard(card, false, AbstractDungeon.miscRng);
/* 144 */           AbstractCard c = AbstractDungeon.getTransformedCard();
/* 145 */           obtainedCards.add(c.cardID);
/* 146 */           if (AbstractDungeon.screen != AbstractDungeon.CurrentScreen.TRANSFORM && c != null) {
/* 147 */             AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(c.makeCopy(), Settings.WIDTH / 3.0F + displayCount, Settings.HEIGHT / 2.0F, false));
/* 148 */             displayCount += Settings.WIDTH / 6.0F;
/*     */           } 
/*     */         } 
/*     */         
/* 152 */         AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 153 */         logMetricTransformCards(ID, "Became Test Subject", transformedCards, obtainedCards);
/* 154 */         (AbstractDungeon.getCurrRoom()).rewardPopOutTimer = 0.25F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void transform() {
/* 161 */     if (!AbstractDungeon.isScreenUp) {
/* 162 */       AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 2, OPTIONS[5], false, false, false, false);
/*     */     } else {
/* 164 */       AbstractDungeon.dynamicBanner.hide();
/* 165 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/* 166 */       AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck.getPurgeableCards(), 2, OPTIONS[5], false, false, false, false);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\events\Augmenter_Evil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */