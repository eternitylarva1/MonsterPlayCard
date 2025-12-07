/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.BufferPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.FossilizedHelix;
/*    */ 
/*    */ public class CBR_FossilizedHelix
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "FossilizedHelix";
/*    */   
/*    */   public CBR_FossilizedHelix() {
/* 18 */     super((AbstractRelic)new FossilizedHelix());
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 22 */     flash();
/* 23 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 24 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new BufferPower((AbstractCreature)this.owner, 1), 1));
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 28 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new CBR_FossilizedHelix();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_FossilizedHelix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */