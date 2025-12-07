/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyIncreaseMaxOrbAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Inserter;
/*    */ 
/*    */ 
/*    */ public class CBR_Inserter
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Inserter";
/*    */   
/*    */   public CBR_Inserter() {
/* 17 */     super((AbstractRelic)new Inserter());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void onEquip() {
/* 25 */     this.counter = 0;
/*    */   }
/*    */   
/*    */   public void atTurnStartPostDraw() {
/* 29 */     if (this.counter == -1) {
/* 30 */       this.counter += 2;
/*    */     } else {
/* 32 */       this.counter++;
/*    */     } 
/*    */     
/* 35 */     if (this.counter == 2) {
/* 36 */       this.counter = 0;
/* 37 */       flash();
/* 38 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 39 */       addToBot((AbstractGameAction)new EnemyIncreaseMaxOrbAction(1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 45 */     return new CBR_Inserter();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Inserter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */