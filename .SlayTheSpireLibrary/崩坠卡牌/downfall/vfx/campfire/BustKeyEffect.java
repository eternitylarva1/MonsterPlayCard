/*    */ package downfall.vfx.campfire;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.rooms.RestRoom;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class BustKeyEffect extends AbstractGameEffect {
/*    */   private static final float DURATION = 2.0F;
/*    */   private boolean hasBusted;
/*    */   private Color screenColor;
/*    */   
/*    */   public BustKeyEffect() {
/* 20 */     this.hasBusted = false;
/* 21 */     this.screenColor = AbstractDungeon.fadeColor.cpy();
/* 22 */     this.duration = 2.0F;
/* 23 */     this.screenColor.a = 0.0F;
/* 24 */     ((RestRoom)AbstractDungeon.getCurrRoom()).cutFireSound();
/*    */   }
/*    */   
/*    */   public void update() {
/* 28 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 29 */     updateBlackScreenColor();
/* 30 */     if (this.duration < 1.0F && !this.hasBusted) {
/* 31 */       this.hasBusted = true;
/* 32 */       CardCrawlGame.sound.playA("BLOCK_BREAK", 1.5F);
/*    */     } 
/*    */     
/* 35 */     if (this.duration < 0.0F) {
/* 36 */       this.isDone = true;
/* 37 */       if (AbstractDungeon.getCurrRoom() instanceof RestRoom) {
/* 38 */         ((RestRoom)AbstractDungeon.getCurrRoom()).fadeIn();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void updateBlackScreenColor() {
/* 45 */     if (this.duration > 1.5F) {
/* 46 */       this.screenColor.a = Interpolation.fade.apply(1.0F, 0.0F, (this.duration - 1.5F) * 2.0F);
/* 47 */     } else if (this.duration < 1.0F) {
/* 48 */       this.screenColor.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration);
/*    */     } else {
/* 50 */       this.screenColor.a = 1.0F;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 55 */     sb.setColor(this.screenColor);
/* 56 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*    */   }
/*    */   
/*    */   public void dispose() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\campfire\BustKeyEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */