/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
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
/*    */ import com.megacrit.cardcrawl.powers.VulnerablePower;
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ 
/*    */ public class EnUppercut
/*    */   extends AbstractBossCard {
/*    */   public EnUppercut() {
/* 21 */     super("downfall_Charboss:Uppercut", cardStrings.NAME, "red/attack/uppercut", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_DEBUFF);
/* 22 */     this.baseDamage = 13;
/* 23 */     this.baseMagicNumber = 1;
/* 24 */     this.magicNumber = this.baseMagicNumber;
/* 25 */     this.artifactConsumedIfPlayed = 2;
/*    */   }
/*    */   public static final String ID = "downfall_Charboss:Uppercut";
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 29 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 30 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
/* 31 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new VulnerablePower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 35 */     if (!this.upgraded) {
/* 36 */       upgradeName();
/* 37 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 43 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Uppercut");
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 47 */     return (AbstractCard)new EnUppercut();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnUppercut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */