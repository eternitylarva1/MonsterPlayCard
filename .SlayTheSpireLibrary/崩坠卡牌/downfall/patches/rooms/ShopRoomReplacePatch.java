/*    */ package downfall.patches.rooms;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.ByRef;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.EventHelper;
/*    */ import com.megacrit.cardcrawl.map.MapRoomNode;
/*    */ import com.megacrit.cardcrawl.map.RoomTypeAssigner;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import downfall.patches.EvilModeCharacterSelect;
/*    */ import downfall.rooms.HeartShopRoom;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class ShopRoomReplacePatch
/*    */ {
/*    */   @SpirePatch(clz = AbstractDungeon.class, method = "generateRoomTypes", paramtypez = {ArrayList.class, int.class})
/*    */   public static class replaceShopRoomType
/*    */   {
/*    */     @SpirePostfixPatch
/*    */     public static void Postfix(ArrayList<AbstractRoom> roomList, int availableRoomCount) {
/* 26 */       if (EvilModeCharacterSelect.evilMode) {
/* 27 */         ArrayList<AbstractRoom> shopRooms = new ArrayList<>();
/* 28 */         for (AbstractRoom r : roomList) {
/* 29 */           if (r instanceof com.megacrit.cardcrawl.rooms.ShopRoom) {
/* 30 */             shopRooms.add(r);
/*    */           }
/*    */         } 
/* 33 */         for (AbstractRoom r : shopRooms) {
/* 34 */           roomList.remove(r);
/* 35 */           roomList.add(new HeartShopRoom());
/*    */         } 
/* 37 */         roomList.removeAll(shopRooms);
/* 38 */         System.out.println("Shop rooms replaced with HeartShopRoom.");
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = AbstractDungeon.class, method = "generateRoom", paramtypez = {EventHelper.RoomResult.class})
/*    */   public static class replaceRoomGeneration
/*    */   {
/*    */     @SpirePrefixPatch
/*    */     public static SpireReturn<AbstractRoom> Prefix(AbstractDungeon __instance, EventHelper.RoomResult roomType) {
/* 52 */       if (EvilModeCharacterSelect.evilMode && 
/* 53 */         roomType == EventHelper.RoomResult.SHOP) {
/* 54 */         System.out.println("Replacing SHOP room with HeartShopRoom.");
/* 55 */         return SpireReturn.Return(new HeartShopRoom());
/*    */       } 
/*    */ 
/*    */       
/* 59 */       return SpireReturn.Continue();
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = RoomTypeAssigner.class, method = "ruleParentMatches", paramtypez = {ArrayList.class, AbstractRoom.class})
/*    */   public static class replaceRuleParentMatches
/*    */   {
/*    */     @SpireInsertPatch(rloc = 5, localvars = {"applicableRooms"})
/*    */     public static void patch(ArrayList<MapRoomNode> parents, AbstractRoom roomToBeSet, @ByRef List<Class<? extends AbstractRoom>>[] applicableRooms) {
/* 71 */       if (EvilModeCharacterSelect.evilMode) {
/* 72 */         applicableRooms[0] = new ArrayList<>(applicableRooms[0]);
/* 73 */         applicableRooms[0].add(HeartShopRoom.class);
/* 74 */         System.out.println("Added HeartShopRoom to applicableRooms.");
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = RoomTypeAssigner.class, method = "ruleSiblingMatches", paramtypez = {ArrayList.class, AbstractRoom.class})
/*    */   public static class replaceRuleSiblingMatches
/*    */   {
/*    */     @SpireInsertPatch(rloc = 6, localvars = {"applicableRooms"})
/*    */     public static void patch(ArrayList<MapRoomNode> parents, AbstractRoom roomToBeSet, @ByRef List<Class<? extends AbstractRoom>>[] applicableRooms) {
/* 87 */       if (EvilModeCharacterSelect.evilMode) {
/* 88 */         applicableRooms[0] = new ArrayList<>(applicableRooms[0]);
/* 89 */         applicableRooms[0].add(HeartShopRoom.class);
/* 90 */         System.out.println("Added HeartShopRoom to sibling applicableRooms.");
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\rooms\ShopRoomReplacePatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */