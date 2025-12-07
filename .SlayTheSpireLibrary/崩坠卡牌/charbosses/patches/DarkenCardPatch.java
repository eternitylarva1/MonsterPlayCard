/*    */ package charbosses.patches;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.badlogic.gdx.graphics.Color;
/*    */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.Settings;
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
/*    */ @SpirePatch(clz = AbstractCard.class, method = "renderTint")
/*    */ public class DarkenCardPatch
/*    */ {
/* 26 */   private static Color lightenColor = new Color(109.65F, 94.35F, 165.75F, 0.0F);
/* 27 */   private static Color darkenColor = new Color(0.0F, 0.0F, 0.0F, 0.75F);
/*    */ 
/*    */   
/*    */   @SpirePrefixPatch
/*    */   public static SpireReturn<Void> Prefix(AbstractCard instance, SpriteBatch sb) {
/* 32 */     if (instance instanceof AbstractBossCard) {
/* 33 */       if (!Settings.hideCards) {
/*    */         Color tintColor;
/* 35 */         if (((AbstractBossCard)instance).bossDarkened && !((AbstractBossCard)instance).hov2) {
/* 36 */           tintColor = darkenColor;
/*    */         } else {
/* 38 */           tintColor = lightenColor;
/*    */         } 
/* 40 */         TextureAtlas.AtlasRegion cardBgImg = instance.getCardBgAtlas();
/* 41 */         AbstractBossCard cB = (AbstractBossCard)instance;
/* 42 */         if (cardBgImg != null) {
/* 43 */           cB.renderHelperB(sb, tintColor, cardBgImg, instance.current_x, instance.current_y);
/*    */         } else {
/* 45 */           cB.renderHelperB(sb, tintColor, instance.getCardBg(), instance.current_x - 256.0F, instance.current_y - 256.0F);
/*    */         } 
/*    */       } 
/* 48 */       return SpireReturn.Return(null);
/*    */     } 
/*    */     
/* 51 */     return SpireReturn.Continue();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\patches\DarkenCardPatch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */