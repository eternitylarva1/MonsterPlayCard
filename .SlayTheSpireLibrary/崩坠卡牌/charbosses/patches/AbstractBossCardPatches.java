/*     */ package charbosses.patches;
/*     */ 
/*     */ import basemod.BaseMod;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.Texture;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*     */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*     */ import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
/*     */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import hermit.util.TextureLoader;
/*     */ import java.lang.reflect.Method;
/*     */ import javassist.CannotCompileException;
/*     */ import javassist.expr.ExprEditor;
/*     */ import javassist.expr.MethodCall;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractBossCardPatches
/*     */ {
/*     */   @SpirePatch(clz = AbstractCard.class, method = "renderCardBg")
/*     */   public static class RenderCardBgPatch
/*     */   {
/*     */     public static SpireReturn<?> Prefix(AbstractCard __instance, SpriteBatch sb, float xPos, float yPos, Color ___renderColor) {
/*  28 */       if (!(__instance instanceof charbosses.cards.AbstractBossCard) || BaseMod.isBaseGameCardColor(__instance.color)) {
/*  29 */         return SpireReturn.Continue();
/*     */       }
/*     */       
/*  32 */       AbstractCard.CardColor color = __instance.color;
/*  33 */       Texture texture = null;
/*  34 */       TextureAtlas.AtlasRegion region = null;
/*     */       
/*  36 */       if (texture == null && region == null) {
/*  37 */         switch (__instance.type) {
/*     */           case BLUE:
/*  39 */             if (BaseMod.getPowerBgTexture(color) == null) {
/*  40 */               BaseMod.savePowerBgTexture(color, TextureLoader.getTexture(BaseMod.getPowerBg(color)));
/*     */             }
/*  42 */             texture = BaseMod.getPowerBgTexture(color);
/*     */             break;
/*     */           case GREEN:
/*  45 */             if (BaseMod.getAttackBgTexture(color) == null) {
/*  46 */               BaseMod.saveAttackBgTexture(color, TextureLoader.getTexture(BaseMod.getAttackBg(color)));
/*     */             }
/*  48 */             texture = BaseMod.getAttackBgTexture(color);
/*     */             break;
/*     */           case RED:
/*  51 */             if (BaseMod.getSkillBgTexture(color) == null) {
/*  52 */               BaseMod.saveSkillBgTexture(color, TextureLoader.getTexture(BaseMod.getSkillBg(color)));
/*     */             }
/*  54 */             texture = BaseMod.getSkillBgTexture(color);
/*     */             break;
/*     */           default:
/*  57 */             region = ImageMaster.CARD_SKILL_BG_BLACK;
/*     */             break;
/*     */         } 
/*     */       
/*     */       }
/*  62 */       if (texture != null) {
/*  63 */         region = new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
/*     */       }
/*     */       
/*  66 */       if (region == null) {
/*  67 */         return SpireReturn.Continue();
/*     */       }
/*     */       
/*     */       try {
/*  71 */         Method renderHelper = AbstractCard.class.getDeclaredMethod("renderHelper", new Class[] { SpriteBatch.class, Color.class, TextureAtlas.AtlasRegion.class, float.class, float.class });
/*  72 */         boolean oldAccessible = renderHelper.isAccessible();
/*  73 */         renderHelper.setAccessible(true);
/*  74 */         renderHelper.invoke(__instance, new Object[] { sb, ___renderColor, region, Float.valueOf(xPos), Float.valueOf(yPos) });
/*  75 */         renderHelper.setAccessible(oldAccessible);
/*  76 */         return SpireReturn.Return(null);
/*  77 */       } catch (NoSuchMethodException|IllegalAccessException|java.lang.reflect.InvocationTargetException e) {
/*  78 */         return SpireReturn.Continue();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @SpirePatch(clz = AbstractCard.class, method = "renderEnergy")
/*     */   public static class RenderEnergyPatch {
/*     */     public static ExprEditor Instrument() {
/*  86 */       return new ExprEditor()
/*     */         {
/*     */           public void edit(MethodCall m) throws CannotCompileException {
/*  89 */             if (m.getClassName().equals(AbstractCard.class.getName()) && m.getMethodName().equals("renderHelper")) {
/*  90 */               m.replace("{$3 = " + AbstractBossCardPatches.RenderEnergyPatch.class
/*  91 */                   .getName() + ".getEnergyOrb(this, $3);$_ = $proceed($$);}");
/*     */             }
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static TextureAtlas.AtlasRegion getEnergyOrb(AbstractCard card, TextureAtlas.AtlasRegion orb) {
/* 100 */       if (!(card instanceof charbosses.cards.AbstractBossCard)) {
/* 101 */         return orb;
/*     */       }
/*     */       
/* 104 */       Texture baseModTexture = BaseMod.getEnergyOrbTexture(card.color);
/* 105 */       if (baseModTexture != null) {
/* 106 */         return new TextureAtlas.AtlasRegion(baseModTexture, 0, 0, baseModTexture.getWidth(), baseModTexture.getHeight());
/*     */       }
/*     */       
/* 109 */       switch (card.color) {
/*     */         case BLUE:
/* 111 */           return ImageMaster.CARD_BLUE_ORB;
/*     */         case GREEN:
/* 113 */           return ImageMaster.CARD_GREEN_ORB;
/*     */         case RED:
/* 115 */           return ImageMaster.CARD_RED_ORB;
/*     */         case PURPLE:
/* 117 */           return ImageMaster.CARD_PURPLE_ORB;
/*     */         case COLORLESS:
/*     */         case CURSE:
/* 120 */           return ImageMaster.CARD_COLORLESS_ORB;
/*     */       } 
/*     */       
/* 123 */       Texture texture = TextureLoader.getTexture(BaseMod.getEnergyOrb(card.color));
/* 124 */       BaseMod.saveEnergyOrbTexture(card.color, texture);
/* 125 */       return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\patches\AbstractBossCardPatches.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */