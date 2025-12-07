/*    */ package charbosses.relics.EventRelics;
/*    */ 
/*    */ import charbosses.bosses.AbstractBossDeckArchetype;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.cards.curses.EnRegret;
/*    */ import charbosses.relics.AbstractCharbossRelic;
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import downfall.downfallMod;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class CBR_BigFish
/*    */   extends AbstractCharbossRelic
/*    */ {
/* 20 */   public static String ID = downfallMod.makeID("BigFish");
/* 21 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.SPECIAL;
/* 22 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/* 23 */   public String relicName = "";
/* 24 */   private int descInt = 0;
/*    */   
/*    */   public CBR_BigFish() {
/* 27 */     super(ID, tier, sound, new Texture(downfallMod.assetPath("images/relics/bigfish.png")));
/* 28 */     this.largeImg = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void box(ArrayList<AbstractBossCard> list) {
/* 33 */     EnRegret enRegret = new EnRegret();
/* 34 */     AbstractBossDeckArchetype.logger.info("Big Fish added 1 " + ((AbstractCard)enRegret).name + ".");
/* 35 */     this.descInt = 1;
/* 36 */     updateDescription(AbstractCharBoss.boss.chosenClass);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription(AbstractPlayer.PlayerClass c) {
/* 41 */     this.description = getUpdatedDescription();
/* 42 */     this.tips.clear();
/* 43 */     this.tips.add(new PowerTip(this.name, this.description));
/* 44 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 49 */     return this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 55 */     return (AbstractRelic)new CBR_BigFish();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\EventRelics\CBR_BigFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */