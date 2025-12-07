/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.unique.EnemyChangeStanceAction;
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.stances.AbstractEnemyStance;
/*    */ import charbosses.stances.EnCalmStance;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnInnerPeace
/*    */   extends AbstractStanceChangeCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:InnerPeace";
/*    */   
/*    */   public EnInnerPeace() {
/* 21 */     super("downfall_Charboss:InnerPeace", cardStrings.NAME, "purple/skill/inner_peace", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 22 */     this.baseMagicNumber = 3;
/* 23 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 27 */     addToBot((AbstractGameAction)new EnemyChangeStanceAction("Calm"));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 32 */     if (AbstractCharBoss.boss.stance instanceof EnCalmStance) return 4; 
/* 33 */     return super.getPriority(hand) + 10;
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 37 */     if (!this.upgraded) {
/* 38 */       upgradeName();
/* 39 */       upgradeMagicNumber(1);
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 44 */     return (AbstractCard)new EnInnerPeace();
/*    */   }
/*    */ 
/*    */   
/* 48 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("InnerPeace");
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractEnemyStance changeStanceForIntentCalc(AbstractEnemyStance previousStance) {
/* 53 */     return (AbstractEnemyStance)new EnCalmStance();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnInnerPeace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */