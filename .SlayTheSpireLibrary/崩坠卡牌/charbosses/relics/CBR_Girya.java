/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Girya;
/*    */ 
/*    */ public class CBR_Girya extends AbstractCharbossRelic {
/*    */   public CBR_Girya(int counter) {
/* 16 */     super((AbstractRelic)new Girya());
/* 17 */     this.counter = counter;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 22 */     return this.DESCRIPTIONS[0] + (CardCrawlGame.languagePack.getRelicStrings("Girya")).DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public static final String ID = "Girya";
/*    */   
/*    */   public void atBattleStart() {
/* 28 */     if (this.counter != 0) {
/* 29 */       flash();
/* 30 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new StrengthPower((AbstractCreature)AbstractCharBoss.boss, this.counter), this.counter));
/* 31 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 38 */     return new CBR_Girya(0);
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Girya.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */