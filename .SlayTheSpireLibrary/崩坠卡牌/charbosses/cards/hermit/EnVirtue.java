/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import hermit.cards.Virtue;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ 
/*    */ public class EnVirtue
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Virtue";
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(Virtue.ID);
/*    */   
/*    */   public EnVirtue() {
/* 23 */     super("downfall_Charboss:Virtue", cardStrings.NAME, "hermitResources/images/cards/virtue.png", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/*    */     
/* 25 */     this.selfRetain = true;
/* 26 */     this.magicNumber = this.baseMagicNumber = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 31 */     for (AbstractPower pow : m.powers) {
/* 32 */       if (pow.type == AbstractPower.PowerType.DEBUFF) {
/* 33 */         addToBot((AbstractGameAction)new ReducePowerAction((AbstractCreature)m, (AbstractCreature)m, pow.ID, this.magicNumber));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 40 */     if (!this.upgraded) {
/* 41 */       upgradeName();
/* 42 */       upgradeMagicNumber(1);
/* 43 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 50 */     return (AbstractCard)new EnVirtue();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnVirtue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */