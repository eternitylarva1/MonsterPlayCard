/*    */ package charbosses.cards.status;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ public class EnDazed
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Dazed";
/* 15 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Dazed");
/*    */ 
/*    */   
/*    */   public EnDazed() {
/* 19 */     super("downfall_Charboss:Dazed", cardStrings.NAME, "status/dazed", -2, cardStrings.DESCRIPTION, AbstractCard.CardType.STATUS, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.MAGIC);
/* 20 */     this.isEthereal = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 33 */     return (AbstractCard)new EnDazed();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\status\EnDazed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */