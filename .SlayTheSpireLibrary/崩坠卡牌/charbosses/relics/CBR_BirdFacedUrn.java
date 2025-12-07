/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.HealAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.BirdFacedUrn;
/*    */ 
/*    */ public class CBR_BirdFacedUrn extends AbstractCharbossRelic {
/*    */   public CBR_BirdFacedUrn() {
/* 14 */     super((AbstractRelic)new BirdFacedUrn());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\002' + this.DESCRIPTIONS[1];
/*    */   }
/*    */   public static final String ID = "BirdFacedUrn";
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 24 */     return new CBR_BirdFacedUrn();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 29 */     if (card.type == AbstractCard.CardType.POWER) {
/* 30 */       flash();
/* 31 */       addToTop((AbstractGameAction)new HealAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, 2));
/* 32 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_BirdFacedUrn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */