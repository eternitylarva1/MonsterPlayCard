/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.MummifiedHand;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_MummifiedHand
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "CBRMummifiedHand";
/*    */   
/*    */   public CBR_MummifiedHand() {
/* 20 */     super((AbstractRelic)new MummifiedHand());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 25 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 29 */     if (card.type == AbstractCard.CardType.POWER) {
/* 30 */       flash();
/* 31 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 39 */     return new CBR_MummifiedHand();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MummifiedHand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */