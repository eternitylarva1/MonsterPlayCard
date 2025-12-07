/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ public class CBR_NeowsBlessing
/*    */   extends AbstractCharbossRelic
/*    */ {
/* 13 */   public static String ID = downfallMod.makeID("NeowBlessing");
/* 14 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.SPECIAL;
/* 15 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/* 16 */   public String relicName = "";
/* 17 */   private int HP = 0;
/*    */   
/*    */   public CBR_NeowsBlessing() {
/* 20 */     super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/blessing.png")));
/* 21 */     this.largeImg = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 26 */     this.description = getUpdatedDescription();
/* 27 */     this.tips.clear();
/* 28 */     this.tips.add(new PowerTip(this.name, this.description));
/* 29 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 34 */     if (this.owner != null) {
/* 35 */       return this.DESCRIPTIONS[0] + this.owner.energyString + this.DESCRIPTIONS[1];
/*    */     }
/* 37 */     return this.DESCRIPTIONS[0] + "[E]" + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 43 */     return new CBR_NeowsBlessing();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_NeowsBlessing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */