/*     */ package charbosses.bosses.Watcher;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Watcher.NewAge.ArchetypeAct1RetainNewAge;
/*     */ import charbosses.bosses.Watcher.NewAge.ArchetypeAct2CalmNewAge;
/*     */ import charbosses.bosses.Watcher.NewAge.ArchetypeAct3DivinityNewAge;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.esotericsoftware.spine.AnimationStateData;
/*     */ import com.esotericsoftware.spine.Bone;
/*     */ import com.esotericsoftware.spine.Skeleton;
/*     */ import com.esotericsoftware.spine.SkeletonData;
/*     */ import com.esotericsoftware.spine.SkeletonJson;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbPurple;
/*     */ import downfall.downfallMod;
/*     */ import downfall.util.LocalizeHelper;
/*     */ 
/*     */ public class CharBossWatcher extends AbstractCharBoss {
/*  27 */   public static final String ID = downfallMod.makeID("Watcher");
/*  28 */   public static final String NAME = LocalizeHelper.DonwfallRunHistoryMonsterNames.TEXT[4];
/*     */   
/*     */   private Bone eyeBone;
/*  31 */   protected TextureAtlas eyeAtlas = null;
/*     */   
/*     */   protected Skeleton eyeSkeleton;
/*     */   public AnimationState eyeState;
/*     */   protected AnimationStateData eyeStateData;
/*     */   
/*     */   public CharBossWatcher() {
/*  38 */     super(NAME, ID, 72, 0.0F, -5.0F, 240.0F, 270.0F, null, 0.0F, -20.0F, AbstractPlayer.PlayerClass.WATCHER);
/*  39 */     this.energyOrb = (EnergyOrbInterface)new EnergyOrbPurple();
/*  40 */     this.energy = new EnemyEnergyManager(3);
/*  41 */     loadAnimation("images/characters/watcher/idle/skeleton.atlas", "images/characters/watcher/idle/skeleton.json", 1.0F);
/*  42 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  43 */     this.flipHorizontal = true;
/*  44 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  45 */     e.setTimeScale(0.7F);
/*  46 */     this.energyString = "[P]";
/*  47 */     this.type = AbstractMonster.EnemyType.BOSS;
/*     */     
/*  49 */     loadEyeAnimation();
/*  50 */     this.eyeBone = this.skeleton.findBone("eye_anchor");
/*     */   }
/*     */   
/*     */   private void loadEyeAnimation() {
/*  54 */     this.eyeAtlas = new TextureAtlas(Gdx.files.internal("images/characters/watcher/eye_anim/skeleton.atlas"));
/*  55 */     SkeletonJson json = new SkeletonJson(this.eyeAtlas);
/*  56 */     json.setScale(Settings.scale / 1.0F);
/*  57 */     SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("images/characters/watcher/eye_anim/skeleton.json"));
/*  58 */     this.eyeSkeleton = new Skeleton(skeletonData);
/*  59 */     this.eyeSkeleton.setColor(Color.WHITE);
/*  60 */     this.eyeStateData = new AnimationStateData(skeletonData);
/*  61 */     this.eyeState = new AnimationState(this.eyeStateData);
/*  62 */     this.eyeStateData.setDefaultMix(0.2F);
/*  63 */     this.eyeState.setAnimation(0, "None", true);
/*     */   }
/*     */   
/*     */   public void onStanceChange(String newStance) {
/*  67 */     if (newStance.equals("Calm")) {
/*  68 */       this.eyeState.setAnimation(0, "Calm", true);
/*  69 */     } else if (newStance.equals("Wrath")) {
/*  70 */       this.eyeState.setAnimation(0, "Wrath", true);
/*  71 */     } else if (newStance.equals("Divinity")) {
/*  72 */       this.eyeState.setAnimation(0, "Divinity", true);
/*  73 */     } else if (newStance.equals("Neutral")) {
/*  74 */       this.eyeState.setAnimation(0, "None", true);
/*     */     } else {
/*  76 */       this.eyeState.setAnimation(0, "None", true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  82 */     super.render(sb);
/*  83 */     this.eyeState.update(Gdx.graphics.getDeltaTime());
/*  84 */     this.eyeState.apply(this.eyeSkeleton);
/*  85 */     this.eyeSkeleton.updateWorldTransform();
/*  86 */     this.eyeSkeleton.setPosition(this.skeleton.getX() + this.eyeBone.getWorldX(), this.skeleton.getY() + this.eyeBone.getWorldY());
/*  87 */     this.eyeSkeleton.setColor(this.tint.color);
/*  88 */     this.eyeSkeleton.setFlip(this.flipHorizontal, this.flipVertical);
/*     */     
/*  90 */     sb.end();
/*  91 */     CardCrawlGame.psb.begin();
/*  92 */     sr.draw(CardCrawlGame.psb, this.eyeSkeleton);
/*  93 */     CardCrawlGame.psb.end();
/*  94 */     sb.begin();
/*     */   }
/*     */ 
/*     */   
/*     */   public void generateDeck() {
/*     */     ArchetypeAct1RetainNewAge archetypeAct1RetainNewAge;
/* 100 */     if (downfallMod.overrideBossDifficulty) {
/* 101 */       archetypeAct1RetainNewAge = new ArchetypeAct1RetainNewAge();
/* 102 */       downfallMod.overrideBossDifficulty = false;
/* 103 */       this.currentHealth -= 100;
/*     */     } else {
/* 105 */       ArchetypeAct1RetainNewAge archetypeAct1RetainNewAge3; ArchetypeAct2CalmNewAge archetypeAct2CalmNewAge2; ArchetypeAct3DivinityNewAge archetypeAct3DivinityNewAge2; ArchetypeAct1RetainNewAge archetypeAct1RetainNewAge2; ArchetypeAct2CalmNewAge archetypeAct2CalmNewAge1; ArchetypeAct3DivinityNewAge archetypeAct3DivinityNewAge1; ArchetypeAct1RetainNewAge archetypeAct1RetainNewAge1; switch (AbstractDungeon.actNum) {
/*     */         case 1:
/* 107 */           archetypeAct1RetainNewAge3 = new ArchetypeAct1RetainNewAge();
/*     */           break;
/*     */         case 2:
/* 110 */           archetypeAct2CalmNewAge2 = new ArchetypeAct2CalmNewAge();
/*     */           break;
/*     */         case 3:
/* 113 */           archetypeAct3DivinityNewAge2 = new ArchetypeAct3DivinityNewAge();
/*     */           break;
/*     */         case 4:
/* 116 */           switch (NeowBoss.Rezzes) {
/*     */             case 0:
/* 118 */               archetypeAct1RetainNewAge2 = new ArchetypeAct1RetainNewAge();
/*     */               break;
/*     */             case 1:
/* 121 */               archetypeAct2CalmNewAge1 = new ArchetypeAct2CalmNewAge();
/*     */               break;
/*     */             case 2:
/* 124 */               archetypeAct3DivinityNewAge1 = new ArchetypeAct3DivinityNewAge();
/*     */               break;
/*     */           } 
/* 127 */           archetypeAct1RetainNewAge1 = new ArchetypeAct1RetainNewAge();
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 133 */           archetypeAct1RetainNewAge = new ArchetypeAct1RetainNewAge();
/*     */           break;
/*     */       } 
/*     */     } 
/* 137 */     archetypeAct1RetainNewAge.initialize();
/* 138 */     this.chosenArchetype = (AbstractBossDeckArchetype)archetypeAct1RetainNewAge;
/* 139 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 140 */       archetypeAct1RetainNewAge.initializeBonusRelic();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayAttackCardSound() {
/* 148 */     switch (MathUtils.random(1)) {
/*     */       case 0:
/* 150 */         CardCrawlGame.sound.play("VO_GREMLINCALM_1A");
/*     */         break;
/*     */       case 1:
/* 153 */         CardCrawlGame.sound.play("VO_GREMLINCALM_1B");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 161 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
/* 162 */       AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
/* 163 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 164 */       e.setTimeScale(0.6F);
/*     */     } 
/*     */     
/* 167 */     super.damage(info);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void die() {
/* 174 */     super.die();
/*     */     
/* 176 */     switch (MathUtils.random(1)) {
/*     */       case 0:
/* 178 */         CardCrawlGame.sound.play("VO_GREMLINCALM_2A");
/*     */         break;
/*     */       case 1:
/* 181 */         CardCrawlGame.sound.play("VO_GREMLINCALM_2B");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Watcher\CharBossWatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */