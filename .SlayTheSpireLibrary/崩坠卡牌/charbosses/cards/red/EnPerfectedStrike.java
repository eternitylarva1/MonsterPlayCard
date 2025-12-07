/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ public class EnPerfectedStrike
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Perfected Strike";
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Perfected Strike");
/*    */ 
/*    */   
/*    */   public EnPerfectedStrike() {
/* 24 */     super("downfall_Charboss:Perfected Strike", cardStrings.NAME, "red/attack/perfected_strike", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 25 */     this.baseDamage = 6;
/* 26 */     this.baseMagicNumber = 2;
/* 27 */     this.magicNumber = this.baseMagicNumber;
/* 28 */     this.tags.add(AbstractCard.CardTags.STRIKE);
/* 29 */     this.tags.add(downfallMod.CHARBOSS_ATTACK);
/*    */   }
/*    */   
/*    */   public static int countCards() {
/* 33 */     int count = 6;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     return count;
/*    */   }
/*    */   
/*    */   public static boolean isStrike(AbstractCard c) {
/* 55 */     return c.hasTag(AbstractCard.CardTags.STRIKE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 60 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/*    */   }
/*    */ 
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 65 */     int realBaseDamage = this.baseDamage;
/* 66 */     this.baseDamage += this.magicNumber * countCards();
/* 67 */     super.calculateCardDamage(mo);
/* 68 */     this.baseDamage = realBaseDamage;
/* 69 */     this.isDamageModified = (this.damage != this.baseDamage);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 85 */     return (AbstractCard)new EnPerfectedStrike();
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 90 */     if (!this.upgraded) {
/* 91 */       upgradeName();
/* 92 */       upgradeMagicNumber(1);
/* 93 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 94 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnPerfectedStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */