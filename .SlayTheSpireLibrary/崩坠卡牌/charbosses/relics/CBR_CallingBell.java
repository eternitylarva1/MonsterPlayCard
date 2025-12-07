/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.badlogic.gdx.math.MathUtils;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.helpers.input.InputHelper;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CallingBell;
/*    */ 
/*    */ public class CBR_CallingBell extends AbstractCharbossRelic {
/*    */   public static final String ID = "CallingBell";
/*    */   
/*    */   public CBR_CallingBell() {
/* 13 */     super((AbstractRelic)new CallingBell());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 23 */     super.update();
/* 24 */     if (this.hb.hovered && InputHelper.justClickedLeft) {
/* 25 */       CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
/* 26 */       flash();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 32 */     return new CBR_CallingBell();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_CallingBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */