/*     */ package downfall.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ 
/*     */ public class PortalBorderEffect extends AbstractGameEffect {
/*  12 */   private float vfxTimer = 0.0F;
/*     */   
/*  14 */   private final float NON_ORBITAL_ADJUSTMENT_MIN_SPEED = 400.0F * Settings.scale;
/*  15 */   private final float NON_ORBITAL_ADJUSTMENT_MAX_SPEED = 600.0F * Settings.scale;
/*  16 */   private final int TARGET_WISP_COUNT_MAX_SIZE_ELLIPSE = 12;
/*  17 */   private final float DEFAULT_ORBIT_DURATION = 8.0F;
/*     */   
/*  19 */   private float ELLIPSIS_WIDTH = 235.0F * Settings.scale;
/*  20 */   private float ELLIPSIS_HEIGHT = 235.0F * Settings.scale;
/*     */   
/*  22 */   private float ELLIPSIS_BASE_WIDTH = 235.0F * Settings.scale;
/*  23 */   private float ELLIPSIS_BASE_HEIGHT = 235.0F * Settings.scale;
/*     */   
/*  25 */   public float ELLIPSIS_SCALE = 1.0F;
/*     */   
/*  27 */   private float ORBIT_DURATION = 8.0F;
/*  28 */   private float NON_ORBITAL_ADJUSTMENT_SPEED = 200.0F * Settings.scale;
/*  29 */   private float ELLIPSIS_X = 0.0F;
/*  30 */   private float ELLIPSIS_Y = 0.0F;
/*     */   
/*     */   private boolean orbitClockwise = false;
/*     */   
/*     */   public float cX;
/*     */   public float cY;
/*     */   private float tX;
/*     */   private float tY;
/*     */   public float initialAngle;
/*     */   public float angle;
/*     */   public float orbitalInterval;
/*  41 */   public Color borderColor = Color.VIOLET;
/*     */   
/*     */   public PortalBorderEffect(float startX, float startY, float angle) {
/*  44 */     this(startX, startY, angle, 1.0F);
/*     */   }
/*     */   
/*     */   public PortalBorderEffect(float startX, float startY, float angle, float scale) {
/*  48 */     this.cX = startX;
/*  49 */     this.cY = startY;
/*  50 */     this.tX = startX;
/*  51 */     this.tY = startY;
/*     */     
/*  53 */     this.ELLIPSIS_X = startX;
/*  54 */     this.ELLIPSIS_Y = startY;
/*     */     
/*  56 */     this.initialAngle = angle;
/*     */ 
/*     */     
/*  59 */     this.renderBehind = false;
/*     */     
/*  61 */     this.ELLIPSIS_SCALE *= scale;
/*     */     
/*  63 */     calculateEllipseSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void calculateNewPosition() {
/*  68 */     this.angle = this.initialAngle + 360.0F * this.orbitalInterval / this.ORBIT_DURATION;
/*  69 */     if (this.angle > 360.0F) {
/*  70 */       this.angle -= 360.0F;
/*     */     }
/*  72 */     if (this.orbitClockwise) {
/*  73 */       this.angle = 360.0F - this.angle;
/*     */     }
/*     */ 
/*     */     
/*  77 */     float tmp = this.angle * 0.017453292F;
/*  78 */     this.tX = this.ELLIPSIS_WIDTH * this.ELLIPSIS_HEIGHT / (float)Math.sqrt((this.ELLIPSIS_HEIGHT * this.ELLIPSIS_HEIGHT) + (this.ELLIPSIS_WIDTH * this.ELLIPSIS_WIDTH) * Math.tan(tmp) * Math.tan(tmp));
/*  79 */     if (90.0F < this.angle && this.angle < 270.0F) {
/*  80 */       this.tX *= -1.0F;
/*     */     }
/*     */ 
/*     */     
/*  84 */     this.tY = (float)Math.sqrt(((this.ELLIPSIS_WIDTH * this.ELLIPSIS_WIDTH * this.ELLIPSIS_HEIGHT * this.ELLIPSIS_HEIGHT - this.tX * this.tX * this.ELLIPSIS_HEIGHT * this.ELLIPSIS_HEIGHT) / this.ELLIPSIS_WIDTH * this.ELLIPSIS_WIDTH));
/*  85 */     if (180.0F < this.angle && this.angle < 360.0F) {
/*  86 */       this.tY *= -1.0F;
/*     */     }
/*     */ 
/*     */     
/*  90 */     this.tX += this.ELLIPSIS_X;
/*  91 */     this.tY += this.ELLIPSIS_Y;
/*     */ 
/*     */ 
/*     */     
/*  95 */     this.cX = this.tX;
/*  96 */     this.cY = this.tY;
/*     */ 
/*     */     
/*  99 */     this.orbitalInterval += Gdx.graphics.getDeltaTime();
/* 100 */     if (this.orbitalInterval > this.ORBIT_DURATION) {
/* 101 */       this.orbitalInterval -= this.ORBIT_DURATION;
/*     */     }
/*     */   }
/*     */   
/*     */   public void calculateEllipseSize() {
/* 106 */     this.ELLIPSIS_WIDTH = this.ELLIPSIS_BASE_WIDTH * this.ELLIPSIS_SCALE;
/* 107 */     this.ELLIPSIS_HEIGHT = this.ELLIPSIS_BASE_HEIGHT * this.ELLIPSIS_SCALE;
/*     */     
/* 109 */     this.NON_ORBITAL_ADJUSTMENT_SPEED = Interpolation.linear.apply(this.NON_ORBITAL_ADJUSTMENT_MIN_SPEED, this.NON_ORBITAL_ADJUSTMENT_MAX_SPEED, Math.min(12, CustomAnimatedNPC.borderEffectCount) / 12.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 114 */     calculateNewPosition();
/* 115 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/* 116 */     if (this.vfxTimer < 0.0F) {
/* 117 */       this.vfxTimer = 0.016F;
/* 118 */       AbstractDungeon.effectsQueue.add(new PortalEdgeFlareParticleEffect(this.cX, this.cY, this.borderColor, this.angle, this));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void end() {
/* 130 */     this.isDone = true;
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\PortalBorderEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */