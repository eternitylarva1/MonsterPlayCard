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
/*     */ public class CollectorTutorials
/*     */   extends FtueTip {
/*  21 */   private static final TutorialStrings tutorialStrings = CardCrawlGame.languagePack.getTutorialString("HermitMod:CollectorTutorials");
/*  22 */   public static final String[] txt = tutorialStrings.TEXT;
/*  23 */   public static final String[] LABEL = tutorialStrings.LABEL;
/*     */   private Texture img1;
/*     */   private Texture img2;
/*     */   private Texture img3;
/*  27 */   private Color screen = Color.valueOf("1c262a00");
/*     */   private float x;
/*     */   private float x1;
/*     */   private float x2;
/*     */   private float x3;
/*     */   private float targetX;
/*     */   private float startX;
/*  34 */   private float scrollTimer = 0.0F;
/*  35 */   private int currentSlot = 0;
/*  36 */   private static String txt1 = txt[0];
/*  37 */   private static String txt2 = txt[1];
/*     */ 
/*     */   
/*     */   private int closeScreen;
/*     */ 
/*     */   
/*     */   public CollectorTutorials() {
/*  44 */     this.img1 = TextureLoader.getTexture("hermitResources/images/tip/collector_t1.png");
/*  45 */     this.img2 = TextureLoader.getTexture("hermitResources/images/tip/collector_t2.png");
/*     */     
/*  47 */     txt1 = txt[0];
/*  48 */     txt2 = txt[1];
/*  49 */     this.closeScreen = -1;
/*     */     
/*  51 */     AbstractDungeon.player.releaseCard();
/*  52 */     if (AbstractDungeon.isScreenUp) {
/*  53 */       AbstractDungeon.dynamicBanner.hide();
/*  54 */       AbstractDungeon.previousScreen = AbstractDungeon.screen;
/*     */     } 
/*  56 */     AbstractDungeon.isScreenUp = true;
/*  57 */     AbstractDungeon.screen = AbstractDungeon.CurrentScreen.FTUE;
/*  58 */     AbstractDungeon.overlayMenu.showBlackScreen();
/*  59 */     this.x = 0.0F;
/*  60 */     this.x1 = 567.0F * Settings.scale;
/*  61 */     this.x2 = this.x1 + Settings.WIDTH;
/*  62 */     AbstractDungeon.overlayMenu.proceedButton.show();
/*  63 */     AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  68 */     AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[0]);
/*  69 */     if (this.currentSlot <= this.closeScreen) {
/*  70 */       AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[1]);
/*     */     }
/*  72 */     if (this.screen.a != 0.8F) {
/*  73 */       this.screen.a += Gdx.graphics.getDeltaTime();
/*  74 */       if (this.screen.a > 0.8F) {
/*  75 */         this.screen.a = 0.8F;
/*     */       }
/*     */     } 
/*  78 */     if ((AbstractDungeon.overlayMenu.proceedButton.isHovered && InputHelper.justClickedLeft) || CInputActionSet.proceed
/*  79 */       .isJustPressed()) {
/*     */       
/*  81 */       CInputActionSet.proceed.unpress();
/*  82 */       if (this.currentSlot <= this.closeScreen) {
/*     */         
/*  84 */         CardCrawlGame.sound.play("DECK_CLOSE");
/*  85 */         AbstractDungeon.closeCurrentScreen();
/*  86 */         AbstractDungeon.overlayMenu.proceedButton.hide();
/*  87 */         AbstractDungeon.effectList.clear();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  92 */       this.currentSlot--;
/*  93 */       this.startX = this.x;
/*  94 */       this.targetX = (this.currentSlot * Settings.WIDTH);
/*  95 */       this.scrollTimer = 0.3F;
/*  96 */       if (this.currentSlot <= this.closeScreen) {
/*  97 */         AbstractDungeon.overlayMenu.proceedButton.setLabel(LABEL[1]);
/*     */       }
/*     */     } 
/* 100 */     if (this.scrollTimer != 0.0F) {
/* 101 */       this.scrollTimer -= Gdx.graphics.getDeltaTime();
/* 102 */       if (this.scrollTimer < 0.0F) {
/* 103 */         this.scrollTimer = 0.0F;
/*     */       }
/*     */     } 
/* 106 */     this.x = Interpolation.fade.apply(this.targetX, this.startX, this.scrollTimer / 0.3F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 111 */     sb.setColor(this.screen);
/* 112 */     sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
/*     */     
/* 114 */     sb.setColor(Color.WHITE);
/* 115 */     sb.draw(this.img1, this.x + this.x1 - 380.0F, Settings.HEIGHT / 2.0F - 290.0F, 380.0F, 290.0F, 760.0F, 580.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 760, 580, false, false);
/*     */     
/* 117 */     sb.draw(this.img2, this.x + this.x2 - 380.0F, Settings.HEIGHT / 2.0F - 290.0F, 380.0F, 290.0F, 760.0F, 580.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 760, 580, false, false);
/*     */     
/* 119 */     FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt1, this.x + this.x1 + 400.0F * Settings.scale, Settings.HEIGHT / 2.0F - 
/* 120 */         FontHelper.getSmartHeight(FontHelper.panelNameFont, txt1, 700.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 700.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */     
/* 122 */     FontHelper.renderSmartText(sb, FontHelper.panelNameFont, txt2, this.x + this.x2 + 400.0F * Settings.scale, Settings.HEIGHT / 2.0F - 
/* 123 */         FontHelper.getSmartHeight(FontHelper.panelNameFont, txt2, 700.0F * Settings.scale, 40.0F * Settings.scale) / 2.0F, 700.0F * Settings.scale, 40.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */     
/* 125 */     FontHelper.renderFontCenteredWidth(sb, FontHelper.panelNameFont, LABEL[2], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 360.0F * Settings.scale, Settings.GOLD_COLOR);
/*     */     
/* 127 */     FontHelper.renderFontCenteredWidth(sb, FontHelper.tipBodyFont, LABEL[3] + Integer.toString(Math.abs(this.currentSlot - 1)) + "/" + 
/* 128 */         Math.abs(this.closeScreen - 1) + LABEL[4], Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F - 400.0F * Settings.scale, Settings.CREAM_COLOR);
/*     */     
/* 130 */     AbstractDungeon.overlayMenu.proceedButton.render(sb);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\tutorials\CollectorTutorials.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */