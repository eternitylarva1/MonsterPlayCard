/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyIncreaseMaxOrbAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.RunicCapacitor;
/*    */ 
/*    */ public class CBR_RunicCapacitor
/*    */   extends AbstractCharbossRelic {
/*    */   public static final String ID = "Runic Capacitor";
/*    */   
/*    */   public CBR_RunicCapacitor() {
/* 15 */     super((AbstractRelic)new RunicCapacitor());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     flash();
/* 24 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 25 */     addToBot((AbstractGameAction)new EnemyIncreaseMaxOrbAction(3));
/*    */   }
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new CBR_RunicCapacitor();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_RunicCapacitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */