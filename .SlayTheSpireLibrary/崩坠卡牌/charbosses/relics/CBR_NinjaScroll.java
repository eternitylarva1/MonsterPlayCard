/*    */ package charbosses.relics;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInHandAction;
/*    */ import charbosses.cards.colorless.EnShiv;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.NinjaScroll;
/*    */ 
/*    */ 
/*    */ public class CBR_NinjaScroll
/*    */   extends AbstractCharbossRelic
/*    */ {
/*    */   public static final String ID = "NinjaScroll";
/*    */   
/*    */   public CBR_NinjaScroll() {
/* 19 */     super((AbstractRelic)new NinjaScroll());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 28 */     flash();
/* 29 */     addToTop((AbstractGameAction)new EnemyMakeTempCardInHandAction((AbstractCard)new EnShiv(), 3));
/* 30 */     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 35 */     return new CBR_NinjaScroll();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_NinjaScroll.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */