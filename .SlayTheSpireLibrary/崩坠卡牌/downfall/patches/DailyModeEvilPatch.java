/*    */ package downfall.patches;
/*    */ 
/*    */ import basemod.ReflectionHacks;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.daily.DailyScreen;
/*    */ import com.megacrit.cardcrawl.daily.TimeHelper;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import com.megacrit.cardcrawl.integrations.DistributorFactory;
/*    */ import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
/*    */ import downfall.downfallMod;
/*    */ import downfall.util.TextureLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DailyModeEvilPatch
/*    */ {
/*    */   public static boolean todaysRunIsEvil;
/* 25 */   public static String campaignText = (CardCrawlGame.languagePack.getUIString(downfallMod.makeID("EvilMenuPanel"))).TEXT[3];
/* 26 */   public static String downfallText = (CardCrawlGame.languagePack.getUIString(downfallMod.makeID("EvilMenuPanel"))).TEXT[0];
/* 27 */   public static String standardText = (CardCrawlGame.languagePack.getUIString("MenuPanels")).TEXT[0];
/*    */   
/* 29 */   private static float header_x = 186.0F * Settings.scale;
/* 30 */   private static float char_x = 304.0F * Settings.scale;
/*    */   
/* 32 */   private static float center_mod_offset_x = 500.0F * Settings.scale;
/*    */   
/* 34 */   private static float mode_offset_x = 400.0F * Settings.scale;
/*    */   
/*    */   @SpirePatch(clz = DailyScreen.class, method = "determineLoadout")
/*    */   public static class Init {
/*    */     @SpirePostfixPatch
/*    */     public static void Postfix(DailyScreen __result) {
/* 40 */       if (Settings.specialSeed.longValue() % 2L != 0L) {
/* 41 */         DailyModeEvilPatch.todaysRunIsEvil = true;
/*    */       }
/*    */       
/* 44 */       if (!DistributorFactory.isLeaderboardEnabled()) {
/* 45 */         DailyModeEvilPatch.header_x += DailyModeEvilPatch.center_mod_offset_x;
/* 46 */         DailyModeEvilPatch.char_x += DailyModeEvilPatch.center_mod_offset_x;
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = DailyScreen.class, method = "update")
/*    */   public static class Proceed
/*    */   {
/*    */     @SpirePostfixPatch
/*    */     public static void Prefix(DailyScreen __result) {
/* 58 */       MenuCancelButton cancelButton = (MenuCancelButton)ReflectionHacks.getPrivate(__result, DailyScreen.class, "cancelButton");
/* 59 */       if (cancelButton.hb.clicked) {
/* 60 */         EvilModeCharacterSelect.evilMode = DailyModeEvilPatch.todaysRunIsEvil;
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @SpirePatch(clz = DailyScreen.class, method = "render", paramtypez = {SpriteBatch.class})
/*    */   public static class renderPatch
/*    */   {
/*    */     @SpirePostfixPatch
/*    */     public static void Postfix(DailyScreen __result, SpriteBatch sb) {
/* 71 */       if (TimeHelper.isTimeSet) {
/* 72 */         Texture img = null;
/* 73 */         StringBuilder builder = new StringBuilder("#y");
/* 74 */         builder.append(DailyModeEvilPatch.campaignText);
/* 75 */         builder.append(" NL ");
/* 76 */         if (DailyModeEvilPatch.todaysRunIsEvil) {
/* 77 */           builder.append(DailyModeEvilPatch.downfallText);
/* 78 */           img = TextureLoader.getTexture(downfallMod.assetPath("/images/ui/dailyEvil.png"));
/*    */         } else {
/* 80 */           builder.append(DailyModeEvilPatch.standardText);
/* 81 */           img = TextureLoader.getTexture(downfallMod.assetPath("/images/ui/dailyStandard.png"));
/*    */         } 
/*    */ 
/*    */         
/* 85 */         sb.setColor(Color.WHITE);
/* 86 */         if (img != null) {
/* 87 */           sb.draw(img, DailyModeEvilPatch.header_x + DailyModeEvilPatch.mode_offset_x, Settings.HEIGHT / 2.0F + 160.0F * Settings.scale, 128.0F * Settings.scale, 128.0F * Settings.scale);
/*    */         }
/*    */         
/* 90 */         FontHelper.renderSmartText(sb, FontHelper.charDescFont, builder
/*    */             
/* 92 */             .toString(), DailyModeEvilPatch.char_x + DailyModeEvilPatch.mode_offset_x, Settings.HEIGHT / 2.0F + 250.0F * Settings.scale, 9999.0F, 30.0F * Settings.scale, Settings.CREAM_COLOR);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\DailyModeEvilPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */