/*     */ package charbosses.bosses.Silent.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import charbosses.cards.green.EnBladeDance;
/*     */ import charbosses.cards.green.EnBlur;
/*     */ import charbosses.cards.green.EnBurst;
/*     */ import charbosses.cards.green.EnCloakAndDagger;
/*     */ import charbosses.cards.green.EnDash;
/*     */ import charbosses.cards.green.EnDefendGreen;
/*     */ import charbosses.cards.green.EnEndlessAgony;
/*     */ import charbosses.cards.green.EnFlyingKnee;
/*     */ import charbosses.cards.green.EnSuckerPunch;
/*     */ import charbosses.powers.bossmechanicpowers.SilentShivTimeEaterPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct3ShivsNewAge extends ArchetypeBaseSilent {
/*     */   public ArchetypeAct3ShivsNewAge() {
/*  22 */     super("SI_SHIV_ARCHETYPE", "Shivs");
/*     */     
/*  24 */     this.maxHPModifier += 350;
/*  25 */     this.actNum = 3;
/*  26 */     this.bossMechanicName = SilentShivTimeEaterPower.NAME;
/*  27 */     this.bossMechanicDesc = SilentShivTimeEaterPower.DESC[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  32 */     super.addedPreBattle();
/*  33 */     AbstractPlayer abstractPlayer = AbstractDungeon.player;
/*  34 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new SilentShivTimeEaterPower((AbstractCreature)AbstractCharBoss.boss)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  41 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  42 */     addRelic((AbstractCharbossRelic)new CBR_FusionHammer());
/*  43 */     addRelic((AbstractCharbossRelic)new CBR_MummifiedHand());
/*  44 */     addRelic((AbstractCharbossRelic)new CBR_Shuriken());
/*  45 */     addRelic((AbstractCharbossRelic)new CBR_OrnamentalFan());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  51 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  52 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*  53 */     if (!this.looped) {
/*  54 */       EnDash enDash; switch (this.turn) {
/*     */         
/*     */         case 0:
/*  57 */           addToList(cardsList, (AbstractCard)new EnPredator());
/*  58 */           addToList(cardsList, (AbstractCard)new EnFlyingKnee());
/*  59 */           addToList(cardsList, (AbstractCard)new EnBurst());
/*  60 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 1:
/*  64 */           addToList(cardsList, (AbstractCard)new EnFlechettes());
/*  65 */           addToList(cardsList, (AbstractCard)new EnLegSweep());
/*  66 */           addToList(cardsList, (AbstractCard)new EnCloakAndDagger());
/*  67 */           addToList(cardsList, (AbstractCard)new EnNeutralize());
/*  68 */           addToList(cardsList, (AbstractCard)new EnBlur());
/*     */           
/*  70 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/*  74 */           addToList(cardsList, (AbstractCard)new EnEndlessAgony());
/*  75 */           addToList(cardsList, (AbstractCard)new EnEndlessAgony());
/*  76 */           addToList(cardsList, (AbstractCard)new EnFinisher(2), extraUpgrades);
/*  77 */           addToList(cardsList, (AbstractCard)new EnDefendGreen());
/*  78 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 3:
/*  82 */           addToList(cardsList, (AbstractCard)new EnBladeDance());
/*  83 */           addToList(cardsList, (AbstractCard)new EnSuckerPunch());
/*  84 */           addToList(cardsList, (AbstractCard)new EnDaggerSpray());
/*  85 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 4:
/*  89 */           addToList(cardsList, (AbstractCard)new EnPiercingWail());
/*  90 */           addToList(cardsList, (AbstractCard)new EnAfterImage());
/*  91 */           enDash = new EnDash();
/*  92 */           ((AbstractBossCard)enDash).cost = 0;
/*  93 */           ((AbstractBossCard)enDash).freeToPlayOnce = true;
/*  94 */           enDash.modifyCostForCombat(-2);
/*  95 */           addToList(cardsList, (AbstractCard)enDash);
/*  96 */           this.turn = 0;
/*  97 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/*     */       EnCloakAndDagger enCloakAndDagger;
/* 103 */       switch (this.turn) {
/*     */ 
/*     */         
/*     */         case 0:
/* 107 */           addToList(cardsList, (AbstractCard)new EnBurst());
/* 108 */           addToList(cardsList, (AbstractCard)new EnCloakAndDagger());
/* 109 */           enCloakAndDagger = new EnCloakAndDagger();
/* 110 */           ((AbstractBossCard)enCloakAndDagger).freeToPlayOnce = true;
/* 111 */           ((AbstractBossCard)enCloakAndDagger).costForTurn = 0;
/* 112 */           addToList(cardsList, (AbstractCard)enCloakAndDagger);
/* 113 */           addToList(cardsList, (AbstractCard)new EnSuckerPunch());
/* 114 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 119 */           addToList(cardsList, (AbstractCard)new EnPredator());
/* 120 */           addToList(cardsList, (AbstractCard)new EnDaggerSpray());
/* 121 */           addToList(cardsList, (AbstractCard)new EnDefendGreen());
/* 122 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/* 126 */           addToList(cardsList, (AbstractCard)new EnFlyingKnee());
/* 127 */           addToList(cardsList, (AbstractCard)new EnFlechettes());
/* 128 */           addToList(cardsList, (AbstractCard)new EnFinisher(2), extraUpgrades);
/* 129 */           addToList(cardsList, (AbstractCard)new EnLegSweep());
/* 130 */           addToList(cardsList, (AbstractCard)new EnBlur());
/* 131 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 3:
/* 135 */           addToList(cardsList, (AbstractCard)new EnDash());
/* 136 */           addToList(cardsList, (AbstractCard)new EnNeutralize());
/* 137 */           addToList(cardsList, (AbstractCard)new EnBladeDance());
/* 138 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     } 
/* 142 */     return cardsList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 148 */     addRelic((AbstractCharbossRelic)new CBR_Kunai());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Silent\NewAge\ArchetypeAct3ShivsNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */