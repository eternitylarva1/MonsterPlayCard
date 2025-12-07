/*     */ package charbosses.bosses.Defect;
/*     */ 
/*     */ import charbosses.bosses.AbstractBossDeckArchetype;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Defect.NewAge.ArchetypeAct1TurboNewAge;
/*     */ import charbosses.bosses.Defect.NewAge.ArchetypeAct2ClawNewAge;
/*     */ import charbosses.bosses.Defect.NewAge.ArchetypeAct3OrbsNewAge;
/*     */ import charbosses.core.EnemyEnergyManager;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.common.InstantKillAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbBlue;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
/*     */ import downfall.downfallMod;
/*     */ import downfall.monsters.NeowBoss;
/*     */ import downfall.util.LocalizeHelper;
/*     */ 
/*     */ public class CharBossDefect
/*     */   extends AbstractCharBoss {
/*  26 */   public static final String ID = downfallMod.makeID("Defect");
/*  27 */   public static final String NAME = LocalizeHelper.DonwfallRunHistoryMonsterNames.TEXT[3];
/*     */   
/*  29 */   public int clawsPlayed = 0;
/*     */   
/*     */   public CharBossDefect() {
/*  32 */     super(NAME, ID, 75, 0.0F, -5.0F, 240.0F, 244.0F, null, 0.0F, -20.0F, AbstractPlayer.PlayerClass.DEFECT);
/*  33 */     this.energyOrb = (EnergyOrbInterface)new EnergyOrbBlue();
/*  34 */     this.energy = new EnemyEnergyManager(3);
/*  35 */     loadAnimation("images/characters/defect/idle/skeleton.atlas", "images/characters/defect/idle/skeleton.json", 1.0F);
/*  36 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  37 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  38 */     this.flipHorizontal = true;
/*     */     
/*  40 */     this.dialogX = this.drawX + 0.0F * Settings.scale;
/*  41 */     this.dialogY = this.drawY + 170.0F * Settings.scale;
/*  42 */     e.setTimeScale(0.9F);
/*  43 */     this.energyString = "[B]";
/*     */     
/*  45 */     this.masterMaxOrbs = 3;
/*  46 */     this.maxOrbs = 3;
/*  47 */     this.type = AbstractMonster.EnemyType.BOSS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateDeck() {
/*     */     ArchetypeAct1TurboNewAge archetypeAct1TurboNewAge;
/*  55 */     if (downfallMod.overrideBossDifficulty) {
/*  56 */       archetypeAct1TurboNewAge = new ArchetypeAct1TurboNewAge();
/*  57 */       this.currentHealth -= 100;
/*  58 */       downfallMod.overrideBossDifficulty = false;
/*     */     } else {
/*  60 */       ArchetypeAct1TurboNewAge archetypeAct1TurboNewAge2; ArchetypeAct2ClawNewAge archetypeAct2ClawNewAge2; ArchetypeAct3OrbsNewAge archetypeAct3OrbsNewAge3; ArchetypeAct1TurboNewAge archetypeAct1TurboNewAge1; ArchetypeAct2ClawNewAge archetypeAct2ClawNewAge1; ArchetypeAct3OrbsNewAge archetypeAct3OrbsNewAge2; ArchetypeAct3OrbsNewAge archetypeAct3OrbsNewAge1; switch (AbstractDungeon.actNum) {
/*     */         case 1:
/*  62 */           archetypeAct1TurboNewAge2 = new ArchetypeAct1TurboNewAge();
/*     */           break;
/*     */         case 2:
/*  65 */           archetypeAct2ClawNewAge2 = new ArchetypeAct2ClawNewAge();
/*     */           break;
/*     */         case 3:
/*  68 */           archetypeAct3OrbsNewAge3 = new ArchetypeAct3OrbsNewAge();
/*     */           break;
/*     */         
/*     */         case 4:
/*  72 */           switch (NeowBoss.Rezzes) {
/*     */             
/*     */             case 0:
/*  75 */               archetypeAct1TurboNewAge1 = new ArchetypeAct1TurboNewAge();
/*     */               break;
/*     */             case 1:
/*  78 */               archetypeAct2ClawNewAge1 = new ArchetypeAct2ClawNewAge();
/*     */               break;
/*     */             case 2:
/*  81 */               archetypeAct3OrbsNewAge2 = new ArchetypeAct3OrbsNewAge();
/*     */               break;
/*     */           } 
/*  84 */           archetypeAct3OrbsNewAge1 = new ArchetypeAct3OrbsNewAge();
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/*  90 */           archetypeAct1TurboNewAge = new ArchetypeAct1TurboNewAge();
/*     */           break;
/*     */       } 
/*     */     } 
/*  94 */     archetypeAct1TurboNewAge.initialize();
/*  95 */     this.chosenArchetype = (AbstractBossDeckArchetype)archetypeAct1TurboNewAge;
/*  96 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  97 */       archetypeAct1TurboNewAge.initializeBonusRelic();
/*     */     }
/*     */   }
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 102 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
/* 103 */       AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
/* 104 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 105 */       e.setTimeScale(0.6F);
/*     */     } 
/*     */     
/* 108 */     super.damage(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 113 */     super.die();
/*     */     
/* 115 */     if (hasPower("Minion"))
/* 116 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 117 */         if (m instanceof charbosses.monsters.BronzeOrbWhoReallyLikesDefectForSomeReason)
/* 118 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new InstantKillAction((AbstractCreature)m)); 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Defect\CharBossDefect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */