/*    */ package charbosses.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class QuietSpecialSmokeBombEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private float x;
/*    */   private float y;
/*    */   private float aV;
/*    */   
/*    */   public QuietSpecialSmokeBombEffect(float x, float y) {
/* 22 */     this.color = new Color(0.0F, 0.0F, 0.0F, 1.0F);
/* 23 */     this.color.r = MathUtils.random(0.5F, 0.6F);
/* 24 */     this.color.g = this.color.r + MathUtils.random(0.0F, 0.2F);
/* 25 */     this.color.b = 0.2F;
/* 26 */     if (MathUtils.randomBoolean()) {
/* 27 */       this.img = ImageMaster.EXHAUST_L;
/*    */     } else {
/* 29 */       this.img = ImageMaster.EXHAUST_S;
/*    */     } 
/* 31 */     this.duration = MathUtils.random(2.0F, 2.5F);
/* 32 */     this.targetScale = MathUtils.random(0.2F, 0.3F);
/*    */     
/* 34 */     this.startDur = this.duration;
/* 35 */     this.x = x - this.img.packedWidth / 2.0F;
/* 36 */     this.y = y - this.img.packedHeight / 2.0F;
/* 37 */     this.scale = 0.01F;
/* 38 */     this.rotation = MathUtils.random(360.0F);
/* 39 */     this.aV = MathUtils.random(-250.0F, 250.0F);
/*    */   }
/*    */   private float startDur; private float targetScale; private TextureAtlas.AtlasRegion img;
/*    */   public void update() {
/* 43 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 44 */     if (this.duration < 0.0F) {
/* 45 */       this.isDone = true;
/*    */     }
/*    */     
/* 48 */     this.x += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
/* 49 */     this.y += MathUtils.random(-2.0F * Settings.scale, 2.0F * Settings.scale);
/* 50 */     this.rotation += this.aV * Gdx.graphics.getDeltaTime();
/* 51 */     this.scale = Interpolation.exp10Out.apply(0.01F, this.targetScale, 1.0F - this.duration / this.startDur);
/* 52 */     if (this.duration < 0.33F) {
/* 53 */       this.color.a = this.duration * 3.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 59 */     sb.setColor(this.color);
/* 60 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\QuietSpecialSmokeBombEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */