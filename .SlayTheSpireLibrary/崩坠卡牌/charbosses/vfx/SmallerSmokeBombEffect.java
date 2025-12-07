/*    */ package charbosses.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class SmallerSmokeBombEffect extends AbstractGameEffect {
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public SmallerSmokeBombEffect(float x, float y) {
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/* 16 */     this.duration = 0.2F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 20 */     if (this.duration == 0.2F) {
/* 21 */       CardCrawlGame.sound.play("ATTACK_WHIFF_2");
/*    */       
/* 23 */       for (int i = 0; i < 90; i++) {
/* 24 */         AbstractDungeon.effectsQueue.add(new ReasonableAmountOfSmoke(this.x, this.y));
/*    */       }
/*    */     } 
/*    */     
/* 28 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 29 */     if (this.duration < 0.0F) {
/* 30 */       CardCrawlGame.sound.play("APPEAR");
/* 31 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\vfx\SmallerSmokeBombEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */