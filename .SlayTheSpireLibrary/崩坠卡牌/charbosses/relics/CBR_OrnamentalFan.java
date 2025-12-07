/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.OrnamentalFan;
/*    */ 
/*    */ public class CBR_OrnamentalFan
/*    */   extends AbstractCharbossRelic {
/*    */   public CBR_OrnamentalFan() {
/* 15 */     super((AbstractRelic)new OrnamentalFan());
/*    */   }
/*    */   public static final String ID = "OrnamentalFan";
/*    */   public String getUpdatedDescription() {
/* 19 */     return this.DESCRIPTIONS[0] + '\004' + this.DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayerEndTurn() {
/* 24 */     this.counter = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atBattleStart() {
/* 29 */     this.counter = 0;
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 33 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 34 */       this.counter++;
/* 35 */       if (this.counter % 3 == 0) {
/* 36 */         flash();
/* 37 */         this.counter = 0;
/* 38 */         addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 39 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, 4));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 48 */     return new CBR_OrnamentalFan();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_OrnamentalFan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */