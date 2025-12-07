/*     */ package downfall.mainmenu;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.scenes.TitleBackground;
/*     */ import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import downfall.util.TextureLoader;
/*     */ import downfall.vfx.PrettyAdEffect;
/*     */ import java.awt.Desktop;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MainMenuAdPatch
/*     */ {
/*  33 */   private static final UIStrings STRINGS = CardCrawlGame.languagePack.getUIString("downfall:MainMenuAd");
/*  34 */   private static final MainMenuAd advert = new MainMenuAd();
/*  35 */   public static final TalesAndTacticsPopup popup = new TalesAndTacticsPopup();
/*     */   
/*     */   private static class MainMenuAdInfo {
/*     */     private String text;
/*     */     private String text2;
/*     */     private String text3;
/*     */     private String text4;
/*     */     private String text5;
/*     */     private String textheader;
/*     */     private String url;
/*     */     private Texture img;
/*     */     
/*     */     private MainMenuAdInfo(String text, String text2, String text3, String text4, String text5, String textheader, String url, Texture img) {
/*  48 */       this.text = text;
/*  49 */       this.text2 = text2;
/*  50 */       this.text3 = text3;
/*  51 */       this.text4 = text4;
/*  52 */       this.text5 = text5;
/*  53 */       this.textheader = textheader;
/*  54 */       this.url = url;
/*  55 */       this.img = img;
/*     */     }
/*     */   }
/*     */   
/*  59 */   private static ArrayList<MainMenuAdInfo> ads = new ArrayList<>();
/*     */ 
/*     */   
/*     */   static {
/*  63 */     ads.add(new MainMenuAdInfo("", "", "", "", "", "", "https://store.steampowered.com/app/603960/Star_of_Providence/", TextureLoader.getTexture("downfallResources/images/menustuff/" + TalesAndTacticsPopup.langFolder() + "/menuSOP.png")));
/*     */ 
/*     */     
/*  66 */     ads.add(new MainMenuAdInfo("", "", "", "", "", "", "https://store.steampowered.com/app/1652250/Tales__Tactics/", TextureLoader.getTexture("downfallResources/images/menustuff/" + TalesAndTacticsPopup.langFolder() + "/menuTNT.png")));
/*     */     
/*  68 */     if (Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) {
/*  69 */       ads.add(new MainMenuAdInfo(STRINGS.TEXT[0], STRINGS.TEXT[1], STRINGS.TEXT[2], STRINGS.TEXT[3], STRINGS.TEXT[4], STRINGS.TEXT[5], "https://www.bilibili.com/video/BV1QA411j7Kx", null));
/*  70 */     } else if (Settings.language == Settings.GameLanguage.KOR) {
/*  71 */       ads.add(new MainMenuAdInfo(STRINGS.TEXT[0], STRINGS.TEXT[1], STRINGS.TEXT[2], STRINGS.TEXT[3], STRINGS.TEXT[4], STRINGS.TEXT[5], "https://blog.naver.com/2020xodn/222147787489", null));
/*     */     } else {
/*  73 */       ads.add(new MainMenuAdInfo(STRINGS.TEXT[0], STRINGS.TEXT[1], STRINGS.TEXT[2], STRINGS.TEXT[3], STRINGS.TEXT[4], STRINGS.TEXT[5], "https://DownfallTutorial.github.io", null));
/*     */     } 
/*  75 */     advert.current = ads.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = TitleBackground.class, method = "render")
/*     */   public static class RenderPatch
/*     */   {
/*     */     @SpirePostfixPatch
/*     */     public static void renderAd(TitleBackground instance, SpriteBatch sb) {
/*  88 */       MainMenuAdPatch.popup.done = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(clz = TitleBackground.class, method = "update")
/*     */   public static class UpdatePatch
/*     */   {
/*     */     @SpirePostfixPatch
/*     */     public static void updateAd(TitleBackground instance) {
/* 103 */       MainMenuAdPatch.popup.done = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class MainMenuAd
/*     */   {
/* 109 */     private static final Texture tex = TextureLoader.getTexture("downfallResources/images/menuPanelHalfBlue.png");
/*     */     
/*     */     private MainMenuAdPatch.MainMenuAdInfo current;
/*     */     
/*     */     public final Hitbox hb;
/*     */     public final Hitbox togglehb;
/* 115 */     private Color tint = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/* 116 */     private Color btnTint = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/* 117 */     private float xPos = Settings.WIDTH * 0.7F;
/* 118 */     private float yPos = Settings.HEIGHT / 2.0F;
/* 119 */     private float angle = 0.0F;
/* 120 */     private float scale = 0.5F;
/* 121 */     private float adjustedWidth = tex.getWidth() * Settings.scale * this.scale;
/* 122 */     private float adjustedHeight = tex.getHeight() * Settings.scale * this.scale;
/* 123 */     private float buttonX = this.xPos + this.adjustedWidth + 25.0F * Settings.scale;
/* 124 */     private float buttonY = this.yPos + this.adjustedHeight / 2.0F;
/* 125 */     private float buttonWidth = ImageMaster.CF_RIGHT_ARROW.getWidth();
/* 126 */     private float buttonHeight = ImageMaster.CF_RIGHT_ARROW.getHeight();
/*     */ 
/*     */     
/*     */     private float particleTimer;
/*     */     
/*     */     private ArrayList<AbstractGameEffect> sparkles;
/*     */ 
/*     */     
/*     */     public void render(SpriteBatch sb) {
/* 135 */       sb.setColor(Color.WHITE.cpy());
/*     */       
/* 137 */       if (this.current.img == null) {
/* 138 */         sb.draw(tex, this.xPos, this.yPos, 0.0F, 0.0F, tex.getWidth(), tex.getHeight(), Settings.scale * this.scale, Settings.scale * this.scale, this.angle, 0, 0, tex.getWidth(), tex.getHeight(), false, false);
/* 139 */         if (this.tint.a > 0.0F) {
/* 140 */           sb.setBlendFunction(770, 1);
/* 141 */           sb.setColor(this.tint);
/* 142 */           sb.draw(tex, this.xPos, this.yPos, 0.0F, 0.0F, tex.getWidth(), tex.getHeight(), Settings.scale * this.scale, Settings.scale * this.scale, this.angle, 0, 0, tex.getWidth(), tex.getHeight(), false, false);
/* 143 */           sb.setBlendFunction(770, 771);
/*     */         } 
/*     */         
/* 146 */         FontHelper.cardTitleFont.getData().setScale(1.0F);
/*     */         
/* 148 */         FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, this.current.text, this.xPos + this.adjustedWidth / 2.0F, this.yPos + 360.0F * Settings.scale);
/*     */         
/* 150 */         FontHelper.cardDescFont_N.getData().setScale(1.0F);
/* 151 */         FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, this.current.text2, this.xPos + this.adjustedWidth / 2.0F, this.yPos + 290.0F * Settings.scale);
/* 152 */         FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, this.current.text3, this.xPos + this.adjustedWidth / 2.0F, this.yPos + 250.0F * Settings.scale);
/* 153 */         FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, this.current.text4, this.xPos + this.adjustedWidth / 2.0F, this.yPos + 210.0F * Settings.scale);
/* 154 */         FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, this.current.text5, this.xPos + this.adjustedWidth / 2.0F, this.yPos + 170.0F * Settings.scale);
/* 155 */         FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, this.current.textheader, this.xPos + this.adjustedWidth / 2.0F, this.yPos + 130.0F * Settings.scale);
/*     */       } else {
/*     */         
/* 158 */         sb.draw(this.current.img, this.xPos, this.yPos, 0.0F, 0.0F, this.current.img.getWidth(), this.current.img.getHeight(), Settings.scale, Settings.scale, this.angle, 0, 0, this.current.img.getWidth(), this.current.img.getHeight(), false, false);
/* 159 */         if (this.tint.a > 0.0F) {
/* 160 */           sb.setBlendFunction(770, 1);
/* 161 */           sb.setColor(this.tint);
/* 162 */           sb.draw(this.current.img, this.xPos, this.yPos, 0.0F, 0.0F, this.current.img.getWidth(), this.current.img.getHeight(), Settings.scale, Settings.scale, this.angle, 0, 0, this.current.img.getWidth(), this.current.img.getHeight(), false, false);
/* 163 */           sb.setBlendFunction(770, 771);
/*     */         } 
/*     */       } 
/*     */       
/* 167 */       sb.setColor(Color.WHITE.cpy());
/*     */       
/* 169 */       sb.draw(ImageMaster.CF_RIGHT_ARROW, this.buttonX, this.buttonY, 0.0F, 0.0F, ImageMaster.CF_RIGHT_ARROW.getWidth(), ImageMaster.CF_RIGHT_ARROW.getHeight(), Settings.scale, Settings.scale, 0.0F, 0, 0, ImageMaster.CF_RIGHT_ARROW.getWidth(), ImageMaster.CF_RIGHT_ARROW.getHeight(), false, false);
/* 170 */       if (this.btnTint.a > 0.0F) {
/* 171 */         sb.setBlendFunction(770, 1);
/* 172 */         sb.setColor(this.btnTint);
/* 173 */         sb.draw(ImageMaster.CF_RIGHT_ARROW, this.buttonX, this.buttonY, 0.0F, 0.0F, ImageMaster.CF_RIGHT_ARROW.getWidth(), ImageMaster.CF_RIGHT_ARROW.getHeight(), Settings.scale, Settings.scale, 0.0F, 0, 0, ImageMaster.CF_RIGHT_ARROW.getWidth(), ImageMaster.CF_RIGHT_ARROW.getHeight(), false, false);
/* 174 */         sb.setBlendFunction(770, 771);
/*     */       } 
/*     */       
/* 177 */       sb.setColor(Color.WHITE.cpy());
/*     */       
/* 179 */       this.sparkles.forEach(q -> q.render(sb));
/*     */ 
/*     */ 
/*     */       
/* 183 */       this.togglehb.render(sb);
/* 184 */       this.hb.render(sb);
/*     */     }
/*     */     
/* 187 */     public MainMenuAd() { this.particleTimer = 0.4F;
/* 188 */       this.sparkles = new ArrayList<>();
/*     */       this.hb = new Hitbox(this.xPos, this.yPos, this.adjustedWidth, this.adjustedHeight);
/*     */       this.togglehb = new Hitbox(this.buttonX, this.buttonY, this.buttonWidth, this.buttonHeight); } public void update() {
/* 191 */       if (CardCrawlGame.mainMenuScreen.screen != MainMenuScreen.CurScreen.MAIN_MENU) {
/*     */         return;
/*     */       }
/*     */       
/* 195 */       if (!MainMenuAdPatch.popup.done) {
/*     */         return;
/*     */       }
/*     */       
/* 199 */       if (!Settings.DISABLE_EFFECTS) {
/* 200 */         this.particleTimer -= Gdx.graphics.getDeltaTime();
/* 201 */         if (this.particleTimer < 0.0F) {
/* 202 */           this.particleTimer = 0.4F;
/* 203 */           this.sparkles.add(new PrettyAdEffect(MathUtils.random(this.xPos, this.xPos + this.adjustedWidth), MathUtils.random(this.yPos, this.yPos + this.adjustedHeight)));
/*     */         } 
/*     */       } 
/*     */       
/* 207 */       this.sparkles.forEach(q -> q.update());
/*     */       
/* 209 */       this.hb.update();
/* 210 */       if (this.hb.hovered) {
/* 211 */         this.tint.a = 0.25F;
/* 212 */         if (InputHelper.justClickedLeft) {
/* 213 */           CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
/*     */           try {
/* 215 */             Desktop.getDesktop().browse(new URI(this.current.url));
/* 216 */           } catch (IOException|java.net.URISyntaxException e) {
/* 217 */             e.printStackTrace();
/*     */           } 
/*     */         } 
/*     */       } else {
/* 221 */         this.tint.a = 0.0F;
/*     */       } 
/*     */       
/* 224 */       this.togglehb.update();
/* 225 */       if (this.togglehb.hovered) {
/* 226 */         this.btnTint.a = 0.25F;
/* 227 */         if (InputHelper.justClickedLeft) {
/* 228 */           CardCrawlGame.sound.play("UI_CLICK_1");
/* 229 */           if (MainMenuAdPatch.advert.current == MainMenuAdPatch.ads.get(2)) {
/* 230 */             MainMenuAdPatch.advert.current = MainMenuAdPatch.ads.get(0);
/*     */           } else {
/* 232 */             MainMenuAdPatch.advert.current = MainMenuAdPatch.ads.get(MainMenuAdPatch.ads.indexOf(MainMenuAdPatch.advert.current) + 1);
/*     */           } 
/*     */         } 
/*     */       } else {
/* 236 */         this.btnTint.a = 0.0F;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\mainmenu\MainMenuAdPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */