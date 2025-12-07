/*    */ package downfall.patches.ui.campfire;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.Matcher;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireField;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.rooms.CampfireUI;
/*    */ import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
/*    */ import com.megacrit.cardcrawl.ui.panels.TopPanel;
/*    */ import downfall.downfallMod;
/*    */ import downfall.ui.campfire.BustKeyOption;
/*    */ import downfall.util.TextureLoader;
/*    */ import java.util.ArrayList;
/*    */ import javassist.CannotCompileException;
/*    */ import javassist.CtBehavior;
/*    */ import javassist.expr.ExprEditor;
/*    */ import javassist.expr.MethodCall;
/*    */ 
/*    */ public class AddBustKeyButtonPatches {
/*    */   public static Texture brokenKeyCheck(Texture tex) {
/* 26 */     if (tex.equals(ImageMaster.RUBY_KEY) && ((Boolean)KeyFields.bustedRuby.get(AbstractDungeon.player)).booleanValue()) {
/* 27 */       return TextureLoader.getTexture(downfallMod.assetPath("images/ui/key_red.png"));
/*    */     }
/* 29 */     if (tex.equals(ImageMaster.EMERALD_KEY) && ((Boolean)KeyFields.bustedEmerald.get(AbstractDungeon.player)).booleanValue()) {
/* 30 */       return TextureLoader.getTexture(downfallMod.assetPath("images/ui/key_green.png"));
/*    */     }
/* 32 */     if (tex.equals(ImageMaster.SAPPHIRE_KEY) && ((Boolean)KeyFields.bustedSapphire.get(AbstractDungeon.player)).booleanValue()) {
/* 33 */       return TextureLoader.getTexture(downfallMod.assetPath("images/ui/key_blue.png"));
/*    */     }
/*    */     
/* 36 */     return tex;
/*    */   }
/*    */   
/*    */   @SpirePatch(clz = AbstractPlayer.class, method = "<class>")
/*    */   public static class KeyFields {
/* 41 */     public static SpireField<Boolean> bustedRuby = new SpireField(() -> Boolean.valueOf(false));
/* 42 */     public static SpireField<Boolean> bustedEmerald = new SpireField(() -> Boolean.valueOf(false));
/* 43 */     public static SpireField<Boolean> bustedSapphire = new SpireField(() -> Boolean.valueOf(false));
/*    */   }
/*    */   
/*    */   @SpirePatch(clz = CampfireUI.class, method = "initializeButtons")
/*    */   public static class AddKeys {
/*    */     @SpireInsertPatch(locator = Locator.class)
/*    */     public static void patch(CampfireUI __instance, ArrayList<AbstractCampfireOption> ___buttons) {
/* 50 */       if (EvilModeCharacterSelect.evilMode) {
/* 51 */         if (Settings.hasRubyKey && !((Boolean)AddBustKeyButtonPatches.KeyFields.bustedRuby.get(AbstractDungeon.player)).booleanValue()) {
/* 52 */           ___buttons.add(new BustKeyOption(BustKeyOption.Keys.RUBY));
/*    */         }
/* 54 */         if (Settings.hasEmeraldKey && !((Boolean)AddBustKeyButtonPatches.KeyFields.bustedEmerald.get(AbstractDungeon.player)).booleanValue()) {
/* 55 */           ___buttons.add(new BustKeyOption(BustKeyOption.Keys.EMERALD));
/*    */         }
/* 57 */         if (Settings.hasSapphireKey && !((Boolean)AddBustKeyButtonPatches.KeyFields.bustedSapphire.get(AbstractDungeon.player)).booleanValue())
/* 58 */           ___buttons.add(new BustKeyOption(BustKeyOption.Keys.SAPPHIRE)); 
/*    */       } 
/*    */     }
/*    */     
/*    */     public static class Locator
/*    */       extends SpireInsertLocator
/*    */     {
/*    */       public int[] Locate(CtBehavior ctBehavior) throws Exception {
/* 66 */         Matcher.FieldAccessMatcher fieldAccessMatcher = new Matcher.FieldAccessMatcher(AbstractPlayer.class, "relics");
/* 67 */         return LineFinder.findInOrder(ctBehavior, (Matcher)fieldAccessMatcher);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   @SpirePatch(clz = TopPanel.class, method = "renderName")
/*    */   public static class BrokenKeyRender {
/*    */     public static ExprEditor Instrument() {
/* 75 */       return new ExprEditor()
/*    */         {
/*    */           public void edit(MethodCall m) throws CannotCompileException {
/* 78 */             if (m.getClassName().equals(SpriteBatch.class.getName()) && m.getMethodName().equals("draw"))
/* 79 */               m.replace("{$proceed(" + AddBustKeyButtonPatches.class
/* 80 */                   .getName() + ".brokenKeyCheck($1), $2, $3, $4, $5, $6, $7, $8, $9, $10, $11, $12, $13, $14, $15, $16);}"); 
/*    */           }
/*    */         };
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patche\\ui\campfire\AddBustKeyButtonPatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */