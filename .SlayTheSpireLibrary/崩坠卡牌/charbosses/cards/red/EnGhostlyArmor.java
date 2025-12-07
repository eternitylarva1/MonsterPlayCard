/*    */ package charbosses.cards.red;
/*    */ 
/*    */ import charbosses.actions.utility.EnemyExhaustAllEtherealAction;
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
/*    */ public class EnGhostlyArmor
/*    */   extends AbstractBossCard {
/*    */   public static final String ID = "downfall_Charboss:Ghostly Armor";
/* 17 */   private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("Ghostly Armor");
/*    */ 
/*    */   
/*    */   public EnGhostlyArmor() {
/* 21 */     super("downfall_Charboss:Ghostly Armor", cardStrings.NAME, "red/skill/ghostly_armor", 1, cardStrings.DESCRIPTION, AbstractCard.CardType.SKILL, AbstractCard.CardColor.RED, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF, AbstractMonster.Intent.DEFEND);
/* 22 */     this.isEthereal = true;
/* 23 */     this.baseBlock = 10;
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer p, AbstractMonster m) {
/* 28 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
/*    */   }
/*    */ 
/*    */   
/*    */   public void triggerOnEndOfPlayerTurn() {
/* 33 */     addToTop((AbstractGameAction)new EnemyExhaustAllEtherealAction());
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 38 */     if (!this.upgraded) {
/* 39 */       upgradeName();
/* 40 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 46 */     return (AbstractCard)new EnGhostlyArmor();
/*    */   }
/*    */ }


/* Location:              C:\Users\gaoming\Desktop\杀戮尖塔 mod\Downfall.jar!\charbosses\cards\red\EnGhostlyArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */