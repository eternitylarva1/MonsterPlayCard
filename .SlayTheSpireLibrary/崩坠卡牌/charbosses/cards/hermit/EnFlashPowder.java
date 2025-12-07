/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.bosses.Hermit.NewAge.ArchetypeAct2WheelOfFateNewAge;
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
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import hermit.cards.FlashPowder;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ public class EnFlashPowder
/*    */   extends AbstractHermitBossCard {
/*    */   public static final String ID = "downfall_Charboss:FlashPowder";
/* 22 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(FlashPowder.ID);
/*    */   
/*    */   public EnFlashPowder() {
/* 25 */     super("downfall_Charboss:FlashPowder", cardStrings.NAME, "hermitResources/images/cards/flash_powder.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND_DEBUFF);
/*    */ 
/*    */     
/* 28 */     this.baseMagicNumber = this.magicNumber = 1;
/* 29 */     this.baseBlock = 5;
/* 30 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 37 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)p, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.LIGHTNING));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnExhaust() {
/* 42 */     if (AbstractCharBoss.boss != null && AbstractCharBoss.boss.chosenArchetype instanceof ArchetypeAct2WheelOfFateNewAge) {
/* 43 */       ((ArchetypeAct2WheelOfFateNewAge)AbstractCharBoss.boss.chosenArchetype).removeCardFromDeck(this.uuid);
/*    */     }
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 48 */     if (!this.upgraded) {
/* 49 */       upgradeName();
/* 50 */       upgradeMagicNumber(1);
/* 51 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 58 */     return (AbstractCard)new EnFlashPowder();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnFlashPowder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */