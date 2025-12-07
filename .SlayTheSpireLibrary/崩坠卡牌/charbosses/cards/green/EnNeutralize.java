/*    */ package charbosses.cards.green;
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
/*    */ import com.megacrit.cardcrawl.powers.WeakPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class EnNeutralize
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Neutralize";
/* 23 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Neutralize");
/*    */ 
/*    */   
/*    */   public EnNeutralize() {
/* 27 */     super("downfall_Charboss:Neutralize", cardStrings.NAME, "green/attack/neutralize", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_DEBUFF);
/* 28 */     this.baseDamage = 3;
/* 29 */     this.baseMagicNumber = 1;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/* 31 */     this.magicValue = 3;
/* 32 */     this.artifactConsumedIfPlayed = 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 37 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
/* 38 */     if (p.hasPower("Weakened")) {
/* 39 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber, true), this.magicNumber));
/*    */     } else {
/* 41 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber + 1, true), this.magicNumber + 1));
/*    */     } 
/*    */   }
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 46 */     return autoPriority() + 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 51 */     if (!this.upgraded) {
/* 52 */       upgradeName();
/* 53 */       upgradeDamage(1);
/* 54 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return (AbstractCard)new EnNeutralize();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnNeutralize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */