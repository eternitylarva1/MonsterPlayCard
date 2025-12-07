/*    */ package charbosses.cards.hermit;
/*    */ 
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
/*    */ import com.megacrit.cardcrawl.powers.PlatedArmorPower;
/*    */ import hermit.cards.Dive;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ public class EnDive
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Dive";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(Dive.ID);
/*    */   
/*    */   public EnDive() {
/* 24 */     super("downfall_Charboss:Dive", cardStrings.NAME, "hermitResources/images/cards/dive.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND_BUFF);
/* 25 */     this.baseBlock = 8;
/* 26 */     this.baseMagicNumber = this.magicNumber = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 31 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 32 */     AbstractPower concentration = this.owner.getPower("downfall:HermitConcentrationPower");
/* 33 */     if (concentration != null && concentration.amount > 0) {
/* 34 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new PlatedArmorPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 40 */     this.intentActive = false;
/* 41 */     this.intent = AbstractMonster.Intent.DEFEND;
/* 42 */     createIntent();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 47 */     if (!this.upgraded) {
/* 48 */       upgradeName();
/* 49 */       upgradeMagicNumber(1);
/* 50 */       upgradeBlock(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 57 */     return (AbstractCard)new EnDive();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnDive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */