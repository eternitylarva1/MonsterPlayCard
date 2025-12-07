/*    */ package downfall.actions;
/*    */ 
/*    */ import basemod.BaseMod;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import downfall.cards.curses.Scatterbrained;
/*    */ import expansioncontent.actions.EchoACardAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScatterbrainedAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public void update() {
/* 17 */     int amount = BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size();
/* 18 */     Scatterbrained scatterbrained = new Scatterbrained();
/* 19 */     addToTop((AbstractGameAction)new EchoACardAction((AbstractCard)scatterbrained, amount));
/* 20 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\ScatterbrainedAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */