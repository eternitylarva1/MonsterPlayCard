/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import charbosses.powers.cardpowers.EnemyMentalFortressPower;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnMentalFortress
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:MentalFortress";
/*    */   
/*    */   public EnMentalFortress() {
/* 21 */     super("downfall_Charboss:MentalFortress", cardStrings.NAME, "purple/power/mental_fortress", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/* 22 */     this.baseMagicNumber = 4;
/* 23 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 27 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyMentalFortressPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 31 */     return (AbstractCard)new EnMentalFortress();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 35 */     if (!this.upgraded) {
/* 36 */       upgradeName();
/* 37 */       upgradeMagicNumber(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 44 */     return 100;
/*    */   }
/*    */ 
/*    */   
/* 48 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("MentalFortress");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnMentalFortress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */