/*    */ package downfall.patches.ui.campfire;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.AbstractRoom;
/*    */ import com.megacrit.cardcrawl.rooms.CampfireUI;
/*    */ import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = CampfireUI.class, method = "update")
/*    */ public class CampfireSoftlockPrevention
/*    */ {
/*    */   @SpirePostfixPatch
/*    */   public static void Postfix(CampfireUI __instance) {
/* 23 */     if (!__instance.somethingSelected) {
/* 24 */       boolean softlocked = true;
/* 25 */       ArrayList<AbstractCampfireOption> rrButtons = (ArrayList<AbstractCampfireOption>)ReflectionHacks.getPrivate(__instance, CampfireUI.class, "buttons");
/* 26 */       for (AbstractCampfireOption b : rrButtons) {
/* 27 */         if (b.usable) {
/* 28 */           softlocked = false;
/*    */         }
/*    */       } 
/* 31 */       if (softlocked) {
/* 32 */         __instance.somethingSelected = true;
/* 33 */         __instance.touchOption = null;
/* 34 */         __instance.confirmButton.show();
/* 35 */         __instance.confirmButton.isDisabled = false;
/*    */         
/* 37 */         AbstractDungeon.overlayMenu.proceedButton.show();
/* 38 */         (AbstractDungeon.getCurrRoom()).phase = AbstractRoom.RoomPhase.COMPLETE;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patche\\ui\campfire\CampfireSoftlockPrevention.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */