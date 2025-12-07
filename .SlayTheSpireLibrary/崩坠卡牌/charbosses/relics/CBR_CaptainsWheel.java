/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CaptainsWheel;
/*    */ 
/*    */ public class CBR_CaptainsWheel extends AbstractCharbossRelic {
/*    */   public CBR_CaptainsWheel() {
/* 13 */     super((AbstractRelic)new CaptainsWheel(), AbstractRelic.RelicTier.COMMON);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0] + '\022' + this.DESCRIPTIONS[1];
/*    */   }
/*    */   public static final String ID = "CaptainsWheel";
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 28 */     if (!this.grayscale) {
/* 29 */       this.counter++;
/*    */     }
/* 31 */     if (this.counter == 3) {
/* 32 */       flash();
/* 33 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/* 34 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, 18));
/* 35 */       this.counter = -1;
/* 36 */       this.grayscale = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 42 */     this.counter = -1;
/* 43 */     this.grayscale = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 48 */     return new CBR_CaptainsWheel();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_CaptainsWheel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */