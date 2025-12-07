/*    */ package charbosses.vfx;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyCalmParticleEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 27 */   private float dur_div2 = this.duration / 2.0F;
/*    */   
/* 29 */   private float vX = MathUtils.random(-300.0F, -50.0F) * Settings.scale;
/* 30 */   private float vY = MathUtils.random(-200.0F, -100.0F) * Settings.scale; private float x; private float y; public EnemyCalmParticleEffect() {
/* 31 */     if (AbstractCharBoss.boss != null) {
/* 32 */       this.x = AbstractCharBoss.boss.hb.cX + MathUtils.random(100.0F, 160.0F) * Settings.scale - 32.0F;
/* 33 */       this.y = AbstractCharBoss.boss.hb.cY + MathUtils.random(-50.0F, 220.0F) * Settings.scale - 32.0F;
/*    */     } 
/* 35 */     this.renderBehind = MathUtils.randomBoolean(0.2F + this.scale - 0.5F);
/* 36 */     this.dvx = 400.0F * Settings.scale * this.scale;
/* 37 */     this.dvy = 100.0F * Settings.scale;
/*    */   }
/*    */   private float dvy; private float dvx;
/*    */   public void update() {
/* 41 */     this.x += this.vX * Gdx.graphics.getDeltaTime();
/* 42 */     this.y += this.vY * Gdx.graphics.getDeltaTime();
/* 43 */     this.vY += Gdx.graphics.getDeltaTime() * this.dvy;
/* 44 */     this.vX -= Gdx.graphics.getDeltaTime() * this.dvx;
/* 45 */     this.rotation = -(57.295776F * MathUtils.atan2(this.vX, this.vY)) - 0.0F;
/* 46 */     if (this.duration > this.dur_div2) {
/* 47 */       this.color.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - this.dur_div2) / this.dur_div2);
/*    */     } else {
/* 49 */       this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / this.dur_div2);
/*    */     } 
/*    */     
/* 52 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 53 */     if (this.duration < 0.0F) {
/* 54 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 60 */     sb.setColor(this.color);
/* 61 */     if (AbstractCharBoss.boss != null) {
/* 62 */       sb.setBlendFunction(770, 1);
/* 63 */       sb.draw(ImageMaster.FROST_ACTIVATE_VFX_1, this.x, this.y, 32.0F, 32.0F, 25.0F, 128.0F, this.scale, this.scale + (this.dur_div2 * 0.4F - this.duration) * Settings.scale, this.rotation, 0, 0, 64, 64, false, false);
/* 64 */       sb.setBlendFunction(770, 771);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyCalmParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */