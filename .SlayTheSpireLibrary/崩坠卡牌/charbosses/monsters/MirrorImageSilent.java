/*     */ package charbosses.monsters;
/*     */ 
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.bosses.Silent.CharBossSilent;
/*     */ import charbosses.vfx.QuietSpecialSmokeBombEffect;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.esotericsoftware.spine.AnimationState;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*     */ import downfall.downfallMod;
/*     */ 
/*     */ public class MirrorImageSilent extends AbstractMonster {
/*  18 */   public static final String ID = downfallMod.makeID("MirrorImageSilent");
/*  19 */   public static final String NAME = (CardCrawlGame.languagePack.getCharacterString("Silent")).NAMES[0];
/*     */   float particleTimer;
/*     */   
/*  22 */   public MirrorImageSilent(float x, float y) { super(NAME, ID, 1, -4.0F, -16.0F, 240.0F, 290.0F, null, x, y, false);
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
/*  41 */     this.particleTimer = 0.0125F; this.type = AbstractMonster.EnemyType.NORMAL; loadAnimation("images/characters/theSilent/idle/skeleton.atlas", "images/characters/theSilent/idle/skeleton.json", 1.0F);
/*     */     AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
/*     */     this.stateData.setMix("Hit", "Idle", 0.1F);
/*     */     this.flipHorizontal = true;
/*  45 */     e.setTimeScale(0.9F); } public void update() { super.update();
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
/*  61 */     if (!this.isDead) {
/*  62 */       this.particleTimer -= Gdx.graphics.getDeltaTime();
/*  63 */       if (this.particleTimer <= 0.0F) {
/*  64 */         this.particleTimer = 0.0125F;
/*  65 */         AbstractDungeon.effectsQueue.add(new QuietSpecialSmokeBombEffect(AbstractDungeon.cardRandomRng.random(this.healthHb.x, this.healthHb.x + this.hb.width), this.hb.y));
/*     */       } 
/*     */     }  }
/*     */   
/*     */   public void init() {
/*     */     super.init();
/*     */     boolean swap = AbstractDungeon.cardRandomRng.randomBoolean();
/*     */     if (swap)
/*     */       CharBossSilent.swapCreature((AbstractCreature)CharBossSilent.boss, (AbstractCreature)this); 
/*     */   }
/*     */   public void flashIntent() {}
/*     */   public void render(SpriteBatch sb) {
/*  77 */     if (!this.isDead) {
/*  78 */       super.render(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderHealth(SpriteBatch sb) {
/*  84 */     if (AbstractCharBoss.boss != null && 
/*  85 */       !((CharBossSilent)AbstractCharBoss.boss).foggy) {
/*  86 */       super.renderHealth(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderPowerTips(SpriteBatch sb) {
/*  93 */     if (AbstractCharBoss.boss != null && 
/*  94 */       !((CharBossSilent)AbstractCharBoss.boss).foggy) {
/*  95 */       super.renderPowerTips(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderTip(SpriteBatch sb) {
/* 102 */     if (AbstractCharBoss.boss != null && 
/* 103 */       !((CharBossSilent)AbstractCharBoss.boss).foggy) {
/* 104 */       super.renderTip(sb);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SpireOverride
/*     */   protected void renderName(SpriteBatch sb) {
/* 111 */     if (AbstractCharBoss.boss != null && 
/* 112 */       !((CharBossSilent)AbstractCharBoss.boss).foggy) {
/* 113 */       SpireSuper.call(new Object[] { sb });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeTurn() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void getMove(int num) {
/* 126 */     setMove((byte)0, AbstractMonster.Intent.NONE);
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\monsters\MirrorImageSilent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */