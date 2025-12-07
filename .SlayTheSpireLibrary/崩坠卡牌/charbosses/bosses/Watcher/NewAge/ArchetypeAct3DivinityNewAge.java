/*     */ package charbosses.bosses.Watcher.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import charbosses.cards.colorless.EnBlind;
/*     */ import charbosses.cards.colorless.EnGoodInstincts;
/*     */ import charbosses.cards.colorless.EnSwiftStrike;
/*     */ import charbosses.cards.curses.EnInjury;
/*     */ import charbosses.cards.curses.EnNormality;
/*     */ import charbosses.cards.purple.EnBrilliance;
/*     */ import charbosses.cards.purple.EnEmptyFist;
/*     */ import charbosses.cards.purple.EnProtect;
/*     */ import charbosses.cards.purple.EnSanctity;
/*     */ import charbosses.cards.purple.EnSwivel;
/*     */ import charbosses.cards.purple.EnWaveOfTheHand;
/*     */ import charbosses.powers.bossmechanicpowers.WatcherDivinityPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct3DivinityNewAge extends ArchetypeBaseDefect {
/*  23 */   private AbstractBossCard theVeryImportantSandsOfTime = null;
/*  24 */   private AbstractBossCard theVeryImportantPerseverence = null;
/*     */   
/*     */   public ArchetypeAct3DivinityNewAge() {
/*  27 */     super("WA_ARCHETYPE_DIVINITY", "Divinity");
/*     */     
/*  29 */     this.maxHPModifier += 348;
/*  30 */     this.actNum = 3;
/*  31 */     this.bossMechanicName = WatcherDivinityPower.NAME;
/*  32 */     this.bossMechanicDesc = WatcherDivinityPower.DESC[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  37 */     super.addedPreBattle();
/*  38 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  39 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new WatcherDivinityPower((AbstractCreature)abstractCharBoss)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  45 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  46 */     addRelic((AbstractCharbossRelic)new CBR_ThreadAndNeedle());
/*  47 */     addRelic((AbstractCharbossRelic)new CBR_DuvuDoll(2));
/*  48 */     addRelic((AbstractCharbossRelic)new CBR_Torii());
/*  49 */     addRelic((AbstractCharbossRelic)new CBR_VelvetChoker());
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  54 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  55 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*  56 */     if (!this.looped) {
/*  57 */       switch (this.turn) {
/*     */ 
/*     */         
/*     */         case 0:
/*  61 */           addToList(cardsList, (AbstractCard)new EnWishPlated(), true);
/*  62 */           addToList(cardsList, (AbstractCard)new EnGoodInstincts());
/*  63 */           addToList(cardsList, (AbstractCard)new EnInjury());
/*  64 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/*  69 */           addToList(cardsList, (AbstractCard)new EnWaveOfTheHand());
/*  70 */           addToList(cardsList, (AbstractCard)new EnBrilliance(), extraUpgrades);
/*  71 */           addToList(cardsList, (AbstractCard)new EnSwivel());
/*  72 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/*  77 */           addToList(cardsList, (AbstractCard)new EnSwiftStrike());
/*  78 */           addToList(cardsList, (AbstractCard)new EnConjurBlade(), false);
/*  79 */           addToList(cardsList, (AbstractCard)new EnSanctity());
/*  80 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/*  85 */           addToList(cardsList, (AbstractCard)new EnProtect(), true);
/*  86 */           addToList(cardsList, (AbstractCard)new EnEmptyFist(), true);
/*  87 */           addToList(cardsList, (AbstractCard)new EnNormality());
/*  88 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/*  93 */           addToList(cardsList, (AbstractCard)new EnExpunger());
/*  94 */           addToList(cardsList, (AbstractCard)new EnBlind());
/*  95 */           addToList(cardsList, (AbstractCard)new EnDevotion());
/*  96 */           this.turn = 0;
/*  97 */           this.looped = true; break;
/*     */       } 
/*     */     } else {
/*     */       EnSwivel enSwivel; EnEmptyFist enEmptyFist;
/* 101 */       switch (this.turn) {
/*     */ 
/*     */         
/*     */         case 0:
/* 105 */           addToList(cardsList, (AbstractCard)new EnWaveOfTheHand());
/* 106 */           enSwivel = new EnSwivel();
/* 107 */           ((AbstractBossCard)enSwivel).energyGeneratedIfPlayed = 1;
/* 108 */           addToList(cardsList, (AbstractCard)enSwivel);
/* 109 */           enEmptyFist = new EnEmptyFist();
/* 110 */           ((AbstractBossCard)enEmptyFist).freeToPlayOnce = true;
/* 111 */           addToList(cardsList, (AbstractCard)enEmptyFist, true);
/* 112 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 1:
/* 116 */           addToList(cardsList, (AbstractCard)new EnExpunger());
/* 117 */           addToList(cardsList, (AbstractCard)new EnSanctity());
/* 118 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 119 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/* 123 */           addToList(cardsList, (AbstractCard)new EnProtect(), true);
/* 124 */           addToList(cardsList, (AbstractCard)new EnBrilliance(), true);
/* 125 */           addToList(cardsList, (AbstractCard)new EnBlind());
/* 126 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 3:
/* 130 */           addToList(cardsList, (AbstractCard)new EnSwiftStrike(), false);
/* 131 */           addToList(cardsList, (AbstractCard)new EnGoodInstincts());
/* 132 */           addToList(cardsList, (AbstractCard)new EnNormality());
/* 133 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 138 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 143 */     addRelic((AbstractCharbossRelic)new CBR_Damaru());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Watcher\NewAge\ArchetypeAct3DivinityNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */