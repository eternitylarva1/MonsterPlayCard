/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
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
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CardCrawlGame.class, method = "saveMigration")
/*    */ public class MigrateSavePatch
/*    */ {
/*    */   @SpirePrefixPatch
/*    */   public static void Prefix(CardCrawlGame __instance) {}
/*    */   
/*    */   private static void copyDirectory(File sourceLocation, File targetLocation) throws IOException {
/* 43 */     if (sourceLocation.isDirectory()) {
/* 44 */       String[] files = sourceLocation.list();
/* 45 */       System.out.println(files.toString());
/* 46 */       for (int i = 0; i < files.length; i++) {
/* 47 */         copyDirectory(new File(sourceLocation, files[i]), new File(targetLocation, files[i]));
/*    */       }
/*    */     } else {
/*    */       
/* 51 */       System.out.println(sourceLocation);
/* 52 */       System.out.println(targetLocation);
/* 53 */       InputStream in = new FileInputStream(sourceLocation);
/* 54 */       OutputStream out = new FileOutputStream(targetLocation);
/* 55 */       byte[] buf = new byte[1024];
/*    */       int len;
/* 57 */       while ((len = in.read(buf)) > 0) {
/* 58 */         out.write(buf, 0, len);
/*    */       }
/* 60 */       in.close();
/* 61 */       out.close();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\MigrateSavePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */