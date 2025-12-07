/*    */ package downfall.patches.ui.map;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*    */ import downfall.patches.EvilModeCharacterSelect;
/*    */ import java.util.ArrayList;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = AbstractDungeon.class, method = "setEmeraldElite")
/*    */ public class EmeraldElite
/*    */ {
/* 24 */   private static final Logger logger = LogManager.getLogger(EmeraldElite.class.getName());
/*    */ 
/*    */   
/*    */   @SpirePrefixPatch
/*    */   public static SpireReturn<?> alternate() {
/* 29 */     if (Settings.isFinalActAvailable && !Settings.hasEmeraldKey && EvilModeCharacterSelect.evilMode) {
/* 30 */       ArrayList<MapRoomNode> eliteNodes = new ArrayList<>();
/*    */       int i;
/* 32 */       for (i = 0; i < AbstractDungeon.map.size() - 5; i++) {
/* 33 */         for (int j = 0; j < ((ArrayList)AbstractDungeon.map.get(i)).size(); j++) {
/* 34 */           if (((MapRoomNode)((ArrayList)AbstractDungeon.map.get(i)).get(j)).room instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite) {
/* 35 */             eliteNodes.add(((ArrayList<MapRoomNode>)AbstractDungeon.map.get(i)).get(j));
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 40 */       if (eliteNodes.isEmpty())
/*    */       {
/* 42 */         for (i = 0; i < AbstractDungeon.map.size(); i++) {
/* 43 */           for (int j = 0; j < ((ArrayList)AbstractDungeon.map.get(i)).size(); j++) {
/* 44 */             if (((MapRoomNode)((ArrayList)AbstractDungeon.map.get(i)).get(j)).room instanceof com.megacrit.cardcrawl.rooms.MonsterRoomElite) {
/* 45 */               eliteNodes.add(((ArrayList<MapRoomNode>)AbstractDungeon.map.get(i)).get(j));
/*    */             }
/*    */           } 
/*    */         } 
/*    */       }
/*    */       
/* 51 */       MapRoomNode chosenNode = eliteNodes.get(AbstractDungeon.mapRng.random(0, eliteNodes.size() - 1));
/* 52 */       chosenNode.hasEmeraldKey = true;
/* 53 */       logger.info("[INFO] Elite nodes identified: " + eliteNodes.size());
/* 54 */       logger.info("[INFO] Emerald Key  placed in: [" + chosenNode.x + "," + chosenNode.y + "]");
/*    */       
/* 56 */       return SpireReturn.Return(null);
/*    */     } 
/* 58 */     return SpireReturn.Continue();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patche\\ui\map\EmeraldElite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */