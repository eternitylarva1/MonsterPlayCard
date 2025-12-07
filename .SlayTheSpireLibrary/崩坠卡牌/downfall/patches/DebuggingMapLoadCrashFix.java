/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.neow.NeowRoom;
/*    */ import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
/*    */ import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
/*    */ import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = AbstractDungeon.class, method = "populatePathTaken")
/*    */ public class DebuggingMapLoadCrashFix
/*    */ {
/*    */   @SpirePrefixPatch
/*    */   public static SpireReturn Prefix(AbstractDungeon __instance, SaveFile save) {
/* 20 */     if (save.current_room.equals(MonsterRoomBoss.class.getName()) || save.current_room
/* 21 */       .equals(TreasureRoomBoss.class.getName()) || (save.room_y == 15 && save.room_x == -1))
/*    */     {
/* 23 */       return SpireReturn.Continue();
/*    */     }
/*    */     
/* 26 */     if (save.room_x < 0 || save.room_y < 0) {
/* 27 */       save.current_room = NeowRoom.class.getName();
/*    */     }
/* 29 */     return SpireReturn.Continue();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\DebuggingMapLoadCrashFix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */