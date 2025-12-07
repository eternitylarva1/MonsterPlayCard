/*     */ package downfall.mainmenu;
/*     */ 
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import downfall.downfallMod;
/*     */ import downfall.util.TextureLoader;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ public class TalesAndTacticsPopup
/*     */ {
/*  18 */   private static final float POS_X = Settings.WIDTH * 0.2F;
/*  19 */   private static final float POS_Y = Settings.HEIGHT * 0.2F;
/*     */   
/*     */   public static String langFolder() {
/*  22 */     if (Settings.language == Settings.GameLanguage.ZHS || Settings.language == Settings.GameLanguage.ZHT) {
/*  23 */       return "zhs";
/*     */     }
/*  25 */     return "eng";
/*     */   }
/*     */   
/*  28 */   public static final Texture bgTex = TextureLoader.getTexture("downfallResources/images/menustuff/" + langFolder() + "/menuPopupBg.png");
/*  29 */   public static final Texture imgTex = TextureLoader.getTexture("downfallResources/images/menustuff/" + langFolder() + "/menuTNTBig.png");
/*  30 */   public static final Texture dismissBtnTex = TextureLoader.getTexture("downfallResources/images/menustuff/" + langFolder() + "/menuTNTDismiss.png");
/*  31 */   public static final Texture discordBtnTex = TextureLoader.getTexture("downfallResources/images/menustuff/" + langFolder() + "/menuDiscord.png");
/*  32 */   public static final Texture steamBtnTex = TextureLoader.getTexture("downfallResources/images/menustuff/" + langFolder() + "/menuSteam.png");
/*     */   
/*     */   private float posY;
/*  35 */   private Color popupBgColor = new Color(1.0F, 1.0F, 1.0F, 0.0F);
/*     */   private Hitbox btnHitbox;
/*     */   private Hitbox steamHitbox;
/*     */   private Hitbox discordHitbox;
/*     */   private boolean fadingIn = false;
/*     */   private boolean fadingOut = false;
/*  41 */   private float fadeTimer = 0.0F;
/*     */   private static final float FADE_TIME = 30.0F;
/*     */   public boolean done = true;
/*     */   
/*     */   public TalesAndTacticsPopup() {
/*  46 */     this.posY = Settings.HEIGHT;
/*  47 */     this.btnHitbox = new Hitbox(POS_X + 986.0F * Settings.scale, POS_Y + 600.0F * Settings.scale, dismissBtnTex.getWidth() * Settings.scale, dismissBtnTex.getHeight() * Settings.scale);
/*  48 */     this.steamHitbox = new Hitbox(POS_X + 662.0F * Settings.scale, POS_Y + 49.0F * Settings.scale, steamBtnTex.getWidth() * Settings.scale, steamBtnTex.getHeight() * Settings.scale);
/*  49 */     this.discordHitbox = new Hitbox(POS_X + 147.0F * Settings.scale, POS_Y + 46.0F * Settings.scale, discordBtnTex.getWidth() * Settings.scale, discordBtnTex.getHeight() * Settings.scale);
/*     */ 
/*     */     
/*  52 */     if (downfallMod.unseenTutorials[5]) {
/*  53 */       downfallMod.unseenTutorials[5] = false;
/*  54 */       this.done = false;
/*  55 */       this.fadingIn = true;
/*     */       try {
/*  57 */         downfallMod.saveTutorialsSeen();
/*  58 */       } catch (IOException e) {
/*  59 */         throw new RuntimeException(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  67 */     if (this.fadingIn) {
/*  68 */       this.posY = MathUtils.lerp(Settings.HEIGHT, POS_Y, this.fadeTimer / 30.0F);
/*  69 */       this.popupBgColor.a = (float)(this.popupBgColor.a + 0.025D);
/*  70 */       this.fadeTimer++;
/*  71 */       if (this.fadeTimer >= 30.0F) {
/*  72 */         this.fadingIn = false;
/*  73 */         this.fadeTimer = 0.0F;
/*     */       } 
/*  75 */     } else if (this.fadingOut) {
/*  76 */       this.posY = MathUtils.lerp(POS_Y, Settings.HEIGHT, this.fadeTimer / 30.0F);
/*  77 */       this.fadeTimer++;
/*  78 */       this.popupBgColor.a = (float)(this.popupBgColor.a - 0.025D);
/*  79 */       if (this.fadeTimer >= 30.0F) {
/*  80 */         this.fadingOut = false;
/*  81 */         this.done = true;
/*     */       } 
/*     */     } else {
/*  84 */       this.steamHitbox.update();
/*  85 */       this.btnHitbox.update();
/*  86 */       this.discordHitbox.update();
/*  87 */       if (this.steamHitbox.justHovered || this.btnHitbox.justHovered || this.discordHitbox.justHovered) {
/*  88 */         CardCrawlGame.sound.playV("UI_HOVER", 0.75F);
/*     */       }
/*  90 */       if (this.btnHitbox.hovered && InputHelper.justClickedLeft) {
/*  91 */         this.fadingOut = true;
/*  92 */         CardCrawlGame.sound.playA("UI_CLICK_1", -0.1F);
/*  93 */       } else if (this.steamHitbox.hovered && InputHelper.justClickedLeft) {
/*  94 */         CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
/*  95 */         DiscordButton.openWebpage("https://store.steampowered.com/app/1652250/Tales__Tactics/");
/*  96 */       } else if (this.discordHitbox.hovered && InputHelper.justClickedLeft) {
/*  97 */         CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
/*  98 */         DiscordButton.openWebpage("https://discord.gg/g7Bv9gttpp");
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 104 */     sb.setColor(this.popupBgColor);
/* 105 */     drawTextureScaled(sb, bgTex, 0.0F, 0.0F, 1.0F);
/* 106 */     sb.setColor(Color.WHITE);
/* 107 */     drawTextureScaled(sb, imgTex, POS_X, this.posY, 0.75F);
/* 108 */     if (!this.fadingIn && !this.fadingOut) {
/* 109 */       if (this.btnHitbox.hovered) {
/* 110 */         sb.setColor(Color.WHITE.cpy());
/*     */       } else {
/* 112 */         sb.setColor(Color.GRAY.cpy());
/*     */       } 
/* 114 */       drawTextureScaled(sb, dismissBtnTex, this.btnHitbox.x, this.btnHitbox.y, 0.8F);
/* 115 */       if (this.discordHitbox.hovered) {
/* 116 */         sb.setColor(Color.WHITE.cpy());
/*     */       } else {
/* 118 */         sb.setColor(Color.GRAY.cpy());
/*     */       } 
/* 120 */       drawTextureScaled(sb, discordBtnTex, this.discordHitbox.x, this.discordHitbox.y, 0.8F);
/* 121 */       if (this.steamHitbox.hovered) {
/* 122 */         sb.setColor(Color.WHITE.cpy());
/*     */       } else {
/* 124 */         sb.setColor(Color.GRAY.cpy());
/*     */       } 
/* 126 */       drawTextureScaled(sb, steamBtnTex, this.steamHitbox.x, this.steamHitbox.y, 0.8F);
/* 127 */       sb.setColor(Color.WHITE);
/* 128 */       this.steamHitbox.render(sb);
/* 129 */       this.btnHitbox.render(sb);
/* 130 */       this.discordHitbox.render(sb);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void drawTextureScaled(SpriteBatch sb, Texture tex, float x, float y, float scaleMod) {
/* 135 */     sb.draw(tex, x, y, 0.0F, 0.0F, tex.getWidth() * Settings.scale * scaleMod, tex.getHeight() * Settings.scale * scaleMod, 1.0F, 1.0F, 0.0F, 0, 0, tex.getWidth(), tex.getHeight(), false, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\mainmenu\TalesAndTacticsPopup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */