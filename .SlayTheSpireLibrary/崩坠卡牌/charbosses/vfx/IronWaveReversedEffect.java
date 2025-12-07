/*    */ package charbosses.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.IronWaveParticle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IronWaveReversedEffect
/*    */   extends AbstractGameEffect
/*    */ {
/* 19 */   private float waveTimer = 0.0F;
/*    */   private float x;
/*    */   private float y;
/*    */   private float cX;
/*    */   private static final float WAVE_INTERVAL = 0.03F;
/*    */   
/*    */   public IronWaveReversedEffect(float x, float y, float cX) {
/* 26 */     this.x = x + 340.0F * Settings.scale;
/* 27 */     this.y = y - 20.0F * Settings.scale;
/* 28 */     this.cX = cX + 40.0F * Settings.scale;
/*    */   }
/*    */   
/*    */   public void update() {
/* 32 */     this.waveTimer -= Gdx.graphics.getDeltaTime();
/* 33 */     if (this.waveTimer < 0.0F) {
/* 34 */       this.waveTimer = 0.03F;
/* 35 */       this.x -= 100.0F * Settings.scale;
/* 36 */       this.y -= 15.0F * Settings.scale;
/* 37 */       AbstractDungeon.effectsQueue.add(new IronWaveParticle(this.x, this.y));
/* 38 */       if (this.x < this.cX) {
/* 39 */         this.isDone = true;
/* 40 */         CardCrawlGame.sound.playA("ATTACK_DAGGER_6", -0.3F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\IronWaveReversedEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */