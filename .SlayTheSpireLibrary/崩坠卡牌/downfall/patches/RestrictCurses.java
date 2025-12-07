/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.helpers.CardLibrary;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CardLibrary.class, method = "getCurse", paramtypez = {})
/*    */ public class RestrictCurses
/*    */ {
/* 16 */   public static String[] vanillaCurses = new String[] { "Clumsy", "Decay", "Doubt", "Injury", "Normality", "Pain", "Parasite", "Regret", "Shame", "Writhe" };
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpireInsertPatch(rloc = 8, localvars = {"tmp"})
/*    */   public static void Insert(ArrayList<String> tmp) {
/* 23 */     if (!downfallMod.contentSharing_curses) {
/* 24 */       ArrayList<String> cardsToRemove = new ArrayList<>();
/*    */       int i;
/* 26 */       for (i = 0; i < tmp.size(); i++) {
/* 27 */         String c = tmp.get(i);
/* 28 */         boolean available = false;
/*    */         
/* 30 */         for (String curse : vanillaCurses) {
/* 31 */           if (c == curse) {
/* 32 */             available = true;
/*    */           }
/*    */         } 
/* 35 */         if (!available) {
/* 36 */           cardsToRemove.add(c);
/*    */         }
/*    */       } 
/* 39 */       for (i = 0; i < cardsToRemove.size(); i++)
/* 40 */         tmp.remove(cardsToRemove.get(i)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\RestrictCurses.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */