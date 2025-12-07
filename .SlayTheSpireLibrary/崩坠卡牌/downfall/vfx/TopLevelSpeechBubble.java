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
/*    */ import com.megacrit.cardcrawl.vfx.SpeechTextEffect;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TopLevelSpeechBubble
/*    */   extends AbstractGameEffect
/*    */ {
/*    */   public TopLevelSpeechBubble(float x, float y, String msg, boolean isPlayer) {
/* 32 */     this(x, y, 2.0F, msg, isPlayer);
/*    */   }
/*    */ 
/*    */   
/* 36 */   private float shadow_offset = 0.0F;
/* 37 */   private float scaleTimer = 0.3F;
/* 38 */   private Color shadowColor = new Color(0.0F, 0.0F, 0.0F, 0.0F); public TopLevelSpeechBubble(float x, float y, float duration, String msg, boolean isPlayer) {
/* 39 */     float effect_x = -170.0F * Settings.scale;
/* 40 */     if (isPlayer) {
/* 41 */       effect_x = 170.0F * Settings.scale;
/*    */     }
/*    */     
/* 44 */     AbstractDungeon.topLevelEffectsQueue.add(new SpeechTextEffect(x + effect_x, y + 124.0F * Settings.scale, duration, msg, DialogWord.AppearEffect.BUMP_IN));
/* 45 */     if (isPlayer) {
/* 46 */       this.x = x + ADJUST_X;
/*    */     } else {
/* 48 */       this.x = x - ADJUST_X;
/*    */     } 
/*    */     
/* 51 */     this.y = y + ADJUST_Y;
/* 52 */     this.color = new Color(0.8F, 0.9F, 0.9F, 0.0F);
/* 53 */     this.duration = duration;
/* 54 */     this.facingRight = !isPlayer;
/*    */   }
/*    */   
/*    */   public void update() {
/* 58 */     updateScale();
/* 59 */     this.wavyHelper += Gdx.graphics.getDeltaTime() * 4.0F;
/* 60 */     this.wavy_y = MathUtils.sin(this.wavyHelper) * WAVY_DISTANCE;
/* 61 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 62 */     if (this.duration < 0.0F) {
/* 63 */       this.isDone = true;
/*    */     }
/*    */     
/* 66 */     if (this.duration > 0.3F) {
/* 67 */       this.color.a = MathUtils.lerp(this.color.a, 1.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*    */     } else {
/* 69 */       this.color.a = MathUtils.lerp(this.color.a, 0.0F, Gdx.graphics.getDeltaTime() * 12.0F);
/*    */     } 
/*    */     
/* 72 */     this.shadow_offset = MathUtils.lerp(this.shadow_offset, SHADOW_OFFSET, Gdx.graphics.getDeltaTime() * 4.0F);
/*    */   }
/*    */   
/*    */   private void updateScale() {
/* 76 */     this.scaleTimer -= Gdx.graphics.getDeltaTime();
/* 77 */     if (this.scaleTimer < 0.0F) {
/* 78 */       this.scaleTimer = 0.0F;
/*    */     }
/*    */     
/* 81 */     this.scale = Interpolation.swingIn.apply(Settings.scale, Settings.scale / 2.0F, this.scaleTimer / 0.3F);
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 85 */     this.color.a /= 4.0F;
/* 86 */     sb.setColor(this.shadowColor);
/* 87 */     sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F + this.shadow_offset, this.y - 256.0F + this.wavy_y - this.shadow_offset, 256.0F, 256.0F, 512.0F, 512.0F, this.scale, this.scale, this.rotation, 0, 0, 512, 512, this.facingRight, false);
/* 88 */     sb.setColor(this.color);
/* 89 */     sb.draw(ImageMaster.SPEECH_BUBBLE_IMG, this.x - 256.0F, this.y - 256.0F + this.wavy_y, 256.0F, 256.0F, 512.0F, 512.0F, this.scale, this.scale, this.rotation, 0, 0, 512, 512, this.facingRight, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void dispose() {}
/*    */ 
/*    */   
/* 96 */   private static final float SHADOW_OFFSET = 16.0F * Settings.scale;
/* 97 */   private static final float WAVY_DISTANCE = 2.0F * Settings.scale;
/* 98 */   private static final float ADJUST_X = 170.0F * Settings.scale;
/* 99 */   private static final float ADJUST_Y = 116.0F * Settings.scale;
/*    */   private static final int RAW_W = 512;
/*    */   private static final float FADE_TIME = 0.3F;
/*    */   private float x;
/*    */   private float y;
/*    */   private float wavy_y;
/*    */   private float wavyHelper;
/*    */   private boolean facingRight;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\TopLevelSpeechBubble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */