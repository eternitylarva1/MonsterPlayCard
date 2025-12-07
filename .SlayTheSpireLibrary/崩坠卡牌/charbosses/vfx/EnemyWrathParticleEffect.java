/*    */ package charbosses.vfx;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
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
/*    */ public class EnemyWrathParticleEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 24 */   private TextureAtlas.AtlasRegion img = ImageMaster.GLOW_SPARK;
/*    */ 
/*    */   
/* 27 */   private float dur_div2 = this.duration / 2.0F; private float vY;
/*    */   public EnemyWrathParticleEffect() {
/* 29 */     if (AbstractCharBoss.boss != null) {
/* 30 */       this.x = AbstractCharBoss.boss.hb.cX + MathUtils.random(-AbstractCharBoss.boss.hb.width / 2.0F - 30.0F * Settings.scale, AbstractCharBoss.boss.hb.width / 2.0F + 30.0F * Settings.scale);
/* 31 */       this.y = AbstractCharBoss.boss.hb.cY + MathUtils.random(-AbstractCharBoss.boss.hb.height / 2.0F - -10.0F * Settings.scale, AbstractCharBoss.boss.hb.height / 2.0F - 10.0F * Settings.scale);
/*    */     } 
/* 33 */     this.x -= this.img.packedWidth / 2.0F;
/* 34 */     this.y -= this.img.packedHeight / 2.0F;
/* 35 */     this.renderBehind = MathUtils.randomBoolean(0.2F + this.scale - 0.5F);
/* 36 */     this.rotation = MathUtils.random(-8.0F, 8.0F);
/*    */   }
/*    */   private float y; private float x;
/*    */   public void update() {
/* 40 */     if (this.duration > this.dur_div2) {
/* 41 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
/*    */     } else {
/* 43 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.dur_div2);
/*    */     } 
/*    */     
/* 46 */     this.vY += Gdx.graphics.getDeltaTime() * 40.0F * Settings.scale;
/* 47 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 48 */     if (this.duration < 0.0F) {
/* 49 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 55 */     sb.setColor(this.color);
/* 56 */     if (AbstractCharBoss.boss != null) {
/* 57 */       sb.setBlendFunction(770, 1);
/* 58 */       sb.draw((TextureRegion)this.img, this.x, this.y + this.vY, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.8F, (0.1F + (this.dur_div2 * 2.0F - this.duration) * 2.0F * this.scale) * Settings.scale, this.rotation);
/* 59 */       sb.setBlendFunction(770, 771);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyWrathParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */