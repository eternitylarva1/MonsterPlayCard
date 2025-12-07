/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.actions.common.EnemyGainEnergyAction;
/*    */ import charbosses.cards.AbstractBossCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ 
/*    */ public class EnSentinel
/*    */   extends AbstractBossCard
/*    */ {
/*    */   public static final String ID = "downfall_Charboss:Sentinel";
/* 19 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Sentinel");
/*    */ 
/*    */   
/*    */   public EnSentinel() {
/* 23 */     super("downfall_Charboss:Sentinel", cardStrings.NAME, "red/skill/sentinel", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 24 */     this.baseBlock = 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 29 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)p, (AbstractCreature)p, this.block));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnExhaust() {
/* 34 */     if (this.upgraded) {
/* 35 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new EnemyGainEnergyAction(3));
/*    */     } else {
/* 37 */       AbstractDungeon.actionManager.addToTop((AbstractGameAction)new EnemyGainEnergyAction(2));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 43 */     if (!this.upgraded) {
/* 44 */       upgradeName();
/* 45 */       upgradeBlock(3);
/* 46 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 47 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 53 */     return (AbstractCard)new EnSentinel();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnSentinel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */