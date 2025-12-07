/*    */ package downfall.patches.ui.campfire;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.rooms.CampfireUI;
/*    */ import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
/*    */ import downfall.relics.GremlinWheel;
/*    */ import downfall.ui.campfire.WheelSpinButton;
/*    */ import java.util.ArrayList;
/*    */ import javassist.CtBehavior;
/*    */ 
/*    */ public class AddWheelSpinButtonPatch {
/*    */   @SpirePatch(clz = CampfireUI.class, method = "initializeButtons")
/*    */   public static class AddKeys {
/*    */     @SpireInsertPatch(locator = AddWheelSpinButtonPatch.Locator.class)
/*    */     public static void patch(CampfireUI __instance, ArrayList<AbstractCampfireOption> ___buttons) {
/* 19 */       if (AbstractDungeon.player.hasRelic(GremlinWheel.ID)) {
/*    */         
/* 21 */         boolean relicActive = ((AbstractDungeon.player.getRelic(GremlinWheel.ID)).counter != 0);
/*    */         
/* 23 */         GremlinWheel gw = (GremlinWheel)AbstractDungeon.player.getRelic(GremlinWheel.ID);
/* 24 */         boolean justUsed = gw.justFailed;
/*    */         
/* 26 */         if (relicActive) ___buttons.add(new WheelSpinButton(!justUsed)); 
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public static class Locator
/*    */     extends SpireInsertLocator {
/*    */     public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 34 */       Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
/* 35 */       return LineFinder.findInOrder(ctBehavior, (Matcher)fieldAccessMatcher);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patche\\ui\campfire\AddWheelSpinButtonPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */