/*    */ package charbosses.orbs;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.localization.OrbStrings;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FrostOrbActivateEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.FrostOrbPassiveEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
/*    */ 
/*    */ public class EnemyFrost extends AbstractEnemyOrb {
/*    */   public static final String ORB_ID = "Frost";
/* 24 */   private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("Frost");
/*    */ 
/*    */   
/* 27 */   private boolean hFlip1 = MathUtils.randomBoolean();
/* 28 */   private boolean hFlip2 = MathUtils.randomBoolean();
/* 29 */   private float vfxTimer = 1.0F;
/* 30 */   private float vfxIntervalMin = 0.15F;
/* 31 */   private float vfxIntervalMax = 0.8F;
/*    */ 
/*    */   
/*    */   public EnemyFrost() {
/* 35 */     this.ID = "Frost";
/* 36 */     this.name = orbString.NAME;
/* 37 */     this.baseEvokeAmount = 5;
/* 38 */     this.evokeAmount = this.baseEvokeAmount;
/* 39 */     this.basePassiveAmount = 2;
/* 40 */     this.passiveAmount = this.basePassiveAmount;
/* 41 */     updateDescription();
/* 42 */     this.channelAnimTimer = 0.5F;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 46 */     applyFocus();
/* 47 */     this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + this.evokeAmount + orbString.DESCRIPTION[2];
/*    */   }
/*    */   
/*    */   public void onEvoke() {
/* 51 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, this.evokeAmount));
/*    */   }
/*    */   
/*    */   public void updateAnimation() {
/* 55 */     super.updateAnimation();
/* 56 */     this.angle += Gdx.graphics.getDeltaTime() * 180.0F;
/* 57 */     this.vfxTimer -= Gdx.graphics.getDeltaTime();
/* 58 */     if (this.vfxTimer < 0.0F) {
/* 59 */       AbstractDungeon.effectList.add(new FrostOrbPassiveEffect(this.cX, this.cY));
/* 60 */       if (MathUtils.randomBoolean()) {
/* 61 */         AbstractDungeon.effectList.add(new FrostOrbPassiveEffect(this.cX, this.cY));
/*    */       }
/*    */       
/* 64 */       this.vfxTimer = MathUtils.random(this.vfxIntervalMin, this.vfxIntervalMax);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onEndOfTurn() {
/* 69 */     float speedTime = 0.6F / AbstractCharBoss.boss.orbs.size();
/* 70 */     if (Settings.FAST_MODE) {
/* 71 */       speedTime = 0.0F;
/*    */     }
/*    */     
/* 74 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), speedTime));
/* 75 */     AbstractDungeon.actionManager.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, this.passiveAmount, true));
/*    */   }
/*    */   
/*    */   public void triggerEvokeAnimation() {
/* 79 */     CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.1F);
/* 80 */     AbstractDungeon.effectsQueue.add(new FrostOrbActivateEffect(this.cX, this.cY));
/*    */   }
/*    */   
/*    */   public void render(SpriteBatch sb) {
/* 84 */     sb.setColor(this.c);
/* 85 */     sb.draw(ImageMaster.FROST_ORB_RIGHT, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip1, false);
/* 86 */     sb.draw(ImageMaster.FROST_ORB_LEFT, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F - this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip1, false);
/* 87 */     sb.draw(ImageMaster.FROST_ORB_MIDDLE, this.cX - 48.0F - this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 2.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip2, false);
/* 88 */     renderText(sb);
/* 89 */     this.hb.render(sb);
/*    */   }
/*    */   
/*    */   public void playChannelSFX() {
/* 93 */     CardCrawlGame.sound.play("ORB_FROST_CHANNEL", 0.1F);
/*    */   }
/*    */   
/*    */   public AbstractOrb makeCopy() {
/* 97 */     return new EnemyFrost();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\orbs\EnemyFrost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */