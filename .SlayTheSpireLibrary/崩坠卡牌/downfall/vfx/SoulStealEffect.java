/*    */ package downfall.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class SoulStealEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private float tX;
/*    */   private float tY;
/*    */   
/*    */   public SoulStealEffect(float sX, float sY, float tX, float tY) {
/* 17 */     this.x = sX;
/* 18 */     this.y = sY;
/* 19 */     this.tX = tX;
/* 20 */     this.tY = tY;
/* 21 */     this.scale = 0.12F;
/* 22 */     this.duration = 0.5F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 26 */     this.scale -= Gdx.graphics.getDeltaTime();
/* 27 */     if (this.scale < 0.0F) {
/* 28 */       AbstractDungeon.effectsQueue.add(new SoulStealParticle(this.x + MathUtils.random(60.0F, -60.0F) * Settings.scale, this.y + MathUtils.random(60.0F, -60.0F) * Settings.scale, this.tX, this.tY, AbstractDungeon.player.flipHorizontal));
/* 29 */       this.scale = 0.04F;
/*    */     } 
/*    */     
/* 32 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 33 */     if (this.duration < 0.0F)
/* 34 */       this.isDone = true; 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\SoulStealEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */