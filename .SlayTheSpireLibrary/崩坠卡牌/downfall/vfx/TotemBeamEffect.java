/*    */ package downfall.vfx;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ public class TotemBeamEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private static final float DUR = 0.5F;
/*    */   private static TextureAtlas.AtlasRegion img;
/*    */   private float sX;
/*    */   private float sY;
/*    */   private float sX2;
/*    */   
/*    */   public TotemBeamEffect(float sX, float sY, float dX, float dY, Color color, float sX2, float sY2) {
/* 27 */     if (img == null) {
/* 28 */       img = ImageMaster.vfxAtlas.findRegion("combat/laserThin");
/*    */     }
/*    */     
/* 31 */     this.sX = sX;
/* 32 */     this.sY = sY;
/*    */     
/* 34 */     this.sX2 = sX2;
/* 35 */     this.sY2 = sY2;
/* 36 */     this.dX = dX;
/* 37 */     this.dY = dY;
/* 38 */     this.dst = Vector2.dst(this.sX, this.sY, this.dX, this.dY) / Settings.scale;
/* 39 */     this.dst2 = Vector2.dst(this.sX2, this.sY2, this.dX, this.dY) / Settings.scale;
/* 40 */     this.color = color;
/* 41 */     this.duration = 0.5F;
/* 42 */     this.startingDuration = 0.5F;
/* 43 */     this.rotation = MathUtils.atan2(dX - sX, dY - sY);
/* 44 */     this.rotation *= 57.295776F;
/* 45 */     this.rotation = -this.rotation + 90.0F;
/*    */   }
/*    */   private float sY2; private float dX; private float dY; private float dst; private float dst2;
/*    */   public void update() {
/* 49 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 50 */     if (this.duration > this.startingDuration / 2.0F) {
/* 51 */       this.color.a = Interpolation.pow2In.apply(1.0F, 0.0F, (this.duration - 0.25F) * 4.0F);
/*    */     } else {
/* 53 */       this.color.a = Interpolation.bounceIn.apply(0.0F, 1.0F, this.duration * 4.0F);
/*    */     } 
/*    */     
/* 56 */     if (this.duration < 0.0F) {
/* 57 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 63 */     sb.setBlendFunction(770, 1);
/* 64 */     sb.setColor(this.color);
/* 65 */     sb.draw((TextureRegion)img, this.sX2, this.sY2 - img.packedHeight / 2.0F + 10.0F * Settings.scale, 0.0F, img.packedHeight / 2.0F, this.dst2, 50.0F, this.scale + MathUtils.random(-0.01F, 0.01F), this.scale, this.rotation);
/* 66 */     sb.draw((TextureRegion)img, this.sX, this.sY - img.packedHeight / 2.0F + 10.0F * Settings.scale, 0.0F, img.packedHeight / 2.0F, this.dst, 50.0F, this.scale + MathUtils.random(-0.01F, 0.01F), this.scale, this.rotation);
/* 67 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\TotemBeamEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */