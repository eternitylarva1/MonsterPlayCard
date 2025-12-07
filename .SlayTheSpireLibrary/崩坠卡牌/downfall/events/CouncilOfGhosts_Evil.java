/*     */ package downfall.events;
/*     */ 
/*     */ import basemod.abstracts.AbstractCardModifier;
/*     */ import basemod.helpers.CardModifierManager;
/*     */ import collector.CollectorChar;
/*     */ import collector.CollectorCollection;
/*     */ import collector.cardmods.CollectedCardMod;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.colorless.Apparition;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.events.AbstractImageEvent;
/*     */ import com.megacrit.cardcrawl.localization.EventStrings;
/*     */ import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
/*     */ import gremlin.patches.GremlinEnum;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CouncilOfGhosts_Evil
/*     */   extends AbstractImageEvent
/*     */ {
/*     */   public static final String ID = "downfall:Ghosts";
/*     */   public static final String NAME;
/*     */   public static final String[] DESCRIPTIONS;
/*     */   public static final String[] OPTIONS;
/*     */   public static final String[] DESCRIPTIONSALT;
/*     */   public static final String[] OPTIONSALT;
/*  37 */   private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("Ghosts"); private static final String INTRO_BODY_M; private static final String ACCEPT_BODY; static {
/*  38 */     NAME = eventStrings.NAME;
/*  39 */     DESCRIPTIONS = eventStrings.DESCRIPTIONS;
/*  40 */     OPTIONS = eventStrings.OPTIONS;
/*  41 */     DESCRIPTIONSALT = (CardCrawlGame.languagePack.getEventString("downfall:Ghosts")).DESCRIPTIONS;
/*  42 */     OPTIONSALT = (CardCrawlGame.languagePack.getEventString("downfall:Ghosts")).OPTIONS;
/*  43 */     INTRO_BODY_M = DESCRIPTIONS[0];
/*  44 */     ACCEPT_BODY = DESCRIPTIONS[2];
/*  45 */     EXIT_BODY = DESCRIPTIONS[3];
/*     */   }
/*     */   private static final String EXIT_BODY; private static final float HP_DRAIN = 0.5F;
/*  48 */   private int screenNum = 0;
/*  49 */   private int hpLoss = 0;
/*  50 */   private int goldCost = 100;
/*     */   
/*     */   public CouncilOfGhosts_Evil() {
/*  53 */     super(NAME, DESCRIPTIONSALT[0], "images/events/ghost.jpg");
/*  54 */     this.hpLoss = MathUtils.ceil(AbstractDungeon.player.maxHealth * 0.5F);
/*  55 */     if (AbstractDungeon.player.chosenClass == GremlinEnum.GREMLIN) {
/*  56 */       this.hpLoss *= 5;
/*     */     }
/*  58 */     if (this.hpLoss >= AbstractDungeon.player.maxHealth) {
/*  59 */       this.hpLoss = AbstractDungeon.player.maxHealth - 1;
/*     */     }
/*     */     
/*  62 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  63 */       this.goldCost = 150;
/*     */     }
/*     */     
/*  66 */     if (AbstractDungeon.player.gold >= this.goldCost) {
/*  67 */       this.imageEventText.setDialogOption(OPTIONSALT[0] + this.goldCost + OPTIONSALT[1], (AbstractCard)new Apparition());
/*     */     } else {
/*  69 */       this.imageEventText.setDialogOption(OPTIONSALT[2] + this.goldCost + OPTIONSALT[3], true);
/*     */     } 
/*     */ 
/*     */     
/*  73 */     if (AbstractDungeon.ascensionLevel >= 15) {
/*  74 */       this.imageEventText.setDialogOption(OPTIONS[3] + this.hpLoss + OPTIONS[1], (AbstractCard)new Apparition());
/*     */     } else {
/*  76 */       this.imageEventText.setDialogOption(OPTIONS[0] + this.hpLoss + OPTIONS[1], (AbstractCard)new Apparition());
/*     */     } 
/*     */     
/*  79 */     if (AbstractDungeon.player.chosenClass.equals(CollectorChar.Enums.THE_COLLECTOR)) {
/*  80 */       Apparition apparition = new Apparition();
/*  81 */       CardModifierManager.addModifier((AbstractCard)apparition, (AbstractCardModifier)new CollectedCardMod());
/*  82 */       this.imageEventText.setDialogOption(CollectorChar.COLLECTORTAKE, (AbstractCard)apparition);
/*     */     } else {
/*  84 */       this.imageEventText.setDialogOption(OPTIONS[2]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void onEnterRoom() {
/*  89 */     if (Settings.AMBIANCE_ON) {
/*  90 */       CardCrawlGame.sound.play("EVENT_GHOSTS");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void buttonEffect(int buttonPressed) {
/*  96 */     switch (this.screenNum) {
/*     */       case 0:
/*  98 */         switch (buttonPressed) {
/*     */           case 0:
/* 100 */             this.imageEventText.updateBodyText(DESCRIPTIONSALT[1]);
/* 101 */             AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)new Apparition(), Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/* 102 */             this.screenNum = 1;
/* 103 */             AbstractDungeon.player.loseGold(this.goldCost);
/* 104 */             this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/* 105 */             this.imageEventText.clearRemainingOptions();
/* 106 */             logMetric("downfall:Ghosts", "Purchased Apparition", Collections.singletonList("Ghostly"), null, null, null, null, null, null, 0, 0, 0, 0, 0, this.goldCost);
/*     */             return;
/*     */           case 1:
/* 109 */             this.imageEventText.updateBodyText(ACCEPT_BODY);
/* 110 */             AbstractDungeon.player.decreaseMaxHealth(this.hpLoss);
/* 111 */             becomeGhost();
/* 112 */             this.screenNum = 1;
/* 113 */             this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/* 114 */             this.imageEventText.clearRemainingOptions();
/*     */             return;
/*     */         } 
/* 117 */         if (AbstractDungeon.player.chosenClass.equals(CollectorChar.Enums.THE_COLLECTOR)) {
/*     */           
/* 119 */           this.imageEventText.updateBodyText(DESCRIPTIONSALT[2]);
/* 120 */           this.imageEventText.clearAllDialogs();
/* 121 */           this.imageEventText.setDialogOption(OPTIONS[5]);
/* 122 */           this.screenNum = 1;
/* 123 */           Apparition apparition = new Apparition();
/* 124 */           CardModifierManager.addModifier((AbstractCard)apparition, (AbstractCardModifier)new CollectedCardMod());
/* 125 */           CollectorCollection.collection.addToTop((AbstractCard)apparition);
/*     */           
/* 127 */           logMetric("downfall:Ghosts", "Take", null, null, null, null, null, null, null, 0, 0, 0, 0, 0, 0);
/*     */           
/*     */           return;
/*     */         } 
/* 131 */         logMetricIgnored("downfall:Ghosts");
/* 132 */         this.imageEventText.updateBodyText(EXIT_BODY);
/* 133 */         this.screenNum = 2;
/* 134 */         this.imageEventText.updateDialogOption(0, OPTIONS[5]);
/* 135 */         this.imageEventText.clearRemainingOptions();
/*     */         return;
/*     */     } 
/*     */ 
/*     */     
/* 140 */     openMap();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void becomeGhost() {
/* 146 */     List<String> cards = new ArrayList<>();
/* 147 */     int amount = 5;
/* 148 */     if (AbstractDungeon.ascensionLevel >= 15) {
/* 149 */       amount -= 2;
/*     */     }
/*     */     
/* 152 */     for (int i = 0; i < amount; i++) {
/* 153 */       Apparition apparition = new Apparition();
/* 154 */       cards.add(((AbstractCard)apparition).cardID);
/* 155 */       AbstractDungeon.effectList.add(new ShowCardAndObtainEffect((AbstractCard)apparition, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
/*     */     } 
/*     */     
/* 158 */     logMetricObtainCardsLoseMapHP("downfall:Ghosts", "Became a Ghost", cards, this.hpLoss);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\events\CouncilOfGhosts_Evil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */