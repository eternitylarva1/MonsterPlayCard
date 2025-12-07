/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.unlock.UnlockTracker;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(cls = "com.megacrit.cardcrawl.unlock.UnlockTracker", method = "refresh")
/*    */ public class LockVillainsPatch
/*    */ {
/*    */   public static void Postfix() {
/* 20 */     UnlockTracker.addCharacter("Guardian");
/* 21 */     UnlockTracker.addCharacter("Hexaghost");
/* 22 */     UnlockTracker.addCharacter("Champ");
/* 23 */     UnlockTracker.addCharacter("Automaton");
/* 24 */     UnlockTracker.addCharacter("Collector");
/* 25 */     UnlockTracker.addCharacter("Gremlin");
/* 26 */     UnlockTracker.addCharacter("Snecko");
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\LockVillainsPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */