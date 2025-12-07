/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.vfx.combat.HealEffect;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_LizardTail
/*    */   extends AbstractCharbossRelic
/*    */ {
/* 17 */   public static String ID = downfallMod.makeID("LizardTail");
/* 18 */   private static AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.BOSS;
/* 19 */   private static AbstractRelic.LandingSound sound = AbstractRelic.LandingSound.MAGICAL;
/*    */ 
/*    */   
/*    */   public CBR_LizardTail() {
/* 23 */     super(ID, "lizardTail.png", tier, sound);
/* 24 */     this.largeImg = null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 30 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCounter(int setCounter) {
/* 35 */     if (setCounter == -2) {
/* 36 */       usedUp();
/* 37 */       this.counter = -2;
/* 38 */       this.grayscale = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTrigger() {
/* 44 */     flash();
/* 45 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/* 46 */     int healAmt = AbstractCharBoss.boss.maxHealth;
/* 47 */     if (healAmt < 1) {
/* 48 */       healAmt = 1;
/*    */     }
/* 50 */     usedUp();
/* 51 */     AbstractCharBoss.boss.heal(healAmt, true);
/* 52 */     AbstractDungeon.effectsQueue.add(new HealEffect(this.hb.cX, this.hb.cY, healAmt));
/*    */     
/* 54 */     setCounter(-2);
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 59 */     return new CBR_LizardTail();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_LizardTail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */