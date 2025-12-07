/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnBrilliance
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Brilliance";
/*    */   
/*    */   public EnBrilliance() {
/* 28 */     super("downfall_Charboss:Brilliance", cardStrings.NAME, "purple/attack/brilliance", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/* 29 */     this.baseDamage = 12;
/* 30 */     this.baseMagicNumber = 0;
/* 31 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyPowers() {
/* 36 */     int realBaseDamage = this.baseDamage;
/* 37 */     this.baseMagicNumber = AbstractCharBoss.boss.mantraGained;
/* 38 */     this.baseDamage += this.baseMagicNumber;
/* 39 */     super.applyPowers();
/* 40 */     this.baseDamage = realBaseDamage;
/* 41 */     this.isDamageModified = (this.damage != this.baseDamage);
/* 42 */     super.applyPowers();
/*    */   }
/*    */   
/*    */   public void calculateCardDamage(AbstractMonster mo) {
/* 46 */     this.baseMagicNumber = AbstractCharBoss.boss.mantraGained;
/* 47 */     int realBaseDamage = this.baseDamage;
/* 48 */     this.baseDamage += this.baseMagicNumber;
/* 49 */     super.calculateCardDamage(mo);
/* 50 */     this.baseDamage = realBaseDamage;
/* 51 */     this.isDamageModified = (this.damage != this.baseDamage);
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 55 */     this.damage += this.magicNumber;
/* 56 */     calculateCardDamage(m);
/* 57 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE, true));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 61 */     if (!this.upgraded) {
/* 62 */       upgradeName();
/* 63 */       upgradeDamage(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 69 */     return (AbstractCard)new EnBrilliance();
/*    */   }
/*    */ 
/*    */   
/* 73 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Brilliance");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnBrilliance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */