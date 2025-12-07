/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class GremlinWheel extends CustomRelic {
/*  9 */   public static final String ID = downfallMod.makeID("GremlinWheel");
/* 10 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/GremlinWheel.png"));
/* 11 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/GremlinWheel.png"));
/*    */   
/*    */   public boolean justFailed;
/*    */   
/*    */   public GremlinWheel() {
/* 16 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/* 17 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 26 */     this.counter = setCounter;
/* 27 */     if (setCounter <= 0)
/* 28 */       usedUp(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\GremlinWheel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */