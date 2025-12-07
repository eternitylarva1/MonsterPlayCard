/*    */ package charbosses.vfx;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyRainbowCardEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   float x;
/*    */   float y;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   
/*    */   public EnemyRainbowCardEffect() {
/* 25 */     this.img = ImageMaster.CRYSTAL_IMPACT;
/* 26 */     this.x = AbstractCharBoss.boss.hb.cX - this.img.packedWidth / 2.0F;
/* 27 */     this.y = AbstractCharBoss.boss.hb.cY - this.img.packedHeight / 2.0F;
/* 28 */     this.startingDuration = 1.5F;
/* 29 */     this.duration = this.startingDuration;
/* 30 */     this.scale = Settings.scale;
/* 31 */     this.color = Color.CYAN.cpy();
/* 32 */     this.color.a = 0.0F;
/* 33 */     this.renderBehind = true;
/*    */   }
/*    */   
/*    */   public void update() {
/* 37 */     if (this.duration == this.startingDuration) {
/* 38 */       CardCrawlGame.sound.playA("HEAL_3", 0.5F);
/*    */     }
/*    */     
/* 41 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 42 */     if (this.duration > this.startingDuration / 2.0F) {
/* 43 */       this.color.a = Interpolation.fade.apply(1.0F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;
/*    */     } else {
/* 45 */       this.color.a = Interpolation.fade.apply(0.01F, 1.0F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*    */     } 
/*    */     
/* 48 */     this.scale = Interpolation.elasticIn.apply(4.0F, 0.01F, this.duration / this.startingDuration) * Settings.scale;
/* 49 */     if (this.duration < 0.0F) {
/* 50 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 56 */     sb.setColor(new Color(1.0F, 0.2F, 0.2F, this.color.a));
/* 57 */     sb.setBlendFunction(770, 1);
/* 58 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 1.15F, this.scale * 1.15F, 0.0F);
/* 59 */     sb.setColor(new Color(1.0F, 1.0F, 0.2F, this.color.a));
/* 60 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, 0.0F);
/* 61 */     sb.setColor(new Color(0.2F, 1.0F, 0.2F, this.color.a));
/* 62 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.85F, this.scale * 0.85F, 0.0F);
/* 63 */     sb.setColor(new Color(0.2F, 0.7F, 1.0F, this.color.a));
/* 64 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.7F, this.scale * 0.7F, 0.0F);
/* 65 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyRainbowCardEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */