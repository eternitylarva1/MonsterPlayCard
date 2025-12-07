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
/*    */ public class EnDefendGreen
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Defend_G";
/* 16 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Defend_G");
/*    */ 
/*    */   
/*    */   public EnDefendGreen() {
/* 20 */     super("downfall_Charboss:Defend_G", cardStrings.NAME, "green/skill/defend", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 21 */     this.baseBlock = 5;
/* 22 */     this.tags.add(AbstractCard.CardTags.STARTER_DEFEND);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 27 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 32 */     if (!this.upgraded) {
/* 33 */       upgradeName();
/* 34 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 40 */     return (AbstractCard)new EnDefendGreen();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnDefendGreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */