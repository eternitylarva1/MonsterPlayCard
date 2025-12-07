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
/*    */ public class NeowRezFlareParticleEffect extends AbstractGameEffect {
/* 15 */   private Vector2 pos = new Vector2();
/*    */   private float speed;
/*    */   private float speedStart;
/*    */   private float speedTarget;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public NeowRezFlareParticleEffect(float x, float y, Color color, float angle, NeowBossRezEffect owner) {
/* 22 */     this.img = ImageMaster.STRIKE_BLUR;
/* 23 */     this.duration = MathUtils.random(0.2F, 0.5F);
/* 24 */     this.startingDuration = this.duration;
/* 25 */     this.pos.x = x - this.img.packedWidth / 2.0F;
/* 26 */     this.pos.y = y - this.img.packedHeight / 2.0F;
/* 27 */     this.speed = MathUtils.random(100.0F, 800.0F) * Settings.scale;
/* 28 */     this.speedStart = this.speed;
/* 29 */     this.speedTarget = MathUtils.random(0.7F, 1.3F) * this.speed;
/* 30 */     this.color = color.cpy();
/* 31 */     this.color.a = 0.0F;
/* 32 */     this.renderBehind = false;
/* 33 */     this.rotation = MathUtils.random(-360.0F, 360.0F);
/* 34 */     this.scale = MathUtils.random(0.2F, 1.0F) * Settings.scale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 38 */     Vector2 tmp = new Vector2(MathUtils.cosDeg(this.rotation), MathUtils.sinDeg(this.rotation));
/* 39 */     tmp.x *= this.speed * Gdx.graphics.getDeltaTime();
/* 40 */     tmp.y *= this.speed * Gdx.graphics.getDeltaTime();
/* 41 */     this.speed = Interpolation.pow2OutInverse.apply(this.speedStart, this.speedTarget, 1.0F - this.duration / this.startingDuration);
/* 42 */     this.pos.x += tmp.x;
/* 43 */     this.pos.y += tmp.y;
/*    */     
/* 45 */     if (this.duration < 0.5F) {
/* 46 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration * 2.0F);
/*    */     } else {
/* 48 */       this.color.a = 1.0F;
/*    */     } 
/*    */     
/* 51 */     super.update();
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 55 */     sb.setBlendFunction(770, 1);
/* 56 */     sb.setColor(new Color(this.color.r, this.color.g, this.color.b, this.color.a / 4.0F));
/* 57 */     sb.draw((TextureRegion)this.img, this.pos.x, this.pos.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 4.0F, this.scale * 4.0F, this.rotation);
/* 58 */     sb.setColor(this.color);
/* 59 */     sb.draw((TextureRegion)this.img, this.pos.x, this.pos.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/* 60 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\NeowRezFlareParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */