/*    */ package charbosses.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyRefreshEnergyEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private static final float EFFECT_DUR = 0.4F;
/* 24 */   private float scale = Settings.scale / 1.2F;
/* 25 */   private Color color = new Color(1.0F, 1.0F, 1.0F, 1.0F);
/* 26 */   private TextureAtlas.AtlasRegion img = ImageMaster.WHITE_RING;
/* 27 */   private float x = 1550.0F * Settings.scale + this.img.packedWidth / 2.0F;
/* 28 */   private float y = 720.0F * Settings.scale + this.img.packedHeight / 2.0F;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 34 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 35 */     this.scale *= 1.0F + Gdx.graphics.getDeltaTime() * 2.5F;
/* 36 */     this.color.a = Interpolation.fade.apply(0.0F, 0.75F, this.duration / 0.4F);
/* 37 */     if (this.color.a < 0.0F) {
/* 38 */       this.color.a = 0.0F;
/*    */     }
/*    */     
/* 41 */     if (this.duration < 0.0F) {
/* 42 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 48 */     sb.setColor(this.color);
/* 49 */     sb.setBlendFunction(770, 1);
/* 50 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 1.5F, this.scale * 1.5F, this.rotation);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 61 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyRefreshEnergyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */