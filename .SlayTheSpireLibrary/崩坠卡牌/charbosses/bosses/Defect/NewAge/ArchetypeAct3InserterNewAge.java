/*     */ package charbosses.bosses.Defect.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Defect.CharBossDefect;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import charbosses.cards.blue.EnBallLightning;
/*     */ import charbosses.cards.blue.EnBarrage;
/*     */ import charbosses.cards.blue.EnColdSnap;
/*     */ import charbosses.cards.blue.EnDefendBlue;
/*     */ import charbosses.cards.blue.EnDualcast;
/*     */ import charbosses.cards.blue.EnFusion;
/*     */ import charbosses.cards.blue.EnMeteorStrike;
/*     */ import charbosses.cards.blue.EnRebound;
/*     */ import charbosses.cards.blue.EnReinforcedBody;
/*     */ import charbosses.cards.blue.EnThunderStrike;
/*     */ import charbosses.cards.blue.EnZap;
/*     */ import charbosses.orbs.AbstractEnemyOrb;
/*     */ import charbosses.orbs.EnemyEmptyOrbSlot;
/*     */ import charbosses.powers.bossmechanicpowers.DefectCuriosityLightningPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import charbosses.relics.CBR_Calipers;
/*     */ import charbosses.relics.CBR_ChemicalX;
/*     */ import charbosses.relics.CBR_IceCream;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ 
/*     */ public class ArchetypeAct3InserterNewAge extends ArchetypeBaseDefect {
/*     */   private CharBossDefect cB;
/*  31 */   private boolean A19 = (AbstractDungeon.ascensionLevel >= 19);
/*     */   int loops;
/*     */   
/*  34 */   public ArchetypeAct3InserterNewAge() { super("DF_ARCHETYPE_ORBS", "Inserter");
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
/*  60 */     this.loops = 0; this.maxHPModifier += 330; this.actNum = 3; this.bossMechanicName = DefectCuriosityLightningPower.NAME;
/*     */     this.bossMechanicDesc = DefectCuriosityLightningPower.DESCRIPTIONS[0] + '\001' + DefectCuriosityLightningPower.DESCRIPTIONS[1]; }
/*     */   public void addedPreBattle() { super.addedPreBattle();
/*     */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  64 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new DefectCuriosityLightningPower((AbstractCreature)abstractCharBoss))); } public ArrayList<AbstractCard> getThisTurnCards() { if (this.cB == null) {
/*  65 */       this.cB = (CharBossDefect)AbstractCharBoss.boss;
/*     */     }
/*  67 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  68 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */     
/*  70 */     if (!this.looped) {
/*  71 */       EnDualcast dualcast; switch (this.turn) {
/*     */         
/*     */         case 0:
/*  74 */           addToList(cardsList, (AbstractCard)new EnFusion(), true);
/*  75 */           addToList(cardsList, (AbstractCard)new EnBallLightning(), extraUpgrades);
/*  76 */           addToList(cardsList, (AbstractCard)new EnClumsy());
/*     */           
/*  78 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/*  83 */           addToList(cardsList, (AbstractCard)new EnColdSnap());
/*     */           
/*  85 */           addToList(cardsList, (AbstractCard)new EnBarrage(3), extraUpgrades);
/*  86 */           addToList(cardsList, (AbstractCard)new EnDefendBlue());
/*  87 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/*  92 */           addToList(cardsList, (AbstractCard)new EnDoubleEnergy(3), true);
/*     */           
/*  94 */           addToList(cardsList, (AbstractCard)new EnRebound());
/*  95 */           addToList(cardsList, (AbstractCard)new EnMeteorStrike(), false);
/*     */           
/*  97 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 3:
/* 101 */           dualcast = new EnDualcast();
/*     */           
/* 103 */           addToList(cardsList, (AbstractCard)dualcast);
/* 104 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeOverride = true;
/* 105 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeMult = 2;
/*     */           
/* 107 */           addToList(cardsList, (AbstractCard)new EnMeteorStrike(), false);
/* 108 */           addToList(cardsList, (AbstractCard)new EnZap());
/*     */           
/* 110 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 4:
/* 114 */           addToList(cardsList, (AbstractCard)new EnDefragment(), false);
/* 115 */           ArchetypeAct3OrbsNewAge.increasePretendFocus(1);
/* 116 */           addToList(cardsList, (AbstractCard)new EnThunderStrike(EnThunderStrike.getLightningCount()), false);
/*     */           
/* 118 */           addToList(cardsList, (AbstractCard)new EnReinforcedBody(this.cB.energyPanel.getCurrentEnergy() - 4), false);
/*     */           
/* 120 */           this.turn = 0;
/* 121 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 125 */       switch (this.turn) {
/*     */ 
/*     */ 
/*     */         
/*     */         case 0:
/* 130 */           addToList(cardsList, (AbstractCard)new EnRebound());
/* 131 */           addToList(cardsList, (AbstractCard)new EnBarrage(this.cB.filledOrbCount()), extraUpgrades);
/* 132 */           addToList(cardsList, (AbstractCard)new EnDefendBlue());
/* 133 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 138 */           addToList(cardsList, (AbstractCard)new EnColdSnap());
/*     */           
/* 140 */           addToList(cardsList, (AbstractCard)new EnBarrage(Math.min(this.cB.maxOrbs, this.cB.filledOrbCount() + 1)), extraUpgrades);
/* 141 */           addToList(cardsList, (AbstractCard)new EnReinforcedBody(this.cB.energyPanel.getCurrentEnergy() - 2), false);
/*     */           
/* 143 */           this.turn++;
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 152 */           addToList(cardsList, (AbstractCard)new EnDualcast());
/* 153 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeOverride = true;
/* 154 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeMult = 2;
/* 155 */           addToList(cardsList, (AbstractCard)new EnFusion(), true);
/* 156 */           addToList(cardsList, (AbstractCard)new EnBallLightning(), extraUpgrades);
/*     */           
/* 158 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 163 */           addToList(cardsList, (AbstractCard)new EnMeteorStrike(), false);
/*     */           
/* 165 */           addToList(cardsList, (AbstractCard)new EnThunderStrike(EnThunderStrike.getLightningCount()), false);
/* 166 */           addToList(cardsList, (AbstractCard)new EnZap());
/*     */           
/* 168 */           this.turn = 0;
/* 169 */           this.looped = true;
/* 170 */           this.loops++;
/*     */           break;
/*     */       } 
/*     */     } 
/* 174 */     fixUpOrbIntents(cardsList);
/* 175 */     return cardsList; }
/*     */   public void initialize() { addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing()); addRelic((AbstractCharbossRelic)new CBR_Calipers()); addRelic((AbstractCharbossRelic)new CBR_IceCream()); addRelic((AbstractCharbossRelic)new CBR_ChemicalX());
/*     */     addRelic((AbstractCharbossRelic)new CBR_Inserter());
/* 178 */     ArchetypeAct3OrbsNewAge.resetPretendFocus(); } public static void fixUpOrbIntents() { fixUpOrbIntents(AbstractCharBoss.boss.hand.group); }
/*     */ 
/*     */   
/*     */   private static void fixUpOrbIntents(Collection<AbstractCard> hand) {
/* 182 */     CharBossDefect cB = (CharBossDefect)AbstractCharBoss.boss;
/* 183 */     int currentE = cB.energyPanel.getCurrentEnergy();
/* 184 */     ArrayList<AbstractEnemyOrb> mockOrbs = new ArrayList<>();
/* 185 */     for (AbstractEnemyOrb orb : cB.orbsAsEn()) {
/* 186 */       if (!(orb instanceof EnemyEmptyOrbSlot))
/* 187 */         mockOrbs.add((AbstractEnemyOrb)orb.makeCopy()); 
/*     */     } 
/* 189 */     int orbsEvoked = 0;
/* 190 */     System.out.println(hand);
/* 191 */     System.out.println(mockOrbs);
/*     */     
/* 193 */     for (AbstractCard absCard : hand) {
/* 194 */       AbstractBossCard card = (AbstractBossCard)absCard;
/* 195 */       if (card.cost > currentE) {
/* 196 */         card.bossDarken();
/*     */         continue;
/*     */       } 
/* 199 */       if (card instanceof EnReinforcedBody) {
/* 200 */         card.cost = currentE;
/*     */         
/* 202 */         cB.refreshIntentHbLocation();
/* 203 */         card.applyPowers();
/* 204 */         AbstractCharBoss.boss.hand.refreshHandLayout();
/* 205 */         currentE = 0;
/*     */       } else {
/*     */         
/* 208 */         currentE -= card.cost;
/*     */       } 
/* 210 */       card.bossLighten();
/* 211 */       currentE += card.energyGeneratedIfPlayed;
/*     */       
/* 213 */       if (card instanceof EnDualcast) {
/* 214 */         if (mockOrbs.get(0) instanceof charbosses.orbs.EnemyPlasma) {
/* 215 */           currentE += 4;
/* 216 */           card.energyGeneratedIfPlayed = 4;
/*     */         } 
/* 218 */         orbsEvoked++;
/* 219 */         mockOrbs.remove(0);
/*     */       } 
/* 221 */       for (int j = 0; j < card.showEvokeOrbCount; j++) {
/* 222 */         mockOrbs.add(new EnemyEmptyOrbSlot());
/*     */       }
/* 224 */       while (mockOrbs.size() > cB.maxOrbs) {
/* 225 */         if (mockOrbs.get(0) instanceof charbosses.orbs.EnemyPlasma) {
/* 226 */           currentE += 2;
/* 227 */           card.energyGeneratedIfPlayed += 2;
/*     */         } 
/* 229 */         orbsEvoked++;
/* 230 */         mockOrbs.remove(0);
/*     */       } 
/* 232 */       System.out.println(mockOrbs);
/* 233 */       System.out.println(card + ": " + card.energyGeneratedIfPlayed);
/*     */     } 
/* 235 */     for (int i = 0; i < cB.orbsAsEn().size(); i++) {
/* 236 */       ((AbstractEnemyOrb)cB.orbsAsEn().get(i)).evokeOverride = (i < orbsEvoked);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 242 */     addRelic((AbstractCharbossRelic)new CBR_StrikeDummy());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Defect\NewAge\ArchetypeAct3InserterNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */