/*    */ package charbosses;
/*    */ 
/*    */ import automaton.EasyInfoDisplayPanel;
/*    */ 
/*    */ public class BossMechanicDisplayPanel
/*    */   extends EasyInfoDisplayPanel {
/*  7 */   public static String mechanicName = "";
/*  8 */   public static String mechanicDesc = "NORENDER";
/*    */   
/* 10 */   private static int X = 1550;
/* 11 */   private static int Y = 550;
/* 12 */   private static int WIDTH = 287;
/*    */   
/*    */   public BossMechanicDisplayPanel() {
/* 15 */     super(X, Y, WIDTH);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTitle() {
/* 20 */     return mechanicName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 25 */     return mechanicDesc;
/*    */   }
/*    */   
/*    */   public static void resetBossPanel() {
/* 29 */     mechanicDesc = "NORENDER";
/*    */   }
/*    */ 
/*    */   
/*    */   public EasyInfoDisplayPanel.RENDER_TIMING getTiming() {
/* 34 */     return EasyInfoDisplayPanel.RENDER_TIMING.TIMING_RENDERSUBSCRIBER;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\BossMechanicDisplayPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */