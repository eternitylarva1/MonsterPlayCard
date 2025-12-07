/*     */ package downfall.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.badlogic.gdx.math.Vector2;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import downfall.util.TextureLoader;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PotionThrowEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static final float DUR = 0.6F;
/*     */   private Texture img;
/*     */   private float sX;
/*     */   private float sY;
/*     */   private float cX;
/*     */   private float cY;
/*     */   private float dX;
/*     */   private float dY;
/*     */   private float yOffset;
/*     */   private float bounceHeight;
/*     */   private boolean playedSfx = false;
/*     */   private boolean rain = false;
/*     */   private boolean mute = false;
/*  38 */   private float height = 100.0F;
/*     */   
/*  40 */   private ArrayList<Vector2> previousPos = new ArrayList<>();
/*     */   
/*     */   public PotionThrowEffect(String imgSrc, float srcX, float srcY, float destX, float destY, float scale, float duration, boolean mute, boolean bigHeight) {
/*  43 */     if (this.img == null) {
/*  44 */       this.img = TextureLoader.getTexture(imgSrc);
/*     */     }
/*     */ 
/*     */     
/*  48 */     this.sX = srcX + 40.0F;
/*  49 */     this.sY = srcY - 40.0F;
/*  50 */     this.cX = this.sX;
/*  51 */     this.cY = this.sY;
/*  52 */     this.dX = destX;
/*  53 */     this.dY = destY;
/*  54 */     this.scale = scale;
/*  55 */     this.rotation = 0.0F;
/*  56 */     this.mute = mute;
/*  57 */     this.duration = duration;
/*  58 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */     
/*  60 */     if (bigHeight) this.height = 400.0F;
/*     */ 
/*     */     
/*  63 */     if (this.sY > this.dY) {
/*  64 */       this.bounceHeight = this.height * Settings.scale;
/*     */     } else {
/*  66 */       this.bounceHeight = this.dY - this.sY + this.height * Settings.scale;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  72 */     if (!this.playedSfx && !this.mute) {
/*  73 */       this.playedSfx = true;
/*  74 */       String sound = "POTION_1";
/*  75 */       switch (AbstractDungeon.cardRng.random(0, 2)) {
/*     */         case 0:
/*  77 */           sound = "POTION_1";
/*     */         case 1:
/*  79 */           sound = "POTION_2";
/*     */         case 2:
/*  81 */           sound = "POTION_3";
/*     */           break;
/*     */       } 
/*  84 */       CardCrawlGame.sound.playA(sound, MathUtils.random(-0.1F, 0.1F));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  89 */     this.cX = Interpolation.linear.apply(this.dX, this.sX, this.duration / 0.6F);
/*  90 */     this.cY = Interpolation.linear.apply(this.dY, this.sY, this.duration / 0.6F);
/*  91 */     this.previousPos.add(new Vector2(this.cX + MathUtils.random(-30.0F, 30.0F) * Settings.scale, this.cY + this.yOffset + MathUtils.random(-30.0F, 30.0F) * Settings.scale));
/*  92 */     if (this.previousPos.size() > 20) {
/*  93 */       this.previousPos.remove(this.previousPos.get(0));
/*     */     }
/*     */     
/*  96 */     if (this.dX > this.sX) {
/*  97 */       this.rotation -= Gdx.graphics.getDeltaTime() * 300.0F;
/*     */     } else {
/*  99 */       this.rotation += Gdx.graphics.getDeltaTime() * 300.0F;
/*     */     } 
/*     */     
/* 102 */     if (this.duration > 0.3F) {
/* 103 */       this.color.a = Interpolation.exp5In.apply(1.0F, 0.5F, (this.duration - 0.3F) / 0.3F) * Settings.scale;
/* 104 */       this.yOffset = Interpolation.circleIn.apply(this.bounceHeight, 0.0F, (this.duration - 0.3F) / 0.3F) * Settings.scale;
/*     */     } else {
/* 106 */       this.yOffset = Interpolation.circleOut.apply(0.0F, this.bounceHeight, this.duration / 0.3F) * Settings.scale;
/*     */     } 
/*     */     
/* 109 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 110 */     if (this.duration < 0.0F)
/*     */     {
/* 112 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 119 */     sb.setColor(Color.BLACK);
/*     */     
/* 121 */     sb.draw(this.img, this.cX - (this.img.getWidth() / 2), this.cY - (this.img.getHeight() / 2) + this.yOffset, this.img.getWidth() / 2.0F, this.img.getHeight() / 2.0F, this.img.getWidth(), this.img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, 100, 100, false, false);
/*     */     
/* 123 */     sb.setColor(new Color(1.0F, 1.0F, 1.0F, 100.0F));
/*     */     
/* 125 */     sb.draw(this.img, this.cX - (this.img.getWidth() / 2), this.cY - (this.img.getHeight() / 2) + this.yOffset, this.img.getWidth() / 2.0F, this.img.getHeight() / 2.0F, this.img.getWidth(), this.img.getHeight(), this.scale, this.scale, this.rotation, 0, 0, 100, 100, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 131 */     this.img.dispose();
/* 132 */     this.isDone = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\PotionThrowEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */