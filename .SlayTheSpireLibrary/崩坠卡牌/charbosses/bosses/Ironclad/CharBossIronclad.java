/*     */ package charbosses.bosses.Ironclad;
/*     */ 
/*     */ import champ.ChampMod;
/*     */ import charbosses.bosses.AbstractBossDeckArchetype;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Ironclad.NewAge.ArchetypeAct1StatusesNewAge;
/*     */ import charbosses.bosses.Ironclad.NewAge.ArchetypeAct2MushroomsNewAge;
/*     */ import charbosses.bosses.Ironclad.NewAge.ArchetypeAct3BlockNewAge;
/*     */ import charbosses.core.EnemyEnergyManager;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.ShoutAction;
/*     */ import com.megacrit.cardcrawl.actions.common.InstantKillAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbRed;
/*     */ import downfall.downfallMod;
/*     */ import downfall.monsters.NeowBoss;
/*     */ import downfall.util.LocalizeHelper;
/*     */ import hermit.util.TextureLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharBossIronclad
/*     */   extends AbstractCharBoss
/*     */ {
/*  44 */   public static final String ID = downfallMod.makeID("Ironclad");
/*  45 */   public static final String NAME = LocalizeHelper.DonwfallRunHistoryMonsterNames.TEXT[1];
/*     */   
/*  47 */   private Texture fgImg = TextureLoader.getTexture("downfallResources/images/fgShrooms.png");
/*  48 */   private Texture bgImg = TextureLoader.getTexture("downfallResources/images/bgShrooms.png");
/*     */   
/*     */   public CharBossIronclad() {
/*  51 */     super(NAME, ID, 80, -4.0F, -16.0F, 220.0F, 290.0F, null, 0.0F, -20.0F, AbstractPlayer.PlayerClass.IRONCLAD);
/*  52 */     this.energyOrb = (EnergyOrbInterface)new EnergyOrbRed();
/*  53 */     this.energy = new EnemyEnergyManager(3);
/*  54 */     loadAnimation("images/characters/ironclad/idle/skeleton.atlas", "images/characters/ironclad/idle/skeleton.json", 1.0F);
/*  55 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  56 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  57 */     this.flipHorizontal = true;
/*  58 */     e.setTimeScale(0.6F);
/*  59 */     this.energyString = "[R]";
/*  60 */     this.type = AbstractMonster.EnemyType.BOSS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateDeck() {
/*     */     ArchetypeAct1StatusesNewAge archetypeAct1StatusesNewAge;
/*  67 */     if (downfallMod.overrideBossDifficulty) {
/*  68 */       archetypeAct1StatusesNewAge = new ArchetypeAct1StatusesNewAge();
/*  69 */       downfallMod.overrideBossDifficulty = false;
/*  70 */       this.currentHealth -= 100;
/*     */     } else {
/*  72 */       ArchetypeAct1StatusesNewAge archetypeAct1StatusesNewAge3; ArchetypeAct2MushroomsNewAge archetypeAct2MushroomsNewAge2; ArchetypeAct3BlockNewAge archetypeAct3BlockNewAge2; ArchetypeAct1StatusesNewAge archetypeAct1StatusesNewAge2; ArchetypeAct2MushroomsNewAge archetypeAct2MushroomsNewAge1; ArchetypeAct3BlockNewAge archetypeAct3BlockNewAge1; ArchetypeAct1StatusesNewAge archetypeAct1StatusesNewAge1; switch (AbstractDungeon.actNum) {
/*     */         case 1:
/*  74 */           archetypeAct1StatusesNewAge3 = new ArchetypeAct1StatusesNewAge();
/*     */           break;
/*     */         case 2:
/*  77 */           archetypeAct2MushroomsNewAge2 = new ArchetypeAct2MushroomsNewAge();
/*     */           break;
/*     */         case 3:
/*  80 */           archetypeAct3BlockNewAge2 = new ArchetypeAct3BlockNewAge();
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/*  85 */           switch (NeowBoss.Rezzes) {
/*     */             case 0:
/*  87 */               archetypeAct1StatusesNewAge2 = new ArchetypeAct1StatusesNewAge();
/*     */               break;
/*     */             case 1:
/*  90 */               archetypeAct2MushroomsNewAge1 = new ArchetypeAct2MushroomsNewAge();
/*     */               break;
/*     */             case 2:
/*  93 */               archetypeAct3BlockNewAge1 = new ArchetypeAct3BlockNewAge();
/*     */               break;
/*     */           } 
/*  96 */           archetypeAct1StatusesNewAge1 = new ArchetypeAct1StatusesNewAge();
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 102 */           archetypeAct1StatusesNewAge = new ArchetypeAct1StatusesNewAge();
/*     */           break;
/*     */       } 
/*     */     } 
/* 106 */     archetypeAct1StatusesNewAge.initialize();
/* 107 */     this.chosenArchetype = (AbstractBossDeckArchetype)archetypeAct1StatusesNewAge;
/* 108 */     if (AbstractDungeon.ascensionLevel >= 19) {
/* 109 */       archetypeAct1StatusesNewAge.initializeBonusRelic();
/*     */     }
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
/*     */   public void loseBlock(int amount) {
/* 124 */     super.loseBlock(amount);
/* 125 */     for (AbstractCard c : this.hand.group) {
/* 126 */       if (c instanceof charbosses.cards.red.EnBodySlam) {
/* 127 */         c.applyPowers();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void takeTurn() {
/* 134 */     super.takeTurn();
/* 135 */     String[] DESCRIPTIONS = (CardCrawlGame.languagePack.getEventString("champ:ChampTalk")).DESCRIPTIONS;
/* 136 */     if (AbstractDungeon.player instanceof champ.ChampChar && AbstractDungeon.actNum == 1) {
/* 137 */       if (!ChampMod.talked1 && !ChampMod.talked2) {
/* 138 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DESCRIPTIONS[0], 1.0F, 2.0F));
/* 139 */         ChampMod.talked1 = true;
/* 140 */       } else if (!ChampMod.talked2) {
/* 141 */         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DESCRIPTIONS[1], 1.0F, 2.0F));
/* 142 */         ChampMod.talked2 = true;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayAttackCardSound() {
/* 150 */     switch (MathUtils.random(2)) {
/*     */       case 0:
/* 152 */         CardCrawlGame.sound.play("VO_IRONCLAD_1A");
/*     */         break;
/*     */       case 1:
/* 155 */         CardCrawlGame.sound.play("VO_IRONCLAD_1B");
/*     */         break;
/*     */       case 2:
/* 158 */         CardCrawlGame.sound.play("VO_IRONCLAD_1C");
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 164 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
/* 165 */       AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
/* 166 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 167 */       e.setTimeScale(0.6F);
/*     */     } 
/*     */     
/* 170 */     super.damage(info);
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 175 */     super.die();
/*     */     
/* 177 */     switch (MathUtils.random(2)) {
/*     */       case 0:
/* 179 */         CardCrawlGame.sound.play("VO_IRONCLAD_2A");
/*     */         break;
/*     */       case 1:
/* 182 */         CardCrawlGame.sound.play("VO_IRONCLAD_2B");
/*     */         break;
/*     */       case 2:
/* 185 */         CardCrawlGame.sound.play("VO_IRONCLAD_2C");
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 190 */     if (hasPower("Minion")) {
/* 191 */       for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 192 */         if (m instanceof charbosses.monsters.Fortification || m instanceof charbosses.monsters.MushroomPurple || m instanceof charbosses.monsters.MushroomRed || m instanceof charbosses.monsters.MushroomWhite) {
/* 193 */           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new InstantKillAction((AbstractCreature)m));
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 201 */     if (this.chosenArchetype instanceof ArchetypeAct2MushroomsNewAge && NeowBoss.neowboss == null) {
/* 202 */       sb.setColor(Color.WHITE.cpy());
/* 203 */       sb.draw(this.bgImg, 0.0F, -10.0F, Settings.WIDTH, 1080.0F * Settings.scale);
/* 204 */       sb.draw(this.fgImg, 0.0F, -20.0F * Settings.scale, Settings.WIDTH, 1080.0F * Settings.scale);
/*     */     } 
/* 206 */     super.render(sb);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Ironclad\CharBossIronclad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */