/*     */ package charbosses.bosses.Watcher.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Watcher.ArchetypeBaseWatcher;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import charbosses.cards.colorless.EnPanacea;
/*     */ import charbosses.cards.curses.EnClumsy;
/*     */ import charbosses.cards.curses.EnParasite;
/*     */ import charbosses.cards.curses.EnRegret;
/*     */ import charbosses.cards.purple.EnCrushJoints;
/*     */ import charbosses.cards.purple.EnDefendPurple;
/*     */ import charbosses.cards.purple.EnHalt;
/*     */ import charbosses.cards.purple.EnSandsOfTime;
/*     */ import charbosses.cards.purple.EnTalkToTheHand;
/*     */ import charbosses.cards.purple.EnWallop;
/*     */ import charbosses.powers.bossmechanicpowers.WatcherAngryPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct1RetainNewAge extends ArchetypeBaseWatcher {
/*  23 */   private AbstractBossCard theVeryImportantSandsOfTime = null;
/*  24 */   private AbstractBossCard theVeryImportantPerseverence = null;
/*     */   
/*     */   public ArchetypeAct1RetainNewAge() {
/*  27 */     super("WA_ARCHETYPE_RETAIN", "Retain");
/*     */     
/*  29 */     this.maxHPModifier += 98;
/*  30 */     this.actNum = 1;
/*  31 */     this.bossMechanicName = WatcherAngryPower.NAME;
/*  32 */     this.bossMechanicDesc = WatcherAngryPower.DESCRIPTIONS[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  37 */     super.addedPreBattle();
/*  38 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  39 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new WatcherAngryPower((AbstractCreature)abstractCharBoss)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  44 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  45 */     addRelic((AbstractCharbossRelic)new CBR_CloakClasp());
/*  46 */     addRelic((AbstractCharbossRelic)new CBR_BagOfPreparation());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  52 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  53 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*  54 */     if (!this.looped) {
/*  55 */       switch (this.turn) {
/*     */         
/*     */         case 0:
/*  58 */           addToList(cardsList, (AbstractCard)new EnWallop(), extraUpgrades);
/*  59 */           addToList(cardsList, (AbstractCard)new EnHalt());
/*  60 */           addToList(cardsList, (AbstractCard)new EnDefendPurple(), true);
/*  61 */           this.theVeryImportantSandsOfTime = (AbstractBossCard)new EnSandsOfTime();
/*  62 */           this.theVeryImportantSandsOfTime.newPrio = 1;
/*  63 */           addToList(cardsList, (AbstractCard)this.theVeryImportantSandsOfTime);
/*  64 */           addToList(cardsList, (AbstractCard)new EnClumsy());
/*  65 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 1:
/*  69 */           this.theVeryImportantSandsOfTime.lockIntentValues = false;
/*  70 */           addToList(cardsList, (AbstractCard)new EnTalkToTheHand());
/*  71 */           addToList(cardsList, (AbstractCard)new EnHalt());
/*  72 */           addToList(cardsList, (AbstractCard)new EnCrushJoints());
/*  73 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/*  77 */           this.theVeryImportantSandsOfTime.lockIntentValues = false;
/*  78 */           addToList(cardsList, (AbstractCard)new EnPanacea());
/*  79 */           addToList(cardsList, (AbstractCard)new EnFasting());
/*  80 */           addToList(cardsList, (AbstractCard)new EnClumsy());
/*  81 */           this.turn++;
/*     */           break;
/*     */         case 3:
/*  84 */           this.theVeryImportantSandsOfTime.newPrio = -1;
/*  85 */           this.theVeryImportantSandsOfTime.lockIntentValues = false;
/*  86 */           addToList(cardsList, (AbstractCard)new EnHalt());
/*  87 */           addToList(cardsList, (AbstractCard)new EnRegret());
/*  88 */           addToList(cardsList, (AbstractCard)new EnParasite());
/*  89 */           this.turn = 0;
/*  90 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     } else {
/*  94 */       switch (this.turn) {
/*     */         case 0:
/*  96 */           addToList(cardsList, (AbstractCard)new EnHalt());
/*  97 */           addToList(cardsList, (AbstractCard)new EnCrushJoints());
/*  98 */           addToList(cardsList, (AbstractCard)new EnDefendPurple(), true);
/*  99 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 102 */           addToList(cardsList, (AbstractCard)new EnWallop(), extraUpgrades);
/* 103 */           addToList(cardsList, (AbstractCard)new EnHalt());
/* 104 */           addToList(cardsList, (AbstractCard)new EnRegret());
/* 105 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 108 */           addToList(cardsList, (AbstractCard)new EnSandsOfTime(3));
/* 109 */           addToList(cardsList, (AbstractCard)new EnHalt());
/* 110 */           addToList(cardsList, (AbstractCard)new EnParasite());
/* 111 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 116 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 121 */     addRelic((AbstractCharbossRelic)new CBR_MercuryHourglass());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Watcher\NewAge\ArchetypeAct1RetainNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */