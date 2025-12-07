/*     */ package downfall.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.ShineLinesEffect;
/*     */ 
/*     */ public class ThrowGoldEffect extends AbstractGameEffect {
/*     */   private static final float GRAVITY = 0.0F;
/*  19 */   private static final float START_VY = 800.0F * Settings.scale;
/*  20 */   private static final float START_VY_JITTER = 400.0F * Settings.scale;
/*  21 */   private static final float START_VX = -200.0F * Settings.scale;
/*  22 */   private static final float START_VX_JITTER = 200.0F * Settings.scale;
/*  23 */   private static final float TARGET_JITTER = 10.0F * Settings.scale;
/*     */   private float rotationSpeed;
/*     */   private float x;
/*     */   private float y;
/*     */   private float vX;
/*     */   private float vY;
/*     */   private float targetX;
/*     */   private float targetY;
/*     */   private float finalTargetX;
/*     */   private float finalTargetY;
/*     */   private TextureAtlas.AtlasRegion img;
/*  34 */   private float alpha = 0.0F;
/*  35 */   private float suctionTimer = 1.4F;
/*     */   private float staggerTimer;
/*  37 */   private float EPSILON = 1.0E-4F;
/*     */   
/*     */   private boolean hitTarget = false;
/*     */   
/*     */   public ThrowGoldEffect(AbstractCreature owner, float x, float y, float targetX, float targetY, float staggerTimer) {
/*  42 */     if (MathUtils.randomBoolean()) {
/*  43 */       this.img = ImageMaster.COPPER_COIN_1;
/*     */     } else {
/*  45 */       this.img = ImageMaster.COPPER_COIN_2;
/*     */     } 
/*  47 */     this.x = x - this.img.packedWidth / 2.0F;
/*  48 */     this.y = y - this.img.packedHeight / 2.0F;
/*  49 */     this.targetX = targetX + MathUtils.random(-TARGET_JITTER, TARGET_JITTER);
/*  50 */     this.targetY = targetY + MathUtils.random(-TARGET_JITTER, TARGET_JITTER);
/*     */     
/*  52 */     this.staggerTimer = staggerTimer;
/*  53 */     this.vX = MathUtils.random(START_VX, START_VX_JITTER);
/*  54 */     this.rotationSpeed = MathUtils.random(500.0F, 2000.0F);
/*  55 */     if (MathUtils.randomBoolean()) {
/*  56 */       this.rotationSpeed *= -1.0F;
/*     */     }
/*  58 */     this.vY = MathUtils.random(START_VY, START_VY_JITTER);
/*  59 */     this.scale = Settings.scale;
/*  60 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public ThrowGoldEffect(AbstractCreature owner, float x, float y, float targetX, float targetY) {
/*  65 */     this(owner, x, y, targetX, targetY, MathUtils.random(0.0F, 0.5F));
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
/*     */   public void update() {
/*  78 */     if (this.alpha != 1.0F) {
/*  79 */       this.alpha += Gdx.graphics.getDeltaTime() * 2.0F;
/*  80 */       if (this.alpha > 1.0F) {
/*  81 */         this.alpha = 1.0F;
/*     */       }
/*  83 */       this.color.a = this.alpha;
/*     */     } 
/*  85 */     this.rotation += Gdx.graphics.getDeltaTime() * this.rotationSpeed;
/*  86 */     this.x += Gdx.graphics.getDeltaTime() * this.vX;
/*  87 */     this.y += Gdx.graphics.getDeltaTime() * this.vY;
/*  88 */     this.vY -= Gdx.graphics.getDeltaTime() * 0.0F;
/*     */     
/*  90 */     if (this.suctionTimer > 0.0F) {
/*  91 */       this.suctionTimer -= Gdx.graphics.getDeltaTime();
/*     */       
/*  93 */       this.vY = MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() * 5.0F);
/*  94 */       this.vX = MathUtils.lerp(this.vX, 0.0F, Gdx.graphics.getDeltaTime() * 5.0F);
/*     */       
/*  96 */       this.finalTargetX = (this.targetX - this.x) * 2.0F + this.x;
/*  97 */       this.finalTargetY = (this.targetY - this.y) * 2.0F + this.y;
/*     */     } else {
/*  99 */       if (this.staggerTimer > 0.0F) {
/* 100 */         this.staggerTimer -= Gdx.graphics.getDeltaTime();
/*     */         
/*     */         return;
/*     */       } 
/* 104 */       this.x = MathUtils.lerp(this.x, this.finalTargetX, Gdx.graphics.getDeltaTime() * 4.0F);
/* 105 */       this.y = MathUtils.lerp(this.y, this.finalTargetY, Gdx.graphics.getDeltaTime() * 4.0F);
/* 106 */       if (!this.hitTarget && Math.abs(this.x - this.targetX) < 20.0F) {
/* 107 */         this.hitTarget = true;
/* 108 */         CardCrawlGame.sound.play("MONSTER_BOOK_STAB_3", 0.1F);
/*     */         
/* 110 */         AbstractDungeon.effectsQueue.add(new ShineLinesEffect(this.x, this.y));
/*     */       } 
/*     */       
/* 113 */       if (this.x < 0.0F) {
/* 114 */         this.isDone = true;
/*     */       }
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
/*     */   public void render(SpriteBatch sb) {
/* 127 */     sb.setColor(this.color);
/* 128 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\ThrowGoldEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */