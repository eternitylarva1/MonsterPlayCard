/*    */ package charbosses.cards.colorless;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.RitualDaggerAction;
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
/*    */ public class EnRitualDagger
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:RitualDagger";
/* 21 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("RitualDagger");
/*    */ 
/*    */   
/*    */   public EnRitualDagger() {
/* 25 */     super("downfall_Charboss:RitualDagger", cardStrings.NAME, "colorless/attack/ritual_dagger", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
/*    */     
/* 27 */     this.misc = 15;
/* 28 */     this.baseMagicNumber = 3;
/* 29 */     this.magicNumber = this.baseMagicNumber;
/* 30 */     this.baseDamage = this.misc;
/* 31 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     addToBot((AbstractGameAction)new RitualDaggerAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), this.magicNumber, this.uuid));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 41 */     return (AbstractCard)new EnRitualDagger();
/*    */   }
/*    */   
/*    */   public void applyPowers() {
/* 45 */     this.baseBlock = this.misc;
/* 46 */     super.applyPowers();
/* 47 */     initializeDescription();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeMagicNumber(2);
/* 53 */       upgradeName();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\colorless\EnRitualDagger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */