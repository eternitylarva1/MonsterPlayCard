/*    */ package charbosses.vfx;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyStanceAuraEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public static boolean switcher = true;
/* 24 */   private TextureAtlas.AtlasRegion img = ImageMaster.EXHAUST_L; private float vY;
/*    */   
/*    */   public EnemyStanceAuraEffect(String stanceId) {
/* 27 */     if (stanceId.equals("Wrath")) {
/* 28 */       this.color = new Color(MathUtils.random(0.6F, 0.7F), MathUtils.random(0.0F, 0.1F), MathUtils.random(0.1F, 0.2F), 0.0F);
/* 29 */     } else if (stanceId.equals("Calm")) {
/* 30 */       this.color = new Color(MathUtils.random(0.5F, 0.55F), MathUtils.random(0.6F, 0.7F), 1.0F, 0.0F);
/*    */     } else {
/* 32 */       this.color = new Color(MathUtils.random(0.6F, 0.7F), MathUtils.random(0.0F, 0.1F), MathUtils.random(0.6F, 0.7F), 0.0F);
/*    */     } 
/* 34 */     if (AbstractCharBoss.boss != null) {
/* 35 */       this.x = AbstractCharBoss.boss.hb.cX + MathUtils.random(-AbstractCharBoss.boss.hb.width / 16.0F, AbstractCharBoss.boss.hb.width / 16.0F);
/* 36 */       this.y = AbstractCharBoss.boss.hb.cY + MathUtils.random(-AbstractCharBoss.boss.hb.height / 16.0F, AbstractCharBoss.boss.hb.height / 12.0F);
/*    */     } 
/* 38 */     this.x -= this.img.packedWidth / 2.0F;
/* 39 */     this.y -= this.img.packedHeight / 2.0F;
/* 40 */     switcher = !switcher;
/* 41 */     this.renderBehind = true;
/* 42 */     this.rotation = MathUtils.random(360.0F);
/* 43 */     if (switcher) {
/* 44 */       this.renderBehind = true;
/* 45 */       this.vY = MathUtils.random(0.0F, 40.0F);
/*    */     } else {
/* 47 */       this.renderBehind = false;
/* 48 */       this.vY = MathUtils.random(0.0F, -40.0F);
/*    */     } 
/*    */   }
/*    */   private float y; private float x;
/*    */   
/*    */   public void update() {
/* 54 */     if (this.duration > 1.0F) {
/* 55 */       this.color.a = Interpolation.fade.apply(0.3F, 0.0F, this.duration - 1.0F);
/*    */     } else {
/* 57 */       this.color.a = Interpolation.fade.apply(0.0F, 0.3F, this.duration);
/*    */     } 
/*    */     
/* 60 */     this.rotation += Gdx.graphics.getDeltaTime() * this.vY;
/* 61 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 62 */     if (this.duration < 0.0F) {
/* 63 */       this.isDone = true;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 69 */     sb.setColor(this.color);
/* 70 */     if (AbstractCharBoss.boss != null) {
/* 71 */       sb.setBlendFunction(770, 1);
/* 72 */       sb.draw((TextureRegion)this.img, this.x, this.y, this.img.packedWidth / 2.0F, this.img.packedHeight / 2.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale, this.rotation);
/* 73 */       sb.setBlendFunction(770, 771);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyStanceAuraEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */