/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Orichalcum;
/*    */ 
/*    */ public class CBR_Orichalcum extends AbstractCharbossRelic {
/*    */   public static final String ID = "Orichalcum";
/*    */   
/*    */   public CBR_Orichalcum() {
/* 16 */     super((AbstractRelic)new Orichalcum());
/* 17 */     this.trigger = false;
/*    */   }
/*    */   private static final int BLOCK_AMT = 6; public boolean trigger;
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0] + '\006' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEndTurn() {
/* 27 */     if (AbstractCharBoss.boss.currentBlock == 0 || this.trigger) {
/* 28 */       this.trigger = false;
/* 29 */       flash();
/* 30 */       stopPulse();
/* 31 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, 6));
/* 32 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 38 */     this.trigger = false;
/* 39 */     if (AbstractCharBoss.boss.currentBlock == 0) {
/* 40 */       beginLongPulse();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int onPlayerGainedBlock(float blockAmount) {
/* 46 */     if (blockAmount > 0.0F) {
/* 47 */       stopPulse();
/*    */     }
/* 49 */     return MathUtils.floor(blockAmount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 54 */     stopPulse();
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 59 */     return new CBR_Orichalcum();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Orichalcum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */