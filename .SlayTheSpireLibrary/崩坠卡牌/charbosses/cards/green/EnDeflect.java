/*    */ package charbosses.cards.green;
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
/*    */ public class EnDeflect
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Deflect";
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Deflect");
/*    */ 
/*    */   
/*    */   public EnDeflect() {
/* 20 */     super("downfall_Charboss:Deflect", cardStrings.NAME, "green/skill/deflect", 0, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 21 */     this.baseBlock = 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 26 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 31 */     if (!this.upgraded) {
/* 32 */       upgradeName();
/* 33 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 39 */     return (AbstractCard)new EnDeflect();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnDeflect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */