/*     */ package charbosses.bosses.Ironclad.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Ironclad.ArchetypeBaseIronclad;
/*     */ import charbosses.cards.curses.EnDecay;
/*     */ import charbosses.cards.red.EnBodySlam;
/*     */ import charbosses.cards.red.EnEntrench;
/*     */ import charbosses.cards.red.EnFlameBarrier;
/*     */ import charbosses.cards.red.EnGhostlyArmor;
/*     */ import charbosses.cards.red.EnImpervious;
/*     */ import charbosses.cards.red.EnIntimidate;
/*     */ import charbosses.cards.red.EnMetallicize;
/*     */ import charbosses.cards.red.EnPowerThrough;
/*     */ import charbosses.cards.red.EnSecondWind;
/*     */ import charbosses.monsters.Fortification;
/*     */ import charbosses.powers.bossmechanicpowers.IroncladFortificationPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import charbosses.relics.CBR_CursedKey;
/*     */ import charbosses.relics.CBR_HornCleat;
/*     */ import charbosses.relics.CBR_Torii;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct3BlockNewAge extends ArchetypeBaseIronclad {
/*     */   public ArchetypeAct3BlockNewAge() {
/*  30 */     super("IC_BLOCK_ARCHETYPE", "Block");
/*     */     
/*  32 */     this.maxHPModifier += 300;
/*  33 */     this.actNum = 3;
/*  34 */     this.bossMechanicName = IroncladFortificationPower.NAME;
/*  35 */     this.bossMechanicDesc = IroncladFortificationPower.DESC[0] + '\n' + IroncladFortificationPower.DESC[1];
/*     */   }
/*     */   public static final int FORTIFICATION_AMOUNT = 10;
/*     */   
/*     */   public void addedPreBattle() {
/*  40 */     super.addedPreBattle();
/*  41 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  42 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new IroncladFortificationPower((AbstractCreature)abstractCharBoss)));
/*  43 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new BarricadePower((AbstractCreature)abstractCharBoss)));
/*  44 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction((AbstractMonster)new Fortification(), true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  52 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*     */ 
/*     */ 
/*     */     
/*  56 */     addRelic((AbstractCharbossRelic)new CBR_HornCleat());
/*  57 */     addRelic((AbstractCharbossRelic)new CBR_CursedKey());
/*  58 */     addRelic((AbstractCharbossRelic)new CBR_Torii());
/*  59 */     addRelic((AbstractCharbossRelic)new CBR_OddMushroom());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  71 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  72 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */ 
/*     */     
/*  75 */     if (!this.looped) {
/*  76 */       switch (this.turn) {
/*     */         case 0:
/*  78 */           addToList(cardsList, (AbstractCard)new EnFeelNoPain());
/*  79 */           addToList(cardsList, (AbstractCard)new EnImpervious());
/*  80 */           addToList(cardsList, (AbstractCard)new EnIntimidate(), true);
/*  81 */           this.turn++;
/*     */           break;
/*     */         case 1:
/*  84 */           addToList(cardsList, (AbstractCard)new EnFlameBarrier(), extraUpgrades);
/*  85 */           addToList(cardsList, (AbstractCard)new EnBodySlam());
/*  86 */           addToList(cardsList, (AbstractCard)new EnClumsy());
/*  87 */           this.turn++;
/*     */           break;
/*     */         case 2:
/*  90 */           addToList(cardsList, (AbstractCard)new EnImpervious());
/*  91 */           addToList(cardsList, (AbstractCard)new EnIntimidate(), true);
/*  92 */           addToList(cardsList, (AbstractCard)new EnEntrench());
/*  93 */           this.turn++;
/*     */           break;
/*     */         case 3:
/*  96 */           addToList(cardsList, (AbstractCard)new EnMetallicize());
/*  97 */           addToList(cardsList, (AbstractCard)new EnBodySlam());
/*  98 */           addToList(cardsList, (AbstractCard)new EnDecay());
/*  99 */           this.turn++;
/*     */           break;
/*     */         case 4:
/* 102 */           addToList(cardsList, (AbstractCard)new EnPowerThrough());
/* 103 */           addToList(cardsList, (AbstractCard)new EnSecondWind());
/* 104 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 105 */           this.turn++;
/*     */           break;
/*     */         case 5:
/* 108 */           addToList(cardsList, (AbstractCard)new EnMetallicize());
/* 109 */           addToList(cardsList, (AbstractCard)new EnGhostlyArmor());
/* 110 */           addToList(cardsList, (AbstractCard)new EnBodySlam());
/* 111 */           this.turn = 0;
/* 112 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 116 */       switch (this.turn) {
/*     */         case 0:
/* 118 */           addToList(cardsList, (AbstractCard)new EnPowerThrough());
/* 119 */           addToList(cardsList, (AbstractCard)new EnSecondWind());
/* 120 */           addToList(cardsList, (AbstractCard)new EnBodySlam());
/* 121 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 124 */           addToList(cardsList, (AbstractCard)new EnFlameBarrier(), extraUpgrades);
/* 125 */           addToList(cardsList, (AbstractCard)new EnBodySlam());
/* 126 */           addToList(cardsList, (AbstractCard)new EnDecay());
/* 127 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 130 */           addToList(cardsList, (AbstractCard)new EnGhostlyArmor());
/* 131 */           addToList(cardsList, (AbstractCard)new EnEntrench());
/* 132 */           addToList(cardsList, (AbstractCard)new EnBodySlam());
/* 133 */           this.turn++;
/* 134 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     } 
/* 138 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 143 */     addRelic((AbstractCharbossRelic)new CBR_SelfFormingClay());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Ironclad\NewAge\ArchetypeAct3BlockNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */