/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PlatedArmorPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.ThreadAndNeedle;
/*    */ 
/*    */ 
/*    */ public class CBR_ThreadAndNeedle
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "ThreadAndNeedle";
/*    */   
/*    */   public CBR_ThreadAndNeedle() {
/* 19 */     super((AbstractRelic)new ThreadAndNeedle());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0] + '\004' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 28 */     flash();
/* 29 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new PlatedArmorPower((AbstractCreature)this.owner, 4), 4));
/* 30 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 35 */     return new CBR_ThreadAndNeedle();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_ThreadAndNeedle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */