/*    */ package downfall.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class LoseGoldAction extends AbstractGameAction {
/*    */   public LoseGoldAction(int amount) {
/*  8 */     this.amount = amount;
/*    */   }
/*    */   
/*    */   public void update() {
/* 12 */     AbstractDungeon.player.loseGold(this.amount);
/* 13 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\LoseGoldAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */