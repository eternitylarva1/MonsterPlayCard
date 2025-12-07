/*    */ package charbosses.relics.EventRelics;
/*    */ 
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_Nest
/*    */   extends AbstractCharbossRelic {
/* 10 */   public static String ID = downfallMod.makeID("Nest");
/* 11 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.SPECIAL;
/* 12 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/*    */   
/*    */   public CBR_Nest() {
/* 15 */     super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/nest.png")));
/* 16 */     this.largeImg = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return (AbstractRelic)new CBR_Nest();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\EventRelics\CBR_Nest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */