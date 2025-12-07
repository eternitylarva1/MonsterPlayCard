/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemySpotWeaknessPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnSpotWeakness
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:SpotWeakness";
/* 26 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Spot Weakness");
/*    */ 
/*    */   
/*    */   public EnSpotWeakness() {
/* 30 */     super("downfall_Charboss:SpotWeakness", cardStrings.NAME, "red/skill/spot_weakness", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 31 */     this.baseMagicNumber = 3;
/* 32 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 38 */     if (m.hasPower("Spot Weakness")) {
/* 39 */       EnemySpotWeaknessPower power = (EnemySpotWeaknessPower)m.getPower("Spot Weakness");
/* 40 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new StrengthPower((AbstractCreature)m, power.amount), power.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 46 */     return super.getPriority(hand) + 10;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 52 */     if (!this.upgraded) {
/* 53 */       upgradeName();
/* 54 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return (AbstractCard)new EnSpotWeakness();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnSpotWeakness.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */