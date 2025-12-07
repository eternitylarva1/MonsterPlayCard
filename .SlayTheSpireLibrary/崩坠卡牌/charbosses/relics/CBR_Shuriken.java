/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.Shuriken;
/*    */ 
/*    */ public class CBR_Shuriken extends AbstractCharbossRelic {
/*    */   public CBR_Shuriken() {
/* 17 */     super((AbstractRelic)new Shuriken());
/* 18 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 23 */     return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1] + '\001' + this.DESCRIPTIONS[2];
/*    */   }
/*    */   public static final String ID = "Shuriken";
/*    */   
/*    */   public void onPlayerEndTurn() {
/* 28 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 33 */     if (card instanceof charbosses.cards.AbstractBossCard && card.type == AbstractCard.CardType.ATTACK) {
/* 34 */       this.counter++;
/* 35 */       if (this.counter % 3 == 0) {
/* 36 */         this.counter = 0;
/* 37 */         flash();
/* 38 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractCharBoss.boss, this));
/* 39 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractCharBoss.boss, (AbstractCreature)AbstractCharBoss.boss, (AbstractPower)new StrengthPower((AbstractCreature)AbstractCharBoss.boss, 1), 1));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 46 */     this.counter = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 51 */     return new CBR_Shuriken();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_Shuriken.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */