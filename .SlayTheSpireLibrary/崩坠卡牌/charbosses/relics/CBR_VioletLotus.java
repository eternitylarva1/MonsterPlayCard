/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.VioletLotus;
/*    */ import com.megacrit.cardcrawl.stances.AbstractStance;
/*    */ 
/*    */ public class CBR_VioletLotus extends AbstractCharbossRelic {
/*    */   public static final String ID = "VioletLotus";
/*    */   
/*    */   public CBR_VioletLotus() {
/* 14 */     super((AbstractRelic)new VioletLotus());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
/* 22 */     if (!prevStance.ID.equals(newStance.ID) && prevStance.ID.equals("Calm")) {
/* 23 */       flash();
/* 24 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new EnemyGainEnergyAction(1));
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new CBR_VioletLotus();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_VioletLotus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */