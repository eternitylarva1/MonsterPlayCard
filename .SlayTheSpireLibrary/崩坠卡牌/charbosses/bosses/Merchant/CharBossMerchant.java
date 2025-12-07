/*     */ package charbosses.bosses.Merchant;
/*     */ import charbosses.bosses.AbstractBossDeckArchetype;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.core.EnemyEnergyManager;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.esotericsoftware.spine.AnimationStateData;
/*     */ import com.esotericsoftware.spine.Skeleton;
/*     */ import com.esotericsoftware.spine.SkeletonData;
/*     */ import com.esotericsoftware.spine.SkeletonJson;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.powers.DexterityPower;
/*     */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
/*     */ import com.megacrit.cardcrawl.vfx.BobEffect;
/*     */ import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
/*     */ import downfall.downfallMod;
/*     */ import downfall.monsters.FleeingMerchant;
/*     */ import downfall.util.LocalizeHelper;
/*     */ import downfall.vfx.NeowBossRezEffect;
/*     */ 
/*     */ public class CharBossMerchant extends AbstractCharBoss {
/*  37 */   private BobEffect bob = new BobEffect();
/*  38 */   private Color tempColor = new Color();
/*  39 */   private Color glowColor = new Color(2.0F, 2.0F, 2.0F, 2.0F);
/*     */   
/*     */   private TextureAtlas atlasGlow;
/*     */   
/*     */   private Skeleton skeletonGlow;
/*     */   private AnimationState stateGlow;
/*     */   private AnimationStateData stateDataGlow;
/*  46 */   private float curveAlpha = 1.0F;
/*     */   private boolean curveUp = false;
/*  48 */   private float curveDuration = 1.0F;
/*     */   
/*     */   private NeowBossRezEffect rezVFX;
/*     */   
/*     */   private float rezTimer;
/*     */   
/*     */   private boolean startVFX = false;
/*     */   private boolean neowSpoke = false;
/*  56 */   public static final String ID = downfallMod.makeID("CharBossMerchant");
/*  57 */   public static final String NAME = LocalizeHelper.DonwfallRunHistoryMonsterNames.TEXT[5];
/*     */   
/*     */   public CharBossMerchant() {
/*  60 */     super(NAME, ID, 300, 0.0F, -40.0F, 200.0F, 190.0F, null, 0.0F, 0.0F, AbstractPlayer.PlayerClass.IRONCLAD);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  68 */     this.energyOrb = (EnergyOrbInterface)new EnergyOrbRed();
/*  69 */     this.energy = new EnemyEnergyManager(3);
/*     */     
/*  71 */     this.drawX = 1260.0F * Settings.scale;
/*  72 */     this.drawY = 460.0F * Settings.scale;
/*     */     
/*  74 */     loadAnimation(downfallMod.assetPath("images/monsters/merchant/noShadow/skeleton.atlas"), downfallMod.assetPath("images/monsters/merchant/noShadow/skeleton.json"), 1.0F);
/*  75 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
/*     */     
/*  77 */     float time = e.getEndTime() * MathUtils.random();
/*  78 */     e.setTime(time);
/*  79 */     e.setTimeScale(1.0F);
/*     */     
/*  81 */     this.energyString = "[R]";
/*     */     
/*  83 */     initGlowMesh(time);
/*     */     
/*  85 */     this.tint.color = new Color(0.5F, 0.5F, 1.0F, 0.0F);
/*     */     
/*  87 */     this.type = AbstractMonster.EnemyType.BOSS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void usePreBattleAction() {
/*  93 */     for (int i = 0; i < 10; i++) {
/*  94 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new WaitAction(0.1F));
/*     */     }
/*     */     
/*  97 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.CYAN, true), 2.0F));
/*     */     
/*  99 */     if (FleeingMerchant.CURRENT_STRENGTH > 0) {
/* 100 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, FleeingMerchant.CURRENT_STRENGTH), FleeingMerchant.CURRENT_STRENGTH));
/* 101 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new DexterityPower((AbstractCreature)this, FleeingMerchant.CURRENT_STRENGTH), FleeingMerchant.CURRENT_STRENGTH));
/* 102 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, FleeingMerchant.CURRENT_STRENGTH), FleeingMerchant.CURRENT_STRENGTH));
/* 103 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new DexterityPower((AbstractCreature)this, FleeingMerchant.CURRENT_STRENGTH), FleeingMerchant.CURRENT_STRENGTH));
/*     */     } 
/*     */     
/* 106 */     super.usePreBattleAction();
/* 107 */     this.tint.color = new Color(0.5F, 0.5F, 1.0F, 0.0F);
/* 108 */     this.rezTimer = 5.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void initGlowMesh(float time) {
/* 114 */     float glowscale = 0.98F;
/* 115 */     this.atlasGlow = new TextureAtlas(Gdx.files.internal(downfallMod.assetPath("images/monsters/merchant/noShadow/skeletonGlow.atlas")));
/* 116 */     SkeletonJson json = new SkeletonJson(this.atlasGlow);
/* 117 */     if (CardCrawlGame.dungeon == null || AbstractDungeon.player != null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     json.setScale(Settings.scale / glowscale);
/* 128 */     SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(downfallMod.assetPath("images/monsters/merchant/noShadow/skeleton.json")));
/* 129 */     this.skeletonGlow = new Skeleton(skeletonData);
/* 130 */     this.skeletonGlow.setColor(this.glowColor);
/* 131 */     this.stateDataGlow = new AnimationStateData(skeletonData);
/* 132 */     this.stateGlow = new AnimationState(this.stateDataGlow);
/*     */     
/* 134 */     AnimationState.TrackEntry e = this.stateGlow.setAnimation(0, "idle", true);
/* 135 */     e.setTime(time);
/* 136 */     e.setTimeScale(1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateDeck() {
/* 143 */     AbstractBossDeckArchetype archetype = new ArchetypeAct3MerchantBoss();
/*     */     
/* 145 */     archetype.initialize();
/* 146 */     this.chosenArchetype = archetype;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateGlow() {
/* 151 */     if (!this.curveUp) {
/* 152 */       this.curveDuration -= Gdx.graphics.getDeltaTime();
/* 153 */       if (this.curveDuration < 0.0F) {
/* 154 */         this.curveUp = true;
/*     */       }
/*     */     } else {
/* 157 */       this.curveDuration += Gdx.graphics.getDeltaTime();
/* 158 */       if (this.curveDuration > 1.0F) {
/* 159 */         this.curveUp = false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 164 */     this.curveAlpha = Interpolation.pow2.apply(0.5F, 1.0F, this.curveDuration / 1.0F);
/*     */     
/* 166 */     this.skeletonGlow.setColor(new Color(this.glowColor.r * this.curveAlpha, this.glowColor.g * this.curveAlpha, this.glowColor.b * this.curveAlpha, this.glowColor.a * this.curveAlpha));
/*     */ 
/*     */     
/* 169 */     this.stateGlow.update(Gdx.graphics.getDeltaTime());
/* 170 */     this.stateGlow.apply(this.skeletonGlow);
/* 171 */     this.skeletonGlow.updateWorldTransform();
/* 172 */     this.skeletonGlow.setPosition(this.drawX + this.animX, this.drawY + this.animY);
/* 173 */     this.skeletonGlow.setFlip(this.flipHorizontal, this.flipVertical);
/*     */   }
/*     */   
/*     */   public void renderGlow(SpriteBatch sb) {
/* 177 */     sb.end();
/* 178 */     CardCrawlGame.psb.begin();
/* 179 */     sr.draw(CardCrawlGame.psb, this.skeletonGlow);
/* 180 */     CardCrawlGame.psb.end();
/* 181 */     sb.begin();
/* 182 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 187 */     this.animY = this.bob.y;
/* 188 */     sb.setColor(Color.WHITE);
/* 189 */     sb.draw(ImageMaster.MERCHANT_RUG_IMG, FleeingMerchant.DRAW_X, FleeingMerchant.DRAW_Y, 512.0F * Settings.scale, 512.0F * Settings.scale);
/*     */     
/* 191 */     if (this.rezTimer <= 0.0F && !this.isDying && !this.isDead) renderGlow(sb); 
/* 192 */     super.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 197 */     this.bob.update();
/* 198 */     updateGlow();
/* 199 */     super.update();
/* 200 */     if (this.rezTimer <= 0.0F) {
/* 201 */       this.rezVFX.end();
/*     */     } else {
/* 203 */       this.rezTimer -= Gdx.graphics.getDeltaTime();
/* 204 */       if (!this.startVFX && this.rezTimer <= 2.0F) {
/* 205 */         this.startVFX = true;
/* 206 */         this.tint.changeColor(Color.WHITE.cpy(), 2.0F);
/* 207 */         AbstractDungeon.effectsQueue.add(new IntenseZoomEffect(this.hb.cX, this.hb.cY, false));
/* 208 */         this.rezVFX = new NeowBossRezEffect(this.hb.cX, this.hb.cY);
/* 209 */         AbstractDungeon.effectsQueue.add(this.rezVFX);
/*     */       } 
/* 211 */       if (this.rezTimer > 2.0F) {
/* 212 */         this.tint.color = new Color(0.5F, 0.5F, 1.0F, 0.0F);
/*     */       }
/*     */     } 
/* 215 */     if (!this.neowSpoke) {
/* 216 */       String line; this.neowSpoke = true;
/*     */       
/* 218 */       if (FleeingMerchant.DEAD) {
/* 219 */         line = (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("NeowEnding"))).OPTIONS[0];
/*     */       } else {
/* 221 */         line = (CardCrawlGame.languagePack.getCharacterString(downfallMod.makeID("NeowEnding"))).OPTIONS[1];
/*     */       } 
/* 223 */       AbstractDungeon.effectList.add(new SpeechBubble(Settings.WIDTH, Settings.HEIGHT / 2.0F, 4.0F, line, false));
/* 224 */       CardCrawlGame.sound.play("VO_NEOW_2B");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void die() {
/* 230 */     super.die();
/* 231 */     FleeingMerchant.DEAD = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Merchant\CharBossMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */