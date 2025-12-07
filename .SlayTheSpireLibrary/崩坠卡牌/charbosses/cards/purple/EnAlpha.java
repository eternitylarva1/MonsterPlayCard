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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnAlpha
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Alpha";
/*    */   
/*    */   public EnAlpha() {
/* 26 */     super("Alpha", cardStrings.NAME, "purple/skill/alpha", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.PURPLE, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.MAGIC);
/* 27 */     this.exhaust = true;
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 31 */     return (AbstractCard)new EnAlpha();
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 35 */     if (!this.upgraded) {
/* 36 */       upgradeName();
/* 37 */       this.isInnate = true;
/* 38 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 39 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 45 */     if (this.usedOnce) {
/* 46 */       addToBot((AbstractGameAction)new EnemyMakeTempCardInDrawPileAction((AbstractCard)new EnBeta(), 1, true, true));
/*    */     } else {
/* 48 */       this.usedOnce = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPriority(ArrayList<AbstractCard> hand) {
/* 54 */     return 100;
/*    */   }
/*    */ 
/*    */   
/* 58 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Alpha");
/*    */   private boolean usedOnce;
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\purple\EnAlpha.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */