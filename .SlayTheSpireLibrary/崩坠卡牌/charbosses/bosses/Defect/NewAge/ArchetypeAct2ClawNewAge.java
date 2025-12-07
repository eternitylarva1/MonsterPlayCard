/*     */ package charbosses.bosses.Defect.NewAge;
/*     */ import basemod.ReflectionHacks;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Defect.ArchetypeBaseDefect;
/*     */ import charbosses.bosses.Defect.CharBossDefect;
/*     */ import charbosses.cards.blue.EnChargeBattery;
/*     */ import charbosses.cards.blue.EnChill;
/*     */ import charbosses.cards.blue.EnClaw;
/*     */ import charbosses.cards.blue.EnCoreSurge;
/*     */ import charbosses.cards.blue.EnDefendBlue;
/*     */ import charbosses.cards.blue.EnDefragment;
/*     */ import charbosses.cards.blue.EnGeneticAlgorithm;
/*     */ import charbosses.cards.blue.EnHyperbeam;
/*     */ import charbosses.cards.blue.EnRebound;
/*     */ import charbosses.cards.blue.EnReprogram;
/*     */ import charbosses.cards.blue.EnStrikeBlue;
/*     */ import charbosses.cards.curses.EnClumsy;
/*     */ import charbosses.monsters.BronzeOrbWhoReallyLikesDefectForSomeReason;
/*     */ import charbosses.powers.bossmechanicpowers.DefectAncientConstructPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import charbosses.relics.CBR_ClockworkSouvenir;
/*     */ import charbosses.relics.CBR_IceCream;
/*     */ import charbosses.relics.CBR_NeowsBlessing;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct2ClawNewAge extends ArchetypeBaseDefect {
/*     */   private EnClaw c;
/*  37 */   int frostOrbsChanneled = 0; private CharBossDefect cB;
/*     */   
/*     */   public ArchetypeAct2ClawNewAge() {
/*  40 */     super("DF_ARCHETYPE_CLAW", "Claw");
/*     */     
/*  42 */     this.maxHPModifier += 192;
/*  43 */     this.actNum = 2;
/*  44 */     this.bossMechanicName = DefectAncientConstructPower.NAME;
/*  45 */     this.bossMechanicDesc = DefectAncientConstructPower.DESC[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  50 */     super.addedPreBattle();
/*  51 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  52 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new DefectAncientConstructPower((AbstractCreature)abstractCharBoss, 1), 1));
/*  53 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction((AbstractMonster)new BronzeOrbWhoReallyLikesDefectForSomeReason(-450.0F, 250.0F, 0), true));
/*  54 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction((AbstractMonster)new BronzeOrbWhoReallyLikesDefectForSomeReason(-600.0F, 0.0F, 1), true));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  60 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  61 */     addRelic((AbstractCharbossRelic)new CBR_IceCream());
/*  62 */     addRelic((AbstractCharbossRelic)new CBR_BagOfPreparation());
/*  63 */     addRelic((AbstractCharbossRelic)new CBR_NuclearBattery());
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  68 */       Method loadAnimationMethod = AbstractCreature.class.getDeclaredMethod("loadAnimation", new Class[] { String.class, String.class, float.class });
/*  69 */       loadAnimationMethod.setAccessible(true);
/*  70 */       loadAnimationMethod.invoke(AbstractCharBoss.boss, new Object[] { "expansioncontentResources/images/bosses/defect/2/clockworkDefect.atlas", "expansioncontentResources/images/bosses/defect/2/clockworkDefect.json", Float.valueOf(1.0F) });
/*  71 */       AnimationState.TrackEntry e = AbstractCharBoss.boss.state.setAnimation(0, "Idle", true);
/*  72 */       ((AnimationStateData)ReflectionHacks.getPrivate(AbstractCharBoss.boss, AbstractCreature.class, "stateData")).setMix("Hit", "Idle", 0.1F);
/*  73 */       e.setTimeScale(0.9F);
/*  74 */     } catch (Exception e) {
/*  75 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  82 */     if (this.cB == null) {
/*  83 */       this.cB = (CharBossDefect)AbstractCharBoss.boss;
/*     */     }
/*  85 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  86 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */     
/*  88 */     if (!this.looped) {
/*  89 */       switch (this.turn) {
/*     */         case 0:
/*  91 */           addToList(cardsList, (AbstractCard)new EnChill(), true);
/*  92 */           this.frostOrbsChanneled++;
/*  93 */           addToList(cardsList, (AbstractCard)new EnChill(), true);
/*  94 */           this.frostOrbsChanneled++;
/*  95 */           addToList(cardsList, (AbstractCard)new EnBootSequence(), true);
/*  96 */           addToList(cardsList, (AbstractCard)new EnClaw(this.cB.clawsPlayed * 2), false);
/*  97 */           addToList(cardsList, (AbstractCard)new EnMachineLearning(), true);
/*  98 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 101 */           addToList(cardsList, (AbstractCard)new EnDefragment(), true);
/* 102 */           ArchetypeAct3OrbsNewAge.increasePretendFocus(2);
/* 103 */           addToList(cardsList, (AbstractCard)new EnChargeBattery());
/* 104 */           addToList(cardsList, (AbstractCard)new EnDefendBlue(), false);
/* 105 */           addToList(cardsList, (AbstractCard)new EnStrikeBlue(), true);
/* 106 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/* 110 */           addToList(cardsList, (AbstractCard)new EnCoreSurge(), false);
/* 111 */           addToList(cardsList, (AbstractCard)new EnReprogram(), extraUpgrades);
/* 112 */           addToList(cardsList, (AbstractCard)new EnRebound(), false);
/* 113 */           addToList(cardsList, (AbstractCard)new EnClaw(this.cB.clawsPlayed * 2), false);
/* 114 */           this.turn++;
/*     */           break;
/*     */         case 3:
/* 117 */           addToList(cardsList, (AbstractCard)new EnClaw(this.cB.clawsPlayed * 2), false);
/* 118 */           addToList(cardsList, (AbstractCard)new EnChargeBattery(), false);
/* 119 */           addToList(cardsList, (AbstractCard)new EnGeneticAlgorithm(14), true);
/* 120 */           addToList(cardsList, (AbstractCard)new EnClumsy(), true);
/* 121 */           this.turn++;
/*     */           break;
/*     */         case 4:
/* 124 */           addToList(cardsList, (AbstractCard)new EnCoreSurge(), false);
/* 125 */           addToList(cardsList, (AbstractCard)new EnHyperbeam(), false);
/* 126 */           addToList(cardsList, (AbstractCard)new EnClumsy(), false);
/* 127 */           addToList(cardsList, (AbstractCard)new EnDefendBlue(), false);
/* 128 */           this.turn = 0;
/* 129 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/* 134 */       switch (this.turn) {
/*     */         case 0:
/* 136 */           addToList(cardsList, (AbstractCard)new EnClaw(this.cB.clawsPlayed * 2), false);
/* 137 */           addToList(cardsList, (AbstractCard)new EnChargeBattery(), false);
/* 138 */           addToList(cardsList, (AbstractCard)new EnDefendBlue(), false);
/* 139 */           addToList(cardsList, (AbstractCard)new EnStrikeBlue(), true);
/* 140 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 143 */           addToList(cardsList, (AbstractCard)new EnCoreSurge(), false);
/* 144 */           addToList(cardsList, (AbstractCard)new EnHyperbeam(), false);
/* 145 */           addToList(cardsList, (AbstractCard)new EnChargeBattery(), false);
/* 146 */           addToList(cardsList, (AbstractCard)new EnRebound(), false);
/* 147 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 150 */           addToList(cardsList, (AbstractCard)new EnCoreSurge(), false);
/* 151 */           addToList(cardsList, (AbstractCard)new EnReprogram(), extraUpgrades);
/* 152 */           addToList(cardsList, (AbstractCard)new EnClaw(this.cB.clawsPlayed * 2), false);
/* 153 */           addToList(cardsList, (AbstractCard)new EnDefendBlue(), false);
/* 154 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 159 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 164 */     addRelic((AbstractCharbossRelic)new CBR_ClockworkSouvenir());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Defect\NewAge\ArchetypeAct2ClawNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */