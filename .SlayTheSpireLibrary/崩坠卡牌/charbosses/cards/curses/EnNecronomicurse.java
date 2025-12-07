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
/*    */ public class EnNecronomicurse
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Necronomicurse";
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Necronomicurse");
/*    */ 
/*    */   
/*    */   public EnNecronomicurse() {
/* 20 */     super("downfall_Charboss:Necronomicurse", cardStrings.NAME, "curse/necronomicurse", -2, cardStrings.DESCRIPTION, AbstractCard.CardType.CURSE, AbstractCard.CardColor.CURSE, AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.NONE);
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
/* 34 */     return (AbstractCard)new EnNecronomicurse();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\curses\EnNecronomicurse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */