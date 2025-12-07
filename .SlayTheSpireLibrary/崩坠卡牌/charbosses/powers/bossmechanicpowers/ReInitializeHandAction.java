/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
/*    */ import charbosses.bosses.Hermit.CharBossHermit;
/*    */ import charbosses.bosses.Hermit.NewAge.ArchetypeAct2WheelOfFateNewAge;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ReInitializeHandAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private final AbstractPower power;
/*    */   
/*    */   ReInitializeHandAction(AbstractCreature hermit, AbstractPower power) {
/* 64 */     this.source = hermit;
/* 65 */     this.target = hermit;
/* 66 */     this.power = power;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 71 */     if (!shouldCancelAction()) {
/* 72 */       this.power.flash();
/* 73 */       ((ArchetypeAct2WheelOfFateNewAge)((CharBossHermit)this.source).chosenArchetype).reInitializeHand();
/*    */     } 
/* 75 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\ReInitializeHandAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */