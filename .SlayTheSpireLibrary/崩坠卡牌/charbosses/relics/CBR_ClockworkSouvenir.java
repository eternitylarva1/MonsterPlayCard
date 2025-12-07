/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.ClockworkSouvenir;
/*    */ 
/*    */ public class CBR_ClockworkSouvenir extends AbstractCharbossRelic {
/*    */   public CBR_ClockworkSouvenir() {
/* 13 */     super((AbstractRelic)new ClockworkSouvenir(), AbstractRelic.RelicTier.UNCOMMON);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 18 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   public static final String ID = "ClockworkSouvenir";
/*    */   
/*    */   public void atBattleStart() {
/* 23 */     flash();
/* 24 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new ArtifactPower((AbstractCreature)this.owner, 1), 1));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 29 */     return new CBR_ClockworkSouvenir();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_ClockworkSouvenir.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */