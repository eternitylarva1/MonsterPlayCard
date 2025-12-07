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
/*    */ public class EnPerseverance extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Perseverance";
/*    */   
/*    */   public EnPerseverance() {
/* 17 */     super("downfall_Charboss:Perseverance", cardStrings.NAME, "purple/skill/perseverance", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 18 */     this.baseBlock = 5;
/* 19 */     this.baseMagicNumber = 2;
/* 20 */     this.magicNumber = this.baseMagicNumber;
/* 21 */     this.selfRetain = true;
/*    */   }
/*    */   
/*    */   public EnPerseverance(int preBlock) {
/* 25 */     this();
/* 26 */     this.baseBlock += preBlock;
/* 27 */     this.block = this.baseBlock;
/*    */   }
/*    */   
/*    */   public void onRetained() {
/* 31 */     upgradeBlock(this.magicNumber);
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 35 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeBlock(2);
/* 42 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 48 */     return (AbstractCard)new EnPerseverance();
/*    */   }
/*    */ 
/*    */   
/* 52 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Perseverance");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnPerseverance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */