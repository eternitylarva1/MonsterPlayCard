/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.LoseStrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.MutagenicStrength;
/*    */ 
/*    */ public class CBR_Mutagenic
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_Mutagenic() {
/* 16 */     super((AbstractRelic)new MutagenicStrength());
/*    */   }
/*    */   public static final String ID = "MutagenicStrength";
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1] + '\003' + this.DESCRIPTIONS[2];
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 25 */     flash();
/* 26 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new StrengthPower((AbstractCreature)this.owner, 3), 3));
/* 27 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new LoseStrengthPower((AbstractCreature)this.owner, 3), 3));
/* 28 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 33 */     return new CBR_Mutagenic();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Mutagenic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */