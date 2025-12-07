/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.bosses.AbstractCharBoss;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.DoubleYourBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.ExhaustAllEtherealAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class EnEntrench
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Entrench";
/* 20 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Entrench");
/*    */ 
/*    */   
/*    */   public EnEntrench() {
/* 24 */     super("downfall_Charboss:Entrench", cardStrings.NAME, "red/skill/entrench", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 25 */     this.baseBlock = this.block = 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 30 */     addToBot((AbstractGameAction)new DoubleYourBlockAction((AbstractCreature)m));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfPlayerTurn() {
/* 35 */     addToTop((AbstractGameAction)new ExhaustAllEtherealAction());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 40 */     if (AbstractCharBoss.boss != null && !AbstractCharBoss.boss.isDead) {
/* 41 */       return AbstractCharBoss.boss.currentBlock + 8;
/*    */     }
/* 43 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 48 */     if (!this.upgraded) {
/* 49 */       upgradeName();
/* 50 */       upgradeBaseCost(1);
/* 51 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 52 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 58 */     return (AbstractCard)new EnEntrench();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnEntrench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */