/*     */ package charbosses.bosses.Hermit.NewAge;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Ironclad.ArchetypeBaseIronclad;
/*     */ import charbosses.cards.curses.EnDoubt;
/*     */ import charbosses.cards.curses.EnInjury;
/*     */ import charbosses.cards.hermit.EnGlare;
/*     */ import charbosses.cards.hermit.EnGrudge;
/*     */ import charbosses.cards.hermit.EnMalice;
/*     */ import charbosses.cards.hermit.EnPurgatory;
/*     */ import charbosses.cards.hermit.EnShadowCloak;
/*     */ import charbosses.cards.hermit.EnSprayNPray;
/*     */ import charbosses.powers.bossmechanicpowers.HermitDoomsday;
/*     */ import charbosses.relics.AbstractCharbossRelic;
/*     */ import charbosses.relics.CBR_CursedKey;
/*     */ import charbosses.relics.CBR_FrozenEgg;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.TalkAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.monsters.city.Byrd;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.RitualPower;
/*     */ import downfall.vfx.PotionThrowEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class ArchetypeAct3DoomsdayNewAge extends ArchetypeBaseIronclad {
/*     */   public ArchetypeAct3DoomsdayNewAge() {
/*  33 */     super("HERMIT_DOOMSDAY_ARCHETYPE", "Doomsday");
/*     */     
/*  35 */     this.maxHPModifier += 363;
/*  36 */     this.actNum = 3;
/*  37 */     this.bossMechanicName = HermitDoomsday.NAME;
/*  38 */     this.bossMechanicDesc = HermitDoomsday.DESC[0];
/*     */   }
/*     */   public boolean froegg = false;
/*     */   public static AbstractMonster getDoomedSnake() {
/*  42 */     return (AbstractMonster)new DoomedDagger(-400.0F, 200.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addedPreBattle() {
/*  47 */     super.addedPreBattle();
/*  48 */     AbstractCharBoss abstractCharBoss = AbstractCharBoss.boss;
/*  49 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(getDoomedSnake(), true));
/*  50 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new HermitDoomsday((AbstractCreature)abstractCharBoss), 1));
/*     */ 
/*     */     
/*  53 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new PotionThrowEffect("downfallResources/images/vfx/CultistPotion.png", ((AbstractCreature)abstractCharBoss).hb.cX - 240.0F, ((AbstractCreature)abstractCharBoss).hb.cY - 150.0F, ((AbstractCreature)abstractCharBoss).hb.cX - 240.0F, ((AbstractCreature)abstractCharBoss).hb.cY - 150.0F, 2.0F, 0.6F, false, true), 0.6F));
/*  54 */     int roll = MathUtils.random(2);
/*  55 */     if (roll == 0) {
/*  56 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CULTIST_1A"));
/*  57 */     } else if (roll == 1) {
/*  58 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CULTIST_1B"));
/*     */     } else {
/*  60 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_CULTIST_1C"));
/*     */     } 
/*     */     
/*  63 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)abstractCharBoss, Byrd.DIALOG[0], 1.2F, 1.2F));
/*     */     
/*  65 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractCharBoss, (AbstractCreature)abstractCharBoss, (AbstractPower)new RitualPower((AbstractCreature)abstractCharBoss, 1, false), 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize() {
/*  72 */     addRelic((AbstractCharbossRelic)new CBR_NeowsBlessing());
/*  73 */     addRelic((AbstractCharbossRelic)new CBR_CursedKey());
/*  74 */     addRelic((AbstractCharbossRelic)new CBR_CharredGlove());
/*  75 */     addRelic((AbstractCharbossRelic)new CBR_BrassTacks());
/*  76 */     addRelic((AbstractCharbossRelic)new CBR_Omamori());
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<AbstractCard> getThisTurnCards() {
/*  81 */     ArrayList<AbstractCard> cardsList = new ArrayList<>();
/*  82 */     boolean extraUpgrades = (AbstractDungeon.ascensionLevel >= 4);
/*     */ 
/*     */     
/*  85 */     if (!this.looped) {
/*  86 */       switch (this.turn) {
/*     */         case 0:
/*  88 */           if (AbstractDungeon.ascensionLevel >= 19) {
/*  89 */             addToList(cardsList, (AbstractCard)new EnShadowCloak(), extraUpgrades);
/*     */           }
/*  91 */           if (AbstractDungeon.ascensionLevel < 19) {
/*  92 */             addToList(cardsList, (AbstractCard)new EnShadowCloak());
/*     */           }
/*  94 */           addToList(cardsList, (AbstractCard)new EnGrudge(15));
/*  95 */           addToList(cardsList, (AbstractCard)new EnGlare());
/*  96 */           this.turn++;
/*     */           break;
/*     */         case 1:
/*  99 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 100 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 101 */           addToList(cardsList, (AbstractCard)new EnDoubt());
/* 102 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 105 */           addToList(cardsList, (AbstractCard)new EnPurgatory(), extraUpgrades);
/* 106 */           addToList(cardsList, (AbstractCard)new EnMalice());
/* 107 */           addToList(cardsList, (AbstractCard)new EnSprayNPray());
/* 108 */           this.turn = 0;
/* 109 */           this.looped = true; break;
/*     */       } 
/*     */     } else {
/*     */       EnMalice mal; EnDoubt enDoubt;
/* 113 */       switch (this.turn) {
/*     */         case 0:
/* 115 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 116 */           addToList(cardsList, (AbstractCard)new EnGrudge(17));
/* 117 */           addToList(cardsList, (AbstractCard)new EnSprayNPray());
/* 118 */           this.turn++;
/*     */           break;
/*     */         case 1:
/* 121 */           mal = new EnMalice();
/* 122 */           addToList(cardsList, (AbstractCard)mal);
/* 123 */           addToList(cardsList, (AbstractCard)new EnGlare());
/* 124 */           enDoubt = new EnDoubt();
/* 125 */           mal.setExhaust((AbstractCard)enDoubt);
/* 126 */           addToList(cardsList, (AbstractCard)enDoubt);
/* 127 */           this.turn++;
/*     */           break;
/*     */         case 2:
/* 130 */           addToList(cardsList, (AbstractCard)new EnPurgatory());
/* 131 */           addToList(cardsList, (AbstractCard)new EnDoubt());
/* 132 */           addToList(cardsList, (AbstractCard)new EnInjury());
/* 133 */           this.turn = 0;
/*     */           break;
/*     */       } 
/*     */     } 
/* 137 */     return cardsList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void initializeBonusRelic() {
/* 142 */     addRelic((AbstractCharbossRelic)new CBR_FrozenEgg());
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Hermit\NewAge\ArchetypeAct3DoomsdayNewAge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */