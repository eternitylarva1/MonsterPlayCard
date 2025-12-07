/*    */ package charbosses.cards.hermit;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ import hermit.cards.Gestalt;
/*    */ import hermit.characters.hermit;
/*    */ import hermit.powers.Rugged;
/*    */ 
/*    */ public class EnGestalt extends AbstractHermitBossCard {
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(Gestalt.ID); public static final String ID = "downfall_Charboss:Gestalt";
/*    */   
/*    */   public EnGestalt() {
/* 21 */     super("downfall_Charboss:Gestalt", cardStrings.NAME, "hermitResources/images/cards/gestalt.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 22 */     this.baseMagicNumber = this.magicNumber = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 27 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new Rugged((AbstractCreature)m, 2), 2));
/* 28 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new VulnerablePower((AbstractCreature)m, this.magicNumber, true), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnExhaust() {
/* 33 */     if (AbstractCharBoss.boss != null && AbstractCharBoss.boss.chosenArchetype instanceof ArchetypeAct2WheelOfFateNewAge) {
/* 34 */       ((ArchetypeAct2WheelOfFateNewAge)AbstractCharBoss.boss.chosenArchetype).removeCardFromDeck(this.uuid);
/*    */     }
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeMagicNumber(-1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 48 */     return (AbstractCard)new EnGestalt();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnGestalt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */