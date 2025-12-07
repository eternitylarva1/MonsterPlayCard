/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.bosses.Hermit.NewAge.ArchetypeAct2WheelOfFateNewAge;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.ArtifactPower;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnPanacea
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Panacea";
/*    */   
/*    */   public EnPanacea() {
/* 26 */     super("downfall_Charboss:Panacea", cardStrings.NAME, "colorless/skill/panacea", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 27 */     this.baseMagicNumber = 1;
/* 28 */     this.magicNumber = this.baseMagicNumber;
/* 29 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 33 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new ArtifactPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnExhaust() {
/* 38 */     if (AbstractCharBoss.boss != null && AbstractCharBoss.boss.chosenArchetype instanceof ArchetypeAct2WheelOfFateNewAge)
/* 39 */       ((ArchetypeAct2WheelOfFateNewAge)AbstractCharBoss.boss.chosenArchetype).removeCardFromDeck(this.uuid); 
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 43 */     if (!this.upgraded) {
/* 44 */       upgradeName();
/* 45 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 52 */     return (AbstractCard)new EnPanacea();
/*    */   }
/*    */ 
/*    */   
/* 56 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Panacea");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnPanacea.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */