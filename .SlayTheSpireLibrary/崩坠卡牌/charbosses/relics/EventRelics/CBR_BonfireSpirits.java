/*    */ package charbosses.relics.EventRelics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ public class CBR_BonfireSpirits
/*    */   extends AbstractCharbossRelic {
/* 13 */   public static String ID = downfallMod.makeID("BonfireSpirits");
/* 14 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.SPECIAL;
/* 15 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/* 16 */   public String cardName = "";
/* 17 */   private int descInt = 0;
/*    */   
/*    */   public CBR_BonfireSpirits() {
/* 20 */     super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/bonfirespirits.png")));
/* 21 */     this.largeImg = null;
/*    */   }
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
/*    */   public void onEquip() {
/* 35 */     AbstractCharBoss.boss.increaseMaxHp(10, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 40 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 45 */     return (AbstractRelic)new CBR_BonfireSpirits();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\EventRelics\CBR_BonfireSpirits.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */