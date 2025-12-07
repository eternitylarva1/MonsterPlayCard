/*    */ package charbosses.actions.common;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnemyDiscardAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public static final String[] TEXT;
/* 19 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction"); private static final float DURATION; static {
/* 20 */     TEXT = uiStrings.TEXT;
/* 21 */     DURATION = Settings.ACTION_DUR_XFAST;
/*    */   }
/*    */   public static int numDiscarded;
/*    */   private AbstractCharBoss p;
/*    */   private boolean endTurn;
/*    */   
/*    */   public EnemyDiscardAction(AbstractCreature target, AbstractCreature source, int amount) {
/* 28 */     this(target, source, amount, false);
/*    */   }
/*    */   
/*    */   public EnemyDiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean endTurn) {
/* 32 */     this.p = (AbstractCharBoss)target;
/* 33 */     setValues(target, source, amount);
/* 34 */     this.actionType = AbstractGameAction.ActionType.DISCARD;
/* 35 */     this.endTurn = endTurn;
/* 36 */     this.duration = DURATION;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 41 */     if (this.duration == DURATION) {
/* 42 */       if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
/* 43 */         this.isDone = true;
/*    */         return;
/*    */       } 
/* 46 */       if (this.p.hand.size() <= this.amount) {
/* 47 */         this.amount = this.p.hand.size();
/* 48 */         for (int tmp = this.p.hand.size(), i = 0; i < tmp; i++) {
/* 49 */           AbstractCard c = this.p.hand.getTopCard();
/* 50 */           this.p.hand.moveToDiscardPile(c);
/* 51 */           if (!this.endTurn) {
/* 52 */             c.triggerOnManualDiscard();
/*    */           }
/*    */         } 
/* 55 */         this.p.hand.applyPowers();
/* 56 */         tickDuration();
/*    */         return;
/*    */       } 
/* 59 */       for (int j = 0; j < this.amount; j++) {
/* 60 */         AbstractCard c2 = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
/* 61 */         this.p.hand.moveToDiscardPile(c2);
/* 62 */         c2.triggerOnManualDiscard();
/*    */       } 
/*    */     } 
/* 65 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyDiscardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */