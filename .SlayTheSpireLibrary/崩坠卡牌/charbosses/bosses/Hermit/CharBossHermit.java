/*     */ package charbosses.bosses.Hermit;
/*     */ 
/*     */ import charbosses.bosses.AbstractBossDeckArchetype;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Hermit.NewAge.ArchetypeAct1SharpshooterNewAge;
/*     */ import charbosses.bosses.Hermit.NewAge.ArchetypeAct2WheelOfFateNewAge;
/*     */ import charbosses.bosses.Hermit.NewAge.ArchetypeAct3DoomsdayNewAge;
/*     */ import charbosses.core.EnemyEnergyManager;
/*     */ import charbosses.ui.EnergyOrbHermit;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.esotericsoftware.spine.Slot;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
/*     */ import downfall.downfallMod;
/*     */ import downfall.util.LocalizeHelper;
/*     */ import hermit.characters.hermit;
/*     */ import hermit.effects.HermitEyeParticle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CharBossHermit
/*     */   extends AbstractCharBoss
/*     */ {
/*  35 */   public static final String ID = downfallMod.makeID("Hermit");
/*  36 */   public static final String NAME = LocalizeHelper.DonwfallRunHistoryMonsterNames.TEXT[6];
/*     */   
/*     */   public Slot eye;
/*  39 */   private float fireTimer = 0.0F; public static AbstractCard previewCard; public static final float PREVIEW_CARD_SIZE = 0.5F;
/*     */   
/*     */   public CharBossHermit() {
/*  42 */     super(NAME, ID, 72, 0.0F, -5.0F, 240.0F, 270.0F, null, 0.0F, -20.0F, hermit.Enums.HERMIT);
/*  43 */     this.energyOrb = (EnergyOrbInterface)new EnergyOrbHermit();
/*  44 */     this.energy = new EnemyEnergyManager(3);
/*  45 */     loadAnimation("hermitResources/images/char/hermit/Hermit.atlas", "hermitResources/images/char/hermit/Hermit.json", 1.0F);
/*     */ 
/*     */ 
/*     */     
/*  49 */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*  50 */     this.flipHorizontal = true;
/*  51 */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*  52 */     e.setTimeScale(0.7F);
/*  53 */     this.energyString = "[E]";
/*  54 */     this.type = AbstractMonster.EnemyType.BOSS;
/*     */ 
/*     */     
/*  57 */     this.eye = this.skeleton.findSlot("eye");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateDeck() {
/*     */     ArchetypeAct1SharpshooterNewAge archetypeAct1SharpshooterNewAge;
/*  64 */     if (downfallMod.overrideBossDifficulty) {
/*  65 */       archetypeAct1SharpshooterNewAge = new ArchetypeAct1SharpshooterNewAge();
/*  66 */       downfallMod.overrideBossDifficulty = false;
/*  67 */       this.currentHealth -= 100;
/*     */     } else {
/*  69 */       ArchetypeAct1SharpshooterNewAge archetypeAct1SharpshooterNewAge1; ArchetypeAct2WheelOfFateNewAge archetypeAct2WheelOfFateNewAge; ArchetypeAct3DoomsdayNewAge archetypeAct3DoomsdayNewAge; switch (AbstractDungeon.actNum) {
/*     */         case 1:
/*  71 */           archetypeAct1SharpshooterNewAge1 = new ArchetypeAct1SharpshooterNewAge();
/*     */           break;
/*     */         case 2:
/*  74 */           archetypeAct2WheelOfFateNewAge = new ArchetypeAct2WheelOfFateNewAge();
/*     */           break;
/*     */         case 3:
/*  77 */           archetypeAct3DoomsdayNewAge = new ArchetypeAct3DoomsdayNewAge();
/*     */           break;
/*     */         default:
/*  80 */           archetypeAct1SharpshooterNewAge = new ArchetypeAct1SharpshooterNewAge();
/*     */           break;
/*     */       } 
/*     */     } 
/*  84 */     archetypeAct1SharpshooterNewAge.initialize();
/*  85 */     this.chosenArchetype = (AbstractBossDeckArchetype)archetypeAct1SharpshooterNewAge;
/*  86 */     if (AbstractDungeon.ascensionLevel >= 19) {
/*  87 */       archetypeAct1SharpshooterNewAge.initializeBonusRelic();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onPlayAttackCardSound() {
/*  95 */     switch (MathUtils.random(1)) {
/*     */       case 0:
/*  97 */         CardCrawlGame.sound.play("VO_GREMLINCALM_1A");
/*     */         break;
/*     */       case 1:
/* 100 */         CardCrawlGame.sound.play("VO_GREMLINCALM_1B");
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void damage(DamageInfo info) {
/* 108 */     if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
/* 109 */       AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
/* 110 */       this.state.addAnimation(0, "Idle", true, 0.0F);
/* 111 */       e.setTimeScale(0.6F);
/*     */     } 
/*     */     
/* 114 */     super.damage(info);
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
/*     */   public void render(SpriteBatch sb) {
/* 143 */     super.render(sb);
/* 144 */     if (this.chosenArchetype instanceof ArchetypeAct2WheelOfFateNewAge) {
/* 145 */       if (previewCard == null && !((ArchetypeAct2WheelOfFateNewAge)this.chosenArchetype).mockDeck.isEmpty()) {
/* 146 */         AbstractCard q = ((ArchetypeAct2WheelOfFateNewAge)this.chosenArchetype).mockDeck.get(0);
/* 147 */         previewCard = q.makeStatEquivalentCopy();
/*     */       } 
/* 149 */       if (previewCard != null && !this.isDead && !this.isDying) {
/* 150 */         int cardsinrow = Math.min(3 - 10 * (int)Math.floor(0.30000001192092896D), 10);
/* 151 */         float widthspacing = AbstractCard.IMG_WIDTH_S + 100.0F * Settings.scale;
/* 152 */         int tar = 4;
/* 153 */         previewCard.target_x = previewCard.current_x = Settings.WIDTH * 0.9F - (cardsinrow + 0.5F) * widthspacing * 0.35F + widthspacing * 0.35F * (tar % 10);
/* 154 */         previewCard.target_y = previewCard.current_y = Settings.HEIGHT * 0.56F + AbstractCard.IMG_HEIGHT_S * 0.35F * ((float)Math.floor((tar / 10.0F)) + ((tar > 10) ? 0.0F : 1.0F));
/* 155 */         previewCard.drawScale = previewCard.targetDrawScale = previewCard.hb.hovered ? 0.8F : 0.35F;
/* 156 */         previewCard.render(sb);
/* 157 */         FontHelper.cardDescFont_N.getData().setScale(1.0F);
/* 158 */         FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, hermit.characterStrings.TEXT[4], previewCard.current_x, previewCard.current_y - (previewCard.hb.hovered ? '¯' : 100) * Settings.scale);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 165 */     super.update();
/* 166 */     if (!this.isDying && hasPower("downfall:HermitConcentrationPower") && 
/* 167 */       (getPower("downfall:HermitConcentrationPower")).amount > 0) {
/* 168 */       this.fireTimer -= Gdx.graphics.getDeltaTime();
/* 169 */       if (this.fireTimer < 0.0F) {
/* 170 */         this.fireTimer = 0.1F;
/* 171 */         HermitEyeParticle shine = new HermitEyeParticle(this.skeleton.getX() + this.eye.getBone().getWorldX(), this.skeleton.getY() + this.eye.getBone().getWorldY(), (AbstractCreature)this, this.skeleton);
/* 172 */         AbstractDungeon.effectList.add(shine);
/*     */       } 
/*     */     } 
/*     */     
/* 176 */     if (previewCard != null && !this.isDying && !this.isDead) {
/* 177 */       previewCard.update();
/* 178 */       previewCard.updateHoverLogic();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\bosses\Hermit\CharBossHermit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */