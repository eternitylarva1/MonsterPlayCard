/*    */ package charbosses.relics;
/*    */ import charbosses.powers.general.EnemyVigorPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Akabeko;
/*    */ 
/*    */ public class CBR_Akabeko extends AbstractCharbossRelic {
/*    */   public CBR_Akabeko() {
/* 12 */     super((AbstractRelic)new Akabeko());
/*    */   } public static final String ID = "Akabeko";
/*    */   public String getUpdatedDescription() {
/* 15 */     return this.DESCRIPTIONS[0] + '\b' + this.DESCRIPTIONS[1];
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 19 */     flash();
/* 20 */     addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new EnemyVigorPower((AbstractCreature)this.owner, 8), 8));
/*    */   }
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 24 */     return new CBR_Akabeko();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Akabeko.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */