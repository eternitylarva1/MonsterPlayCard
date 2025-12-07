/*    */ package charbosses.actions.utility;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
/*    */ 
/*    */ public class EnemyShowCardAndPoofAction extends AbstractGameAction {
/*    */   private static final float PURGE_DURATION = 0.2F;
/*    */   
/*    */   public EnemyShowCardAndPoofAction(AbstractCard card) {
/* 14 */     this.card = null;
/* 15 */     setValues((AbstractCreature)AbstractCharBoss.boss, null, 1);
/* 16 */     this.card = card;
/* 17 */     this.duration = 0.2F;
/* 18 */     this.actionType = AbstractGameAction.ActionType.SPECIAL;
/*    */   }
/*    */   private AbstractCard card;
/*    */   
/*    */   public void update() {
/* 23 */     if (this.duration == 0.2F) {
/* 24 */       AbstractDungeon.effectList.add(new ExhaustCardEffect(this.card));
/* 25 */       if (AbstractCharBoss.boss.limbo.contains(this.card)) {
/* 26 */         AbstractCharBoss.boss.limbo.removeCard(this.card);
/*    */       }
/* 28 */       AbstractCharBoss.boss.cardInUse = null;
/*    */     } 
/* 30 */     tickDuration();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\utility\EnemyShowCardAndPoofAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */