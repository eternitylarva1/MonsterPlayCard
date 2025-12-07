/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.powers.BossIntangiblePower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.IncenseBurner;
/*    */ 
/*    */ public class CBR_IncenseBurner
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_IncenseBurner(int counter) {
/* 16 */     super((AbstractRelic)new IncenseBurner());
/* 17 */     this.counter = counter;
/*    */   }
/*    */   public static final String ID = "IncenseBurner";
/*    */   public CBR_IncenseBurner() {
/* 21 */     this(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 26 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnergyRecharge() {
/* 36 */     if (this.counter == -1) {
/* 37 */       this.counter += 2;
/*    */     } else {
/* 39 */       this.counter++;
/*    */     } 
/* 41 */     if (this.counter == 6) {
/* 42 */       this.counter = 0;
/* 43 */       beginLongPulse();
/* 44 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/* 45 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, null, (AbstractPower)new BossIntangiblePower((AbstractCreature)AbstractCharBoss.boss, 1), 1));
/*    */     } else {
/* 47 */       stopPulse();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 53 */     return new CBR_IncenseBurner();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_IncenseBurner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */