/*     */ package downfall.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
/*     */ import com.megacrit.cardcrawl.vfx.ShineLinesEffect;
/*     */ import downfall.downfallMod;
/*     */ import java.util.Iterator;
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
/*     */ public class GainSingleSoulEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   public GainSingleSoulEffect(AbstractCreature owner, float x, float y, float targetX, float targetY, boolean showGainEffect) {
/*  48 */     this.alpha = 0.0F;
/*  49 */     this.suctionTimer = 1.0F;
/*     */     
/*  51 */     this.img = downfallMod.soulsImage;
/*     */     
/*  53 */     this.x = x - this.img.getWidth() / 2.0F;
/*  54 */     this.y = y - this.img.getHeight() / 2.0F;
/*  55 */     this.targetX = targetX + MathUtils.random(-TARGET_JITTER, TARGET_JITTER);
/*  56 */     this.targetY = targetY + MathUtils.random(-TARGET_JITTER, TARGET_JITTER * 2.0F);
/*  57 */     this.showGainEffect = showGainEffect;
/*  58 */     this.owner = owner;
/*  59 */     this.staggerTimer = MathUtils.random(0.0F, 1.0F);
/*  60 */     this.vX = MathUtils.random(START_VX - 50.0F * Settings.scale, START_VX_JITTER);
/*  61 */     this.rotationSpeed = MathUtils.random(500.0F, 2000.0F);
/*  62 */     if (MathUtils.randomBoolean()) {
/*  63 */       this.vX = -this.vX;
/*  64 */       this.rotationSpeed = -this.rotationSpeed;
/*     */     } 
/*     */     
/*  67 */     this.vY = MathUtils.random(START_VY, START_VY_JITTER);
/*  68 */     this.scale = Settings.scale * 0.75F;
/*  69 */     this.color = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public GainSingleSoulEffect(float x, float y) {
/*  73 */     this((AbstractCreature)AbstractDungeon.player, x, y, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, true);
/*     */   }
/*     */   
/*     */   public void update() {
/*  77 */     if (this.staggerTimer > 0.0F) {
/*  78 */       this.staggerTimer -= Gdx.graphics.getDeltaTime();
/*     */     } else {
/*  80 */       if (this.alpha != 1.0F) {
/*  81 */         this.alpha += Gdx.graphics.getDeltaTime() * 2.0F;
/*  82 */         if (this.alpha > 1.0F) {
/*  83 */           this.alpha = 1.0F;
/*     */         }
/*     */         
/*  86 */         this.color.a = this.alpha;
/*     */       } 
/*     */       
/*  89 */       this.rotation += Gdx.graphics.getDeltaTime() * this.rotationSpeed;
/*  90 */       this.x += Gdx.graphics.getDeltaTime() * this.vX;
/*  91 */       this.y += Gdx.graphics.getDeltaTime() * this.vY;
/*  92 */       this.vY -= Gdx.graphics.getDeltaTime() * GRAVITY;
/*  93 */       if (this.suctionTimer > 0.0F) {
/*  94 */         this.suctionTimer -= Gdx.graphics.getDeltaTime();
/*     */       } else {
/*  96 */         this.vY = MathUtils.lerp(this.vY, 0.0F, Gdx.graphics.getDeltaTime() * 5.0F);
/*  97 */         this.vX = MathUtils.lerp(this.vX, 0.0F, Gdx.graphics.getDeltaTime() * 5.0F);
/*  98 */         this.x = MathUtils.lerp(this.x, this.targetX, Gdx.graphics.getDeltaTime() * 4.0F);
/*  99 */         this.y = MathUtils.lerp(this.y, this.targetY, Gdx.graphics.getDeltaTime() * 4.0F);
/* 100 */         if (Math.abs(this.x - this.targetX) < 20.0F) {
/* 101 */           this.isDone = true;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 106 */           if (!this.owner.isPlayer) {
/* 107 */             this.owner.gainGold(1);
/*     */           }
/*     */           
/* 110 */           AbstractDungeon.effectsQueue.add(new ShineLinesEffect(this.x, this.y));
/* 111 */           boolean textEffectFound = false;
/* 112 */           Iterator<AbstractGameEffect> var2 = AbstractDungeon.effectList.iterator();
/*     */ 
/*     */           
/* 115 */           while (var2.hasNext()) {
/* 116 */             AbstractGameEffect e = var2.next();
/* 117 */             if (e instanceof GainGoldTextEffect && ((GainGoldTextEffect)e).ping(1)) {
/* 118 */               textEffectFound = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/* 123 */           if (!textEffectFound) {
/* 124 */             var2 = AbstractDungeon.effectsQueue.iterator();
/*     */             
/* 126 */             while (var2.hasNext()) {
/* 127 */               AbstractGameEffect e = var2.next();
/* 128 */               if (e instanceof GainGoldTextEffect && ((GainGoldTextEffect)e).ping(1)) {
/* 129 */                 textEffectFound = true;
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 134 */           if (!textEffectFound && this.showGainEffect) {
/* 135 */             AbstractDungeon.effectsQueue.add(new GainGoldTextEffect(1));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 144 */     if (this.staggerTimer <= 0.0F) {
/* 145 */       sb.setColor(this.color);
/* 146 */       sb.draw(this.img, this.x, this.y, this.img.getWidth() / 2.0F, this.img.getHeight() / 2.0F, this.img.getWidth(), this.img.getHeight(), Settings.scale, Settings.scale, 0.0F, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispose() {}
/*     */ 
/*     */   
/* 154 */   private static final float GRAVITY = 500.0F * Settings.scale;
/* 155 */   private static final float START_VY = 800.0F * Settings.scale;
/* 156 */   private static final float START_VY_JITTER = 400.0F * Settings.scale;
/* 157 */   private static final float START_VX = 200.0F * Settings.scale;
/* 158 */   private static final float START_VX_JITTER = 300.0F * Settings.scale;
/* 159 */   private static final float TARGET_JITTER = 50.0F * Settings.scale;
/*     */   private float rotationSpeed;
/*     */   private float x;
/*     */   private float y;
/*     */   private float vX;
/*     */   private float vY;
/*     */   private float targetX;
/*     */   private float targetY;
/*     */   private Texture img;
/*     */   private float alpha;
/*     */   private float suctionTimer;
/*     */   private float staggerTimer;
/*     */   private boolean showGainEffect;
/*     */   private AbstractCreature owner;
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\GainSingleSoulEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */