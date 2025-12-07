/*     */ package charbosses.actions.vfx.cardmanip;
/*     */ 
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
/*     */ 
/*     */ 
/*     */ public class EnemyShowCardAndAddToDiscardEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static final float EFFECT_DUR = 1.5F;
/*  19 */   private static final float PADDING = 30.0F * Settings.scale;
/*     */   
/*     */   private AbstractCard card;
/*     */ 
/*     */   
/*     */   public EnemyShowCardAndAddToDiscardEffect(AbstractCard srcCard, float x, float y) {
/*  25 */     this.card = srcCard.makeStatEquivalentCopy();
/*  26 */     this.duration = 1.5F;
/*  27 */     this.card.target_x = x;
/*  28 */     this.card.target_y = y;
/*  29 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
/*  30 */     this.card.drawScale = 0.75F;
/*  31 */     this.card.targetDrawScale = 0.75F;
/*  32 */     CardCrawlGame.sound.play("CARD_OBTAIN");
/*  33 */     if (this.card.type != AbstractCard.CardType.CURSE && this.card.type != AbstractCard.CardType.STATUS && AbstractCharBoss.boss.hasPower("MasterRealityPower")) {
/*  34 */       this.card.upgrade();
/*     */     }
/*     */   }
/*     */   
/*     */   public EnemyShowCardAndAddToDiscardEffect(AbstractCard card) {
/*  39 */     this.card = card;
/*  40 */     this.duration = 1.5F;
/*  41 */     identifySpawnLocation(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/*  42 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(card.target_x, card.target_y));
/*  43 */     card.drawScale = 0.01F;
/*  44 */     card.targetDrawScale = 1.0F;
/*  45 */     if (card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS && AbstractCharBoss.boss.hasPower("MasterRealityPower")) {
/*  46 */       card.upgrade();
/*     */     }
/*     */   }
/*     */   
/*     */   private void identifySpawnLocation(float x, float y) {
/*  51 */     int effectCount = 0;
/*  52 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/*  53 */       if (e instanceof EnemyShowCardAndAddToDiscardEffect) {
/*  54 */         effectCount++;
/*     */       }
/*     */     } 
/*  57 */     this.card.target_y = Settings.HEIGHT * 0.5F;
/*  58 */     switch (effectCount) {
/*     */       case 0:
/*  60 */         this.card.target_x = Settings.WIDTH * 0.5F;
/*     */         return;
/*     */       
/*     */       case 1:
/*  64 */         this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*     */         return;
/*     */       
/*     */       case 2:
/*  68 */         this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*     */         return;
/*     */       
/*     */       case 3:
/*  72 */         this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         return;
/*     */       
/*     */       case 4:
/*  76 */         this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         return;
/*     */     } 
/*     */     
/*  80 */     this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/*  81 */     this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  89 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  90 */     this.card.update();
/*  91 */     if (this.duration < 0.0F) {
/*  92 */       this.isDone = true;
/*  93 */       this.card.shrink();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 100 */     if (!this.isDone)
/* 101 */       this.card.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\vfx\cardmanip\EnemyShowCardAndAddToDiscardEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */