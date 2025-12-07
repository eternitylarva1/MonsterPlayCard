/*    */ package downfall.util;
/*    */ 
/*    */ import com.megacrit.cardcrawl.helpers.Prefs;
/*    */ 
/*    */ public class IndividualBetaArtEnablerPref extends Prefs {
/*    */   public IndividualBetaArtEnablerPref(Prefs parent) {
/*  7 */     this.data = parent.data;
/*  8 */     this.filepath = parent.filepath;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getBoolean(String key, boolean def) {
/* 13 */     if (isBossCard(key)) {
/* 14 */       return super.getBoolean(key.substring(18), def);
/*    */     }
/* 16 */     return super.getBoolean(key, def);
/*    */   }
/*    */   
/*    */   public boolean isBossCard(String key) {
/* 20 */     return key.contains("downfall_Charboss:");
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\IndividualBetaArtEnablerPref.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */