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
/*    */ public class EnBootSequence extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:BootSequence";
/*    */   
/*    */   public EnBootSequence() {
/* 17 */     super("downfall_Charboss:BootSequence", cardStrings.NAME, "blue/skill/boot_sequence", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.BLUE, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 18 */     this.isInnate = true;
/* 19 */     this.exhaust = true;
/* 20 */     this.baseBlock = 10;
/*    */   }
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 24 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */   
/*    */   public void upgrade() {
/* 28 */     if (!this.upgraded) {
/* 29 */       upgradeName();
/* 30 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 36 */     return (AbstractCard)new EnBootSequence();
/*    */   }
/*    */ 
/*    */   
/* 40 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("BootSequence");
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\blue\EnBootSequence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */