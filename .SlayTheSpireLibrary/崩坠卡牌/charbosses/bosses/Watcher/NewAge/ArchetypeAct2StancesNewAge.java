/*     */ package charbosses.bosses.Watcher.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import charbosses.cards.curses.EnDoubt;
/*     */ import charbosses.cards.purple.EnDefendPurple;
/*     */ import charbosses.cards.purple.EnEmptyBody;
/*     */ import charbosses.cards.purple.EnEmptyFist;
/*     */ import charbosses.cards.purple.EnFearNoEvil;
/*     */ import charbosses.cards.purple.EnHalt;
/*     */ import charbosses.cards.purple.EnInnerPeace;
/*     */ import charbosses.cards.purple.EnSimmeringFury;
/*     */ import charbosses.cards.purple.EnTantrum;
/*     */ import charbosses.cards.purple.EnWeave;
/*     */ import charbosses.powers.bossmechanicpowers.WatcherSkillPower;
/*     */ import charbosses.powers.cardpowers.EnemyFearNoEvilPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct2StancesNewAge extends ArchetypeBaseWatcher {
/*  26 */   private AbstractBossCard tranquility = null;
/*     */   
/*     */   public ArchetypeAct2StancesNewAge() {
/*  29 */     super("WA_ARCHETYPE_STANCES", "Stances");
/*  30 */     this.maxHPModifier += 160;
/*  31 */     this.actNum = 2;
/*  32 */     this.bossMechanicName = WatcherSkillPower.NAME;
/*  33 */     this.bossMechanicDesc = WatcherSkillPower.DESCRIPTIONS[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  38 */     super.addedPreBattle();
/*  39 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  40 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new WatcherSkillPower((AbstractCreature)abstractCharBoss, 1), 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  47 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  48 */     addRelic((AbstractCharbossRelic)new CBR_HornCleat());
/*  49 */     addRelic((AbstractCharbossRelic)new CBR_TungstenRod());
/*  50 */     addRelic((AbstractCharbossRelic)new CBR_BagOfMarbles());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  56 */     AbstractCharBoss b = AbstractCharBoss.boss;
/*  57 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  58 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*  59 */     if (!this.looped) {
/*  60 */       boolean isCalm; switch (this.turn) {
/*     */         case 0:
/*  62 */           addToList(cardsList, (AbstractCard)new EnMentalFortress(), true);
/*  63 */           addToList(cardsList, (AbstractCard)new EnTantrum(), false);
/*  64 */           addToList(cardsList, (AbstractCard)new EnDoubt());
/*  65 */           this.turn++;
/*     */           break;
/*     */         case 1:
/*  68 */           addToList(cardsList, (AbstractCard)new EnWeave(), extraUpgrades);
/*  69 */           addToList(cardsList, (AbstractCard)new EnFearNoEvil(), false);
/*  70 */           addToList(cardsList, (AbstractCard)new EnSimmeringFury(), false);
/*  71 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)b, (AbstractCreature)b, (AbstractPower)new EnemyFearNoEvilPower((AbstractCreature)b)));
/*  72 */           this.turn++;
/*     */           break;
/*     */         case 2:
/*  75 */           addToList(cardsList, (AbstractCard)new EnTalkToTheHand(), extraUpgrades);
/*  76 */           isCalm = b.stance instanceof charbosses.stances.EnCalmStance;
/*  77 */           addToList(cardsList, (AbstractBossCard)new EnEmptyFist(), false, isCalm ? 2 : 0);
/*  78 */           addToList(cardsList, (AbstractCard)new EnWreathOfFlame(), false);
/*  79 */           addToList(cardsList, (AbstractCard)new EnDefendPurple(), false);
/*  80 */           this.tranquility = (AbstractBossCard)new EnTranquility();
/*  81 */           this.tranquility.newPrio = 1;
/*  82 */           addToList(cardsList, (AbstractCard)this.tranquility, false);
/*  83 */           this.turn++;
/*     */           break;
/*     */         case 3:
/*  86 */           this.tranquility.newPrio = -2;
/*  87 */           this.tranquility.lockIntentValues = false;
/*  88 */           addToList(cardsList, (AbstractBossCard)new EnTantrum(), false, 2);
/*  89 */           addToList(cardsList, (AbstractCard)new EnEmptyBody(), false);
/*  90 */           addToList(cardsList, (AbstractCard)new EnInnerPeace(), false);
/*  91 */           this.turn++;
/*     */           break;
/*     */         case 4:
/*  94 */           addToList(cardsList, (AbstractCard)new EnFlyingSleeves(), extraUpgrades);
/*  95 */           addToList(cardsList, (AbstractBossCard)new EnEmptyFist(), false, 2);
/*  96 */           addToList(cardsList, (AbstractCard)new EnMeditate(), false);
/*  97 */           this.turn++;
/*     */           break;
/*     */         case 5:
/* 100 */           addToList(cardsList, (AbstractBossCard)new EnTantrum(), false, 2);
/* 101 */           addToList(cardsList, (AbstractCard)new EnApotheosis(), false);
/* 102 */           addToList(cardsList, (AbstractCard)new EnHalt(), true);
/* 103 */           addToList(cardsList, (AbstractCard)new EnFearNoEvil(), true);
/* 104 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)b, (AbstractCreature)b, (AbstractPower)new EnemyFearNoEvilPower((AbstractCreature)b)));
/* 105 */           this.turn = 0;
/* 106 */           this.looped = true; break;
/*     */       } 
/*     */     } else {
/*     */       boolean isCalm;
/* 110 */       switch (this.turn) {
/*     */         case 0:
/* 112 */           isCalm = b.stance instanceof charbosses.stances.EnCalmStance;
/* 113 */           addToList(cardsList, (AbstractBossCard)new EnEmptyBody(), true, isCalm ? 2 : 0);
/* 114 */           addToList(cardsList, (AbstractCard)new EnSimmeringFury(), true);
/* 115 */           addToList(cardsList, (AbstractCard)new EnWreathOfFlame(), true);
/* 116 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 119 */           addToList(cardsList, (AbstractCard)new EnInnerPeace(), true);
/* 120 */           addToList(cardsList, (AbstractBossCard)new EnTantrum(), true, 2);
/* 121 */           addToList(cardsList, (AbstractCard)new EnHalt(), true);
/* 122 */           addToList(cardsList, (AbstractCard)new EnEmptyFist(), true);
/* 123 */           addToList(cardsList, (AbstractCard)new EnMeditate(), true);
/* 124 */           addToList(cardsList, (AbstractCard)new EnDoubt());
/* 125 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 128 */           addToList(cardsList, (AbstractBossCard)new EnTantrum(), true, 2);
/* 129 */           addToList(cardsList, (AbstractCard)new EnWeave(), true);
/* 130 */           addToList(cardsList, (AbstractCard)new EnEmptyFist(), true);
/* 131 */           addToList(cardsList, (AbstractCard)new EnDefendPurple(), true);
/* 132 */           addToList(cardsList, (AbstractCard)new EnFearNoEvil(), true);
/* 133 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)b, (AbstractCreature)b, (AbstractPower)new EnemyFearNoEvilPower((AbstractCreature)b)));
/* 134 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 139 */     return cardsList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 145 */     addRelic((AbstractCharbossRelic)new CBR_IncenseBurner(3));
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Watcher\NewAge\ArchetypeAct2StancesNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */