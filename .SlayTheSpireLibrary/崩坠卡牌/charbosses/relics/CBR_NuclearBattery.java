/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyChannelAction;
/*    */ import charbosses.orbs.EnemyPlasma;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.orbs.AbstractOrb;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.NuclearBattery;
/*    */ 
/*    */ public class CBR_NuclearBattery
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_NuclearBattery() {
/* 13 */     super((AbstractRelic)new NuclearBattery());
/*    */   }
/*    */   public static final String ID = "NuclearBattery";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     flash();
/* 24 */     addToTop((AbstractGameAction)new EnemyChannelAction((AbstractOrb)new EnemyPlasma()));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new CBR_NuclearBattery();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_NuclearBattery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */