/*    */ package charbosses.relics;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.UseCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ import com.megacrit.cardcrawl.relics.OrangePellets;
/*    */ 
/*    */ public class CBR_OrangePellets extends AbstractCharbossRelic {
/*    */   public static final String ID = "OrangePellets";
/*    */   private static boolean SKILL = false;
/*    */   private static boolean POWER = false;
/*    */   private static boolean ATTACK = false;
/*    */   
/*    */   public CBR_OrangePellets() {
/* 19 */     super((AbstractRelic)new OrangePellets());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getUpdatedDescription() {
/* 24 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip() {
/* 29 */     this.owner.increaseMaxHp(7, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 34 */     SKILL = false;
/* 35 */     POWER = false;
/* 36 */     ATTACK = false;
/*    */   }
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 40 */     if (card.type == AbstractCard.CardType.ATTACK) {
/* 41 */       ATTACK = true;
/* 42 */     } else if (card.type == AbstractCard.CardType.SKILL) {
/* 43 */       SKILL = true;
/* 44 */     } else if (card.type == AbstractCard.CardType.POWER) {
/* 45 */       POWER = true;
/*    */     } 
/*    */     
/* 48 */     if (ATTACK && SKILL && POWER) {
/* 49 */       flash();
/* 50 */       addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)this.owner, this));
/* 51 */       addToBot((AbstractGameAction)new RemoveDebuffsAction((AbstractCreature)this.owner));
/* 52 */       SKILL = false;
/* 53 */       POWER = false;
/* 54 */       ATTACK = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 62 */     return new CBR_OrangePellets();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\relics\CBR_OrangePellets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */