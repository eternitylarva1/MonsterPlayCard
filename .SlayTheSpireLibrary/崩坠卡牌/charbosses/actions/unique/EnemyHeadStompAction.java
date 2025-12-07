/*    */ package charbosses.actions.unique;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class EnemyHeadStompAction extends AbstractGameAction {
/*    */   private AbstractPlayer m;
/*    */   private int magicNumber;
/*    */   
/*    */   public EnemyHeadStompAction(AbstractPlayer monster, int vulnAmount) {
/* 18 */     this.m = monster;
/* 19 */     this.magicNumber = vulnAmount;
/*    */   }
/*    */   
/*    */   public void update() {
/* 23 */     if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2 && ((AbstractCard)AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2)).type == AbstractCard.CardType.ATTACK) {
/* 24 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new WeakPower((AbstractCreature)this.m, this.magicNumber, true), this.magicNumber));
/*    */     }
/*    */     
/* 27 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\action\\unique\EnemyHeadStompAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */