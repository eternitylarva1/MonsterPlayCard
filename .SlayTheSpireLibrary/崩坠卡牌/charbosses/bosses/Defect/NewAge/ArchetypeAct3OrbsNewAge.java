/*     */ package charbosses.bosses.Defect.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Defect.CharBossDefect;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import charbosses.cards.blue.EnCapacitor;
/*     */ import charbosses.cards.blue.EnChargeBattery;
/*     */ import charbosses.cards.blue.EnColdSnap;
/*     */ import charbosses.cards.blue.EnConsume;
/*     */ import charbosses.cards.blue.EnDualcast;
/*     */ import charbosses.cards.blue.EnForceField;
/*     */ import charbosses.cards.blue.EnFusion;
/*     */ import charbosses.cards.blue.EnHologram;
/*     */ import charbosses.cards.blue.EnMulticast;
/*     */ import charbosses.cards.colorless.EnGoodInstincts;
/*     */ import charbosses.cards.curses.EnInjury;
/*     */ import charbosses.orbs.AbstractEnemyOrb;
/*     */ import charbosses.powers.bossmechanicpowers.DefectBiasCuriosityPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct3OrbsNewAge extends ArchetypeBaseDefect {
/*     */   private EnClaw c;
/*  29 */   private boolean A19 = (AbstractDungeon.ascensionLevel >= 19); private CharBossDefect cB;
/*     */   
/*     */   public ArchetypeAct3OrbsNewAge() {
/*  32 */     super("DF_ARCHETYPE_ORBS", "Orbs");
/*     */     
/*  34 */     this.maxHPModifier += 350;
/*  35 */     this.actNum = 3;
/*  36 */     this.bossMechanicName = DefectBiasCuriosityPower.NAME;
/*  37 */     this.bossMechanicDesc = DefectBiasCuriosityPower.DESCRIPTIONS[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  42 */     super.addedPreBattle();
/*  43 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  44 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new FocusPower((AbstractCreature)abstractCharBoss, 4)));
/*  45 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new DefectBiasCuriosityPower((AbstractCreature)abstractCharBoss)));
/*  46 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  47 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, 2));
/*     */     }
/*     */   }
/*     */   
/*     */   public void initialize() {
/*  52 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  53 */     addRelic((AbstractCharbossRelic)new CBR_ArtOfWar());
/*  54 */     addRelic((AbstractCharbossRelic)new CBR_Lantern());
/*  55 */     addRelic((AbstractCharbossRelic)new CBR_IceCream());
/*  56 */     addRelic((AbstractCharbossRelic)new CBR_FusionHammer());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void increasePretendFocus(int amount) {
/*  61 */     AbstractEnemyOrb.masterPretendFocus += amount;
/*  62 */     for (AbstractOrb o : AbstractCharBoss.boss.orbs) {
/*  63 */       if (o instanceof AbstractEnemyOrb) {
/*  64 */         ((AbstractEnemyOrb)o).pretendFocus += amount;
/*  65 */         o.applyFocus();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void resetPretendFocus() {
/*  72 */     for (AbstractOrb o : AbstractCharBoss.boss.orbs) {
/*  73 */       if (o instanceof AbstractEnemyOrb) {
/*  74 */         ((AbstractEnemyOrb)o).pretendFocus = 0;
/*  75 */         AbstractEnemyOrb.masterPretendFocus = 0;
/*  76 */         o.applyFocus();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  87 */     if (this.cB == null) {
/*  88 */       this.cB = (CharBossDefect)AbstractCharBoss.boss;
/*     */     }
/*  90 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  91 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */     
/*  93 */     if (!this.looped) {
/*  94 */       switch (this.turn) {
/*     */ 
/*     */         
/*     */         case 0:
/*  98 */           addToList(cardsList, (AbstractCard)new EnMachineLearning(), true);
/*  99 */           addToList(cardsList, (AbstractCard)new EnChargeBattery());
/* 100 */           addToList(cardsList, (AbstractCard)new EnRainbow());
/*     */           
/* 102 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 107 */           addToList(cardsList, (AbstractCard)new EnDualcast());
/*     */           
/* 109 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeOverride = true;
/* 110 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeMult = 2;
/* 111 */           addToList(cardsList, (AbstractCard)new EnHologram(), true);
/*     */           
/* 113 */           addToList(cardsList, (AbstractCard)new EnDualcast());
/*     */           
/* 115 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeOverride = true;
/* 116 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeMult = 2;
/* 117 */           addToList(cardsList, (AbstractCard)new EnStorm());
/* 118 */           AbstractBossCard.fakeStormPower = true;
/*     */ 
/*     */           
/* 121 */           addToList(cardsList, (AbstractCard)new EnMulticast(1));
/* 122 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(2)).evokeOverride = true;
/* 123 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(2)).evokeMult = 1;
/*     */           
/* 125 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 130 */           addToList(cardsList, (AbstractCard)new EnConsume());
/* 131 */           increasePretendFocus(2);
/* 132 */           addToList(cardsList, (AbstractCard)new EnCapacitor());
/*     */           
/* 134 */           addToList(cardsList, (AbstractCard)new EnColdSnap(), extraUpgrades);
/*     */ 
/*     */           
/* 137 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 138 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 143 */           addToList(cardsList, (AbstractCard)new EnDoubleEnergy(), false);
/*     */           
/* 145 */           addToList(cardsList, (AbstractCard)new EnCreativeAI(), false);
/*     */           
/* 147 */           addToList(cardsList, (AbstractCard)new EnFusion(true, false), true);
/*     */           
/* 149 */           addToList(cardsList, (AbstractCard)new EnForceField());
/* 150 */           this.turn = 0;
/* 151 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     } else {
/* 155 */       switch (this.turn) {
/*     */         
/*     */         case 0:
/* 158 */           addToList(cardsList, (AbstractCard)new EnBuffer(true), false);
/* 159 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeOverride = true;
/* 160 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeMult = 1;
/*     */ 
/*     */           
/* 163 */           addToList(cardsList, (AbstractCard)new EnGoodInstincts());
/* 164 */           addToList(cardsList, (AbstractCard)new EnBarrage(4));
/* 165 */           addToList(cardsList, (AbstractCard)new EnColdSnap(), extraUpgrades);
/* 166 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeOverride = true;
/* 167 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeMult = 1;
/*     */           
/* 169 */           addToList(cardsList, (AbstractCard)new EnChargeBattery());
/*     */ 
/*     */           
/* 172 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 1:
/* 176 */           addToList(cardsList, (AbstractCard)new EnElectrodynamics(), false);
/* 177 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeOverride = true;
/* 178 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeMult = 1;
/* 179 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeOverride = true;
/* 180 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeMult = 1;
/* 181 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(2)).evokeOverride = true;
/* 182 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(2)).evokeMult = 1;
/*     */           
/* 184 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 185 */           addToList(cardsList, (AbstractCard)new EnDualcast());
/*     */           
/* 187 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(3)).evokeOverride = true;
/* 188 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(3)).evokeMult = 2;
/*     */           
/* 190 */           addToList(cardsList, (AbstractCard)new EnFusion(true, false), true);
/* 191 */           addToList(cardsList, (AbstractCard)new EnForceField());
/*     */           
/* 193 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/* 197 */           addToList(cardsList, (AbstractCard)new EnDefragment(), false);
/* 198 */           increasePretendFocus(1);
/* 199 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeOverride = true;
/* 200 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeMult = 1;
/*     */           
/* 202 */           addToList(cardsList, (AbstractCard)new EnColdSnap(), false);
/* 203 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeOverride = true;
/* 204 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeMult = 1;
/*     */           
/* 206 */           addToList(cardsList, (AbstractCard)new EnHologram(), true);
/* 207 */           addToList(cardsList, (AbstractCard)new EnDualcast());
/* 208 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(2)).evokeOverride = true;
/* 209 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(2)).evokeMult = 2;
/*     */           
/* 211 */           addToList(cardsList, (AbstractCard)new EnChargeBattery());
/* 212 */           addToList(cardsList, (AbstractCard)new EnGoodInstincts());
/* 213 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeOverride = true;
/* 214 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(1)).evokeMult = 1;
/*     */           
/* 216 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 3:
/* 220 */           addToList(cardsList, (AbstractCard)new EnCapacitor(), false);
/*     */           
/* 222 */           addToList(cardsList, (AbstractBossCard)new EnMulticast(this.cB.energyPanel.getCurrentEnergy() - 1), false, 2 * (this.cB.energyPanel.getCurrentEnergy() - 1));
/*     */           
/* 224 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeOverride = true;
/* 225 */           ((AbstractEnemyOrb)this.cB.orbsAsEn().get(0)).evokeMult = 4;
/* 226 */           addToList(cardsList, (AbstractCard)new EnConsume());
/* 227 */           increasePretendFocus(2);
/*     */           
/* 229 */           addToList(cardsList, (AbstractCard)new EnFusion(true, false), true);
/*     */ 
/*     */           
/* 232 */           addToList(cardsList, (AbstractCard)new EnHologram(), true);
/* 233 */           addToList(cardsList, (AbstractCard)new EnConsume());
/* 234 */           increasePretendFocus(2);
/*     */           
/* 236 */           this.turn = 0;
/* 237 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 242 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 247 */     addRelic((AbstractCharbossRelic)new CBR_BirdFacedUrn());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Defect\NewAge\ArchetypeAct3OrbsNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */