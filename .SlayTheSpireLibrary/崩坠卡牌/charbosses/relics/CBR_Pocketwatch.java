/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Pocketwatch;
/*    */ 
/*    */ public class CBR_Pocketwatch extends AbstractCharbossRelic {
/*    */   public static final String ID = "Pocketwatch";
/*    */   private boolean firstTurn = true;
/*    */   
/*    */   public CBR_Pocketwatch() {
/* 13 */     super((AbstractRelic)new Pocketwatch());
/* 14 */     this.counter = 0;
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atPreBattle() {
/* 24 */     this.counter = 0;
/* 25 */     this.firstTurn = true;
/* 26 */     beginLongPulse();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStartPostDraw() {
/* 31 */     if (this.counter > 3 || this.firstTurn)
/*    */     {
/*    */       
/* 34 */       this.firstTurn = false;
/*    */     }
/*    */     
/* 37 */     this.counter = 0;
/* 38 */     beginLongPulse();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
/* 43 */     this.counter++;
/* 44 */     if (this.counter > 3) {
/* 45 */       stopPulse();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 51 */     return new CBR_Pocketwatch();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Pocketwatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */