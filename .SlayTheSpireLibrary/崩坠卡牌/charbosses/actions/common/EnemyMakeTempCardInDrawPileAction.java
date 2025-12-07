/*    */ package charbosses.actions.common;
/*    */ 
/*    */ import charbosses.actions.vfx.cardmanip.EnemyShowCardAndAddToDrawPileEffect;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ public class EnemyMakeTempCardInDrawPileAction extends AbstractGameAction {
/*    */   private AbstractCard cardToMake;
/*    */   private boolean randomSpot;
/*    */   private boolean autoPosition;
/*    */   private boolean toBottom;
/*    */   private float x;
/*    */   private float y;
/*    */   
/*    */   public EnemyMakeTempCardInDrawPileAction(AbstractCard card, int amount, boolean randomSpot, boolean autoPosition, boolean toBottom, float cardX, float cardY) {
/* 21 */     UnlockTracker.markCardAsSeen(card.cardID);
/* 22 */     setValues(this.target, this.source, amount);
/* 23 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/* 24 */     this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
/* 25 */     this.duration = this.startDuration;
/* 26 */     this.cardToMake = card;
/* 27 */     this.randomSpot = randomSpot;
/* 28 */     this.autoPosition = autoPosition;
/* 29 */     this.toBottom = toBottom;
/* 30 */     this.x = cardX;
/* 31 */     this.y = cardY;
/*    */   }
/*    */   
/*    */   public EnemyMakeTempCardInDrawPileAction(AbstractCard card, int amount, boolean randomSpot, boolean autoPosition, boolean toBottom) {
/* 35 */     this(card, amount, randomSpot, autoPosition, toBottom, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F);
/*    */   }
/*    */   
/*    */   public EnemyMakeTempCardInDrawPileAction(AbstractCard card, int amount, boolean shuffleInto, boolean autoPosition) {
/* 39 */     this(card, amount, shuffleInto, autoPosition, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 44 */     if (this.duration == this.startDuration) {
/* 45 */       if (this.amount < 6) {
/* 46 */         for (int i = 0; i < this.amount; i++) {
/* 47 */           AbstractCard c = this.cardToMake.makeStatEquivalentCopy();
/* 48 */           if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS && AbstractCharBoss.boss.hasPower("MasterRealityPower")) {
/* 49 */             c.upgrade();
/*    */           }
/* 51 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDrawPileEffect(c, this.x, this.y, this.randomSpot, this.autoPosition, this.toBottom));
/*    */         } 
/*    */       } else {
/* 54 */         for (int i = 0; i < this.amount; i++) {
/* 55 */           AbstractCard c = this.cardToMake.makeStatEquivalentCopy();
/* 56 */           if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS && AbstractCharBoss.boss.hasPower("MasterRealityPower")) {
/* 57 */             c.upgrade();
/*    */           }
/* 59 */           AbstractDungeon.effectList.add(new EnemyShowCardAndAddToDrawPileEffect(c, this.randomSpot, this.toBottom));
/*    */         } 
/*    */       } 
/* 62 */       this.duration -= Gdx.graphics.getDeltaTime();
/*    */     } 
/* 64 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\actions\common\EnemyMakeTempCardInDrawPileAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */