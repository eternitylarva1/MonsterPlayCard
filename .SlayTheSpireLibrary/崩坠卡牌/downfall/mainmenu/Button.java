/*     */ package downfall.mainmenu;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ 
/*     */ public class Button {
/*  14 */   private int W = 512;
/*     */   
/*  16 */   private int H = 256;
/*     */   
/*  18 */   private Color HOVER_BLEND_COLOR = new Color(1.0F, 1.0F, 1.0F, 0.4F);
/*     */   
/*  20 */   private Color TEXT_DISABLED_COLOR = new Color(0.6F, 0.6F, 0.6F, 1.0F);
/*     */   
/*  22 */   public float SHOW_X = Settings.WIDTH - 256.0F * Settings.scale; public float DRAW_Y = 128.0F * Settings.scale;
/*     */   
/*  24 */   private float HIDE_X = this.SHOW_X + 400.0F * Settings.scale;
/*     */   
/*  26 */   private float current_x = this.HIDE_X;
/*     */   
/*  28 */   private float target_x = this.current_x;
/*     */   
/*     */   public boolean isDisabled = false;
/*     */   
/*     */   public boolean isHovered = false;
/*     */   
/*  34 */   private String buttonText = "NOT_SET";
/*     */   
/*  36 */   private float TEXT_OFFSET_X = 0.0F * Settings.scale;
/*     */   
/*  38 */   private float TEXT_OFFSET_Y = 0.0F * Settings.scale;
/*     */   
/*     */   public Hitbox hb;
/*     */   
/*  42 */   public float alpha = 1.0F;
/*     */   
/*     */   public Texture image;
/*     */   
/*     */   public Button(float x, float y, String label, Texture image) {
/*  47 */     updateText(label);
/*  48 */     this.SHOW_X = x;
/*  49 */     this.current_x = this.SHOW_X;
/*  50 */     this.target_x = this.current_x;
/*     */     
/*  52 */     this.DRAW_Y = y;
/*     */     
/*  54 */     this.image = image;
/*     */     
/*  56 */     this.hb = new Hitbox(0.0F, 0.0F, image.getWidth(), image.getHeight());
/*  57 */     this.hb.move(this.SHOW_X, this.DRAW_Y);
/*     */   }
/*     */   
/*     */   public void move(float x, float y) {
/*  61 */     this.SHOW_X = x;
/*  62 */     this.DRAW_Y = y;
/*  63 */     this.hb.move(this.SHOW_X, this.DRAW_Y);
/*     */   }
/*     */   
/*     */   public void updateText(String label) {
/*  67 */     this.buttonText = label;
/*     */   }
/*     */   
/*     */   public void update() {
/*  71 */     this.hb.update();
/*  72 */     if (InputHelper.justClickedLeft && this.hb.hovered && !this.isDisabled) {
/*  73 */       this.hb.clickStarted = true;
/*  74 */       CardCrawlGame.sound.play("UI_CLICK_1");
/*     */     } 
/*  76 */     if (this.hb.justHovered && !this.isDisabled)
/*  77 */       CardCrawlGame.sound.play("UI_HOVER"); 
/*  78 */     this.isHovered = this.hb.hovered;
/*  79 */     if (CInputActionSet.proceed.isJustPressed()) {
/*  80 */       CInputActionSet.proceed.unpress();
/*  81 */       this.hb.clicked = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/*  86 */     Color c = new Color(1.0F, 1.0F, 1.0F, this.alpha);
/*     */     
/*  88 */     sb.setColor(c);
/*  89 */     renderButton(sb);
/*  90 */     if (this.hb.hovered && !this.isDisabled && !this.hb.clickStarted) {
/*  91 */       sb.setBlendFunction(770, 1);
/*  92 */       sb.setColor(this.HOVER_BLEND_COLOR);
/*  93 */       renderButton(sb);
/*  94 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*  96 */     if (this.isDisabled) {
/*  97 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.SHOW_X + this.TEXT_OFFSET_X, this.DRAW_Y + this.TEXT_OFFSET_Y, this.TEXT_DISABLED_COLOR);
/*  98 */     } else if (this.hb.clickStarted) {
/*  99 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.SHOW_X + this.TEXT_OFFSET_X, this.DRAW_Y + this.TEXT_OFFSET_Y, Color.LIGHT_GRAY);
/* 100 */     } else if (this.hb.hovered) {
/* 101 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.SHOW_X + this.TEXT_OFFSET_X, this.DRAW_Y + this.TEXT_OFFSET_Y, Settings.LIGHT_YELLOW_COLOR);
/*     */     } else {
/* 103 */       FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.buttonText, this.SHOW_X + this.TEXT_OFFSET_X, this.DRAW_Y + this.TEXT_OFFSET_Y, Settings.LIGHT_YELLOW_COLOR);
/*     */     } 
/* 105 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   private void renderButton(SpriteBatch sb) {
/* 109 */     sb.draw(this.image, this.SHOW_X - this.image.getWidth() / 2.0F, this.DRAW_Y - this.image.getHeight() / 2.0F, this.image.getWidth() / 2.0F, this.image.getHeight() / 2.0F, this.image.getWidth(), this.image.getHeight(), Settings.scale, Settings.scale, 0.0F, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\mainmenu\Button.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */