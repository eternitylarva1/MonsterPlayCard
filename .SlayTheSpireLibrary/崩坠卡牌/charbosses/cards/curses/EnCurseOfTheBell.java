/*    */ package charbosses.cards.curses;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ public class EnCurseOfTheBell
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:CurseOfTheBell";
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("CurseOfTheBell");
/*    */ 
/*    */   
/*    */   public EnCurseOfTheBell() {
/* 20 */     super("downfall_Charboss:CurseOfTheBell", cardStrings.NAME, "curse/curse_of_the_bell", -2, cardStrings.DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.NONE);
/* 21 */     SoulboundField.soulbound.set(this, Boolean.valueOf(true));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {}
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 34 */     return (AbstractCard)new EnCurseOfTheBell();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\curses\EnCurseOfTheBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */