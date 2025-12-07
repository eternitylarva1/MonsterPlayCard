/*    */ package charbosses.relics.EventRelics;
/*    */ 
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_Augmenter
/*    */   extends AbstractCharbossRelic {
/* 10 */   public static String ID = downfallMod.makeID("Augmenter");
/* 11 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.SPECIAL;
/* 12 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/*    */   private String addedName;
/*    */   private String removedName;
/*    */   private String addedName2;
/* 16 */   private int descInt = 0;
/*    */   private String removedName2;
/*    */   
/*    */   public CBR_Augmenter(int choiceIndex) {
/* 20 */     super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/augmenter.png")));
/* 21 */     this.descInt = choiceIndex;
/* 22 */     this.largeImg = null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 28 */     return this.DESCRIPTIONS[this.descInt];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return (AbstractRelic)new CBR_Augmenter(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\EventRelics\CBR_Augmenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */