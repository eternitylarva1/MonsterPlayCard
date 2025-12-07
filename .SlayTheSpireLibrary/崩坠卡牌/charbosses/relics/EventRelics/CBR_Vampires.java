/*    */ package charbosses.relics.EventRelics;
/*    */ 
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_Vampires
/*    */   extends AbstractCharbossRelic {
/* 12 */   public static String ID = downfallMod.makeID("Vampires");
/* 13 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.SPECIAL;
/* 14 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/*    */   
/*    */   public int hpLoss;
/*    */   
/*    */   public CBR_Vampires() {
/* 19 */     super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/vampires.png")));
/* 20 */     this.largeImg = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 27 */     this.description = getUpdatedDescription();
/* 28 */     this.tips.clear();
/* 29 */     this.tips.add(new PowerTip(this.name, this.description));
/* 30 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 35 */     return this.DESCRIPTIONS[0] + this.hpLoss + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 40 */     return (AbstractRelic)new CBR_Vampires();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\EventRelics\CBR_Vampires.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */