/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.powers.cardpowers.EnemyPenNibPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.PenNib;
/*    */ 
/*    */ 
/*    */ public class CBR_PenNib
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Pen Nib";
/*    */   
/*    */   public CBR_PenNib() {
/* 21 */     super((AbstractRelic)new PenNib());
/* 22 */     this.counter = 0;
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 26 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 30 */     if (card.type == AbstractCard.CardType.ATTACK && card instanceof charbosses.cards.AbstractBossCard) {
/* 31 */       this.counter++;
/* 32 */       if (this.counter == 10) {
/* 33 */         this.counter = 0;
/* 34 */         flash();
/* 35 */         this.pulse = false;
/* 36 */       } else if (this.counter == 9) {
/* 37 */         beginPulse();
/* 38 */         this.pulse = true;
/* 39 */         this.owner.hand.refreshHandLayout();
/* 40 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 41 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new EnemyPenNibPower((AbstractCreature)this.owner, 1), 1, true));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 50 */     return new CBR_PenNib();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_PenNib.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */