/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.localization.LocalizedStrings;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Lantern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_Lantern
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "Lantern";
/*    */   
/*    */   public CBR_Lantern() {
/* 26 */     super((AbstractRelic)new Lantern());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 30 */     return this.DESCRIPTIONS[0] + this.DESCRIPTIONS[1] + LocalizedStrings.PERIOD;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 35 */     flash();
/* 36 */     addToBot((AbstractGameAction)new EnemyGainEnergyAction(1));
/* 37 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 43 */     return new CBR_Lantern();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Lantern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */