/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireField;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = AbstractCard.class, method = "<class>")
/*    */ public class NoDiscardField
/*    */ {
/* 12 */   public static SpireField<Boolean> noDiscard = new SpireField(() -> Boolean.valueOf(false));
/*    */   public static boolean freeCard = false;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\NoDiscardField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */