/*    */ package charbosses.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.stance.DivinityStanceChangeParticle;
/*    */ 
/*    */ public class EnemyStanceChangeParticleGenerator
/*    */   extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   private String stanceId;
/*    */   
/*    */   public EnemyStanceChangeParticleGenerator(float x, float y, String stanceId) {
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/* 18 */     this.stanceId = stanceId;
/*    */   }
/*    */   
/*    */   public void update() {
/* 22 */     if (!this.stanceId.equals("Calm"))
/*    */     {
/* 24 */       if (this.stanceId.equals("Divinity")) {
/* 25 */         for (int i = 0; i < 20; i++) {
/* 26 */           AbstractDungeon.effectsQueue.add(new DivinityStanceChangeParticle(Color.PINK, this.x, this.y));
/*    */         }
/* 28 */       } else if (this.stanceId.equals("Wrath")) {
/* 29 */         for (int i = 0; i < 10; i++) {
/* 30 */           AbstractDungeon.effectsQueue.add(new EnemyWrathStanceChangeParticle(this.x));
/*    */         }
/* 32 */       } else if (this.stanceId.equals("Neutral")) {
/* 33 */         for (int i = 0; i < 20; i++) {
/* 34 */           AbstractDungeon.effectsQueue.add(new DivinityStanceChangeParticle(Color.WHITE, this.x, this.y));
/*    */         }
/*    */       } else {
/* 37 */         for (int i = 0; i < 20; i++) {
/* 38 */           AbstractDungeon.effectsQueue.add(new DivinityStanceChangeParticle(Color.WHITE, this.x, this.y));
/*    */         }
/*    */       } 
/*    */     }
/*    */     
/* 43 */     this.isDone = true;
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\EnemyStanceChangeParticleGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */