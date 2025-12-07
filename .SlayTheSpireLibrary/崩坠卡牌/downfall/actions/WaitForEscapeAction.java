/*    */ package downfall.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WaitForEscapeAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 16 */     for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 17 */       if (m.isEscaping && !m.escaped) {
/*    */         
/* 19 */         tickDuration();
/*    */         
/*    */         return;
/*    */       } 
/*    */     } 
/* 24 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\WaitForEscapeAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */