/*     */ package charbosses.orbs;
/*     */ 
/*     */ import charbosses.actions.common.EnemyGainEnergyAction;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.localization.OrbStrings;
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;
/*     */ 
/*     */ 
/*     */ public class EnemyPlasma
/*     */   extends AbstractEnemyOrb
/*     */ {
/*     */   public static final String ORB_ID = "Plasma";
/*     */   public static final String[] DESC;
/*  28 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Plasma"); private static final float ORB_WAVY_DIST = 0.04F; static {
/*  29 */     DESC = orbString.DESCRIPTION;
/*     */   }
/*     */   private static final float PI_4 = 12.566371F;
/*     */   private float vfxTimer;
/*     */   private float vfxIntervalMin;
/*     */   private float vfxIntervalMax;
/*     */   
/*     */   public EnemyPlasma() {
/*  37 */     this.vfxTimer = 1.0F;
/*  38 */     this.vfxIntervalMin = 0.1F;
/*  39 */     this.vfxIntervalMax = 0.4F;
/*  40 */     this.ID = "Plasma";
/*  41 */     this.img = ImageMaster.ORB_PLASMA;
/*  42 */     this.name = orbString.NAME;
/*  43 */     this.baseEvokeAmount = 2;
/*  44 */     this.evokeAmount = this.baseEvokeAmount;
/*  45 */     this.basePassiveAmount = 1;
/*  46 */     this.passiveAmount = this.basePassiveAmount;
/*  47 */     updateDescription();
/*  48 */     this.angle = MathUtils.random(360.0F);
/*  49 */     this.channelAnimTimer = 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDescription() {
/*  54 */     applyFocus();
/*  55 */     this.description = DESC[0] + this.evokeAmount + DESC[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvoke() {
/*  60 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new EnemyGainEnergyAction(this.evokeAmount));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEndOfTurn() {
/*  65 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA), 0.1F));
/*  66 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new EnemyGainEnergyAction(this.passiveAmount));
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerEvokeAnimation() {
/*  71 */     CardCrawlGame.sound.play("ORB_PLASMA_EVOKE", 0.1F);
/*  72 */     AbstractDungeon.effectsQueue.add(new PlasmaOrbActivateEffect(this.cX, this.cY));
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimation() {
/*  77 */     super.updateAnimation();
/*  78 */     this.angle += Gdx.graphics.getDeltaTime() * 45.0F;
/*  79 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/*  80 */     if (this.vfxTimer < 0.0F) {
/*  81 */       AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(this.cX, this.cY));
/*  82 */       this.vfxTimer = MathUtils.random(this.vfxIntervalMin, this.vfxIntervalMax);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  88 */     this.c.a /= 2.0F;
/*  89 */     sb.setColor(this.shineColor);
/*  90 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
/*  91 */     sb.setBlendFunction(770, 1);
/*  92 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale, -this.angle, 0, 0, 96, 96, false, false);
/*  93 */     sb.setBlendFunction(770, 771);
/*  94 */     renderText(sb);
/*  95 */     this.hb.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderText(SpriteBatch sb) {
/* 100 */     if (this.showEvokeValue) {
/* 101 */       FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void playChannelSFX() {
/* 107 */     CardCrawlGame.sound.play("ORB_PLASMA_CHANNEL", 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractOrb makeCopy() {
/* 112 */     return new EnemyPlasma();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\orbs\EnemyPlasma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */