/*     */ package charbosses.bosses.Silent.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Silent.CharBossSilent;
/*     */ import charbosses.cards.colorless.EnJAX;
/*     */ import charbosses.cards.curses.EnDecay;
/*     */ import charbosses.cards.green.EnAfterImage;
/*     */ import charbosses.cards.green.EnBackstab;
/*     */ import charbosses.cards.green.EnBlur;
/*     */ import charbosses.cards.green.EnDaggerSpray;
/*     */ import charbosses.cards.green.EnDeflect;
/*     */ import charbosses.cards.green.EnDoppleganger;
/*     */ import charbosses.cards.green.EnFlyingKnee;
/*     */ import charbosses.cards.green.EnLegSweep;
/*     */ import charbosses.cards.green.EnNightmare;
/*     */ import charbosses.cards.green.EnOutmaneuver;
/*     */ import charbosses.cards.green.EnRiddleWithHoles;
/*     */ import charbosses.powers.bossmechanicpowers.SilentMirrorImagePower;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import charbosses.relics.CBR_Anchor;
/*     */ import charbosses.relics.CBR_BagOfMarbles;
/*     */ import charbosses.relics.CBR_OddlySmoothStone;
/*     */ import charbosses.relics.CBR_PaperKrane;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct2MirrorImageNewAge extends ArchetypeBaseSilent {
/*     */   public ArchetypeAct2MirrorImageNewAge() {
/*  30 */     super("SI_MIRROR_ARCHETYPE", "Mirror");
/*     */     
/*  32 */     this.maxHPModifier += 240;
/*  33 */     this.actNum = 2;
/*  34 */     this.bossMechanicName = SilentMirrorImagePower.NAME;
/*  35 */     this.bossMechanicDesc = SilentMirrorImagePower.DESC[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  40 */     super.addedPreBattle();
/*  41 */     CharBossSilent p = (CharBossSilent)AbstractCharBoss.boss;
/*  42 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new SilentMirrorImagePower((AbstractCreature)p)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  51 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*     */     
/*  53 */     addRelic((AbstractCharbossRelic)new CBR_BagOfMarbles());
/*  54 */     addRelic((AbstractCharbossRelic)new CBR_Anchor());
/*  55 */     addRelic((AbstractCharbossRelic)new CBR_PaperKrane());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  65 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  66 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*  67 */     if (!this.looped) {
/*  68 */       switch (this.turn) {
/*     */         
/*     */         case 0:
/*  71 */           addToList(cardsList, (AbstractCard)new EnAfterImage(), true);
/*  72 */           addToList(cardsList, (AbstractCard)new EnBackstab());
/*  73 */           addToList(cardsList, (AbstractCard)new EnDoppleganger(), true);
/*  74 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 1:
/*  78 */           addToList(cardsList, (AbstractCard)new EnNightmare(), false);
/*  79 */           addToList(cardsList, (AbstractCard)new EnDeflect(), extraUpgrades);
/*  80 */           addToList(cardsList, (AbstractCard)new EnJAX(), true);
/*  81 */           addToList(cardsList, (AbstractCard)new EnDaggerSpray());
/*  82 */           addToList(cardsList, (AbstractCard)new EnDecay());
/*  83 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 2:
/*  87 */           addToList(cardsList, (AbstractCard)new EnDeflect(), extraUpgrades);
/*  88 */           addToList(cardsList, (AbstractCard)new EnDeflect(), extraUpgrades);
/*  89 */           addToList(cardsList, (AbstractCard)new EnDeflect(), extraUpgrades);
/*  90 */           addToList(cardsList, (AbstractCard)new EnOutmaneuver());
/*  91 */           addToList(cardsList, (AbstractCard)new EnFlyingKnee());
/*  92 */           addToList(cardsList, (AbstractCard)new EnBlur());
/*  93 */           this.turn++;
/*     */           break;
/*     */         
/*     */         case 3:
/*  97 */           addToList(cardsList, (AbstractCard)new EnFootwork());
/*  98 */           addToList(cardsList, (AbstractCard)new EnLegSweep());
/*  99 */           addToList(cardsList, (AbstractCard)new EnRiddleWithHoles());
/* 100 */           this.turn = 0;
/* 101 */           this.looped = true;
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/*     */     } else {
/* 107 */       switch (this.turn) {
/*     */         case 0:
/* 109 */           addToList(cardsList, (AbstractCard)new EnJAX(), true);
/* 110 */           addToList(cardsList, (AbstractCard)new EnDeflect(), extraUpgrades);
/* 111 */           addToList(cardsList, (AbstractCard)new EnDecay());
/* 112 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 115 */           addToList(cardsList, (AbstractCard)new EnDeflect(), extraUpgrades);
/* 116 */           addToList(cardsList, (AbstractCard)new EnDeflect(), extraUpgrades);
/* 117 */           addToList(cardsList, (AbstractCard)new EnDaggerSpray());
/* 118 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 121 */           addToList(cardsList, (AbstractCard)new EnOutmaneuver());
/* 122 */           addToList(cardsList, (AbstractCard)new EnFlyingKnee());
/* 123 */           addToList(cardsList, (AbstractCard)new EnDeflect(), extraUpgrades);
/* 124 */           this.turn++;
/*     */           break;
/*     */         case 3:
/* 127 */           addToList(cardsList, (AbstractCard)new EnLegSweep());
/* 128 */           addToList(cardsList, (AbstractCard)new EnRiddleWithHoles());
/* 129 */           addToList(cardsList, (AbstractCard)new EnBlur());
/* 130 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     } 
/* 134 */     return cardsList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 140 */     addRelic((AbstractCharbossRelic)new CBR_OddlySmoothStone());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Silent\NewAge\ArchetypeAct2MirrorImageNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */