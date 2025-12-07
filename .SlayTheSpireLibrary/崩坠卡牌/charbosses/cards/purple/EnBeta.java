/*    */ package charbosses.cards.purple;
/*    */ 
/*    */ import charbosses.actions.common.EnemyMakeTempCardInDrawPileAction;
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
/*    */ public class EnBeta
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Beta";
/*    */   
/*    */   public EnBeta() {
/* 23 */     super("downfall_Charboss:Beta", cardStrings.NAME, "colorless/skill/beta", 2, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.COLORLESS, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.MAGIC);
/* 24 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 28 */     return (AbstractCard)new EnBeta();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 32 */     if (!this.upgraded) {
/* 33 */       upgradeName();
/* 34 */       upgradeBaseCost(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 40 */     if (this.usedOnce) {
/* 41 */       addToBot((AbstractGameAction)new EnemyMakeTempCardInDrawPileAction((AbstractCard)new EnOmega(), 1, true, true));
/*    */     } else {
/* 43 */       this.usedOnce = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 49 */     return 100;
/*    */   }
/*    */ 
/*    */   
/* 53 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Alpha");
/*    */   private boolean usedOnce;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnBeta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */