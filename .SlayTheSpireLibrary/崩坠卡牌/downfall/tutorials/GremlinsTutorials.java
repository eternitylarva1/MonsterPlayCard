/*     */ package downfall.tutorials;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.TutorialStrings;
/*     */ import com.megacrit.cardcrawl.ui.FtueTip;
/*     */ import downfall.util.TextureLoader;
/*     */ 
/*     */ public class GremlinsTutorials
/*     */   extends FtueTip
/*     */ {
/*  22 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("HermitMod:GremlinsTutorials");
/*  23 */   public static final String[] txt = tutorialStrings.TEXT;
/*  24 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*     */   private Texture img1;
/*     */   private Texture img2;
/*     */   private Texture img3;
/*  28 */   private Color screen = Color.valueOf("1c262a00");
/*     */   private float x;
/*     */   private float x1;
/*     */   private float x2;
/*     */   private float x3;
/*     */   private float targetX;
/*     */   private float startX;
/*  35 */   private float scrollTimer = 0.0F;
/*  36 */   private int currentSlot = 0;
/*  37 */   private static String txt1 = txt[0];
/*  38 */   private static String txt2 = txt[1];
/*     */ 
/*     */   
/*     */   private int closeScreen;
/*     */ 
/*     */   
/*     */   public GremlinsTutorials() {
/*  45 */     this.img1 = TextureLoader.getTexture("hermitResources/images/tip/GremlinsTip1.png");
/*  46 */     this.img2 = TextureLoader.getTexture("hermitResources/images/tip/GremlinsTip2.png");
/*     */     
/*  48 */     txt1 = txt[0];
/*  49 */     txt2 = txt[1];
/*  50 */     this.closeScreen = -1;
/*     */     
/*  52 */     AbstractDungeon.player.releaseCard();
/*  53 */     if (AbstractDungeon.isScreenUp) {
/*  54 */       AbstractDungeon.dynamicBanner.hide();
/*  55 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*     */     } 
/*  57 */     AbstractDungeon.isScreenUp = true;
/*  58 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.FTUE;
/*  59 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  60 */     this.x = 0.0F;
/*  61 */     this.x1 = 567.0F * Settings.scale;
/*  62 */     this.x2 = this.x1 + Settings.WIDTH;
/*  63 */     AbstractDungeon.overlayMenu.proceedButton.show();
/*  64 */     AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  69 */     AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
/*  70 */     if (this.currentSlot <= this.closeScreen) {
/*  71 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[1]);
/*     */     }
/*  73 */     if (this.screen.a != 0.8F) {
/*  74 */       this.screen.a += Gdx.graphics.getDeltaTime();
/*  75 */       if (this.screen.a > 0.8F) {
/*  76 */         this.screen.a = 0.8F;
/*     */       }
/*     */     } 
/*  79 */     if ((AbstractDungeon.overlayMenu.proceedButton.isHovered && InputHelper.justClickedLeft) || CInputActionSet.proceed
/*  80 */       .isJustPressed()) {
/*     */       
/*  82 */       CInputActionSet.proceed.unpress();
/*  83 */       if (this.currentSlot <= this.closeScreen) {
/*     */         
/*  85 */         CardCrawlGame.sound.play("DECK_CLOSE");
/*  86 */         AbstractDungeon.closeCurrentScreen();
/*  87 */         AbstractDungeon.overlayMenu.proceedButton.hide();
/*  88 */         AbstractDungeon.effectList.clear();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  93 */       this.currentSlot--;
/*  94 */       this.startX = this.x;
/*  95 */       this.targetX = (this.currentSlot * Settings.WIDTH);
/*  96 */       this.scrollTimer = 0.3F;
/*  97 */       if (this.currentSlot <= this.closeScreen) {
/*  98 */         AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[1]);
/*     */       }
/*     */     } 
/* 101 */     if (this.scrollTimer != 0.0F) {
/* 102 */       this.scrollTimer -= Gdx.graphics.getDeltaTime();
/* 103 */       if (this.scrollTimer < 0.0F) {
/* 104 */         this.scrollTimer = 0.0F;
/*     */       }
/*     */     } 
/* 107 */     this.x = Interpolation.fade.apply(this.targetX, this.startX, this.scrollTimer / 0.3F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 112 */     sb.setColor(this.screen);
/* 113 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 115 */     sb.setColor(Color.WHITE);
/* 116 */     sb.draw(this.img1, this.x + this.x1 - 380.0F, Settings.HEIGHT / 2.0F - 290.0F, 380.0F, 290.0F, 760.0F, 580.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 760, 580, false, false);
/*     */     
/* 118 */     sb.draw(this.img2, this.x + this.x2 - 380.0F, Settings.HEIGHT / 2.0F - 290.0F, 380.0F, 290.0F, 760.0F, 580.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 760, 580, false, false);
/*     */     
/* 120 */     FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt1, this.x + this.x1 + 400.0F * Settings.scale, Settings.HEIGHT / 2.0F - 
/* 121 */         FontHelper.getSmartHeight(FontHelper.panelNameFont, txt1, 700.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 700.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */     
/* 123 */     FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt2, this.x + this.x2 + 400.0F * Settings.scale, Settings.HEIGHT / 2.0F - 
/* 124 */         FontHelper.getSmartHeight(FontHelper.panelNameFont, txt2, 700.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 700.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */     
/* 126 */     FontHelper.renderFontCenteredWidth(sb, FontHelper.panelNameFont, LABEL[2], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 360.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */     
/* 128 */     FontHelper.renderFontCenteredWidth(sb, FontHelper.tipBodyFont, LABEL[3] + Integer.toString(Math.abs(this.currentSlot - 1)) + "/" + 
/* 129 */         Math.abs(this.closeScreen - 1) + LABEL[4], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 400.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */     
/* 131 */     AbstractDungeon.overlayMenu.proceedButton.render(sb);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\tutorials\GremlinsTutorials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */