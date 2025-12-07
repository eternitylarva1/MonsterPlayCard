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
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*     */ import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnemyShowCardAndAddToHandEffect
/*     */   extends AbstractGameEffect
/*     */ {
/*     */   private static final float EFFECT_DUR = 0.8F;
/*  22 */   private static final float PADDING = 25.0F * Settings.scale;
/*     */   
/*     */   private AbstractCard card;
/*     */ 
/*     */   
/*     */   public EnemyShowCardAndAddToHandEffect(AbstractCard card, float offsetX, float offsetY) {
/*  28 */     this.card = card;
/*  29 */     UnlockTracker.markCardAsSeen(card.cardID);
/*  30 */     card.current_x = Settings.WIDTH / 2.0F;
/*  31 */     card.current_y = Settings.HEIGHT / 2.0F;
/*  32 */     card.target_x = offsetX;
/*  33 */     card.target_y = offsetY;
/*  34 */     this.duration = 0.8F;
/*  35 */     card.drawScale = 0.75F;
/*  36 */     card.targetDrawScale = 0.75F;
/*  37 */     card.transparency = 0.01F;
/*  38 */     card.targetTransparency = 1.0F;
/*  39 */     card.fadingOut = false;
/*  40 */     playCardObtainSfx();
/*  41 */     if (card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS && AbstractCharBoss.boss.hasPower("MasterRealityPower")) {
/*  42 */       card.upgrade();
/*     */     }
/*  44 */     card.untip();
/*  45 */     ArrayList<AbstractCard> group = AbstractCharBoss.boss.hand.group;
/*  46 */     if (card.name.equals("Smite")) {
/*  47 */       group.add(group.size(), card);
/*     */     } else {
/*     */       
/*  50 */       group.add(0, card);
/*     */     } 
/*  52 */     card.triggerWhenCopied();
/*  53 */     AbstractCharBoss.boss.hand.refreshHandLayout();
/*  54 */     AbstractCharBoss.boss.hand.applyPowers();
/*  55 */     AbstractCharBoss.boss.onCardDrawOrDiscard();
/*  56 */     if (AbstractCharBoss.boss.hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {
/*  57 */       card.setCostForTurn(-9);
/*     */     }
/*     */   }
/*     */   
/*     */   public EnemyShowCardAndAddToHandEffect(AbstractCard card) {
/*  62 */     this.card = card;
/*  63 */     identifySpawnLocation(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/*  64 */     this.duration = 0.8F;
/*  65 */     card.drawScale = 0.75F;
/*  66 */     card.targetDrawScale = 0.75F;
/*  67 */     card.transparency = 0.01F;
/*  68 */     card.targetTransparency = 1.0F;
/*  69 */     card.fadingOut = false;
/*  70 */     if (card.type != AbstractCard.CardType.CURSE && card.type != AbstractCard.CardType.STATUS && AbstractCharBoss.boss.hasPower("MasterRealityPower")) {
/*  71 */       card.upgrade();
/*     */     }
/*  73 */     card.untip();
/*  74 */     AbstractCharBoss.boss.hand.group.add(0, card);
/*  75 */     card.triggerWhenCopied();
/*  76 */     AbstractCharBoss.boss.hand.refreshHandLayout();
/*  77 */     AbstractCharBoss.boss.hand.applyPowers();
/*  78 */     AbstractCharBoss.boss.onCardDrawOrDiscard();
/*  79 */     if (AbstractCharBoss.boss.hasPower("Corruption") && card.type == AbstractCard.CardType.SKILL) {
/*  80 */       card.setCostForTurn(-9);
/*     */     }
/*     */   }
/*     */   
/*     */   private void playCardObtainSfx() {
/*  85 */     int effectCount = 0;
/*  86 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/*  87 */       if (e instanceof EnemyShowCardAndAddToHandEffect) {
/*  88 */         effectCount++;
/*     */       }
/*     */     } 
/*  91 */     if (effectCount < 2) {
/*  92 */       CardCrawlGame.sound.play("CARD_OBTAIN");
/*     */     }
/*     */   }
/*     */   
/*     */   private void identifySpawnLocation(float x, float y) {
/*  97 */     int effectCount = 0;
/*  98 */     for (AbstractGameEffect e : AbstractDungeon.effectList) {
/*  99 */       if (e instanceof EnemyShowCardAndAddToHandEffect) {
/* 100 */         effectCount++;
/*     */       }
/*     */     } 
/* 103 */     this.card.target_y = Settings.HEIGHT * 0.5F;
/* 104 */     switch (effectCount) {
/*     */       case 0:
/* 106 */         this.card.target_x = Settings.WIDTH * 0.5F;
/*     */         break;
/*     */       
/*     */       case 1:
/* 110 */         this.card.target_x = Settings.WIDTH * 0.5F - PADDING - AbstractCard.IMG_WIDTH;
/*     */         break;
/*     */       
/*     */       case 2:
/* 114 */         this.card.target_x = Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH;
/*     */         break;
/*     */       
/*     */       case 3:
/* 118 */         this.card.target_x = Settings.WIDTH * 0.5F - (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         break;
/*     */       
/*     */       case 4:
/* 122 */         this.card.target_x = Settings.WIDTH * 0.5F + (PADDING + AbstractCard.IMG_WIDTH) * 2.0F;
/*     */         break;
/*     */       
/*     */       default:
/* 126 */         this.card.target_x = MathUtils.random(Settings.WIDTH * 0.1F, Settings.WIDTH * 0.9F);
/* 127 */         this.card.target_y = MathUtils.random(Settings.HEIGHT * 0.2F, Settings.HEIGHT * 0.8F);
/*     */         break;
/*     */     } 
/*     */     
/* 131 */     this.card.current_x = this.card.target_x;
/* 132 */     this.card.current_y = this.card.target_y - 200.0F * Settings.scale;
/* 133 */     AbstractDungeon.effectsQueue.add(new CardPoofEffect(this.card.target_x, this.card.target_y));
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 138 */     this.duration -= Gdx.graphics.getDeltaTime();
/* 139 */     this.card.update();
/* 140 */     if (this.duration < 0.0F) {
/* 141 */       this.isDone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(SpriteBatch sb) {
/* 147 */     if (!this.isDone)
/* 148 */       this.card.render(sb); 
/*     */   }
/*     */   
/*     */   public void dispose() {}
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\vfx\cardmanip\EnemyShowCardAndAddToHandEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */