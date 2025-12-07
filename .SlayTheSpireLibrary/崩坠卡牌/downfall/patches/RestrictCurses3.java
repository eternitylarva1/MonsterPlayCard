/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*    */ import com.megacrit.cardcrawl.random.Random;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CardLibrary.class, method = "getCurse", paramtypez = {AbstractCard.class, Random.class})
/*    */ public class RestrictCurses3
/*    */ {
/* 20 */   public static String[] vanillaCurses = new String[] { "Clumsy", "Decay", "Doubt", "Injury", "Normality", "Pain", "Parasite", "Regret", "Shame", "Writhe" };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpireInsertPatch(rloc = 8, localvars = {"tmp"})
/*    */   public static void Insert(ArrayList<String> tmp) {
/* 27 */     if (!downfallMod.contentSharing_curses) {
/* 28 */       ArrayList<String> cardsToRemove = new ArrayList<>();
/*    */       int i;
/* 30 */       for (i = 0; i < tmp.size(); i++) {
/* 31 */         String c = tmp.get(i);
/* 32 */         boolean available = false;
/*    */         
/* 34 */         for (String curse : vanillaCurses) {
/* 35 */           if (c == curse) {
/* 36 */             available = true;
/*    */           }
/*    */         } 
/* 39 */         if (!available) {
/* 40 */           cardsToRemove.add(c);
/*    */         }
/*    */       } 
/* 43 */       for (i = 0; i < cardsToRemove.size(); i++)
/* 44 */         tmp.remove(cardsToRemove.get(i)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\RestrictCurses3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */