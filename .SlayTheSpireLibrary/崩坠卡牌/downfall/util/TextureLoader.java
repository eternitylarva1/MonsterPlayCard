/*    */ package downfall.util;
/*    */ 
/*    */ import com.badlogic.gdx.Gdx;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*    */ import com.badlogic.gdx.utils.GdxRuntimeException;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
/*    */ import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class TextureLoader {
/* 13 */   private static HashMap<String, Texture> textures = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Texture getTexture(String textureString) {
/* 21 */     if (textures.get(textureString) == null) {
/*    */       try {
/* 23 */         loadTexture(textureString, true);
/* 24 */       } catch (GdxRuntimeException e) {
/* 25 */         return getTexture("downfallResources/images/ui/missing_texture.png");
/*    */       } 
/*    */     }
/* 28 */     return textures.get(textureString);
/*    */   }
/*    */   
/*    */   private static void loadTexture(String textureString) throws GdxRuntimeException {
/* 32 */     loadTexture(textureString, false);
/*    */   }
/*    */   
/*    */   private static void loadTexture(String textureString, boolean linearFilter) throws GdxRuntimeException {
/* 36 */     Texture texture = new Texture(textureString);
/* 37 */     if (linearFilter) {
/* 38 */       texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
/*    */     } else {
/* 40 */       texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
/*    */     } 
/* 42 */     textures.put(textureString, texture);
/*    */   }
/*    */   
/*    */   public static boolean testTexture(String filePath) {
/* 46 */     return Gdx.files.internal(filePath).exists();
/*    */   }
/*    */   
/*    */   public static TextureAtlas.AtlasRegion getTextureAsAtlasRegion(String textureString) {
/* 50 */     Texture texture = getTexture(textureString);
/* 51 */     return new TextureAtlas.AtlasRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
/*    */   }
/*    */   
/*    */   @SpirePatch(clz = Texture.class, method = "dispose")
/*    */   public static class DisposeListener {
/*    */     @SpirePrefixPatch
/*    */     public static void DisposeListenerPatch(Texture __instance) {
/* 58 */       TextureLoader.textures.entrySet().removeIf(entry -> {
/*    */             if (((Texture)entry.getValue()).equals(__instance))
/*    */               System.out.println("TextureLoader | Removing Texture: " + (String)entry.getKey()); 
/*    */             return ((Texture)entry.getValue()).equals(__instance);
/*    */           });
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfal\\util\TextureLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */