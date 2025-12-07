/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.BagOfMarbles;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_BagOfMarbles
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "BagOfMarbles";
/*    */   
/*    */   public CBR_BagOfMarbles() {
/* 21 */     super((AbstractRelic)new BagOfMarbles());
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 25 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 29 */     flash();
/* 30 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 31 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this.owner, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, 1, true), 1, true));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 38 */     return new CBR_BagOfMarbles();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_BagOfMarbles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */