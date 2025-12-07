/*    */ package charbosses.cards.green;
/*    */ 
/*    */ import charbosses.actions.common.EnemyDiscardAction;
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
/*    */ public class EnSurvivor
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Survivor";
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Survivor");
/*    */ 
/*    */   
/*    */   public EnSurvivor() {
/* 21 */     super("downfall_Charboss:Survivor", cardStrings.NAME, "green/skill/survivor", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.GREEN, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 22 */     this.baseBlock = 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 27 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/* 28 */     addToBot((AbstractGameAction)new EnemyDiscardAction((AbstractCreature)m, (AbstractCreature)m, 1, false));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 34 */     if (!this.upgraded) {
/* 35 */       upgradeName();
/* 36 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 42 */     return (AbstractCard)new EnSurvivor();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\green\EnSurvivor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */