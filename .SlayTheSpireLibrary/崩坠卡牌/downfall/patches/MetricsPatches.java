/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.metrics.Metrics;
/*    */ import com.megacrit.cardcrawl.screens.GameOverScreen;
/*    */ import downfall.downfallMod;
/*    */ import java.lang.reflect.Method;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MetricsPatches
/*    */ {
/* 17 */   private static final Logger logger = LogManager.getLogger(MetricsPatches.class);
/*    */   private static final String url = "http://downfallstats.atwebpages.com/beta/";
/*    */   
/*    */   @SpirePatch(clz = GameOverScreen.class, method = "shouldUploadMetricData")
/*    */   public static class ShouldUploadMetricData {
/*    */     public static boolean Postfix(boolean returnValue) {
/* 23 */       if (downfallMod.isDownfallCharacter(AbstractDungeon.player)) {
/* 24 */         returnValue = Settings.UPLOAD_DATA;
/*    */       }
/* 26 */       return returnValue;
/*    */     } }
/*    */   
/*    */   @SpirePatch(clz = Metrics.class, method = "run")
/*    */   public static class RunPatch {
/*    */     public static void Postfix(Metrics metrics) {
/* 32 */       if (metrics.type == Metrics.MetricRequestType.UPLOAD_METRICS && downfallMod.isDownfallCharacter(AbstractDungeon.player))
/*    */         try {
/* 34 */           Method m = Metrics.class.getDeclaredMethod("sendPost", new Class[] { String.class, String.class });
/* 35 */           m.setAccessible(true);
/* 36 */           m.invoke(metrics, new Object[] { "http://downfallstats.atwebpages.com/beta/", null });
/* 37 */         } catch (NoSuchMethodException|java.lang.reflect.InvocationTargetException|IllegalAccessException e) {
/* 38 */           MetricsPatches.logger.error("Exception while sending metrics", e);
/*    */         }  
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\MetricsPatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */