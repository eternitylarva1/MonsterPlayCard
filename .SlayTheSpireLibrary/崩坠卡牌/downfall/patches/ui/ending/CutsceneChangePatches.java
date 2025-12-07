/*    */ package downfall.patches.ui.ending;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.cutscenes.Cutscene;
/*    */ import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
/*    */ import downfall.downfallMod;
/*    */ import downfall.patches.EvilModeCharacterSelect;
/*    */ import downfall.util.TextureLoader;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import theHexaghost.TheHexaghost;
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpirePatch(clz = Cutscene.class, method = "<ctor>")
/*    */ public class CutsceneChangePatches
/*    */ {
/*    */   @SpirePostfixPatch
/*    */   public static void patch(Cutscene __instance, AbstractPlayer.PlayerClass chosenClass) {
/* 24 */     if (EvilModeCharacterSelect.evilMode) {
/*    */       
/* 26 */       Texture customBg = TextureLoader.getTexture("images/scenes/redBg.jpg");
/* 27 */       if (customBg != null) {
/*    */         try {
/* 29 */           Field f = Cutscene.class.getDeclaredField("bgImg");
/* 30 */           f.setAccessible(true);
/*    */           
/* 32 */           Texture oldBg = (Texture)f.get(__instance);
/* 33 */           oldBg.dispose();
/* 34 */           f.set(__instance, customBg);
/* 35 */         } catch (IllegalAccessException|NoSuchFieldException e) {
/* 36 */           e.printStackTrace();
/*    */         } 
/*    */       }
/*    */       
/* 40 */       List<CutscenePanel> customPanels = new ArrayList<>();
/* 41 */       if (chosenClass == TheHexaghost.Enums.THE_SPIRIT) {
/* 42 */         customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/hexaending1.jpg"), "VO_NEOW_1A"));
/* 43 */         customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/hexaending2.jpg")));
/* 44 */         customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/hexaending3.jpg")));
/*    */       } else {
/* 46 */         customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/ending1.png"), "VO_NEOW_1A"));
/* 47 */         customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/ending2.png")));
/* 48 */         customPanels.add(new CutscenePanel(downfallMod.assetPath("images/scenes/ending3.png")));
/*    */       } 
/* 50 */       if (customPanels != null)
/*    */         try {
/* 52 */           Field f = Cutscene.class.getDeclaredField("panels");
/* 53 */           f.setAccessible(true);
/*    */           
/* 55 */           ArrayList<CutscenePanel> panels = (ArrayList<CutscenePanel>)f.get(__instance);
/* 56 */           for (CutscenePanel panel : panels) {
/* 57 */             panel.dispose();
/*    */           }
/* 59 */           panels.clear();
/* 60 */           panels.addAll(customPanels);
/* 61 */         } catch (IllegalAccessException|NoSuchFieldException e) {
/* 62 */           e.printStackTrace();
/*    */         }  
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patche\\ui\ending\CutsceneChangePatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */