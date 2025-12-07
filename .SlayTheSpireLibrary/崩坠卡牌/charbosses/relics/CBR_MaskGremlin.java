/*    */ package charbosses.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.GremlinMask;
/*    */ 
/*    */ public class CBR_MaskGremlin extends AbstractCharbossRelic {
/*    */   public CBR_MaskGremlin() {
/* 12 */     super((AbstractRelic)new GremlinMask());
/*    */   }
/*    */   public static final String ID = "CBRMaskOfGremlin";
/*    */   
/*    */   public void atBattleStart() {
/* 17 */     flash();
/* 18 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 19 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new WeakPower((AbstractCreature)this.owner, 1, false), 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 25 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 30 */     return new CBR_MaskGremlin();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_MaskGremlin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */