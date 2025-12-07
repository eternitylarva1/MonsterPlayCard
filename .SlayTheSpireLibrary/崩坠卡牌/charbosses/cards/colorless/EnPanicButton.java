/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.NoBlockPower;
/*    */ 
/*    */ public class EnPanicButton
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:PanicButton";
/*    */   
/*    */   public EnPanicButton() {
/* 22 */     super("downfall_Charboss:PanicButton", cardStrings.NAME, "colorless/skill/panic_button", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 23 */     this.baseBlock = 30;
/* 24 */     this.baseMagicNumber = 2;
/* 25 */     this.magicNumber = this.baseMagicNumber;
/* 26 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 31 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new NoBlockPower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 35 */     if (!this.upgraded) {
/* 36 */       upgradeName();
/* 37 */       upgradeBlock(10);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
/* 44 */     return !m.hasPower("NoBlockPower");
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 48 */     return (AbstractCard)new EnPanicButton();
/*    */   }
/*    */ 
/*    */   
/* 52 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("PanicButton");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnPanicButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */