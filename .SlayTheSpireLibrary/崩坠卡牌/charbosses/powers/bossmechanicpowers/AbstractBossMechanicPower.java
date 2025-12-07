/*    */ package charbosses.powers.bossmechanicpowers;
/*    */ 
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
/*    */ public abstract class AbstractBossMechanicPower
/*    */   extends AbstractPower
/*    */ {
/*    */   private float timer;
/*    */   private boolean firstTurn = true;
/*    */   
/*    */   public void update(int slot) {
/* 21 */     super.update(slot);
/*    */   }
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
/*    */   public void atStartOfTurn() {
/* 48 */     super.atStartOfTurn();
/* 49 */     this.firstTurn = false;
/*    */   }
/*    */   
/*    */   public void playApplyPowerSfx() {}
/*    */   
/*    */   public void PreRoundLoseBlock() {}
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\powers\bossmechanicpowers\AbstractBossMechanicPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */