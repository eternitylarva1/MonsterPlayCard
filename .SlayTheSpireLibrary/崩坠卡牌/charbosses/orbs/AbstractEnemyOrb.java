/*     */ package charbosses.orbs;
/*     */ 
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.MathHelper;
/*     */ import com.megacrit.cardcrawl.helpers.TipHelper;
/*     */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractEnemyOrb
/*     */   extends AbstractOrb
/*     */ {
/*     */   public boolean showValues = true;
/*     */   public boolean evokeOverride = false;
/*     */   public boolean pretendLockOn = false;
/*  28 */   public int evokeMult = 0;
/*  29 */   public int pretendFocus = 0;
/*     */   
/*  31 */   public static int masterPretendFocus = 0;
/*     */ 
/*     */   
/*     */   public AbstractEnemyOrb() {
/*  35 */     this.pretendFocus = masterPretendFocus;
/*     */   }
/*     */   
/*     */   public static AbstractOrb getRandomOrb(boolean useCardRng) {
/*  39 */     ArrayList<AbstractOrb> orbs = new ArrayList<>();
/*  40 */     orbs.add(new EnemyDark());
/*  41 */     orbs.add(new EnemyFrost());
/*  42 */     orbs.add(new EnemyLightning());
/*  43 */     orbs.add(new EnemyPlasma());
/*  44 */     return useCardRng ? orbs.get(AbstractDungeon.cardRandomRng.random(orbs.size() - 1)) : orbs.get(MathUtils.random(orbs.size() - 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  49 */     this.hb.update();
/*  50 */     if (this.hb.hovered) {
/*  51 */       if (InputHelper.mX < 1400.0F * Settings.scale) {
/*  52 */         TipHelper.renderGenericTip(InputHelper.mX + 60.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, this.name, this.description);
/*     */       } else {
/*  54 */         TipHelper.renderGenericTip(InputHelper.mX - 350.0F * Settings.scale, InputHelper.mY - 50.0F * Settings.scale, this.name, this.description);
/*     */       } 
/*     */     }
/*     */     
/*  58 */     this.fontScale = MathHelper.scaleLerpSnap(this.fontScale, 0.7F);
/*     */   }
/*     */   
/*     */   public void setSlot(int slotNum, int maxOrbs) {
/*  62 */     float dist = 160.0F * Settings.scale + maxOrbs * 10.0F * Settings.scale;
/*  63 */     float angle = 100.0F + maxOrbs * 12.0F;
/*  64 */     float offsetAngle = angle / 2.0F;
/*  65 */     angle *= slotNum / (maxOrbs - 1.0F);
/*  66 */     angle += 90.0F - offsetAngle;
/*  67 */     this.tX = dist * MathUtils.cosDeg(angle) + AbstractCharBoss.boss.drawX;
/*  68 */     this.tY = -80.0F * Settings.scale + dist * MathUtils.sinDeg(angle) + AbstractCharBoss.boss.drawY + AbstractCharBoss.boss.hb_h / 2.0F;
/*  69 */     if (maxOrbs == 1) {
/*  70 */       this.tX = AbstractCharBoss.boss.drawX;
/*  71 */       this.tY = 160.0F * Settings.scale + AbstractCharBoss.boss.drawY + AbstractCharBoss.boss.hb_h / 2.0F;
/*     */     } 
/*     */     
/*  74 */     this.hb.move(this.tX, this.tY);
/*     */   }
/*     */   
/*     */   public void updateAnimation() {
/*  78 */     this.bobEffect.update();
/*  79 */     if (AbstractCharBoss.boss != null) {
/*  80 */       this.cX = MathHelper.orbLerpSnap(this.cX, AbstractCharBoss.boss.animX + this.tX);
/*  81 */       this.cY = MathHelper.orbLerpSnap(this.cY, AbstractCharBoss.boss.animY + this.tY);
/*  82 */       if (this.channelAnimTimer != 0.0F) {
/*  83 */         this.channelAnimTimer -= Gdx.graphics.getDeltaTime();
/*  84 */         if (this.channelAnimTimer < 0.0F) {
/*  85 */           this.channelAnimTimer = 0.0F;
/*     */         }
/*     */       } 
/*     */       
/*  89 */       this.c.a = Interpolation.pow2In.apply(1.0F, 0.01F, this.channelAnimTimer / 0.5F);
/*  90 */       this.scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, this.channelAnimTimer / 0.5F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void applyFocus() {
/*  95 */     if (this instanceof EnemyPlasma) {
/*     */       return;
/*     */     }
/*  98 */     if (AbstractCharBoss.boss.hasPower("Focus")) {
/*  99 */       AbstractPower power = AbstractCharBoss.boss.getPower("Focus");
/* 100 */       this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount + this.pretendFocus);
/* 101 */       this.evokeAmount = Math.max(0, this.baseEvokeAmount + power.amount + this.pretendFocus);
/*     */     } else {
/* 103 */       this.passiveAmount = Math.max(this.basePassiveAmount + this.pretendFocus, 0);
/* 104 */       this.evokeAmount = Math.max(this.baseEvokeAmount + this.pretendFocus, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyLockOn() {
/* 110 */     if (AbstractDungeon.player.hasPower("Lockon") || this.pretendLockOn) {
/* 111 */       if (this instanceof EnemyEmptyOrbSlot)
/* 112 */         return;  if (this.ID.equals("Lightning")) {
/* 113 */         this.passiveAmount = Math.max(0, (int)Math.floor(this.passiveAmount * 1.5D));
/* 114 */         this.evokeAmount = Math.max(0, (int)Math.floor(this.evokeAmount * 1.5D));
/*     */       } 
/* 116 */       if (this.ID.equals("Dark")) {
/* 117 */         this.evokeAmount = Math.max(0, (int)Math.floor(this.evokeAmount * 1.5D));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderText(SpriteBatch sb) {
/* 124 */     if (!(this instanceof EnemyEmptyOrbSlot) && this.showValues)
/* 125 */       if (this.showEvokeValue || this.evokeOverride) {
/* 126 */         FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, (this.evokeMult > 0) ? (this.evokeAmount + "x" + this.evokeMult) : Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
/*     */       } else {
/* 128 */         FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, this.c, this.fontScale);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\orbs\AbstractEnemyOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */