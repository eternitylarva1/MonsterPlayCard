/*    */ package downfall.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ 
/*    */ public class LoseRelicAction extends AbstractGameAction {
/*    */   public String relicID;
/*    */   
/*    */   public LoseRelicAction(String relicID) {
/* 10 */     this.relicID = relicID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 15 */     AbstractDungeon.player.loseRelic(this.relicID);
/* 16 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\LoseRelicAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */