/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.TeardropLocket;
/*    */ 
/*    */ 
/*    */ public class CBR_TeardropLocket
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "TeardropLocket";
/*    */   
/*    */   public CBR_TeardropLocket() {
/* 18 */     super((AbstractRelic)new TeardropLocket());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 27 */     flash();
/* 28 */     addToTop((AbstractGameAction)new EnemyChangeStanceAction("Calm"));
/* 29 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/*    */   }
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new CBR_TeardropLocket();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_TeardropLocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */