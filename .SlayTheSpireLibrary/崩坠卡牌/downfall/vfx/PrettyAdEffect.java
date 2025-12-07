/*    */ package downfall.vfx;
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
/*    */ public class PrettyAdEffect extends AbstractGameEffect {
/*    */   private float effectDuration;
/*    */   private float x;
/*    */   private float y;
/*    */   private float vY;
/*    */   private float alpha;
/*    */   private float targetScale;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public PrettyAdEffect(float x, float y) {
/* 24 */     this.img = ImageMaster.ROOM_SHINE_2;
/* 25 */     this.effectDuration = MathUtils.random(1.0F, 3.0F);
/* 26 */     this.duration = this.effectDuration;
/* 27 */     this.startingDuration = this.effectDuration;
/* 28 */     this.x = x;
/* 29 */     this.y = y;
/* 30 */     this.vY = MathUtils.random(10.0F, 50.0F) * Settings.scale;
/* 31 */     this.alpha = MathUtils.random(0.7F, 1.0F);
/* 32 */     this.color = new Color(1.0F, 1.0F, MathUtils.random(0.6F, 0.9F), this.alpha);
/* 33 */     this.scale = 0.01F;
/* 34 */     this.targetScale = MathUtils.random(0.5F, 1.2F);
/* 35 */     this.rotation = MathUtils.random(-3.0F, 3.0F);
/*    */   }
/*    */   
/*    */   public void update() {
/* 39 */     if (this.vY != 0.0F) {
/* 40 */       this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 41 */       MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() * 10.0F);
/* 42 */       if (this.vY < 0.5F) {
/* 43 */         this.vY = 0.0F;
/*    */       }
/*    */     } 
/*    */     
/* 47 */     float t = (this.effectDuration - this.duration) * 2.0F;
/* 48 */     if (t > 1.0F) {
/* 49 */       t = 1.0F;
/*    */     }
/*    */     
/* 52 */     float tmp = Interpolation.bounceOut.apply(0.01F, this.targetScale, t);
/* 53 */     this.scale = tmp * tmp * Settings.scale;
/* 54 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 55 */     if (this.duration < 0.0F) {
/* 56 */       this.isDone = true;
/* 57 */     } else if (this.duration < this.effectDuration / 2.0F) {
/* 58 */       this.color.a = Interpolation.exp5In.apply(0.0F, this.alpha, this.duration / this.effectDuration / 2.0F);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 64 */     sb.setColor(this.color);
/* 65 */     sb.setBlendFunction(770, 1);
/* 66 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * MathUtils.random(0.9F, 1.1F), this.scale * MathUtils.random(0.7F, 1.3F), this.rotation);
/* 67 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\PrettyAdEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */