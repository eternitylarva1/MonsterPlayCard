/*     */ package charbosses.actions.common;
/*     */ 
/*     */ import charbosses.actions.vfx.cardmanip.EnemyShowCardAndAddToDiscardEffect;
/*     */ import charbosses.actions.vfx.cardmanip.EnemyShowCardAndAddToHandEffect;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.cards.AbstractBossCard;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*     */ 
/*     */ 
/*     */ public class EnemyMakeTempCardInHandAction
/*     */   extends AbstractGameAction
/*     */ {
/*  19 */   private static final float PADDING = 25.0F * Settings.scale;
/*     */   
/*     */   private AbstractCard c;
/*     */   
/*     */   private boolean isOtherCardInCenter;
/*     */   private boolean sameUUID;
/*     */   
/*     */   public EnemyMakeTempCardInHandAction(AbstractCard card, boolean isOtherCardInCenter) {
/*  27 */     this.isOtherCardInCenter = true;
/*  28 */     this.sameUUID = false;
/*  29 */     this.amount = 1;
/*  30 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/*  31 */     this.c = card;
/*  32 */     AbstractBossCard cB = (AbstractBossCard)this.c;
/*  33 */     cB.createIntent();
/*  34 */     if (this.c.type != AbstractCard.CardType.CURSE && this.c.type != AbstractCard.CardType.STATUS && AbstractCharBoss.boss.hasPower("MasterRealityPower")) {
/*  35 */       this.c.upgrade();
/*     */     }
/*  37 */     this.isOtherCardInCenter = isOtherCardInCenter;
/*     */   }
/*     */   
/*     */   public EnemyMakeTempCardInHandAction(AbstractCard card) {
/*  41 */     this(card, 1);
/*     */   }
/*     */   
/*     */   public EnemyMakeTempCardInHandAction(AbstractCard card, int amount) {
/*  45 */     this.isOtherCardInCenter = true;
/*  46 */     this.sameUUID = false;
/*  47 */     UnlockTracker.markCardAsSeen(card.cardID);
/*  48 */     this.amount = amount;
/*  49 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/*  50 */     this.c = card;
/*  51 */     if (this.c.type != AbstractCard.CardType.CURSE && this.c.type != AbstractCard.CardType.STATUS && AbstractCharBoss.boss.hasPower("MasterRealityPower")) {
/*  52 */       this.c.upgrade();
/*     */     }
/*     */   }
/*     */   
/*     */   public EnemyMakeTempCardInHandAction(AbstractCard card, int amount, boolean isOtherCardInCenter) {
/*  57 */     this(card, amount);
/*  58 */     this.isOtherCardInCenter = isOtherCardInCenter;
/*     */   }
/*     */   
/*     */   public EnemyMakeTempCardInHandAction(AbstractCard card, boolean isOtherCardInCenter, boolean sameUUID) {
/*  62 */     this(card, 1);
/*  63 */     this.isOtherCardInCenter = isOtherCardInCenter;
/*  64 */     this.sameUUID = sameUUID;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  69 */     if (AbstractCharBoss.boss == null || AbstractCharBoss.boss.isDead || AbstractCharBoss.boss.isDying) {
/*  70 */       this.isDone = true;
/*     */       return;
/*     */     } 
/*  73 */     if (this.amount == 0) {
/*  74 */       this.isDone = true;
/*     */       return;
/*     */     } 
/*  77 */     int discardAmount = 0;
/*  78 */     int handAmount = this.amount;
/*  79 */     if (this.amount + AbstractCharBoss.boss.hand.size() > 10) {
/*  80 */       discardAmount = this.amount + AbstractCharBoss.boss.hand.size() - 10;
/*  81 */       handAmount -= discardAmount;
/*     */     } 
/*  83 */     addToHand(handAmount);
/*  84 */     addToDiscard(discardAmount);
/*  85 */     if (this.amount > 0) {
/*  86 */       addToTop((AbstractGameAction)new WaitAction(0.8F));
/*     */     }
/*  88 */     AbstractCharBoss.boss.preApplyIntentCalculations();
/*     */     
/*  90 */     this.isDone = true;
/*     */   }
/*     */   
/*     */   private void addToHand(int handAmt) {
/*  94 */     switch (this.amount) {
/*     */       case 0:
/*     */         return;
/*     */       
/*     */       case 1:
/*  99 */         if (handAmt == 1)
/*     */         {
/*     */           
/* 102 */           if (this.isOtherCardInCenter) {
/* 103 */             AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */           } else {
/*     */             
/* 106 */             AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard()));
/*     */           } 
/*     */         }
/*     */       case 2:
/* 110 */         if (handAmt == 1) {
/* 111 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH * 0.5F, Settings.HEIGHT / 2.0F));
/*     */         
/*     */         }
/* 114 */         else if (handAmt == 2) {
/* 115 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard(), Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/* 116 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 122 */         if (handAmt == 1) {
/* 123 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */         
/*     */         }
/* 126 */         else if (handAmt == 2) {
/* 127 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard(), Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/* 128 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard(), Settings.WIDTH / 2.0F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */         
/*     */         }
/* 131 */         else if (handAmt == 3) {
/* 132 */           for (int j = 0; j < this.amount; j++) {
/* 133 */             AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard()));
/*     */           }
/*     */         } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 140 */     for (int i = 0; i < handAmt; i++) {
/* 141 */       AbstractDungeon.effectList.add(new EnemyShowCardAndAddToHandEffect(makeNewCard(), MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addToDiscard(int discardAmt) {
/* 149 */     switch (this.amount) {
/*     */       case 0:
/*     */         return;
/*     */       
/*     */       case 1:
/* 154 */         if (discardAmt == 1) {
/* 155 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH / 2.0F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT / 2.0F));
/*     */         }
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 161 */         if (discardAmt == 1) {
/* 162 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5F - PADDING + AbstractCard.IMG_WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */         
/*     */         }
/* 165 */         else if (discardAmt == 2) {
/* 166 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5F - PADDING + AbstractCard.IMG_WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/* 167 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 173 */         if (discardAmt == 1) {
/* 174 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5F));
/*     */         
/*     */         }
/* 177 */         else if (discardAmt == 2) {
/* 178 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/* 179 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5F));
/*     */         
/*     */         }
/* 182 */         else if (discardAmt == 3) {
/* 183 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5F, Settings.HEIGHT * 0.5F));
/* 184 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5F - PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5F));
/* 185 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), Settings.WIDTH * 0.5F + PADDING + AbstractCard.IMG_WIDTH, Settings.HEIGHT * 0.5F));
/*     */         } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 191 */     for (int i = 0; i < discardAmt; i++) {
/* 192 */       AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard(), MathUtils.random(Settings.WIDTH * 0.2F, Settings.WIDTH * 0.8F), MathUtils.random(Settings.HEIGHT * 0.3F, Settings.HEIGHT * 0.7F)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private AbstractCard makeNewCard() {
/* 200 */     if (this.sameUUID) {
/* 201 */       return this.c.makeSameInstanceOf();
/*     */     }
/* 203 */     return this.c.makeStatEquivalentCopy();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyMakeTempCardInHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */