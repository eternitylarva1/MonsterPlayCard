/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.MedicalKit;
/*    */ 
/*    */ public class CBR_MedicalKit
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "CBRMedicalKit";
/*    */   
/*    */   public CBR_MedicalKit() {
/* 14 */     super((AbstractRelic)new MedicalKit());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 22 */     if (card.type == AbstractCard.CardType.STATUS) {
/* 23 */       flash();
/* 24 */       card.exhaust = true;
/* 25 */       action.exhaustCard = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new CBR_MedicalKit();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MedicalKit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */