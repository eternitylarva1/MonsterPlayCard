/*    */ package downfall.cards.curses;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import downfall.downfallMod;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PrideStandard
/*    */   extends AbstractCard
/*    */ {
/*    */   public static final String ID = "Pride";
/*    */   
/*    */   public PrideStandard() {
/* 25 */     super("Pride", cardStrings.NAME, "curse/pride", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.CURSE, AbstractCard.CardTarget.SELF);
/* 26 */     this.exhaust = true;
/* 27 */     this.isInnate = true;
/* 28 */     this.tags.add(downfallMod.DOWNFALL_CURSE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */   
/*    */   public void triggerOnEndOfTurnForPlayingCard() {
/* 35 */     addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(makeStatEquivalentCopy(), 1, false, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 42 */     return new PrideStandard();
/*    */   }
/*    */ 
/*    */   
/* 46 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Pride");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\downfall\cards\curses\PrideStandard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */