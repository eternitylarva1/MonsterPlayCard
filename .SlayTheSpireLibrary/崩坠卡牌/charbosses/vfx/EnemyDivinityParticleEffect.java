/*     */ package charbosses.vfx;
/*     */ 
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class EnemyDivinityParticleEffect extends AbstractGameEffect {
/*     */   private float x;
/*     */   private float y;
/*     */   private float vY;
/*     */   private float dur_div2;
/*     */   private TextureAtlas.AtlasRegion img;
/*     */   
/*     */   public EnemyDivinityParticleEffect() {
/*  23 */     this.scale = Settings.scale;
/*  24 */     this.img = ImageMaster.EYE_ANIM_0;
/*  25 */     this.scale = MathUtils.random(1.0F, 1.5F);
/*  26 */     this.startingDuration = this.scale + 0.8F;
/*  27 */     this.duration = this.startingDuration;
/*  28 */     this.scale *= Settings.scale;
/*  29 */     this.dur_div2 = this.duration / 2.0F;
/*  30 */     this.color = new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.5F, 0.7F), MathUtils.random(0.8F, 1.0F), 0.0F);
/*  31 */     if (AbstractCharBoss.boss != null) {
/*  32 */       this.x = AbstractCharBoss.boss.hb.cX + MathUtils.random(-AbstractCharBoss.boss.hb.width / 2.0F - 50.0F * Settings.scale, AbstractCharBoss.boss.hb.width / 2.0F + 50.0F * Settings.scale);
/*  33 */       this.y = AbstractCharBoss.boss.hb.cY + MathUtils.random(-AbstractCharBoss.boss.hb.height / 2.0F + 10.0F * Settings.scale, AbstractCharBoss.boss.hb.height / 2.0F - 20.0F * Settings.scale);
/*     */     } 
/*  35 */     this.renderBehind = MathUtils.randomBoolean();
/*  36 */     this.rotation = MathUtils.random(12.0F, 6.0F);
/*  37 */     if (AbstractCharBoss.boss != null && 
/*  38 */       this.x > AbstractCharBoss.boss.hb.cX) {
/*  39 */       this.rotation = -this.rotation;
/*     */     }
/*     */ 
/*     */     
/*  43 */     this.x -= this.img.packedWidth / 2.0F;
/*  44 */     this.y -= this.img.packedHeight / 2.0F;
/*     */   }
/*     */   
/*     */   public void update() {
/*  48 */     if (this.duration > this.dur_div2) {
/*  49 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
/*     */     } else {
/*  51 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.dur_div2);
/*     */     } 
/*     */     
/*  54 */     if (this.duration > this.startingDuration * 0.85F) {
/*  55 */       this.vY = 12.0F * Settings.scale;
/*  56 */       this.img = ImageMaster.EYE_ANIM_0;
/*  57 */     } else if (this.duration > this.startingDuration * 0.8F) {
/*  58 */       this.vY = 8.0F * Settings.scale;
/*  59 */       this.img = ImageMaster.EYE_ANIM_1;
/*  60 */     } else if (this.duration > this.startingDuration * 0.75F) {
/*  61 */       this.vY = 4.0F * Settings.scale;
/*  62 */       this.img = ImageMaster.EYE_ANIM_2;
/*  63 */     } else if (this.duration > this.startingDuration * 0.7F) {
/*  64 */       this.vY = 3.0F * Settings.scale;
/*  65 */       this.img = ImageMaster.EYE_ANIM_3;
/*  66 */     } else if (this.duration > this.startingDuration * 0.65F) {
/*  67 */       this.img = ImageMaster.EYE_ANIM_4;
/*  68 */     } else if (this.duration > this.startingDuration * 0.6F) {
/*  69 */       this.img = ImageMaster.EYE_ANIM_5;
/*  70 */     } else if (this.duration > this.startingDuration * 0.55F) {
/*  71 */       this.img = ImageMaster.EYE_ANIM_6;
/*  72 */     } else if (this.duration > this.startingDuration * 0.38F) {
/*  73 */       this.img = ImageMaster.EYE_ANIM_5;
/*  74 */     } else if (this.duration > this.startingDuration * 0.3F) {
/*  75 */       this.img = ImageMaster.EYE_ANIM_4;
/*  76 */     } else if (this.duration > this.startingDuration * 0.25F) {
/*  77 */       this.vY = 3.0F * Settings.scale;
/*  78 */       this.img = ImageMaster.EYE_ANIM_3;
/*  79 */     } else if (this.duration > this.startingDuration * 0.2F) {
/*  80 */       this.vY = 4.0F * Settings.scale;
/*  81 */       this.img = ImageMaster.EYE_ANIM_2;
/*  82 */     } else if (this.duration > this.startingDuration * 0.15F) {
/*  83 */       this.vY = 8.0F * Settings.scale;
/*  84 */       this.img = ImageMaster.EYE_ANIM_1;
/*     */     } else {
/*  86 */       this.vY = 12.0F * Settings.scale;
/*  87 */       this.img = ImageMaster.EYE_ANIM_0;
/*     */     } 
/*     */     
/*  90 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  91 */     if (this.duration < 0.0F) {
/*  92 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  98 */     sb.setColor(this.color);
/*  99 */     if (AbstractCharBoss.boss != null) {
/* 100 */       sb.setBlendFunction(770, 1);
/* 101 */       sb.draw((TextureRegion)this.img, this.x, this.y + this.vY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/* 102 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyDivinityParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */