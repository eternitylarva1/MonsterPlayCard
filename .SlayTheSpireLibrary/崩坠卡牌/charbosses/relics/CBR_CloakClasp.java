/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CloakClasp;
/*    */ 
/*    */ 
/*    */ public class CBR_CloakClasp
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "CloakClasp";
/*    */   
/*    */   public CBR_CloakClasp() {
/* 17 */     super((AbstractRelic)new CloakClasp());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void onPlayerEndTurn() {
/* 26 */     if (!AbstractCharBoss.boss.hand.group.isEmpty()) {
/* 27 */       flash();
/* 28 */       addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)null, AbstractCharBoss.boss.hand.group.size()));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 35 */     return (AbstractRelic)new CloakClasp();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_CloakClasp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */