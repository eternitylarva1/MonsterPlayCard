/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.blue.GeneticAlgorithm;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnGeneticAlgorithm extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Genetic Algorithm";
/*    */   
/*    */   public EnGeneticAlgorithm() {
/* 18 */     super("Genetic Algorithm", cardStrings.NAME, "blue/skill/genetic_algorithm", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 19 */     this.baseMagicNumber = 1;
/* 20 */     this.magicNumber = this.baseMagicNumber;
/* 21 */     this.baseBlock = 14;
/* 22 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public EnGeneticAlgorithm(int preBlock) {
/* 26 */     this();
/* 27 */     this.baseBlock = preBlock;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 32 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 36 */     if (!this.upgraded) {
/* 37 */       upgradeMagicNumber(1);
/* 38 */       upgradeName();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 44 */     return (AbstractCard)new GeneticAlgorithm();
/*    */   }
/*    */ 
/*    */   
/* 48 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Genetic Algorithm");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnGeneticAlgorithm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */