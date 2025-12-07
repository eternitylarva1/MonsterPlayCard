/*    */ package downfall.patches;
/*    */ 
/*    */ import basemod.eventUtil.EventUtils;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.events.AbstractEvent;
/*    */ import com.megacrit.cardcrawl.helpers.EventHelper;
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = EventHelper.class, method = "getEventName")
/*    */ public class EventHelperEventNamePatch
/*    */ {
/*    */   @SpirePostfixPatch
/*    */   static String getEventName(String __result, String key) {
/* 23 */     if (!__result.equals("")) {
/* 24 */       return __result;
/*    */     }
/*    */     
/* 27 */     Class<? extends AbstractEvent> cls = EventUtils.getEventClass(key);
/* 28 */     if (cls == null) {
/* 29 */       return key + " (event class not found)";
/*    */     }
/*    */     
/*    */     try {
/* 33 */       Field field = cls.getDeclaredField("NAME");
/* 34 */       String name = (String)field.get(null);
/* 35 */       return name;
/* 36 */     } catch (NoSuchFieldException|IllegalAccessException e) {
/* 37 */       return key + " (NAME not found)";
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\EventHelperEventNamePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */