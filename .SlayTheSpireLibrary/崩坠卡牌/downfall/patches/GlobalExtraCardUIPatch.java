/*    */ package downfall.patches;
/*    */ 
/*    */ import basemod.helpers.CardModifierManager;
/*    */ import champ.ChampMod;
/*    */ import collector.cardmods.PyreMod;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.helpers.FontHelper;
/*    */ import sneckomod.SneckoMod;
/*    */ import sneckomod.cards.unknowns.AbstractUnknownCard;
/*    */ import sneckomod.patches.UnknownExtraUiPatch;
/*    */ 
/*    */ public class GlobalExtraCardUIPatch {
/* 20 */   private static TextureAtlas.AtlasRegion healthBlob = ChampMod.UIAtlas.findRegion("heartOrb");
/* 21 */   private static TextureAtlas.AtlasRegion crown = ChampMod.UIAtlas.findRegion("crown");
/* 22 */   private static TextureAtlas.AtlasRegion openerall = ChampMod.UIAtlas.findRegion("openerAll");
/* 23 */   private static TextureAtlas.AtlasRegion finisher = ChampMod.UIAtlas.findRegion("finisher");
/* 24 */   private static TextureAtlas.AtlasRegion openerR = ChampMod.UIAtlas.findRegion("openerR");
/* 25 */   private static TextureAtlas.AtlasRegion openerB = ChampMod.UIAtlas.findRegion("openerB");
/* 26 */   private static TextureAtlas.AtlasRegion openerY = ChampMod.UIAtlas.findRegion("openerY");
/* 27 */   private static TextureAtlas.AtlasRegion openerYR = ChampMod.UIAtlas.findRegion("openerYR");
/* 28 */   private static TextureAtlas.AtlasRegion openerYB = ChampMod.UIAtlas.findRegion("openerYB");
/* 29 */   private static TextureAtlas.AtlasRegion openerRB = ChampMod.UIAtlas.findRegion("openerRB");
/* 30 */   private static TextureAtlas.AtlasRegion pyreIcon = ChampMod.UIAtlas.findRegion("pyreIcon");
/*    */   
/*    */   @SpirePatch(clz = AbstractCard.class, method = "renderEnergy")
/*    */   public static class SecondEnergyRenderPatch {
/*    */     @SpirePrefixPatch
/*    */     public static void patch(AbstractCard __instance, SpriteBatch sb) {
/* 36 */       if (UnknownExtraUiPatch.parentCard.get(__instance) != null) {
/* 37 */         renderHelper(sb, SneckoMod.overBannerAll, __instance.current_x, __instance.current_y, __instance);
/* 38 */         renderHelper(sb, ((AbstractUnknownCard)UnknownExtraUiPatch.parentCard.get(__instance)).getOverBannerTex(), __instance.current_x, __instance.current_y, __instance);
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 70 */       if (__instance instanceof champ.cards.AbstractChampCard) {
/* 71 */         FontHelper.cardEnergyFont_L.getData().setScale(__instance.drawScale);
/* 72 */         renderHelper(sb, GlobalExtraCardUIPatch.crown, __instance.current_x, __instance.current_y, __instance);
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 84 */       if (CardModifierManager.hasModifier(__instance, PyreMod.ID)) {
/* 85 */         FontHelper.cardEnergyFont_L.getData().setScale(__instance.drawScale);
/* 86 */         renderHelper(sb, GlobalExtraCardUIPatch.pyreIcon, __instance.current_x, __instance.current_y, __instance);
/*    */       } 
/*    */     }
/*    */     
/*    */     private static void renderHelper(SpriteBatch sb, TextureAtlas.AtlasRegion img, float drawX, float drawY, AbstractCard C) {
/* 91 */       Color color = Color.WHITE.cpy();
/* 92 */       color.a = C.transparency;
/* 93 */       sb.setColor(color);
/* 94 */       if (img == null) {
/* 95 */         System.out.println("HELP!");
/* 96 */         img = ChampMod.UIAtlas.findRegion("crown");
/*    */       } 
/* 98 */       sb.draw((TextureRegion)img, drawX + img.offsetX - img.originalWidth / 2.0F, drawY + img.offsetY - img.originalHeight / 2.0F, img.originalWidth / 2.0F - img.offsetX, img.originalHeight / 2.0F - img.offsetY, img.packedWidth, img.packedHeight, C.drawScale * Settings.scale, C.drawScale * Settings.scale, C.angle);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\patches\GlobalExtraCardUIPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */