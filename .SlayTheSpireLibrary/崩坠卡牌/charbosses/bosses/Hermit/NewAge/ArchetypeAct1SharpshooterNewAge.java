/*     */ package charbosses.bosses.Hermit.NewAge;
/*     */ import basemod.ReflectionHacks;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Hermit.CharBossHermit;
/*     */ import charbosses.bosses.Ironclad.ArchetypeBaseIronclad;
/*     */ import charbosses.cards.curses.EnInjury;
/*     */ import charbosses.cards.hermit.EnDeadeye;
/*     */ import charbosses.cards.hermit.EnDefendHermit;
/*     */ import charbosses.cards.hermit.EnDive;
/*     */ import charbosses.cards.hermit.EnGhostlyPresence;
/*     */ import charbosses.cards.hermit.EnHeadshot;
/*     */ import charbosses.cards.hermit.EnItchyTrigger;
/*     */ import charbosses.cards.hermit.EnItchyTriggerStrikeHermit;
/*     */ import charbosses.cards.hermit.EnLoneWolf;
/*     */ import charbosses.cards.hermit.EnRoughhouse;
/*     */ import charbosses.cards.hermit.EnStrikeHermit;
/*     */ import charbosses.monsters.LouseTangerine;
/*     */ import charbosses.powers.bossmechanicpowers.HermitConcentrateAdder;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import charbosses.relics.CBR_Abacus;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.esotericsoftware.spine.Skeleton;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct1SharpshooterNewAge extends ArchetypeBaseIronclad {
/*  31 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("hermit:SpecialFriend"); public static final int damageThreshold = 10;
/*     */   
/*     */   public ArchetypeAct1SharpshooterNewAge() {
/*  34 */     super("HERMIT_SHARPSHOOTER_ARCHETYPE", "Dead On");
/*     */     
/*  36 */     this.maxHPModifier += 95;
/*  37 */     this.actNum = 1;
/*  38 */     this.bossMechanicName = HermitConcentrateAdder.NAME;
/*  39 */     this.bossMechanicDesc = HermitConcentrateAdder.DESC[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  44 */     super.addedPreBattle();
/*  45 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  46 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new HermitConcentrationPower((AbstractCreature)abstractCharBoss), 10));
/*     */     
/*  48 */     LouseTangerine louseTangerine = new LouseTangerine(-400.0F, 0.0F);
/*  49 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction((AbstractMonster)louseTangerine, true));
/*  50 */     louseTangerine.usePreBattleAction();
/*     */     
/*     */     try {
/*  53 */       Method loadAnimationMethod = AbstractCreature.class.getDeclaredMethod("loadAnimation", new Class[] { String.class, String.class, float.class });
/*  54 */       loadAnimationMethod.setAccessible(true);
/*  55 */       loadAnimationMethod.invoke(AbstractCharBoss.boss, new Object[] { "expansioncontentResources/images/bosses/hermit/1/Hermit_Sharp.atlas", "expansioncontentResources/images/bosses/hermit/1/Hermit_Sharp.json", Float.valueOf(1.0F) });
/*     */       
/*  57 */       AnimationState.TrackEntry e = AbstractCharBoss.boss.state.setAnimation(0, "Idle", true);
/*  58 */       ((AnimationStateData)ReflectionHacks.getPrivate(AbstractCharBoss.boss, AbstractCreature.class, "stateData")).setMix("Hit", "Idle", 0.1F);
/*  59 */       e.setTimeScale(0.9F);
/*  60 */       ((CharBossHermit)AbstractCharBoss.boss).eye = ((Skeleton)ReflectionHacks.getPrivate(AbstractCharBoss.boss, AbstractCreature.class, "skeleton")).findSlot("Eye1");
/*  61 */     } catch (Exception e) {
/*  62 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  70 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  71 */     addRelic((AbstractCharbossRelic)new CBR_Abacus());
/*  72 */     addRelic((AbstractCharbossRelic)new CBR_OddlySmoothStone());
/*  73 */     addRelic((AbstractCharbossRelic)new CBR_BrassTacks());
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  78 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  79 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */     
/*  81 */     if (!this.looped) {
/*  82 */       switch (this.turn) {
/*     */         case 0:
/*  84 */           addToList(cardsList, (AbstractCard)new EnHeadshot());
/*  85 */           addToList(cardsList, (AbstractCard)new EnDefendHermit());
/*  86 */           addToList(cardsList, (AbstractCard)new EnInjury());
/*  87 */           this.turn++;
/*     */           break;
/*     */         case 1:
/*  90 */           addToList(cardsList, (AbstractCard)new EnStrikeHermit());
/*  91 */           addToList(cardsList, (AbstractCard)new EnItchyTrigger());
/*  92 */           addToList(cardsList, (AbstractCard)new EnItchyTriggerStrikeHermit());
/*  93 */           this.turn++;
/*     */           break;
/*     */         case 2:
/*  96 */           addToList(cardsList, (AbstractCard)new EnGhostlyPresence());
/*  97 */           addToList(cardsList, (AbstractCard)new EnDive(), extraUpgrades);
/*  98 */           addToList(cardsList, (AbstractCard)new EnDefendHermit());
/*  99 */           this.turn++;
/*     */           break;
/*     */         case 3:
/* 102 */           addToList(cardsList, (AbstractCard)new EnLoneWolf());
/* 103 */           addToList(cardsList, (AbstractCard)new EnRoughhouse());
/* 104 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 105 */           this.turn++;
/*     */           break;
/*     */         case 4:
/* 108 */           addToList(cardsList, (AbstractCard)new EnDive());
/* 109 */           addToList(cardsList, (AbstractCard)new EnDeadeye());
/* 110 */           addToList(cardsList, (AbstractCard)new EnStrikeHermit());
/* 111 */           this.turn = 0;
/* 112 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 116 */       switch (this.turn) {
/*     */         case 0:
/* 118 */           if (AbstractCharBoss.boss.hasRelic("Abacus")) {
/* 119 */             AbstractCharBoss.boss.getRelic("Abacus").onShuffle();
/*     */           }
/* 121 */           addToList(cardsList, (AbstractCard)new EnHeadshot());
/* 122 */           addToList(cardsList, (AbstractCard)new EnDefendHermit());
/* 123 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 124 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 127 */           addToList(cardsList, (AbstractCard)new EnStrikeHermit());
/* 128 */           addToList(cardsList, (AbstractCard)new EnItchyTrigger());
/* 129 */           addToList(cardsList, (AbstractCard)new EnItchyTriggerStrikeHermit());
/* 130 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 133 */           addToList(cardsList, (AbstractCard)new EnGhostlyPresence());
/* 134 */           addToList(cardsList, (AbstractCard)new EnDive(), extraUpgrades);
/* 135 */           addToList(cardsList, (AbstractCard)new EnDefendHermit());
/* 136 */           this.turn++;
/*     */           break;
/*     */         case 3:
/* 139 */           addToList(cardsList, (AbstractCard)new EnLoneWolf());
/* 140 */           addToList(cardsList, (AbstractCard)new EnRoughhouse());
/* 141 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 142 */           this.turn++;
/*     */           break;
/*     */         case 4:
/* 145 */           addToList(cardsList, (AbstractCard)new EnDive());
/* 146 */           if (this.defaultToggle) {
/* 147 */             addToList(cardsList, (AbstractCard)new EnInjury());
/*     */           } else {
/* 149 */             addToList(cardsList, (AbstractCard)new EnDeadeye());
/* 150 */           }  addToList(cardsList, (AbstractCard)new EnStrikeHermit());
/* 151 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     } 
/* 155 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 160 */     addRelic((AbstractCharbossRelic)new CBR_Orichalcum());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Hermit\NewAge\ArchetypeAct1SharpshooterNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */