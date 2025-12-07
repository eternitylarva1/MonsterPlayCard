/*     */ package charbosses.orbs;
/*     */ 
/*     */ import charbosses.actions.orb.EnemyDarkOrbEvokeAction;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.localization.OrbStrings;
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
/*     */ 
/*     */ 
/*     */ public class EnemyDark
/*     */   extends AbstractEnemyOrb
/*     */ {
/*     */   public static final String ORB_ID = "Dark";
/*     */   public static final String[] DESC;
/*  31 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Dark"); static {
/*  32 */     DESC = orbString.DESCRIPTION;
/*     */   }
/*     */   private static final float ORB_BORDER_SCALE = 1.2F;
/*     */   private static final float VFX_INTERVAL_TIME = 0.25F;
/*     */   private float vfxTimer;
/*     */   
/*     */   public EnemyDark() {
/*  39 */     this.vfxTimer = 0.5F;
/*  40 */     this.ID = "Dark";
/*  41 */     this.img = ImageMaster.ORB_DARK;
/*  42 */     this.name = orbString.NAME;
/*  43 */     this.baseEvokeAmount = 6;
/*  44 */     this.evokeAmount = this.baseEvokeAmount;
/*  45 */     this.basePassiveAmount = 6;
/*  46 */     this.passiveAmount = this.basePassiveAmount;
/*  47 */     updateDescription();
/*  48 */     this.channelAnimTimer = 0.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateDescription() {
/*  53 */     applyFocus();
/*  54 */     this.description = DESC[0] + this.passiveAmount + DESC[1] + this.evokeAmount + DESC[2];
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEvoke() {
/*  59 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new EnemyDarkOrbEvokeAction(new DamageInfo((AbstractCreature)AbstractCharBoss.boss, this.evokeAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEndOfTurn() {
/*  64 */     float speedTime = 0.6F / AbstractCharBoss.boss.orbs.size();
/*  65 */     if (Settings.FAST_MODE) {
/*  66 */       speedTime = 0.0F;
/*     */     }
/*  68 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.DARK), speedTime));
/*  69 */     this.evokeAmount += this.passiveAmount;
/*  70 */     updateDescription();
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerEvokeAnimation() {
/*  75 */     CardCrawlGame.sound.play("ORB_DARK_EVOKE", 0.1F);
/*  76 */     AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(this.cX, this.cY));
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyFocus() {
/*  81 */     AbstractPower power = AbstractCharBoss.boss.getPower("Focus");
/*  82 */     if (power != null) {
/*  83 */       this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
/*     */     } else {
/*  85 */       this.passiveAmount = this.basePassiveAmount;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateAnimation() {
/*  91 */     super.updateAnimation();
/*  92 */     this.angle += Gdx.graphics.getDeltaTime() * 120.0F;
/*  93 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/*  94 */     if (this.vfxTimer < 0.0F) {
/*  95 */       AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(this.cX, this.cY));
/*  96 */       this.vfxTimer = 0.25F;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 102 */     sb.setColor(this.c);
/* 103 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
/* 104 */     this.c.a /= 3.0F;
/* 105 */     sb.setColor(this.shineColor);
/* 106 */     sb.setBlendFunction(770, 1);
/* 107 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.2F, this.scale * 1.2F, this.angle / 1.2F, 0, 0, 96, 96, false, false);
/* 108 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.5F, this.scale * 1.5F, this.angle / 1.4F, 0, 0, 96, 96, false, false);
/* 109 */     sb.setBlendFunction(770, 771);
/* 110 */     renderText(sb);
/* 111 */     this.hb.render(sb);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void renderText(SpriteBatch sb) {
/* 116 */     if (this.showValues) {
/* 117 */       Color niceCalmBlue = new Color(0.2F, 1.0F, 1.0F, this.c.a);
/* 118 */       if (this.evokeOverride || this.showEvokeValue) {
/* 119 */         niceCalmBlue = Color.RED.cpy();
/*     */       }
/* 121 */       if (this.showEvokeValue || this.evokeOverride) {
/* 122 */         FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, (this.evokeMult > 0) ? (Integer.toString(this.evokeAmount) + "x" + Integer.toString(this.evokeMult)) : Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, niceCalmBlue, this.fontScale);
/*     */       } else {
/* 124 */         FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, niceCalmBlue, this.fontScale);
/* 125 */         FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, this.c, this.fontScale);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void playChannelSFX() {
/* 132 */     CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractOrb makeCopy() {
/* 137 */     return new EnemyDark();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\orbs\EnemyDark.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */