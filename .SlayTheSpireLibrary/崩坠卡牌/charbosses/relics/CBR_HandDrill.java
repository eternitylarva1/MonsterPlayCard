/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.HandDrill;
/*    */ 
/*    */ public class CBR_HandDrill extends AbstractCharbossRelic {
/*    */   public CBR_HandDrill() {
/* 14 */     super((AbstractRelic)new HandDrill());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public static final String ID = "HandDrill";
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 24 */     return new CBR_HandDrill();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockBroken(AbstractCreature m) {
/* 29 */     flash();
/* 30 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 31 */     addToBot((AbstractGameAction)new ApplyPowerAction(m, (AbstractCreature)this.owner, (AbstractPower)new VulnerablePower(m, 2, true), 2));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_HandDrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */