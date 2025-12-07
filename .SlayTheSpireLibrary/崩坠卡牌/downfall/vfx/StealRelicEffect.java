/*    */ package downfall.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StealRelicEffect
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   private static final int flighttime = 40;
/*    */   private static final int dispersalspeed = 100;
/*    */   private int frames;
/*    */   private AbstractCreature ac;
/*    */   private AbstractRelic ar;
/*    */   private float startX;
/*    */   private float startY;
/*    */   private float currentX;
/*    */   private float currentY;
/*    */   private float opacity;
/*    */   
/*    */   public StealRelicEffect(AbstractRelic ar, AbstractCreature ac) {
/* 28 */     this.ar = ar;
/* 29 */     this.ac = ac;
/* 30 */     this.startX = ar.currentX;
/* 31 */     this.startY = ar.currentY;
/* 32 */     this.currentX = ar.currentX;
/* 33 */     this.currentY = ar.currentY;
/* 34 */     this.opacity = 1.0F;
/* 35 */     this.frames = -40;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 40 */     sb.setColor(1.0F, 1.0F, 1.0F, this.opacity);
/* 41 */     sb.draw(this.ar.img, this.currentX - 64.0F, this.currentY - 64.0F, 64.0F, 64.0F, 128.0F, 128.0F, Settings.scale, Settings.scale, this.rotation, 0, 0, 128, 128, false, false);
/* 42 */     sb.setColor(Color.WHITE);
/*    */   }
/*    */   
/*    */   public void update() {
/* 46 */     if (this.frames < 40) {
/* 47 */       this.currentX = this.startX + sigmoid(this.ac.hb.cX - this.startX, 0.15F, this.frames);
/* 48 */       this.currentY = this.startY + sigmoid(this.ac.hb.y + this.ac.hb.height - this.startY, 0.15F, this.frames++);
/* 49 */     } else if (this.opacity > 0.0F) {
/* 50 */       this.opacity -= 0.01F;
/* 51 */       if (this.opacity < 0.0F)
/* 52 */         this.opacity = 0.0F; 
/*    */     } else {
/* 54 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void dispose() {}
/*    */ 
/*    */   
/*    */   private float sigmoid(float endvalue, float steepness, float curval) {
/* 64 */     return endvalue / (1.0F + (float)Math.pow(Math.E, (-steepness * curval)));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\StealRelicEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */