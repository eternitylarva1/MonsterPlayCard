/*    */ package downfall.patches;
/*    */ 
/*    */ import com.evacipated.cardcrawl.modthespire.lib.ByRef;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import downfall.relics.Hecktoplasm;
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = AbstractPlayer.class, method = "gainGold")
/*    */ public class HecktoplasmPatch
/*    */ {
/*    */   public static void Prefix(AbstractPlayer __instance, @ByRef int[] amount) {
/* 14 */     if (AbstractDungeon.player.hasRelic(Hecktoplasm.ID)) {
/* 15 */       AbstractDungeon.player.getRelic(Hecktoplasm.ID).flash();
/* 16 */       System.out.println("Removing " + amount[0] + " Souls.");
/* 17 */       amount[0] = amount[0] * 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\HecktoplasmPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */