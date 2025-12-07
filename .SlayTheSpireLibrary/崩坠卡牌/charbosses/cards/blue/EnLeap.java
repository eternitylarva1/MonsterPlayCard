/*    */ package charbosses.cards.blue;
/*    */ 
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnLeap extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Leap";
/*    */   
/*    */   public EnLeap() {
/* 17 */     super("downfall_Charboss:Leap", cardStrings.NAME, "blue/skill/leap", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 18 */     this.baseBlock = 9;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 23 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 27 */     if (!this.upgraded) {
/* 28 */       upgradeName();
/* 29 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 35 */     return (AbstractCard)new EnLeap();
/*    */   }
/*    */ 
/*    */   
/* 39 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Leap");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnLeap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */