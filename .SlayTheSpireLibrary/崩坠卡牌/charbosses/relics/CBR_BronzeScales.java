/*    */ package charbosses.relics;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.BronzeScales;
/*    */ 
/*    */ public class CBR_BronzeScales extends AbstractCharbossRelic {
/*    */   public CBR_BronzeScales() {
/* 11 */     super((AbstractRelic)new BronzeScales());
/*    */   }
/*    */   public static final String ID = "BronzeScales";
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 16 */     return new CBR_BronzeScales();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 21 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 26 */     flash();
/* 27 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new ThornsPower((AbstractCreature)this.owner, 3), 3));
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_BronzeScales.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */