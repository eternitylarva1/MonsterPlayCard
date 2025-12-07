/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.stances.AbstractEnemyStance;
/*    */ import charbosses.stances.EnRealWrathStance;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnTantrum
/*    */   extends AbstractStanceChangeCard
/*    */ {
/*    */   boolean realWrath = false;
/*    */   
/*    */   public EnTantrum() {
/* 27 */     super("downfall_Charboss:Tantrum", cardStrings.NAME, "purple/attack/tantrum", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_BUFF);
/* 28 */     this.baseDamage = 3;
/* 29 */     this.baseMagicNumber = 3;
/* 30 */     this.magicNumber = 3;
/* 31 */     this.isMultiDamage = true;
/* 32 */     this.intentMultiAmt = this.magicNumber;
/* 33 */     this.shuffleBackIntoDrawPile = true;
/*    */   }
/*    */   
/*    */   public EnTantrum(boolean realWrath) {
/* 37 */     this();
/* 38 */     this.realWrath = realWrath;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 42 */     for (int i = 0; i < this.magicNumber; i++) {
/* 43 */       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*    */     }
/* 45 */     addToBot((AbstractGameAction)new EnemyChangeStanceAction("Real Wrath"));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 50 */     return super.getPriority(hand) + 10;
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 54 */     if (!this.upgraded) {
/* 55 */       upgradeName();
/* 56 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 62 */     return (AbstractCard)new EnTantrum();
/*    */   }
/*    */ 
/*    */   
/* 66 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Tantrum");
/*    */   
/*    */   public static final String ID = "downfall_Charboss:Tantrum";
/*    */   
/*    */   public AbstractEnemyStance changeStanceForIntentCalc(AbstractEnemyStance previousStance) {
/* 71 */     return (AbstractEnemyStance)new EnRealWrathStance();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnTantrum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */