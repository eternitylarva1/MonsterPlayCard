/*     */ package downfall.patches;
/*     */ 
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*     */ import com.megacrit.cardcrawl.map.MapEdge;
/*     */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*     */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*     */ import downfall.relics.TeleportStone;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TeleportStonePatch
/*     */ {
/*     */   public static boolean isDirectlyConnectedTo(MapRoomNode start, MapRoomNode end) {
/*  18 */     for (MapEdge edge : start.getEdges()) {
/*  19 */       if (end.x == edge.dstX && end.y == edge.dstY) {
/*  20 */         return true;
/*     */       }
/*     */     } 
/*  23 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean pseudoTaken(MapEdge edge) {
/*  28 */     if (edge.taken) {
/*  29 */       return true;
/*     */     }
/*     */     
/*  32 */     if (AbstractDungeon.player.hasRelic(TeleportStone.ID)) {
/*  33 */       MapRoomNode node = getNode(edge.dstX, edge.dstY);
/*     */       
/*  35 */       return (node != null && (node.taken || node.equals(AbstractDungeon.getCurrMapNode())));
/*     */     } 
/*  37 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static MapRoomNode getNode(int x, int y) {
/*     */     try {
/*  44 */       return ((ArrayList<MapRoomNode>)CardCrawlGame.dungeon.getMap().get(y)).get(x);
/*  45 */     } catch (IndexOutOfBoundsException e) {
/*  46 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(cls = "com.megacrit.cardcrawl.map.MapRoomNode", method = "isConnectedTo")
/*     */   public static class IsConnectedTo
/*     */   {
/*  56 */     private static int depth = 0;
/*     */ 
/*     */     
/*     */     public static boolean Postfix(boolean __result, MapRoomNode __instance, MapRoomNode node) {
/*  60 */       depth++;
/*  61 */       AbstractRelic teleporter = AbstractDungeon.player.getRelic(TeleportStone.ID);
/*  62 */       if (!__result && teleporter != null && teleporter.counter > 0) {
/*  63 */         for (MapEdge edge : __instance.getEdges()) {
/*  64 */           MapRoomNode nextNode = TeleportStonePatch.getNode(edge.dstX, edge.dstY);
/*  65 */           if (nextNode != null && nextNode.isConnectedTo(node)) {
/*  66 */             depth--;
/*     */             
/*  68 */             if (node.hb.hovered) {
/*  69 */               teleporter.energyBased = true;
/*     */             }
/*  71 */             return true;
/*     */           } 
/*     */         } 
/*     */       }
/*  75 */       depth--;
/*  76 */       return __result;
/*     */     }
/*     */ 
/*     */     
/*     */     static int getNodeDistance(MapRoomNode start, MapRoomNode end) {
/*  81 */       return getNodeDistance(start, end, 1);
/*     */     }
/*     */ 
/*     */     
/*     */     private static int getNodeDistance(MapRoomNode start, MapRoomNode end, int depth) {
/*  86 */       if (start == null) {
/*  87 */         return -1;
/*     */       }
/*     */       
/*  90 */       for (MapEdge edge : start.getEdges()) {
/*  91 */         MapRoomNode nextNode = TeleportStonePatch.getNode(edge.dstX, edge.dstY);
/*  92 */         if (nextNode != null && nextNode.equals(end)) {
/*  93 */           return depth;
/*     */         }
/*  95 */         int dist = getNodeDistance(nextNode, end, depth + 1);
/*  96 */         if (dist != -1) {
/*  97 */           return dist;
/*     */         }
/*     */       } 
/* 100 */       return -1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SpirePatch(cls = "com.megacrit.cardcrawl.map.MapRoomNode", method = "playNodeSelectedSound")
/*     */   public static class NodeSelected
/*     */   {
/*     */     public static void Postfix(MapRoomNode __instance) {
/* 112 */       if (Settings.isDebug) {
/*     */         return;
/*     */       }
/*     */       
/* 116 */       AbstractRelic teleporter = AbstractDungeon.player.getRelic(TeleportStone.ID);
/* 117 */       if (teleporter != null)
/* 118 */         teleporter.onTrigger(); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\TeleportStonePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */