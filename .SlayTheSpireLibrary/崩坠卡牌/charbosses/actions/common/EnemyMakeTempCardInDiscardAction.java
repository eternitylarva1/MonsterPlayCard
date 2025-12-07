/*    */ package charbosses.actions.common;
/*    */ 
/*    */ import charbosses.actions.vfx.cardmanip.EnemyShowCardAndAddToDiscardEffect;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ public class EnemyMakeTempCardInDiscardAction extends AbstractGameAction {
/*    */   private AbstractCard c;
/*    */   private int numCards;
/*    */   private boolean sameUUID;
/*    */   
/*    */   public EnemyMakeTempCardInDiscardAction(AbstractCard card, int amount) {
/* 18 */     UnlockTracker.markCardAsSeen(card.cardID);
/* 19 */     this.numCards = amount;
/* 20 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/* 21 */     this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
/* 22 */     this.duration = this.startDuration;
/* 23 */     this.c = card;
/* 24 */     this.sameUUID = false;
/*    */   }
/*    */   
/*    */   public EnemyMakeTempCardInDiscardAction(AbstractCard card, boolean sameUUID) {
/* 28 */     this(card, 1);
/* 29 */     this.sameUUID = sameUUID;
/* 30 */     if (!sameUUID && this.c.type != AbstractCard.CardType.CURSE && this.c.type != AbstractCard.CardType.STATUS && AbstractCharBoss.boss.hasPower("MasterRealityPower")) {
/* 31 */       this.c.upgrade();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 37 */     if (this.duration == this.startDuration) {
/* 38 */       if (this.numCards < 6) {
/* 39 */         for (int i = 0; i < this.numCards; i++) {
/* 40 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDiscardEffect(makeNewCard()));
/*    */         }
/*    */       }
/* 43 */       this.duration -= Gdx.graphics.getDeltaTime();
/*    */     } 
/* 45 */     tickDuration();
/*    */   }
/*    */   
/*    */   private AbstractCard makeNewCard() {
/* 49 */     if (this.sameUUID) {
/* 50 */       return this.c.makeSameInstanceOf();
/*    */     }
/* 52 */     return this.c.makeStatEquivalentCopy();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyMakeTempCardInDiscardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */