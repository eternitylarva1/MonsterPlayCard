/*     */ package charbosses.actions.common;
/*     */ 
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.localization.UIStrings;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EnemyExhaustAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   public static final String[] TEXT;
/*  19 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction"); static {
/*  20 */     TEXT = uiStrings.TEXT;
/*     */   }
/*     */   public static int numExhausted;
/*     */   private AbstractCharBoss p;
/*     */   private boolean isRandom;
/*     */   private boolean anyNumber;
/*     */   private boolean canPickZero;
/*     */   
/*     */   public EnemyExhaustAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
/*  29 */     this.anyNumber = anyNumber;
/*  30 */     this.p = AbstractCharBoss.boss;
/*  31 */     this.canPickZero = canPickZero;
/*  32 */     this.isRandom = isRandom;
/*  33 */     this.amount = amount;
/*  34 */     float action_DUR_FAST = Settings.ACTION_DUR_FAST;
/*  35 */     this.startDuration = action_DUR_FAST;
/*  36 */     this.duration = action_DUR_FAST;
/*  37 */     this.actionType = AbstractGameAction.ActionType.EXHAUST;
/*     */   }
/*     */   
/*     */   public EnemyExhaustAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber) {
/*  41 */     this(amount, isRandom, anyNumber);
/*  42 */     this.target = target;
/*  43 */     this.source = source;
/*     */   }
/*     */   
/*     */   public EnemyExhaustAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
/*  47 */     this(amount, isRandom, false, false);
/*  48 */     this.target = target;
/*  49 */     this.source = source;
/*     */   }
/*     */   
/*     */   public EnemyExhaustAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean anyNumber, boolean canPickZero) {
/*  53 */     this(amount, isRandom, anyNumber, canPickZero);
/*  54 */     this.target = target;
/*  55 */     this.source = source;
/*     */   }
/*     */   
/*     */   public EnemyExhaustAction(boolean isRandom, boolean anyNumber, boolean canPickZero) {
/*  59 */     this(99, isRandom, anyNumber, canPickZero);
/*     */   }
/*     */   
/*     */   public EnemyExhaustAction(int amount, boolean canPickZero) {
/*  63 */     this(amount, false, false, canPickZero);
/*     */   }
/*     */   
/*     */   public EnemyExhaustAction(int amount, boolean isRandom, boolean anyNumber) {
/*  67 */     this(amount, isRandom, anyNumber, false);
/*     */   }
/*     */   
/*     */   public EnemyExhaustAction(int amount, boolean isRandom, boolean anyNumber, boolean canPickZero, float duration) {
/*  71 */     this(amount, isRandom, anyNumber, canPickZero);
/*  72 */     this.startDuration = duration;
/*  73 */     this.duration = duration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  78 */     if (this.duration == this.startDuration) {
/*  79 */       if (this.p.hand.size() == 0) {
/*  80 */         this.isDone = true;
/*     */         return;
/*     */       } 
/*  83 */       if (!this.anyNumber && this.p.hand.size() <= this.amount) {
/*  84 */         this.amount = this.p.hand.size();
/*  85 */         numExhausted = this.amount;
/*  86 */         for (int tmp = this.p.hand.size(), i = 0; i < tmp; i++) {
/*  87 */           AbstractCard c = this.p.hand.getTopCard();
/*  88 */           this.p.hand.moveToExhaustPile(c);
/*     */         } 
/*     */         return;
/*     */       } 
/*  92 */       if (this.isRandom) {
/*  93 */         this.p.hand.moveToExhaustPile(this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng));
/*     */         return;
/*     */       } 
/*  96 */       for (int j = 0; j < this.amount; j++) {
/*  97 */         AbstractCard tc = this.p.hand.getTopCard();
/*  98 */         for (AbstractCard c : this.p.hand.group) {
/*  99 */           if (c.rarity == AbstractCard.CardRarity.BASIC) {
/* 100 */             tc = c;
/*     */           }
/*     */         } 
/* 103 */         this.p.hand.moveToExhaustPile(tc);
/*     */       } 
/*     */     } 
/* 106 */     tickDuration();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyExhaustAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */