/*     */ package charbosses.orbs;
/*     */ 
/*     */ import charbosses.actions.orb.EnemyLightningOrbEvokeAction;
/*     */ import charbosses.actions.orb.EnemyLightningOrbPassiveAction;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.localization.OrbStrings;
/*     */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*     */ import com.megacrit.cardcrawl.vfx.combat.LightningOrbActivateEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.LightningOrbPassiveEffect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnemyLightning
/*     */   extends AbstractEnemyOrb
/*     */ {
/*     */   public static final String ORB_ID = "Lightning";
/*  27 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Lightning");
/*     */   private static final float PI_DIV_16 = 0.19634955F;
/*     */   private static final float ORB_WAVY_DIST = 0.05F;
/*  30 */   private float vfxTimer = 1.0F;
/*     */   private static final float PI_4 = 12.566371F;
/*     */   
/*     */   public EnemyLightning() {
/*  34 */     this.ID = "Lightning";
/*  35 */     this.img = ImageMaster.ORB_LIGHTNING;
/*  36 */     this.name = orbString.NAME;
/*  37 */     this.baseEvokeAmount = 8;
/*  38 */     this.evokeAmount = this.baseEvokeAmount;
/*  39 */     this.basePassiveAmount = 3;
/*  40 */     this.passiveAmount = this.basePassiveAmount;
/*  41 */     updateDescription();
/*  42 */     this.angle = MathUtils.random(360.0F);
/*  43 */     this.channelAnimTimer = 0.5F;
/*     */   }
/*     */   private static final float ORB_BORDER_SCALE = 1.2F;
/*     */   public void updateDescription() {
/*  47 */     applyFocus();
/*  48 */     this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + this.evokeAmount + orbString.DESCRIPTION[2];
/*     */   }
/*     */   
/*     */   public void onEvoke() {
/*  52 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new EnemyLightningOrbEvokeAction(new DamageInfo((AbstractCreature)AbstractCharBoss.boss, this.evokeAmount, DamageInfo.DamageType.THORNS), false));
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
/*     */   public void onEndOfTurn() {
/*  90 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new EnemyLightningOrbPassiveAction(new DamageInfo((AbstractCreature)AbstractCharBoss.boss, this.passiveAmount, DamageInfo.DamageType.THORNS), this, false));
/*     */   }
/*     */   
/*     */   public void triggerEvokeAnimation() {
/*  94 */     CardCrawlGame.sound.play("ORB_LIGHTNING_EVOKE", 0.1F);
/*  95 */     AbstractDungeon.effectsQueue.add(new LightningOrbActivateEffect(this.cX, this.cY));
/*     */   }
/*     */   
/*     */   public void updateAnimation() {
/*  99 */     super.updateAnimation();
/* 100 */     this.angle += Gdx.graphics.getDeltaTime() * 180.0F;
/* 101 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/* 102 */     if (this.vfxTimer < 0.0F) {
/* 103 */       AbstractDungeon.effectList.add(new LightningOrbPassiveEffect(this.cX, this.cY));
/* 104 */       if (MathUtils.randomBoolean()) {
/* 105 */         AbstractDungeon.effectList.add(new LightningOrbPassiveEffect(this.cX, this.cY));
/*     */       }
/*     */       
/* 108 */       this.vfxTimer = MathUtils.random(0.15F, 0.8F);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 113 */     this.c.a /= 2.0F;
/* 114 */     sb.setColor(this.shineColor);
/* 115 */     sb.setBlendFunction(770, 1);
/* 116 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, this.scale * 1.2F, this.angle, 0, 0, 96, 96, false, false);
/* 117 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.2F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, -this.angle, 0, 0, 96, 96, false, false);
/* 118 */     sb.setBlendFunction(770, 771);
/* 119 */     sb.setColor(this.c);
/* 120 */     sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 12.0F, 0, 0, 96, 96, false, false);
/* 121 */     renderText(sb);
/* 122 */     this.hb.render(sb);
/*     */   }
/*     */   
/*     */   public void playChannelSFX() {
/* 126 */     CardCrawlGame.sound.play("ORB_LIGHTNING_CHANNEL", 0.1F);
/*     */   }
/*     */   
/*     */   public AbstractOrb makeCopy() {
/* 130 */     return new EnemyLightning();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\orbs\EnemyLightning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */