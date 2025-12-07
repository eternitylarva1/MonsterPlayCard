/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.dungeons.TheEnding;
/*    */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import downfall.rooms.HeartShopRoom;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = TheEnding.class, method = "generateSpecialMap")
/*    */ public class SwapOutShop
/*    */ {
/*    */   public static void Postfix() {
/* 16 */     if (EvilModeCharacterSelect.evilMode)
/* 17 */       for (ArrayList<MapRoomNode> nodes : (Iterable<ArrayList<MapRoomNode>>)AbstractDungeon.map) {
/* 18 */         for (MapRoomNode node : nodes) {
/* 19 */           if (node.getRoom() instanceof com.megacrit.cardcrawl.rooms.ShopRoom)
/* 20 */             node.setRoom((AbstractRoom)new HeartShopRoom()); 
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\SwapOutShop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */