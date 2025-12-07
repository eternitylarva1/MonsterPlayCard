/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyHaltAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnHalt
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Halt";
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Halt");
/*    */ 
/*    */   
/*    */   public EnHalt() {
/* 22 */     super("downfall_Charboss:Halt", cardStrings.NAME, "purple/skill/halt", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 23 */     this.block = this.baseBlock = 3;
/* 24 */     this.magicNumber = this.baseMagicNumber = 9;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 29 */     applyPowers();
/* 30 */     addToBot((AbstractGameAction)new EnemyHaltAction(m, this.block, this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 35 */     this.baseBlock += 6 + this.timesUpgraded * 4;
/* 36 */     this.baseMagicNumber = this.baseBlock;
/* 37 */     super.applyPowers();
/* 38 */     this.magicNumber = this.block;
/* 39 */     this.isMagicNumberModified = this.isBlockModified;
/* 40 */     this.baseBlock -= 6 + this.timesUpgraded * 4;
/* 41 */     super.applyPowers();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 46 */     if (!this.upgraded) {
/* 47 */       upgradeName();
/* 48 */       upgradeBlock(1);
/* 49 */       this.baseMagicNumber = this.baseBlock + 6 + this.timesUpgraded * 4;
/* 50 */       this.upgradedMagicNumber = this.upgradedBlock;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 56 */     return (AbstractCard)new EnHalt();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnHalt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */