/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnSeeingRed
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Seeing Red";
/* 18 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Seeing Red");
/*    */ 
/*    */   
/*    */   public EnSeeingRed() {
/* 22 */     super("downfall_Charboss:Seeing Red", cardStrings.NAME, "red/skill/seeing_red", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.NONE, AbstractMonster.Intent.BUFF);
/* 23 */     this.exhaust = true;
/* 24 */     this.energyGeneratedIfPlayed = 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 29 */     addToBot((AbstractGameAction)new EnemyGainEnergyAction(2));
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 34 */     if (!this.upgraded) {
/* 35 */       upgradeName();
/* 36 */       upgradeBaseCost(0);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 42 */     return (AbstractCard)new EnSeeingRed();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnSeeingRed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */