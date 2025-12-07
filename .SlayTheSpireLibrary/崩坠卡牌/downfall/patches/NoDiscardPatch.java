/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.CardGroup;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import javassist.CannotCompileException;
/*    */ import javassist.expr.ExprEditor;
/*    */ import javassist.expr.MethodCall;
/*    */ import theHexaghost.actions.LimboToHandAction;
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = UseCardAction.class, method = "update")
/*    */ public class NoDiscardPatch
/*    */ {
/*    */   public static ExprEditor Instrument() {
/* 19 */     return new ExprEditor()
/*    */       {
/*    */         public void edit(MethodCall m) throws CannotCompileException {
/* 22 */           if (m.getClassName().equals(CardGroup.class.getName()) && m.getMethodName().equals("moveToDiscardPile")) {
/* 23 */             m.replace("if (" + NoDiscardPatch.class.getName() + ".Do($1)) {$_ = $proceed($$);}");
/*    */           }
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean Do(AbstractCard card) {
/* 33 */     if (((Boolean)NoDiscardField.noDiscard.get(card)).booleanValue()) {
/* 34 */       if (NoDiscardField.freeCard) {
/* 35 */         card.freeToPlayOnce = true;
/* 36 */         NoDiscardField.freeCard = false;
/*    */       } 
/* 38 */       NoDiscardField.noDiscard.set(card, Boolean.valueOf(false));
/* 39 */       AbstractDungeon.player.limbo.addToTop(card);
/* 40 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new LimboToHandAction(card));
/* 41 */       return false;
/*    */     } 
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\NoDiscardPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */