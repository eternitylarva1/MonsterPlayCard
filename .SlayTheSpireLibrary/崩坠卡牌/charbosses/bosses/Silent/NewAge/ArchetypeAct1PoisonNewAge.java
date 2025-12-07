/*     */ package charbosses.bosses.Silent.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import charbosses.cards.green.EnBane;
/*     */ import charbosses.cards.green.EnBurst;
/*     */ import charbosses.cards.green.EnCripplingCloud;
/*     */ import charbosses.cards.green.EnDeflect;
/*     */ import charbosses.cards.green.EnDodgeAndRoll;
/*     */ import charbosses.cards.green.EnPoisonedStab;
/*     */ import charbosses.cards.green.EnStrikeGreen;
/*     */ import charbosses.cards.green.EnSurvivor;
/*     */ import charbosses.powers.bossmechanicpowers.SilentPoisonPower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct1PoisonNewAge extends ArchetypeBaseSilent {
/*     */   public ArchetypeAct1PoisonNewAge() {
/*  24 */     super("SI_POISON_ARCHETYPE", "Poison");
/*     */ 
/*     */     
/*  27 */     this.maxHPModifier += 100;
/*  28 */     this.actNum = 1;
/*  29 */     this.bossMechanicName = SilentPoisonPower.NAME;
/*  30 */     this.bossMechanicDesc = SilentPoisonPower.DESC[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  35 */     super.addedPreBattle();
/*     */ 
/*     */     
/*  38 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new SilentPoisonPower((AbstractCreature)AbstractCharBoss.boss)));
/*  39 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Antidote(), 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  46 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  47 */     addRelic((AbstractCharbossRelic)new CBR_TwistedFunnel());
/*  48 */     addRelic((AbstractCharbossRelic)new CBR_Anchor());
/*  49 */     addRelic((AbstractCharbossRelic)new CBR_Lantern());
/*     */ 
/*     */     
/*     */     try {
/*  53 */       Method loadAnimationMethod = AbstractCreature.class.getDeclaredMethod("loadAnimation", new Class[] { String.class, String.class, float.class });
/*  54 */       loadAnimationMethod.setAccessible(true);
/*  55 */       loadAnimationMethod.invoke(AbstractCharBoss.boss, new Object[] { "expansioncontentResources/images/bosses/silent/1/Poison_Silent.atlas", "expansioncontentResources/images/bosses/silent/1/Poison_Silent.json", Float.valueOf(1.0F) });
/*  56 */       AnimationState.TrackEntry e = AbstractCharBoss.boss.state.setAnimation(0, "Idle", true);
/*  57 */       e.setTimeScale(0.9F);
/*  58 */     } catch (Exception e) {
/*  59 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  65 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  66 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */     
/*  68 */     if (!this.looped) {
/*  69 */       EnCripplingCloud enCripplingCloud; switch (this.turn) {
/*     */         
/*     */         case 0:
/*  72 */           addToList(cardsList, (AbstractCard)new EnBurst());
/*  73 */           addToList(cardsList, (AbstractCard)new EnCripplingCloud());
/*  74 */           enCripplingCloud = new EnCripplingCloud();
/*  75 */           ((AbstractBossCard)enCripplingCloud).cost = 0;
/*  76 */           ((AbstractBossCard)enCripplingCloud).freeToPlayOnce = true;
/*  77 */           enCripplingCloud.modifyCostForCombat(-2);
/*  78 */           addToList(cardsList, (AbstractCard)enCripplingCloud);
/*  79 */           addToList(cardsList, (AbstractCard)new EnSurvivor());
/*  80 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/*  85 */           addToList(cardsList, (AbstractCard)new EnPoisonedStab(), true);
/*  86 */           addToList(cardsList, (AbstractCard)new EnDodgeAndRoll());
/*  87 */           addToList(cardsList, (AbstractCard)new EnStrikeGreen());
/*  88 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/*  93 */           addToList(cardsList, (AbstractCard)new EnFootwork(), extraUpgrades);
/*  94 */           addToList(cardsList, (AbstractCard)new EnNoxiousFumes());
/*  95 */           addToList(cardsList, (AbstractCard)new EnPoisonedStab());
/*  96 */           this.turn++;
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 101 */           addToList(cardsList, (AbstractCard)new EnBane());
/* 102 */           addToList(cardsList, (AbstractCard)new EnStrikeGreen());
/* 103 */           addToList(cardsList, (AbstractCard)new EnDeflect());
/* 104 */           this.turn = 0;
/* 105 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */     
/*     */     } else {
/* 110 */       switch (this.turn) {
/*     */         case 0:
/* 112 */           addToList(cardsList, (AbstractCard)new EnDodgeAndRoll());
/* 113 */           addToList(cardsList, (AbstractCard)new EnPoisonedStab());
/* 114 */           addToList(cardsList, (AbstractCard)new EnSurvivor());
/* 115 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 118 */           addToList(cardsList, (AbstractCard)new EnBurst());
/* 119 */           addToList(cardsList, (AbstractCard)new EnDeflect());
/* 120 */           addToList(cardsList, (AbstractCard)new EnDeflect());
/* 121 */           addToList(cardsList, (AbstractCard)new EnPoisonedStab(), true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 127 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 130 */           addToList(cardsList, (AbstractCard)new EnBane());
/* 131 */           addToList(cardsList, (AbstractCard)new EnStrikeGreen());
/* 132 */           addToList(cardsList, (AbstractCard)new EnDefendGreen());
/* 133 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     } 
/* 137 */     return cardsList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 143 */     addRelic((AbstractCharbossRelic)new CBR_SneckoSkull());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Silent\NewAge\ArchetypeAct1PoisonNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */