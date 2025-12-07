/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.HornCleat;
/*    */ 
/*    */ public class CBR_HornCleat
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "HornCleat";
/*    */   
/*    */   public CBR_HornCleat() {
/* 15 */     super((AbstractRelic)new HornCleat());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 20 */     return this.DESCRIPTIONS[0] + '\016' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 25 */     if (!this.grayscale) {
/* 26 */       this.counter++;
/*    */     }
/*    */     
/* 29 */     if (this.counter == 2) {
/* 30 */       flash();
/* 31 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 32 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, 14));
/* 33 */       this.counter = -1;
/* 34 */       this.grayscale = true;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 39 */     this.counter = 0;
/*    */   }
/*    */   
/*    */   public void onVictory() {
/* 43 */     this.counter = -1;
/* 44 */     this.grayscale = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 49 */     return new CBR_HornCleat();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_HornCleat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */