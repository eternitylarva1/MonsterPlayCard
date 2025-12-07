/*    */ package downfall.cardmods;
/*    */ 
/*    */ import basemod.abstracts.AbstractCardModifier;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import guardian.GuardianMod;
/*    */ import guardian.cards.AbstractGuardianCard;
/*    */ 
/*    */ 
/*    */ public class BraceMod
/*    */   extends AbstractCardModifier
/*    */ {
/*    */   public String modifyDescription(String rawDescription, AbstractCard card) {
/* 16 */     return rawDescription + (CardCrawlGame.languagePack.getUIString(GuardianMod.makeID("BraceMod"))).TEXT[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
/* 21 */     super.onUse(card, target, action);
/* 22 */     AbstractGuardianCard.brace(2);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCardModifier makeCopy() {
/* 27 */     return new BraceMod();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cardmods\BraceMod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */