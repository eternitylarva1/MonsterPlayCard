/*     */ package charbosses.actions.common;
/*     */ 
/*     */ import charbosses.actions.utility.EnemyHandCheckAction;
/*     */ import charbosses.actions.utility.EnemyShowCardAction;
/*     */ import charbosses.actions.utility.EnemyShowCardAndPoofAction;
/*     */ import charbosses.bosses.AbstractCharBoss;
/*     */ import charbosses.powers.cardpowers.EnemyReboundPower;
/*     */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*     */ import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ 
/*     */ public class EnemyUseCardAction
/*     */   extends AbstractGameAction
/*     */ {
/*     */   private static final float DUR = 0.15F;
/*     */   public AbstractCreature target;
/*     */   public boolean exhaustCard;
/*     */   public boolean returnToHand;
/*     */   public boolean reboundCard;
/*     */   private AbstractCard targetCard;
/*     */   
/*     */   public EnemyUseCardAction(AbstractCard card, AbstractCreature target) {
/*  29 */     this.target = null;
/*  30 */     this.reboundCard = false;
/*  31 */     this.targetCard = card;
/*  32 */     this.target = target;
/*  33 */     if (card.exhaustOnUseOnce || card.exhaust) {
/*  34 */       this.exhaustCard = true;
/*     */     }
/*  36 */     setValues((AbstractCreature)AbstractCharBoss.boss, null, 1);
/*  37 */     this.duration = 0.15F;
/*  38 */     for (AbstractPower p : AbstractCharBoss.boss.powers) {
/*  39 */       if (!card.dontTriggerOnUseCard && p.type != AbstractPower.PowerType.DEBUFF) {
/*  40 */         p.onUseCard(card, makeNormalCardAction());
/*     */       }
/*     */     } 
/*  43 */     for (AbstractRelic r : AbstractCharBoss.boss.relics) {
/*  44 */       if (!card.dontTriggerOnUseCard) {
/*  45 */         r.onUseCard(card, makeNormalCardAction());
/*     */       }
/*     */     } 
/*  48 */     for (AbstractCard c : AbstractCharBoss.boss.hand.group) {
/*  49 */       if (!card.dontTriggerOnUseCard) {
/*  50 */         c.triggerOnCardPlayed(card);
/*     */       }
/*     */     } 
/*  53 */     if (this.exhaustCard) {
/*  54 */       this.actionType = AbstractGameAction.ActionType.EXHAUST;
/*     */     } else {
/*  56 */       this.actionType = AbstractGameAction.ActionType.USE;
/*     */     } 
/*     */   }
/*     */   
/*     */   public EnemyUseCardAction(AbstractCard targetCard) {
/*  61 */     this(targetCard, (AbstractCreature)null);
/*     */   }
/*     */   
/*     */   public UseCardAction makeNormalCardAction() {
/*  65 */     AbstractCard cc = this.targetCard.makeStatEquivalentCopy();
/*  66 */     cc.dontTriggerOnUseCard = true;
/*  67 */     return new UseCardAction(cc, (AbstractCreature)AbstractCharBoss.boss);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  72 */     if (this.duration == 0.15F)
/*     */     {
/*  74 */       if (AbstractCharBoss.boss != null) {
/*  75 */         for (AbstractPower p : AbstractCharBoss.boss.powers) {
/*  76 */           if (!this.targetCard.dontTriggerOnUseCard && p.type != AbstractPower.PowerType.DEBUFF) {
/*     */             
/*  78 */             if (p instanceof EnemyReboundPower) {
/*     */               
/*  80 */               EnemyReboundPower eP = (EnemyReboundPower)p;
/*  81 */               eP.onAfterUse(this.targetCard, this);
/*     */             } 
/*     */ 
/*     */             
/*  85 */             p.onAfterUseCard(this.targetCard, makeNormalCardAction());
/*     */           } 
/*     */         } 
/*     */         
/*  89 */         this.targetCard.freeToPlayOnce = false;
/*  90 */         this.targetCard.isInAutoplay = false;
/*  91 */         if (this.targetCard.purgeOnUse) {
/*  92 */           addToTop((AbstractGameAction)new EnemyShowCardAndPoofAction(this.targetCard));
/*  93 */           this.isDone = true;
/*  94 */           AbstractCharBoss.boss.cardInUse = null;
/*     */           return;
/*     */         } 
/*  97 */         if (this.targetCard.type == AbstractCard.CardType.POWER) {
/*  98 */           addToTop((AbstractGameAction)new EnemyShowCardAction(this.targetCard));
/*  99 */           if (Settings.FAST_MODE) {
/* 100 */             addToTop((AbstractGameAction)new WaitAction(0.1F));
/*     */           } else {
/* 102 */             addToTop((AbstractGameAction)new WaitAction(0.7F));
/*     */           } 
/* 104 */           AbstractCharBoss.boss.hand.empower(this.targetCard);
/* 105 */           this.isDone = true;
/* 106 */           AbstractCharBoss.boss.hand.applyPowers();
/* 107 */           AbstractCharBoss.boss.hand.glowCheck();
/* 108 */           AbstractCharBoss.boss.cardInUse = null;
/*     */           return;
/*     */         } 
/* 111 */         AbstractCharBoss.boss.cardInUse = null;
/* 112 */         boolean spoonProc = false;
/* 113 */         if (this.exhaustCard && AbstractCharBoss.boss.hasRelic("Strange Spoon") && this.targetCard.type != AbstractCard.CardType.POWER) {
/* 114 */           spoonProc = AbstractDungeon.cardRandomRng.randomBoolean();
/*     */         }
/*     */ 
/*     */         
/* 118 */         if (!this.exhaustCard || spoonProc) {
/* 119 */           if (spoonProc) {
/* 120 */             AbstractCharBoss.boss.getRelic("Strange Spoon").flash();
/*     */           }
/* 122 */           if (this.reboundCard) {
/*     */ 
/*     */             
/* 125 */             AbstractCharBoss.boss.hand.moveToDeck(this.targetCard, false);
/* 126 */           } else if (this.targetCard.shuffleBackIntoDrawPile) {
/* 127 */             AbstractCharBoss.boss.hand.moveToDeck(this.targetCard, true);
/* 128 */           } else if (this.targetCard.returnToHand) {
/* 129 */             AbstractCharBoss.boss.hand.moveToHand(this.targetCard);
/* 130 */             AbstractCharBoss.boss.onCardDrawOrDiscard();
/*     */           } else {
/* 132 */             AbstractCharBoss.boss.hand.moveToDiscardPile(this.targetCard);
/*     */           } 
/*     */         } else {
/* 135 */           AbstractCharBoss.boss.hand.moveToExhaustPile(this.targetCard);
/*     */         } 
/* 137 */         this.targetCard.exhaustOnUseOnce = false;
/* 138 */         this.targetCard.dontTriggerOnUseCard = false;
/* 139 */         addToBot((AbstractGameAction)new EnemyHandCheckAction());
/*     */       } 
/*     */     }
/* 142 */     tickDuration();
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyUseCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */