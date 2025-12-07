/*    */ package downfall.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class RainingSoulsEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private int amount;
/*    */   private int min;
/*    */   private int max;
/*    */   private float staggerTimer;
/*    */   private boolean playerCentered;
/*    */   
/*    */   public RainingSoulsEffect(int amount) {
/* 19 */     this.staggerTimer = 0.0F;
/* 20 */     this.amount = amount;
/* 21 */     this.playerCentered = false;
/* 22 */     if (amount < 100) {
/* 23 */       this.min = 1;
/* 24 */       this.max = 7;
/*    */     } else {
/* 26 */       this.min = 3;
/* 27 */       this.max = 18;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public RainingSoulsEffect(int amount, boolean centerOnPlayer) {
/* 33 */     this(amount);
/* 34 */     this.playerCentered = centerOnPlayer;
/*    */   }
/*    */   
/*    */   public void update() {
/* 38 */     this.staggerTimer -= Gdx.graphics.getDeltaTime();
/* 39 */     if (this.staggerTimer < 0.0F) {
/* 40 */       int goldToSpawn = MathUtils.random(this.min, this.max);
/* 41 */       if (goldToSpawn <= this.amount) {
/* 42 */         this.amount -= goldToSpawn;
/*    */       } else {
/* 44 */         goldToSpawn = this.amount;
/* 45 */         this.isDone = true;
/*    */       } 
/*    */       
/* 48 */       for (int i = 0; i < goldToSpawn; i++) {
/* 49 */         AbstractDungeon.effectsQueue.add(new TouchPickupSouls(this.playerCentered));
/*    */       }
/*    */       
/* 52 */       this.staggerTimer = MathUtils.random(0.3F);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {}
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\RainingSoulsEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */