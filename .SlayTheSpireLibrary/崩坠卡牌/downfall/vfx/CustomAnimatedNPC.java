/*     */ package downfall.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Pixmap;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*     */ import com.badlogic.gdx.graphics.glutils.FrameBuffer;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.esotericsoftware.spine.AnimationStateData;
/*     */ import com.esotericsoftware.spine.Skeleton;
/*     */ import com.esotericsoftware.spine.SkeletonData;
/*     */ import com.esotericsoftware.spine.SkeletonJson;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.HeartAnimListener;
/*     */ import hermit.util.TextureLoader;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomAnimatedNPC
/*     */ {
/*  32 */   public TextureAtlas atlas = null;
/*     */   
/*     */   public Skeleton skeleton;
/*     */   public AnimationState state;
/*     */   public AnimationStateData stateData;
/*     */   public boolean customFlipX;
/*  38 */   public Float customRot = Float.valueOf(0.0F);
/*  39 */   public Float customShadowScale = Float.valueOf(0.0F);
/*     */   
/*     */   public boolean highlighted = false;
/*     */   
/*     */   public boolean portalRender;
/*     */   
/*     */   public boolean portalRenderActive = false;
/*     */   
/*  47 */   public static int borderEffectCount = 36;
/*     */   
/*     */   private boolean colorSwapped = false;
/*     */   
/*     */   private boolean noMesh;
/*  52 */   private ArrayList<PortalBorderEffect> borderEffects = new ArrayList<>();
/*     */   
/*     */   private float heartCenterX;
/*     */   
/*     */   private float heartCenterY;
/*     */   private float heartScale;
/*     */   public Texture portalImage;
/*  59 */   private HeartAnimListener animListener = new HeartAnimListener();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private FrameBuffer heartBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);
/*     */ 
/*     */   
/*  70 */   private FrameBuffer maskBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);
/*     */   
/*     */   private static final float PORTAL_GROW_TIME = 2.0F;
/*  73 */   private float maskDuration = 0.0F;
/*     */ 
/*     */   
/*  76 */   private static final TextureRegion MASK_REGION = new TextureRegion(new Texture("downfallResources/images/vfx/HeartMask.png"), 500, 500);
/*     */   
/*     */   public CustomAnimatedNPC(float x, float y, String atlasUrl, String skeletonUrl, String trackName, boolean portalRender, int portalType) {
/*  79 */     this(x, y, atlasUrl, skeletonUrl, trackName, portalRender, portalType, false, 1.0F);
/*     */   }
/*     */   
/*     */   public CustomAnimatedNPC(float x, float y, String atlasUrl, String skeletonUrl, String trackName, boolean portalRender, int portalType, boolean noMesh, float heartScale) {
/*  83 */     this.noMesh = noMesh;
/*  84 */     this.heartScale = heartScale;
/*     */     
/*  86 */     if (!this.noMesh) {
/*  87 */       loadAnimation(atlasUrl, skeletonUrl, 1.0F);
/*  88 */       this.skeleton.setPosition(x, y - 300.0F * Settings.scale * this.heartScale);
/*  89 */       this.state.setAnimation(0, trackName, true);
/*  90 */       this.state.setTimeScale(1.0F);
/*     */     } 
/*     */     
/*  93 */     this.portalRender = portalRender;
/*     */     
/*  95 */     this.heartCenterX = x;
/*  96 */     this.heartCenterY = y;
/*     */     
/*  98 */     if (portalType == 0) {
/*  99 */       this.portalImage = TextureLoader.getTexture("downfallResources/images/vfx/beyondPortal.png");
/*     */     }
/* 101 */     if (portalType == 1) {
/* 102 */       this.portalImage = TextureLoader.getTexture("downfallResources/images/vfx/cityPortal.png");
/*     */     }
/*     */     
/* 105 */     if (this.portalRender) {
/* 106 */       if (!this.noMesh) {
/* 107 */         addListener(new HeartAnimListener());
/* 108 */         this.skeleton.getRootBone().setScale(0.8F * this.heartScale);
/*     */       } 
/* 110 */       for (int i = 1; i <= borderEffectCount; i++) {
/*     */         
/* 112 */         PortalBorderEffect effect = new PortalBorderEffect(this.heartCenterX, this.heartCenterY, (i * 100) * borderEffectCount / 360.0F, this.heartScale);
/* 113 */         this.borderEffects.add(effect);
/* 114 */         effect.orbitalInterval = ((PortalBorderEffect)this.borderEffects.get(0)).orbitalInterval;
/* 115 */         effect.ELLIPSIS_SCALE = this.heartScale;
/* 116 */         effect.calculateEllipseSize();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void loadAnimation(String atlasUrl, String skeletonUrl, float scale) {
/* 122 */     this.atlas = new TextureAtlas(Gdx.files.internal(atlasUrl));
/* 123 */     SkeletonJson json = new SkeletonJson(this.atlas);
/* 124 */     json.setScale(Settings.scale / scale);
/* 125 */     SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal(skeletonUrl));
/* 126 */     this.skeleton = new Skeleton(skeletonData);
/* 127 */     this.skeleton.setColor(Color.WHITE);
/* 128 */     this.stateData = new AnimationStateData(skeletonData);
/* 129 */     this.state = new AnimationState(this.stateData);
/*     */   }
/*     */   
/*     */   public void changeBorderColor(Color color) {
/* 133 */     for (PortalBorderEffect pb : this.borderEffects) {
/* 134 */       pb.borderColor = color;
/*     */     }
/*     */   }
/*     */   
/*     */   public void update() {
/* 139 */     if (this.portalRender && 
/* 140 */       this.portalRenderActive) {
/* 141 */       for (PortalBorderEffect pb : this.borderEffects) {
/* 142 */         pb.update();
/*     */       }
/*     */       
/* 145 */       if (this.maskDuration < 2.0F) {
/* 146 */         this.maskDuration += Gdx.graphics.getDeltaTime();
/*     */         
/* 148 */         for (PortalBorderEffect pb : this.borderEffects) {
/* 149 */           pb.ELLIPSIS_SCALE = this.maskDuration / 2.0F * this.heartScale;
/* 150 */           pb.calculateEllipseSize();
/*     */         } 
/*     */         
/* 153 */         if (this.maskDuration > 2.0F) {
/* 154 */           this.maskDuration = 2.0F;
/* 155 */           for (PortalBorderEffect pb : this.borderEffects) {
/* 156 */             pb.ELLIPSIS_SCALE = this.maskDuration / 2.0F * this.heartScale;
/* 157 */             pb.calculateEllipseSize();
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 167 */     render(sb, Color.WHITE);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb, Color color) {
/* 171 */     if (this.portalRender) {
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
/* 188 */       sb.end();
/* 189 */       this.maskBuffer.begin();
/*     */ 
/*     */ 
/*     */       
/* 193 */       Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/* 194 */       Gdx.gl.glClear(16640);
/* 195 */       Gdx.gl.glColorMask(true, true, true, true);
/*     */       
/* 197 */       sb.begin();
/* 198 */       sb.setColor(Color.WHITE);
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
/* 216 */       float scale = this.maskDuration / 2.0F * Settings.scale;
/*     */ 
/*     */       
/* 219 */       float w = MASK_REGION.getRegionWidth() * this.heartScale;
/* 220 */       float h = MASK_REGION.getRegionHeight() * this.heartScale;
/* 221 */       sb.draw(MASK_REGION, this.heartCenterX - w / 2.0F, this.heartCenterY - h / 2.0F, w / 2.0F, h / 2.0F, w, h, scale, scale, 0.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 226 */       sb.end();
/* 227 */       this.maskBuffer.end();
/*     */       
/* 229 */       Texture tmpTexture = (Texture)this.maskBuffer.getColorBufferTexture();
/* 230 */       TextureRegion tmpMask = new TextureRegion(tmpTexture);
/*     */ 
/*     */ 
/*     */       
/* 234 */       tmpMask.flip(false, true);
/*     */ 
/*     */ 
/*     */       
/* 238 */       this.heartBuffer.begin();
/* 239 */       Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
/* 240 */       Gdx.gl.glClear(16640);
/* 241 */       Gdx.gl.glColorMask(true, true, true, true);
/* 242 */       sb.begin();
/*     */       
/* 244 */       if (this.highlighted) {
/* 245 */         sb.setColor(Color.WHITE);
/*     */       } else {
/* 247 */         sb.setColor(Color.LIGHT_GRAY);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 255 */       sb.draw(this.portalImage, this.heartCenterX - 250.0F * Settings.scale, this.heartCenterY - 250.0F * Settings.scale, 500.0F * Settings.scale, 500.0F * Settings.scale);
/*     */ 
/*     */       
/* 258 */       standardRender(sb);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 267 */       sb.setBlendFunction(0, 770);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 272 */       sb.draw(tmpMask, 0.0F, 0.0F);
/*     */ 
/*     */       
/* 275 */       sb.setBlendFunction(770, 771);
/*     */ 
/*     */       
/* 278 */       sb.end();
/* 279 */       this.heartBuffer.end();
/* 280 */       TextureRegion maskedHeart = new TextureRegion((Texture)this.heartBuffer.getColorBufferTexture());
/* 281 */       maskedHeart.flip(false, true);
/*     */ 
/*     */       
/* 284 */       sb.begin();
/*     */ 
/*     */ 
/*     */       
/* 288 */       sb.draw(maskedHeart, (-2 * Settings.VERT_LETTERBOX_AMT), (-2 * Settings.HORIZ_LETTERBOX_AMT));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 293 */       standardRender(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 298 */     if (this.atlas != null) this.atlas.dispose(); 
/*     */   }
/*     */   
/*     */   public void standardRender(SpriteBatch sb, Color color) {
/* 302 */     if (!this.noMesh) {
/* 303 */       this.state.update(Gdx.graphics.getDeltaTime());
/* 304 */       this.state.apply(this.skeleton);
/* 305 */       if (this.skeleton.getRootBone() != null) {
/* 306 */         this.skeleton.getRootBone().setRotation(this.customRot.floatValue());
/* 307 */         if (this.skeleton.findBone("shadow") != null) {
/*     */           
/* 309 */           this.skeleton.findBone("shadow").setRotation(-1.0F * this.customRot.floatValue());
/* 310 */           this.skeleton.findBone("shadow").setScale(this.customShadowScale.floatValue());
/*     */         } 
/*     */       } 
/* 313 */       this.skeleton.updateWorldTransform();
/* 314 */       this.skeleton.setFlip(this.customFlipX, false);
/* 315 */       this.skeleton.setColor(color);
/*     */       
/* 317 */       sb.end();
/* 318 */       CardCrawlGame.psb.begin();
/* 319 */       AbstractCreature.sr.draw(CardCrawlGame.psb, this.skeleton);
/* 320 */       CardCrawlGame.psb.end();
/* 321 */       sb.begin();
/* 322 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void standardRender(SpriteBatch sb) {
/* 327 */     standardRender(sb, Color.WHITE);
/*     */   }
/*     */   
/*     */   public void setTimeScale(float setScale) {
/* 331 */     this.state.setTimeScale(setScale);
/*     */   }
/*     */   
/*     */   public void addListener(HeartAnimListener listener) {
/* 335 */     this.state.addListener((AnimationState.AnimationStateListener)listener);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\vfx\CustomAnimatedNPC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */