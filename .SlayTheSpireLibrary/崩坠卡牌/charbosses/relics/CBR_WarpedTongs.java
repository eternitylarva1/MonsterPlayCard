/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyUpgradeRandomCardAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.WarpedTongs;
/*    */ 
/*    */ public class CBR_WarpedTongs extends AbstractCharbossRelic {
/*    */   public CBR_WarpedTongs() {
/* 13 */     super((AbstractRelic)new WarpedTongs());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public static final String ID = "WarpedTongs";
/*    */   
/*    */   public void atTurnStartPostDraw() {
/* 23 */     flash();
/* 24 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/* 25 */     addToBot((AbstractGameAction)new EnemyUpgradeRandomCardAction());
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 30 */     return new CBR_WarpedTongs();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_WarpedTongs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */