/*    */ package downfall.vfx;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.badlogic.gdx.math.Vector2;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class PortalEdgeFlareParticleEffect extends AbstractGameEffect {
/* 15 */   private Vector2 pos = new Vector2();
/*    */   private float speed;
/*    */   private float speedStart;
/*    */   private float speedTarget;
/*    */   private float waveIntensity;
/*    */   private float waveSpeed;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private PortalBorderEffect owner;
/*    */   private float baseScale;
/*    */   private float baseRotation;
/*    */   
/*    */   public PortalEdgeFlareParticleEffect(float x, float y, Color color, float angle, PortalBorderEffect owner) {
/* 27 */     this.img = ImageMaster.STRIKE_BLUR;
/* 28 */     this.duration = MathUtils.random(0.2F, 0.5F);
/* 29 */     this.startingDuration = this.duration;
/* 30 */     this.pos.x = x - this.img.packedWidth / 2.0F;
/* 31 */     this.pos.y = y - this.img.packedHeight / 2.0F;
/* 32 */     this.speed = MathUtils.random(100.0F, 200.0F) * Settings.scale;
/* 33 */     this.speedStart = this.speed;
/* 34 */     this.speedTarget = MathUtils.random(10.0F, 20.0F) * Settings.scale;
/* 35 */     this.color = color.cpy();
/* 36 */     this.color.a = 0.0F;
/* 37 */     this.renderBehind = false;
/* 38 */     this.baseRotation = angle;
/* 39 */     this.rotation = angle + MathUtils.random(-15.0F, 15.0F);
/* 40 */     this.waveIntensity = MathUtils.random(2.0F, 5.0F);
/* 41 */     this.waveSpeed = MathUtils.random(5.0F, 10.0F);
/* 42 */     this.speedTarget = MathUtils.random(0.1F, 0.5F);
/* 43 */     this.owner = owner;
/* 44 */     this.scale = MathUtils.random(0.2F, 1.0F) * Settings.scale;
/* 45 */     this.baseScale = this.scale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 49 */     Vector2 tmp = new Vector2(MathUtils.cosDeg(this.rotation), MathUtils.sinDeg(this.rotation));
/* 50 */     tmp.x *= this.speed * Gdx.graphics.getDeltaTime();
/* 51 */     tmp.y *= this.speed * Gdx.graphics.getDeltaTime();
/* 52 */     this.speed = Interpolation.pow2OutInverse.apply(this.speedStart, this.speedTarget, 1.0F - this.duration / this.startingDuration);
/* 53 */     this.pos.x += tmp.x;
/* 54 */     this.pos.y += tmp.y;
/* 55 */     this.rotation = this.baseRotation + MathUtils.random(-15.0F, 15.0F);
/*    */     
/* 57 */     if (this.duration < 0.5F) {
/* 58 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 2.0F);
/*    */     } else {
/* 60 */       this.color.a = 1.0F;
/*    */     } 
/* 62 */     this.scale = this.baseScale * this.owner.ELLIPSIS_SCALE;
/*    */     
/* 64 */     super.update();
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 68 */     sb.setBlendFunction(770, 1);
/* 69 */     sb.setColor(new Color(this.color.r, this.color.g, this.color.b, this.color.a / 4.0F));
/* 70 */     sb.draw((TextureRegion)this.img, this.pos.x, this.pos.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 4.0F, this.scale * 4.0F, this.rotation);
/* 71 */     sb.setColor(this.color);
/* 72 */     sb.draw((TextureRegion)this.img, this.pos.x, this.pos.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/* 73 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\PortalEdgeFlareParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */