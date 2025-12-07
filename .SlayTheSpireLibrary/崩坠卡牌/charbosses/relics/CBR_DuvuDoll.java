/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.helpers.PowerTip;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.DuVuDoll;
/*    */ 
/*    */ public class CBR_DuvuDoll extends AbstractCharbossRelic {
/*    */   public CBR_DuvuDoll() {
/* 15 */     super((AbstractRelic)new DuVuDoll());
/*    */   }
/*    */   public static final String ID = "DuvuDoll";
/*    */   public CBR_DuvuDoll(int counter) {
/* 19 */     this();
/* 20 */     this.counter = counter;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 25 */     return this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 30 */     return new CBR_DuvuDoll();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCounter(int c) {
/* 35 */     this.counter = c;
/* 36 */     if (this.counter == 0) {
/* 37 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
/*    */     } else {
/* 39 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3] + this.counter + this.DESCRIPTIONS[4];
/*    */     } 
/* 41 */     this.tips.clear();
/* 42 */     this.tips.add(new PowerTip(this.name, this.description));
/* 43 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 48 */     if (this.counter == 0) {
/* 49 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[2];
/*    */     } else {
/* 51 */       this.description = this.DESCRIPTIONS[0] + '\001' + this.DESCRIPTIONS[1] + this.DESCRIPTIONS[3] + this.counter + this.DESCRIPTIONS[4];
/*    */     } 
/* 53 */     this.tips.clear();
/* 54 */     this.tips.add(new PowerTip(this.name, this.description));
/* 55 */     initializeTips();
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 60 */     if (this.counter > 0) {
/* 61 */       flash();
/* 62 */       addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new StrengthPower((AbstractCreature)this.owner, this.counter), this.counter));
/* 63 */       addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_DuvuDoll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */