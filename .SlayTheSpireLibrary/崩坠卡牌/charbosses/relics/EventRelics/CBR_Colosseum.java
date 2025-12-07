/*    */ package charbosses.relics.EventRelics;
/*    */ 
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_Colosseum
/*    */   extends AbstractCharbossRelic {
/* 10 */   public static String ID = downfallMod.makeID("Colosseum");
/* 11 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.SPECIAL;
/* 12 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/*    */   private String addedName;
/*    */   private String addedName2;
/*    */   private String addedName3;
/*    */   
/*    */   public CBR_Colosseum() {
/* 18 */     super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/colosseum.png")));
/* 19 */     this.largeImg = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return (AbstractRelic)new CBR_Colosseum();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\EventRelics\CBR_Colosseum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */