/*    */ package charbosses.relics.EventRelics;
/*    */ 
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_WeMeetAgain
/*    */   extends AbstractCharbossRelic {
/* 10 */   public static String ID = downfallMod.makeID("WeMeetAgain");
/* 11 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.SPECIAL;
/* 12 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/*    */   private String removedName;
/*    */   private String addedName;
/*    */   
/*    */   public CBR_WeMeetAgain() {
/* 17 */     super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/wemeetagain.png")));
/* 18 */     this.largeImg = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 28 */     return (AbstractRelic)new CBR_WeMeetAgain();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\EventRelics\CBR_WeMeetAgain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */