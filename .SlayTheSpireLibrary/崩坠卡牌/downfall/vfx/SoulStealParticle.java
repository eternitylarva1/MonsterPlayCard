/*     */ package downfall.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.CatmullRomSpline;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.math.Vector;
/*     */ import com.badlogic.gdx.math.Vector2;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.DamageImpactLineEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SoulStealParticle
/*     */   extends AbstractGameEffect
/*     */ {
/*  24 */   private CatmullRomSpline<Vector2> crs = new CatmullRomSpline();
/*  25 */   private ArrayList<Vector2> controlPoints = new ArrayList<>();
/*     */   
/*  27 */   private Vector2[] points = new Vector2[60];
/*     */ 
/*     */   
/*  30 */   private float currentSpeed = 0.0F;
/*     */ 
/*     */   
/*     */   private boolean rotateClockwise = true;
/*     */ 
/*     */   
/*     */   private boolean stopRotating = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public SoulStealParticle(float sX, float sY, float tX, float tY, boolean facingLeft) {
/*  41 */     this.img = ImageMaster.GLOW_SPARK_2;
/*  42 */     this.pos = new Vector2(sX, sY);
/*  43 */     if (!facingLeft) {
/*  44 */       this.target = new Vector2(tX + DST_THRESHOLD, tY);
/*     */     } else {
/*  46 */       this.target = new Vector2(tX - DST_THRESHOLD, tY);
/*     */     } 
/*     */     
/*  49 */     this.facingLeft = facingLeft;
/*  50 */     this.crs.controlPoints = (Vector[])new Vector2[1];
/*  51 */     this.rotateClockwise = MathUtils.randomBoolean();
/*  52 */     this.rotation = MathUtils.random(0, 359);
/*  53 */     this.controlPoints.clear();
/*  54 */     this.rotationRate = MathUtils.random(600.0F, 650.0F) * Settings.scale;
/*  55 */     this.currentSpeed = 1000.0F * Settings.scale;
/*  56 */     this.color = new Color(0.5F, 0.5F, 1.0F, 0.6F);
/*  57 */     this.duration = 0.7F;
/*  58 */     this.scale = 1.0F * Settings.scale;
/*  59 */     this.renderBehind = MathUtils.randomBoolean();
/*     */   }
/*     */   
/*     */   public void update() {
/*  63 */     updateMovement();
/*     */   }
/*     */   
/*     */   private void updateMovement() {
/*  67 */     Vector2 tmp = new Vector2(this.pos.x - this.target.x, this.pos.y - this.target.y);
/*  68 */     tmp.nor();
/*  69 */     float targetAngle = tmp.angle();
/*  70 */     this.rotationRate += Gdx.graphics.getDeltaTime() * 2000.0F;
/*  71 */     this.scale += Gdx.graphics.getDeltaTime() * 1.0F * Settings.scale;
/*  72 */     if (!this.stopRotating) {
/*  73 */       if (this.rotateClockwise) {
/*  74 */         this.rotation += Gdx.graphics.getDeltaTime() * this.rotationRate;
/*     */       } else {
/*  76 */         this.rotation -= Gdx.graphics.getDeltaTime() * this.rotationRate;
/*  77 */         if (this.rotation < 0.0F) {
/*  78 */           this.rotation += 360.0F;
/*     */         }
/*     */       } 
/*     */       
/*  82 */       this.rotation %= 360.0F;
/*  83 */       if (!this.stopRotating && Math.abs(this.rotation - targetAngle) < Gdx.graphics.getDeltaTime() * this.rotationRate) {
/*  84 */         this.rotation = targetAngle;
/*  85 */         this.stopRotating = true;
/*     */       } 
/*     */     } 
/*     */     
/*  89 */     tmp.setAngle(this.rotation);
/*  90 */     tmp.x *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/*  91 */     tmp.y *= Gdx.graphics.getDeltaTime() * this.currentSpeed;
/*  92 */     this.pos.sub(tmp);
/*  93 */     if (this.stopRotating) {
/*  94 */       this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 3.0F;
/*     */     } else {
/*  96 */       this.currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 1.5F;
/*     */     } 
/*     */     
/*  99 */     if (this.currentSpeed > MAX_VELOCITY) {
/* 100 */       this.currentSpeed = MAX_VELOCITY;
/*     */     }
/*     */     
/* 103 */     if (this.target.dst(this.pos) < DST_THRESHOLD) {
/* 104 */       for (int i = 0; i < 5; i++) {
/* 105 */         if (this.facingLeft) {
/* 106 */           AbstractDungeon.effectsQueue.add(new DamageImpactLineEffect(this.target.x + DST_THRESHOLD, this.target.y));
/*     */         } else {
/* 108 */           AbstractDungeon.effectsQueue.add(new DamageImpactLineEffect(this.target.x - DST_THRESHOLD, this.target.y));
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 114 */       this.isDone = true;
/*     */     } 
/*     */     
/* 117 */     if (!this.controlPoints.isEmpty()) {
/* 118 */       if (!((Vector2)this.controlPoints.get(0)).equals(this.pos)) {
/* 119 */         this.controlPoints.add(this.pos.cpy());
/*     */       }
/*     */     } else {
/* 122 */       this.controlPoints.add(this.pos.cpy());
/*     */     } 
/*     */     
/* 125 */     if (this.controlPoints.size() > 3) {
/* 126 */       Vector2[] vec2Array = new Vector2[0];
/* 127 */       this.crs.set((Vector[])this.controlPoints.<Vector2>toArray(vec2Array), false);
/*     */       
/* 129 */       for (int i = 0; i < 60; i++) {
/* 130 */         this.points[i] = new Vector2();
/* 131 */         this.crs.valueAt((Vector)this.points[i], i / 59.0F);
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     if (this.controlPoints.size() > 10) {
/* 136 */       this.controlPoints.remove(0);
/*     */     }
/*     */     
/* 139 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 140 */     if (this.duration < 0.0F) {
/* 141 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 147 */     if (!this.isDone) {
/* 148 */       sb.setColor(Color.BLACK);
/* 149 */       float scaleCpy = this.scale;
/*     */       
/*     */       int i;
/* 152 */       for (i = this.points.length - 1; i > 0; i--) {
/* 153 */         if (this.points[i] != null) {
/* 154 */           sb.draw((TextureRegion)this.img, (this.points[i]).x - (this.img.packedWidth / 2), (this.points[i]).y - (this.img.packedHeight / 2), this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, scaleCpy * 1.5F, scaleCpy * 1.5F, this.rotation);
/* 155 */           scaleCpy *= 0.98F;
/*     */         } 
/*     */       } 
/*     */       
/* 159 */       sb.setBlendFunction(770, 1);
/* 160 */       sb.setColor(this.color);
/* 161 */       scaleCpy = this.scale;
/*     */       
/* 163 */       for (i = this.points.length - 1; i > 0; i--) {
/* 164 */         if (this.points[i] != null) {
/* 165 */           sb.draw((TextureRegion)this.img, (this.points[i]).x - (this.img.packedWidth / 2), (this.points[i]).y - (this.img.packedHeight / 2), this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, scaleCpy, scaleCpy, this.rotation);
/* 166 */           scaleCpy *= 0.98F;
/*     */         } 
/*     */       } 
/*     */       
/* 170 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {}
/*     */ 
/*     */   
/* 179 */   private static final float MAX_VELOCITY = 4000.0F * Settings.scale;
/* 180 */   private static final float VELOCITY_RAMP_RATE = 3000.0F * Settings.scale;
/* 181 */   private static final float DST_THRESHOLD = 42.0F * Settings.scale;
/*     */   private TextureAtlas.AtlasRegion img;
/*     */   private static final int TRAIL_ACCURACY = 60;
/*     */   private Vector2 pos;
/*     */   private Vector2 target;
/*     */   private float rotation;
/*     */   private boolean facingLeft;
/*     */   private float rotationRate;
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\SoulStealParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */