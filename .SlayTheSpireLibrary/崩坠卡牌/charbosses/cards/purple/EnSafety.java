/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ public class EnSafety
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Safety";
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Safety");
/*    */ 
/*    */   
/*    */   public EnSafety() {
/* 22 */     super("downfall_Charboss:Safety", cardStrings.NAME, "colorless/skill/safety", -2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 23 */     this.baseBlock = 12;
/* 24 */     this.selfRetain = true;
/* 25 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atTurnStart() {
/* 30 */     super.atTurnStart();
/* 31 */     upgradeBaseCost(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 41 */     if (!this.upgraded) {
/* 42 */       upgradeName();
/* 43 */       upgradeBlock(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 49 */     return (AbstractCard)new EnSafety();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnSafety.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */