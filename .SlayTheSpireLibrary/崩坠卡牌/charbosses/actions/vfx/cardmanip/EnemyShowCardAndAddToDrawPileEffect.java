/*     */ package charbosses.actions.vfx.cardmanip;
/*     */ 
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
/*     */ 
/*     */ public class EnemyShowCardAndAddToDrawPileEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static final float EFFECT_DUR = 1.5F;
/*  19 */   private static final float PADDING = 30.0F * Settings.scale;
/*     */   
/*     */   private AbstractCard card;
/*     */   
/*     */   private boolean randomSpot;
/*     */   private boolean cardOffset;
/*     */   
/*     */   public EnemyShowCardAndAddToDrawPileEffect(AbstractCard srcCard, float x, float y, boolean randomSpot, boolean cardOffset, boolean toBottom) {
/*  27 */     this.randomSpot = false;
/*  28 */     this.cardOffset = false;
/*  29 */     this.card = srcCard.makeStatEquivalentCopy();
/*  30 */     this.cardOffset = cardOffset;
/*  31 */     this.duration = 1.5F;
/*  32 */     this.randomSpot = randomSpot;
/*  33 */     if (cardOffset) {
/*  34 */       identifySpawnLocation(x, y);
/*     */     } else {
/*  36 */       this.card.target_x = x;
/*  37 */       this.card.target_y = y;
/*     */     } 
/*  39 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
/*  40 */     this.card.drawScale = 0.01F;
/*  41 */     this.card.targetDrawScale = 1.0F;
/*  42 */     if (this.card.type != AbstractCard.CardType.CURSE && this.card.type != AbstractCard.CardType.STATUS && AbstractDungeon.player.hasPower("MasterRealityPower")) {
/*  43 */       this.card.upgrade();
/*     */     }
/*  45 */     CardCrawlGame.sound.play("CARD_OBTAIN");
/*     */   }
/*     */ 
/*     */   
/*     */   public EnemyShowCardAndAddToDrawPileEffect(AbstractCard srcCard, float x, float y, boolean randomSpot, boolean cardOffset) {
/*  50 */     this(srcCard, x, y, randomSpot, cardOffset, false);
/*     */   }
/*     */   
/*     */   public EnemyShowCardAndAddToDrawPileEffect(AbstractCard srcCard, float x, float y, boolean randomSpot) {
/*  54 */     this(srcCard, x, y, randomSpot, false);
/*     */   }
/*     */   
/*     */   public EnemyShowCardAndAddToDrawPileEffect(AbstractCard srcCard, boolean randomSpot, boolean toBottom) {
/*  58 */     this.randomSpot = false;
/*  59 */     this.cardOffset = false;
/*  60 */     this.card = srcCard.makeStatEquivalentCopy();
/*  61 */     this.duration = 1.5F;
/*  62 */     this.randomSpot = randomSpot;
/*  63 */     this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/*  64 */     this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.8F, Settings.HEIGHT * 0.2F);
/*  65 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
/*  66 */     this.card.drawScale = 0.01F;
/*  67 */     this.card.targetDrawScale = 1.0F;
/*     */   }
/*     */   
/*     */   private void identifySpawnLocation(float x, float y) {
/*  71 */     int effectCount = 0;
/*  72 */     if (this.cardOffset) {
/*  73 */       effectCount = 1;
/*     */     }
/*  75 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/*  76 */       if (e instanceof EnemyShowCardAndAddToDrawPileEffect) {
/*  77 */         effectCount++;
/*     */       }
/*     */     } 
/*  80 */     this.card.current_x = x;
/*  81 */     this.card.current_y = y;
/*  82 */     this.card.target_y = Settings.HEIGHT * 0.5F;
/*  83 */     switch (effectCount) {
/*     */       case 0:
/*  85 */         this.card.target_x = Settings.WIDTH * 0.5F;
/*     */         return;
/*     */       
/*     */       case 1:
/*  89 */         this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*     */         return;
/*     */       
/*     */       case 2:
/*  93 */         this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*     */         return;
/*     */       
/*     */       case 3:
/*  97 */         this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         return;
/*     */       
/*     */       case 4:
/* 101 */         this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         return;
/*     */     } 
/*     */     
/* 105 */     this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/* 106 */     this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/* 114 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 115 */     this.card.update();
/* 116 */     if (this.duration < 0.0F) {
/* 117 */       this.isDone = true;
/* 118 */       this.card.shrink();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 125 */     if (!this.isDone)
/* 126 */       this.card.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\vfx\cardmanip\EnemyShowCardAndAddToDrawPileEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */