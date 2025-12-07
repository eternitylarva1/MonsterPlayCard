/*     */ package charbosses.bosses.Watcher.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import charbosses.cards.purple.EnFearNoEvil;
/*     */ import charbosses.cards.purple.EnLikeWater;
/*     */ import charbosses.cards.purple.EnPressurePoints;
/*     */ import charbosses.cards.purple.EnTantrum;
/*     */ import charbosses.cards.purple.EnWish;
/*     */ import charbosses.powers.bossmechanicpowers.WatcherCripplePower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct2CalmNewAge extends ArchetypeBaseWatcher {
/*  20 */   private AbstractBossCard theVeryImportantBlasphemy = null;
/*  21 */   private AbstractBossCard theVeryImportantFlyingSleeves = null;
/*     */   
/*     */   public ArchetypeAct2CalmNewAge() {
/*  24 */     super("WA_ARCHETYPE_CALM", "Calm");
/*  25 */     this.maxHPModifier += 198;
/*  26 */     this.actNum = 2;
/*  27 */     this.bossMechanicName = WatcherCripplePower.NAME;
/*  28 */     this.bossMechanicDesc = WatcherCripplePower.DESCRIPTIONS[0] + '\031' + WatcherCripplePower.DESCRIPTIONS[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  33 */     super.addedPreBattle();
/*  34 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  35 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new WatcherCripplePower((AbstractCreature)abstractCharBoss, 75), 25));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  41 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  42 */     addRelic((AbstractCharbossRelic)new CBR_VioletLotus());
/*  43 */     addRelic((AbstractCharbossRelic)new CBR_TeardropLocket());
/*  44 */     addRelic((AbstractCharbossRelic)new CBR_IncenseBurner());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  55 */     AbstractCharBoss b = AbstractCharBoss.boss;
/*  56 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  57 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*  58 */     if (!this.looped) {
/*  59 */       EnWish enWish; switch (this.turn) {
/*     */         case 0:
/*  61 */           if (AbstractDungeon.ascensionLevel >= 19) {
/*  62 */             EnLikeWater enLikeWater = new EnLikeWater();
/*  63 */             ((AbstractBossCard)enLikeWater).freeToPlayOnce = true;
/*  64 */             ((AbstractBossCard)enLikeWater).costForTurn = 0;
/*     */             
/*  66 */             addToList(cardsList, (AbstractCard)enLikeWater, false);
/*     */           } 
/*  68 */           addToList(cardsList, (AbstractCard)new EnMentalFortress(), true);
/*  69 */           addToList(cardsList, (AbstractCard)new EnPressurePoints(), true);
/*  70 */           addToList(cardsList, (AbstractCard)new EnRagnarok(), false);
/*  71 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 1:
/*  75 */           addToList(cardsList, (AbstractCard)new EnFearNoEvil(), false);
/*  76 */           addToList(cardsList, (AbstractCard)new EnFollowUp(), false);
/*  77 */           addToList(cardsList, (AbstractCard)new EnWreathOfFlame(), false);
/*  78 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/*  82 */           addToList(cardsList, (AbstractBossCard)new EnTantrum(), true, 3);
/*  83 */           addToList(cardsList, (AbstractCard)new EnClumsy(), false);
/*  84 */           addToList(cardsList, (AbstractCard)new EnVigilance(), false);
/*  85 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 3:
/*  89 */           addToList(cardsList, (AbstractCard)new EnLessonLearned(), true);
/*  90 */           this.theVeryImportantBlasphemy = (AbstractBossCard)new EnBlasphemy();
/*  91 */           this.theVeryImportantBlasphemy.newPrio = 1;
/*  92 */           addToList(cardsList, this.theVeryImportantBlasphemy, true, 6);
/*  93 */           this.theVeryImportantFlyingSleeves = (AbstractBossCard)new EnFlyingSleeves();
/*  94 */           this.theVeryImportantFlyingSleeves.newPrio = 1;
/*  95 */           addToList(cardsList, (AbstractCard)this.theVeryImportantFlyingSleeves, extraUpgrades);
/*  96 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 4:
/* 100 */           this.theVeryImportantBlasphemy.newPrio = -2;
/* 101 */           this.theVeryImportantBlasphemy.lockIntentValues = false;
/* 102 */           this.theVeryImportantFlyingSleeves.newPrio = 0;
/* 103 */           this.theVeryImportantFlyingSleeves.lockIntentValues = false;
/* 104 */           enWish = new EnWish();
/* 105 */           ((AbstractBossCard)enWish).newPrio = -1;
/* 106 */           addToList(cardsList, (AbstractCard)enWish, false);
/* 107 */           addToList(cardsList, (AbstractCard)new EnRagnarok(), false);
/* 108 */           addToList(cardsList, (AbstractCard)new EnTranquility(), true);
/* 109 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 5:
/* 113 */           addToList(cardsList, (AbstractBossCard)new EnEmptyBody(), true, 3);
/* 114 */           addToList(cardsList, (AbstractCard)new EnDevaForm(), false);
/* 115 */           addToList(cardsList, (AbstractCard)new EnLikeWater(), true);
/* 116 */           this.turn = 0;
/* 117 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } else {
/* 123 */       switch (this.turn) {
/*     */         case 0:
/* 125 */           addToList(cardsList, (AbstractBossCard)new EnTantrum(), true, 3);
/* 126 */           addToList(cardsList, (AbstractCard)new EnFlyingSleeves(), extraUpgrades);
/* 127 */           addToList(cardsList, (AbstractCard)new EnFearNoEvil(), false);
/* 128 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)b, (AbstractCreature)b, (AbstractPower)new EnemyFearNoEvilPower((AbstractCreature)b)));
/*     */           
/* 130 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 133 */           addToList(cardsList, (AbstractCard)new EnFollowUp(), false);
/* 134 */           addToList(cardsList, (AbstractCard)new EnPressurePoints(), false);
/* 135 */           addToList(cardsList, (AbstractCard)new EnEmptyBody(), true);
/*     */           
/* 137 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 140 */           addToList(cardsList, (AbstractBossCard)new EnTantrum(), true, 3);
/* 141 */           addToList(cardsList, (AbstractCard)new EnWreathOfFlame(), false);
/* 142 */           addToList(cardsList, (AbstractCard)new EnFearNoEvil(), false);
/* 143 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)b, (AbstractCreature)b, (AbstractPower)new EnemyFearNoEvilPower((AbstractCreature)b)));
/*     */           
/* 145 */           this.turn++;
/*     */           break;
/*     */         case 3:
/* 148 */           addToList(cardsList, (AbstractCard)new EnPressurePoints(), true);
/* 149 */           addToList(cardsList, (AbstractCard)new EnRagnarok(), false);
/* 150 */           addToList(cardsList, (AbstractCard)new EnVigilance(), false);
/*     */           
/* 152 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 157 */     if (AbstractCharBoss.boss.powerhouseTurn && 
/* 158 */       AbstractCharBoss.boss.hasPower("downfall:WatcherCripplePower")) {
/* 159 */       AbstractCharBoss.boss.getPower("downfall:WatcherCripplePower").onSpecificTrigger();
/*     */     }
/*     */     
/* 162 */     return cardsList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 168 */     addRelic((AbstractCharbossRelic)new CBR_Enchiridon());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Watcher\NewAge\ArchetypeAct2CalmNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */