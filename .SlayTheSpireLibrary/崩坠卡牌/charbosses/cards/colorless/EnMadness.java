/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnMadness
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Madness";
/*    */   
/*    */   public EnMadness() {
/* 19 */     super("downfall_Charboss:Madness", cardStrings.NAME, "colorless/skill/madness", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 20 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 28 */     if (!this.upgraded) {
/* 29 */       upgradeName();
/* 30 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 37 */     return (AbstractCard)new EnMadness();
/*    */   }
/*    */ 
/*    */   
/* 41 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Madness");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnMadness.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */