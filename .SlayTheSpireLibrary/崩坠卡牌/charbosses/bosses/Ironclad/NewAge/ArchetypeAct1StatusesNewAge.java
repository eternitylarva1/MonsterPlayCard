/*     */ package charbosses.bosses.Ironclad.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Ironclad.ArchetypeBaseIronclad;
/*     */ import charbosses.cards.curses.EnDoubt;
/*     */ import charbosses.cards.curses.EnIcky;
/*     */ import charbosses.cards.curses.EnMalfunctioning;
/*     */ import charbosses.cards.red.EnBash;
/*     */ import charbosses.cards.red.EnDefendRed;
/*     */ import charbosses.cards.red.EnFeelNoPain;
/*     */ import charbosses.cards.red.EnImmolate;
/*     */ import charbosses.cards.red.EnPowerThrough;
/*     */ import charbosses.cards.red.EnRecklessCharge;
/*     */ import charbosses.cards.red.EnSecondWind;
/*     */ import charbosses.cards.red.EnSeeingRed;
/*     */ import charbosses.cards.red.EnThunderclap;
/*     */ import charbosses.cards.red.EnTrueGrit;
/*     */ import charbosses.cards.red.EnWildStrike;
/*     */ import charbosses.cards.status.EnDazed;
/*     */ import charbosses.cards.status.EnWound;
/*     */ import charbosses.powers.bossmechanicpowers.IroncladStatusPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import charbosses.relics.CBR_ChampionsBelt;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct1StatusesNewAge extends ArchetypeBaseIronclad {
/*     */   boolean secondLoop = false;
/*     */   
/*     */   public ArchetypeAct1StatusesNewAge() {
/*  33 */     super("IC_STATUS_ARCHETYPE", "Status");
/*     */     
/*  35 */     this.maxHPModifier += 60;
/*  36 */     this.actNum = 1;
/*  37 */     this.bossMechanicName = IroncladStatusPower.NAME;
/*  38 */     this.bossMechanicDesc = IroncladStatusPower.DESC[0];
/*     */   }
/*     */   private AbstractCharbossRelic theArtOfWar;
/*     */   
/*     */   public void addedPreBattle() {
/*  43 */     super.addedPreBattle();
/*  44 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  45 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new IroncladStatusPower((AbstractCreature)abstractCharBoss)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  52 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*     */     
/*  54 */     this.theArtOfWar = (AbstractCharbossRelic)new CBR_ArtOfWar();
/*  55 */     addRelic(this.theArtOfWar);
/*     */ 
/*     */     
/*  58 */     addRelic((AbstractCharbossRelic)new CBR_ChampionsBelt());
/*     */ 
/*     */     
/*  61 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  66 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  67 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */ 
/*     */     
/*  70 */     if (!this.looped) {
/*  71 */       switch (this.turn) {
/*     */         case 0:
/*  73 */           addToList(cardsList, (AbstractCard)new EnBash());
/*  74 */           addToList(cardsList, (AbstractCard)new EnMalfunctioning());
/*  75 */           addToList(cardsList, (AbstractCard)new EnDoubt());
/*  76 */           this.turn++;
/*     */           break;
/*     */         case 1:
/*  79 */           addToList(cardsList, (AbstractCard)new EnWildStrike());
/*  80 */           addToList(cardsList, (AbstractCard)new EnRecklessCharge());
/*  81 */           addToList(cardsList, (AbstractCard)new EnDefendRed());
/*  82 */           this.turn++;
/*     */           break;
/*     */         case 2:
/*  85 */           addToList(cardsList, (AbstractCard)new EnPowerThrough(true), extraUpgrades);
/*  86 */           addToList(cardsList, (AbstractCard)new EnSecondWind());
/*  87 */           addToList(cardsList, (AbstractCard)new EnDefendRed());
/*  88 */           this.theArtOfWar.beginPulse();
/*  89 */           this.turn++;
/*     */           break;
/*     */         case 3:
/*  92 */           addToList(cardsList, (AbstractCard)new EnSeeingRed());
/*  93 */           addToList(cardsList, (AbstractCard)new EnImmolate());
/*  94 */           addToList(cardsList, (AbstractCard)new EnIcky());
/*  95 */           this.turn++;
/*     */           break;
/*     */         case 4:
/*  98 */           addToList(cardsList, (AbstractCard)new EnTrueGrit(), true);
/*  99 */           addToList(cardsList, (AbstractCard)new EnTrueGrit(), true);
/* 100 */           addToList(cardsList, (AbstractCard)new EnWound());
/* 101 */           this.theArtOfWar.beginPulse();
/* 102 */           this.turn++;
/*     */           break;
/*     */         case 5:
/* 105 */           addToList(cardsList, (AbstractCard)new EnThunderclap(), true);
/* 106 */           addToList(cardsList, (AbstractCard)new EnFeelNoPain());
/* 107 */           addToList(cardsList, (AbstractCard)new EnDazed());
/* 108 */           this.turn = 0;
/* 109 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 113 */       switch (this.turn) {
/*     */         case 0:
/* 115 */           addToList(cardsList, (AbstractCard)new EnPowerThrough(true), extraUpgrades);
/* 116 */           addToList(cardsList, (AbstractCard)new EnTrueGrit(), true);
/*     */ 
/*     */           
/* 119 */           addToList(cardsList, (AbstractCard)new EnWound());
/* 120 */           this.turn++;
/* 121 */           this.theArtOfWar.beginPulse();
/*     */           break;
/*     */         case 1:
/* 124 */           addToList(cardsList, (AbstractCard)new EnWildStrike());
/* 125 */           addToList(cardsList, (AbstractCard)new EnRecklessCharge());
/* 126 */           addToList(cardsList, (AbstractCard)new EnBash());
/* 127 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 130 */           addToList(cardsList, (AbstractCard)new EnSecondWind());
/* 131 */           addToList(cardsList, (AbstractCard)new EnBurn());
/* 132 */           addToList(cardsList, (AbstractCard)new EnWound());
/* 133 */           this.theArtOfWar.beginPulse();
/* 134 */           this.turn++;
/*     */           break;
/*     */         case 3:
/* 137 */           addToList(cardsList, (AbstractCard)new EnImmolate());
/* 138 */           addToList(cardsList, (AbstractCard)new EnThunderclap(), true);
/* 139 */           addToList(cardsList, (AbstractCard)new EnDazed());
/* 140 */           this.turn++;
/*     */           break;
/*     */         case 4:
/* 143 */           addToList(cardsList, (AbstractCard)new EnTrueGrit(), true);
/* 144 */           addToList(cardsList, (AbstractCard)new EnDefendRed());
/* 145 */           addToList(cardsList, (AbstractCard)new EnWound());
/* 146 */           this.theArtOfWar.beginPulse();
/* 147 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     } 
/* 151 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 156 */     addRelic((AbstractCharbossRelic)new CBR_Orichalcum());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Ironclad\NewAge\ArchetypeAct1StatusesNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */