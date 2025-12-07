/*    */ package downfall.vfx;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.Interpolation;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.ui.DialogWord;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ 
/*    */ public class TopLevelInfiniteSpeechBubble
/*    */   extends AbstractGameEffect {
/* 16 */   private float shadow_offset = 0.0F;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   private float scaleTimer = 0.3F;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public TopLevelInfiniteSpeechBubble(float x, float y, String msg) {
/* 34 */     this.textEffect = new CustomSpeechTextEffect(x - 170.0F * Settings.scale, y + 124.0F * Settings.scale, Float.MAX_VALUE, msg, DialogWord.AppearEffect.BUMP_IN);
/* 35 */     AbstractDungeon.topLevelEffectsQueue.add(this.textEffect);
/* 36 */     this.x = x - ADJUST_X;
/* 37 */     this.y = y + ADJUST_Y;
/* 38 */     this.scaleTimer = 0.3F;
/* 39 */     this.color = new Color(0.8F, 0.9F, 0.9F, 0.0F);
/* 40 */     this.duration = Float.MAX_VALUE;
/* 41 */     this.facingRight = true;
/*    */   }
/*    */   
/*    */   public void dismiss() {
/* 45 */     this.duration = 0.3F;
/* 46 */     this.textEffect.duration = 0.3F;
/*    */   }
/*    */   
/*    */   public void update() {
/* 50 */     updateScale();
/* 51 */     this.wavyHelper += Gdx.graphics.getDeltaTime() * 4.0F;
/* 52 */     this.wavy_y = MathUtils.sin(this.wavyHelper) * WAVY_DISTANCE;
/* 53 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 54 */     if (this.duration < 0.0F) {
/* 55 */       this.isDone = true;
/*    */     }
/*    */     
/* 58 */     if (this.duration > 0.3F) {
/* 59 */       this.color.a = MathUtils.lerp(this.color.a, 1.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*    */     } else {
/* 61 */       this.color.a = MathUtils.lerp(this.color.a, 0.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*    */     } 
/*    */     
/* 64 */     this.shadow_offset = MathUtils.lerp(this.shadow_offset, SHADOW_OFFSET, Gdx.graphics.getDeltaTime() * 4.0F);
/*    */   }
/*    */   
/*    */   private void updateScale() {
/* 68 */     this.scaleTimer -= Gdx.graphics.getDeltaTime();
/* 69 */     if (this.scaleTimer < 0.0F) {
/* 70 */       this.scaleTimer = 0.0F;
/*    */     }
/*    */     
/* 73 */     this.scale_x = Interpolation.circleIn.apply(Settings.scale, Settings.scale * 0.5F, this.scaleTimer / 0.3F);
/* 74 */     this.scale_y = Interpolation.swingIn.apply(Settings.scale, Settings.scale * 0.8F, this.scaleTimer / 0.3F);
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 78 */     if (!AbstractDungeon.isScreenUp) {
/* 79 */       sb.setColor(new Color(0.0F, 0.0F, 0.0F, this.color.a / 4.0F));
/* 80 */       sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F + this.shadow_offset, this.y - 256.0F + this.wavy_y - this.shadow_offset, 256.0F, 256.0F, 512.0F, 512.0F, this.scale_x, this.scale_y, this.rotation, 0, 0, 512, 512, this.facingRight, false);
/* 81 */       sb.setColor(this.color);
/* 82 */       sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F, this.y - 256.0F + this.wavy_y, 256.0F, 256.0F, 512.0F, 512.0F, this.scale_x, this.scale_y, this.rotation, 0, 0, 512, 512, this.facingRight, false);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void dispose() {
/* 87 */     this.textEffect.dispose();
/*    */   }
/*    */ 
/*    */   
/* 91 */   private static final float SHADOW_OFFSET = 16.0F * Settings.scale;
/* 92 */   private static final float WAVY_DISTANCE = 2.0F * Settings.scale;
/* 93 */   private static final float ADJUST_X = 170.0F * Settings.scale;
/* 94 */   private static final float ADJUST_Y = 116.0F * Settings.scale;
/*    */   private static final int RAW_W = 512;
/*    */   private float x;
/*    */   private float y;
/*    */   private float scale_x;
/*    */   private float scale_y;
/*    */   private float wavy_y;
/*    */   private float wavyHelper;
/*    */   private static final float SCALE_TIME = 0.3F;
/*    */   private boolean facingRight;
/*    */   private static final float FADE_TIME = 0.3F;
/*    */   public CustomSpeechTextEffect textEffect;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\TopLevelInfiniteSpeechBubble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */