/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.ConfusionPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.SneckoEye;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CBR_SneckoEye
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "SneckoEye";
/*    */   
/*    */   public CBR_SneckoEye() {
/* 22 */     super((AbstractRelic)new SneckoEye());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 27 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 32 */     this.owner.masterHandSize++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip() {
/* 37 */     this.owner.masterHandSize--;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atBattleStartPreDraw() {
/* 43 */     flash();
/* 44 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new ConfusionPower((AbstractCreature)this.owner)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void atPreBattle() {}
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 53 */     return new CBR_SneckoEye();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_SneckoEye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */