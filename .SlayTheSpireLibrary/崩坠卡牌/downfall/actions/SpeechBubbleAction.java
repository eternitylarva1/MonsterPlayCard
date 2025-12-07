/*    */ package downfall.actions;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.vfx.SpeechBubble;
/*    */ 
/*    */ public class SpeechBubbleAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   public String Dialog;
/*    */   private AbstractMonster m;
/*    */   private float duration;
/*    */   
/*    */   public SpeechBubbleAction(String Dialog, AbstractMonster m, float duration) {
/* 16 */     this.Dialog = Dialog;
/* 17 */     this.m = m;
/* 18 */     this.duration = duration;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     AbstractDungeon.effectList.add(new SpeechBubble(this.m.hb.cX + this.m.dialogX, this.m.hb.cY + this.m.dialogY, this.duration, this.Dialog, false));
/* 25 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\actions\SpeechBubbleAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */