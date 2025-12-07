/*    */ package downfall.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import downfall.monsters.NeowBoss;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NeowReturnAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private NeowBoss owner;
/*    */   
/*    */   public NeowReturnAction(NeowBoss owner) {
/* 16 */     this.owner = owner;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 21 */     NeowBoss.neowboss.escape();
/* 22 */     NeowBoss.neowboss = null;
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
/* 37 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\NeowReturnAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */