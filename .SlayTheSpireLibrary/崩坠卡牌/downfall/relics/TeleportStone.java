/*    */ package downfall.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class TeleportStone extends CustomRelic {
/*  9 */   public static final String ID = downfallMod.makeID("TeleportStone");
/* 10 */   private static final Texture IMG = new Texture(downfallMod.assetPath("images/relics/TeleportStone.png"));
/* 11 */   private static final Texture OUTLINE = new Texture(downfallMod.assetPath("images/relics/Outline/TeleportStone.png"));
/*    */   
/*    */   public TeleportStone() {
/* 14 */     super(ID, IMG, OUTLINE, AbstractRelic.RelicTier.SPECIAL, AbstractRelic.LandingSound.FLAT);
/* 15 */     this.counter = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTrigger() {
/* 25 */     setCounter(0);
/*    */   }
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 29 */     this.counter = setCounter;
/* 30 */     if (setCounter <= 0)
/* 31 */       usedUp(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\relics\TeleportStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */