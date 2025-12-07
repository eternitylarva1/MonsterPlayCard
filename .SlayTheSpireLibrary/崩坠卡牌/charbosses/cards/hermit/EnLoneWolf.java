/*    */ package charbosses.cards.hermit;
/*    */ 
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import hermit.cards.LoneWolf;
/*    */ import hermit.characters.hermit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnLoneWolf
/*    */   extends AbstractHermitBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:LoneWolf";
/* 19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(LoneWolf.ID);
/*    */   
/*    */   public EnLoneWolf() {
/* 22 */     super("downfall_Charboss:LoneWolf", cardStrings.NAME, "hermitResources/images/cards/lone_wolf.png", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, hermit.Enums.COLOR_YELLOW, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.NONE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 32 */     if (!this.upgraded) {
/* 33 */       upgradeName();
/* 34 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 41 */     return (AbstractCard)new EnLoneWolf();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\hermit\EnLoneWolf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */