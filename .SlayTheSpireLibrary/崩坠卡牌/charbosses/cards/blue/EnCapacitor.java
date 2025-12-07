/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.actions.orb.EnemyIncreaseMaxOrbAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
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
/*    */ public class EnCapacitor
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Capacitor";
/*    */   
/*    */   public EnCapacitor() {
/* 28 */     super("downfall_Charboss:Capacitor", cardStrings.NAME, "blue/power/capacitor", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.POWER, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.BUFF);
/* 29 */     this.baseMagicNumber = 2;
/* 30 */     this.magicNumber = this.baseMagicNumber;
/* 31 */     this.alwaysDisplayText = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 36 */     addToBot((AbstractGameAction)new EnemyIncreaseMaxOrbAction(this.magicNumber));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 40 */     if (!this.upgraded) {
/* 41 */       upgradeName();
/* 42 */       upgradeMagicNumber(1);
/* 43 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 44 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 51 */     return 20;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 55 */     return (AbstractCard)new EnCapacitor();
/*    */   }
/*    */ 
/*    */   
/* 59 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Capacitor");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnCapacitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */