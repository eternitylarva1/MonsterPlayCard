/*     */ package charbosses.bosses.Ironclad.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Ironclad.ArchetypeBaseIronclad;
/*     */ import charbosses.cards.colorless.EnSwiftStrike;
/*     */ import charbosses.cards.curses.EnClumsy;
/*     */ import charbosses.cards.red.EnBloodletting;
/*     */ import charbosses.cards.red.EnClothesline;
/*     */ import charbosses.cards.red.EnDefendRed;
/*     */ import charbosses.cards.red.EnFeed;
/*     */ import charbosses.cards.red.EnFlameBarrier;
/*     */ import charbosses.cards.red.EnGhostlyArmor;
/*     */ import charbosses.cards.red.EnHeadbutt;
/*     */ import charbosses.cards.red.EnHeavyBlade;
/*     */ import charbosses.cards.red.EnHemokinesis;
/*     */ import charbosses.cards.red.EnReaper;
/*     */ import charbosses.cards.red.EnStrikeRed;
/*     */ import charbosses.cards.red.EnSummonMushrooms;
/*     */ import charbosses.cards.red.EnTwinStrike;
/*     */ import charbosses.powers.bossmechanicpowers.IroncladMushroomPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import charbosses.relics.CBR_RedSkull;
/*     */ import charbosses.relics.CBR_Vajra;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct2MushroomsNewAge extends ArchetypeBaseIronclad {
/*     */   public ArchetypeAct2MushroomsNewAge() {
/*  30 */     super("IC_MUSHROOM_ARCHETYPE", "Mushroom");
/*     */     
/*  32 */     this.maxHPModifier += 190;
/*  33 */     this.actNum = 2;
/*  34 */     this.bossMechanicName = IroncladMushroomPower.NAME;
/*  35 */     this.bossMechanicDesc = IroncladMushroomPower.DESC[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  40 */     super.addedPreBattle();
/*  41 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  42 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new IroncladMushroomPower((AbstractCreature)abstractCharBoss)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  50 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*     */ 
/*     */ 
/*     */     
/*  54 */     addRelic((AbstractCharbossRelic)new CBR_RedSkull());
/*  55 */     addRelic((AbstractCharbossRelic)new CBR_MagicFlower());
/*  56 */     addRelic((AbstractCharbossRelic)new CBR_Vajra());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  79 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  80 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */ 
/*     */     
/*  83 */     if (!this.looped) {
/*  84 */       switch (this.turn) {
/*     */         case 0:
/*  86 */           addToList(cardsList, (AbstractCard)new EnTwinStrike());
/*  87 */           addToList(cardsList, (AbstractCard)new EnFeed());
/*  88 */           addToList(cardsList, (AbstractCard)new EnClothesline());
/*  89 */           this.turn++;
/*     */           break;
/*     */         case 1:
/*  92 */           addToList(cardsList, (AbstractCard)new EnSummonMushrooms());
/*  93 */           addToList(cardsList, (AbstractCard)new EnGhostlyArmor(), true);
/*  94 */           addToList(cardsList, (AbstractCard)new EnHemokinesis());
/*  95 */           this.turn++;
/*     */           break;
/*     */         case 2:
/*  98 */           addToList(cardsList, (AbstractCard)new EnReaper());
/*  99 */           addToList(cardsList, (AbstractCard)new EnDefendRed());
/* 100 */           addToList(cardsList, (AbstractCard)new EnClumsy());
/* 101 */           this.turn++;
/*     */           break;
/*     */         case 3:
/* 104 */           addToList(cardsList, (AbstractCard)new EnSwiftStrike());
/* 105 */           addToList(cardsList, (AbstractCard)new EnHeavyBlade());
/* 106 */           addToList(cardsList, (AbstractCard)new EnClumsy());
/* 107 */           this.turn++;
/*     */           break;
/*     */         case 4:
/* 110 */           addToList(cardsList, (AbstractCard)new EnBloodletting());
/* 111 */           addToList(cardsList, (AbstractCard)new EnHeadbutt());
/* 112 */           addToList(cardsList, (AbstractCard)new EnFlameBarrier());
/* 113 */           this.turn++;
/*     */           break;
/*     */         case 5:
/* 116 */           addToList(cardsList, (AbstractCard)new EnSummonMushrooms());
/* 117 */           addToList(cardsList, (AbstractCard)new EnInflame(), extraUpgrades);
/* 118 */           addToList(cardsList, (AbstractCard)new EnStrikeRed());
/* 119 */           this.turn = 0;
/* 120 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 124 */       switch (this.turn) {
/*     */         case 0:
/* 126 */           addToList(cardsList, (AbstractCard)new EnBloodletting());
/* 127 */           addToList(cardsList, (AbstractCard)new EnHeavyBlade());
/* 128 */           addToList(cardsList, (AbstractCard)new EnReaper());
/* 129 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 132 */           addToList(cardsList, (AbstractCard)new EnTwinStrike());
/* 133 */           addToList(cardsList, (AbstractCard)new EnGhostlyArmor(), true);
/* 134 */           addToList(cardsList, (AbstractCard)new EnSwiftStrike());
/* 135 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 138 */           addToList(cardsList, (AbstractCard)new EnClothesline());
/* 139 */           addToList(cardsList, (AbstractCard)new EnStrikeRed());
/* 140 */           addToList(cardsList, (AbstractCard)new EnFlameBarrier());
/* 141 */           this.turn++;
/*     */           break;
/*     */         case 3:
/* 144 */           addToList(cardsList, (AbstractCard)new EnSummonMushrooms());
/* 145 */           addToList(cardsList, (AbstractCard)new EnHemokinesis());
/* 146 */           addToList(cardsList, (AbstractCard)new EnHeadbutt());
/* 147 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     } 
/* 151 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 156 */     addRelic((AbstractCharbossRelic)new CBR_DuvuDoll(2));
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Ironclad\NewAge\ArchetypeAct2MushroomsNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */