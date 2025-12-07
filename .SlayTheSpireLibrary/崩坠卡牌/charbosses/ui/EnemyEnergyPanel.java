/*     */ package charbosses.ui;
/*     */ 
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.vfx.EnemyRefreshEnergyEffect;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.BitmapFont;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.Hitbox;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*     */ import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.RefreshEnergyEffect;
/*     */ import downfall.downfallMod;
/*     */ 
/*     */ public class EnemyEnergyPanel extends AbstractPanel {
/*     */   public static final String[] MSG;
/*  30 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(downfallMod.makeID("EnergyPanelTip")); public static final String[] LABEL; public static final float FONT_POP_SCALE = 2.0F;
/*     */   public static final float ENERGY_VFX_TIME = 2.0F;
/*     */   private static final int RAW_W = 256;
/*     */   public static final Color ENERGY_TEXT_COLOR;
/*     */   private static final float VFX_ROTATE_SPEED = -30.0F;
/*     */   public static float fontScale;
/*     */   public static int totalCount;
/*     */   public static float energyVfxTimer;
/*     */   
/*     */   static {
/*  40 */     MSG = uiStrings.TEXT;
/*  41 */     LABEL = uiStrings.EXTRA_TEXT;
/*  42 */     ENERGY_TEXT_COLOR = new Color(1.0F, 1.0F, 0.86F, 1.0F);
/*  43 */     fontScale = 1.0F;
/*  44 */     totalCount = 0;
/*  45 */     energyVfxTimer = 0.0F;
/*     */   }
/*     */   public BitmapFont energyNumFont;
/*     */   private Hitbox tipHitbox;
/*     */   public Texture gainEnergyImg;
/*     */   private float energyVfxAngle;
/*     */   private float energyVfxScale;
/*     */   private Color energyVfxColor;
/*     */   private AbstractCharBoss owner;
/*     */   
/*     */   public EnemyEnergyPanel(AbstractCharBoss owner) {
/*  56 */     super(Settings.WIDTH - 198.0F * Settings.scale, Settings.HEIGHT - 190.0F * Settings.scale, -480.0F * Settings.scale, 200.0F * Settings.scale, 12.0F * Settings.scale, -12.0F * Settings.scale, null, false);
/*  57 */     this.tipHitbox = new Hitbox(0.0F, 0.0F, 120.0F * Settings.scale, 120.0F * Settings.scale);
/*  58 */     this.energyVfxAngle = 0.0F;
/*  59 */     this.energyVfxScale = Settings.scale;
/*  60 */     this.energyVfxColor = Color.WHITE.cpy();
/*  61 */     this.owner = owner;
/*  62 */     switch (this.owner.chosenClass) {
/*     */       case DEFECT:
/*  64 */         this.gainEnergyImg = ImageMaster.BLUE_ORB_FLASH_VFX;
/*  65 */         this.energyNumFont = FontHelper.energyNumFontBlue;
/*     */         return;
/*     */       case IRONCLAD:
/*  68 */         this.gainEnergyImg = ImageMaster.RED_ORB_FLASH_VFX;
/*  69 */         this.energyNumFont = FontHelper.energyNumFontRed;
/*     */         return;
/*     */       case THE_SILENT:
/*  72 */         this.gainEnergyImg = ImageMaster.GREEN_ORB_FLASH_VFX;
/*  73 */         this.energyNumFont = FontHelper.energyNumFontGreen;
/*     */         return;
/*     */       case WATCHER:
/*  76 */         this.gainEnergyImg = ImageMaster.PURPLE_ORB_FLASH_VFX;
/*  77 */         this.energyNumFont = FontHelper.energyNumFontPurple;
/*     */         return;
/*     */     } 
/*  80 */     this.gainEnergyImg = ImageMaster.RED_ORB_FLASH_VFX;
/*  81 */     this.energyNumFont = FontHelper.energyNumFontRed;
/*     */   }
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
/*     */   public static void setEnergy(int energy) {
/*  95 */     totalCount = energy;
/*  96 */     AbstractDungeon.effectsQueue.add(new RefreshEnergyEffect());
/*  97 */     energyVfxTimer = 2.0F;
/*  98 */     fontScale = 2.0F;
/*     */   }
/*     */   
/*     */   public static void addEnergy(int e) {
/* 102 */     totalCount += e;
/* 103 */     if (totalCount >= 9) {
/* 104 */       UnlockTracker.unlockAchievement("ADRENALINE");
/*     */     }
/* 106 */     if (totalCount > 999) {
/* 107 */       totalCount = 999;
/*     */     }
/* 109 */     AbstractDungeon.effectsQueue.add(new EnemyRefreshEnergyEffect());
/* 110 */     fontScale = 2.0F;
/* 111 */     energyVfxTimer = 2.0F;
/*     */   }
/*     */   
/*     */   public static void useEnergy(int e) {
/* 115 */     totalCount -= e;
/* 116 */     if (totalCount < 0) {
/* 117 */       totalCount = 0;
/*     */     }
/* 119 */     if (e != 0) {
/* 120 */       fontScale = 2.0F;
/*     */     }
/*     */   }
/*     */   
/*     */   public int getCurrentEnergy() {
/* 125 */     if (AbstractDungeon.player == null) {
/* 126 */       return 0;
/*     */     }
/* 128 */     return totalCount;
/*     */   }
/*     */   
/*     */   public void update() {
/* 132 */     this.owner.energyOrb.updateOrb(totalCount);
/* 133 */     updateVfx();
/* 134 */     if (fontScale != 1.0F) {
/* 135 */       fontScale = MathHelper.scaleLerpSnap(fontScale, 1.0F);
/*     */     }
/* 137 */     this.tipHitbox.update();
/* 138 */     if (this.tipHitbox.hovered && !AbstractDungeon.isScreenUp) {
/* 139 */       AbstractDungeon.overlayMenu.hoveredTip = true;
/*     */     }
/* 141 */     if (Settings.isDebug) {
/* 142 */       if (InputHelper.scrolledDown) {
/* 143 */         addEnergy(1);
/* 144 */       } else if (InputHelper.scrolledUp && totalCount > 0) {
/* 145 */         useEnergy(1);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateVfx() {
/* 151 */     if (energyVfxTimer != 0.0F) {
/* 152 */       this.energyVfxColor.a = Interpolation.exp10In.apply(0.5F, 0.0F, 1.0F - energyVfxTimer / 2.0F);
/* 153 */       this.energyVfxAngle += Gdx.graphics.getDeltaTime() * -30.0F;
/* 154 */       this.energyVfxScale = Settings.scale * Interpolation.exp10In.apply(1.0F, 0.1F, 1.0F - energyVfxTimer / 2.0F);
/* 155 */       energyVfxTimer -= Gdx.graphics.getDeltaTime();
/* 156 */       if (energyVfxTimer < 0.0F) {
/* 157 */         energyVfxTimer = 0.0F;
/* 158 */         this.energyVfxColor.a = 0.0F;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 165 */     if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
/* 166 */       this.tipHitbox.move(this.current_x, this.current_y);
/* 167 */       renderOrb(sb);
/* 168 */       renderVfx(sb);
/* 169 */       String energyMsg = totalCount + "/" + this.owner.energy.energy;
/* 170 */       AbstractDungeon.player.getEnergyNumFont().getData().setScale(fontScale);
/* 171 */       FontHelper.renderFontCentered(sb, this.energyNumFont, energyMsg, this.current_x, this.current_y, ENERGY_TEXT_COLOR);
/* 172 */       this.tipHitbox.render(sb);
/* 173 */       if (this.tipHitbox.hovered && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp) {
/* 174 */         TipHelper.renderGenericTip(1550.0F * Settings.scale, 750.0F * Settings.scale, LABEL[0], MSG[0]);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderOrb(SpriteBatch sb) {
/* 180 */     if (totalCount == 0) {
/* 181 */       this.owner.energyOrb.renderOrb(sb, false, this.current_x, this.current_y);
/*     */     } else {
/* 183 */       this.owner.energyOrb.renderOrb(sb, true, this.current_x, this.current_y);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void renderVfx(SpriteBatch sb) {
/* 188 */     if (energyVfxTimer != 0.0F) {
/* 189 */       sb.setBlendFunction(770, 1);
/* 190 */       sb.setColor(this.energyVfxColor);
/* 191 */       sb.draw(this.gainEnergyImg, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.energyVfxScale, this.energyVfxScale, -this.energyVfxAngle + 50.0F, 0, 0, 256, 256, true, false);
/* 192 */       sb.draw(this.gainEnergyImg, this.current_x - 128.0F, this.current_y - 128.0F, 128.0F, 128.0F, 256.0F, 256.0F, this.energyVfxScale, this.energyVfxScale, this.energyVfxAngle, 0, 0, 256, 256, false, false);
/* 193 */       sb.setBlendFunction(770, 771);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosse\\ui\EnemyEnergyPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */