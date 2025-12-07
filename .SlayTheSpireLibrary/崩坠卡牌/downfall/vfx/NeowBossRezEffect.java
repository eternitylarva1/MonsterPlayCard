/*    */ package downfall.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NeowBossRezEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public float cX;
/*    */   public float cY;
/*    */   public float angle;
/*    */   public boolean timed = false;
/* 19 */   public Color borderColor = Color.CYAN;
/*    */   
/*    */   public NeowBossRezEffect(float startX, float startY) {
/* 22 */     this.cX = startX;
/* 23 */     this.cY = startY;
/*    */     
/* 25 */     this.renderBehind = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 31 */     AbstractDungeon.effectsQueue.add(new NeowRezFlareParticleEffect(this.cX + MathUtils.random(-100.0F, 100.0F), this.cY + MathUtils.random(-100.0F, 100.0F), this.borderColor, this.angle, this));
/* 32 */     AbstractDungeon.effectsQueue.add(new NeowRezFlareParticleEffect(this.cX + MathUtils.random(-100.0F, 100.0F), this.cY + MathUtils.random(-100.0F, 100.0F), this.borderColor, this.angle, this));
/* 33 */     AbstractDungeon.effectsQueue.add(new NeowRezFlareParticleEffect(this.cX + MathUtils.random(-100.0F, 100.0F), this.cY + MathUtils.random(-100.0F, 100.0F), this.borderColor, this.angle, this));
/*    */     
/* 35 */     if (this.timed) {
/*    */       
/* 37 */       this.duration -= Gdx.graphics.getDeltaTime();
/* 38 */       if (this.duration < 0.0F) {
/* 39 */         this.isDone = true;
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void end() {
/* 48 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\NeowBossRezEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */