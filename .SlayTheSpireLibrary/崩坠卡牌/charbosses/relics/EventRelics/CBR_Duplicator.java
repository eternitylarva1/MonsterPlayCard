/*    */ package charbosses.relics.EventRelics;
/*    */ 
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_Duplicator
/*    */   extends AbstractCharbossRelic {
/* 10 */   public static String ID = downfallMod.makeID("DuplicatorEvil");
/* 11 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.SPECIAL;
/* 12 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/*    */   
/* 14 */   public String cardName = "";
/*    */   
/*    */   public CBR_Duplicator() {
/* 17 */     super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/shrine4.png")));
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
/* 28 */     return (AbstractRelic)new CBR_Duplicator();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\EventRelics\CBR_Duplicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */