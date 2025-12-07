/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnMasterOfStrategy
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Master of Strategy";
/*    */   
/*    */   public EnMasterOfStrategy() {
/* 19 */     super("downfall_Charboss:Master of Strategy", cardStrings.NAME, "colorless/skill/master_of_strategy", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.NONE);
/* 20 */     this.baseMagicNumber = this.magicNumber = 3;
/* 21 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 29 */     if (!this.upgraded) {
/* 30 */       upgradeName();
/* 31 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 38 */     return 10;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 42 */     return (AbstractCard)new EnMasterOfStrategy();
/*    */   }
/*    */ 
/*    */   
/* 46 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Master of Strategy");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnMasterOfStrategy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */