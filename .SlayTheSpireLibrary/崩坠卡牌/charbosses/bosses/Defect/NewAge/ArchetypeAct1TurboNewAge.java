/*     */ package charbosses.bosses.Defect.NewAge;
/*     */ import basemod.ReflectionHacks;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Defect.ArchetypeBaseDefect;
/*     */ import charbosses.cards.blue.EnBuffer;
/*     */ import charbosses.cards.blue.EnCoreSurge;
/*     */ import charbosses.cards.blue.EnDefendBlue;
/*     */ import charbosses.cards.blue.EnDoomAndGloom;
/*     */ import charbosses.cards.blue.EnEquilibrium;
/*     */ import charbosses.cards.blue.EnSteamBarrier;
/*     */ import charbosses.cards.blue.EnStrikeBlue;
/*     */ import charbosses.cards.blue.EnSunder;
/*     */ import charbosses.cards.blue.EnTurbo;
/*     */ import charbosses.cards.curses.EnInjury;
/*     */ import charbosses.orbs.AbstractEnemyOrb;
/*     */ import charbosses.powers.bossmechanicpowers.DefectVoidPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import charbosses.relics.CBR_ClockworkSouvenir;
/*     */ import charbosses.relics.CBR_FossilizedHelix;
/*     */ import charbosses.relics.CBR_SymbioticVirus;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import slimebound.SlimeboundMod;
/*     */ 
/*     */ public class ArchetypeAct1TurboNewAge extends ArchetypeBaseDefect {
/*  30 */   int darkOrbsChanneled = 0; private int steamBarrierCasts;
/*     */   
/*     */   public ArchetypeAct1TurboNewAge() {
/*  33 */     super("DF_ARCHETYPE_STREAMLINE", "Streamline");
/*  34 */     this.maxHPModifier += 102;
/*  35 */     this.actNum = 1;
/*  36 */     SlimeboundMod.logger.info("Archetype act num: " + this.actNum);
/*  37 */     this.bossMechanicName = DefectVoidPower.NAME;
/*  38 */     this.bossMechanicDesc = DefectVoidPower.DESC[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  43 */     super.addedPreBattle();
/*  44 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  45 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new DefectVoidPower((AbstractCreature)abstractCharBoss)));
/*     */     
/*     */     try {
/*  48 */       Method loadAnimationMethod = AbstractCreature.class.getDeclaredMethod("loadAnimation", new Class[] { String.class, String.class, float.class });
/*  49 */       loadAnimationMethod.setAccessible(true);
/*  50 */       loadAnimationMethod.invoke(AbstractCharBoss.boss, new Object[] { "expansioncontentResources/images/bosses/defect/1/Defect_thief.atlas", "expansioncontentResources/images/bosses/defect/1/Defect_thief.json", Float.valueOf(1.0F) });
/*  51 */       AnimationState.TrackEntry e = AbstractCharBoss.boss.state.setAnimation(0, "Idle", true);
/*  52 */       ((AnimationStateData)ReflectionHacks.getPrivate(AbstractCharBoss.boss, AbstractCreature.class, "stateData")).setMix("Hit", "Idle", 0.1F);
/*  53 */       e.setTimeScale(0.9F);
/*  54 */     } catch (Exception e) {
/*  55 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  63 */     this.steamBarrierCasts = 0;
/*  64 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*     */ 
/*     */     
/*  67 */     addRelic((AbstractCharbossRelic)new CBR_FossilizedHelix());
/*  68 */     addRelic((AbstractCharbossRelic)new CBR_ClockworkSouvenir());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  76 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  77 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*  78 */     if (!this.looped) {
/*  79 */       switch (this.turn) {
/*     */         case 0:
/*  81 */           addToList(cardsList, (AbstractCard)new EnBuffer(), false);
/*  82 */           addToList(cardsList, (AbstractCard)new EnDoomAndGloom(), extraUpgrades);
/*  83 */           addToList(cardsList, (AbstractCard)new EnInjury(), false);
/*  84 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 1:
/*  88 */           addToList(cardsList, (AbstractCard)new EnSteamBarrier(), false);
/*  89 */           addToList(cardsList, (AbstractCard)new EnDefendBlue(), false);
/*  90 */           addToList(cardsList, (AbstractCard)new EnClumsy(), false);
/*  91 */           this.turn++;
/*  92 */           this.steamBarrierCasts++;
/*     */           break;
/*     */         
/*     */         case 2:
/*  96 */           addToList(cardsList, (AbstractCard)new EnTurbo(), true);
/*  97 */           addToList(cardsList, (AbstractCard)new EnSunder(), false);
/*  98 */           addToList(cardsList, (AbstractCard)new EnDefendBlue());
/*  99 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 3:
/* 103 */           addToList(cardsList, (AbstractCard)new EnStrikeBlue(), false);
/* 104 */           addToList(cardsList, (AbstractCard)new EnCoreSurge(), false);
/* 105 */           addToList(cardsList, (AbstractCard)new EnEquilibrium());
/* 106 */           this.turn = 0;
/* 107 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 111 */       switch (this.turn) {
/*     */         case 0:
/* 113 */           addToList(cardsList, (AbstractCard)new EnDoomAndGloom(), extraUpgrades);
/* 114 */           this.darkOrbsChanneled++;
/* 115 */           if (this.darkOrbsChanneled > 3 && AbstractCharBoss.boss.orbs.get(0) instanceof AbstractEnemyOrb) {
/* 116 */             ((AbstractEnemyOrb)AbstractCharBoss.boss.orbs.get(0)).evokeOverride = true;
/*     */           }
/* 118 */           addToList(cardsList, (AbstractCard)new EnDefendBlue());
/* 119 */           addToList(cardsList, (AbstractCard)new EnInjury(), false);
/* 120 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 123 */           addToList(cardsList, (AbstractCard)new EnTurbo(), true);
/* 124 */           addToList(cardsList, (AbstractCard)new EnSunder(), false);
/* 125 */           addToList(cardsList, (AbstractCard)new EnEquilibrium());
/* 126 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 129 */           addToList(cardsList, (AbstractCard)new EnDefendBlue(), false);
/* 130 */           addToList(cardsList, (AbstractCard)new EnStrikeBlue(), false);
/* 131 */           addToList(cardsList, (AbstractCard)new EnSteamBarrier(this.steamBarrierCasts), false);
/* 132 */           this.steamBarrierCasts++;
/* 133 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } 
/* 139 */     return cardsList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 145 */     addRelic((AbstractCharbossRelic)new CBR_SymbioticVirus());
/* 146 */     this.darkOrbsChanneled++;
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Defect\NewAge\ArchetypeAct1TurboNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */