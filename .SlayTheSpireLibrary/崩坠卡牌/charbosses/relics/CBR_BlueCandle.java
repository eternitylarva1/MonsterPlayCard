/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.LoseHPAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.BlueCandle;
/*    */ 
/*    */ public class CBR_BlueCandle
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_BlueCandle() {
/* 14 */     super((AbstractRelic)new BlueCandle());
/*    */   }
/*    */   public static final String ID = "BlueCandle";
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 22 */     if (card.type == AbstractCard.CardType.CURSE) {
/* 23 */       flash();
/* 24 */       addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, 1, AbstractGameAction.AttackEffect.FIRE));
/* 25 */       card.exhaust = true;
/* 26 */       action.exhaustCard = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new CBR_BlueCandle();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_BlueCandle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */