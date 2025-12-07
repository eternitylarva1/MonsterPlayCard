/*     */ package downfall.events;
/*     */ 
/*     */ import champ.relics.SignatureFinisher;
/*     */ import collector.relics.BottledCollectible;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.curses.Pain;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import com.megacrit.cardcrawl.relics.Circlet;
/*     */ import com.megacrit.cardcrawl.relics.NlothsGift;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import sneckomod.relics.D8;
/*     */ import sneckomod.relics.SneckoCommon;
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
/*     */ public class Nloth_Evil
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "downfall:Nloth";
/*     */   public static final String NAME;
/*     */   public static final String[] DESCRIPTIONS;
/*     */   public static final String[] OPTIONS;
/*     */   public static final String[] DESCRIPTIONSALT;
/*     */   public static final String[] OPTIONSALT;
/*  41 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("N'loth"); private static final String DIALOG_1; static {
/*  42 */     NAME = eventStrings.NAME;
/*  43 */     DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  44 */     OPTIONS = eventStrings.OPTIONS;
/*  45 */     DESCRIPTIONSALT = (CardCrawlGame.languagePack.getEventString("downfall:Nloth")).DESCRIPTIONS;
/*  46 */     OPTIONSALT = (CardCrawlGame.languagePack.getEventString("downfall:Nloth")).OPTIONS;
/*  47 */     DIALOG_1 = DESCRIPTIONS[0];
/*  48 */     DIALOG_2 = DESCRIPTIONS[1];
/*  49 */     DIALOG_3 = DESCRIPTIONS[2];
/*     */   }
/*     */   private static final String DIALOG_2; private static final String DIALOG_3;
/*  52 */   private int screenNum = 0;
/*     */   private AbstractRelic choice1;
/*     */   private AbstractRelic choice2;
/*     */   private AbstractRelic gift;
/*     */   
/*     */   public Nloth_Evil() {
/*  58 */     super(NAME, DIALOG_1, "images/events/nloth.jpg");
/*  59 */     ArrayList<AbstractRelic> relics = new ArrayList<>();
/*  60 */     Iterator<AbstractRelic> relicIterator = relics.iterator();
/*  61 */     while (relicIterator.hasNext()) {
/*  62 */       AbstractRelic r = relicIterator.next();
/*     */       
/*  64 */       if (r.tier == AbstractRelic.RelicTier.STARTER || r.tier == AbstractRelic.RelicTier.BOSS || r.relicId
/*     */         
/*  66 */         .equals("Strawberry") || r.relicId
/*  67 */         .equals("MawBank") || r.relicId
/*  68 */         .equals("Guardian:PickAxe") || r.relicId
/*  69 */         .equals("Matryoshka") || r.relicId
/*  70 */         .equals("War Paint") || r.relicId
/*  71 */         .equals("Whetstone") || r.relicId.equals(SneckoCommon.ID) || r.relicId.equals("Potion Belt") || r.relicId
/*     */         
/*  73 */         .equals("Bottled Flame") || r.relicId
/*  74 */         .equals("Bottled Lightning") || r.relicId
/*  75 */         .equals("Bottled Tornado") || r.relicId
/*  76 */         .equals("Guardian:BottledStasis") || r.relicId
/*  77 */         .equals("Pear") || r.relicId
/*     */         
/*  79 */         .equals(BottledCollectible.ID) || r.relicId
/*  80 */         .equals("Lizard Tail") || r.relicId
/*  81 */         .equals("Mango") || r.relicId
/*  82 */         .equals(SignatureFinisher.ID) || r.relicId
/*  83 */         .equals("bronze:BottledCode") || r.relicId
/*  84 */         .equals("WingedGreaves") || r.relicId
/*     */ 
/*     */         
/*  87 */         .equals(D8.ID))
/*     */       {
/*  89 */         relicIterator.remove();
/*     */       }
/*     */     } 
/*     */     
/*  93 */     Collections.shuffle(relics, new Random(AbstractDungeon.miscRng.randomLong()));
/*  94 */     this.choice1 = relics.get(0);
/*  95 */     this.choice2 = relics.get(1);
/*  96 */     this.gift = (AbstractRelic)new NlothsGift();
/*  97 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.choice1.name + OPTIONS[1], (AbstractRelic)new NlothsGift());
/*  98 */     this.imageEventText.setDialogOption(OPTIONS[0] + this.choice2.name + OPTIONS[1], (AbstractRelic)new NlothsGift());
/*  99 */     this.imageEventText.setDialogOption(OPTIONSALT[0], (AbstractCard)new Pain());
/* 100 */     this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */   }
/*     */   
/*     */   public void onEnterRoom() {
/* 104 */     if (Settings.AMBIANCE_ON)
/* 105 */       CardCrawlGame.sound.play("EVENT_SERPENT"); 
/*     */   }
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*     */     Pain curse;
/*     */     AbstractRelic relic;
/* 111 */     switch (this.screenNum) {
/*     */       case 0:
/* 113 */         switch (buttonPressed) {
/*     */           case 0:
/* 115 */             this.imageEventText.updateBodyText(DIALOG_2);
/* 116 */             if (AbstractDungeon.player.hasRelic("Nloth's Gift")) {
/* 117 */               this.gift = (AbstractRelic)new Circlet();
/* 118 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), this.gift);
/*     */             } else {
/* 120 */               AbstractDungeon.player.loseRelic(this.choice1.relicId);
/* 121 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), this.gift);
/*     */             } 
/*     */             
/* 124 */             this.screenNum = 1;
/* 125 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/* 126 */             this.imageEventText.clearRemainingOptions();
/* 127 */             logMetricRelicSwap("downfall:Nloth", "Traded Relic", this.gift, this.choice1);
/*     */             return;
/*     */           case 1:
/* 130 */             this.imageEventText.updateBodyText(DIALOG_2);
/* 131 */             if (AbstractDungeon.player.hasRelic("Nloth's Gift")) {
/* 132 */               this.gift = (AbstractRelic)new Circlet();
/* 133 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), this.gift);
/*     */             } else {
/* 135 */               AbstractDungeon.player.loseRelic(this.choice2.relicId);
/* 136 */               AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), this.gift);
/*     */             } 
/*     */             
/* 139 */             this.screenNum = 1;
/* 140 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/* 141 */             this.imageEventText.clearRemainingOptions();
/* 142 */             logMetricRelicSwap("downfall:Nloth", "Traded Relic", this.gift, this.choice2);
/*     */             return;
/*     */           case 2:
/* 145 */             this.imageEventText.updateBodyText(DESCRIPTIONSALT[0]);
/* 146 */             curse = new Pain();
/* 147 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)curse, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/* 148 */             relic = AbstractDungeon.returnRandomRelic(AbstractDungeon.returnRandomRelicTier());
/* 149 */             AbstractDungeon.getCurrRoom().spawnRelicAndObtain((Settings.WIDTH / 2), (Settings.HEIGHT / 2), relic);
/* 150 */             CardCrawlGame.sound.play("BLUNT_HEAVY");
/* 151 */             logMetricObtainCardAndRelic("downfall:Nloth", "Punch", (AbstractCard)curse, relic);
/* 152 */             this.screenNum = 1;
/* 153 */             this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/* 154 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */         } 
/* 157 */         this.imageEventText.updateBodyText(DIALOG_3);
/* 158 */         this.screenNum = 1;
/* 159 */         this.imageEventText.updateDialogOption(0, OPTIONS[2]);
/* 160 */         this.imageEventText.clearRemainingOptions();
/* 161 */         logMetricIgnored("downfall:Nloth");
/*     */         return;
/*     */       
/*     */       case 1:
/* 165 */         openMap();
/*     */         return;
/*     */     } 
/* 168 */     openMap();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\events\Nloth_Evil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */