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
/*    */ 
/*    */ public class EnemyMiracleEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private float x;
/*    */   private float y;
/*    */   private TextureAtlas.AtlasRegion img;
/*    */   private Color altColor;
/* 25 */   private String sfxUrl = "HEAL_3";
/*    */   
/*    */   public EnemyMiracleEffect(Color setColor, Color altColor, String setSfx) {
/* 28 */     this.img = ImageMaster.CRYSTAL_IMPACT;
/* 29 */     this.x = AbstractCharBoss.boss.hb.cX - this.img.packedWidth / 2.0F;
/* 30 */     this.y = AbstractCharBoss.boss.hb.cY - this.img.packedHeight / 2.0F;
/* 31 */     this.startingDuration = 0.7F;
/* 32 */     this.duration = this.startingDuration;
/* 33 */     this.scale = Settings.scale;
/* 34 */     this.altColor = altColor;
/* 35 */     this.color = setColor.cpy();
/* 36 */     this.color.a = 0.0F;
/* 37 */     this.renderBehind = false;
/* 38 */     this.sfxUrl = setSfx;
/*    */   }
/*    */   
/*    */   public EnemyMiracleEffect() {
/* 42 */     this.img = ImageMaster.CRYSTAL_IMPACT;
/* 43 */     this.x = AbstractCharBoss.boss.hb.cX - this.img.packedWidth / 2.0F;
/* 44 */     this.y = AbstractCharBoss.boss.hb.cY - this.img.packedHeight / 2.0F;
/* 45 */     this.startingDuration = 0.7F;
/* 46 */     this.duration = this.startingDuration;
/* 47 */     this.scale = Settings.scale;
/* 48 */     this.altColor = new Color(1.0F, 0.6F, 0.2F, 0.0F);
/* 49 */     this.color = Settings.GOLD_COLOR.cpy();
/* 50 */     this.color.a = 0.0F;
/* 51 */     this.renderBehind = false;
/*    */   }
/*    */   
/*    */   public void update() {
/* 55 */     if (this.duration == this.startingDuration) {
/* 56 */       CardCrawlGame.sound.playA(this.sfxUrl, 0.5F);
/*    */     }
/*    */     
/* 59 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 60 */     if (this.duration > this.startingDuration / 2.0F) {
/* 61 */       this.color.a = Interpolation.fade.apply(1.0F, 0.01F, this.duration - this.startingDuration / 2.0F) * Settings.scale;
/*    */     } else {
/* 63 */       this.color.a = Interpolation.fade.apply(0.01F, 1.0F, this.duration / this.startingDuration / 2.0F) * Settings.scale;
/*    */     } 
/*    */     
/* 66 */     this.scale = Interpolation.pow5In.apply(2.4F, 0.3F, this.duration / this.startingDuration) * Settings.scale;
/* 67 */     if (this.duration < 0.0F) {
/* 68 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 74 */     sb.setBlendFunction(770, 1);
/* 75 */     this.altColor.a = this.color.a;
/* 76 */     sb.setColor(this.altColor);
/* 77 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 1.1F, this.scale * 1.1F, 0.0F);
/* 78 */     sb.setColor(this.color);
/* 79 */     sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.9F, this.scale * 0.9F, 0.0F);
/* 80 */     sb.setBlendFunction(770, 771);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyMiracleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */