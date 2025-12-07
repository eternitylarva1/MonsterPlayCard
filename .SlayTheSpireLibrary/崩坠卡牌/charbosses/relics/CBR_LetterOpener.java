/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.LetterOpener;
/*    */ 
/*    */ public class CBR_LetterOpener extends AbstractCharbossRelic {
/*    */   public static final String ID = "LetterOpener";
/*    */   
/*    */   public CBR_LetterOpener() {
/* 18 */     super((AbstractRelic)new LetterOpener());
/* 19 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0] + '\005' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 29 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 34 */     if (card instanceof charbosses.cards.AbstractBossCard && card.type == AbstractCard.CardType.SKILL) {
/* 35 */       this.counter++;
/* 36 */       if (this.counter % 3 == 0) {
/* 37 */         flash();
/* 38 */         this.counter = 0;
/* 39 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 40 */         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, new DamageInfo((AbstractCreature)this.owner, 5, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 47 */     this.counter = -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 52 */     return new CBR_LetterOpener();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_LetterOpener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */