/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import hermit.cards.Deadeye;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ public class EnDeadeye extends AbstractHermitBossCard {
/*    */   public static final String ID = "downfall_Charboss:Deadeye";
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(Deadeye.ID);
/*    */   
/*    */   public EnDeadeye() {
/* 23 */     super("downfall_Charboss:Deadeye", cardStrings.NAME, "hermitResources/images/cards/deadeye.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_BUFF);
/* 24 */     this.baseDamage = 5;
/* 25 */     this.baseMagicNumber = this.magicNumber = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 31 */     AbstractPower concentration = this.owner.getPower("downfall:HermitConcentrationPower");
/* 32 */     if (concentration != null && concentration.amount > 0) {
/* 33 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onSpecificTrigger() {
/* 39 */     this.intentActive = false;
/* 40 */     this.intent = AbstractMonster.Intent.ATTACK;
/* 41 */     createIntent();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 46 */     if (!this.upgraded) {
/* 47 */       upgradeName();
/* 48 */       upgradeDamage(2);
/* 49 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 55 */     return (AbstractCard)new EnDeadeye();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnDeadeye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */