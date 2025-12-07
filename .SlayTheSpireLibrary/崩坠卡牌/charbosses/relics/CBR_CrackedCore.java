/*    */ package charbosses.relics;
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.orbs.EnemyLightning;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.CrackedCore;
/*    */ 
/*    */ public class CBR_CrackedCore extends AbstractCharbossRelic {
/*    */   public CBR_CrackedCore() {
/* 11 */     super((AbstractRelic)new CrackedCore());
/*    */   }
/*    */   public static final String ID = "CrakedCore";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 16 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 21 */     flash();
/* 22 */     addToTop((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyLightning()));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 27 */     return new CBR_CrackedCore();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_CrackedCore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */