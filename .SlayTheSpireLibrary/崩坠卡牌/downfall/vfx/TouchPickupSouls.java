/*     */ package downfall.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.ShineLinesEffect;
/*     */ import downfall.downfallMod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TouchPickupSouls
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static final float RAW_IMG_WIDTH = 32.0F;
/*     */   
/*     */   public TouchPickupSouls() {
/*  38 */     this.isPickupable = false;
/*  39 */     this.pickedup = false;
/*  40 */     this.img = downfallMod.soulsImage;
/*     */     
/*  42 */     this.y = MathUtils.random(0.0F, Settings.HEIGHT * -0.15F);
/*  43 */     this.x = MathUtils.random(Settings.WIDTH * 0.05F, Settings.WIDTH * 0.95F) - this.img.getWidth() / 2.0F;
/*  44 */     this.landingY = MathUtils.random(AbstractDungeon.floorY + Settings.HEIGHT * 0.25F, AbstractDungeon.floorY + Settings.HEIGHT * 0.38F);
/*  45 */     this.rotation = 180.0F + MathUtils.random(-30.0F, 30.0F);
/*  46 */     this.flip = MathUtils.randomBoolean();
/*  47 */     this.scale = Settings.scale;
/*     */     
/*  49 */     this.vY = MathUtils.random(-1.0F, 1.0F) + (this.y - this.landingY) / 40.0F;
/*  50 */     this.vX = MathUtils.random(-0.5F, 0.5F);
/*     */     
/*  52 */     this.color = Color.WHITE.cpy();
/*  53 */     this.color.a = 0.8F;
/*     */   }
/*     */   
/*     */   public TouchPickupSouls(boolean centerOnPlayer) {
/*  57 */     this();
/*  58 */     if (centerOnPlayer) {
/*  59 */       this.x = MathUtils.random(AbstractDungeon.player.drawX - AbstractDungeon.player.hb_w, AbstractDungeon.player.drawX + AbstractDungeon.player.hb_w);
/*     */       
/*  61 */       this.vY *= 0.5F;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void update() {
/*  66 */     this.x += this.vX * Gdx.graphics.getDeltaTime() * 60.0F;
/*  67 */     this.y -= this.vY * Gdx.graphics.getDeltaTime() * 60.0F;
/*     */     
/*  69 */     if (this.vY < -25.0F) {
/*  70 */       this.vY *= Math.max(0.5F, 1.0F - 2.5F * Gdx.graphics.getDeltaTime());
/*  71 */     } else if (this.vY < -10.0F) {
/*  72 */       this.vY *= Math.max(0.5F, 1.0F - Gdx.graphics.getDeltaTime());
/*     */     } 
/*     */ 
/*     */     
/*  76 */     if (!this.isPickupable) {
/*  77 */       if (this.y > this.landingY) {
/*  78 */         this.isPickupable = true;
/*  79 */         this.hitbox = new Hitbox(this.x - IMG_WIDTH * 2.0F, this.y - IMG_WIDTH * 2.0F, IMG_WIDTH * 4.0F, IMG_WIDTH * 4.0F);
/*     */       } 
/*  81 */     } else if (!this.pickedup) {
/*  82 */       this.color.a -= Gdx.graphics.getDeltaTime() * 2.0F;
/*  83 */       this.pickedup = true;
/*  84 */       playGainGoldSFX();
/*  85 */       AbstractDungeon.effectsQueue.add(new ShineLinesEffect(this.x, this.y));
/*     */     } else {
/*  87 */       this.color.a -= Gdx.graphics.getDeltaTime() * 2.0F;
/*     */       
/*  89 */       if (this.color.a <= 0.0F) {
/*  90 */         this.isDone = true;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void playGainGoldSFX() {
/*  96 */     int roll = MathUtils.random(2);
/*  97 */     switch (roll) {
/*     */       case 0:
/*  99 */         CardCrawlGame.sound.play("GOLD_GAIN", 0.1F);
/*     */         return;
/*     */       case 1:
/* 102 */         CardCrawlGame.sound.play("GOLD_GAIN_3", 0.1F);
/*     */         return;
/*     */     } 
/* 105 */     CardCrawlGame.sound.play("GOLD_GAIN_5", 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 110 */     sb.setColor(this.color);
/* 111 */     sb.draw(this.img, this.x, this.y, this.img.getWidth() / 2.0F, this.img.getHeight() / 2.0F, this.img.getWidth(), this.img.getHeight(), Settings.scale, Settings.scale, this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), this.flip, false);
/* 112 */     if (this.hitbox != null) {
/* 113 */       this.hitbox.render(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {}
/*     */ 
/*     */   
/* 122 */   private static final float IMG_WIDTH = 32.0F * Settings.scale;
/*     */   private Texture img;
/*     */   private boolean isPickupable;
/*     */   public boolean pickedup;
/*     */   private float x;
/*     */   private float y;
/*     */   private float landingY;
/*     */   private boolean flip;
/*     */   private float vY;
/*     */   private float vX;
/*     */   private Hitbox hitbox;
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\TouchPickupSouls.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */