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
/*    */ public class EnemyWrathStanceChangeParticle
/*    */   extends AbstractGameEffect
/*    */ {
/* 22 */   private TextureAtlas.AtlasRegion img = ImageMaster.STRIKE_LINE;
/*    */ 
/*    */ 
/*    */   
/* 26 */   private float x = MathUtils.random(-30.0F, 30.0F) * Settings.scale - this.img.packedWidth / 2.0F;
/* 27 */   private float y = Settings.HEIGHT / 2.0F + MathUtils.random(-150.0F, 150.0F) * Settings.scale - this.img.packedHeight / 2.0F;
/*    */   
/* 29 */   private float delayTimer = MathUtils.random(0.5F);
/*    */ 
/*    */   
/*    */   public EnemyWrathStanceChangeParticle(float playerX) {}
/*    */   
/*    */   public void update() {
/* 35 */     if (this.delayTimer > 0.0F) {
/* 36 */       this.delayTimer -= Gdx.graphics.getDeltaTime();
/*    */     } else {
/* 38 */       this.duration -= Gdx.graphics.getDeltaTime();
/* 39 */       if (this.duration < 0.0F) {
/* 40 */         this.isDone = true;
/*    */       }
/* 42 */       else if (this.duration > this.startingDuration / 2.0F) {
/* 43 */         this.color.a = Interpolation.pow3In.apply(0.6F, 0.0F, (this.duration - this.startingDuration / 2.0F) / this.startingDuration / 2.0F);
/*    */       } else {
/* 45 */         this.color.a = Interpolation.fade.apply(0.0F, 0.6F, this.duration / this.startingDuration / 2.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 53 */     if (this.delayTimer <= 0.0F) {
/* 54 */       sb.setColor(this.color);
/* 55 */       if (AbstractCharBoss.boss != null) {
/* 56 */         sb.setBlendFunction(770, 1);
/* 57 */         sb.draw((TextureRegion)this.img, AbstractCharBoss.boss.hb.cX + this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * MathUtils.random(2.9F, 3.1F), this.scale * MathUtils.random(0.95F, 1.05F), this.rotation);
/* 58 */         sb.setBlendFunction(770, 771);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyWrathStanceChangeParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */