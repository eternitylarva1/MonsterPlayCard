/*     */ package downfall.events.shrines_evil;
/*     */ 
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import downfall.cards.curses.Aged;
/*     */ import downfall.downfallMod;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DuplicatorEvil
/*     */   extends AbstractImageEvent
/*     */ {
/*  24 */   public static final String ID = downfallMod.makeID("Duplicator");
/*     */   
/*  26 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Duplicator");
/*  27 */   public static final String NAME = eventStrings.NAME;
/*  28 */   public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  29 */   public static final String[] OPTIONS = eventStrings.OPTIONS;
/*     */   
/*     */   public static String[] DESCRIPTIONSALT;
/*     */   
/*     */   public static String[] OPTIONSALT;
/*  34 */   private int screenNum = 0;
/*  35 */   private static final String DIALOG_1 = DESCRIPTIONS[0];
/*  36 */   private static final String DIALOG_2 = DESCRIPTIONS[1];
/*  37 */   private static final String IGNORE = DESCRIPTIONS[2];
/*     */   
/*     */   public DuplicatorEvil() {
/*  40 */     super(NAME, DIALOG_1, "images/events/shrine4.jpg");
/*  41 */     DESCRIPTIONSALT = (CardCrawlGame.languagePack.getEventString("downfall:EvilShrines")).DESCRIPTIONS;
/*  42 */     OPTIONSALT = (CardCrawlGame.languagePack.getEventString("downfall:EvilShrines")).OPTIONS;
/*     */     
/*  44 */     this.imageEventText.setDialogOption(OPTIONSALT[0], (AbstractCard)new Aged());
/*  45 */     this.imageEventText.setDialogOption(OPTIONS[0]);
/*  46 */     this.imageEventText.setDialogOption(OPTIONS[1]);
/*     */   }
/*     */   
/*     */   public void onEnterRoom() {
/*  50 */     CardCrawlGame.music.playTempBGM("SHRINE");
/*     */   }
/*     */   
/*     */   public void update() {
/*  54 */     super.update();
/*  55 */     if (!AbstractDungeon.isScreenUp && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
/*  56 */       ArrayList<String> cards = new ArrayList<>();
/*     */       
/*  58 */       AbstractCard c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(0)).makeStatEquivalentCopy();
/*  59 */       c.inBottleFlame = false;
/*  60 */       c.inBottleLightning = false;
/*  61 */       c.inBottleTornado = false;
/*  62 */       AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH * 0.25F, Settings.HEIGHT / 2.0F));
/*  63 */       cards.add(c.cardID);
/*     */       
/*  65 */       if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 1) {
/*  66 */         c = ((AbstractCard)AbstractDungeon.gridSelectScreen.selectedCards.get(1)).makeStatEquivalentCopy();
/*  67 */         c.inBottleFlame = false;
/*  68 */         c.inBottleLightning = false;
/*  69 */         c.inBottleTornado = false;
/*  70 */         AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, Settings.WIDTH * 0.75F, Settings.HEIGHT / 2.0F));
/*  71 */         cards.add(c.cardID);
/*     */         
/*  73 */         Aged aged = new Aged();
/*  74 */         cards.add(((AbstractCard)aged).cardID);
/*  75 */         AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)aged, Settings.WIDTH * 0.5F, (Settings.HEIGHT / 2)));
/*     */         
/*  77 */         logMetricObtainCards(ID, "Desecrated", cards);
/*     */       } else {
/*  79 */         logMetricObtainCard(ID, "Copied", c);
/*     */       } 
/*     */       
/*  82 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  88 */     switch (this.screenNum) {
/*     */       case 0:
/*  90 */         switch (buttonPressed) {
/*     */           case 0:
/*  92 */             this.imageEventText.updateBodyText(DESCRIPTIONSALT[0]);
/*  93 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/*  94 */             this.imageEventText.clearRemainingOptions();
/*  95 */             use(2);
/*  96 */             this.screenNum = 2;
/*     */             break;
/*     */           case 1:
/*  99 */             this.imageEventText.updateBodyText(DIALOG_2);
/* 100 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 101 */             this.imageEventText.clearRemainingOptions();
/* 102 */             use(1);
/* 103 */             this.screenNum = 2;
/*     */             break;
/*     */           case 2:
/* 106 */             this.screenNum = 2;
/* 107 */             this.imageEventText.updateBodyText(IGNORE);
/* 108 */             this.imageEventText.updateDialogOption(0, OPTIONS[1]);
/* 109 */             this.imageEventText.clearRemainingOptions();
/* 110 */             logMetricIgnored(ID);
/*     */             break;
/*     */         } 
/*     */         break;
/*     */       case 1:
/* 115 */         this.screenNum = 2;
/*     */         break;
/*     */       case 2:
/* 118 */         openMap();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void use(int count) {
/* 125 */     AbstractDungeon.gridSelectScreen.open(AbstractDungeon.player.masterDeck, count, OPTIONS[2], false, false, false, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\events\shrines_evil\DuplicatorEvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */